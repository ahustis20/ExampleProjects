package proj3;

import java.util.Scanner;

/**
 * 
 * @author Abbey Hustis and Noah Anthony
 *
 */

public class CaesarsCipherDemo {

	public static void main(String[] args) {
		
Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a 5 digit key: ");
		String key = input.next();
		
		char[] key2 = key.toCharArray();			//converts the inputed string into a char array
		int[] finalKey = new int[5];				//creates an empty int array
		
		//changes the chars into ints
		for(int i = 0; i < key2.length; i++) {				 
			finalKey[i] = Integer.parseInt(String.valueOf(key2[i]));
		}
	
		System.out.println("Key is " + finalKey[0] + "-" + finalKey[1] + "-" +finalKey[2]);
		
		//passes the three ints into the cipher as the three keys
		CaesarsCipher cipher = new CaesarsCipher(finalKey[0], finalKey[1], finalKey[2]);
		
		System.out.println("1 for encrypt, 2 for decrypt: ");
		while(!input.hasNextInt()) {
			input.nextLine();
			System.out.println("I need a whole number here.");
		}
		int e = input.nextInt();
		input.nextLine();
		
		if(e == 1) {
			System.out.println("Enter message to encrpyt: ");
			String mess = input.nextLine();
			String coded = cipher.encrypt(mess);
			System.out.println(coded);
			
		} else if (e == 2) {
			System.out.println("Enter message to decrypt: ");
			String mess = input.nextLine();
			String answer = cipher.decrypt(mess);
			System.out.println(answer);
			
		}
		
		
		input.close();
	}	
	

}
