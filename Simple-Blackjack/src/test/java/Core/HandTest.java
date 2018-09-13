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
	    
	    assertTrue(hand.Empty());
	    
		Card cardToAdd1 = deck.DrawCard(); 
		hand.AddCard(cardToAdd1); 
	    assertFalse(hand.Empty());

		assertEquals(2, hand.GetCard(0).getValue()); 
		
		Card cardToAdd2 = deck.DrawCard(); 
		hand.AddCard(cardToAdd2); 
		assertEquals(2, hand.GetCard(1).getValue()); 
		
	}
	
	/*public void testGetCard() {
		
	}*/
	/*public void testAddCards() {
		
	}*/
	
	
	public void testCountPoints() {
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
		
		Card cardToAdd2 = deck.DrawCard(); 
		hand.AddCard(cardToAdd2); 
		
		assertEquals(4, hand.CountTotal()); 
	}
	
	/*public void testIsSoft() {
		Deck deck = new Deck(); 
		Hand hand1 = new Hand(); 
		Hand hand2 = new Hand(); 
		Hand hand3 = new Hand(); 
		Hand hand4 = new Hand(); 


		
	    Card card_1 = new Card("SA"); 
	    
	    Card card_2 = new Card("S3"); 
	    Card card_3 = new Card("S3");
	    
	    Card card_4 = new Card("S4"); 	    
	    Card card_5 = new Card("S2"); 
	    
	    Card card_6 = new Card("S2"); 
	    Card card_7 = new Card("S2");
	    Card card_8 = new Card("S2");

	    Card card_9 = new Card("S6"); 


		hand1.AddCard(card_1); 
		hand1.AddCard(card_9); 
		
		assertTrue(hand.IsSoft())
		
		hand2.AddCard(card_1); 
		hand2.AddCard(card_2); 
		hand2.AddCard(card_3); 
		
		hand3.AddCard(card_1); 
		hand3.AddCard(card_4); 
		hand3.AddCard(card_5); 
		
		hand4.AddCard(card_1); 
		hand4.AddCard(card_6); 
		hand4.AddCard(card_7); 
		hand4.AddCard(card_8); 

		assertEquals(4, hand.CountTotal()); 
	}*/
}
