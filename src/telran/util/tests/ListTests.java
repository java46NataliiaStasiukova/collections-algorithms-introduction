package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.List;

abstract class ListTests extends CollectionTests {

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
	}
	
	@Test
	void addIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		//TODO
		//complete the test
	}
	//TODO
	//write rest three tests

}
