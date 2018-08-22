
public class QuickSort {
	public static void sort(int arr[], int pivot, int right) {
		long startTime, stopTime, elapsedTime;
		
		String msg = "\nPerforming Quick Sort... \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
		
		
		startTime = System.nanoTime();
		/* Start Timer */
		quickSort(arr, pivot, right);
		/* Stop Timer */
		stopTime = System.nanoTime();
		elapsedTime = (stopTime - startTime) / 1000;
		
		msg = "Finished Quick Sort... \n";
		msg += "Execution Time: " + elapsedTime + " ms \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
		
	}
	
	public static void quickSort(int arr[], int pivot, int right) {
		if(pivot < right) {
			int mid = partition(arr, pivot, right);
			quickSort(arr, pivot, mid);
			quickSort(arr, mid + 1, right);
		}
	}
	
	public static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	public static int partition(int arr[], int pivot, int right) {
		int i, j, pKey;
		pKey = arr[pivot];
		i = j = pivot + 1;
	
		while(j <= right) {
			if(arr[j] < pKey) {
				swap(arr, i, j);
				i++;
			}
			j++;
		}
		
		swap(arr, pivot, i - 1);
		return  i - 1;
	}
}
