import swiftbot.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LogFile {
	private static String FileName = "TrafficLightLogFile.txt"; // Name our text file as a
	// private variable
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
	}
	public static void Information() {
		MainProgram.swiftbot.stopMove(); 
		System.out.println("Before the buttons are displayed");
		System.out.println("Quick Game");
		System.out.println("If you remember all the encountered traffic Lights, try and guess the most frequent colour");
		System.out.println("Hard difficulty: If they are all the same numbers, it will be based on your luck to guess the colour");
		try {
			Thread.sleep(13000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		ExecutionLogButtons();
	}
	public static void ExecutionLogButtons() {  // Main function of this method is to enable the required buttons for the execution log
		System.out.println();
		System.out.println(MainProgram.purple+"\r\n" // ASCII as purple
				+ " _____                    _   _               _                 _ \r\n"
				+ "|  ___|                  | | (_)             | |               | |\r\n"
				+ "| |____  _____  ___ _   _| |_ _  ___  _ __   | |     ___   __ _| |\r\n"
				+ "|  __\\ \\/ / _ \\/ __| | | | __| |/ _ \\| '_ \\  | |    / _ \\ / _` | |\r\n"
				+ "| |___>  <  __/ (__| |_| | |_| | (_) | | | | | |___| (_) | (_| |_|\r\n"
				+ "\\____/_/\\_\\___|\\___|\\__,_|\\__|_|\\___/|_| |_| \\_____/\\___/ \\__, (_)\r\n"
				+ "                                                           __/ |  \r\n"
				+ "                                                          |___/   \r\n"
				+ "");
		System.out.println();
		System.out.println(MainProgram.RESET);
		MainProgram.swiftbot.disableAllButtons();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Well done for completing traffic lights"); // (additonal function)
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Press Button 'Y' to view log, or Button 'X' to skip" + "\n");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long EndExecution = System.currentTimeMillis() + 15_000; // 15 second timer
		MainProgram.swiftbot.stopMove(); 
		MainProgram.swiftbot.enableButton(Button.A, () -> {
			System.out.println("An Invalid Button has been pressed");
		});
		MainProgram.swiftbot.enableButton(Button.X, () -> {
			MainProgram.swiftbot.stopMove(); 
			buttonXPressed();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		});
		MainProgram.swiftbot.enableButton(Button.Y, () -> {
		MainProgram.swiftbot.stopMove(); 
		ExecutionLog(); 
		});
		MainProgram.swiftbot.enableButton(Button.B, () -> {
			System.out.println("An Invalid Button has been pressed");
		});
		while (System.currentTimeMillis() < EndExecution) {
			;
		}
		System.exit(0); // after the 15 second timer, program will exit (Additonal function)

	}

	public static void ExecutionLog() { // main function of this method is to display the information to the user
		MainProgram.swiftbot.stopMove();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.disableAllButtons(); 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Red Traffic Light Encountered: " + TrafficLights.RedCount);  // display all the details required for the user to see
		System.out.println("Green Traffic Light Encountered: " + TrafficLights.GreenCount);
		System.out.println("Blue Traffic Light Encountered: " + TrafficLights.BlueCount);
		System.out.println();
		System.out.println("Traffic Lights Encountered In Total: " + TrafficLights.totalEncounters);
		System.out.println("Most Frequent Colour: " + TrafficLights.mostFrequentColour);
		System.out.println("Number Of Times Most Frequent Traffic Light Was Encountered " + TrafficLights.numOfMostFrequentCol);
		System.out.println("Total Execution Time: " + MainProgram.totalExecutionTime + " " + "Seconds");
		System.out.println();
		UpdatedUnderlights();
		CreatingTxtFile();
		System.exit(0);
	}

	public static void UpdatedUnderlights() {  // additonal functionality, to display the underlights that was most encountered
		if (TrafficLights.RedCount > TrafficLights.BlueCount && TrafficLights.RedCount > TrafficLights.GreenCount) {
			int[] R = { 255, 0, 0 };
			MainProgram.swiftbot.fillUnderlights(R);
		} else if (TrafficLights.BlueCount > TrafficLights.RedCount
				&& TrafficLights.BlueCount > TrafficLights.GreenCount) {
			int[] B = { 0, 0, 255 };
			MainProgram.swiftbot.fillUnderlights(B);
		} else if (TrafficLights.GreenCount > TrafficLights.RedCount
				&& TrafficLights.GreenCount > TrafficLights.BlueCount) {
			int[] G = { 0, 255, 0 };
			MainProgram.swiftbot.fillUnderlights(G);
		} else {
			System.out.println("Most frequent colours are all the same numbers, UnderLights cannot be changed");
		}
	}

	public static void CreatingTxtFile() {  // the main function of this method is to write all the information to a text file
		boolean RedAppending = TrafficLights.RedCount != 0; // checking if any of the lights are not 0
		boolean BlueAppending = TrafficLights.BlueCount != 0;
		boolean GreenAppending = TrafficLights.GreenCount != 0;
		try (FileWriter LF = new FileWriter(FileName, RedAppending && BlueAppending && GreenAppending); // open the file
				BufferedWriter writeText = new BufferedWriter(LF)) { // write the information
			System.out.println("Writing the information to a text file...");
			if (!RedAppending && GreenAppending && BlueAppending) { // first time of the traffic lights, so it would
				// empty the file and start a new one
				writeText.write("Traffic Lights Full Log\n");
			}
			// write all the
			// details of
			// the traffic lights within
			// the text file
			writeText.write("Red Traffic Lights: " + TrafficLights.RedCount++ + "\n");
			writeText.write("Green Traffic Lights: " + TrafficLights.GreenCount++ + "\n");
			writeText.write("Blue Traffic Lights: " + TrafficLights.BlueCount++ + "\n");
			writeText.write("Number Of Traffic Lights Encountered: " + TrafficLights.totalEncounters++ + "\n");
			writeText.write("Most Frequent Colour: " + TrafficLights.mostFrequentColour + "\n");
			writeText.write("Number Of Most Frequent Colour: " + TrafficLights.numOfMostFrequentCol++ + "\n");
			writeText.write("Total Execution Time: " + MainProgram.totalExecutionTime + " " + "Seconds" + "\n");
			writeText.flush(); // saves the changes to the file
			System.out.println("File changes has been saved");
			System.out.println("File will be located at: " + new File(FileName).getAbsolutePath()); // shows full path
			// of the file
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("To view the log within the text file, please exit the program, then type 'nano sudo TrafficLightLogFile.txt'.");
		
		} catch (IOException e) {
			System.out.println(
					"ERROR: the details are not able to be written into a text file, please retry or check if the file exists\n");
			e.printStackTrace(); // catch if it does not write the information to a text file
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void buttonXPressed() {  // button X function
		MainProgram.swiftbot.stopMove();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.disableAllButtons();
		int[] P = { 128, 0, 128 };
		MainProgram.swiftbot.fillUnderlights(P);  //turn underlights purple 
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		CreatingTxtFile();  // displays the location of text file, before exiting the program
	}
}