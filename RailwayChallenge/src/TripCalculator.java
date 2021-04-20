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
		if (routes[1][2] == 0 || routes[2][3] == 0) {
			return -1;
		}
		else {
			return routes[1][2] + routes[2][3];
		}
	}
	private int output2() {
		// calculate AD
		if (routes[1][4] == 0) {
			return -1;
		}
		else {
			return routes[1][4];
		}
	}
	private int output3() {
		// calculate ADC
		if (routes[1][4] == 0 || routes[4][3] == 0) {
			return -1;
		}
		else {
			return routes[1][4] + routes[4][3];
		}
	}
	private int output4() {
		// calculate AEBCD
		if (routes[1][5] == 0 || routes[5][2] == 0 || 
				routes[2][3] == 0 || routes[3][4] == 0) {
			return -1;
		}
		else {
			return routes[1][5] + routes[5][2] + routes[2][3] + routes[3][4];
		}
	}
	private int output5() {
		// calculate AED
		if (routes[1][5] == 0 || routes[5][4] == 0) {
			return -1;
		}
		else {
			return routes[1][5] + routes[5][4];
		}
	}
	private int output6() {
		// calculate CC with max 3 stops
		
		return 0;
	}
	private int output7() {
		// calculate AC with 4 stops
		return 0;
	}
	private int output8() {
		// calculate lowest AC
		return 0;
	}
	private int output9() {
		// calculate lowest BB
		return 0;
	}
	private int output10() {
		// calculate number of CC <30
		return 0;
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
				if (routes[i][j] < 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[]args) {
		RouteManager manager = new RouteManager();
		int [][] routes = manager.getRoutes();
		
		TripCalculator test = new TripCalculator(routes);
		System.out.println(Arrays.toString(test.getOutput()));
	}
}
