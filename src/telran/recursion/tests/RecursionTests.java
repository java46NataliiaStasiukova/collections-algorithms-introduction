package telran.recursion.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static telran.recursion.LineRecursion.*;

class RecursionTests {

	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	void SquarTest() {
		assertEquals(25, square(5));
		assertEquals(100, square(10));
		assertEquals(100, square(-10));
	}
	
	@Test
	void factorialTest() {
		assertEquals(24, factirial(4));
	}
	@Test
	void powTest() {
		assertEquals(16, pow(2, 4));
		assertEquals(1000, pow(10, 3));
		assertEquals(-1000, pow(-10, 3));


	}
	@Test
	void sumTest() {
		int ar[] = {1, 2, 3, 4};
		assertEquals(10, sum(ar));
	}

//	@Test
//	void test() {
//		f();
//		System.out.println(count);
//	}
//	int count = 0;
//	
//	private void f() {
//		if(Math.random() < 0.99) {
//			f();
//			count++;
//		}
//		if(Math.random() < 1) {
//			f();
//			count++;
//		}
//	}

}
