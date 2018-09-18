package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 



public class DeckTest extends TestCase{
	
	static int deckSize = 4; 
	boolean aceIsSoft = false; 

	
	public void testaddCard() {
	    // List<Card> deck = new ArrayList<Card>();
	     Deck deck = new Deck(); 

	     Card card_1 = new Card("S2"); 
	     Card card_2 = new Card("S2"); 
	     Card card_3 = new Card("S2"); 
	     Card card_4 = new Card("S2"); 
	     
	     deck.addCard(card_1); 
	     deck.addCard(card_2); 
	     deck.addCard(card_3); 
	     deck.addCard(card_4); 
	     
	     assertFalse(deck.empty()); 
	     

	     assertEquals(2,deck.getCard(1).getValue()); 

	}
	
	public void testaddCards() {
	     //List<Card> deck = new ArrayList<Card>();
	     List <Card> cardsToAdd = new ArrayList<Card>();
	     Deck deck = new Deck(); 
	     
	     Card card_1 = new Card("S2"); 
	     Card card_2 = new Card("S2"); 
	     Card card_3 = new Card("S2"); 
	     Card card_4 = new Card("S2"); 
	     
	     cardsToAdd.add(card_1); 
	     cardsToAdd.add(card_2); 
	     cardsToAdd.add(card_3); 
	     cardsToAdd.add(card_4); 
	     
	     deck.addCards(cardsToAdd); 
	     
	     assertFalse(deck.empty()); 

	     assertEquals(2,deck.getCard(1).getValue()); 

	}
	
	public void testdrawCard() {
	     //List<Card> deck = new ArrayList<Card>();
	     
	     Deck deck = new Deck(); 
 
	     int deckSizeBefore; 
	     int deckSizeAfter; 
	     boolean removedCard = false; 
	     
	     List <Card> cardsToAdd = new ArrayList<Card>();
	     
	     Card card_1 = new Card("S2"); 
	     Card card_2 = new Card("S2"); 
	     Card card_3 = new Card("S2"); 
	     Card card_4 = new Card("S2");  
	     
	     cardsToAdd.add(card_1); 
	     cardsToAdd.add(card_2); 
	     cardsToAdd.add(card_3); 
	     cardsToAdd.add(card_4); 
	     
	     deck.addCards(cardsToAdd); 
	     
	     Card cardToRemove = deck.getCard(deckSize-1);  
	     
	     deckSizeBefore = deck.sizeOfDeck(); 
	     
	     deck.drawCard(); 
	     removedCard = true;
	     
	     deckSizeAfter = deck.sizeOfDeck(); 
	     
	     assertEquals((deckSizeBefore - 1), deckSizeAfter);
	     
	     assertTrue(removedCard); 
	        
	}
    
	//check that all cards are added to deck...


}
