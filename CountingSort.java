
public class CountingSort {
	public static int[] sort(int arr[]) {
		long startTime, stopTime, elapsedTime;
		
		String msg = "\nPerforming Counting Sort... \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
		
		
		startTime = System.nanoTime();
		/* Start Timer */
		int n = arr.length;
		int k = -1;
		int temp[], sorted[];
		
		for(int i = 0; i < n; i++)
			if(arr[i] > k)
				k = arr[i]; 
		k++;// + 1 is for array size
		
		temp = new int[k];
		for(int i = 0; i < k; i++)
			temp[i] = 0;
		for(int i = 0; i < n; i++) {
			temp[arr[i]] += 1;
		}
		
		temp[0]--;
		for(int i = 1; i < k; i++) 
			temp[i] += temp[i - 1];
		
		sorted = new int[n];
		for(int i = n - 1; i >= 0; i--){
			sorted[temp[arr[i]]] = arr[i];
			temp[arr[i]]--;
		}
		/* Stop Timer */
		stopTime = System.nanoTime();
		elapsedTime = (stopTime - startTime) / 1000;
		
		msg = "Finished Counting Sort... \n";
		msg += "Execution Time: " + elapsedTime + " ms \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");

		return sorted;
	}
	
}
