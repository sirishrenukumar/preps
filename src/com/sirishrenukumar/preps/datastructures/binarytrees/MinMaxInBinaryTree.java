package com.sirishrenukumar.preps.datastructures.binarytrees;

import com.sirishrenukumar.preps.datastructures.binarytrees.BinaryTree.Node;



public class MinMaxInBinaryTree {
	
	
	private static int findMin(Node root) {

		if (root == null)
			return Integer.MAX_VALUE;

		int minInLeftSubtree = findMin(root.left);
		int minInRightSubtree = findMin(root.right);
		return Math
				.min(root.key, Math.min(minInLeftSubtree, minInRightSubtree));

	}
	private static int findMax(Node root) {

		if (root == null)
			return Integer.MIN_VALUE;

		int maxInLeftSubtree = findMax(root.left);
		int maxInRightSubtree = findMax(root.right);
		return Math
				.max(root.key, Math.max(maxInLeftSubtree, maxInRightSubtree));

	}

	public static void main(String[] args) {
		
		Node root = BinaryTree.getBinaryTreeRootNode();
		
		System.out.println("Min: " + findMin(root));
		System.out.println("Max: " + findMax(root));
		
	}

}
