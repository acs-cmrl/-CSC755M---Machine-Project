
public class InsertionSort {
	public static void sort(int arr[]){
		long startTime, stopTime, elapsedTime;
		
		String msg = "\nPerforming Insertion Sort... \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
		
		startTime = System.nanoTime();
		
        int n = arr.length;
        for (int i=1; i<n; i++)
        {
            int key = arr[i];
            int j = i-1;
 
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
        
        /* Stop Timer */
		stopTime = System.nanoTime();
		elapsedTime = (stopTime - startTime) / 1000;
		
		msg = "Finished Insertion Sort... \n";
		msg += "Execution Time: " + elapsedTime + " ms \n";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
	}
}
