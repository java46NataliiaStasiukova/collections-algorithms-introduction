package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.LinkedList;

public class LinkedListTest extends ListTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new LinkedList<>();
	}
	@Test
	void reverseTest() {
		LinkedList<Integer> linkedList = (LinkedList<Integer>) collection;
		//{ 10, -5, 13, 20, 40, 15 }; - initial order 1
		//{ 15, 40, 20, 13, -5, 10, 100 }; - initial order 2 
		Integer expected1[] = {15, 40, 20, 13, -5, 10};
		Integer expected2[] = { 100, 10, -5, 13, 20, 40, 15 };
		linkedList.reverse();
		assertArrayEquals(expected1, linkedList.toArray(new Integer[0]));
		linkedList.add(100);
		linkedList.reverse();
		assertArrayEquals(expected2, linkedList.toArray(new Integer[0]));
	}	
//		@Test
//		void reverseTest() {
//			LinkedList<Integer> linkedList = (LinkedList<Integer>) collection;
//			linkedList.reverse();
//			Integer[] array = linkedList.toArray(new Integer[0]);
//			Integer[] expected = {15, 40, 20, 13, -5, 10};
//			assertArrayEquals(expected, array);
//			
////			LinkedList<Integer> linkedList2 = new LinkedList<>();//{10, -5, 30}
////			linkedList2.add(10);
////			linkedList2.add(-5);
////			linkedList2.add(30);
////			linkedList2.reverse();
////			Integer[] array2 = linkedList2.toArray(new Integer[0]);
////			Integer[] expected2 = {30, -5, 10};
////			assertArrayEquals(expected2, array2);
//	 	}

}