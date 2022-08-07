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
	boolean flNext = false;
	@Override
	public boolean hasNext() {
		return currentInd < size;
	}

	@Override
	public T next() {
		flNext = true;
		return  array[currentInd++];
	}
	
	@Override
	public void remove() {
		if(!flNext) {
			throw new IllegalStateException();
		}
		ArrayList.this.remove(--currentInd);//to  previous object 
		flNext = false;
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
		int index = indexOf(pattern);
		if(index < 0) {
			return false;
		}
		removeByIndex(index);
		return true;
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
		if(index < 0 || index > size) {
			return null;
		}
			res = array[index];
			removeByIndex(index);
		return res;
	}
	
	private void removeByIndex(int index) {
		System.arraycopy(array, index + 1, array, index, size - index);
		//array[size] == array[size - 1] => Memory leak
		array[size] = null;//solution for preventing memory leak
		size--;
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

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int sizeOld =size();

	      // find first element to delete
        int index = 0;
        while (true) {
            if (index == sizeOld) {
                return false;
            }
            T element = array[index];
            if (predicate.test(element)) {
                break;
            }
            index++;
        }
        
        // skip and copy
        int tempIndex = index;
        while (++index < sizeOld) {
            T element = array[index];
            if (!predicate.test(element)) {
                array[tempIndex++] = element;
            }
        }
        
        // null out the tail of array
        while (tempIndex < sizeOld) {
            array[tempIndex++] = null;
            size--;
        }

      return sizeOld > size();

		//TODO
		//Write method for removing all objects matching the given 
		//predicate with O[N] 
		//bonus: with no additional arrays (playing with two indexes)
		
		//take into consideration a possible memory leak (reference from index == size should be null's)
		
	}
  //Write method for removing all objects matching the given predicate with O[N] 
}
