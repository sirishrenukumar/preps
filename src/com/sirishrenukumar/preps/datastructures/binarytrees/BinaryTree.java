package com.sirishrenukumar.preps.datastructures.binarytrees;

import java.util.List;

public class BinaryTree {

	static class Node {
		int key;
		Node left;
		Node right;
		Node(int key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return String.format("[%s]", key);
		}
		
	}
	
	static Node insertLeft(Node parent, int key) {
		Node node = new Node(key);
		parent.left = node;
		return node;
	}
	static Node insertRight(Node parent, int key) {
		Node node = new Node(key);
		parent.right = node;
		return node;
	}

	static Node getBinaryTreeRootNode() {
		Node root = new Node(1);
		Node n2 = BinaryTree.insertLeft(root, 2);
		Node n3 = BinaryTree.insertRight(root, 3);
		Node n4 = BinaryTree.insertLeft(n2, 4);
		Node n5 = BinaryTree.insertRight(n2, 5);
		Node n6 = BinaryTree.insertLeft(n3, 6);
		Node n7 = BinaryTree.insertRight(n3, 7);
		return root;
	}
	static void inorder(Node root, List<Integer> keys) {
		if(root == null)
			return;
		else {
			inorder(root.left, keys);
			keys.add(root.key);
			inorder(root.right, keys);
		}
	}
	static void inorder(Node root) {
		if(root == null)
			return;
		else {
			inorder(root.left);
			System.out.println(root);
			inorder(root.right);
		}
	}
}
