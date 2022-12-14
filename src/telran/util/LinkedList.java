package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T>  extends AbstractCollection<T>implements List<T> {
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
		Node<T> nodeAfter = currentNode.next;
		Node<T> nodeBefore = currentNode.prev;
		nodeBefore.next = nodeAfter;
		nodeAfter.prev = nodeBefore;
		
	}
	@Override
	public boolean contains(Object pattern) {

		return indexOf(pattern) >= 0;
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

	@Override
	public T remove(int index) {
		T res = null;
		if (index >=0 && index < size) {
			Node<T> node = getNodeIndex(index);
			res = node.obj;
			removeNode(node); 
		}
		return res;
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

	public void reverse() {
		//TODO
		//no cycles allowed(recursion);
		tail = head;
		head = reverse(head);	
	}
	
	public Node<T> reverse(Node<T> node){
		Node<T> current;
		if(node.next == null) {
			return node;
		}
		current = reverse(node.next);
		node.next.next = node;
		node.next = null;
		
		return current;
	}

}
