import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.port.Port;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * The selection menu for starting robot functions
 */
public class ArmArbitrator {

    //brick
    private EV3 ev3;

    //ports
    private Port s1;
    private Port s2;
    private Port s3;

    //sensors
    private Pressure pressure;
    private SenseColour colour;

    //motors
    private Horizontal horizontal;
    private Vertical vertical;
    private Claw claw;

    //behaviors

    private Wait wait;
    private ArmCalibrate cali;
    private MoveHome home;
    private MoveBall moveBall;
    /*private MoveWhite white;*/
    private Stop stop;

    //behavior array
    private Behavior[] bArray;

    //arbitrator
    private Arbitrator arb;



    /**
     * Constructs ports, Arbitrator, behaviors, sensors and movement classes.
     */
    public ArmArbitrator()
    {
        ev3 = (EV3) BrickFinder.getDefault();
        //sensor ports
        s1 = ev3.getPort("S1");
        s2 = ev3.getPort("S2");
        s3 = ev3.getPort("S3");
        //sensors
        colour = new SenseColour(s3, s1);
        pressure = new Pressure(s2);

        //motor objects
        //TODO init motors here, and pass as param to motor class constructors

        //motors classes
        vertical = new Vertical();
        horizontal = new Horizontal();
        claw = new Claw();


        // Behaviors
        wait = new Wait();
        cali = new ArmCalibrate();
        moveBall = new MoveBall(horizontal, vertical, claw, colour, pressure);
        home = new MoveHome(pressure, horizontal, claw, vertical);
        stop = new Stop();

        // Behavior array
        bArray = new Behavior[]{wait, home, moveBall, cali, stop};

        // Arbitrator
        arb = new Arbitrator(bArray);

    }

    public void start() {

        cali.action(); // uncomment to run calibration at start
        arb.go(); //uncomment to start arbitrator
        System.exit(0);
        //TODO fuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuck
    }//start

    /**
     * Nested behavior class which takes control when ESCAPE button is held,
     * and calls System.exit(0), which quits the program.
     */
    private class Stop implements Behavior {
        @Override
        public boolean takeControl() {
            return (Button.ESCAPE.isDown());
        }

        @Override
        public void action() {
            System.exit(0);
            //TODO change to arb.stop, to see if that works?
        }

        @Override
        public void suppress() {

        }
    }

    /**
     * Behavior that handles calibration of the robot motors.
     */
    public class ArmCalibrate implements Behavior {

        private boolean suppressed = false;
        public ArmCalibrate()
        {

        }

        @Override
        public boolean takeControl() {
            return (Button.ENTER.isDown());
        }
    /**
     * moves all motors to their intended zeroing position
     */
        @Override
        public void action() {
            suppressed = false;
            if(!suppressed) {

                claw.stallAndReset();
                claw.openClaw();
                while (colour.getDistance() < 0.7) {
                    vertical.moveArm(-300);
                }
                vertical.stop();
                vertical.resetTacho();
                //TODO add ball color calibration for startup
                home.action();
                suppress();
            }
        }

        @Override
        public void suppress() {
            this.suppressed = true;

        }
    }

}
