package Core;

public class Player {
	private Hand playerHand = new Hand("Player");
	private Hand splitPlayer = new Hand("PlayerSplit"); 
	
	public Hand getHand() {
		return this.playerHand; 
	}
	
	public Hand getSplitHand() {
		return this.splitPlayer; 
	}
	
	public Card hit(Hand hand, Deck deck) {
		Card tempCard; 
		hand.addCard(tempCard = deck.drawCard());
		return tempCard; 
	}
	
}
