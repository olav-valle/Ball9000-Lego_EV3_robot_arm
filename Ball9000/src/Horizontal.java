import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.robotics.RegulatedMotor;

/**
 * Class to control horizontal arm movement, i.e. rotation.
 */
@SuppressWarnings("WeakerAccess")
public class Horizontal {

    private RegulatedMotor horizontal;

    /**
     * Horizontal class constructor.
     * @param rotation the motor that rotates arm in the XY plane
     */
    public Horizontal(EV3LargeRegulatedMotor rotation) {
        horizontal = rotation;
        horizontal.setSpeed(CalibrationValues.MOVEMENT_SPEED.getValue());
        horizontal.setAcceleration(CalibrationValues.MOVEMENT_ACCELERATION.getValue());
    }

    //TODO find acceleration values?

    /**
     * Rotates motor to the specified angle value, limit < 0 rotates to left.
     * Angle limits have a ratio of approx. 3:1 to actual arm movement.
     * For example, a 90 degree turn to the left would be rotate(-280)
     * rotate(0) returns the arm to its initial starting point,
     * unless motor tachometer has been reset
     *
     * @param limit the angle position to rotate to
     */
    public void rotateTo(int limit) {
        horizontal.rotateTo(limit);
    }

    /**
     * Rotates the motor by a number of degrees.
     * @param degrees the number of degrees to rotate motor.
     */
    //TODO implement boolean return if motor stalls during rotation?

    public void absoluteRotation(int degrees)
    {
        horizontal.rotate(degrees, true);
    }

    /**
     * Halts the movement of the horizontal motor.
     */
    public void haltHorizontal()
    {

        horizontal.stop();
    }

    /**
     * Makes the robot arm rotate in the negative direction (towards the cups) until stopped
     */
    public void rotateBackwards()
    {
        horizontal.backward();
    }

    /**
     * Makes the robot arm rotate in the positive direction (towards start position) until stopped.
     */
    public void rotateForward(){
        horizontal.forward();
    }

    /**
     * Rotates motor to the zero position of its tachometer, with immediate return.
     * The position the motor moves to is the position the motor was in
     * when the tachometer was last reset, either during calibration or
     * when the resetTacho method was called.
     */
    @SuppressWarnings("unused")
    public void rotateHome()
    {
        horizontal.rotateTo(0, true);
    }

    /**
     * Sets the tachometer to 0, which immediately stops any motor movement.
     * Any further calls to absoluteRotation will use this position as 0.
     */
    public void resetTacho()
    {
        horizontal.resetTachoCount();
    }


}
