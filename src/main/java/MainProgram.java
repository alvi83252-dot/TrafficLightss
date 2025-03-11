
import swiftbot.*;
import java.util.Scanner;

public class MainProgram {
	public static long totalExecutionTime = 0;  // display this global variable for the execution log
	static SwiftBotAPI swiftbot;
	public static final String purple = "\033[35m";
	public static final String RESET = "\u001B[0m";
	static Scanner sc = new Scanner(System.in);

	public static void ASCII() { // this method displays the ASCII art at the beginning
		String ASCIITrafficLight = "-->  TTTTT  RRRRR   AAAAA   FFFFF  FFFFF  I   CCCCC          L      I   GGGGG  H   H  TTTTT  <--\n"
				+ "-->    T    R    R  A     A F      F      I   C              L      I   G      H   H    T    <--\n"
				+ "-->    T    RRRRR   AAAAAAA FFFFF  FFFFF  I   C              L      I   G  GG  HHHHH    T    <--\n"
				+ "-->    T    R  R    A     A F      F      I   C              L      I   G   G  H   H    T    <--\n"
				+ "-->    T    R   RR  A     A F      F      I   CCCCC          LLLLL  I   GGGGG  H   H    T    <--\n"
				+ "/ / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / / /  \n";
		System.out.println(purple + ASCIITrafficLight);
		System.out.println(RESET);
		System.out.println("Press Button 'A' to start"); // displays this in the output
		System.out.println("Press Button 'X' to quit");
		System.out.println("Press Button 'Y' to view the log");
	}

	public static void buttonFunctions() { // this method enables all buttons to be used, as
		// well as the functions to be implemented
		long EndTiming = System.currentTimeMillis() + 10_000; // 10 second timer
		System.out.println("10 seconds timer has now started, please press a button within this time");
		swiftbot.enableButton(Button.A, () -> { // button A functions
			System.out.println();
			System.out.println(purple + "\r\n" + "   ___                  ___ _            _          _ _ \r\n"
					+ "  / __|__ _ _ __  ___  / __| |_ __ _ _ _| |_ ___ __| | |\r\n"
					+ " | (_ / _` | '  \\/ -_) \\__ \\  _/ _` | '_|  _/ -_) _` |_|\r\n"
					+ "  \\___\\__,_|_|_|_\\___| |___/\\__\\__,_|_|  \\__\\___\\__,_(_)\r\n"
					+ "                                                        \r\n" + "");
			System.out.println(RESET);  // reset the colour back to normal
			System.out.println();
			try {
				Thread.sleep(1000);  // waits 1 second
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ButtonA(0, 0);
		});
		swiftbot.enableButton(Button.X, () -> {
			System.out.println("Goodbye!");
			System.exit(0);
			// Type 'nano sudo' after exiting the program
		});
		swiftbot.enableButton(Button.Y, () -> {
			if (TrafficLights.RedCount == 0 && TrafficLights.BlueCount == 0 && TrafficLights.GreenCount == 0) {
				System.out.println("Please complete at least one or more Traffic Lights before going to the execution log");
			} else if (TrafficLights.RedCount >= 1 || TrafficLights.BlueCount >= 1 || TrafficLights.GreenCount >= 1) {
				LogFile.Information(); 
			} else; 
			 
		});
		swiftbot.enableButton(Button.B, () -> {
			System.out.println("Invalid button pressed, please choose from the list!");
		});
		while (System.currentTimeMillis() < EndTiming) {
			;  // this will do nothing for 10 seconds
		}
		System.out.println("All buttons except from button 'X' and 'Y' has been disabled,");
		System.out.println();
		System.out.println("You may wish to press button 'X' to exit the program at any time");
		System.out.println();
		System.out.println("You may wish to view the Execution log by clicking button 'Y'");
		System.out.println();
		swiftbot.disableButton(Button.B);
		swiftbot.disableButton(Button.A);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static int ButtonA(long startTime, long endTime) { // this method shows how the program starts (used Pollymorphism)
		try {  
			Thread.sleep(900); // 0.9 seconds after disabling buttons
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		startTime = System.currentTimeMillis(); // 10 second timer
		endTime = startTime + 10000;
		while (System.currentTimeMillis() < endTime) {
			int[] yellow = { 255, 255, 0 };  
			swiftbot.fillUnderlights(yellow); // filling underlights yellow
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			swiftbot.startMove(31, 40); // swiftbot moves at low initial speed
			System.out.println("-----------------------------------------------");
			System.out.println("Initial speed of the SwiftBot is 15cm/s");
			System.out.println("Left wheel power:  31%");
			System.out.println("Right wheel power: 40%");
			System.out.println();
			System.out.println("-----------------------------------------------");
			System.out.println((System.currentTimeMillis() - startTime));
			break;
		}
		ButtonA(0);
		return ButtonA(startTime, endTime); // this is the second half of the main program, where it would now move onto to capturing images
	}

	public static int ButtonA(double distance) {
		CaptureImage.DetermineColour();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		swiftbot.disableUnderlights();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return ButtonA(distance);
	}

	public static void main(String[] args) {
		long StartingTime = System.currentTimeMillis();
		try {
			swiftbot = new SwiftBotAPI();
		} catch (Exception e) {
			/*
			 * Outputs a warning if I2C is disabled. This only needs to be turned on once,
			 * so you won't need to worry about this problem again!
			 */
			System.out.println("\nI2C disabled!");
			System.out.println("Run the following command:");
			System.out.println("sudo raspi-config nonint do_i2c 0\n");
			System.exit(5);

		}
		long totalStartTime = System.currentTimeMillis();
		ASCII();
		buttonFunctions();
		long EndOfTime = System.currentTimeMillis(); 
		double totalTimeInSeconds = (EndOfTime - totalStartTime)/ 1000.0;  // formula to convert MilliTime to seconds
		
		long endOfTime = System.currentTimeMillis(); 
		totalTimeInSeconds = (endOfTime - StartingTime) / 1000.0; 
		totalExecutionTime = (long) totalTimeInSeconds;  // getting the total execution time in seconds
	}
}
