import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
/**
 * Controls robot driving, stopping and turning.
 * @author Olav Valle
 * @version 20191023
 */

public class MotorDriver
{

    //declare motors
    private Brick brick;
    private RegulatedMotor right;
    private RegulatedMotor left;
    private RegulatedMotor[] motors;

    /**
     * constructor for MotorDriver class.
     */
    public MotorDriver()
    {

        brick = BrickFinder.getDefault();

        left = new EV3LargeRegulatedMotor(MotorPort.D);
        right = new EV3LargeRegulatedMotor(MotorPort.A);

        motors = new RegulatedMotor[]{left};
        right.synchronizeWith(motors);

    }

    public void rightMotorSpeed()
    {

    }
    /**
     * Drives robot forward.
     */
    public void forward()
    {
        right.setSpeed(450);
        left.setSpeed(450);

        right.startSynchronization(); // starts the synchronization of right and left motors.
        right.forward();
        left.forward();
        right.endSynchronization();
    }

    /**
     * Stops both motors.
     */
    public void stop()
    {
        right.startSynchronization();         // starts the synchronization of right and left motors.
        left.stop();
        right.stop();
        right.endSynchronization();
    }

    /**
     * Drives backwards a short distance.
     */
    public void backwards(){
        right.setSpeed(450);
        left.setSpeed(450);
        right.startSynchronization();
        // starts the synchronization of right and left motors.

        right.rotate(-180);
        left.rotate(-180);
        right.waitComplete();
        left.waitComplete();

        right.endSynchronization();
        //ends sync between motors.
    }

    public void tweakTurn()
    {

    }
    /**
     * Turns right motor 300 deg forward and left motor -60 deg backwards, turning robot approx. 90 degrees to the left.
     */
    public void turnLeft()
    {
        right.setSpeed(900);
        left.setSpeed(900);
        right.startSynchronization();
        // starts the synchronization of right and left motors.

        right.rotate(300);
        left.rotate(-60);

        right.waitComplete();
        left.waitComplete();

        right.endSynchronization(); //ends sync between motors.
    }
}
