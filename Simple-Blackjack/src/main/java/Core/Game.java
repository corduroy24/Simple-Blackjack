package Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

	static int deckSize = 52; 

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
	
	private static boolean otherwise; 
	private static boolean playerIsWinner;
	private static boolean dealerIsWinner; 
	
	
	public static void main(String[] arg) {
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
		
		CreateCards(input);
		DefaultDeck(deck);
		deck.ShuffleDeck();
		
		//turn into function = deal cards 
		playerHand.AddCard(deck.DrawCard());
		playerHand.AddCard(deck.DrawCard());
		playerHand.GetCard(0).SetVisibility(true);
		playerHand.GetCard(1).SetVisibility(true);
		
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.AddCard(deck.DrawCard());
		dealerHand.GetCard(0).SetVisibility(true);
		
		//put display hand method in hand class... 
		String printPlayersHand = "";
		String printDealersHand = ""; 
		for(int i = 0; i < playerHand.GetSize(); i++) {
			printPlayersHand += playerHand.GetCard(i).getInput() + " "; 
			//System.out.println(playerHand.GetCard(i).getInput());
		}
		System.out.println("Players Hand: "+ printPlayersHand);
		printPlayersHand = ""; 
		
		for(int i = 0; i < dealerHand.GetSize(); i++) {
			//System.out.println(dealerHand.GetCard(i).getVisibility());
			if(dealerHand.GetCard(i).getVisibility())
				printDealersHand += dealerHand.GetCard(i).getInput() + " "; 
			//System.out.println(playerHand.GetCard(i).getInput());
		}
		System.out.println("Dealers Hand: "+printDealersHand);
		printDealersHand = ""; 

		
		//natural blackJack (only 2 cards)
		/*System.out.println(dealerHand.IsBlackJack() +"&" + playerHand.IsBlackJack() + "dealer has blackjack");
		System.out.println(playerHand.IsBlackJack() + "player has blackjack");

		System.out.println(dealerHand.IsBlackJack() + "dealer has blackjack");*/

		if(playerHand.IsBlackJack() && dealerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			dealerIsWinner = true;
			System.out.println("Dealer is the winner (both had blackjack)"); 
			System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
		}

		else if(playerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			System.out.println("Player is the winner (blackjack)");
			System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

		}

		else if(dealerHand.IsBlackJack()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner(blackjack)");
			System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

		}
		
		while(playerIsWinner == false & dealerIsWinner == false) {
			System.out.println("Hit (H) or Stand (S) ?");
			Scanner console = new Scanner(System.in); 
			char input = console.next().charAt(0);
			
			//keep asking until its the correct input
			//lower case vs upper case 
			
			if(input == 'H') {
				Hit(playerHand);
				for(int i = 0; i < playerHand.GetSize(); i++) {
					printPlayersHand += playerHand.GetCard(i).getInput() + " "; 
				}
				System.out.println("Players Hand: "+ printPlayersHand);
				printPlayersHand = ""; 
				//CheckWinner();
				if(CheckBust() ==true)break;
			}
			else if(input == 'S') {
				HitOrStand(); 
				CheckWinner();
				break; 
			}						
		}
		//CheckWinner();
	}
	
	private static void HitOrStand() {
		// TODO Auto-generated method stub
		String printDealersHand = ""; 

		System.out.println(dealerHand.CountTotal() + " " + dealerHand.isSoft() );
		while((dealerHand.CountTotal() < 17) || (dealerHand.isSoft())) {
			Hit(dealerHand);

			if(CheckBust() == true)break;
			for(int i = 0; i < dealerHand.GetSize(); i++) {
				printDealersHand += dealerHand.GetCard(i).getInput() + " "; 
			}
			System.out.println("Dealers Hand: "+printDealersHand);

		}
		return;
	}


	private static boolean CheckBust() {
		// TODO Auto-generated method stub
		if(playerHand.IsBust()) {
			dealerIsWinner = true; 
			//winner = true; 
			//System.out.println(playerHand.IsBust() +"check player is bust"); 
			System.out.println("Dealer is the winner (player is bust) -- Score: "+dealerHand.CountTotal());
			System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

		}
		else if(dealerHand.IsBust()) {
			playerIsWinner = true; 
			//winner = true; 
			//System.out.println(dealerHand.IsBust()+ "check dealer is bust"); 

			System.out.println("Player is the winner (dealer is bust) -- Score: "+playerHand.CountTotal());
			System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

		}
		return false;
	}

	
	private static void Hit(Hand hand) {
		// TODO Auto-generated method stub
		hand.AddCard(deck.DrawCard());

	}
