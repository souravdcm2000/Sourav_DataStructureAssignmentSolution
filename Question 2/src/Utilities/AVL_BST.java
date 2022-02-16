package utilities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AVL_BST {
	static Scanner in = new Scanner(System.in);
	private Node root = null;
	private Node skewRoot = null;
	private Node skewPoint = null;
	
	public Node getRoot() {
		return root;
	}

	public Node getSkewRoot() {
		return skewRoot;
	}

	static class Node {
		int key;
		int height;
		Node left;
		Node right;
	}
	
	Node newNode(int key) {
		Node n = new Node();
		n.key = key;
		n.height = 0;
		n.left = null;
		n.right = null;
		return n;
	}
	
	public void insertNode() {
		System.out.println("Enter value to insert : ");
		int key = in.nextInt();
		in.nextLine();
				
		if(this.root == null) {
			this.root = newNode(key);
			return;
		}
		else {
			root = this.insertRecursive(root, key);
		}
	}
	
	public Node insertRecursive(Node n, int key) {
		if(n == null) {
			return this.newNode(key);
		}
		else {
			if(key < n.key) {
				n.left = this.insertRecursive(n.left, key);
			}
			else if(key > n.key) {
				n.right = this.insertRecursive(n.right, key);
			}
			else {
				System.out.println("\nDuplicate keys not allowed.");
				return n;
			}
			
			n.height = this.heightOfTree(n) - 1;
			int balance_factor = this.heightOfTree(n.left) - this.heightOfTree(n.right);
			
			if(balance_factor > 1 && key < n.left.key) {
				System.out.println("Value inserted successfully!!! and the tree is unbalanced");
				System.out.println("Performing Right Rotation");
				return this.rightRotation(n);
			}
			else if(balance_factor > 1 && key > n.left.key) {
				System.out.println("Value inserted successfully!!! and the tree is unbalanced");
				System.out.println("Performing Left-Right Rotation");
				n.left = this.leftRotation(n.left);
				return this.rightRotation(n);
			}
			else if(balance_factor < -1 && key > n.right.key) {
				System.out.println("Value inserted successfully!!! and the tree is unbalanced");
				System.out.println("Performing Left Rotation");
				return this.leftRotation(n);
			}
			else if(balance_factor < -1 && key < n.right.key){
				System.out.println("Value inserted successfully!!! and the tree is unbalanced");
				System.out.println("Performing Right-Left Rotation");
				n.right = this.rightRotation(n.right);
				return this.leftRotation(n);
			}
			return n;
		}
	}
	
	public void deleteNode() {
		if(this.root == null) {
			System.out.println("Binary Tree is empty!");
			return;
		}
		
		System.out.print("Enter value to delete : ");
		int key = in.nextInt();
		
		this.deleteRecursive(this.root, key);
	}
	
	public Node deleteRecursive(Node n, int key) {
		if(n == null)
		{
			return n;
		}
		
		if(key < n.key) {
			n.left = this.deleteRecursive(n.left, key);
		}
		else if(key > n.key) {
			n.right = this.deleteRecursive(n.right, key);
		}
		else {
			if(n.left == null) {
				return n.right;
			}
			else if(n.right == null) {
				return n.left;
			}
			else {
				n.key = this.findMinNode(n.right);
				n.right = this.deleteRecursive(n.right, n.key);
			}
		}
		
		n.height = this.heightOfTree(n) - 1;
		int balance_factor_1 = this.heightOfTree(n.left) - this.heightOfTree(n.right);
		int balance_factor_2 = balance_factor_1 > 0 ? (this.heightOfTree(n.left.left) - this.heightOfTree(n.left.right)):(this.heightOfTree(n.right.left) - this.heightOfTree(n.right.right));
		
		if(balance_factor_1 > 1 && balance_factor_2 > 1) {
			System.out.println("Performing Right Rotation");
			return this.rightRotation(n);
		}
		else if(balance_factor_1 > 1 && balance_factor_2 < -1) {
			System.out.println("Performing Left-Right Rotation");
			n.left = this.leftRotation(n.left);
			return this.rightRotation(n);
		}
		else if(balance_factor_1 < -1 && balance_factor_2 < -1) {
			System.out.println("Performing Left Rotation");
			return this.leftRotation(n);
		}
		else if(balance_factor_1 < -1 && balance_factor_2 > 1){
			System.out.println("Performing Right-Left Rotation");
			n.right = this.rightRotation(n.right);
			return this.leftRotation(n);
		}
		
		return n;
		
	}
	
	public int findMinNode(Node n) {
		int key = n.key;
		while(n.left != null) {
			key = n.left.key;
			n = n.left;
		}
		return key;
	}
	
	public Node rightRotation(Node n) {
		Node x = n.left;
		Node shiftNode = x.right;
		x.right = n;
		n.left = shiftNode;
		x.height = this.heightOfTree(x) - 1;
		n.height = this.heightOfTree(n) - 1;
		return x;
	}
	
	public Node leftRotation(Node n) {
		Node x = n.right;
		Node shiftNode = x.left;
		x.left = n;
		n.right = shiftNode;
		x.height = this.heightOfTree(x) - 1;
		n.height = this.heightOfTree(n) - 1;
		return x;
	}
	
	public int heightOfTree(Node n) {
		if(n == null) {
			return 0;
		}
		else {
			int right_height = this.heightOfTree(n.right);
			int left_height = this.heightOfTree(n.left);
			
			return (right_height > left_height ? (right_height + 1) : (left_height + 1));
		}
	}
	
	public void searchNode(int s) {
		System.out.println("Enter value to search for : ");
		int key = in.nextInt();
		in.nextLine();
		
		
		Node n = s == 1 ? this.root :  this.skewRoot;
		boolean found = false;
		while(n != null) {
			if(key < n.key) {
				n = n.left;
			}
			else if(key > n.key) {
				n = n.right;
			}
			else if(n.key == key) {
				found = true;
				break;
			}
			else {
				continue;
			}
		}
		
		if(found == false) {
			System.out.println("\nElement not found!");
		}
		else {
			System.out.println("\nElement present...");
		}
	}
	
	public void inOrder(Node n) {
		if(n == null) {
			return;
		}
		else {
			this.inOrder(n.left);
			System.out.print(n.key + " ");
			this.inOrder(n.right);
		}
	}
	
	public void preOrder(Node n) {
		if(n == null) {
			return;
		}
		else {
			System.out.print(n.key + " ");
			this.preOrder(n.left);
			this.preOrder(n.right);
		}
	}
	
	public void postOrder(Node n) {
		if(n == null) {
			return;
		}
		else {
			this.postOrder(n.left);
			this.postOrder(n.right);
			System.out.print(n.key + " ");
		}
	}
	
	public void leveByLevel(int s) {
		Node n = s == 1 ? this.root :  this.skewRoot;
		
		if(n == null) {
			System.out.println("Binary Tree is empty!");
			return;
		}
		
		Queue<Node> q = new LinkedList<Node>();
		q.add(n);
		
		while(!q.isEmpty()) {
			Node tempNode = q.remove();
			System.out.print(tempNode.key + " ");
			
			if(tempNode.left != null) {
				q.add(tempNode.left);
			}
			if(tempNode.right != null) {
				q.add(tempNode.right);
			}
		}
	}
	
	public void toRightSkewed(Node n) {
		if(n == null) {
			return;
		}
		else {
			this.toRightSkewed(n.left);
			if(this.skewRoot == null) {
				this.skewRoot = this.newNode(n.key);
				this.skewPoint = this.skewRoot;
			}
			else {
				Node skew = this.newNode(n.key);
				this.skewPoint.right = skew;
				this.skewPoint = skew;
			}
			this.toRightSkewed(n.right);
		}
	}
}