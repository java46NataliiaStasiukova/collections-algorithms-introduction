package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;
		Node(T obj) {
			this.obj = obj;
		}
	}
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	private class LinkedListIterator implements Iterator<T> {
		Node<T> currentInd = head;
		@Override
		public boolean hasNext() {

			return currentInd != null;
		}

		@Override
		public T next() {
			T res  = currentInd.obj;
			currentInd = currentInd.next;
			return res;

		}
		
	}

	@Override
	public boolean add(T obj) {
		Node<T> newNode = new Node<>(obj);
		if (head == null) {
			head = tail = newNode;
		} else {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
		return true;
	}
	@Override
	public boolean remove(Object pattern) {
		if(head != null) {
			int index = indexOf(pattern);
			if(index < 0) {
				return false;
			}
			removeByIndex(index);
		}
		return true;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldSize = size;
		T temp = get(0);
		int index = 0;
		while(index < size) {
			if(predicate.test(temp)) {
				remove(index);
			} else {
				index++;
				temp = get(index);
			}	
		}
		
		return oldSize > size;
	}

	@Override
	public boolean contains(Object pattern) {

		return indexOf(pattern) >= 0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator() ;
	}

	@Override
	public boolean add(int index, T obj) {
		boolean res = false;
		if (index >=0 && index <= size) {
			res = true;
			if (index == size) {
				add(obj);
			} else if (index == 0) {
				addHead(obj);
			} else {
				addIndex(index, obj);
			}
		}
		return res;
	}

	private void addIndex(int index, T obj) {
		size++;
		Node<T> newNode = new Node<>(obj);
		Node<T> afterNode = getNodeIndex(index);
		Node<T> beforeNode = afterNode.prev;
		newNode.next = afterNode;
		afterNode.prev = newNode;
		beforeNode.next = newNode;
		newNode.prev = beforeNode;
		
	}

	private Node<T> getNodeIndex(int index) {
		
		return index > size / 2 ? getNodeRightToLeft(index) : getNodeLeftToRight(index);
	}

	private Node<T> getNodeLeftToRight(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	private Node<T> getNodeRightToLeft(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private void addHead(T obj) {
		size++;
		Node<T> newNode = new Node<>(obj);
		newNode.next = head;
		head.prev = newNode;
		head = newNode;
		
		
	}

	private boolean checkExistingIndex(int index) {
		
		return index >= 0 && index < size;
	}

	@Override
	public T remove(int index) {
		T res = null;
		if (index >=0 && index < size) {
			res = get(index);
			removeByIndex(index);
		}
		return res;
	}
	
	private void removeByIndex(int index) {
		Node<T> currentNode = getNodeIndex(index);
		if(currentNode.equals(head)) {
			head = currentNode.next;
		}
		else if(currentNode.equals(tail)) {
			tail = currentNode.prev;
		}
		else {
			Node<T> afterNode = currentNode.next;
			currentNode = currentNode.prev;
			currentNode.next = afterNode;
		}
		size--;
	}

	@Override
	public int indexOf(Object pattern) {
		Node<T> temp = head;
		int index = 0;
		while(!temp.obj.equals(pattern)) {
			temp = temp.next;
			index++;
			if(temp == null) {
				return -1;
			}
		}
		return index;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		Node<T> temp = tail;
		int index = size - 1;
		while(!temp.obj.equals(pattern)) {
			temp = temp.prev;
			index--;
			if(temp == null) {
				return -1;
			}
		}
		return index;
	}

	@Override
	public T get(int index) {
		T res = null;
		if (checkExistingIndex(index)) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
		}
		return res;
	}

}