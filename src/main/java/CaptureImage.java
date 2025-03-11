import swiftbot.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class CaptureImage {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
	}

	public static void CaptureAImage() {
		try {
			BufferedImage image = MainProgram.swiftbot.takeStill(ImageSize.SQUARE_48x48); // takeStill method takes the
			// picture)
			if (image != null) {
				ImageIO.write(image, "jpg", new File("/data/home/pi/Documents/ColourImage.jpg"));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("SUCCESS: Image has been saved!");
			} else; 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void DetermineColour() {
		long cameraTime = System.currentTimeMillis();
		double ObjectDistance = 0; // from here to end this else, we check for any objects
		for (int i = 0; i < 1000; ++i) {
			long BeforeTime = System.currentTimeMillis();
			ObjectDistance = MainProgram.swiftbot.useUltrasound(); // find the distance
			long AfterTime = System.currentTimeMillis();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ObjectDistance > 30) { // detect if an object is within 25cm (Additonal function)
				MainProgram.swiftbot.startMove(31, 40);
				break; 
			} else if (ObjectDistance <= 30) {
				MainProgram.swiftbot.stopMove();
				CaptureAImage(); // mention the method to capture a image
				try {
					File FN = new File("/data/home/pi/Documents/ColourImage.jpg"); // Full path of the image
					BufferedImage img = ImageIO.read(FN);
					myBreakLabel: for (int x = 0; x < img.getWidth(); ++x) { // this is getting width and height of the
						// image
						for (int y = 0; y < img.getHeight(); ++y) {
							int p = img.getRGB(y, x); // collect the rgb values of the image
							int r = (p >> 16) & 0xFF; // determining the colour
							int g = (p >> 8) & 0xFF;
							int b = p & 0xFF;
							System.out.println("Colour of image has been determined");

							if (r > g && r > b) { // prints out the determined colour of the traffic light as well as
													// the distance
								System.out.println("Determined Colour: Red"); // then runs the method for which traffic
																				// light was determined
								System.out.println("Distance of Traffic Light: " + ObjectDistance + "cm");
								MainProgram.swiftbot.stopMove();
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								TrafficLights.RedLight();
								break myBreakLabel;
							} else if (g > r && g > b) {
								System.out.println("Determined Colour: Green");
								System.out.println("Distance of Traffic Light: " + ObjectDistance+ "cm");
								MainProgram.swiftbot.stopMove();
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								TrafficLights.GreenLight();
								break myBreakLabel;
							} else if (b > r && b > g) {
								System.out.println("Determined Colour: Blue");
								System.out.println("Distance of Traffic Light: " + ObjectDistance + "cm");
								MainProgram.swiftbot.stopMove();
								try {
									Thread.sleep(1500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								TrafficLights.BlueLight();
								break myBreakLabel;
							} else if (p != r || p != g || p != b) {  // additonal function (Object Detection)
								long MinTime = System.currentTimeMillis() + 15_000; // 15 second timer to allow the user
																					// to remove the object othewise
																					// swiftbot
								System.out.println("Object within 25cm radius, please prepare to remove it"); // will
																												// turn
								MainProgram.swiftbot.stopMove();
								while (System.currentTimeMillis() < MinTime) {
									;
								}
								if (ObjectDistance <= 25) { // user does not move object

									MainProgram.swiftbot.move(0, 40, 1350);
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
									MainProgram.swiftbot.startMove(31, 40);
								}

								break myBreakLabel;
							} else
								;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println();
		}
		long cameraEndTime = System.currentTimeMillis();
		double totalCameraTime = (cameraEndTime - cameraTime) / 1000.0; // convert milliTime to seconds
		MainProgram.totalExecutionTime += totalCameraTime; // add the total seconds it takes to capture a image to the
															// total execution time
	}
}
