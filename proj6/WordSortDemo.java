package proj6;

/** 
 * Project 6
 * 
 * Use Quicksort or Mergesort (your choice) to sort words from a file
 * by word length, from the longest words to the shortest.
 * You may use the implementation given in the book for Mergesort and/or Quicksort.
 * 
 * Write the output of the program to files Lexicographical.txt and TaleOfTwoCitiesLongToShort.txt.
 * Submit four files for grading:
 * 1. the sorted text file
 * 2. the modified WordSorter Demo program
 * 3. your Quicksort or Mergesort implementation, and
 * 4. the StringLengthComparitor.
 * 
 * Included in this week's content are
 * 1. IOExample.java -- an example program showing how read from and write to files Java 
 * 2. SortExample.java -- an example program demonstrating the use of comparators
 * 3. TaleOfTwoCities.txt -- a text file of Charles Dickens' masterpiece opening to the book
 * 4. WordSortDemo driver file to get you started
 *     
 *     Note: Punctuation marks will be stuck to some of the words. That's fine.
 *     
 *  Grading:
 *  3 pt - Your implementation of Mergesort or Quicksort
 *  2 pt - Your implementation of StringLengthComparitor
 *  1 pt - The text file sorted from longest to shorts word
 *  3 pt - Your modified WordSortDemo program putting it all together
 *  1 pt - Proper use of Javadoc comments and coherent code
 */

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



