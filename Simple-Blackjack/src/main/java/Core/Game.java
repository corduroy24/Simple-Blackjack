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


	//private static Card[] card = new Card[deckSize];
	
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
	
	private static Hand playerHand = new Hand("Player");
	private static Hand dealerHand = new Hand("Dealer");
	private static Hand splitPlayer = new Hand("PlayerSplit"); 
	private static Hand splitDealer = new Hand("DealerSplit"); 
	
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
		

		deck.createDefaultDeck(input);
		deck.shuffleDeck();
		
		Card tempCard;
		
		//turn into function = deal cards 
		playerHand.addCard(deck.drawCard());
		playerHand.addCard(deck.drawCard());

		
		dealerHand.addCard(deck.drawCard());
		dealerHand.addCard(deck.drawCard());
		dealerHand.getCard(1).SetVisibility(false);
		

		playerHand.showHand();
		dealerHand.showHand();
		dealerHand.getCard(1).SetVisibility(true);

		checkBlackjack(playerHand, dealerHand);

			
			if(playerHand.isSplit()) {
				
				System.out.println("Do you wanna split (D)?");
				Scanner console = new Scanner(System.in); 
				char inputD = console.next().charAt(0);
				if(inputD == 'D') {
					splitting = true; 
					System.out.println("isSplit == true");
					splitPlayer.addCard(playerHand.split());
					
					/*playerHand.addCard(tempCard = deck.drawCard());
					System.out.println("Player Receives " + tempCard.getName());
						
					splitPlayer.addCard(tempCard = deck.drawCard()); 
					System.out.println("Player Split Receives " + tempCard.getName());*/
					
					playerHand.showHand();
					splitPlayer.showHand();

				}
			}
			
			if(dealerHand.isSplit() && (dealerHand.countTotal() <= 17)) {
				
					dealerSplitting = true; 
					System.out.println("isSplitDealer == true");
					splitDealer.addCard(dealerHand.split());
					
					/*dealerHand.addCard(tempCard = deck.drawCard());
					System.out.println("Dealer Receives " + tempCard.getName());
					
					splitDealer.addCard(tempCard = deck.drawCard()); 
					System.out.println("Dealer Split Receives " + tempCard.getName());*/
					
					dealerHand.showHand();
					splitDealer.showHand();
					
			}

			playerHand.turn = true; 

			while(playerIsWinner == false & dealerIsWinner == false) {
				System.out.println("Hit (H) or Stand (S) ?");
				Scanner console = new Scanner(System.in); 
				char input = console.next().charAt(0);
				
				//keep asking until its the correct input
				//lower case vs upper case 
				if(input == 'H' && playerHand.turn == true) {
					Hit(playerHand);
					playerHand.showHand();

					if(CheckBust() ==true)break;
				}
				else if(input == 'H' && splitPlayer.turn == true) {
					if(splitting) {				
						System.out.println("splitting");

						Hit(splitPlayer);
						splitPlayer.showHand();

						if(CheckBust() ==true)break;
					}
				}
				else if(input == 'S' && playerHand.turn == true) {
					playerHand.turn= false; 
					splitPlayer.turn= true; 
					if(splitting  == false)
						splitPlayer.turn = false; 
					
					if((input == 'S' && splitPlayer.turn == false)) {
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
				
				else if(input == 'S' && splitPlayer.turn ==true) {
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
	}
	
	/*private static void splitSequence() {

	}*/
	
	private static void checkBlackjack(Hand playerHand, Hand dealerHand) {
		// TODO Auto-generated method stub
		if(playerHand.IsBlackJack() && dealerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			dealerIsWinner = true;
			System.out.println("Dealer is the winner (both had blackjack)"); 
			System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
		}

		else if(playerHand.IsBlackJack()) {
			playerIsWinner  = true; 
			System.out.println("Player is the winner (blackjack)");
			System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());

		}

		else if(dealerHand.IsBlackJack()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner(blackjack)");
			System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());

		}
		
	}


	private static void HitOrStand(Hand hand) {
		// TODO Auto-generated method stub


	//	System.out.println(hand.countTotal() + " " + hand.isSoft() );
		while((hand.countTotal() < 17) || (hand.isSoft())) {
			Hit(hand);

			if(CheckBust() == true)break;
			
			if(dealerHand.turn) {
				dealerHand.showHand();
			}
			
			else if(dealerSplitting && splitDealer.turn == true) {
				splitDealer.showHand();
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
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
			else if(splitting)
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+playerHand.countTotal()+ " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
			else
				System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
		}
		else if(splitting) {
			if(splitPlayer.IsBust()) {
				dealerIsWinner = true; 
				System.out.println("Dealer is the winner (player is bust)");
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
				splitting = false; 
			}
		}
		else if(dealerSplitting) {
			if(splitDealer.IsBust()) {
				playerIsWinner = true; 
				System.out.println("Player is the winner (dealer is bust)");
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
				dealerSplitting = false; 
			}
		}
		else if(dealerHand.IsBust()) {
			playerIsWinner = true; 
			//winner = true; 
			//System.out.println(dealerHand.IsBust()+ "check dealer is bust"); 

			System.out.println("Player is the winner (dealer is bust)");
			if (splitting && dealerSplitting)
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
			else if(splitting)
				System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+playerHand.countTotal()+ " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
			else
				System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
		}
		return false;
	}

	
	private static void Hit(Hand hand) {
		// TODO Auto-generated method stub
		hand.addCard(deck.drawCard());

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
		String filename = "File1.txt"; 
	

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
		boolean dealCards1 = true; 

		List<Card> fileInputCards = new ArrayList<Card>(); 
		Card tempCard; 
		playerHand.turn = true; 
		for(int i = 0; i < parseCommands.length; i++) {
			if(dealCards1) {

				for(int k = 0; k < 4; k++) {
					fileInputCards.add(new Card(parseCommands[k]));
				}
				for(int k = 0; k < 4; k++) {
					fileInputDeck.addCard(fileInputCards.get(k));	
				}
				fileInputDeck.reverseDeck();
				playerHand.addCard(tempCard = fileInputDeck.drawCard()); 
				System.out.println("Player Receives " + tempCard.getName());
				playerHand.addCard(tempCard = fileInputDeck.drawCard()); 
				System.out.println("Player Receives " + tempCard.getName());

				dealerHand.addCard(tempCard = fileInputDeck.drawCard()); 
				System.out.println("Dealer Receives " + tempCard.getName());

				dealerHand.addCard(tempCard = fileInputDeck.drawCard()); 
				System.out.println("Dealer Receives " + tempCard.getName());

				playerHand.showHand();
				
				dealerHand.getCard(1).SetVisibility(false);
				dealerHand.showHand();
				dealerHand.getCard(0).SetVisibility(true);

				dealCards1=false;
				
				checkBlackjack(playerHand, dealerHand); 
				
			}
		

				else {
					if(parseCommands[i].equals("D")) {
						System.out.println("Command D");

						if(playerHand.isSplit()) {
							System.out.println("(splitting)");

						splitting = true; 
						splitPlayer.addCard(playerHand.split());

						i++;
						fileInputCards.add(new Card(parseCommands[i]));

						fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));

						playerHand.addCard(tempCard = fileInputDeck.drawCard()); 
						System.out.println("Player Receives " + tempCard.getName());
						
						
						i++;
						fileInputCards.add(new Card(parseCommands[i]));

						fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));

						
						splitPlayer.addCard(tempCard = fileInputDeck.drawCard()); 
						System.out.println("Player Split Receives " + tempCard.getName());
						
						playerHand.showHand();
						
						splitPlayer.showHand();
					}
				}
					

					while(playerIsWinner == false & dealerIsWinner == false) {	
						if(parseCommands[i].equals("H") && playerHand.turn) {
							i++;
							fileInputCards.add(new Card(parseCommands[i]));
	
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
	
							playerHand.addCard(tempCard = fileInputDeck.drawCard()); 
							System.out.println("Player Receives " + tempCard.getName());

							playerHand.showHand();
	
							if(CheckBust() == true) break;
						}
						else if(parseCommands[i].equals("H") && splitPlayer.turn) {
							if(splitting) {
							i++;
							fileInputCards.add(new Card(parseCommands[i]));
	
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
	
							splitPlayer.addCard(tempCard = fileInputDeck.drawCard()); 
							System.out.println("Player Split Receives " + tempCard.getName());

							splitPlayer.showHand();

							if(CheckBust() == true) break;
							}
						}
						

						
						else if(parseCommands[i].equals("S") && playerHand.turn == true) {
							playerHand.turn= false; 
							splitPlayer.turn = true; 	
							if(splitting == false)
								splitPlayer.turn = false ;
								
							if((parseCommands[i].equals("S") && splitPlayer.turn == false)) {
								splitPlayer.turn = false; 
								playerHand.turn= false; 
								dealerHand.turn = true;
								if(dealerHand.isSplit() && (dealerHand.countTotal() <= 17)) {
									
									dealerSplitting = true; 
									System.out.println("isSplitDealer == true");
									splitDealer.addCard(dealerHand.split());
							}
								
								while((dealerHand.countTotal() < 17) || (dealerHand.isSoft())) {
									i++;
									fileInputCards.add(new Card(parseCommands[i]));
								
		
									fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));	
		
									dealerHand.addCard(tempCard = fileInputDeck.drawCard()); 
									System.out.println("Dealer Receives " + tempCard.getName());

									dealerHand.showHand();


									if(CheckBust() == true) break;	
								}

								if(dealerSplitting) {

									dealerHand.turn = false;
									splitDealer.turn = true; 


									while((splitDealer.countTotal() < 17) || (splitDealer.isSoft())) {
										i++;

									fileInputCards.add(new Card(parseCommands[i]));
								
									fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));	
		
									splitDealer.addCard(tempCard = fileInputDeck.drawCard()); 
									System.out.println("Dealer Split Receives " + tempCard.getName());

									splitDealer.showHand();

									if(CheckBust() == true) break;	
								}

								}
								CheckWinner(); 

								break;

								}
						}
							
		
						else if(parseCommands[i].equals("S") && splitPlayer.turn == true) {
							splitPlayer.turn = false; 
							dealerHand.turn = true; 							
							System.out.println(dealerHand.countTotal() + " " + dealerHand.isSoft() );
							while((dealerHand.countTotal() < 17) || (dealerHand.isSoft())) {
								i++;
								fileInputCards.add(new Card(parseCommands[i]));
							
								fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));	
	
								dealerHand.addCard(tempCard = fileInputDeck.drawCard()); 
								System.out.println("Dealer Receives " + tempCard.getName());

								dealerHand.showHand();

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

		if((betterHand.countTotal() < 22) & (betterDealerHand.countTotal() < 22)) {

			if(betterHand.countTotal() == betterDealerHand.countTotal()) {
				winner = true; 
				dealerIsWinner = true;
				
				betterDealerHand.showHand();
				
				System.out.println("Dealer wins! (Tied score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else
					System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
			}

			else if(betterHand.countTotal() > betterDealerHand.countTotal()) {

				winner  = true; 
				playerIsWinner = true; 

				betterHand.showHand();
				
				System.out.println("Player wins! (Higher score) ");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else
					System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
			}

			else if(betterHand.countTotal() < betterDealerHand.countTotal()) {

				winner = true; 
				dealerIsWinner = true; 

				betterDealerHand.showHand();

				
				System.out.println("Dealer wins! (Higher score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else if(splitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Player Split Score: " + splitPlayer.countTotal() + " Dealer Score: "+dealerHand.countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+playerHand.countTotal()+ " Dealer Score: "+dealerHand.countTotal() + " Dealer Split Score: "+splitDealer.countTotal());
				else
					System.out.println("Player Score: "+playerHand.countTotal()+" Dealer Score: "+dealerHand.countTotal());
			}
		}
		return winner; 
	}


	private static Hand betterHand(Hand hand, Hand splitHand) {
		// TODO Auto-generated method stub
		if(splitting || dealerSplitting) {
			if((hand.countTotal() < 22) & (splitHand.countTotal() < 22)) {
				if(hand.countTotal() == splitHand.countTotal()) {
					return hand; 
				}
				else if(hand.countTotal() > splitHand.countTotal()) {
					
					return hand; 
				}
				else if(hand.countTotal() < splitHand.countTotal()) {
					return splitHand; 
				}
			}
			else if(hand.countTotal()< 22)return hand; 
			else if(splitHand.countTotal()< 22)return splitHand; 

		}
		return hand;
	}

}
