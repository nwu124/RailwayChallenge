import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TripCalculatorTest {

	@Test
	void getOutput_routes_txt_expectedResults() {
		RouteManager test = new RouteManager("routes.txt");
		TripCalculator calculator = new TripCalculator(test.getRoutes());
		int [] results = calculator.getOutput();
		assertEquals(results[0], 9);
		assertEquals(results[1], 5);
		assertEquals(results[2], 13);
		assertEquals(results[3], 22);
		assertEquals(results[4], -1);
		assertEquals(results[5], 2);
		assertEquals(results[6], 3);
		assertEquals(results[7], 9);
		assertEquals(results[8], 9);
		assertEquals(results[9], 7);
	}
	@Test
	void getOutput_routes1_txt_expectedResults() {
		RouteManager test = new RouteManager("routes1.txt");
		TripCalculator calculator = new TripCalculator(test.getRoutes());
		int [] results = calculator.getOutput();
		assertEquals(results[0], 2);
		assertEquals(results[1], -1);
		assertEquals(results[2], -1);
		assertEquals(results[3], -1);
		assertEquals(results[4], -1);
		assertEquals(results[5], 0);
		assertEquals(results[6], 0);
		assertEquals(results[7], 2);
		assertEquals(results[8], 5);
		assertEquals(results[9], 5);
	}
	@Test
	void getOutput_routes2_txt_expectedResults() {
		RouteManager test = new RouteManager("routes2.txt");
		TripCalculator calculator = new TripCalculator(test.getRoutes());
		int [] results = calculator.getOutput();
		assertEquals(results[0], 3);
		assertEquals(results[1], -1);
		assertEquals(results[2], -1);
		assertEquals(results[3], -1);
		assertEquals(results[4], -1);
		assertEquals(results[5], 1);
		assertEquals(results[6], 0);
		assertEquals(results[7], 3);
		assertEquals(results[8], 7);
		assertEquals(results[9], 11);
	}
	@Test
	void getOutput_routes3_txt_expectedResults() {
		RouteManager test = new RouteManager("routes3.txt");
		TripCalculator calculator = new TripCalculator(test.getRoutes());
		int [] results = calculator.getOutput();
		assertEquals(results[0], 5);
		assertEquals(results[1], 3);
		assertEquals(results[2], 8);
		assertEquals(results[3], -1);
		assertEquals(results[4], 9);
		assertEquals(results[5], 1);
		assertEquals(results[6], 3);
		assertEquals(results[7], 5);
		assertEquals(results[8], -1);
		assertEquals(results[9], 13);
	}
	@Test
	void getOutput_tests_txt_expectedResults() {
		RouteManager test = new RouteManager("tests.txt");
		TripCalculator calculator = new TripCalculator(test.getRoutes());
		int [] results = calculator.getOutput();
		assertEquals(results[0], -1);
		assertEquals(results[1], -1);
		assertEquals(results[2], -1);
		assertEquals(results[3], -1);
		assertEquals(results[4], -1);
		assertEquals(results[5], 0);
		assertEquals(results[6], 0);
		assertEquals(results[7], -1);
		assertEquals(results[8], -1);
		assertEquals(results[9], 0);
	}

}
