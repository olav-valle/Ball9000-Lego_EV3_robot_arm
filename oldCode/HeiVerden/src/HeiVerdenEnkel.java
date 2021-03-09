/* Programmet skriver en tekst på skjermen til EV3en, venter 500 ms.
Dernest skrives en ny tekst og EV3en venter til det trykkes på en knapp
før programmet avsluttes.
7  */

import lejos.hardware.BrickFinder;
import lejos.hardware.Keys;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;

public class HeiVerdenEnkel {
    public static void main(String[] args)  throws Exception{
        System.out.println("Hi, there. Is this thing on?");
        Thread.sleep(500);

        EV3 ev3 = (EV3) BrickFinder.getLocal();
        TextLCD lcd = ev3.getTextLCD();
        Keys keys = ev3.getKeys();
        lcd.drawString("Hello World", 4, 4);
        keys.waitForAnyPress();
    }
}