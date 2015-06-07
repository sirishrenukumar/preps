package com.sirishrenukumar.preps.datastructures.binarytrees;

public class RemoveHalfNodes {

	private static class Node<T> {
		private T key;
		private Node<T> left;
		private Node<T> right;
		Node(T key) {
			this.key = key;
		}
		@Override
		public String toString() {
			return String.format("[%s]", key);
		}
	}
	
	private enum LEFT_OR_RIGHT {
		LEFT,
		RIGHT
	};

	private static class BinaryTree<T> {
		
		Node<T> insert(Node<T> parent, T key, LEFT_OR_RIGHT leftOrRight) {
			Node<T> newNode = new Node<T>(key);
			
			if(leftOrRight == LEFT_OR_RIGHT.LEFT) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}
			
			return newNode;
		}
		
		void inOrder(Node<T> current) {
			if(current == null) 
				return;
			inOrder(current.left);
			System.out.print(current);
			inOrder(current.right);
		}
		
		Node<T> removeHalfNodes(Node<T> current) {
			System.out.println("Visiting node: " + current);
			
			/*
			 * Base case
			 */
			if(current == null)
				return null;
			
			/*
			 * Remove the half nodes from the subtrees. Using post-order traversal technique  
			 */
			current.left = removeHalfNodes(current.left);
			current.right = removeHalfNodes(current.right);
			
			/*
			 * At this point, check if the current node is either a leaf node OR a full node. In that case retain the node
			 */
			if(isLeafNode(current) || isFullNode(current)) 
				return current;
			else {
				/*
				 * The current node is a half node. Hence return the existing child node to the parent and discard the current node
				 */
				if(current.left != null)
					return current.left;
				else 
					return current.right;
			}
		}
		private boolean isLeafNode(Node<T> current) {
			return current.left == null && current.right == null;
		}
		private boolean isFullNode(Node<T> current) {
			return current.left != null && current.right != null;
		}
	}
	
	public static void main(String[] args) {
		
		/*
		 * http://www.geeksforgeeks.org/given-a-binary-tree-how-do-you-remove-all-the-half-nodes/
		 */
		BinaryTree<Integer> bt = new BinaryTree<Integer>();
		Node<Integer> root = new Node<Integer>(2);
		Node<Integer> n7 = bt.insert(root, 7, LEFT_OR_RIGHT.LEFT);
		Node<Integer> n5 = bt.insert(root, 5, LEFT_OR_RIGHT.RIGHT);
		Node<Integer> n6 = bt.insert(n7, 6, LEFT_OR_RIGHT.RIGHT);
		Node<Integer> n1 = bt.insert(n6, 1, LEFT_OR_RIGHT.LEFT);
		Node<Integer> n11 = bt.insert(n6, 11, LEFT_OR_RIGHT.RIGHT);
		Node<Integer> n9 = bt.insert(n5, 9, LEFT_OR_RIGHT.RIGHT);
		Node<Integer> n4 = bt.insert(n9, 4, LEFT_OR_RIGHT.LEFT);

		System.out.println();
		bt.inOrder(root);
		System.out.println();
		
		root = bt.removeHalfNodes(root);
		
		System.out.println();
		bt.inOrder(root);
		System.out.println();

	}

}
