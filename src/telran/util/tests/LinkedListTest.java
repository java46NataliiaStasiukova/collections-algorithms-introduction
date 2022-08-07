package telran.util.tests;

import static org.junit.Assert.assertArrayEquals;


import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import telran.util.LinkedList;

public abstract class LinkedListTest extends ListTests {

		LinkedList<Integer> linkedList;
		@BeforeEach
		@Override
		void setUp() throws Exception {
			super.setUp(); // content of the collection is {10, -5, 13, 20, 40, 15} from the setup 
			linkedList = (LinkedList<Integer>)list; //
		}
		@Test
		void reverseTest() {
			linkedList.reverse();
			Integer[] array = linkedList.toArray(new Integer[0]);
			Integer[] expected = {15, 40, 20, 13, -5, 10};
			assertArrayEquals(expected, array);
			
			LinkedList<Integer> linkedList2 = new LinkedList<>();//{10, -5, 30}
			linkedList2.add(10);
			linkedList2.add(-5);
			linkedList2.add(30);
			linkedList2.reverse();
			Integer[] array2 = linkedList2.toArray(new Integer[0]);
			Integer[] expected2 = {30, -5, 10};
			assertArrayEquals(expected2, array2);
	 	}

}