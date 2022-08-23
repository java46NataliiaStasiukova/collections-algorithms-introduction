package telran.util.tests;

import telran.util.Collection;
import telran.util.StandardTreeSet;

class StandardTreeSetTests extends SortedSetTests {

	@Override
	protected Collection<Integer> createCollection() {
		
		return new StandardTreeSet<Integer>();
	}
	

}
