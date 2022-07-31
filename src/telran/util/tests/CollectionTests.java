package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.List;

abstract class  CollectionTests {
protected Collection<Integer> collection;
protected abstract Collection<Integer> createCollection();
private static final int TEST_NUMBER = 5;
int currentSize;

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		collection.add(1);
		collection.add(2);
		collection.add(TEST_NUMBER);
		collection.add(3);
		collection.add(4);
		collection.add(6);
		collection.add(7);
		collection.add(12);
		currentSize = collection.size();
		//TODO fill collection
	}

	//TODO
	//Write 6 tests for collection
	
	@Test
	void addTest() {
		int count = 0;
		int addNumbersAmount = 100 - collection.size();
		do {
			assertTrue(collection.add(TEST_NUMBER));
			count++;
		}while(count < addNumbersAmount);
		assertEquals(100, collection.size());
	}
	
	@Test
	void removeTest() {

		int sizeBeforDel = collection.size();
		int sizeAfterDel;
		assertTrue(collection.remove(TEST_NUMBER));
		sizeAfterDel = collection.size();
		assertEquals(sizeBeforDel, sizeAfterDel + 1);
		assertFalse(collection.remove(TEST_NUMBER));
		assertEquals(sizeAfterDel, sizeAfterDel);
	
		
	}
	
	@Test
	void removeIfTest() { 
		//NOT DONE YET
		//final Predicate<Integer> predicate = ;
		//assertFalse(collection.removeIf());
	}
	@Test
	void containsTest() {

		assertTrue(collection.contains(TEST_NUMBER));
		assertFalse(collection.contains(120));
	}
	
	@Test
	void sizeTest() {
		
		assertEquals(currentSize, collection.size());
	}
	
	@Test 
	void toArrayTest() {
		//NOT DONE YET
		Integer[] expected1 = {1, 2, TEST_NUMBER, 3, 4, 6, 7, 12};
		Integer[] expected2 = new Integer[100];
		assertArrayEquals(expected1, collection.toArray(expected1));
		assertArrayEquals(expected1, collection.toArray(new Integer[0]));
		assertTrue(expected1 == collection.toArray(expected1));
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected2, Arrays.copyOf(expected2, collection.size()));
		
		
		
	}		
	
	

}