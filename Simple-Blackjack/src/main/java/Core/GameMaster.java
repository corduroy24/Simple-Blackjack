package Core;

public class GameMaster {
	
	public  boolean playerIsWinner;
	public  boolean dealerIsWinner; 
	
	public  boolean splitting=false; 
	public  boolean dealerSplitting=false; 

	public  Dealer dealer = new Dealer(); 
	public  Player player = new Player(); 
	
	public void checkBlackjack(Hand playerHand, Hand dealerHand) {
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
	
	public  boolean CheckBust() {
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
	
	public boolean CheckWinner() {
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

}
