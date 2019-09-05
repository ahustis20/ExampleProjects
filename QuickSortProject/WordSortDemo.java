package QuickSortProject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class WordSortDemo {
	
	public static void main(String[] args) {
		
		
		ArrayList<String> wordAL = new ArrayList<>();		//creating the dynamic ArrayList
	
		
		try {
			
			Scanner fileInput = new Scanner(new File("TaleOfTwoCities.txt"));
			while (fileInput.hasNext()){ 
				String word = fileInput.next(); 
				wordAL.add(word);
			}
			fileInput.close();
		} catch (FileNotFoundException exc) {
			System.out.println("There was a problem opening the input file");
		}
		
        
		String[] wordArray = new String[wordAL.size()];			//Converting to an Array
		wordArray = wordAL.toArray(wordArray);
		
		
		Comparator<String> byLexigraphical = Comparator.naturalOrder();		//Creating a byLexigraphical Comparator
		StringLengthComparator byLength = new StringLengthComparator();		//Creating a byStringLength Comparator
	
	
		
		QuickSort.quickSortInPlace(wordArray, byLength, 0, wordArray.length-1);		//Sorting by string length
		
		try {
			PrintWriter outputFile = new PrintWriter(new FileWriter("TaleOfTwoCitiesLongToShort.txt"));

			for (String word : wordArray) { 
				outputFile.println(word);
			}

			outputFile.close();
		} catch (IOException exc) {
			System.out.println("There was a problem opening the output file.");
		}
		
		 
		
		QuickSort.quickSortInPlace(wordArray, byLexigraphical, 0, wordArray.length-1);		//Sorting by natural order
		
		
		try {
			PrintWriter outputFile = new PrintWriter(new FileWriter("Lexicographical.txt"));

			for (String word : wordArray) { 
				outputFile.println(word);
			}

			outputFile.close();
		} catch (IOException exc) {
			System.out.println("There was a problem opening the output file.");
		}
		
		System.out.println("The deed is done.");
		System.out.println("<end>");
	}
}



