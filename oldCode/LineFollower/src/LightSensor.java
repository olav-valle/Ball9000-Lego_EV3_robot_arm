import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class LightSensor {

    //fields to hold sensors

    private Brick brick; //the main brain
    private Port s1; // sensor port for front light sensor
    private Port s2; // sensor port for back light sensor

    private EV3ColorSensor frontSensor; // front light sensor
    private EV3ColorSensor backSensor; // back light sensor
    private SampleProvider frontReader;
    private SampleProvider backReader;
    private float[] frontSample;
    private float[] backSample;



    public LightSensor()
    {
        brick = BrickFinder.getDefault();
        s1 = brick.getPort("S1");
        s2 = brick.getPort("S2");
        frontSensor = new EV3ColorSensor(s1);
        backSensor = new EV3ColorSensor(s2);
        frontReader = frontSensor.getMode("Red");
        backReader = backSensor.getMode("Red");
        frontSample = new float[frontReader.sampleSize()];
        backSample = new float[backReader.sampleSize()];
    }

    /**
     * The front mounted color sensor.
     * @return true when black has been detected.
     */
    public boolean blackDetectedFront()
    {
        double black = 0.1;

        boolean blackDetected = false;
        frontReader.fetchSample(frontSample, 0);

        if(black >= frontSample[0]){
            blackDetected = true;
        }

        return blackDetected;
    }

    /**
     * The rear mounted color sensor.
     * @return true when black has been detected.
     */
    public boolean blackDetectedBack()
    {
        double black = 0.1;

        boolean blackDetected = false;
        frontReader.fetchSample(backSample, 0);

        if(black >= backSample[0]){
            blackDetected = true;
        }

        return blackDetected;
    }

    public String getSampleString()
    {
        frontReader.fetchSample(frontSample, 0);
        backReader.fetchSample(backSample, 0);
        return "Front: " + frontSample[0] + "\n" +
                "Rear: " + backSample[0];

    }
}
