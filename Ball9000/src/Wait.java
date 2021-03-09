import lejos.robotics.subsumption.Behavior;

/**
 * The default behavior of the RobotArm. Holds arm upright, while waiting for a new ball to be discovered.
 */

public class Wait implements Behavior {

    private boolean supressed = false;

    public Wait()
    {

    }

    /**
     * Always requests control, but has lowest priority.
     * @return always true
     */
    @Override
    public boolean takeControl()
    {
        return true;
        //always wants control, but has lowest priority
    }

    /**
     * Sets the Wait behavior's suppressed field to true,
     * which interrupts Wait action.
     */
    @Override
    public void suppress()
    {
        supressed = true;
    }

    /**
     * The Wait behavior action keeps arm raised and stationary,
     * while robot waits for a higher priority.
     */
    @Override
    public void action()
    {
        supressed = false;
        while(!supressed){/*runs until suppressed by Arbitrator*/}
        //TODO: add method to keep robot arm raised and stationary


    }
}
