package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> extends AbstractCollection<T> implements SortedSet<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private static final String FILL_SYMBOL = " ";

	private static final int N_SYMBOLS_PER_LEVEL = 2;

	private Node<T> root;
	Comparator<T> comp;
	private Node<T> getLeastNodeFrom(Node<T> node) {
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root == null ? null : getLeastNodeFrom(root);
		Node<T> prev = null;
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
			prev = current;
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
			if (!flNext) {
				throw new IllegalStateException();
			}
			if (isJunction(prev)) {
				current = prev;
			}
			removeNode(prev);
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
		//no cycles allowed
		Node<T> newNode = new Node<>(obj);
		boolean res = add(root, newNode);
		if (res) {
			size++;
		}
		return res;
	}

	private boolean add(Node<T> parent, Node<T> newNode) {
		boolean res = true;
		if (parent == null) {
			root = newNode;
		} else {
			int resComp = comp.compare(newNode.obj, parent.obj);
			if (resComp == 0) {
				res = false;
			} else {
				if (resComp < 0) {
					if (parent.left == null) {
						insert(parent, newNode, true);//new node inserted to left from parent
					} else {
						add(parent.left, newNode);
					}
				} else {
					if (parent.right == null) {
						insert(parent, newNode, false);//new node inserted to right from parent
					} else {
						add(parent.right, newNode);
					}
				}
			}
		}
		return res;
	}
	private void insert(Node<T> parent, Node<T> newNode, boolean isLeft) {
		if (isLeft) {
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
		@SuppressWarnings("unchecked")
		T patternT = (T) pattern;
		Node<T> node = getNodeOrParent(patternT);
		if (node != null && comp.compare(node.obj, patternT) == 0) {
			res = true;
			removeNode(node);
		}
		
		return res;
	}

	private void removeNode(Node<T> node) {
		if (isJunction(node)) {
			removeJunctionNode(node);
		} else {
			removeNonJunctionNode(node);
		}
		size--;
		
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
	private void removeJunctionNode(Node<T> node) {
		Node<T> substitution = getLeastNodeFrom(node.right);
		node.obj = substitution.obj;
		removeNonJunctionNode(substitution);
		
	}
	private boolean isJunction(Node<T> node) {
		
		return node.left != null && node.right != null;
	}
	@Override
	public boolean contains(Object pattern) {
		@SuppressWarnings("unchecked")
		T tPattern = (T)pattern;
		Node<T> node = getNodeOrParent(tPattern);
		return node != null && comp.compare(tPattern, node.obj) == 0;
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
	public void displayRotated() {
		displayRotated(root, 0);
	}
	private void displayRotated(Node<T> root, int level) {
		if (root != null) {
			displayRotated(root.right, level + 1);
			displayRoot(root, level);
			displayRotated(root.left, level + 1);
		}
		
		
	}
	private void displayRoot(Node<T> root, int level) {
		System.out.printf("%s%s\n", FILL_SYMBOL.repeat(level * N_SYMBOLS_PER_LEVEL), root.obj);
		
	}
	public void displayAsDirectory() {
		displayAsDirectory(root, 0);
	}
	private void displayAsDirectory(Node<T> root, int level) {
		if(root != null) {
			displayRoot(root, level);
			displayAsDirectory(root.left, level + 1);
			displayAsDirectory(root.right, level + 1);
		}
		
	}
	public int height() {
		
		return height(root);
	}
	private int height(Node<T> root) {
		int res = 0;
		if(root != null) {
			int heightLeft = height(root.left);
			int heightRight = height(root.right);
			res = Math.max(heightLeft, heightRight) + 1;
		}
		return res;
	}
	public int width() {
		
		return width(root);
	}
	private int width(Node<T> root) {
		int res = 0;
		if(root != null) {
			res = (int) (root.left == null && root.right == null ? 1 : width(root.left) 
					+ width(root.right));
		}
		return res;
	}
	public void inversion() {
		invertion(root);
		comp = comp.reversed();
		
	}
	private Node<T> invertion(Node<T> root) {
		if(root == null) {
			return root;
		}
		Node<T> left = invertion(root.left);
		Node<T> right = invertion(root.right);
		root.left = right;
		root.right = left;
		
		return root;
	}
	
	

	public void balance() {
		@SuppressWarnings("unchecked")
		Node<T>[] array = (Node<T>[]) new Node[size];
		Iterator<T> it = iterator();
		int index = 0;
		while(it.hasNext()) {
			array[index++] = getNodeOrParent(it.next());
		}
		root = balance(array, 0, size - 1);
		root.parent = null;
	}
	private Node<T> balance(Node<T>[] array, int left, int right) {
		if(left > right) {
			return null;
		}
			int middle = (left + right) / 2;
			Node<T> current = array[middle];
			current.left = balance(array, left, middle - 1);
			if(current.left != null) {
				current.left.parent = current;
			}
			current.right = balance(array, middle + 1, right);
			if(current.right != null) {
				current.right.parent = current;		
			}
		return current;	
	}
	@Override
	public T ceiling(T pattern) {
		// TODO
		T res = null;
		if(pattern != null) {
			Node<T> node = ceiling(root, pattern);	
			if(node != null) {
				res = node.obj;
			}
		}
		return res;
		
		
	}
	private Node<T> ceiling(Node<T> current, T pattern) {
		if(current == null) {
			return null;
		}
		if(comp.compare(current.obj, pattern) < 0) {
			current = ceiling(current.right, pattern);
		}
		return current;
	}
	@Override
	public T floor(T pattern) {
		// TODO
		T res = null;
		if(pattern != null) {
			Node<T> node = floor(root, pattern);	
			if(node != null) {
				res = node.obj;
			}
		}
		return res;
	}
	private Node<T> floor(Node<T> current, T pattern) {
		if(current == null) {
			return null;
		}
		if(comp.compare(current.obj, pattern) > 0) {
			current = floor(current.left, pattern);
		}
		return current;
	}
}
