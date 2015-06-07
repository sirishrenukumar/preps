package com.sirishrenukumar.preps.datastructures.bst;

public class KthLargestKey {
	
	private static class BST {
		private class Node {
			int key;
			Node left;
			Node right;
			
			Node(int key){
				this.key = key;
			}
		}
		private Node root;
		
		void insert(int key){
			root = insert(root,key);
		}
		private Node insert(Node current, int key){
			if(current == null)
				return new Node(key);
			
			if(key < current.key) 
				current.left = insert(current.left, key);
			else 
				current.right = insert(current.right, key);
			
			return current;
		}
		
		private int visitedCount;
		void kThLargestKey(int k){
			visitedCount = 0;
			kThLargestKey(root, k);
		}
		
		/**
		 * Leverage reverse inorder traversal and count the number of visited nodes 
		 * @param current
		 * @param k
		 */
		private void kThLargestKey(Node current, int k){
			
			if(current == null)
				return;
			
			kThLargestKey(current.right, k);
			++visitedCount;
			if(visitedCount == k) {
				System.out.println("K: " + k + " Key: " + current.key);
				return;
			}
			kThLargestKey(current.left, k);
		}
	}
	
	

	public static void main(String[] args) {
		
		BST bst = new BST();
		for(int i = 0; i < 10; ++i)
			bst.insert(i);
		
		for(int i = 1; i <= 10; ++i) {
			bst.kThLargestKey(i);	
		}
	}

}
