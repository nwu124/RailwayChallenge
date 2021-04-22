

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class RouteManager {
	// adjacency matrix for storing edges between vertices
	private int [][] routes;
	private String filename;
	
	public RouteManager(String filename) {
		this.filename = filename;
		// 27x27 adjacency matrix
		// A will be row/col 1, B will be 2, C will be 3, etc.
		// Row/col 0 is unused purely for convenience
		routes = new int [27][27];
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[0].length; j++) {
				routes[i][j] = -1;
			}
		}
		readFile();
	}
	public RouteManager() {
		// default filename
		this("routes.txt");
	}
	
	// public methods
	
	// overrides pre-existing route 
	public String addRoute(String start, String finish, int distance) {
		if (validateInput(start, finish, distance)) {
			routes[convertString(start)][convertString(finish)] = distance;
			writeFile();
			return "Route added";
		}
		else {
			return "Invalid input";
		}
	}
	public String removeRoute(String start, String finish) {
		if (validateInput(start, finish)) {
			if (routes[convertString(start)][convertString(finish)] == -1) {
				return "No such route found";
			}
			else {
				routes[convertString(start)][convertString(finish)] = -1;
				writeFile();
				return "Route removed";
			}
		}
		else {
			return "Invalid input";
		}
	}
	public int [][] getRoutes() {
		return routes;
	}
	
	// private methods
	private String readFile() {
		// assumes that cities only use one character in capitals 
		// assumes that distances are less than int max and nonnegative
		File file = new File(filename);
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String data = scan.next();
				routes[data.charAt(0) - 64][data.charAt(1) - 64] = Integer.parseInt(data.substring(2));
			}
			scan.close();
			return "Routes imported successfully";
		} catch (FileNotFoundException e) {
			filename = "routes.txt";
			readFile();
			return "Failed to read from file, the default filename will be used instead";
		}
	}
	// write existing routes to file
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
			return "Routes updated successfully";
		} catch (IOException e) {
			return "Failed to write to file";
		}
	}
	// assumes only one character in capitals
	private int convertString(String input) {
		return input.charAt(0) - 64;
	}
	// validate user input
	private boolean validateInput(String start, String finish, int distance) {
		if (start == null || finish == null) {
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
	private boolean validateInput(String start, String finish) {
		return validateInput(start, finish, 1);
	}
}
