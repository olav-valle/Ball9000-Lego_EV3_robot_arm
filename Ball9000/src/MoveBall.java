import lejos.robotics.Color;
import lejos.robotics.subsumption.Behavior;
/**
 * The Behavior that should become active when a black ball is detected.
 */


public class MoveBall implements Behavior {

    private boolean suppressed = false;
    private Horizontal horizontal;
    private Vertical vertical;
    private Claw claw;
    private SenseColour colour;
    private Pressure pressure;

    /**
     * Constructor for the MoveBlack behavior, which should request control when a black ball is present.
     * @param hori the horizontal motor
     * @param vertical
     * @param claw
     * @param col the color sensors
     * @param pres the pressure sensor
     */
    //TODO add constructor with motors and sensors as parameters
    public MoveBall(Horizontal hori, Vertical vertical, Claw claw, SenseColour col, Pressure pres)
    {
        this.horizontal = hori;
        this.vertical = vertical;
        this.claw = claw;
        this.colour = col;
        this.pressure = pres;
    }


    /**
     * Requests control if the ball sensor reports a black ball.
     * @return true if ball is black && pressure sensor is pressed, else false.
     */
    @Override
    public boolean takeControl()
    {
        return ((colour.getColor() == Color.WHITE || colour.getColor() == Color.BLACK) && pressure.isPressed());
        /*colour.getColor() == 6 || colour.getColor() == 7*/
        //TODO add method to take control when SenseColor returns a black reading.
    }

    /**
     * Sets the MoveBlack behavior's suppressed field to true,
     * which interrupts MoveBlack action.
     */
    @Override
    public void suppress()
    {
        suppressed = true;
    }

    /**
     * Grabs any ball located on the platform, and deposits it in the correct place based on its colour.
     */
    @Override
    public void action()
    {
        suppressed = false;

        while(!suppressed) {


            while(pressure.isPressed()) {
                horizontal.rotateBackwards(); //lines arm up in front of platform
                //CalibrationValues.PLATFORM_HORIZONTAL.getValue()
            }//while
            horizontal.haltHorizontal(); //stop quickly if pressure button is not pressed

            while(colour.getDistance() > 0.25) {
                vertical.lowerArm(); //moves arm down to ball height
            }
            vertical.stop();

            //wait for the color sensor to deliver a new reading before continuing
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int ballColour = colour.getColor();

            // check if ball is still on platform, and suppress action if ball is missing
            if (ballColour != Color.BLACK && ballColour != Color.WHITE) {
                suppressed = true;
                continue;
            }
            claw.closeClaw(); //claw starts open, this closes it to grab ball

            vertical.changeElevation(CalibrationValues.MOVE_HEIGHT_VERT.getValue());

            if (ballColour == Color.BLACK) { //if ball is black
                horizontal.rotateTo(CalibrationValues.BLACK_CUP_HORIZONTAL.getValue());
            } else if (ballColour == Color.WHITE) { //if ball is white
                horizontal.rotateTo(CalibrationValues.WHITE_CUP_HORIZONTAL.getValue());
            }

            while(colour.getDistance() > 0.35) {
                vertical.lowerArm(); //moves arm down to ball height
            }
            vertical.stop();
            //vertical.changeElevation(CalibrationValues.CUP_VERT.getValue()); //lowers arm into cup

            claw.openClaw(); //drops ball
            suppressed = true;
        }//while(!suppressed)

    }//action
}//class
