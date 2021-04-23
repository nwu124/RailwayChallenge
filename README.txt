# Railway Challenge for MiBolsillo 2021
***
Includes a RouteManager class for managing railway routes and a separate 
TripCalculator class for determining the route information for the current
set of routes. The specific output is tailored for the original requirements
specified at:
https://docs.google.com/document/d/1MTGDcV4DNqCgXtzx-AqJGhPT2qwWfrqV4Qkc2kHjRNQ/edit
but can be easily altered within the TripCalculator class. The included driver 
provides a basic command line interface for interacting with both classes at once.

## Installation
***
This solution is designed to be run on a local machine. The project can 
be loaded onto any Java IDE for compilation and running. Development and 
testing was done using the Eclipse IDE using Java version 8 update 241. 

## Algorithm/Implementation
***
A 27x27 adjacency matrix was used for storing the edges between the vertices since
there are only 26 possible uppercase letters for specifying vertices. 27x27 was used
instead of 26x26 purely for convenience so that the first letter of the alphabet would
be in row/col 1 with minimal additional storage usage.

Outputs 1-5: Utilized simple edge look up by referencing the appropriate rows and 
columns and making sure that the trip was possible in its entirety.

Output 6: Utilized 2 nested for loops to exhaustively search all possible routes 
leading away from the starting vertex. If it was possible to return to the starting 
vertex at any time, a counter was incremented accordingly.

Output 7: Very similar to output 6 but with an additional nested for loop for allowing
4 vertex stops. If it was possible to return to the starting vertex at the innermost
loop, a counter was incremented accordingly.

Output 8: Calculated the shortest path between 2 vertices using Dijkstra's algorithm
and visiting the closest unvisited vertex and checking to see if the shortest path array 
needed to be updated. This was repeated until all vertices had been visited.

Output 9: Very similar to output 8 but with an additional distance added to return to the
starting vertex. This was done since Dijkstra's algorithm normally only calculates one
way trips.

Output 10: Recursively calculates the number of unique cyclic trips by exhaustively 
searching all possible trip combinations that stay under the maximum allowable trip
distance.

## Unit Testing
***
Unit testing was done using JUnit 5 with tests stored in the tests folder. Only public
methods were tested in each class.

## Test Files
***
Several different files of routes are included as well as their expected outputs.
-1 indicates that there is no possible route. All files are utilized in unit tests
so modifications to the routes in these files will result in test failure.

routes.txt = default filename containing the sample test graph specified in 
the original requirements. 
Routes: AB5, AD5, AE7, BC4, CD8, CE2, DC8, DE6, EB3
Expected results: {9, 5, 13, 22, -1, 2, 3, 9, 9, 7}

routes1.txt = test graph
Routes: AB1, BC1, CD1, DE1, EA1
Expected results: {2, -1, -1, -1, -1, 0, 0, 2, 5, 5}

routes2.txt = test graph
Routes: AB1, BC2, CD3, DB2, DE4, EA2
Expected results: {3, -1, -1, -1, -1, 1, 0, 3, 7, 11)

routes3.txt = test graph
Routes: AB3, AD3, AE7, BC2, CD4, DC5, DE2, ED2 
Expected results: {5, 3, 8, -1, 9, 1, 3, 5, -1, 13}

tests.txt = blank file
Routes: NONE
Expected results: {-1, -1, -1, -1, -1, 0, 0, -1, -1, 0}

