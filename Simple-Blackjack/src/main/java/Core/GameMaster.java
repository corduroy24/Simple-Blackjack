package Core;

import java.util.Scanner;

public class GameMaster {
	
	public  boolean playerIsWinner;
	public  boolean dealerIsWinner; 
	
	public  boolean splitting=false; 
	public  boolean dealerSplitting=false; 

	public  Dealer dealer = new Dealer(); 
	public  Player player = new Player(); 
	
	public  Deck deck = new Deck(); 

	public void checkBlackjack(Hand playerHand, Hand dealerHand) {
		// TODO Auto-generated method stub
		if(player.getHand().isBlackJack() && dealer.getHand().isBlackJack()) {
			playerIsWinner  = true; 
			dealerIsWinner = true;
			System.out.println("Dealer is the winner (both had blackjack)"); 
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());
		}

		else if(player.getHand().isBlackJack()) {
			playerIsWinner  = true; 
			System.out.println("Player is the winner (blackjack)");
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());

		}

		else if(dealer.getHand().isBlackJack()) {
			dealerIsWinner = true; 
			System.out.println("Dealer is the winner(blackjack)");
			System.out.println("Player Score: "+player.getHand().countTotal()+" Dealer Score: "+dealer.getHand().countTotal());

		}
		
	}
	
	public  boolean checkBust() {
		// TODO Auto-generated method stub
		if(player.getHand().isBust()) {
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
			if(player.getSplitHand().isBust()) {
				dealerIsWinner = true; 
				System.out.println("Dealer is the winner (player is bust)");
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				splitting = false; 
			}
		}
		else if(dealerSplitting) {
			if(dealer.getSplitHand().isBust()) {
				playerIsWinner = true; 
				System.out.println("Player is the winner (dealer is bust)");
				System.out.println("Player Score: "+player.getHand().countTotal()+ " Player Split Score: " + player.getSplitHand().countTotal() + " Dealer Score: "+dealer.getHand().countTotal());
				dealerSplitting = false; 
			}
		}
		else if(dealer.getHand().isBust()) {
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
	
	public boolean checkWinner() {
		boolean winner =  false; 
		
		Hand betterHand = betterHand(player.getHand(), player.getSplitHand());  
		Hand betterDealer = betterHand(dealer.getHand(), dealer.getSplitHand()); 
		System.out.println(" ");

		if((betterHand.countTotal() < 22) & (betterDealer.countTotal() < 22)) {

			if(betterHand.countTotal() == betterDealer.countTotal()) {
				winner = true; 
				dealerIsWinner = true;
				
				//betterDealer.showHand();
				
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

			else if(betterHand.countTotal() > betterDealer.countTotal()) {

				winner  = true; 
				playerIsWinner = true; 

				//betterHand.showHand();

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

			else if(betterHand.countTotal() < betterDealer.countTotal()) {

				winner = true; 
				dealerIsWinner = true; 

				//betterDealer.showHand();
	
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
	
	public Hand betterHand(Hand hand, Hand splitHand) {
		// TODO Auto-generated method stub
		
		if(splitting || dealerSplitting) {
			if((hand.countTotal() < 22) & (splitHand.countTotal() < 22)) {
				System.out.println(" ");

				System.out.println("--------BETTER HANDS---------");


				if(hand.countTotal() == splitHand.countTotal()) {
					hand.showHand();
					return hand; 
				}
				else if(hand.countTotal() > splitHand.countTotal()) {
					hand.showHand();
					return hand; 
				}
				else if(hand.countTotal() < splitHand.countTotal()) {
					splitHand.showHand();
					return splitHand; 
				}
			}
			else if(hand.countTotal()< 22)return hand; 
			else if(splitHand.countTotal()< 22)return splitHand; 

		}
		return hand;
	}

	public void bettingSequence(char input) {
		// TODO Auto-generated method stub
		Card tempCard; 

			if(input == 'H' && player.getHand().turn == true) {
				tempCard = player.hit(player.getHand(), deck);
				System.out.println(player.getHand().getName() +" Receives " + tempCard.getName());


				checkBust();
			}
			
			else if(input == 'H' && player.getSplitHand().turn == true) {
				if(splitting) {				
					System.out.println("splitting");

					tempCard = player.hit(player.getSplitHand(), deck);
					System.out.println(player.getSplitHand().getName() +" Receives " + tempCard.getName());

					checkBust();
				}
			}
			
			else if(input == 'S' && player.getHand().turn == true) {
				player.getHand().turn= false; 
				player.getHand().showHand();

				
				if(splitting) {
					player.getSplitHand().turn = true; 
					tempCard = player.hit(player.getSplitHand(), deck);
					System.out.println(player.getSplitHand().getName() +" Receives " + tempCard.getName());
				}
					
				if((input == 'S' && player.getSplitHand().turn == false)) {
					player.getSplitHand().turn = false; 
					player.getHand().turn= false; 
					dealer.getHand().turn = true;
					tempCard = dealer.hitOrStand(dealer.getHand(), deck);
					System.out.println(dealer.getHand().getName() + " Receives " + tempCard.getName());

					if(dealerSplitting) {

						dealer.getHand().turn = false;
						dealer.getSplitHand().turn = true; 
						tempCard = dealer.hitOrStand(dealer.getSplitHand(), deck); 
						System.out.println(dealer.getSplitHand().getName() + " Receives " + tempCard.getName());
					}
					checkWinner();
				}
			}
				
			else if(input == 'S' && player.getSplitHand().turn ==true) {
				player.getSplitHand().turn = false; 
				player.getSplitHand().showHand();

				player.getHand().turn= false; 
				dealer.getHand().turn = true;
				tempCard = dealer.hitOrStand(dealer.getHand(), deck); 
				System.out.println(dealer.getHand().getName() + " Receives " + tempCard.getName());

				if(dealerSplitting) {
					dealer.getHand().turn = false;
					dealer.getSplitHand().turn = true; 
					tempCard = dealer.hitOrStand(dealer.getSplitHand(), deck); 
					System.out.println(dealer.getSplitHand().getName() + " Receives " + tempCard.getName());

				}
				checkWinner();
			}
			else
				System.out.println("Please enter a valid command");
	}
	
	public int bettingSequence(Deck fileInputDeck, String[] parseCommands, int i) {
		Card fileInputCards; 
		Card tempCard; 
		if(parseCommands[i].equals("D")) {
			System.out.println("Command D");

			playerSplit();
			
			i++;
			
			fileInputCards = new Card(parseCommands[i]);
			deck.addCard(fileInputCards);
			tempCard = player.hit(player.getHand(), deck); 
			System.out.println(player.getHand().getName() +" Receives " + tempCard.getName());
			
			
			player.getHand().showHand();
		}
		
		while(playerIsWinner == false & dealerIsWinner == false) {	
			if(parseCommands[i].equals("H") && player.getHand().turn) {
				i++;
				
				fileInputCards = new Card(parseCommands[i]);
				fileInputDeck.addCard(fileInputCards);
				tempCard = player.hit(player.getHand(), fileInputDeck); 
				System.out.println(player.getHand().getName() +" Receives " + tempCard.getName());

				if(checkBust() == true) break;
			}
			else if(parseCommands[i].equals("H") && player.getSplitHand().turn) {
				if(splitting) {
				i++;
				
				fileInputCards = new Card(parseCommands[i]);
				fileInputDeck.addCard(fileInputCards);
				tempCard = player.hit(player.getSplitHand(), fileInputDeck); 
				System.out.println(player.getSplitHand().getName() +" Receives " + tempCard.getName());

				if(checkBust() == true) break;
				}
			}
			

			else if(parseCommands[i].equals("S") && player.getHand().turn == true) {
					player.getHand().showHand();
					player.getHand().turn= false; 
					
					
					//player.getSplitHand().turn = true; 	
				if(splitting) {
					i++;
					fileInputCards = new Card(parseCommands[i]);
					deck.addCard(fileInputCards);
					tempCard = player.hit(player.getSplitHand(), deck); 
					System.out.println(player.getSplitHand().getName() +" Receives " + tempCard.getName());
					player.getSplitHand().turn = true ;
				}
					
				if((parseCommands[i].equals("S") && player.getSplitHand().turn == false)) {
					player.getSplitHand().turn = false;

					dealer.getHand().turn = true;
					dealerSplit();

					i = dealer.hitOrStand(dealer.getHand(), fileInputDeck, parseCommands, i);
					checkBust();

					if(dealerSplitting) {

						dealer.getHand().turn = false;
						dealer.getSplitHand().turn = true; 
						i = dealer.hitOrStand(dealer.getSplitHand(), fileInputDeck, parseCommands, i);
						checkBust(); 

					}
					checkWinner();

					break;
				}
			}
				

			else if(parseCommands[i].equals("S") && player.getSplitHand().turn == true) {
				player.getSplitHand().turn = false; 
				player.getSplitHand().showHand();
				dealer.getHand().turn = true; 							
				System.out.println(dealer.getHand().countTotal() + " " + dealer.getHand().isSoft() );
				i = dealer.hitOrStand(dealer.getHand(), fileInputDeck, parseCommands, i);
				checkBust();

				checkWinner(); 
			}
			
			break; 
		}
		return i; 
		
	}
	
	public  void dealCards(Deck deck) {
		// TODO Auto-generated method stub
		Card tempCard; 
		tempCard = player.hit(player.getHand(), deck);
		System.out.println(player.getHand().getName() +" Receives " + tempCard.getName());

		tempCard = player.hit(player.getHand(), deck);
		System.out.println(player.getHand().getName() +" Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println(dealer.getHand().getName() +" Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println(dealer.getHand().getName() +" Receives " + tempCard.getName());

		
		dealer.getHand().getCard(1).SetVisibility(false);
		
		player.getHand().showHand();
		dealer.getHand().showHand();
		
		dealer.getHand().getCard(1).SetVisibility(true);
		
		
	}

	public void playerSplit() {
		// TODO Auto-generated method stub
		Card tempCard; 
		splitting = true; 
		System.out.println("isSplit == true");
		player.getSplitHand().addCard(player.getHand().split());
	}
	
	/*public int playerSplit(Deck deck, String[] input, int index) {
		// TODO Auto-generated method stub
		Card fileInputCards; 
		Card tempCard; 
		if(player.getHand().isSplit()) {
			System.out.println("(splitting)");

			splitting = true; 
			player.getSplitHand().addCard(player.getHand().split());

		}
		return index; 
	}*/

	public void dealerSplit() {
		// TODO Auto-generated method stub
		if(dealer.getHand().isSplit() && (dealer.getHand().countTotal() <= 17)) {
			
			dealerSplitting = true; 
			System.out.println("getSplitHand() == true");
			dealer.getSplitHand().addCard(dealer.getHand().split());
					
					
			dealer.getHand().showHand();
			dealer.getSplitHand().showHand();
					
		}
	}
		
}
