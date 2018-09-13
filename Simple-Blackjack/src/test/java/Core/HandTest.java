package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 

public class HandTest extends TestCase{
	
	public void testAddCard() {
		Deck deck = new Deck(); 
		Hand hand = new Hand(); 
		
	    Card card_1 = new Card("S2"); 
	    Card card_2 = new Card("S2"); 
	    Card card_3 = new Card("S2"); 
	    Card card_4 = new Card("S2"); 
	    
	    deck.AddCard(card_1);
	    deck.AddCard(card_2);
	    deck.AddCard(card_3);
	    deck.AddCard(card_4);

		Card cardToAdd1 = deck.DrawCard(); 
		hand.AddCard(cardToAdd1); 
		assertEquals(2, hand.getCard(0).getValue()); 
		
		Card cardToAdd2 = deck.DrawCard(); 
		hand.AddCard(cardToAdd2); 
		assertEquals(2, hand.getCard(1).getValue()); 
		
	}
	
	public void testgetCard() {
		
	}
	/*public void testAddCards() {
		
	}*/
	
	
	public void testCountPoints() {
		
	}
	
	public void testIsSoft() {
		
	}
}
