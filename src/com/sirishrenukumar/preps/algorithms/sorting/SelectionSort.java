package com.sirishrenukumar.preps.algorithms.sorting;

public class SelectionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int arr[] = new int[]{5,4,3,2,1};
		
		for(int i = 0 ; i < arr.length - 1; ++i) {
			int minIndex = i ;
			for(int j = i + 1; j < arr.length; ++j) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex]= temp;
		}
	}
}
