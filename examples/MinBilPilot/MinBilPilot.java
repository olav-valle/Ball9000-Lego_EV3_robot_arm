/*    MinBilPilot.java G.S 2011 - 08 - 16
* Program som styrer en bil med 2 motorer. Bilen oppf�rer seg slik:
* 1. kj�r framover
* 2. Rygg
* 3. Sving h�yre
* 4. Endre hastighet p� motorene
* 5. Kj�r framover igjen
*
* Programmet bruker klassen TachoPilot som er egnet til � styre en robot med to motorer
*/

import lejos.hardware.motor.Motor;
import lejos.robotics.navigation.DifferentialPilot;


public class MinBilPilot
{
	public static void main (String[] args)  throws Exception
	{
		int distanse = 20;

		DifferentialPilot  volvo = new DifferentialPilot (2.1f, 4.4f, Motor.A, Motor.C, true);
		volvo.setTravelSpeed(450);
	 	volvo.travel(distanse);  // kj�r framover
	    volvo.travel(-distanse); // rygg
		volvo.rotate(180); // roter 180 grader
		volvo.travel(distanse);  // kj�r framover

	}
}

