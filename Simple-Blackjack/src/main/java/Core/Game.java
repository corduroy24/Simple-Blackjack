package Core;

import java.util.Scanner;

public class Game {

	
	static int deckSize = 52; 

	private static Card[] card = new Card[deckSize];
	
	private static Deck deck = new Deck(); 
	
	public static final String [] input = {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
	"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
	"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
	"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
	
	private static Hand playerHand = new Hand();
	private static Hand dealerHand = new Hand();
	
	
	private static boolean playerIsWinner;
	private static boolean dealerIsWinner; 
	
	
	
	public static void main(String[] arg)	{
		//Deck deck = new Deck();
		//launch(arg);
		PromptUser();
			

	}
	


	

	public static void PromptUser () {
		System.out.println("Which input method are you using? (File = f or Console = c)");
		Scanner console = new Scanner(System.in); 
		char input = console.next().charAt(0);
		
		//keeping asking until its the correct input
		if(input == 'c') 
			ConsoleInput(); 
		else if (input == 'f')
			FileInput();
		console.close();

	
	}
	
	//turn into class 
	public static void ConsoleInput() {
		System.out.println("ConsoleInput");
		
		CreateCards();
		DefaultDeck();
		deck.ShuffleDeck();
		
		//turn into function = deal cards 
		playerHand.AddCard(deck.DrawCard());
		playerHand.AddCard(deck.DrawCard());
		playerHand.GetCard(0).SetVisibility(true);
		playerHand.GetCard(1).SetVisibility(true);

		
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.GetCard(0).SetVisibility(true);
		
		//natural blackJack (only 2 cards)
		if(playerHand.IsBlackJack() == true) {
			playerIsWinner  = true; 
			System.out.println("Dealer is the winner");
		}
		
		if(dealerHand.IsBlackJack() == true) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner");
		}
		
		while(playerIsWinner == false && dealerIsWinner == false) {
			System.out.println("Hit (H) or Stand (S) ?");
			Scanner console = new Scanner(System.in); 
			char input = console.next().charAt(0);
			
			//keep asking until its the correct input
			//lower case vs upper case 
			
			if(input == 'H')
				Hit();
			else if(input == 'S')
				Stand(); 

			CheckWinner();
			
		}
		

	}
	
	private static void Hit() {
		// TODO Auto-generated method stub
		
	}





	public static void FileInput() {
		System.out.println("FileInput");
	}
	
	public static void CheckWinner() {
		
		if(playerHand.IsBust() == true) {
			System.out.println("Dealer is the winner");
		}
		else if(dealerHand.IsBust() == true) {
			System.out.println("Player is the winner");
		}
	}


	public static void CreateCards () {
			
		for(int i = 0; i < card.length; i++) {
			card[i] = new Card (input[i]);
		}
	}
	
	public static void DefaultDeck() {
		for(int i = 0;i < deckSize; i++) {
			deck.AddCard(card[i]);
		}
	}


}
