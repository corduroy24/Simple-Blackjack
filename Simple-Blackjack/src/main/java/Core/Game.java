package Core;

import java.util.Scanner;

public class Game {

	
	static int deckSize = 20; 

	private static Card[] card = new Card[deckSize];
	
	private static Deck deck = new Deck(); 
	
	public static final String [] input = {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
	"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
	"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
	"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
	
	/*public static final String [] input = {"CA", "C10", "CJ", "CQ", "CK",
	"DA","D10", "DJ", "DQ", "DK",
	"HA", "H10", "HJ", "HQ", "HK",
	"SA", "S10", "SJ", "SQ", "SK"};*/
	
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

		//System.out.println("Your hand is: ");
		
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.GetCard(0).SetVisibility(true);
		
		//put display hand method in hand class... 
		for(int i = 0; i < playerHand.GetSize(); i++) {
			System.out.println(playerHand.GetCard(i).getInput());
		}

		
		//natural blackJack (only 2 cards)
		System.out.println(dealerHand.IsBlackJack() +"&" + playerHand.IsBlackJack() + "dealer has blackjack");
		System.out.println(playerHand.IsBlackJack() + "player has blackjack");

		System.out.println(dealerHand.IsBlackJack() + "dealer has blackjack");

		if(playerHand.IsBlackJack() && dealerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			dealerIsWinner = true;
			System.out.println("Both are winners (push)"); 
		}

		else if(playerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			System.out.println("player is the winner(blackjack)");
		}

		else if(dealerHand.IsBlackJack()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner(blackjack)");
		}
		

		
		while(playerIsWinner == false & dealerIsWinner == false) {
			System.out.println("Hit (H) or Stand (S) ?");
			Scanner console = new Scanner(System.in); 
			char input = console.next().charAt(0);
			
			//keep asking until its the correct input
			//lower case vs upper case 
			
			if(input == 'H') {
				Hit(playerHand);
		H		for(int i = 0; i < playerHand.GetSize(); i++) {
					System.out.println(playerHand.GetCard(i).getInput());
				}
				if(CheckBust() ==true)break;

				//if(playerHand.IsBust())break; 
			}
			else if(input == 'S') {
				for(int i = 0; i < dealerHand.GetSize(); i++) {
					System.out.println(dealerHand.GetCard(i).getInput());
				}
				HitOrStand(); 

			}						
			
		}
		CheckWinner();

		

	}
	
	private static void HitOrStand() {
		// TODO Auto-generated method stub
		System.out.println(dealerHand.CountTotal() + " " + dealerHand.isSoft() );
		while((dealerHand.CountTotal() < 17) || (dealerHand.isSoft())) {
			Hit(dealerHand);
			if(CheckBust() == true)break;
			for(int i = 0; i < dealerHand.GetSize(); i++) {
				System.out.println(dealerHand.GetCard(i).getInput());
			}
		}

		 //have to stop the prompt for hit or stand when the game is basically done.. .
			return;
			//Stand(); 	
	}





	private static boolean CheckBust() {
		// TODO Auto-generated method stub
		if(playerHand.IsBust()) {
			dealerIsWinner = true; 
			//winner = true; 
			System.out.println(playerHand.IsBust() +"check player is bust"); 
			System.out.println("Dealer is the winner");
		}
		else if(dealerHand.IsBust()) {
			playerIsWinner = true; 
			//winner = true; 
			System.out.println(dealerHand.IsBust()+ "check dealer is bust"); 

			//System.out.println(playerIsWinner); 

			System.out.println("Player is the winner");
		}
		return false;
	}





	private static void Stand() {
		// TODO Auto-generated method stub
		//Hit(dealerHand); 
		return; 
	}





	private static void Hit(Hand hand) {
		// TODO Auto-generated method stub
		hand.AddCard(deck.DrawCard());

	}





	public static void FileInput() {
		System.out.println("FileInput");
	}
	
	public static boolean CheckWinner() {
		boolean winner =  false; 
	
		if((playerHand.CountTotal() < 22) & (dealerHand.CountTotal() < 22)) {
			if(playerHand.CountTotal() == dealerHand.CountTotal()) {
				winner = true; 
				playerIsWinner = true;
				System.out.println("Push"); 
			}
			else if(playerHand.CountTotal() > dealerHand.CountTotal()) {
				winner  = true; 
				playerIsWinner = true; 
	
				System.out.println("Player wins!"); 
			}
			else if(playerHand.CountTotal() < dealerHand.CountTotal()) {
				winner = true; 
				dealerIsWinner = true; 
				System.out.println("Dealer wins"); 
			}
		}
		return winner; 
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
