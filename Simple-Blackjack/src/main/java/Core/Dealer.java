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

			if(hand.isBust() == true)return false; 
			
			}
		if(hand.turn) {
			hand.showHand();
			
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
			if(hand.isBust() == true)break; 
			
		}
		if(hand.turn) {
			hand.showHand();
		}
		return index; 

	}

}
