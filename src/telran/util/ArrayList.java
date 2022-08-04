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
private class ArrayListIterator implements Iterator<T> {
	int currentInd = 0;
	@Override
	public boolean hasNext() {
		// TODO
		return currentInd < size;
	}

	@Override
	public T next() {
		// TODO
		return  array[currentInd++];
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
		int index = -1;
		for(int i = 0; i < size; i++) {
			if(array[i].equals(pattern)) {
				index = i;
			}
		}
		if(index < 0) {
			return false;
		}
		System.arraycopy(array, index + 1, array, index, size - index);
		size--;
		return true;
	}
	
	

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		int i = 0;
		while(i < size) {
			if(predicate.test(array[i])) {
				remove(i);
			}
			else {
				i++;
			}
		}
		return oldSize > size;
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
		if(index < 0) {
			return false;
		}
		if(array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
		
		return true;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if(index < 0) {
			return null;
		}
		for(int i = 0; i < size; i++) {
			if(i == index) {
				res = array[index];
				System.arraycopy(array, index + 1, array, index, size - index);
			}
		}
		size--;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for(int i = 0; i < size; i++) {
			if(array[i].equals(pattern)) {
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for(int i = size - 1; i > 0; i--) {
			if(array[i].equals(pattern)) {
				return i;
			}
		}
		
		return -1;
	}
	
	@Override
	public T get(int index) {
		for(int i = 0; i < size; i++) {
			if(i == index) {
				return array[i];
			}
		}
		return null;
	}

}
