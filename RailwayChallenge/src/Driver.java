import java.util.Arrays;
import java.util.Scanner;

public class Driver {
	public static void main(String [] args) {
		// Scanner for user input
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Railway Challenge!");
		
		// create RouteManager using the given filename
		System.out.println("Please enter a filename to import routes from or enter 'NONE' to overwrite the default file");
		String filename = scan.next();
		RouteManager manager;
		if (filename.equals("NONE")) {
			manager = new RouteManager();
		}
		else {
			manager = new RouteManager(filename);
		}
		
		// display user options
		while (true) {
			System.out.println("\nPlease enter a command number, 'HELP' for the commands list, or 'EXIT' to quit");
			// process user input
			String input = scan.next();
			if (input.equals("EXIT")) {
				break;
			}
			else if (input.equals("HELP")) {
				System.out.println("Command list:\n"
						+ "1: Get route information\n"
						+ "2: Add route\n"
						+ "3: Remove route");
			}
			else {
				try {
					// validate input
					int command = Integer.parseInt(input);
					if (command < 1 || command > 3) {
						System.out.println("Invalid input");
					}
					
					switch (command) {
					case 1:
						// get route information
						int [][] routes = manager.getRoutes();
						TripCalculator calculator = new TripCalculator(routes);
						int [] results = calculator.getOutput();
						System.out.println(Arrays.toString(results));
						break;
					case 2:
						// add route
						System.out.println("Please enter the route information to be added in the format <start><end><length>, e.g. 'AB1'");
						String routeToAdd = scan.next();
						System.out.println(manager.addRoute(routeToAdd.substring(0, 1), routeToAdd.substring(1, 2), 
								Integer.parseInt(routeToAdd.substring(2))));
						break;
					case 3:
						// remove route
						System.out.println("Please enter the route information to be removed in the format <start><end>, e.g. 'AB'");
						String routeToRemove = scan.next();
						if (routeToRemove.length() > 2) {
							System.out.println("Invalid input");
							break;
						}
						System.out.println(manager.removeRoute(routeToRemove.substring(0, 1), routeToRemove.substring(1, 2)));
						break;
					}
				}
				catch (NumberFormatException e){
					System.out.println("Invalid input");
				}
			}
		}
		scan.close();
		System.out.println("\nThanks for using Railway Challenge!");
	}
}
