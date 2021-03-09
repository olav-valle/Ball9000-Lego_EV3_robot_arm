import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control claw operations.
 */
public class Claw {

    private RegulatedMotor claw;
    //TODO add constructor with motors as parameters

    /**
     * Constructor for Claw class.
     * @param medium the medium motor that controls the claw
     */
    public Claw(EV3MediumRegulatedMotor medium) {
        claw = medium;
        claw.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());
        claw.setAcceleration(CalibrationValues.MOVEMENT_ACCELERATION.getValue());

    }

    /**
     * Calibrates the cllaw by rotating the claw motor until the motor stalls because the claw is closed,
     * and then resets the tachometer in this position.
     */
    public void stallAndReset()
    {
        claw.setStallThreshold(3, 10); //sets low stall tolerance, to prevent overtightening of claw.
        while (!claw.isStalled()) {
            claw.forward();
            //if(claw.isStalled()){claw.stop();}
            System.out.println("claw is not stalled, rotating");
        }//while

            claw.resetTachoCount();

            System.out.println("claw is stalled, resetting tacho");
            System.out.println("Claw tacho is: " + claw.getTachoCount());

            claw.setStallThreshold(50, 1000);//sets stall tolerance back to default
    }


    /**
     * Opens the claw.
     */
    public void openClaw() {
        claw.rotateTo(CalibrationValues.CLAW_OPEN.getValue());
        System.out.println("Claw open. Thacho is: " + claw.getTachoCount());
        // -75 degrees is approx. to open from completely closed
    }

    /**
     * Closes claw until motor stalls, and then stops motor movement to prevent gears from slipping
     */
    public void closeClaw()
    {
        claw.setStallThreshold(3, 10); //sets low stall tolerance, to prevent overtightening of claw.

        while(!claw.isStalled()){
            claw.forward();
            //if(claw.isStalled()){claw.stop();}
        }
        claw.stop();
        System.out.println("Claw closed.  Thacho is: " + claw.getTachoCount());

        claw.setStallThreshold(50, 1000); //sets stall tolerance back to default
        //TODO find tacho count when stalled because of ball? THIS!!
    }

}
