package Core;

import junit.framework.TestCase;

public class DealerTest extends TestCase {

	public void testLessThan16Hits() {
		Dealer dealer = new Dealer(); 
		Card card1 = new Card("S5"); 
		Card card2 = new Card("D5"); 
		Card card3 = new Card("S5"); 
		Card card4 = new Card("H5"); 
		Card card5 = new Card("S5"); 
		
		Deck deck = new Deck(); 
		deck.addCard(card1);
		deck.addCard(card2);
		deck.addCard(card3);
		deck.addCard(card4);
		deck.addCard(card5);

		dealer.getHand().addCard(deck.drawCard());
		dealer.getHand().addCard(deck.drawCard());
		
		dealer.hitOrStand(dealer.getHand(), deck);
		assertEquals(20, dealer.getHand().countTotal());

	}
	
	public void testSoft17Hits() {
		Dealer dealer = new Dealer(); 
		Card card1 = new Card("SA"); 
		Card card2 = new Card("D6"); 
		Card card3 = new Card("S5"); 
		Card card4 = new Card("H5"); 
		Card card5 = new Card("S5"); 
		
		Deck deck = new Deck(); 

		deck.addCard(card3);
		deck.addCard(card4);
		deck.addCard(card5);

		dealer.getHand().addCard(card1);
		dealer.getHand().addCard(card2);
		
		dealer.hitOrStand(dealer.getHand(), deck);
		assertEquals(17, dealer.getHand().countTotal());

	}
}
