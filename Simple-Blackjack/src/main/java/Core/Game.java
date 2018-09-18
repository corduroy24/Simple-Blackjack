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

	//static int deckSize = 52; 
	static int deckSize = 27; 


	private static Card[] card = new Card[deckSize];
	
	private static Deck deck = new Deck(); 
	
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
	
	private static Hand playerHand = new Hand();
	private static Hand dealerHand = new Hand();
	private static Hand splitPlayer = new Hand(); 
	private static Hand splitDealer = new Hand(); 
	
	private static boolean splitting=false; 
	private static boolean dealerSplitting=false; 

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
		
		Card tempCard;
		
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
		String printSplitPlayerHand = ""; 
		String printSplitDealerHand = ""; 

		for(int i = 0; i < playerHand.GetSize(); i++) {
			printPlayersHand += playerHand.GetCard(i).getInput() + " "; 
			//System.out.println(playerHand.GetCard(i).getInput());
		}
		System.out.println("Players Hand: "+ printPlayersHand);
		printPlayersHand = ""; 
		
		for(int i = 0; i < dealerHand.GetSize(); i++) {
			//System.out.println(dealerHand.GetCard(i).getVisibility());
			//if(dealerHand.GetCard(i).getVisibility())
				printDealersHand += dealerHand.GetCard(i).getInput() + " "; 
			//System.out.println(playerHand.GetCard(i).getInput());
		}
		System.out.println("Dealers Hand: "+printDealersHand);
		printDealersHand = ""; 

		
	//	else {
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
			
			if(playerHand.isSplit()) {
				
				System.out.println("Do you wanna split (D)?");
				Scanner console = new Scanner(System.in); 
				char inputD = console.next().charAt(0);
				if(inputD == 'D') {
					splitting = true; 
					//splitPlayer = new Hand(); 
					System.out.println("isSplit == true");
					splitPlayer.AddCard(playerHand.split());
					
					playerHand.AddCard(tempCard = deck.DrawCard());
					System.out.println("Player Receives " + tempCard.getName());
						
					splitPlayer.AddCard(tempCard = deck.DrawCard()); 
					System.out.println("Player Split Receives " + tempCard.getName());
					
					for(int j = 0; j < playerHand.GetSize(); j++) {	
						printPlayersHand += playerHand.GetCard(j).getInput() + " "; 
					}
					System.out.println("Players Hand: "+ printPlayersHand);
					printPlayersHand = ""; 
					
					for(int j = 0; j < splitPlayer.GetSize(); j++) {	
						printSplitPlayerHand += splitPlayer.GetCard(j).getInput() + " "; 
					}
					System.out.println("Players Split Hand: "+ printSplitPlayerHand);
					printSplitPlayerHand = ""; 
				}
			}
			
			if(dealerHand.isSplit() && (dealerHand.CountTotal() <= 17)) {
				
					dealerSplitting = true; 
					//splitDealer = new Hand(); 
					System.out.println("isSplitDealer == true");
					splitDealer.AddCard(dealerHand.split());
					
					dealerHand.AddCard(tempCard = deck.DrawCard());
					System.out.println("Dealer Receives " + tempCard.getName());
					
			
					
					splitDealer.AddCard(tempCard = deck.DrawCard()); 
					System.out.println("Dealer Split Receives " + tempCard.getName());
					
					for(int j = 0; j < dealerHand.GetSize(); j++) {	
						printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
					}
					System.out.println("Dealer Hand: "+ printDealersHand);
					printDealersHand = ""; 
					
					for(int j = 0; j < splitDealer.GetSize(); j++) {	
						printSplitDealerHand += splitDealer.GetCard(j).getInput() + " "; 
					}
					System.out.println("Dealer Split Hand: "+ printSplitDealerHand);
					printSplitDealerHand = ""; 
			}

			playerHand.turn = true; 

			while(playerIsWinner == false & dealerIsWinner == false) {
				System.out.println("Hit (H) or Stand (S) ?");
				Scanner console = new Scanner(System.in); 
				char input = console.next().charAt(0);
				
				//keep asking until its the correct input
				//lower case vs upper case 
				//turn[0] = true; 
				if(input == 'H' && playerHand.turn == true) {
					Hit(playerHand);
					for(int i = 0; i < playerHand.GetSize(); i++) {
						printPlayersHand += playerHand.GetCard(i).getInput() + " "; 
					}
					System.out.println("Players Hand: "+ printPlayersHand);
					printPlayersHand = ""; 
					//CheckWinner();
					if(CheckBust() ==true)break;
				}
				else if(input == 'H' && splitPlayer.turn == true) {
					if(splitting) {				
						System.out.println("splitting");

						Hit(splitPlayer);
						for(int i = 0; i < splitPlayer.GetSize(); i++) {
							printSplitPlayerHand += splitPlayer.GetCard(i).getInput() + " "; 
						}
						System.out.println("Split Players Hand: "+ printSplitPlayerHand);
						printSplitPlayerHand = ""; 
						//CheckWinner();
						if(CheckBust() ==true)break;
						//splitting = false;
					//	turn[1] = false;
					}
				}
				else if(input == 'S' && playerHand.turn == true) {
					System.out.println("check 1 ");
					playerHand.turn= false; 
					splitPlayer.turn= true; 
					if(splitting  == false)
						splitPlayer.turn = false; 
					
					if((input == 'S' && splitPlayer.turn == false)) {
						System.out.println("check 2 ");
						splitPlayer.turn = false; 
						playerHand.turn= false; 
						dealerHand.turn = true;
						//turn[3] = true; 
						HitOrStand(dealerHand); 
						if(dealerSplitting) {
							System.out.println("check 3");

							dealerHand.turn = false;
							splitDealer.turn = true; 
							HitOrStand(splitDealer); 
						}
						CheckWinner();
						break; 
				}
				}
				
				else if(input == 'S' && splitPlayer.turn ==true) {
					System.out.println("check 4 ");
					splitPlayer.turn = false; 
					playerHand.turn= false; 
					dealerHand.turn = true;
					HitOrStand(dealerHand); 
					if(dealerSplitting) {
						dealerHand.turn = false;
						splitDealer.turn = true; 
						HitOrStand(splitDealer); 
					}
					CheckWinner();
					break; 
				}
			}
		//}
		//CheckWinner();
	}
	
	/*private static void splitSequence() {
		boolean[] turn = new boolean[4];
		System.out.println("Hit (H) or Stand (S) ?");
		Scanner console = new Scanner(System.in); 
		char input = console.next().charAt(0);
		
		while(turn[0] == false) {
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
		}
		else if(input == 'S') {
			HitOrStand(); 
			CheckWinner();
			break; 
		}	
	}*/
	//make turns part of hand class
	private static void HitOrStand(Hand hand) {
		// TODO Auto-generated method stub
		String printDealersHand = ""; 
		String printSplitDealerHand = "";

	//	System.out.println(hand.CountTotal() + " " + hand.isSoft() );
		while((hand.CountTotal() < 17) || (hand.isSoft())) {
			Hit(hand);

			if(CheckBust() == true)break;
			
			if(dealerHand.turn) {
				for(int i = 0; i < hand.GetSize(); i++) {
					printDealersHand += hand.GetCard(i).getInput() + " "; 
				}
				System.out.println("Dealers Hand---: "+printDealersHand);
				printDealersHand = ""; 
			}
			
			else if(dealerSplitting && splitDealer.turn == true) {
				for(int i = 0; i < hand.GetSize(); i++) {
					printSplitDealerHand += hand.GetCard(i).getInput() + " "; 
				}
				System.out.println("Dealers Split Hand---: "+printSplitDealerHand);
				printSplitDealerHand = ""; 
			}
		}
		return;
	}


	private static boolean CheckBust() {
		// TODO Auto-generated method stub
		if(playerHand.IsBust()) {
			dealerIsWinner = true; 
			//winner = true; 
			//System.out.println(playerHand.IsBust() +"check player is bust"); 
			System.out.println("Dealer is the winner (player is bust) ");
			if (splitting && dealerSplitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
			else if(splitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
			else
				System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
		}
		else if(splitting) {
			if(splitPlayer.IsBust()) {
				dealerIsWinner = true; 
				System.out.println("Dealer is the winner (player is bust)");
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
				splitting = false; 
			}
		}
		else if(dealerSplitting) {
			if(splitDealer.IsBust()) {
				playerIsWinner = true; 
				System.out.println("Player is the winner (dealer is bust)");
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
				dealerSplitting = false; 
			}
		}
		else if(dealerHand.IsBust()) {
			playerIsWinner = true; 
			//winner = true; 
			//System.out.println(dealerHand.IsBust()+ "check dealer is bust"); 

			System.out.println("Player is the winner (dealer is bust)");
			if (splitting && dealerSplitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
			else if(splitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+playerHand.CountTotal()+ " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
			else
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
		String filename = "File5.txt"; 
	

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
		String printSplitPlayerHand = "";
		String printSplitDealerHand = ""; 

		
		List<Card> fileInputCards = new ArrayList<Card>(); 
		Card tempCard; 
		playerHand.turn = true; 
		for(int i = 0; i < parseCommands.length; i++) {
			if(dealCards1) {

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
					//if(dealerHand.GetCard(j).getVisibility())
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
		

				else {
					if(parseCommands[i].equals("D")) {
						System.out.println("Command D");

						if(playerHand.isSplit()) {
							System.out.println("(splitting)");

						splitting = true; 
						//splitPlayer = new Hand(); 
						splitPlayer.AddCard(playerHand.split());

						i++;
						fileInputCards.add(new Card(parseCommands[i]));

						fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));

						playerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
						System.out.println("Player Receives " + tempCard.getName());
						
						
						i++;
						fileInputCards.add(new Card(parseCommands[i]));

						fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));

						
						splitPlayer.AddCard(tempCard = fileInputDeck.DrawCard()); 
						System.out.println("Player Split Receives " + tempCard.getName());
						
						for(int j = 0; j < playerHand.GetSize(); j++) {	
							printPlayersHand += playerHand.GetCard(j).getInput() + " "; 
						}
						System.out.println("Players Hand: "+ printPlayersHand);
						printPlayersHand = ""; 
						
						for(int j = 0; j < splitPlayer.GetSize(); j++) {	
							printSplitPlayerHand += splitPlayer.GetCard(j).getInput() + " "; 
						}
						System.out.println("Players Split Hand: "+ printSplitPlayerHand);
						printSplitPlayerHand = ""; 
					}
				}
					

					while(playerIsWinner == false & dealerIsWinner == false) {	
						if(parseCommands[i].equals("H") && playerHand.turn) {
							i++;
							fileInputCards.add(new Card(parseCommands[i]));
	
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
						else if(parseCommands[i].equals("H") && splitPlayer.turn) {
							if(splitting) {
							i++;
							fileInputCards.add(new Card(parseCommands[i]));
	
							fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));
	
							splitPlayer.AddCard(tempCard = fileInputDeck.DrawCard()); 
							System.out.println("Player Split Receives " + tempCard.getName());

							for(int j = 0; j < splitPlayer.GetSize(); j++) {	
								printSplitPlayerHand += splitPlayer.GetCard(j).getInput() + " "; 
							}
							System.out.println("Players Split Hand: "+ printSplitPlayerHand);
							printSplitPlayerHand = ""; 
							if(CheckBust() == true) break;
							}
						}
						

						
						else if(parseCommands[i].equals("S") && playerHand.turn == true) {
							System.out.println("check 1 ");
							playerHand.turn= false; 
							splitPlayer.turn = true; 	
							if(splitting == false)
								splitPlayer.turn = false ;
								
							if((parseCommands[i].equals("S") && splitPlayer.turn == false)) {
								System.out.println("check 2 ");
								splitPlayer.turn = false; 
								playerHand.turn= false; 
								dealerHand.turn = true;
								//HitOrStand(dealerHand); 
								if(dealerHand.isSplit() && (dealerHand.CountTotal() <= 17)) {
									
									dealerSplitting = true; 
									System.out.println("isSplitDealer == true");
									splitDealer.AddCard(dealerHand.split());
									/*i++;
									System.out.println("check: " +parseCommands[i] );

									fileInputCards.add(new Card(parseCommands[i]));

									fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));

									dealerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
					
									System.out.println("Dealer Receives " + tempCard.getName());
									
									i++; 
									//System.out.println("check: " +parseCommands[i] );

									fileInputCards.add(new Card(parseCommands[i]));

									fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));

									splitDealer.AddCard(tempCard = fileInputDeck.DrawCard()); 
									
									System.out.println("Dealer Split Receives " + tempCard.getName());
									
									for(int j = 0; j < dealerHand.GetSize(); j++) {	
										printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
									}
									System.out.println("Dealer Hand: "+ printDealersHand);
									printDealersHand = ""; 
									
									for(int j = 0; j < splitDealer.GetSize(); j++) {	
										printSplitDealerHand += splitDealer.GetCard(j).getInput() + " "; 
									}
									System.out.println("Dealer Split Hand: "+ printSplitDealerHand);
									printSplitDealerHand = ""; */
							}
								
								while((dealerHand.CountTotal() < 17) || (dealerHand.isSoft())) {
									i++;
									System.out.println("check 1 --  " + parseCommands[i]);
									fileInputCards.add(new Card(parseCommands[i]));
								
		
									fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));	
		
									dealerHand.AddCard(tempCard = fileInputDeck.DrawCard()); 
									System.out.println("Dealer Receives " + tempCard.getName());

									for(int j = 0; j < dealerHand.GetSize(); j++) {
										printDealersHand += dealerHand.GetCard(j).getInput() + " "; 
									}
									System.out.println("Dealers Hand: "+printDealersHand);
									printDealersHand = ""; 
									//i++;

									if(CheckBust() == true) break;	
								}
								//CheckWinner(); 
								if(dealerSplitting) {
									System.out.println("check 3");

									dealerHand.turn = false;
									splitDealer.turn = true; 
									//HitOrStand(splitDealer); 	
									//i++;
									System.out.println("check 2 --  " + parseCommands[i]);

									while((splitDealer.CountTotal() < 17) || (splitDealer.isSoft())) {
										i++;
										System.out.println("check 3--  " + parseCommands[i]);

									fileInputCards.add(new Card(parseCommands[i]));
								
									fileInputDeck.AddCard(fileInputCards.get(fileInputCards.size()-1));	
		
									splitDealer.AddCard(tempCard = fileInputDeck.DrawCard()); 
									//System.out.println("Dealer Receives " + tempCard.getName());

									for(int j = 0; j < splitDealer.GetSize(); j++) {
										printSplitDealerHand += splitDealer.GetCard(j).getInput() + " "; 
									}
									System.out.println("Dealers Split Hand: "+printSplitDealerHand);
									printSplitDealerHand = ""; 
									if(CheckBust() == true) break;	
									System.out.println("end");
								}
									System.out.println("winner");

									CheckWinner(); 

								}
								System.out.println("break");

								break;

								}
						}
							
					
						
						else if(parseCommands[i].equals("S") && splitPlayer.turn == true) {
							splitPlayer.turn = false; 
							dealerHand.turn = true; 							
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
		
		Hand betterHand = betterHand(playerHand, splitPlayer);  
		Hand betterDealerHand = betterHand(dealerHand, splitDealer); 
		System.out.println("betterHand: "+betterHand.CountTotal() + "betterDealerHand: " + betterDealerHand.CountTotal());
		System.out.println("befiore tied");

		if((betterHand.CountTotal() < 22) & (betterDealerHand.CountTotal() < 22)) {
			System.out.println("tied");

			if(betterHand.CountTotal() == betterDealerHand.CountTotal()) {
				winner = true; 
				dealerIsWinner = true;
				
				for(int j = 0; j < betterDealerHand.GetSize(); j++) {
						printDealersHand += betterDealerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Dealers Hand: "+ printDealersHand);
				printDealersHand = ""; 
				
				
				System.out.println("Dealer wins! (Tied score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
			}
			//System.out.println("before player");

			else if(betterHand.CountTotal() > betterDealerHand.CountTotal()) {
				System.out.println(" player");

				winner  = true; 
				playerIsWinner = true; 

				for(int j = 0; j < betterHand.GetSize(); j++) {	
					printPlayersHand += betterHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Players Hand: "+ printPlayersHand);
				printPlayersHand = ""; 
				
				
				System.out.println("Player wins! (Higher score) ");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
			}
			//System.out.println("before dealer");

			else if(betterHand.CountTotal() < betterDealerHand.CountTotal()) {
				System.out.println("dealer");

				winner = true; 
				dealerIsWinner = true; 

				
				for(int j = 0; j < betterDealerHand.GetSize(); j++) {
						printDealersHand += betterDealerHand.GetCard(j).getInput() + " "; 
				}
				System.out.println("Dealers Hand: "+printDealersHand);
				printDealersHand = ""; 
				
				
				System.out.println("Dealer wins! (Higher score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Player Split Score: " + splitPlayer.CountTotal() + " Dealer Score: "+dealerHand.CountTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.CountTotal()+ " Dealer Score: "+dealerHand.CountTotal() + " Dealer Split Score: "+splitDealer.CountTotal());
				else
					System.out.println("Player Score: "+playerHand.CountTotal()+" Dealer Score: "+dealerHand.CountTotal());
			}
		}
		return winner; 
	}


	private static Hand betterHand(Hand hand, Hand splitHand) {
		// TODO Auto-generated method stub
		if(splitting || dealerSplitting) {
			if((hand.CountTotal() < 22) & (splitHand.CountTotal() < 22)) {
				if(hand.CountTotal() == splitHand.CountTotal()) {
					return hand; 
				}
				else if(hand.CountTotal() > splitHand.CountTotal()) {
					
					return hand; 
				}
				else if(hand.CountTotal() < splitHand.CountTotal()) {
					return splitHand; 
				}
			}
			else if(hand.CountTotal()< 22)return hand; 
			else if(splitHand.CountTotal()< 22)return splitHand; 

		}
		return hand;
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
