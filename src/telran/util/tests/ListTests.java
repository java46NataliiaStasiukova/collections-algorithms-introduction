package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.List;

abstract class ListTests extends CollectionTests {



	@Test
	void addIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		//TODO complete the test
		int number = 10;
		assertTrue(list.add(0, number));
		assertEquals(number, list.get(0));
		int currentSize = 100 - list.size();
		int count = 0;
		do {
			list.add(2, number);
			count++;
		}while(count < currentSize);
		assertEquals(100, list.size());
		assertTrue(list.add(list.size() + 1, 155));
		assertEquals(155, list.get(list.size()));
		currentSize = list.size();
		assertFalse(list.add(-1, number));
		assertEquals(currentSize, list.size());
		
	}
	//TODO write rest three tests
	
	@Test
	void removeIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		int currentSize = list.size();
		assertEquals(1, list.remove(0));
		assertEquals(list.size(), currentSize - 1);
		assertEquals(2, list.get(0));
		currentSize = list.size();
		assertEquals(3, list.remove(2));
		assertEquals(list.size(), currentSize - 1);
		assertEquals(4, list.get(2));
		currentSize = list.size();
		assertEquals(12, list.remove(list.size() - 1));
		assertEquals(list.size(), currentSize - 1);
		assertEquals(2, list.get(0));
		currentSize = list.size();
		assertNull(list.remove(-1));
		assertEquals(list.size(), currentSize);
		
	}
	
	@Test
	void indexOfTest() {
		List<Integer> list = (List<Integer>) collection;
		list.add(1);
		assertEquals(0, list.indexOf(1));
	}
	
	@Test
	void lastIndexOf() {
		List<Integer> list = (List<Integer>) collection;
		list.add(1);
		assertEquals(list.size() - 1, list.lastIndexOf(1));
	}
	
	@Test
	void getTest() {
		List<Integer> list = (List<Integer>) collection;
		assertEquals(1, list.get(0));
		assertNull(list.get(-1));
	}
	
	@Test
	void test() {
		Integer ar[] = new Integer[3];
		System.out.println(Arrays.toString(ar));
	}
	
	

}