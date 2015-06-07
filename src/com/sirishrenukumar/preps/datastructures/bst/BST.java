package com.sirishrenukumar.preps.datastructures.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Using generics for the datatype of the 'key' field in each node. Extends
 * comparable so that we can determine the subtree where it has to be inserted
 * in
 * 
 * @author sirishr
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>> {

	private class Node<U> {
		private U key;
		private Node<U> left;
		private Node<U> right;
		Node(U key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return String.format("[%d]", key);
		}
		
		
	}

	private Node<T> root;

	public void insert(T key) {
		root = insertNode(root, key);
	}

	/**
	 * Recursive function to insert into the BST. This is based on the compareTo
	 * method from the {@link Comparable} interface
	 * 
	 * @param currentParent
	 * @param key
	 * @return
	 */
	private Node<T> insertNode(Node<T> currentParent, T key) {

		if (currentParent == null) {
			return new Node<T>(key);
		}

		/*
		 * Either insert into the left subtree (in case the new key is less than
		 * the key in the current key) or insert into the right subtree
		 */
		if (key.compareTo(currentParent.key) <= 0) {
			currentParent.left = insertNode(currentParent.left, key);
		} else {
			currentParent.right = insertNode(currentParent.right, key);
		}

		return currentParent;
	}

	/**
	 * Search for a key in a BST
	 * 
	 * @param key
	 * @return
	 */
	public boolean search(T key) {
		return search(root, key);
		//return searchIterative(key);
	}

	private boolean search(Node<T> current, T key) {

		if (current == null) {
			return false;
		}

		if (current.key == key) {
			return true;
		}

		if (key.compareTo(current.key) <= 0) {
			return search(current.left, key);
		} else {
			return search(current.right, key);
		}
	}
	private boolean searchIterative(T key) {
		
		Node<T> current = root;
		while(current != null) {
			if(current.key == key) {
				return true;
			} 
			
			if(key.compareTo(current.key) <=0 ) {
				current = current.left;
			} else {
				current = current.right;
			}
		}
		return false;
	}

	/**
	 * Prints the contents in ascending order
	 */
	public void inOrder() {
		System.out.println("Inorder traversal of tree: ");
		inOrder(root);
		System.out.println();
	}

	private void inOrder(Node<T> current) {
		if (current == null) {
			return;
		}

		inOrder(current.left);
		System.out.print(String.format("%s ", current.key));
		inOrder(current.right);
	}
	
	private void inOrder(Node<T> current, StringBuilder sb) {
		if (current == null) {
			return;
		}

		inOrder(current.left, sb);
		sb.append(String.format("%s ", current.key));
		inOrder(current.right, sb);
	}

	/**
	 * Prints the contents in descending order
	 */
	public void reverseInOrder() {
		System.out.println("Reverse Inorder traversal of tree: ");
		reverseInOrder(root);
		System.out.println();
	}

	private void reverseInOrder(Node<T> current) {
		if (current == null) {
			return;
		}

		reverseInOrder(current.right);
		System.out.print(String.format("%s ", current.key));
		reverseInOrder(current.left);
	}
	
	public int size() {
		return size(root);
	}
	private int size(Node<T> current) {
		if(current == null) 
			return 0;
		
		return size(current.left) + 1 + size(current.right);
	}
	public int sizeIterativeWithStack() {
		Stack<Node<T>> stack = new Stack<BST<T>.Node<T>>();
		
		if(root!= null)
			stack.push(root);
		
		int size = 0;
		while(!stack.empty()) {
			Node<T> current = stack.pop();

			/*
			 * Push the non-empty left subtree onto the stack 
			 */
			if(current.left!= null)
				stack.push(current.left);

			/*
			 * Count the current node
			 */
			++size;
			
			/*
			 * Push the non-empty right subtree onto the stack 
			 */
			if(current.right!= null)
				stack.push(current.right);
		}
		return size;
	}
	public int sizeIterativeWithQueue() {
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		
		
		if(root!= null)
			queue.add(root);
		
		int size = 0;
		while(!queue.isEmpty()) {
			Node<T> current = queue.remove();

			/*
			 * Push the non-empty left subtree onto the queue 
			 */
			if(current.left!= null)
				queue.add(current.left);

			/*
			 * Count the current node
			 */
			++size;
			
			/*
			 * Push the non-empty right subtree onto the queue 
			 */
			if(current.right!= null)
				queue.add(current.right);
		}
		return size;
	}

	/**
	 * Returns the height of the tree
	 * 
	 * @return
	 */
	public int height() {
		return height(root);
	}

	private int height(Node<T> current) {
		if (current == null) {
			return 0;
		}
		return 1 + Math.max(height(current.left), height(current.right));
	}

	/**
	 * Creates the mirror replica of the tree. This breaks the definition of BST
	 * since the keys less than current key are moved to the right subtree and
	 * those that are greater are moved to the left subtree
	 */
	public void mirror() {
		mirror(root);
	}

	private void mirror(Node<T> current) {
		if (current == null) {
			return;
		}
		mirror(current.left);
		mirror(current.right);
		Node<T> temp = current.left;
		current.left = current.right;
		current.right = temp;
	}

	
	public int countLeaves() {
		return countLeaves(root);
	}
	private int countLeaves(Node<T> current) {
		if(current == null)
			return 0;
		if(current.left == null && current.right == null)
			return 1;
		
		return countLeaves(current.left) + countLeaves(current.right);
	}
	public int countLeavesIterativeWithStack() {
		Stack<Node<T>> stack = new Stack<Node<T>>();
		
		if(root!=null)
			stack.push(root);
		
		int count = 0;
		while(!stack.empty()){
			
			Node<T> current = stack.pop();
			
			if(current.left == null && current.right == null)
				++count;
			
			if(current.left != null)
				stack.push(current.left);
			
			if(current.right != null)
				stack.push(current.right);
		}
		
		return count;
	}
	
	/**
	 * Find the minimum key in the BST
	 * @return
	 */
	public T findMinimum() {
		return findMinimum(root);
	}
	private T findMinimum(Node<T> current) {
		
		/*
		 * Handle empty tree condition
		 */
		if(current == null)
			return null;
		
		/*
		 * Base case
		 */
		if(current.left == null)
			return current.key;
		
		return findMinimum(current.left);
	}
	
	
	/**
	 * Find the maximum key in the BST
	 * @return
	 */
	public T findMaximum() {
		return findMaximum(root);
	}
	private T findMaximum(Node<T> current) {
		
		if(current == null)
			return null;
		
		if(current.right == null)
			return current.key;
		
		return findMaximum(current.right);
	}

	public boolean isBST() {
		return isBST_Incorrect(root);
	}
	
	/**
	 * This method is simple but incorrect since it only ensures that the left child of each node is <= the key of the current node AND the right child is > key
	 * 
	 * However we need to check this for entire subtrees of each node and not just its immediate children 
	 * 
	 * Refer <a>http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/<a>
	 * 
	 * @param current
	 * @return
	 */
	private boolean isBST_Incorrect(Node<T> current) {
		
		/*
		 * NULL case is BST by default
		 */
		if(current == null) 
			return true;
		
		/*
		 * Left child, if present, should NOT be greater than the current key
		 */
		if(current.left != null && current.left.key.compareTo(current.key) > 0 ) {
			return false;
		}
		/*
		 * Right child, if present, should NOT be less than or equal to current key
		 */
		if(current.right != null && current.right.key.compareTo(current.key) <= 0) {
			return false;
		}
		
		if(!isBST_Incorrect(current.left) || !isBST_Incorrect(current.right)) {
			return false;
		}
		
		return true;
	}
	
	private Node<T> predecessor;
	public T inOrderPredecessor(T key) {
		predecessor = null;
		inOrderPredecessor(root, key);
		return predecessor != null ? predecessor.key : null;
	}

	private void inOrderPredecessor(Node<T> current, T key) {

		if (current == null)
			return;

		if (current.key == key)
			predecessor = current.left != null ? rightmost(current.left) : null;
		else if (key.compareTo(current.key) <= 0) {
			predecessor = current;
			inOrderPredecessor(current.left, key);
		} else {
			predecessor = current;
			inOrderPredecessor(current.right, key);
		}
	}
	private Node<T> rightmost(Node<T> current) {
		Node<T> temp = current;
		while(temp.right != null)
			temp = temp.right;
		return temp;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inOrder(root, sb);
		return sb.toString();
	}

	public static void main(String a[]) {

		/*
		 * BST with integers
		 */
		BST<Integer> bstWithInteger = new BST<Integer>();
		for (int i = 5; i <10 ; ++i) {
			bstWithInteger.insert(i);
		}
		for (int i = 0; i <5; ++i) {
			bstWithInteger.insert(i);
		}
		
		
		bstWithInteger.inOrder();
		bstWithInteger.reverseInOrder();
		
		System.out.println();
		System.out.println("Height: " + bstWithInteger.height());
		System.out.println();
		
		System.out.println();
		System.out.println("Size: " + bstWithInteger.size());
		System.out.println();

		System.out.println();
		System.out.println("Size (With Stack): " + bstWithInteger.sizeIterativeWithStack());
		System.out.println();
		System.out.println();
		System.out.println("Size (With Queue): " + bstWithInteger.sizeIterativeWithQueue());
		System.out.println();

		System.out.println();
		System.out.println("Leaves count: " + bstWithInteger.countLeaves());
		System.out.println();
		System.out.println();
		System.out.println("Leaves count (With Stack): " + bstWithInteger.countLeavesIterativeWithStack());
		System.out.println();
		
		System.out.println(String.format("Search result for %d in tree: %s", 100, bstWithInteger.search(100)));
		System.out.println(String.format("Search result for %d in tree: %s", 9 , bstWithInteger.search(9)));
		
		System.out.println();
		System.out.println("Minimum key in tree: " + bstWithInteger.findMinimum());
		System.out.println();

		System.out.println();
		System.out.println("Maximum key in tree: " + bstWithInteger.findMaximum());
		System.out.println();

		
		
		for(int i = 0; i < 11; ++i) {
			System.out.println();
			System.out.println(String.format("Predecessor of %d: %d", i, bstWithInteger.inOrderPredecessor(i)));
			System.out.println();
		}
		
		
		System.out.println();
		System.out.println("Is BST: " + bstWithInteger.isBST());
		System.out.println();
		
		System.out.println();
		System.out.println("Input tree: " + bstWithInteger);
		bstWithInteger.mirror();
		System.out.println("Mirror image of tree: " + bstWithInteger);
		System.out.println();
		
		System.out.println();
		System.out.println("Is BST: " + bstWithInteger.isBST());
		System.out.println();
		
		
		
		
//		/*
//		 * BST with strings
//		 */
//		BST<String> bstWithString = new BST<String>();
//		bstWithString.insert("e");
//		bstWithString.insert("d");
//		bstWithString.insert("c");
//		bstWithString.insert("b");
//		bstWithString.insert("a");
//
//		bstWithString.inOrder();
//		bstWithString.reverseInOrder();
//		System.out.println();
//		System.out.println("Height: " + bstWithString.height());
//		System.out.println();
//
//		System.out.println();
//		System.out.println("Size: " + bstWithString.size());
//		System.out.println();
//
//		System.out.println();
//		System.out.println("Leaves count: " + bstWithString.countLeaves());
//		System.out.println();
		
	}

}
