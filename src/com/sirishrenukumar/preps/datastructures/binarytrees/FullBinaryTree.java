package com.sirishrenukumar.preps.datastructures.binarytrees;

public class FullBinaryTree {

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

			if (leftOrRight == LEFT_OR_RIGHT.LEFT) {
				parent.left = newNode;
			} else {
				parent.right = newNode;
			}

			return newNode;
		}

		void inOrder(Node<T> current) {
			if (current == null)
				return;
			inOrder(current.left);
			System.out.print(current);
			inOrder(current.right);
		}

		boolean isFullBinaryTree(Node<T> current) {

			if (current == null)
				return true;
			if (current.left == null && current.right == null)
				return true;
			
			if(current.left != null && current.right != null)
				return isFullBinaryTree(current.left)&& isFullBinaryTree(current.right);
				
			return false;
		}
	}

	public static void main(String[] args) {
		/*
		 * http://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/
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
		
		System.out.println(bt.isFullBinaryTree(root));

	}

}
