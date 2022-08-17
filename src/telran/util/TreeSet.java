
package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class TreeSet<T> implements SortedSet<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> root;
	int size;
	Comparator<T> comp;
	
	private Node<T> getLeastNodeFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root == null ? null : getLeastNodeFrom(root);
		Node<T> prevNode = current;
		boolean flNext = false;
		@Override
		public boolean hasNext() {
			
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevNode = current;
			T res = current.obj;
			updateCurrent();
			flNext = true;
			return res;
		}
		private void updateCurrent() {
			current = current.right != null ? getLeastNodeFrom(current.right) : getGreaterParent(current);
			
		}

		private Node<T> getGreaterParent(Node<T> node) {
			
			while(node.parent != null && node.parent.left != node) {
				node = node.parent;
			}
			return node.parent;
		}

		@Override
		public void remove() {
			//TODO
			if (!flNext) {
				throw new IllegalStateException();
			}
			if (isJunction(prevNode)) {
				current = prevNode;
			}
			removeNode(prevNode);
			flNext = false;
		}
	}
	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}
	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}
	@Override
	public boolean add(T obj) {
		//TODO
		//no cycles allowed
		Node<T> newNode = new Node<>(obj);
		boolean res = add(root, newNode);
		if(res) {
			size++;
		}
//		Node<T> parent = getNodeOrParent(obj);
//		boolean res = false;
//		int compRes = 0;
//		if (parent == null || (compRes = comp.compare(obj, parent.obj)) != 0) {
//			//obj doesn't exist
//			Node<T> newNode = new Node<>(obj);
//			if (parent == null) {
//				//added first element that is the root
//				root = newNode;
//			} else if(compRes > 0) {
//				parent.right = newNode;
//			} else {
//				parent.left = newNode;
//			}
//			res = true;
//			newNode.parent = parent;
//			size++;
//		}
		return res;
	}

	private boolean add(Node<T> parent, Node<T> newNode) {
		boolean res = true;
		if(parent == null) {
			this.root = newNode;
		} else {
			int resComp = comp.compare(newNode.obj, parent.obj);
			if(resComp == 0) {
				res = false;
			} else {
				if(resComp < 0) {
					if(parent.left == null) {
						insert(parent, newNode, true);// new node inserted to left from parent.
					} else {
						add(parent.left, newNode);
					}
				} else {
					if(parent.right == null) {
						insert(parent, newNode, true);// new node inserted to right from parent.
					} else {
						add(parent.right, newNode);
				}
			}
		}
		
	}
		
		return res;
}
	private void insert(Node<T> parent, Node<T> newNode, boolean isLeft) {
		if(isLeft) {
			parent.left = newNode;
		} else {
			parent.right = newNode;
		}
		newNode.parent = parent;
	}
	private Node<T> getNodeOrParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while (current != null) {
			parent = current;
			compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				break;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean res = false;
		if(root == null) {
			return res;
		}
		@SuppressWarnings("unchecked")
		Node<T> current = getNodeOrParent((T)pattern);
		if(isNodeExist(pattern)) {
			removeNode(current);
			res = true;
		}
		return res;
}

	private boolean isJunction(Node<T> node) {

		return (node.left != null && node.right != null);
	}
	
	private void removeJunctionNode(Node<T> node) {
		Node<T> substitution = getLeastNodeFrom(node.right);
		node.obj = substitution.obj;
		removeNonJunctionNode(substitution);
	}
	
	private void removeNonJunctionNode(Node<T> node) {
		Node<T> child = node.left == null ? node.right : node.left;
		Node<T> parent = node.parent;
		if(parent == null) {
			root = child;
		} else {
			if (parent.left == node) {
				parent.left = child;
			} else {
				parent.right = child;
			}
		}
		if (child != null) {
			child.parent = parent;
		}
	}		
	
	private void removeNode(Node<T> node) {
		if(isJunction(node)) {
			removeJunctionNode(node);
		} else {
			removeNonJunctionNode(node);
		}
		size--;
	}

	@SuppressWarnings("unchecked")
	private boolean isNodeExist(Object pattern) {
		Node<T> current = getNodeOrParent((T)pattern);
		int compRes = comp.compare((T) pattern, current.obj);
		return compRes == 0;
}

	@Override
	public boolean contains(Object pattern) {

		return isNodeExist(pattern);
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new TreeSetIterator();
	}

	@Override
	public T first() {
		if (root == null) {
			return null;
		}
		return getLeastNodeFrom(root).obj;
	}

	
	@Override
	public T last() {
		if (root == null) {
			return null;
		}
		return getMostNodeFrom(root).obj;
	}
	private Node<T> getMostNodeFrom(Node<T> node) {
		while(node.right != null) {
			node = node.right;
		}
		return node;
	}

}
