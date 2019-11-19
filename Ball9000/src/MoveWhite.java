import lejos.robotics.subsumption.Behavior;

/**
 * The Behavior that should become active when a white ball is detected.
 */


public class MoveWhite implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;
    private Vertical vertical;

    //TODO add constructor with motors and sensors as parameters
    /**
     * Constructor for the MoveWhite behavior, which should request control when a white ball is present.
     * @param hori the horizontal motor
     * @param claw
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    public MoveWhite(Horizontal hori, Vertical vert, Claw claw, SenseColour col, Pressure pres)
    {

        this.horizontal = hori;
        this.vertical = vert;
        this.claw = claw;
        this.colour = col;
        this.pressure = pres;
    }

    /**
     * Requests control if the ball sensor reports a white ball.
     * @return true if ball is white.
     */
    @Override
    public boolean takeControl()
    {
        return (colour.getColor() == 6) && (pressure.isPressed());
        //TODO add method to take control when SenseColor returns a white reading
        //TODO and so that button is NOT pushed
    }


    /**
     * Sets the MoveWhite behavior's suppressed field to true,
     * which interrupts MoveWhite action.
     */
    @Override
    public void suppress()
    {
        suppressed = true;
    }

    @Override
    public void action()
    {
        suppressed = false;

        horizontal.rotateTo(-20); // arm is centered to 0 at a position slightly to the right of the ball tray
        claw.openClaw();
        vertical.changeElevation(220); //moves arm down to ball height
        claw.closeClaw();
        vertical.changeElevation(110); //moves arm and ball up to cup height
        horizontal.rotateTo(-330); //moves arm to white cup
        vertical.changeElevation(180); //lowers arm into cup
        claw.openClaw(); //drops ball
        //MoveWhite is finished, MoveHome should take over

        /*TODO:
        1. lower arm
        2. grab ball
        3. raise arm
        4. rotate to white cup
            (4.b lower arm?)
        5. drop ball
        6. finish
        */
    }
}
