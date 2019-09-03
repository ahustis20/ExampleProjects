package proj6;

/**
 * Take a look at this example and experiment to see how to use comparators for sorting.
 * The StringLengthComparator (p 364) compares by length, not numerical or lexicographical order.
 */

import java.util.Arrays;
import java.util.Comparator;

public class SortExample {

	public static void main(String[] args) {
		
		Comparator<Double> byValue = Comparator.naturalOrder();
		Comparator<Double> byReverse = Comparator.reverseOrder();

		Double[] myDoubles = { 34.99, 12.1, 5.0, 769.0001, 3.00303303, 100.0, 22.2, 77.1 };
		System.out.println(Arrays.toString(myDoubles));
		Arrays.sort(myDoubles, byReverse);
		System.out.println(Arrays.toString(myDoubles));
		Arrays.sort(myDoubles, byValue);
		System.out.println(Arrays.toString(myDoubles));
		System.out.println();
		
		// Convert array of Doubles to array of Strings
		// (We have to do this first because StringLengthComparator works on Strings, not Doubles)
		String[] myStrings = new String[myDoubles.length];
		for (int i = 0; i < myDoubles.length; i++) {
			myStrings[i] = myDoubles[i].toString();
		}

		// To use this code, create the StringLengthComparator from the textbook
		
		// We can define a custom comparator to sort things in custom ways
		// The StringLengthComparator is from Chapter 9 in textbook, p 364
		StringLengthComparator byLength = new StringLengthComparator();

		// Sort strings by length using StringLengthComparator
		Arrays.sort(myStrings, byLength);
		System.out.println(Arrays.toString(myStrings));
		System.out.println();


	}
}
