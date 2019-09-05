package SlotMachineProject;

import java.text.DecimalFormat;
import java.util.Scanner;


public class SlotMachineDriverProject01 {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		SlotMachine BiddingTheOdds = new SlotMachine(100);
		boolean quit = false;
	
		DecimalFormat money = new DecimalFormat("$0");
		
		System.out.println("Welcome to Bidding the Odds. \n"
				+ "You have $100 to start. \n"
				+ "Enter -1 anytime to cash out");
		
		
		  while (quit == false) { 
			  System.out.println("Bet Amount:"); 
			  int bet = input.nextInt(); 
			  	if (bet == -1) { 
			  		quit = true; 
			  	} else {
			  		BiddingTheOdds.setBet(bet); 
			  		int [] numbers = BiddingTheOdds.generateNums();
			  		System.out.println("+---+---+---+ \n" 
					+ "| " + numbers[0] + " | " + numbers[1] + " | " + numbers[2] + " | \n"
					+ "+---+---+---+"); 
			  		
			  		BiddingTheOdds.checkWinning();
			  		String amountWon = money.format(BiddingTheOdds.getWinning());
			  		System.out.println("Win: " + amountWon);
			  		
			  		BiddingTheOdds.setBalance();
			  		String yourBalance  = money.format(BiddingTheOdds.getBalance());
			  		System.out.println("Balance: " + yourBalance);
			  	}
		  }
		  
		String yourBalance  = money.format(BiddingTheOdds.getBalance());

		System.out.println("Thanks for playing with us! \n"
		 		+ "Your ending balance is " + yourBalance);
		
		for (int i = 0; i < 1000000; i++) {
			 BiddingTheOdds.setBet(1);
			 BiddingTheOdds.generateNums();
			 BiddingTheOdds.checkWinning();
			 BiddingTheOdds.setBalance(); 
		 }
		 
		
		System.out.println("After 1,000,000 more games:");
		
		int [] numbers = BiddingTheOdds.generateNums();
	  	System.out.println("+---+---+---+ \n" 
	  		+ "| " + numbers[0] + " | " + numbers[1] + " | " + numbers[2] + " | \n"
			+ "+---+---+---+");
	  		
	  	BiddingTheOdds.checkWinning();
	  	String amountWon = money.format(BiddingTheOdds.getWinning());
	  	System.out.println("Win: " + amountWon);
	  		
	  	BiddingTheOdds.setBalance();
	  	String yourFinalBalance  = money.format(BiddingTheOdds.getBalance());
	  	System.out.println("Balance: " + yourFinalBalance);
		
		input.close();
	}



}
