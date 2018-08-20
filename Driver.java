import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java. util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
	public static int[] generateRandomData(int n) {
		ArrayList<Integer> sortedData = new ArrayList();
		int shuffledData[] = new int[n];
		
		for(int i = 0; i < n; i++)
			sortedData.add(i + 1);
		
		Collections.shuffle(sortedData);
		
		for(int i = 0; i < n; i++) 
			shuffledData[i] = sortedData.get(i);
		return shuffledData;
	}
	
	public static String checkIfFileExists(String fileName) {
		File f = new File(fileName);
		int i = 0;
		/* if file exists, add index after name */
		while(f.exists() && !f.isDirectory()) {
			i++;
			fileName = fileName.substring(0, fileName.length() - 4) + i + ".txt";
			f = new File(fileName);
		} 
		
		return fileName;
	}
	public static void write(String text, String fileName) {
		FileWriter fw = null;

		try {
			fw = new FileWriter(fileName);
			fw.append(text);
		    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static int[] readData(String fileName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String dataString = br.readLine();
		
		String[] listData = dataString.split(" ");
		int data[] = new int[listData.length];
				
		for(int i = 0; i < listData.length; i++) 
			data[i] = Integer.parseInt(listData[i]);
		
		return data;
	}
	
	public static int[] countingSort(int arr[]) {
		int n = arr.length;
		int k = -1;
		int temp[], sorted[];
		long startTime, stopTime, elapsedTime;
		
		String msg = "\nPerforming Counting Sort... \n";
		System.out.print(msg);
		write(msg, "log.txt");
		
		/* Start Timer */
		startTime = System.nanoTime();
		
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
		for(int i = n - 1; i > 0; i--){
			sorted[temp[arr[i]]] = arr[i];
			temp[arr[i]]--;
		}
		
		/* Stop Timer */
		stopTime = System.nanoTime();
		elapsedTime = (stopTime - startTime) / 1000;
		
		msg = "Finished Counting Sort... \n";
		msg += "Execution Time: " + elapsedTime + " ms \n";
		System.out.print(msg);
		write(msg, "log.txt");

		return sorted;
	}
	
	public static void main(String[] args) {
		int n = 0, selection = -1, data[] = null;
		String dataFileName = "", dataString = "", 
				fileName = "", inputType = "";
		
		
		String msg = "\n\n### STARTED NEW PROCESS ###\n\n";
		write(msg, "log.txt");
		
		Scanner sc = new Scanner(System.in);
		selection = -1;
		
		do {
			System.out.println("Select Data Input: ");
			System.out.println("1 - Manual");
			System.out.println("2 - Randomly Generated");
			System.out.println("3 - Import Text File");
			selection = sc.nextInt();
			
			switch(selection) {
				case 1: 
					inputType = "Manual";
					String input;
					do{
						sc.nextLine();
						input = sc.nextLine();
						try {
							dataString += Integer.parseInt(input) + " ";
						} catch(NumberFormatException e) {
							input = null;
						}
					} while(input != null);
					
					fileName = "mdata.txt";
					fileName = checkIfFileExists(fileName);
					write(dataString, fileName);
				try {
					data = readData(fileName);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					break;
				case 2:
					inputType = "Randomly Generated";
					try {
					System.out.print("Data Size: ");
					n = sc.nextInt();
					data = generateRandomData(n);
					msg = "\nGenerating Data... \n";
					
					fileName = "rdata.txt";
					fileName = checkIfFileExists(fileName);
					for(int i = 0; i < n; i++) 
						dataString += data[i] + " ";
					write(dataString, fileName);
						
					System.out.print(msg);
					write(msg, "log.txt");
					} catch(InputMismatchException e) {
						System.out.println("Invalid Input");
						inputType = "";
						selection = -1;
					}
					break;
				case 3:
					inputType = "Text File";
					System.out.print("File Name: ");
					sc.nextLine();
					fileName = sc.nextLine();
					msg = "\nReading Data... \n";
					System.out.print(msg);
					write(msg, "log.txt");
					try {
						data = readData(fileName);
					} catch (Exception e) {
						System.out.println("File Not Found");
						dataFileName = "";
						inputType = "";
						selection = -1;
					}
					break;
				default:
					System.out.println("Invalid Selection");
					selection = -1;
			}
		} while(selection == - 1);

		msg = "Data Input Type: " + inputType + "\n";
		msg += "Data Size: " + data.length + "\n";
		msg += "Data: ";
		for(int i = 0; i < data.length; i++) 
			msg += data[i] + " ";
		System.out.println(msg);
		write(msg + "\n", "log.txt");
		
		System.out.println("\nSelect Sorting Algorithm: ");
		System.out.println("1 - Insertion Sort");
		System.out.println("2 - Quick Sort");
		System.out.println("3 - Counting Sort");
		
		do {
			selection = sc.nextInt();
			switch(selection) {
				case 1:break;
				case 2:break;
				case 3:
						System.out.print("\n");
						data = countingSort(data);
						System.out.print("\n");
						break;
				default: selection = -1;
			}
		} while(selection == -1);
		
		msg = "Sorted Data: ";
		for(int i = 0; i < data.length; i++) 
			msg += data[i] + " ";
		System.out.print(msg);
		write(msg, "log.txt");
		
		msg = "\n\n### TERMINATED PROCESS ###\n\n";
		write(msg, "log.txt");
		
		sc.close();
	}
}
