import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class FollowLine {

    public static void main(String[] args)
    {



        Brick brick = BrickFinder.getDefault();

        //motors
        RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.D);
        RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.A);

        RegulatedMotor[] motors = new RegulatedMotor[]{left};
        right.synchronizeWith(motors);



        //sensors

        // sensor port for front light sensor
        Port s1 = brick.getPort("S1");
        // front light sensor
        EV3ColorSensor leftSensor = new EV3ColorSensor(s1);
        SampleProvider leftReader = leftSensor.getMode("Red");
        float[] leftSample = new float[leftReader.sampleSize()];
        // sensor port for side light sensor
        Port s2 = brick.getPort("S2");
        // side light sensor
        EV3ColorSensor rightSensor = new EV3ColorSensor(s2);
        SampleProvider rightReader = rightSensor.getMode("Red");
        float[] rightSample = new float[rightReader.sampleSize()];

        int scalar = 5; // TODO: reconsider scalar
        int speedLeft = 0;
        int speedRight = 0;

        int black = 10; // 10 higher than avg black reading
        int grey = 50; // 10 lower than avg grey reading

        while(true){
            right.startSynchronization();
            //start the motor sync

            // set motor acceleration
            //right.setAcceleration(600);
            //left.setAcceleration(600);

            // fetch sample
            rightReader.fetchSample(rightSample, 0);
            leftReader.fetchSample(leftSample, 0);

            if ((leftSample[0]*100) < 10){ // if left sensor sees line, do 90* spin left
                right.setSpeed(250);
                left.setSpeed(250);

                right.startSynchronization();

                right.rotate(180);
                left.rotate(-180);

                right.endSynchronization();

                right.waitComplete();
                left.waitComplete();

                // set speeds back to calculated to avoid running away
            /*    right.setSpeed(speedRight);
                left.setSpeed(speedLeft);*/

            }
            //calculate values as difference between known values and RLI
            speedRight = (int) (((grey) - (rightSample[0]*100))*scalar);
            // right should be negative when reading is "too" grey
            speedLeft = (int) (((rightSample[0]*100) - (black))*scalar);
            // left should be negative when reading is "too" black

            // set speeds to calculated values
            right.setSpeed(speedRight);
            left.setSpeed(speedLeft);


            if(speedLeft <= 0 && speedRight >= 0){ //
                left.stop();
                //left.setSpeed(speedLeft*3);
                left.backward();
                right.forward();
            }
            else if(speedLeft >= 0 && speedRight <= 0){
                right.stop();
                //right.setSpeed(speedRight*3);
                right.backward();
                left.forward();
            }
            else{
                right.forward();
                left.forward();
            }

            // start movement


            //end motor sync
            right.endSynchronization();
        }
    }


}
