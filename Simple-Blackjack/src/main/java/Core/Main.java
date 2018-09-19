package Core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	//static int game.deckSize = 52; 
	static int deckSize = 27; 


	//private static Card[] card = new Card[game.deckSize];
	
	//private static game.deck game.deck = new game.deck(); 
	
	/*public static final String [] input = {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
	"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
	"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
	"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};*/
	
	/*public static final String [] input = {"CA", "C10", "CJ", "CQ", "CK",
	"DA","D10", "DJ", "DQ", "DK",
	"HA", "H10", "HJ", "HQ", "HK",
	"SA", "S10", "SJ", "SQ", "SK"};*/
	public static final String [] input = {"C3", "C3", "C3", "C4", "C4",
	"C3","C3", "C3", "C3", "C3", "C3", "C4", "C4",
	"C3","C3", "C3", "C3", "C3", "C3", "C3", "C3",
	"C3","C3", "C3", "C3", "C3", "C3"}; 
	
	
	private static GameMaster game = new GameMaster(); 
	
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
		else {
			System.out.println("Please enter a valid input method!");
		
			while(input!='c' | input !='f') {
				System.out.println("Which input method are you using? (File = f or Console = c)");
				console = new Scanner(System.in); 
				input = console.next().charAt(0);
				if(input == 'c') {
					ConsoleInput();
					break;
				}
				else if (input == 'f') {
					FileInput();
					break;
				}
				else
					System.out.println("Please enter a valid input method!");
			}
		}
			
		console.close();
	}
	
	public static void ConsoleInput() {
		System.out.println("ConsoleInput");
		
		game.deck.createDefaultDeck(input);
		game.deck.shuffleDeck();
		
		Card tempCard;
		game.dealCards(game.deck); 


		game.checkBlackjack(game.player.getHand(), game.dealer.getHand());

			
		if(game.player.getHand().isSplit()) {
			//char empty = ' '; 
			System.out.println("Do you wanna split (D)? Press any other letter to skip");
			Scanner console = new Scanner(System.in); 
			char inputD = console.next().charAt(0);
			if(inputD == 'D') {
				game.playerSplit();
			}
		}
			
		game.dealerSplit(); 

		game.player.getHand().turn = true; 
		char input; 
		while(game.playerIsWinner == false & game.dealerIsWinner == false) {
			System.out.println("Hit (H) or Stand (S) ?");
			Scanner console = new Scanner(System.in); 
			input = console.next().charAt(0);
			game.bettingSequence(input); 
		}

	}
	

	
	
	public static void FileInput() {
		
		String[] parseCommands = new String[deckSize]; 
		parseCommands  = readFile(); 
		System.out.println("FileInput");
		
		Deck fileInputDeck = new Deck(); 
		boolean dealCards1 = true; 

		List<Card> fileInputCards = new ArrayList<Card>(); 
		Card tempCard; 
		game.player.getHand().turn = true; 
		
		for(int i = 0; i < parseCommands.length; i++) {
			if(dealCards1) {

				for(int k = 0; k < 4; k++) {
					fileInputCards.add(new Card(parseCommands[k]));
				}
				for(int k = 0; k < 4; k++) {
					fileInputDeck.addCard(fileInputCards.get(k));	
				}
				fileInputDeck.reverseDeck();
				game.dealCards(fileInputDeck);

				dealCards1=false;
				
				game.checkBlackjack(game.player.getHand(), game.dealer.getHand()); 
				
			}
		

				else {
					i = game.bettingSequence(fileInputDeck, parseCommands, i); 
/*					if(parseCommands[i].equals("D")) {
						System.out.println("Command D");

						i = game.playerSplit(fileInputDeck, parseCommands, i);
				}
					
					while(game.playerIsWinner == false & game.dealerIsWinner == false) {	
						if(parseCommands[i].equals("H") && game.player.getHand().turn) {
							i++;
							
							fileInputCards.add(new Card(parseCommands[i]));
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
							tempCard = game.player.hit(game.player.getHand(), fileInputDeck); 
							System.out.println("game.player Receives " + tempCard.getName());
	
							if(game.checkBust() == true) break;
						}
						else if(parseCommands[i].equals("H") && game.player.getSplitHand().turn) {
							if(game.splitting) {
							i++;
							
							fileInputCards.add(new Card(parseCommands[i]));
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
							tempCard = game.player.hit(game.player.getSplitHand(), fileInputDeck); 
							System.out.println("game.player Split Receives " + tempCard.getName());

							if(game.checkBust() == true) break;
							}
						}
						

						else if(parseCommands[i].equals("S") && game.player.getHand().turn == true) {
								game.player.getHand().showHand();
								game.player.getHand().turn= false; 
								game.player.getSplitHand().turn = true; 	
							if(game.splitting == false)
								game.player.getSplitHand().turn = false ;
								
							if((parseCommands[i].equals("S") && game.player.getSplitHand().turn == false)) {
								game.player.getSplitHand().turn = false;

								game.dealer.getHand().turn = true;
								game.dealerSplit();

								i = game.dealer.hitOrStand(game.dealer.getHand(), fileInputDeck, parseCommands, i);
								game.checkBust();

								if(game.dealerSplitting) {

									game.dealer.getHand().turn = false;
									game.dealer.getSplitHand().turn = true; 
									i = game.dealer.hitOrStand(game.dealer.getSplitHand(), fileInputDeck, parseCommands, i);
									game.checkBust(); 

								}
								game.checkWinner();

								break;
							}
						}
							
		
						else if(parseCommands[i].equals("S") && game.player.getSplitHand().turn == true) {
							game.player.getSplitHand().turn = false; 
							game.player.getSplitHand().showHand();
							game.dealer.getHand().turn = true; 							
							System.out.println(game.dealer.getHand().countTotal() + " " + game.dealer.getHand().isSoft() );
							i = game.dealer.hitOrStand(game.dealer.getHand(), fileInputDeck, parseCommands, i);
							game.checkBust();

							game.checkWinner(); 
						}
						
						break; 
					}*/
				}
		}
	}
	//code inspired from:
		//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
	private static String[] readFile() {
	// TODO Auto-generated method stub
		//System.out.println("Enter the filename: ");
				//Scanner console = new Scanner(System.in); 
				//String fileName = console.nextLine();
		String line = null; 
		BufferedReader reader = null; 
		String[] parseCommands = new String[deckSize]; 
		String delims = "[ ]+";
		String filename = "File5.txt"; 
			

		try {
			FileReader fileReader =  new FileReader(filename); 
		    BufferedReader bufferedReader = new BufferedReader(fileReader); 
				    
			while((line = bufferedReader.readLine()) != null){
				parseCommands = line.split(delims); 
			    System.out.println(line);

			}
			bufferedReader.close();
		//	console.close();

		}
		catch(FileNotFoundException ex) {
			System.out.println(  "Unable to open file '" +   filename + "'"); 
		}
		catch(IOException ex) {
			System.out.println( "Error reading file '"  + filename + "'");                  
		}		
		for (int i = 0; i < parseCommands.length; i++)
			System.out.println(parseCommands[i]);
				
		return parseCommands;
	}

}
