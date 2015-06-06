package com.sirishrenukumar.preps.datastructures.bst;


/**
 * Using generics for the datatype of the 'data' field in each node. Extends comparable so that we can determine the subtree where it has to be inserted in 
 * @author sirishr
 *
 * @param <T>
 */
public class BST<T extends Comparable<T>> {

	private class Node<U> {
		private U data;
		private Node<U> left;
		private Node<U> right;
		Node(U data) {
			this.data = data;
		}
	}

	private Node<T> root;

	public void insert(T data) {
		root = insertNode(root, data);
	}

	/**
	 * Recursive function to insert into the BST. This is based on the compareTo method from the {@link Comparable} interface 
	 * @param currentParent
	 * @param data
	 * @return
	 */
	private Node<T> insertNode(Node<T> currentParent, T data ) {
		
		if(currentParent == null) {
			return new Node<T>(data);
		}

		/*
		 * Either insert into the left subtree (in case the new data is less than the data in the current data) or insert into the right subtree
		 */
		if(data.compareTo(currentParent.data) <=0) {
			currentParent.left = insertNode(currentParent.left, data);
		} else {
			currentParent.right = insertNode(currentParent.right, data);
		}
		
		return currentParent;
	}
	
	/**
	 * Prints the contents in ascending order 
	 */
	public void inOrder() {
		inOrder(root);
		System.out.println();
	}
	/**
	 * Prints the contents in descending order 
	 */
	public void reverseInOrder() {
		reverseInOrder(root);
		System.out.println();
	}
	private void inOrder(Node<T> current) {
		if(current == null) {
			return;
		}
		
		inOrder(current.left);
		System.out.print(current.data);
		inOrder(current.right);
	}
	private void reverseInOrder(Node<T> current) {
		if(current == null) {
			return;
		}
		
		reverseInOrder(current.right);
		System.out.print(current.data);
		reverseInOrder(current.left);
	}

	public static void main(String a[]) {
		
		/*
		 * BST with integers
		 */
		BST<Integer> bstWithInteger = new BST<Integer>();
		for(int i = 10 ; i>0; --i) {
			bstWithInteger.insert(i);
		}
		bstWithInteger.inOrder();
		bstWithInteger.reverseInOrder();

		/*
		 * BST with strings
		 */
		BST<String> bstWithString = new BST<String>();
		bstWithString.insert("e");
		bstWithString.insert("d");
		bstWithString.insert("c");
		bstWithString.insert("b");
		bstWithString.insert("a");

		bstWithString.inOrder();
		bstWithString.reverseInOrder();
	}

}
