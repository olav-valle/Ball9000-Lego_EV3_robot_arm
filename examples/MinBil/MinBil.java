/*    MinBil.java G.S 2011 - 08 - 24
* Program som styrer en bil med 2 motorer. Bilen oppf�rer seg slik:
* 1. kj�r framover
* 2. Rygg
* 3. Sving h�yre
* 4. Endre hastighet p� motorene
* 5. Kj�r framover igjen
*/

import lejos.hardware.motor.*;
import lejos.hardware.lcd.*;

public class MinBil
{
  public static void main (String[] args)  throws Exception
  {
     Motor.A.setSpeed(450);  // sett hastighet (toppfart = 900)
	 Motor.C.setSpeed(450);

     // Kj�r framover
     System.out.println("Fram 2000 ms");
     Motor.A.forward();  // Start motor A - kj�r framover
     Motor.C.forward();  // Start motor C - kj�r framover
     Thread.sleep(2000); // Vent i 1000 ms f�r vi g�r videre i koden

     // Rygg
     LCD.clear();		 // Rens lcd-skjermen
     System.out.println("Bak 2000");
     Motor.A.backward();
     Motor.C.backward();
     Thread.sleep(2000);

     // Sving
     LCD.clear();
     System.out.println("Snu 180 grader");
     Motor.A.rotate(180);  // roter 180 gr med motor A
     Motor.C.stop();
     while (Motor.A.isMoving()) Thread.yield();  // vent til rotasjon er ferdig

     // Kj�r framover
     LCD.clear();
     System.out.println("Fram 1000");
     Motor.A.forward();
     Motor.C.forward();

     Thread.sleep(1000);
     Motor.A.stop();
     Motor.C.stop();
  }
}
