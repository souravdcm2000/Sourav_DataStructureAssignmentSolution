package utilities;

public class MergeSort {

	static void merge(int arr[], int left, int mid, int right) {
		int len_left = mid - left + 1;
		int len_right = right - mid;
		
		int [] left_arr = new int[len_left];
		int [] right_arr = new int[len_right];
		
		int i, j, k;
		for(i = 0; i < len_left; ++i) {
			left_arr[i] = arr[i + left];
		}
		for(i = 0; i < len_right; ++i) {
			right_arr[i] = arr[i + mid + 1];
		}
		//i -> left array, j -> right array, k -> sorted array
		i = j = 0;
		k = left;
		while (i < len_left && j < len_right) {
			if(left_arr[i] <= right_arr[j]) {
				arr[k] = left_arr[i];
				++i;
			}
			else {
				arr[k] = right_arr[j];
				++j;
			}
			++k;
		}
		
		while (i < len_left) {
			arr[k] = left_arr[i];
			++i;
			++k;
		}
		
		while(j < len_right) {
			arr[k] = right_arr[j];
			++j;
			++k; 
		}
	}
	
	public static void mergeSort(int[] arr, int left, int right) {
		if(left < right) {
			int mid = (left + right)/2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			
			merge(arr, left, mid, right);
		}
	}
}