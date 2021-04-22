import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * RouteManager is responsible for reading and writing railway routes from local files
 * as well as adding or removing routes per user input.
 * @author Nathaniel Wu
 * @version 1.0
 */
public class RouteManager {
	private int [][] routes;	// adjacency matrix 
	private String filename;
	
	/**
	 * Constructor that utilizes the user specified filename. If such a file isn't found, 
	 * the default filename of "routes.txt" will be used instead.
	 * @param filename		filename to import
	 */
	public RouteManager(String filename) {
		this.filename = filename;
		// 27x27 adjacency matrix
		// Row/col 0 is unused purely for convenience so that A will be row/col 1, B will be 2, etc.
		// Initialized to -1 to represent no edges
		routes = new int [27][27];
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[0].length; j++) {
				routes[i][j] = -1;
			}
		}
		System.out.println(readFile());
	}
	/**
	 * Constructor that utilizes the default file name of "routes.txt"
	 */
	public RouteManager() {
		this("routes.txt");
	}
	
	/**
	 * addRoute method for adding routes and updating the appropriate file
	 * Overrides pre-existing routes in the event of a collision. The starting
	 * and ending station cannot be the same
	 * @param start			starting station represented by a single uppercase letter
	 * @param finish		finishing station represented by a single uppercase letter
	 * @param distance		distance between the start and the finish
	 * @return				resulting message string
	 */
	public String addRoute(String start, String finish, int distance) {
		if (validateInput(start, finish, distance)) {
			routes[convertString(start)][convertString(finish)] = distance;
			return "Route added. " + writeFile();
		}
		else {
			return "Invalid input";
		}
	}
	
	/**
	 * removeRoute method for removing routes and updating the appropriate file
	 * @param start			starting station represented by a single uppercase letter
	 * @param finish		finishing station represented by a single uppercase letter
	 * @return				resulting message string
	 */
	public String removeRoute(String start, String finish) {
		if (validateInput(start, finish)) {
			if (routes[convertString(start)][convertString(finish)] == -1) {
				return "No such route found";
			}
			else {
				routes[convertString(start)][convertString(finish)] = -1;
				return "Route removed. " + writeFile();
			}
		}
		else {
			return "Invalid input";
		}
	}
	
	/**
	 * getRoutes method for returning the adjacency matrix
	 * @return		adjacency matrix
	 */
	public int [][] getRoutes() {
		return routes;
	}
	
	/**
	 * readFile method for importing routes from the filename. If such a file isn't found, 
	 * the default filename of "routes.txt" will be used instead. Assumes that all stations are
	 * represented by a single uppercase letter and distances are less than int max and nonnegative.
	 * Consecutive entries must be separated by whitespace
	 * @return		resulting message string
	 */
	private String readFile() {
		File file = new File(filename);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String data = scan.next();
				// Need to test improperly formatted files
				routes[data.charAt(0) - 64][data.charAt(1) - 64] = Integer.parseInt(data.substring(2));
			}
			scan.close();
			return "Imported from file successfully";
		} catch (FileNotFoundException e) {		
			filename = "routes.txt";	// default filename
			readFile();
			return "Failed to read from file, the default filename will be used instead";
		}
	}
	
	/**
	 * writeFile method for exporting routes to the filename. All stations will be represented by
	 * a single uppercase letter with the appropriate distance appended on. Routes will be exported
	 * in alphabetical order separated by whitespace
	 * @return		resulting message string
	 */
	private String writeFile() {
		try 
		{
			FileWriter output = new FileWriter(filename);
			for (int i = 1; i < routes.length; i++) {
				for (int j = 1; j < routes[0].length; j++) {
					if (routes[i][j] > 0) {
						String start = Character.toString((char) (i + 64));
						String finish = Character.toString((char) (j + 64));
						output.write(start + finish + routes[i][j] + "\n");
					}
				}
			}
			output.close();
			return "File updated successfully";
		} catch (IOException e) {
			return "Failed to write to file";
		}
	}
	
	/**
	 * convertString helper method for turning the first letter of a string into the corresponding int 
	 * @param input		string to be converted
	 * @return			corresponding int 
	 */
	private int convertString(String input) {
		return input.charAt(0) - 64;
	}
	
	/**
	 * validateInput method for validating user route input by checking that both stations are 
	 * represented by a single uppercase letter and that the distance is greater than 0. Routes
	 * cannot start and end in the same city.
	 * @param start			starting station
	 * @param finish		finishing station
	 * @param distance		distance between stations
	 * @return				true if valid, false if invalid
	 */
	private boolean validateInput(String start, String finish, int distance) {
		if (start == null || finish == null) {
			return false;
		}
		if (start.equals(finish)) {
			return false;
		}
		if (start.charAt(0) < 65 || start.charAt(0) > 90) {
			return false;
		}
		if (finish.charAt(0) < 65 || finish.charAt(0) > 90) {
			return false;
		}
		if (distance < 1) {
			return false;
		}
		return true;
	}
	
	/**
	 * validateInput method for validating user route input by checking that both stations are 
	 * represented by a single uppercase letter
	 * @param start
	 * @param finish
	 * @return
	 */
	private boolean validateInput(String start, String finish) {
		return validateInput(start, finish, 1);
	}
}
