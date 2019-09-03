package proj1;

import java.util.Random;

public class SlotMachine {
	
	static Random rnd = new Random();
	
	private int balance;
	private int winnings;
	private int bet;
	int[] rand = new int [3];
	
	
	// constructors
	
	public SlotMachine (int initial) {
		balance = initial;
	}
	
	
	//methods
	
	public void setBet (int betAmount) {
		bet = betAmount;
		balance -= bet;
	}
	
	public int[] generateNums() {     // spin the reels
		rand[0] = rnd.nextInt(7)+1;
		rand[1] = rnd.nextInt(7)+1;
		rand[2] = rnd.nextInt(7)+1;
		return rand;	
	}

	public void checkWinning() {       //evaluate the game
		if (this.rand[0] == this.rand[1] && this.rand[0] == this.rand[2]) {
			winnings = bet * this.rand[0] * 5; 
		} else if (this.rand[0] == this.rand[1]) {
			winnings = bet * this.rand[0];
		} else {
			winnings = 0;
		}
			
	}
			
	public double getWinning() {
		return winnings;
	}
	
	public void setBalance() {
		balance += winnings;
	}
	
	public double getBalance() {
		return balance;
	}
	
	
	}
	
	
