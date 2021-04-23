/**
 * TripCalculator is responsible for calculating route information using the
 * supplied adjacency matrix
 * @author Nathaniel Wu
 * @version 1.0
 */
public class TripCalculator {
	private int [][] routes;
	private int recursiveCounter;
	
	/**
	 * Constructor that validates the adjacency matrix provided 
	 * @param routes		adjacency matrix
	 */
	public TripCalculator(int [][] routes) {
		if (validateRoutes(routes)) {
			this.routes = routes;
		}
		else {
			System.out.println("Invalid route input");
			// create empty routes [][] initialized to -1 to represent no edges
			routes = new int [27][27];
			for (int i = 0; i < routes.length; i++) {
				for (int j = 0; j < routes[0].length; j++) {
					routes[i][j] = -1;
				}
			}
		}
	}
	
	/**
	 * Calculates the route information supplied in the requirements
	 * @return		array of int results
	 */
	public int [] getOutput() {
		// calculate all outputs
		int [] results = new int [10];
		results[0] = threeStops(1, 2, 3);		// calculate ABC
		results[1] = twoStops(1, 4);			// calculate AD
		results[2] = threeStops(1, 4, 3);		// calculate ADC
		results[3] = threeStops(1, 5, 2, 3, 4); // calculate AEBCD
		results[4] = threeStops(1, 5, 4);		// calculate AED
		results[5] = threeStopCycles(3);		// calculate CC with max 3 stops
		results[6] = tripsWithFourStops(1, 3);	// calculate AC with exactly 4 stops
		results[7] = shortestPath(1, 3);		// calculate lowest AC
		results[8] = shortestPath(2, 2);		// calculate lowest BB
		results[9] = cyclesWithLimit(3, 30);	// calculate number of CC <30
		return results;
	}
	
	/**
	 * Finds the edge between two vertices if it exists
	 * @param start		starting vertex
	 * @param finish	finishing vertex
	 * @return			edge distance if possible, otherwise -1
	 */
	private int twoStops(int start, int finish) {
		if (routes[start][finish] == -1) {
			return -1;
		}
		else {
			return routes[start][finish];
		}
	}
	
	/**
	 * Finds the total edge distance between three vertices if it exists
	 * @param start		starting vertex
	 * @param middle	middle vertex
	 * @param finish	finishing vertex
	 * @return			edge distance if possible, otherwise -1
	 */
	private int threeStops(int start, int middle, int finish) {
		if (routes[start][middle] == -1 || routes[middle][finish] == -1) {
			return -1;
		}
		else {
			return routes[start][middle] + routes[middle][finish];
		}
	}
	
	/**
	 * Finds the total edge distance between five vertices if it exists
	 * @param first		first vertex
	 * @param second	second vertex
	 * @param third		third vertex
	 * @param fourth	fourth vertex
	 * @param fifth		fifth vertex
	 * @return			edge distance if possible, otherwise -1
	 */
	private int threeStops(int first, int second, int third, int fourth, int fifth) {
		if (routes[first][second] == -1 || routes[second][third] == -1 || 
				routes[third][fourth] == -1 || routes[fourth][fifth] == -1) {
			return -1;
		}
		else {
			return routes[first][second] + routes[second][third] + 
					routes[third][fourth] + routes[fourth][fifth];
		}
	}
	
	/**
	 * Calculates the number of cyclic trips from the starting vertex with <3 stops
	 * @param start		starting vertex
	 * @return			number of <3 stop cyclic trips
	 */
	private int threeStopCycles(int start) {
		int counter = 0;
		// first vertex
		for (int i = 0; i < routes.length; i++) {
			if (routes[start][i] >= 0) {
				// second vertex
				if (routes[i][start] >= 0) {
					counter++;
				}
				for (int j = 0; j < routes.length; j++) {
					if (routes[i][j] >= 0) {
						// third vertex
						if (routes[j][start] >= 0) {
							counter++;
						}
					}
				}
			}
		}
		return counter;
	}
	
