import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RouteManagerTest {

	// testing constructor
	@Test
	void constructor_routes_notNull() {
		RouteManager test = new RouteManager("tests.txt");
		assertNotNull(test.getRoutes());
	}
	
	// testing addRoute
	@Test
	void addRoute_start_notNull() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute(null, "B", 1));
	}
	@Test
	void addRoute_start_lessThan65() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("@", "B", 1));
	}
	@Test
	void addRoute_start_greaterThan90() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("[", "B", 1));
	}
	@Test
	void addRoute_finish_notNull() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("A", null, 1));
	}
	@Test
	void addRoute_finish_lessThan65() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("A", "@", 1));
	}
	@Test
	void addRoute_finish_greaterThan90() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("A", "[", 1));
	}
	@Test
	void addRoute_distance_negative() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("A", "B", -1));
	}
	@Test
	void addRoute_distance_zero() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.addRoute("A", "B", 0));
	}
	@Test
	void addRoute_validInputAA_routeAddedMessage() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Route added", test.addRoute("A", "A", 1));
	}
	@Test
	void addRoute_validInputZZ_routeAddedMessage() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Route added", test.addRoute("Z", "Z", 1));
	}
	@Test
	void addRoute_validInputAA_routeExists() {
		RouteManager test = new RouteManager("tests.txt");
		test.addRoute("A", "A", 1);
		int [][] temporary = test.getRoutes();
		assertEquals(temporary[1][1], 1);
	}
	@Test
	void addRoute_validInputZZ_routeExists() {
		RouteManager test = new RouteManager("tests.txt");
		test.addRoute("Z", "Z", 1);
		int [][] temporary = test.getRoutes();
		assertEquals(temporary[26][26], 1);
	}
	
	// testing removeRoute
	@Test
	void removeRoute_start_notNull() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute(null, "B"));
	}
	@Test
	void removeRoute_start_lessThan65() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute("@", "B"));
	}
	@Test
	void removeRoute_start_greaterThan90() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute("[", "B"));
	}
	@Test
	void removeRoute_finish_notNull() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute("A", null));
	}
	@Test
	void removeRoute_finish_lessThan65() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute("A", "@"));
	}
	@Test
	void removeRoute_finish_greaterThan90() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("Invalid input", test.removeRoute("A", "["));
	}
	@Test
	void removeRoute_validInput_noSuchRouteFound() {
		RouteManager test = new RouteManager("tests.txt");
		assertEquals("No such route found", test.removeRoute("A", "B"));
	}
	@Test
	void removeRoute_validInput_routeRemovedMessage() {
		RouteManager test = new RouteManager("tests.txt");
		test.addRoute("A", "B", 1);
		assertEquals("Route removed", test.removeRoute("A", "B"));
	}
	@Test
	void removeRoute_validInput_routeDoesNotExist() {
		RouteManager test = new RouteManager("tests.txt");
		test.addRoute("A", "B", 1);
		test.removeRoute("A", "B");
		int [][] temporary = test.getRoutes();
		assertEquals(temporary[1][2], 0);
	}
}
