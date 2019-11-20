import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when the arm has to return to home position.
 */


public class MoveHome implements Behavior {

    private boolean suppressed = false; // supressed acts as off switch for action()
    private Claw claw;
    private Vertical vertical;
    private Horizontal horizontal; //the horizontal motor
    private Pressure touch; //the touch button

    public MoveHome(Pressure touch, Horizontal horizontal, Claw claw, Vertical vertical)
    {
        this.touch = touch;
        this.horizontal = horizontal;
        this.claw = claw;
        this.vertical = vertical;
    }

    /**
     * Requests control if touch button is not pressed.
     * @return true if it requests control, false if not
     */
    @Override
    public boolean takeControl()
    {
        return !touch.isPressed();
        //behavior requests control when pressure sensor is NOT pushed
    }


    /**
     * Sets the MoveHome behavior's suppressed field to true,
     * which interrupts MoveHome action.
     */
    @Override
    public void suppress()
    {
        suppressed = true;
    }

    /**+
     * Performs the MoveHome action,
     * which returns the arm to starting position
     * and resets rotation tachometer to zero.
     */
    @Override
    public void action()
    {
        this.suppressed = false;
        vertical.changeElevation(0); //raises arm to highest position
        claw.closeClaw(); //closes claw

        boolean startRotation = true;
        while (!this.suppressed) {

            //rotates arm back to known 0 position until suppressed
            // or interrupted by touch button

            //while touch is not pressed and suppressed = true
            if (startRotation && !touch.isPressed()) {
                horizontal.absoluteRotation(600);
                startRotation = false;
            }




            // if touch is pressed
            if (touch.isPressed()) {
                //touch button has been pressed.
                //halts motor movement
                horizontal.haltHorizontal();
                //we are home, so we reset tacho to 0.
                horizontal.resetTacho();
                //calling resetTacho stops any motor movement
                this.suppress();
            } //if
        }
        //TODO should this be two whiles, for !suppressed and touch.pressed separately?


        //TODO move arm back to home position, or until pressure sensor is pressed
        // implement gyro and tachometer sensors, as well as pressure, for higher accuracy.
        // possible to use pressure sensor as a way to calibrate gyro and tachometer?
    }//action
}