//code inspired from https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
	public static void FileInput() {
		System.out.println("FileInput");
		//System.out.println("Enter the filename: ");
		//Scanner console = new Scanner(System.in); 
		//String fileName = console.nextLine();
		String line = null; 
		BufferedReader reader = null; 
		String[] parseCommands = new String[deckSize]; 
		String delims = "[ ]+";
		String filename = "File3.txt"; 
	

		try {
			FileReader fileReader =  new FileReader(filename); 
		    BufferedReader bufferedReader = new BufferedReader(fileReader); 
		    
			while((line = bufferedReader.readLine()) != null)
			{
				parseCommands = line.split(delims); 
			    System.out.println(line);

			}
			bufferedReader.close();
		//	console.close();

		}
	        catch(FileNotFoundException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                filename + "'");                
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Error reading file '" 
	                + filename + "'");                  
	        }		
		for (int i = 0; i < parseCommands.length; i++)
		    System.out.println(parseCommands[i]);
		
		/////////////////////////////////
		Deck fileInputDeck = new Deck(); 
		//int size = 1; 
		boolean dealCards1 = true; 
		int temp = 0; 
		String printPlayersHand = "";
		String printDealersHand = ""; 
		
		List<Card> fileInputCards = new ArrayList<Card>(); 
		Card tempCard; 
		for(int i = 0; i < parseCommands.length; i++) {
			if(dealCards1) {
			//if(parseCommands[i].length() > 1) {
				//System.out.println("length: "+parseCommands.length);
				//System.out.println("length 1: "+parseCommands[i].length());
				for(int k = 0; k < 4; k++) {
					fileInputCards.add(new Card(parseCommands[k]));
				}
				for(int k = 0; k < 4; k++) {
					fileInputDeck.AddCard(fileInputCards.get(k));	
				}
				fileInputDeck.reverseDeck();
				playerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
				System.out.println("Player Receives " + tempCard.getName());
				playerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
				System.out.println("Player Receives " + tempCard.getName());

				dealerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
				System.out.println("Dealer Receives " + tempCard.getName());

				dealerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
				System.out.println("Dealer Receives " + tempCard.getName());

				
			
				for(int j = 0; j < playerHand.GetSize(); j++) {	
					printPlayersHand += playerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Players Hand: "+ printPlayersHand);
				printPlayersHand = ""; 
				
				dealerHand.GetCard(0).SetVisibility(true);
				for(int j = 0; j < dealerHand.GetSize(); j++) {
					if(dealerHand.GetCard(j).getVisibility())
						printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Dealers Hand: "+printDealersHand);
				printDealersHand = ""; 
				dealCards1=false;
				
				if(playerHand.IsBlackJack() && dealerHand.IsBlackJack()) {
					playerIsWinner  = true; 
					dealerIsWinner = true;
					System.out.println("Dealer is the winner (both had blackjack)"); 
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
				}

				else if(playerHand.IsBlackJack()) {
					playerIsWinner  = true; 
					System.out.println("Player is the winner (blackjack)");
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
				}

				else if(dealerHand.IsBlackJack()) {
					dealerIsWinner = true; 
					System.out.println("Dealer is the winner(blackjack) -- Score: "+dealerHand.CountTotal());
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
				}
			}
		//	(parseCommands[i].length() == 1)
				else {
					while(playerIsWinner == false & dealerIsWinner == false) {	
						if(parseCommands[i].equals("H")) {
							//System.out.println("Player")
							i++;
							fileInputCards.add(new Card(parseCommands[i]));
	
							//System.out.println("----"+fileInputDeck.SizeOfDeck()+"--"+ fileInputCards.size());
							fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));
	
							playerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
							System.out.println("Player Receives " + tempCard.getName());

							for(int j = 0; j < playerHand.GetSize(); j++) {	
								printPlayersHand += playerHand.GetCard(j).getInput() + " "; 
							}
							System.out.println("Players Hand: "+ printPlayersHand);
							printPlayersHand = ""; 
							if(CheckBust() == true) break;
					
						}
						
						else if(parseCommands[i].equals("S")) {
							System.out.println(dealerHand.CountTotal() + " " + dealerHand.isSoft() );
							while((dealerHand.CountTotal() < 17) || (dealerHand.isSoft())) {
								i++;
								fileInputCards.add(new Card(parseCommands[i]));
							//	System.out.println("check 2");
							//	System.out.println(parseCommands[i] +" "+ parseCommands[i-1]);
								//System.out.println(fileInputCards.get(fileInputCards.size()-1).getInput());
	
								fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));	
	
								dealerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
								System.out.println("Dealer Receives " + tempCard.getName());

								for(int j = 0; j < dealerHand.GetSize(); j++) {
									printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
								}
								System.out.println("Dealers Hand: "+printDealersHand);
								printDealersHand = ""; 
								if(CheckBust() == true) break;								
							}
							CheckWinner(); 

						}
						
						break; 
					}

				}
		}
	}
	
	public static boolean CheckWinner() {
		boolean winner =  false; 
		String printPlayersHand = "";
		String printDealersHand = ""; 
		
		if((playerHand.CountTotal() < 22) & (dealerHand.CountTotal() < 22)) {
			if(playerHand.CountTotal() == dealerHand.CountTotal()) {
				winner = true; 
				playerIsWinner = true;
				for(int j = 0; j < dealerHand.GetSize(); j++) {
						printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Dealers Hand: "+printDealersHand);
				printDealersHand = ""; 
				System.out.println("Dealer wins! (Tied score)");
				System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

			}
			else if(playerHand.CountTotal() > dealerHand.CountTotal()) {
				winner  = true; 
				playerIsWinner = true; 

				for(int j = 0; j < playerHand.GetSize(); j++) {	
					printPlayersHand += playerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Players Hand: "+ printPlayersHand);
				printPlayersHand = ""; 
				
				System.out.println("Player wins! (Higher score) ");
				System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());

			}
			else if(playerHand.CountTotal() < dealerHand.CountTotal()) {
				winner = true; 
				dealerIsWinner = true; 

				
				for(int j = 0; j < dealerHand.GetSize(); j++) {
						printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Dealers Hand: "+printDealersHand);
				printDealersHand = ""; 
				System.out.println("Dealer wins! (Higher score)");
				System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
			}
		}
		return winner; 
	}


	public static void CreateCards (String[] input) {
		for(int i = 0; i < card.length; i++) {
			card[i] = new Card (input[i]);
		}
	}
	
	public static void DefaultDeck(Deck deckToMake) {
		for(int i = 0;i < deckSize; i++) {
			deckToMake.AddCard(card[i]);
		}
	}


}
