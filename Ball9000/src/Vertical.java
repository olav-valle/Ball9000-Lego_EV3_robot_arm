import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control vertical movement of arm.
 */
public class Vertical {


    private RegulatedMotor vertical;

    /**
     * Horizontal class constructor.
     * @param arm the motor that raises and lowers the arm
     */
    public Vertical(EV3LargeRegulatedMotor arm) {
        vertical = arm;
        vertical.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());
        vertical.setAcceleration(CalibrationValues.MOVEMENT_ACCELERATION.getValue());
    }

    //TODO find acceleration values?

    /**
     * changes arm elevation to specified level
     * @param elevation the limit angle to move arm to
     */
    public void changeElevation(int elevation)
    {
        vertical.rotateTo(elevation);
    }

    /**
     * Moves arm a set distance
     * @param distance the distance to move arm, in number of degrees from current position
     */
    public void moveArm(int distance)
    {
        vertical.rotate(distance, true);
    }

    /**
     * Resets arm motor tachometer to 0.
     */
    public void resetTacho() {
        vertical.resetTachoCount();
    }


    /**
     * Moves arm upwards until stopped.
     */
    public void raiseArm()
    {
        vertical.backward();
    }

    /**
     * Moves arm downward until stopped
     */
    public void lowerArm()
    {
        vertical.forward();
    }

    /**
    * Returns true if arm motor is stalled.
     */
    public boolean getArmStalled()
    {
        return vertical.isStalled();
    }
    /**
     * Immediately stops all arm motor movements
     */
    public void stop() {
        vertical.stop();
    }
}
