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
			// create empty routes [][] initialized to -1
			routes = new int [27][27];
			for (int i = 0; i < routes.length; i++) {
				for (int j = 0; j < routes[0].length; j++) {
					routes[i][j] = -1;
				}
			}
		}
	}
	// public methods
	public int [] getOutput() {
		// calculate all outputs
		int [] results = new int [10];
		results[0] = threeStops(1, 2, 3);						// calculate ABC
		results[1] = twoStops(1, 4);							// calculate AD
		results[2] = threeStops(1, 4, 3);						// calculate ADC
		results[3] = threeStops(1, 5, 2) + threeStops(2, 3, 4);	// calculate AEBCD
		results[4] = threeStops(1, 5, 4);						// calculate AED
		results[5] = threeStopCycles(3);						// calculate CC with max 3 stops
		results[6] = tripsWithFourStops(1, 3);					// calculate AC with exactly 4 stops
		results[7] = shortestPath(1, 3);// calculate lowest AC
		results[8] = shortestPath(2, 2);// calculate lowest BB
		// results[9] = output9();// calculate number of CC <30
		return results;
	}
	
	// private methods
	// -1 means not possible
	private int twoStops(int start, int finish) {
		if (routes[start][finish] == -1) {
			return -1;
		}
		else {
			return routes[start][finish];
		}
	}
	private int threeStops(int start, int middle, int finish) {
		if (routes[start][middle] == -1 || routes[middle][finish] == -1) {
			return -1;
		}
		else {
			return routes[start][middle] + routes[middle][finish];
		}
	}
	private int threeStopCycles(int start) {
		int counter = 0;
		// first stop, can we go back to the start? 
		if (routes[start][start] >= 0) {
			counter++;
		}
		// search all routes leading away from start
		for (int i = 0; i < routes.length; i++) {
			if (routes[start][i] >= 0) {
				// two stops, can we go back to the start? 
				if (routes[i][start] >= 0) {
					counter++;
				}
				// search all routes leading away from the first stop
				for (int j = 0; j < routes.length; j++) {
					if (routes[i][j] >= 0) {
						// three stops, can we go back to the start? 
						if (routes[j][start] >= 0) {
							counter++;
						}
					}
				}
			}
		}
		return counter;
	}
	private int tripsWithFourStops(int start, int finish) {
		int counter = 0;
		// first stop
		for (int i = 0; i < routes.length; i++) {
			if (routes[1][i] >= 0) {
				// second stop
				for (int j = 0; j < routes.length; j++) {
					if (routes[i][j] >= 0) {
						// third stop
						for (int k = 0; k < routes.length; k++) {
							if (routes[j][k] >= 0) {
								// fourth stop, are we at the finish? 
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
    public int shortestPath(int start, int finish){
        int [] distances = new int[routes.length];
        boolean [] visitedNodes = new boolean[routes.length];
        // initialize distances to Integer.MAX_VALUE and the start node to 0
        for (int i = 0; i < routes.length ; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0;

        for (int i = 1; i < routes.length; i++) {
            // get the closest node
            int minimum = Integer.MAX_VALUE;
            int closestNode = -1;
            for (int j = 1; j < routes.length; j++) {
                if (visitedNodes[j] == false && minimum > distances[j]) {
                    minimum = distances[j];
                    closestNode = j;
                }
            }
            // update the visitedNodes if valid
            if (closestNode == -1) {
            	break;
            }
            else {
            	visitedNodes[closestNode] = true;
            }

            // search adjacent nodes and update distances if needed
            for (int j = 1; j < routes.length; j++) {
                if (routes[closestNode][j] > -1 && visitedNodes[j] == false && 
                		routes[closestNode][j] != Integer.MAX_VALUE) {
                	if (routes[closestNode][j] + distances[closestNode] < distances[j]) {
                		distances[j] = routes[closestNode][j] + distances[closestNode];
                	}    
                }
            }
        }
        // return one-way trips
        if (start != finish) {
        	return distances[finish];
        }
        // add additional leg back to start and then return cycles
        else {
        	int minimum = Integer.MAX_VALUE;
        	for (int i = 1; i < routes.length; i++) {
        		if (routes[i][start] > -1 && routes[i][start] != Integer.MAX_VALUE && 
        				distances[i] != Integer.MAX_VALUE) {
        			if (distances[i] + routes[i][start] < minimum) {
        				minimum = distances[i] + routes[i][start];
        			}
        		}
        	}
        	return minimum;
        }
    }
	private boolean validateRoutes(int [][] routes) {
		if (routes == null) {
			return false;
		}
		if (routes.length != 27 || routes[0].length != 27) {
			return false;
		}
		for (int i = 1; i < routes.length; i++) {
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
