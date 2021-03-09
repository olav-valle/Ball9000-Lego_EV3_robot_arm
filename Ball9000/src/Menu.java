/*
import lejos.hardware.Button;

*
 * Menu used for debugging and manual controll over Ball9000


public class Menu {

    private Menu() {
        //TODO change this to a lejos.utility.TextMenu
        // implementing choices "Start Arbitrator", "Calibrate", and "Manual Control"

        //TODO add app shutdown with ESCAPE button
        // while(Button.ESCAPE.isUp())
        // Exits program when ESCAPE is pressed


        //TODO add lejos.hardware.Button functions for testing of behaviors separately
        // i.e. up for Home, left for 90 deg, down for 180 deg, right for calibrate.
        // center button to activate/deactivate arbitrator?

        //TODO start with arbitrator off, and activate manually?

        //TODO possible to call a behavior directly with Behavior.action?

        boolean cont = true;
        while (cont) {
            switch (Button.waitForAnyPress()) {
                case (Button.ID_ENTER):
                    calibrate();
                    break;
                case (Button.ID_ESCAPE):
                    arb.stop();
                    cont = false;
                    break;
                case (Button.ID_LEFT):
                    white.action();
                    break;
                case (Button.ID_DOWN):
                    armTest();
                    break;
                case (Button.ID_UP):
                    home.action();
                    break;
                case (Button.ID_RIGHT):
                    clawTest();
                    break;
            }//switch
        }//while
    }

        private void armTest() {
        boolean armTest = true;
        while (armTest) {
            switch(Button.waitForAnyPress()) {
                case (Button.ID_DOWN):
                    vertical.changeElevation(0);
                    break;
                case (Button.ID_UP):
                    vertical.changeElevation(220);
                    break;
                case (Button.ID_ENTER):
                    while(colour.getDistance() < 0.7){
                        vertical.moveArm(-300);
                    }
                    vertical.stop();
                    vertical.resetTacho();
                    break;
                case (Button.ID_RIGHT):
                    vertical.changeElevation(110);
                    break;
                case (Button.ID_ESCAPE):
                    armTest = false;
                    break;
            }
        }
    }

    private void clawTest() {
        boolean clawTest = true;
        while(clawTest) {
            switch (Button.waitForAnyPress()) {
                case (Button.ID_UP):
                    claw.closeClaw();
                    break;
                case (Button.ID_DOWN):
                    claw.openClaw();
                    break;
                case (Button.ID_ESCAPE):
                    clawTest = false;
                    break;
                case (Button.ID_ENTER):
                    claw.findZero();
                    break;
            }
        }
    }
}
*/
