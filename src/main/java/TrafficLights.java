import java.util.Scanner;

public class TrafficLights {
	public static int RedCount = 0;
	public static int GreenCount = 0;
	public static int BlueCount = 0;  // all the global veriables to store the data, in order to display it for
	public static String mostFrequentColour = "";   // the execution log
	public static int numOfMostFrequentCol = 0;
	public static int totalEncounters = 0; 
	private static final String Red = "\033[31m";
	private static final String Green = "\033[34m";
	private static final String BLUECOLOUR = "\033[34m";
	private static final String Reset = "\033[0m";
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
	}

	public static void RedLight() {  // red light function of the swiftbot
		long redTime = System.currentTimeMillis();
		MainProgram.swiftbot.disableUnderlights();
		System.out.println(Red + "\r\n"
				+ "$$$$$$$\\                  $$\\       $$\\       $$\\           $$\\        $$\\     \r\n"
				+ "$$  __$$\\                 $$ |      $$ |      \\__|          $$ |       $$ |    \r\n"
				+ "$$ |  $$ | $$$$$$\\   $$$$$$$ |      $$ |      $$\\  $$$$$$\\  $$$$$$$\\ $$$$$$\\   \r\n"
				+ "$$$$$$$  |$$  __$$\\ $$  __$$ |      $$ |      $$ |$$  __$$\\ $$  __$$\\\\_$$  _|  \r\n"
				+ "$$  __$$< $$$$$$$$ |$$ /  $$ |      $$ |      $$ |$$ /  $$ |$$ |  $$ | $$ |    \r\n"
				+ "$$ |  $$ |$$   ____|$$ |  $$ |      $$ |      $$ |$$ |  $$ |$$ |  $$ | $$ |$$\\ \r\n"
				+ "$$ |  $$ |\\$$$$$$$\\ \\$$$$$$$ |      $$$$$$$$\\ $$ |\\$$$$$$$ |$$ |  $$ | \\$$$$  |\r\n"
				+ "\\__|  \\__| \\_______| \\_______|      \\________|\\__| \\____$$ |\\__|  \\__|  \\____/ \r\n"
				+ "                                                  $$\\   $$ |                   \r\n"
				+ "                                                  \\$$$$$$  |                   \r\n"
				+ "                                                   \\______/                    \r\n" + "");
		System.out.println(Reset);
		System.out.println("Welcome To Red Light");
		System.out.println("----------------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.startMove(31, 40);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int[] red = { 255, 0, 0 };
		MainProgram.swiftbot.fillUnderlights(red);

		try {
			Thread.sleep(1000);
			MainProgram.swiftbot.stopMove();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.startMove(31, 40);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Well done for completing the Red Traffic Light!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The robot will now dance!");
		int[][] RandomColours = { // Array of colours
				{ 255, 0, 255 }, // pink
				{ 102, 255, 255 }, // cyan
				{ 153, 0, 153 }, // purple
				{ 76, 153, 0 } // green
		};
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int[] UniqueColours : RandomColours) {
			MainProgram.swiftbot.fillUnderlights(UniqueColours);
			MainProgram.swiftbot.move(100, 0, 5000);
			MainProgram.swiftbot.move(0, 100, 5000);
			MainProgram.swiftbot.stopMove();
			MainProgram.swiftbot.disableUnderlights();
		}
		RedCount++;
		try {
			Thread.sleep(1250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long redEndTime = System.currentTimeMillis();
		double totalRedTime = (redEndTime - redTime) / 1000.0;  // convert milliTime to seconds
		MainProgram.totalExecutionTime += totalRedTime; // add the total seconds it takes for the red light to run to the execution time
		if (RedCount > BlueCount && RedCount > GreenCount) {
			mostFrequentColour = "Red"; 
			numOfMostFrequentCol = RedCount;
		}
		totalEncounters = RedCount + BlueCount + GreenCount;  // calculate the total number of traffic lights encountered
	}

	public static void GreenLight() {  // green light function of the swiftbot
		long greenStart = System.currentTimeMillis();
		MainProgram.swiftbot.disableUnderlights();
		System.out.println(Green + "\r\n" + "                                (                           \r\n"
				+ " (                              )\\ )               )     )  \r\n"
				+ " )\\ )    (      (    (         (()/( (   (  (   ( /(  ( /(  \r\n"
				+ "(()/(    )(    ))\\  ))\\  (      /(_)))\\  )\\))(  )\\()) )\\()) \r\n"
				+ " /(_))_ (()\\  /((_)/((_) )\\ )  (_)) ((_)((_))\\ ((_)\\ (_))/  \r\n"
				+ "(_)) __| ((_)(_)) (_))  _(_/(  | |   (_) (()(_)| |(_)| |_   \r\n"
				+ "  | (_ || '_|/ -_)/ -_)| ' \\)) | |__ | |/ _` | | ' \\ |  _|  \r\n"
				+ "   \\___||_|  \\___|\\___||_||_|  |____||_|\\__, | |_||_| \\__|  \r\n"
				+ "                                        |___/               \r\n" + "");
		System.out.println(Reset);
		int[] Green = { 0, 255, 0 };
		MainProgram.swiftbot.fillUnderlights(Green);
		System.out.println("Welcome To Green Light");
		System.out.println("----------------------------");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.move(77, 100, 2000);
		System.out.println("-----------------------------------------------");
		System.out.println("Initial speed of the SwiftBot is 27cm/s");  // max initial speed of the swiftbot
		System.out.println("Left wheel power:  77%");
		System.out.println("Right wheel power: 100%");
		System.out.println();
		System.out.println("-----------------------------------------------"); 
		MainProgram.swiftbot.stopMove();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int[] YellowLight = { 255, 255, 0 };
		MainProgram.swiftbot.fillUnderlights(YellowLight);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.startMove(31, 40);
		System.out.println("_________________________________________________");
		System.out.println("Initial speed of the SwiftBot is 15cm/s");
		System.out.println("Left wheel power:  31%");
		System.out.println("Right wheel power: 40%");
		System.out.println("_________________________________________________");
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Well done for completing Green Traffic Light!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The robot will now dance!");
		int[][] RandCol = { // Array of colours
				{ 255, 153, 255 }, // pink
				{ 255, 128, 0 }, // orange
				{ 255, 255, 51 }, // yellow
				{ 204, 0, 0 } // red
		};
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int[] RGB : RandCol) {
			MainProgram.swiftbot.fillUnderlights(RGB);
			MainProgram.swiftbot.move(-100, 0, 5000);
			MainProgram.swiftbot.move(0, -100, 5000);
			MainProgram.swiftbot.stopMove();
			MainProgram.swiftbot.disableUnderlights();
		}
		GreenCount++;
		try {
			Thread.sleep(1250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long greenEndTime = System.currentTimeMillis();
		double totalGreenTime = (greenEndTime - greenStart) / 1000.0; // convert milliTime to seconds
		MainProgram.totalExecutionTime += totalGreenTime; // Store the total seconds of green light in the execution time
		if (GreenCount > BlueCount && GreenCount > RedCount) {
			mostFrequentColour = "Green"; 
			numOfMostFrequentCol = GreenCount;
		}
		totalEncounters = RedCount + BlueCount + GreenCount;  // calculate the total number of traffic lights encountered
	}

	public static void BlueLight() {  // blue light function
		long blueStart = System.currentTimeMillis(); 
		MainProgram.swiftbot.disableUnderlights();
		System.out.println(BLUECOLOUR + "\r\n"
				+ "                                                                                        \r\n"
				+ "@@@@@@@   @@@       @@@  @@@  @@@@@@@@     @@@       @@@   @@@@@@@@  @@@  @@@  @@@@@@@  \r\n"
				+ "@@@@@@@@  @@@       @@@  @@@  @@@@@@@@     @@@       @@@  @@@@@@@@@  @@@  @@@  @@@@@@@  \r\n"
				+ "@@!  @@@  @@!       @@!  @@@  @@!          @@!       @@!  !@@        @@!  @@@    @@!    \r\n"
				+ "!@   @!@  !@!       !@!  @!@  !@!          !@!       !@!  !@!        !@!  @!@    !@!    \r\n"
				+ "@!@!@!@   @!!       @!@  !@!  @!!!:!       @!!       !!@  !@! @!@!@  @!@!@!@!    @!!    \r\n"
				+ "!!!@!!!!  !!!       !@!  !!!  !!!!!:       !!!       !!!  !!! !!@!!  !!!@!!!!    !!!    \r\n"
				+ "!!:  !!!  !!:       !!:  !!!  !!:          !!:       !!:  :!!   !!:  !!:  !!!    !!:    \r\n"
				+ ":!:  !:!   :!:      :!:  !:!  :!:           :!:      :!:  :!:   !::  :!:  !:!    :!:    \r\n"
				+ " :: ::::   :: ::::  ::::: ::   :: ::::      :: ::::   ::   ::: ::::  ::   :::     ::    \r\n"
				+ ":: : ::   : :: : :   : :  :   : :: ::      : :: : :  :     :: :: :    :   : :     :     \r\n"
				+ "                                                                                        \r\n" + "");
		System.out.println(Reset);
		MainProgram.swiftbot.startMove(31, 40);
		System.out.println("Welcome To Blue Light");
		System.out.println("----------------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.stopMove();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int[] blue = { 0, 0, 255 };
		int[] off = { 0, 0, 0 };

		for (int i = 0; i < 12; i++) { // blinking underlights
			MainProgram.swiftbot.fillUnderlights(blue);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainProgram.swiftbot.fillUnderlights(off);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		MainProgram.swiftbot.move(0, 40, 1350);  // turning the swiftbot
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.move(31, 40, 1000);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.move(-31, -40, 1000); // retracing to its original path
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		MainProgram.swiftbot.move(0, -40, 1327);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 12; i++) {
			MainProgram.swiftbot.fillUnderlights(blue);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			MainProgram.swiftbot.fillUnderlights(off);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Well done for completing Blue Traffic Light!");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("The robot will now dance!");
		int[][] RandomCol = { { 127, 0, 255 }, // purple
				{ 0, 255, 255 }, // Cyan
				{ 128, 255, 0 }, // Green
				{ 0, 0, 255 } }; // blue
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int[] RGBVal : RandomCol) {  // additional function to make the robot dance
			MainProgram.swiftbot.fillUnderlights(RGBVal);
			MainProgram.swiftbot.move(0, 40, 1500);
			MainProgram.swiftbot.move(40, 0, 1500);
			MainProgram.swiftbot.stopMove();
			MainProgram.swiftbot.disableUnderlights();
		}
		BlueCount++;
		try {
			Thread.sleep(1250);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long blueEndTime = System.currentTimeMillis(); 
		double totalBlueTime = (blueEndTime - blueStart) / 1000.0; // convert milliTime to seconds
		MainProgram.totalExecutionTime += totalBlueTime; // store the total seconds of blue in the execution time
		if (BlueCount > RedCount && BlueCount > GreenCount) {
			mostFrequentColour = "Blue"; 
			numOfMostFrequentCol = BlueCount;
		}
		totalEncounters = RedCount + BlueCount + GreenCount;  // calculate the total number of traffic lights encountered
	}
}