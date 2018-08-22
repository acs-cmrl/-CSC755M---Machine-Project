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
	
	public static void main(String[] args) {
		int n = 0, selection = -1, data[] = null;
		String dataString = "", fileName = "", inputType = "";
		
		
		String msg = "\n\n### STARTED NEW PROCESS ###\n\n";
		FileHandler.write(msg, "log.txt");
		
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
					sc.nextLine();
					do{
						System.out.print("Input Number (x to escape):");
						input = sc.nextLine();
						try {
							dataString += Integer.parseInt(input) + " ";
						} catch(NumberFormatException e) {
							input = null;
						}
					} while(input != null);
					
					fileName = "mdata(0).txt";
					fileName = FileHandler.checkIfFileExists(fileName);
					FileHandler.write(dataString, fileName);
				try {
					data = FileHandler.readData(fileName);
					n = data.length;
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
					data = FileHandler.generateRandomData(n);
					msg = "Generating Data... \n";
					
					fileName = "rdata(0).txt";
					fileName = FileHandler.checkIfFileExists(fileName);
					for(int i = 0; i < n; i++) 
						dataString += data[i] + " ";
					FileHandler.write(dataString, fileName);
						
					System.out.print(msg);
					FileHandler.write(msg, "log.txt");
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
					msg = "Reading Data... \n";
					System.out.print(msg);
					FileHandler.write(msg, "log.txt");
					try {
						data = FileHandler.readData(fileName);
						n = data.length;
					} catch (Exception e) {
						System.out.println("File Not Found");
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
		msg += "Data Size: " + n + "\n";
		msg += "Data: ";
		for(int i = 0; i < data.length; i++) 
			msg += data[i] + " ";
		System.out.println(msg);
		FileHandler.write(msg + "\n", "log.txt");
		
		System.out.println("\nSelect Sorting Algorithm: ");
		System.out.println("1 - Insertion Sort");
		System.out.println("2 - Quick Sort");
		System.out.println("3 - Counting Sort");
		
		boolean withSteps;
		do {
			selection = sc.nextInt();
			switch(selection) {
				case 1:	
						System.out.print("Show Steps?: (1 - Yes, 0 - No):");
						withSteps = sc.nextInt() == 1;
						System.out.print("\n");
						InsertionSort.performSort(data, withSteps);
						System.out.print("\n");	
						break;
				case 2: 
						System.out.print("Show Steps?: (1 - Yes, 0 - No):");
						withSteps = sc.nextInt() == 1;
						System.out.print("\n");
						QuickSort.performSort(data, withSteps);
						System.out.print("\n");
						break;
				case 3:
						System.out.print("Show Steps?: (1 - Yes, 0 - No):");
						withSteps = sc.nextInt() == 1;
						System.out.print("\n");
						data = CountingSort.performSort(data, withSteps);
						System.out.print("\n");
						break;
				default: selection = -1;
			}
		} while(selection == -1);
		
		msg = "Sorted Data: ";
		for(int i = 0; i < data.length; i++) 
			msg += data[i] + " ";
		System.out.print(msg);
		FileHandler.write(msg, "log.txt");
		
		msg = "\n\n### TERMINATED PROCESS ###\n\n";
		FileHandler.write(msg, "log.txt");
		
		sc.close();
	}
}
