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

		if((betterHand.countTotal() < 22) & (betterDealer.countTotal() < 22)) {

			if(betterHand.countTotal() == betterDealer.countTotal()) {
				winner = true; 
				dealerIsWinner = true;
				
				betterDealer.showHand();
				
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

			else if(betterHand.countTotal() < betterDealer.countTotal()) {

				winner = true; 
				dealerIsWinner = true; 

				betterDealer.showHand();
	
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

	public void bettingSequence(char input) {
		// TODO Auto-generated method stub

				
				//keep asking until its the correct input
				//lower case vs upper case 
			if(input == 'H' && player.getHand().turn == true) {
				player.hit(player.getHand(), deck);
				player.getHand().showHand();

				checkBust();
			}
			
			else if(input == 'H' && player.getSplitHand().turn == true) {
				if(splitting) {				
					System.out.println("splitting");

					player.hit(player.getSplitHand(), deck);
					player.getSplitHand().showHand();

					checkBust();
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
					if(splitting) {

						dealer.getHand().turn = false;
						dealer.getSplitHand().turn = true; 
						dealer.hitOrStand(dealer.getSplitHand(), deck); 
					}
					checkWinner();
				}
			}
				
			else if(input == 'S' && player.getSplitHand().turn ==true) {
				player.getSplitHand().turn = false; 
				player.getHand().turn= false; 
				dealer.getHand().turn = true;
				dealer.hitOrStand(dealer.getHand(), deck); 
				if(splitting) {
					dealer.getHand().turn = false;
					dealer.getSplitHand().turn = true; 
					dealer.hitOrStand(dealer.getSplitHand(), deck); 
				}
				checkWinner();
				//break; 
			}
	}
	
	public  void dealCards(Deck deck) {
		//implement similar to hit in player and dealer class 
		// TODO Auto-generated method stub
		Card tempCard; 
		tempCard = player.hit(player.getHand(), deck);
		System.out.println("player Receives " + tempCard.getName());

		tempCard = player.hit(player.getHand(), deck);
		System.out.println("player Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println("dealer Receives " + tempCard.getName());

		tempCard = dealer.hit(dealer.getHand(), deck);
		System.out.println("dealer Receives " + tempCard.getName());

		
		dealer.getHand().getCard(1).SetVisibility(false);
		
		player.getHand().showHand();
		dealer.getHand().showHand();
		
		dealer.getHand().getCard(1).SetVisibility(true);
		
		
	}

}
