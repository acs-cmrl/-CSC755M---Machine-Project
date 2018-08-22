import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FileHandler {
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
			fileName = (fileName.split("\\("))[0] + "(" + i + ").txt";
			f = new File(fileName);
		} 
		
		return fileName;
	}
	public static void write(String text, String fileName) {
		FileWriter fw = null;

		try {
			fw = new FileWriter(fileName, true);
			fw.write(text);
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
}
