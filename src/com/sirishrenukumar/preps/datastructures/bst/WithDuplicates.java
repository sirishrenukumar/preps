package com.sirishrenukumar.preps.datastructures.bst;

public class WithDuplicates<T extends Comparable<T>>{
	
	/**
	 * Class that has additional key count field. This ensures that we do not create duplicate nodes. Instead we merely increment the key count withing each node
	 * @author sirishr
	 *
	 * @param <U>
	 */
	private class Node<U> {
		private T key;
		private int keyCount;
		private Node<U> left;
		private Node<U> right;
		
		Node(T key) {
			this.key = key;
			incrementKeyCount();
		}
		void incrementKeyCount() {
			++keyCount;
		}
		void decrementKeyCount() {
			--keyCount;
		}
	}
	
	private Node<T> root;
	public void insert(T key) {
		root = insert(root, key);
	}
	private Node<T> insert(Node<T> current, T key) {
		if(current == null)
			return new Node<T>(key);
		
		int result = key.compareTo(current.key);
		
		/*
		 * In case the key is already found, then it is duplicate. Merely increment the key count
		 */
		if(result == 0) {
			current.incrementKeyCount();
		} else if (result < 0) {
			current.left = insert(current.left, key);
		} else {
			current.right = insert(current.right, key);
		}
		return current;
	}
	private void inorder(Node<T> current, StringBuilder sb) {
		if(current == null)
			return;
		inorder(current.left, sb);
		sb.append(String.format("[%s:%s]", current.key, current.keyCount));
		inorder(current.right, sb);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inorder(root, sb);
		return sb.toString();
	}
	public static void main(String[] args) {
		
		WithDuplicates<Integer> bst = new WithDuplicates<Integer>();
		
		bst.insert(5);
		bst.insert(4);
		bst.insert(6);
		bst.insert(3);
		bst.insert(7);
		bst.insert(5);
		bst.insert(4);
		bst.insert(6);
		
		System.out.println(bst);

	}

}
