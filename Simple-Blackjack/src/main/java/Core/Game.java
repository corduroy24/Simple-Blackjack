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
	
	
	private static Dealer dealer = new Dealer(); 
	private static Player player = new Player(); 
	
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
	
	public static void ConsoleInput() {
		System.out.println("ConsoleInput");
		
		deck.createDefaultDeck(input);
		deck.shuffleDeck();
		
		Card tempCard;
		dealCards(deck); 


		checkBlackjack(player.getHand(), dealer.getHand());

			
		if(player.getHand().isSplit()) {
				
			System.out.println("Do you wanna split (D)?");
			Scanner console = new Scanner(System.in); 
			char inputD = console.next().charAt(0);
			if(inputD == 'D') {
				splitting = true; 
				System.out.println("isSplit == true");
				player.getSplitHand().addCard(player.getHand().split());
					
				/*player.getHand().addCard(tempCard = deck.drawCard());
				System.out.println("Player Receives " + tempCard.getName());
						
				player.getSplitHand().addCard(tempCard = deck.drawCard()); 
				System.out.println("Player Split Receives " + tempCard.getName());*/
					
				player.getHand().showHand();
				player.getSplitHand().showHand();

			}
		}
			
		if(dealer.getHand().isSplit() && (dealer.getHand().countTotal() <= 17)) {
				
			dealerSplitting = true; 
			System.out.println("isdealer.getSplitHand() == true");
			dealer.getSplitHand().addCard(dealer.getHand().split());
					
			/*dealer.getHand().addCard(tempCard = deck.drawCard());
			System.out.println("Dealer Receives " + tempCard.getName());
					
			dealer.getSplitHand().addCard(tempCard = deck.drawCard()); 
			System.out.println("Dealer Split Receives " + tempCard.getName());*/
					
			dealer.getHand().showHand();
			dealer.getSplitHand().showHand();
					
		}

		player.getHand().turn = true; 

		while(playerIsWinner == false & dealerIsWinner == false) {
			System.out.println("Hit (H) or Stand (S) ?");
			Scanner console = new Scanner(System.in); 
			char input = console.next().charAt(0);
				
				//keep asking until its the correct input
				//lower case vs upper case 
			if(input == 'H' && player.getHand().turn == true) {
				player.hit(player.getHand(), deck);
				player.getHand().showHand();

				if(CheckBust() ==true)break;
			}
			
			else if(input == 'H' && player.getSplitHand().turn == true) {
				if(splitting) {				
					System.out.println("splitting");

					player.hit(player.getSplitHand(), deck);
					player.getSplitHand().showHand();

					if(CheckBust() ==true)break;
				}
			}
			
			else if(input == 'S' && player.getHand().turn == true) {
				player.getHand().turn= false; 
				player.getSplitHand().turn= true; 
				if(splitting  == false)
					player.getSplitHand().turn = false; 
					
				if((input == 'S' && player.getSplitHand().turn == false)) {
					player.getSplitHand().turn = false; 
					player.getHand().turn= false; 
					dealer.getHand().turn = true;
					dealer.hitOrStand(dealer.getHand(), deck); 
					if(dealerSplitting) {

						dealer.getHand().turn = false;
						dealer.getSplitHand().turn = true; 
						dealer.hitOrStand(dealer.getSplitHand(), deck); 
					}
					CheckWinner();
					break; 
				}
			}
				
			else if(input == 'S' && player.getSplitHand().turn ==true) {
				player.getSplitHand().turn = false; 
				player.getHand().turn= false; 
				dealer.getHand().turn = true;
				dealer.hitOrStand(dealer.getHand(), deck); 
				if(dealerSplitting) {
					dealer.getHand().turn = false;
					dealer.getSplitHand().turn = true; 
					dealer.hitOrStand(dealer.getSplitHand(), deck); 
				}
				CheckWinner();
				break; 
			}
		}
	}
	
	public static void dealCards(Deck deck) {
		//implement similar to hit in player and dealer class 
		// TODO Auto-generated method stub
		Card tempCard; 
		tempCard = player.hit(player.getHand(), deck);
		System.out.println("Player Receives " + tempCard.getName());

		tempCard = player.hit(player.getHand(), deck);
		System.out.println("Player Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println("Dealer Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println("Dealer Receives " + tempCard.getName());

		
		dealer.getHand().getCard(1).SetVisibility(false);
		
		player.getHand().showHand();
		dealer.getHand().showHand();
		
		dealer.getHand().getCard(1).SetVisibility(true);
		
		
	}


	/*private static void splitSequence() {

	}*/
	
	private static void checkBlackjack(Hand playerHand, Hand dealerHand) {
		// TODO Auto-generated method stub
		if(player.getHand().IsBlackJack() && dealer.getHand().IsBlackJack()) {
			playerIsWinner  = true; 
			dealerIsWinner = true;
			System.out.println("Dealer is the winner (both had blackjack)"); 
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
		}

		else if(player.getHand().IsBlackJack()) {
			playerIsWinner  = true; 
			System.out.println("Player is the winner (blackjack)");
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());

		}

		else if(dealer.getHand().IsBlackJack()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner(blackjack)");
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());

		}
		
	}




	public static boolean CheckBust() {
		// TODO Auto-generated method stub
		if(player.getHand().IsBust()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner (player is bust) ");
			if (splitting && dealerSplitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
			else if(splitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
			else
				System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
		}
		else if(splitting) {
			if(player.getSplitHand().IsBust()) {
				dealerIsWinner = true; 
				System.out.println("Dealer is the winner (player is bust)");
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				splitting = false; 
			}
		}
		else if(dealerSplitting) {
			if(dealer.getSplitHand().IsBust()) {
				playerIsWinner = true; 
				System.out.println("Player is the winner (dealer is bust)");
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				dealerSplitting = false; 
			}
		}
		else if(dealer.getHand().IsBust()) {
			playerIsWinner = true; 
			System.out.println("Player is the winner (dealer is bust)");
			if (splitting && dealerSplitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
			else if(splitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
			else if(dealerSplitting)
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
			else
				System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
		}
		return false;
	}

	
	public static void FileInput() {
		
		String[] parseCommands = new String[deckSize]; 
		parseCommands  = readFile(); 
		System.out.println("FileInput");
		
		Deck fileInputDeck = new Deck(); 
		boolean dealCards1 = true; 

		List<Card> fileInputCards = new ArrayList<Card>(); 
		Card tempCard; 
		player.getHand().turn = true; 
		for(int i = 0; i < parseCommands.length; i++) {
			if(dealCards1) {

				for(int k = 0; k < 4; k++) {
					fileInputCards.add(new Card(parseCommands[k]));
				}
				for(int k = 0; k < 4; k++) {
					fileInputDeck.addCard(fileInputCards.get(k));	
				}
				fileInputDeck.reverseDeck();
				dealCards(fileInputDeck);

				dealCards1=false;
				
				checkBlackjack(player.getHand(), dealer.getHand()); 
				
			}
		

				else {
					if(parseCommands[i].equals("D")) {
						System.out.println("Command D");

						if(player.getHand().isSplit()) {
							System.out.println("(splitting)");

						splitting = true; 
						player.getSplitHand().addCard(player.getHand().split());

						i++;
						
						fileInputCards.add(new Card(parseCommands[i]));
						fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
						tempCard = player.hit(player.getHand(), fileInputDeck); 
						System.out.println("Player Receives " + tempCard.getName());
						
						i++;
						fileInputCards.add(new Card(parseCommands[i]));
						fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
						tempCard = player.hit(player.getSplitHand(), fileInputDeck); 
						System.out.println("Player Split Receives " + tempCard.getName());
						
						player.getHand().showHand();
						
						player.getSplitHand().showHand();
					}
				}
					
					while(playerIsWinner == false & dealerIsWinner == false) {	
						if(parseCommands[i].equals("H") && player.getHand().turn) {
							i++;
							
							fileInputCards.add(new Card(parseCommands[i]));
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
							tempCard = player.hit(player.getHand(), fileInputDeck); 
							System.out.println("Player Receives " + tempCard.getName());

							player.getHand().showHand();
	
							if(CheckBust() == true) break;
						}
						else if(parseCommands[i].equals("H") && player.getSplitHand().turn) {
							if(splitting) {
							i++;
							
							fileInputCards.add(new Card(parseCommands[i]));
							fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));
							tempCard = player.hit(player.getSplitHand(), fileInputDeck); 
							System.out.println("Player Split Receives " + tempCard.getName());

							player.getSplitHand().showHand();

							if(CheckBust() == true) break;
							}
						}
						

						
						else if(parseCommands[i].equals("S") && player.getHand().turn == true) {
							player.getHand().turn= false; 
							player.getSplitHand().turn = true; 	
							if(splitting == false)
								player.getSplitHand().turn = false ;
								
							if((parseCommands[i].equals("S") && player.getSplitHand().turn == false)) {
								player.getSplitHand().turn = false; 
								player.getHand().turn= false; 
								dealer.getHand().turn = true;
								if(dealer.getHand().isSplit() && (dealer.getHand().countTotal() <= 17)) {
									
									dealerSplitting = true; 
									System.out.println("isdealer.getSplitHand() == true");
									dealer.getSplitHand().addCard(dealer.getHand().split());
							}
								i = dealer.hitOrStand(dealer.getHand(), fileInputDeck, parseCommands, i);
								CheckBust();

								if(dealerSplitting) {

									dealer.getHand().turn = false;
									dealer.getSplitHand().turn = true; 
									i = dealer.hitOrStand(dealer.getSplitHand(), fileInputDeck, parseCommands, i);
									CheckBust(); 

								}
								CheckWinner(); 

								break;
							}
						}
							
		
						else if(parseCommands[i].equals("S") && player.getSplitHand().turn == true) {
							player.getSplitHand().turn = false; 
							dealer.getHand().turn = true; 							
							System.out.println(dealer.getHand().countTotal() + " " + dealer.getHand().isSoft() );
							i = dealer.hitOrStand(dealer.getHand(), fileInputDeck, parseCommands, i);
							CheckBust();

							CheckWinner(); 
						}
						
						break; 
					}
				}
		}
	}
	
	private static String[] readFile() {
	// TODO Auto-generated method stub
		//System.out.println("Enter the filename: ");
				//Scanner console = new Scanner(System.in); 
				//String fileName = console.nextLine();
				String line = null; 
				BufferedReader reader = null; 
				String[] parseCommands = new String[deckSize]; 
				String delims = "[ ]+";
				String filename = "File2.txt"; 
			

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
				
				return parseCommands;
}


	public static boolean CheckWinner() {
		boolean winner =  false; 
		
		Hand betterHand = betterHand(player.getHand(), player.getSplitHand());  
		Hand betterdealer = betterHand(dealer.getHand(), dealer.getSplitHand()); 

		if((betterHand.countTotal() < 22) & (betterdealer.countTotal() < 22)) {

			if(betterHand.countTotal() == betterdealer.countTotal()) {
				winner = true; 
				dealerIsWinner = true;
				
				betterdealer.showHand();
				
				System.out.println("Dealer wins! (Tied score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else if(splitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else
					System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
			}

			else if(betterHand.countTotal() > betterdealer.countTotal()) {

				winner  = true; 
				playerIsWinner = true; 

				betterHand.showHand();
				
				System.out.println("Player wins! (Higher score) ");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else if(splitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else
					System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
			}

			else if(betterHand.countTotal() < betterdealer.countTotal()) {

				winner = true; 
				dealerIsWinner = true; 

				betterdealer.showHand();

				
				System.out.println("Dealer wins! (Higher score)");
				if (splitting && dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else if(splitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				else if(dealerSplitting)
					System.out.println("Player Score: "+player.getHand().countTotal()+ " Dealer Score: "+dealer.getHand().countTotal() + " Dealer Split Score: "+dealer.getSplitHand().countTotal());
				else
					System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
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
