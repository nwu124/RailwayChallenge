package application;
public class RouteManager {
	// adjacency matrix for storing edges between vertices
	int [][] routes;
	
	public RouteManager(String fileLocation) {
		routes = new int [26][26];
		// read file
		// 
	}
	public RouteManager() {
		// without file location
	}
	
	// public methods
	public void addRoute() {
		
	}
	public void removeRoute() {
		
	}
	public int [][] getRoutes() {
		return routes;
	}
	
	// private methods
	private void readFile() {
		// assumes that cities only use one character
		// assumes that distances are less than int Max and nonnegative
	}
	private void writeFile() {
		
	}
}