	/**
	 * Calculate the number of trips from the starting vertex to the finishing vertex
	 * with exactly 4 stops
	 * @param start		starting vertex
	 * @param finish	finishing vertex
	 * @return			number of 4 stop trips
	 */
	private int tripsWithFourStops(int start, int finish) {
		int counter = 0;
		// first vertex
		for (int i = 0; i < routes.length; i++) {
			if (routes[1][i] >= 0) {
				// second vertex
				for (int j = 0; j < routes.length; j++) {
					if (routes[i][j] >= 0) {
						// third vertex
						for (int k = 0; k < routes.length; k++) {
							if (routes[j][k] >= 0) {
								// fourth vertex
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
	
	/**
	 * Calculates the shortest path from the starting vertex to the finishing vertex
	 * using Dijkstra's algorithm. An additional leg is tagged on if searching for a
	 * cyclic trip
	 * @param start		starting vertex
	 * @param finish	finishing vertex
	 * @return			shortest trip if possible, otherwise -1
	 */
    private int shortestPath(int start, int finish){
        int [] distances = new int[routes.length];
        boolean [] visited = new boolean[routes.length];
        // initialize distances to Integer.MAX_VALUE and the start vertex to 0
        for (int i = 0; i < routes.length ; i++) {
            distances[i] = Integer.MAX_VALUE;
        }
        distances[start] = 0;

        for (int i = 1; i < routes.length; i++) {
            // get the closest vertex
            int minimum = Integer.MAX_VALUE;
            int closestVertex = -1;
            for (int j = 1; j < routes.length; j++) {
                if (visited[j] == false && minimum > distances[j]) {
                    minimum = distances[j];
                    closestVertex = j;
                }
            }
            // update the visited vertices if valid
            if (closestVertex == -1) {
            	break;
            }
            else {
            	visited[closestVertex] = true;
            }

            // search adjacent vertices and update distances if needed
            for (int j = 1; j < routes.length; j++) {
                if (routes[closestVertex][j] > -1 && visited[j] == false && 
                		routes[closestVertex][j] != Integer.MAX_VALUE) {
                	if (routes[closestVertex][j] + distances[closestVertex] < distances[j]) {
                		distances[j] = routes[closestVertex][j] + distances[closestVertex];
                	}    
                }
            }
        }
        // return one-way trips
        if (start != finish) {
        	if (distances[finish] == Integer.MAX_VALUE) {
        		return -1;
        	}
        	else {
        		return distances[finish];
        	}
        }
        // add additional leg back to the starting vertex and then return the cyclic trip
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
        	if (minimum == Integer.MAX_VALUE) {
        		return -1;
        	}
        	else {
        		return minimum;
        	}
        }
    }
    
    /**
     * Recursively calculate the number of unique cyclic trips for a certain vertex
     * that is less than a particular distance
     * @param start		starting vertex
     * @param limit		maximum trip distance
     * @return			number of valid cyclic trips
     */
    private int cyclesWithLimit(int start, int limit) {
    	// counter increments to 0 for the initial start
    	recursiveCounter = -1;
    	recursiveCycles(start, start, 0, limit);
    	return recursiveCounter;
    }
    
    /**
     * Recursive helper method for calculating the number of unique cyclic trips
     * @param start			starting vertex
     * @param current		current vertex
     * @param distance		current trip distance
     * @param limit			maximum trip distance
     */
    private void recursiveCycles(int start, int current, int distance, int limit) {
    	if (distance >= limit) {
    		return;
    	}
    	if (current == start) {
    		recursiveCounter++;
    	}
    	for (int i = 1; i < routes.length; i++) {
    		if (routes[current][i] > -1) {
    			recursiveCycles(start, i, distance + routes[current][i], limit);
    		}
    	}
    }
    
    /**
     * Validate adjacency matrix containing route information
     * @param routes		adjacency matrix to validate
     * @return				true if valid, false if invalid
     */
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
				// no cyclic routes
				if (i == j && routes[i][j] != -1) {
					return false;
				}
			}
		}
		return true;
	}
}
