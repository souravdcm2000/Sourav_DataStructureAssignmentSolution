package driver;

import java.util.Scanner;

import utilities.AVL_BST;

public class Driver {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		AVL_BST Obj = new AVL_BST();
		
		char status = 'a';
		int s;
		do {
			System.out.println("\nEnter your choice : ");
			System.out.println("0. Exit\n1. Insert into AVL BST\n2. Deletion in AVL_BST\n3. Search an element.\n4. Height of Tree.\n5. Print Tree (Level by Level)\n6. Print Tree (Inorder)\n7. Print Tree (Preorder)\n8. Print Tree (Postorder)\n9. Convert AVL_BST to Right Skewed BST.");
			int choice = in.nextInt();
			
			switch(choice) {
				case 0 :
					status = 's';
					break;
				case 1 :
					Obj.insertNode();
					break;
				case 2 :
					Obj.deleteNode();
					break;
				case 3:
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Search in AVL BST\n2. Search in Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					Obj.searchNode(s);
					break;
				case 4:
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Height of AVL BST\n2. Height of Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					System.out.println("\nHeight of Tree : " + (Obj.heightOfTree(s == 1 ? Obj.getRoot() : Obj.getSkewRoot()) - 1));
					break;
				case 5 :
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Print AVL BST\n2. Print Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					System.out.println("AVL_BST (level by level) : ");
					Obj.leveByLevel(s);
					break;
				case 6 :
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Print AVL BST\n2. Print Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					
					System.out.println("AVL_BST (inorder) : ");
					if(s == 1) {
						Obj.inOrder(Obj.getRoot());
					}
					else{
						Obj.inOrder(Obj.getSkewRoot());
					}	
					break;
				case 7 :
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Print AVL BST\n2. Print Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					System.out.println("AVL_BST (preorder) : ");
					if(s == 1) {
						Obj.preOrder(Obj.getRoot());
					}
					else{
						Obj.preOrder(Obj.getSkewRoot());
					}
					break;
				case 8 :
					System.out.println("\nEnter your choice of tree.");
					System.out.println("\n1. Print AVL BST\n2. Print Right Skewed BST");
					s = in.nextInt();
					
					if(s != 1 && s != 2) {
						System.out.println("\nInvalid Choice!");
						break;
					}
					System.out.println("AVL_BST (postorder) : ");
					if(s == 1) {
						Obj.postOrder(Obj.getRoot());
					}
					else{
						Obj.postOrder(Obj.getSkewRoot());
					}
					break;
				case 9 :
					System.out.println("AVL_BST converted to Right Skewed BST : ");
					Obj.toRightSkewed(Obj.getRoot());
					break;
				default :
					System.out.println("Invalid Input!");
			}
		} while(status != 's');	
				
		System.out.println("Program completed successfully!!!");
		
		in.close();
	}

}