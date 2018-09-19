package Core;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	private Hand dealerHand = new Hand("Dealer");
	private  Hand splitDealer = new Hand("DealerSplit"); 
	
	
	public Hand getHand() {
		return this.dealerHand; 
	}
	
	public Hand getSplitHand() {
		return this.splitDealer; 
	}
	public Card hit(Hand hand, Deck deck) {
		Card tempCard; 
		hand.addCard(tempCard = deck.drawCard());
		return tempCard; 
	}
	
	public boolean hitOrStand(Hand hand, Deck deck) {
		while((hand.countTotal() < 17) || (hand.isSoft())) {
			hit(hand, deck);

			if(hand.IsBust() == true)return false; 
			
			if(hand.turn) {
				hand.showHand();
			}
			
		}
		return true; 
	}
	
	public int hitOrStand(Hand hand, Deck deck, String[] input, int index) {
		//List<Card> fileInputCards = new ArrayList<Card>(); 
		Card fileInputCards; 
		Card tempCard; 
		while((hand.countTotal() < 17) || (hand.isSoft())) {
			index++; 
			fileInputCards = new Card(input[index]);						
			deck.addCard(fileInputCards);	
			//hit(hand, deck);

			tempCard = hit(hand, deck); 
			System.out.println("Dealer Receives " + tempCard.getName());
			if(hand.IsBust() == true)break; 
			
			if(hand.turn) {
				hand.showHand();
			}
			
		}
		return index; 
		/*while((dealer.getHand().countTotal() < 17) || (dealer.getHand().isSoft())) {
		i++;
		
		fileInputCards.add(new Card(parseCommands[i]));						
		fileInputDeck.addCard(fileInputCards.get(fileInputCards.size()-1));	
		tempCard = Hit(dealer.getHand(), fileInputDeck); 
		System.out.println("Dealer Receives " + tempCard.getName());

		dealer.getHand().showHand();

		if(CheckBust() == true) break;								
	}*/
		//return true; 
	}

}
