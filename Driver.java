import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java. util.Arrays;
import java.util.Collections;

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
	
	public static void write(String text, String fileName) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName,true);
			fw.write(text);
		    fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static int[] readData(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String dataString = br.readLine();
			
			ArrayList<Integer> listData = new ArrayList();
			
			while(!dataString.isEmpty()) {
				int i = 0;
				while(dataString.charAt(i) != ' ') i++;
				String num = dataString.substring(0, i);
				listData.add(Integer.parseInt(num));
				dataString = dataString.substring(i + 1, dataString.length());
			}
			int data[] = new int[listData.size()];
			
			for(int i = 0; i < listData.size(); i++) 
				data[i] = listData.get(i);
			
			return data;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		int n = 100;
		int data[] = generateRandomData(n);
		
		for(int i = 0; i < n; i++) {
			String dataString = data[i] + " ";
			write(dataString, "data.txt");
		}
		
		data = readData("data.txt");
		
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}
		
	}
}
