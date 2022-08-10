package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
		Node<T> current = head;
		boolean flNext = false;
		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T res  = current.obj;
			current = current.next;
			flNext = true;
			return res;

		}
		@Override
		public void remove() {
			if(!flNext) {
				throw new IllegalStateException();
			}
			if(current == null) {
				removeNode(tail);
				
			} else {
				removeNode(current.prev);
			}
			flNext = false;
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
		boolean res = false;
		if(head != null) {
			int index = indexOf(pattern);
			if(index >= 0) {
				res = true;
				Node<T> node = getNodeIndex(index);
				removeNode(node);
			}
		}
		return res;
	}
	
	private void removeNode(Node<T> currentNode) {
		if(currentNode == head) {
			removeHead();
		}
		else if(currentNode == tail) {
			removeTail();
		}
		else {
			removeMiddle(currentNode);
		}
		size--;
	}


	private void removeHead() {
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		
	}
	private void removeTail() {
		tail = tail.prev;
		tail.next = null;
		
	}
	private void removeMiddle(Node<T> currentNode) {
		Node<T> afterNode = currentNode.next;
		currentNode = currentNode.prev;
		currentNode.next = afterNode;
		
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
		Node<T> newNode = new Node<>(obj);
		Node<T> afterNode = getNodeIndex(index);
		Node<T> beforeNode = afterNode.prev;
		newNode.next = afterNode;
		afterNode.prev = newNode;
		beforeNode.next = newNode;
		newNode.prev = beforeNode;
		size++;
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
			Node<T> node = getNodeIndex(index);
			res = node.obj;
			removeNode(node); //res =
		}
		return res;
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
	/**
	 * performs reversing of the objects order
	 * current - {10, -5, 30} - after reverse - {30, -5, 10}
	 * +to write test
	 */
	public void reverse() {//void
		//TODO
		Node<T> previos = null;
		Node<T> current = head;
		Node<T> nextNode = null;//{10, 5, 3}
		while(current != null) {
			nextNode = current.next;
			current.next = previos;
			previos = current;
			current = nextNode;
		}
		head = previos;
	}

}