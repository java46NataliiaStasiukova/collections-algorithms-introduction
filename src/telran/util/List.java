package telran.util;

public interface List<T> extends Collection<T> {
	/**
	 * insert object at the given index
	 * @param index
	 * @param obg
	 * @return true if index >= 0 && index <= size
	 */
	boolean add(int index, T obg);
	/**
	 * 
	 * @param index
	 * @return reference to being removed (if index >= 0 && index < size) object or null
	 */
	T remove(int index);
	/**
	 * 
	 * @param pattern
	 * @return index of the first object equaled to the given pattern
	 */
	int indexOf(Object pattern);
	/**
	 * 
	 * @param pattern
	 * @return index of the last object equaled to the given pattern
	 */
	int lastIndexOf(Object pattern);
}
