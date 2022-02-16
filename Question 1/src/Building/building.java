package building;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import utilities.MergeSort;

public class Building {
	private int floors;
	private int [] floorSizes;
	Scanner in = new Scanner(System.in);

	public int getFloors() {
		return this.floors;
	}
	
	private void setFloors() {
		System.out.println("\nEnter the total number of floors in the building :");
		this.floors = in.nextInt();
	}
	
	public int[] getFloorSizes() {
		return this.floorSizes;
	}
	
	private void setFloorSizes() {
		this.floorSizes = new int[this.floors];
	}

	public Building() {
		this.setFloors();
		this.setFloorSizes();
		this.FloorSizes();
	}
	
	public void FloorSizes() {
		for(int i = 0; i < this.floors; ++i) {
			System.out.println("\nEnter the floor size given on day : " + (i+1));
			this.floorSizes[i] = in.nextInt();
		}
	}

	private int completeFloors(int floor_size, List<Integer> available, Stack<Integer> floor_size_sorted) {		
		System.out.print(floor_size + " ");
		
		while(!available.isEmpty()) {
			if(available.contains(floor_size_sorted.peek())) {
				available.remove(floor_size_sorted.peek());
				System.out.print(floor_size_sorted.pop() + " ");
			}
			else {
				break;
			}
		}
		
		return floor_size_sorted.isEmpty() ? -1 : floor_size_sorted.pop();
	}
	
	public void orderOfConstruction() {
		int i, ele = -1;
		
		int [] size_sorted = new int[this.getFloorSizes().length];
		for(i = 0; i < this.getFloorSizes().length; ++i) {
			size_sorted[i] = this.getFloorSizes()[i];
		}
		MergeSort.mergeSort(size_sorted, 0, size_sorted.length - 1);

		Stack<Integer> floor_size_sorted = new Stack<Integer>();
		for(i = 0; i < size_sorted.length; ++i) {
			floor_size_sorted.push(size_sorted[i]);
		}
		
		List<Integer> available = new ArrayList<Integer>();
		ele = floor_size_sorted.pop();
		
		System.out.println("\nThe order of construction is as follows : ");
		for(int days = 0; days < this.getFloors(); ++days) {
			System.out.print("\nDay " + (days + 1) + " : ");
						
			if(this.getFloorSizes()[days] == ele) {
				ele = this.completeFloors(this.getFloorSizes()[days], available, floor_size_sorted);
			}
			else {
				available.add(this.getFloorSizes()[days]);
			}
		}
	}
}