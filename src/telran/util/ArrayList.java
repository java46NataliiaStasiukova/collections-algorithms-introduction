package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
private static final int DEFAULT_CAPACITY = 16;
private T[] array;
private int size;
@SuppressWarnings("unchecked")
public ArrayList(int capacity) {
	array = (T[]) new Object[capacity];
}
public ArrayList() {
	this(DEFAULT_CAPACITY);
}
private class ArrayListIterator<T> implements Iterator<T> {

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	@Override
	public boolean add(T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		// TODO
		//array reallocation isn't done
		//that is new array won't be created - essence of remove
		//to use System.arraycopy
		// size--
		int index = findIndex(pattern);
		if(index < 0) {
			return false;
		}
		T[] temp;// = (T[]) new Object[array.length];
		temp = Arrays.copyOf(array, array.length);
		System.arraycopy(array, 0, temp, 0, index);
		System.arraycopy(array, index + 1, temp, index, temp.length - index - 1);
		size--;
		array = temp;
		return true;
	}
	private int findIndex(Object pattern) { 
		for(int i = 0; i < array.length; i++) {
			if(array[i] == pattern) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		//NOT DONE YET
		
		
		return false;
	}

	@Override
	public boolean contains(Object pattern) {
		
		return findIndex(pattern) > 0 ? true : false;
	}

	@Override
	public int size() {
		
		return size;
	}
	
	

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
//if size == array.length you should do reallocation see the method add
//if size < array.length new array won't be created - essence of the algorithm
		T[] temp;
		if(index < 0) {
			return false;
		}
		if(array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		temp = Arrays.copyOf(array, array.length + 1);
		System.arraycopy(array, 0, temp, 0, index);
		System.arraycopy(array, index, temp, index + 1, array.length - index);
		temp[index] = obj;
		array = temp;
		size++;
		
		return true;
	}

	@Override
	public T remove(int index) {
		if(index < 0) {
			return null;
		}
		T res = array[index];
		T[] temp;
		temp = Arrays.copyOf(array, array.length);
		System.arraycopy(array, 0, temp, 0, index);
		System.arraycopy(array, index + 1, temp, index, temp.length - index - 1);
		size--;
		array = temp;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == pattern) {
				return i;
			}
		}
		
		return 0;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for(int i = array.length - 1; i > 0; i--) {
			if(array[i] == pattern) {
				return i;
			}
		}
		
		return 0;
	}
	@Override
	public Integer get(int index) {
		for(int i = 0; i < array.length; i++) {
			if(i == index) {
				return (Integer) array[i];
			}
		}
		return null;
	}

}
