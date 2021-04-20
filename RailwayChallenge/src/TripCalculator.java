import java.util.ArrayList;
import java.util.Arrays;

public class TripCalculator {
	private int [][] routes;
	
	public TripCalculator(int [][] routes) {
		if (validateRoutes(routes)) {
			this.routes = routes;
		}
		else {
			System.out.println("Invalid route input");
			routes = new int [27][27];
		}
		
	}
	// public methods
	public int [] getOutput() {
		// calculate all outputs
		// 11 instead of 10 purely for convenience
		int [] results = new int [11];
		results[0] = -1;
		results[1] = output1();
		results[2] = output2();
		results[3] = output3();
		results[4] = output4();
		results[5] = output5();
		results[6] = output6();
		results[7] = output7();
		results[8] = output8();
		results[9] = output9();
		results[10] = output10();
		return results;
	}
	
	// private methods
	// -1 means not possible
	private int output1() {
		// calculate ABC
		if (routes[1][2] == -1 || routes[2][3] == -1) {
			return -1;
		}
		else {
			return routes[1][2] + routes[2][3];
		}
	}
	private int output2() {
		// calculate AD
		if (routes[1][4] == -1) {
			return -1;
		}
		else {
			return routes[1][4];
		}
	}
	private int output3() {
		// calculate ADC
		if (routes[1][4] == -1 || routes[4][3] == -1) {
			return -1;
		}
		else {
			return routes[1][4] + routes[4][3];
		}
	}
	private int output4() {
		// calculate AEBCD
		if (routes[1][5] == -1 || routes[5][2] == -1 || 
				routes[2][3] == -1 || routes[3][4] == -1) {
			return -1;
		}
		else {
			return routes[1][5] + routes[5][2] + routes[2][3] + routes[3][4];
		}
	}
	private int output5() {
		// calculate AED
		if (routes[1][5] == -1 || routes[5][4] == -1) {
			return -1;
		}
		else {
			return routes[1][5] + routes[5][4];
		}
	}
	private int output6() {
		// calculate CC with max 3 stops
		int counter = 0;
		
		// can we go back to C? 
		// one stop
		if (routes[3][3] >= 0) {
			counter++;
		}
		// search all routes leading away from C
		for (int i = 0; i < routes.length; i++) {
			// potential stops
			if (routes[3][i] >= 0) {
				// can we go back to C? 
				// two stops
				if (routes[i][3] >= 0) {
					counter++;
				}
				
				// search all routes leading away from the first stop
				for (int j = 0; j < routes.length; j++) {
					// potential stops
					if (routes[i][j] >= 0) {
						// can we go back to C? 
						// three stops
						if (routes[j][3] >= 0) {
							counter++;
						}
					}
					
				}
			}
		}
		return counter;
	}
	private int output7() {
		// calculate AC with exactly 4 stops
		int counter = 0;
		
		// search all routes leading away from A
		// one stop
		for (int i = 0; i < routes.length; i++) {
			// potential stops
			if (routes[1][i] >= 0) {
				// search all routes leading away from the first stop
				// two stops
				for (int j = 0; j < routes.length; j++) {
					// potential stops
					if (routes[i][j] >= 0) {
						// search all routes leading away from the second stop
						// three stops
						for (int k = 0; k < routes.length; k++) {
							// potential stops
							if (routes[j][k] >= 0) {
								// can we can go back to C? 
								// four stops
								if (routes[k][3] >= 0) {
									counter++;
								}
							}
						}
					}
					
				}
			}
		}
		return counter;
	}
	private int output8() {
		// calculate lowest AC
		return getShortestRoute(1, 3);
	}
	private int output9() {
		// calculate lowest BB
		return getShortestRoute(2, 2);
	}
	private int output10() {
		// calculate number of CC <30
		return 0;
	}
	private int getShortestRoute(int start, int finish) {
		// shortest distance to each route from the start
		// row 1 for distance values
		// row 2
		int [][] minimumDistances = new int [2][routes.length];
		
		for (int i = 0; i < minimumDistances.length; i++) {
			minimumDistances[0][i] = -1;
			minimumDistances[1][i] = -1;
		}
		minimumDistances[0][start] = 0;
		
		for (int i = 1; i < routes.length - 1; i++) {
			
			int minimum = Integer.MAX_VALUE;
			int minimumIndex = -1;
			
			for (int j = 1; j < routes.length; j++) {
				if (minimumDistances[1][j] == -1 && minimumDistances[0][j] <= minimum) {
					minimum = minimumDistances[0][j];
					minimumIndex = j;
				}
			}
			
			// minimumDistances[1][minimumIndex] = 0;
			
			for (int j = 1; j < routes.length; j++) {
				if (minimumDistances[1][j] != -1 && routes[i][j] != 0 && 
						minimumDistances[0][i] != Integer.MAX_VALUE && minimumDistances[0][i] + routes[i][j] < minimumDistances[0][j]) {
					minimumDistances[0][j] = minimumDistances[0][i] + routes[i][j];
				}
			}
		}
		
		System.out.println(Arrays.toString(minimumDistances[0]));
		return minimumDistances[0][finish];
	}
	
	private boolean validateRoutes(int [][] routes) {
		if (routes == null) {
			return false;
		}
		if (routes.length != 27 || routes[0].length != 27) {
			return false;
		}
		for (int i = 0; i < routes.length; i++) {
			for (int j = 0; j < routes[0].length; j++) {
				if (routes[i][j] < -1) {
					return false;
				}
			}
		}
		return true;
	}
	// temporary debugging use
	public static void main(String[]args) {
		RouteManager manager = new RouteManager();
		int [][] routes = manager.getRoutes();
		
		TripCalculator test = new TripCalculator(routes);
		System.out.println(Arrays.toString(test.getOutput()));
	}
}
