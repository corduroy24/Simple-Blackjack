package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 



public class DeckTest extends TestCase{
	
	static int deckSize = 4; 
	
	public void addCardTest() {
	     List<Card> deck = new ArrayList<Card>();
	     Card card_1 = new Card("1","Spades"); 
	     Card card_2 = new Card("1","Spades"); 
	     Card card_3 = new Card("1","Spades"); 
	     Card card_4 = new Card("1","Spades"); 
	     
	     deck.addCard(card_1); 
	     deck.addCard(card_2); 
	     deck.addCard(card_3); 
	     deck.addCard(card_4); 
	     
	     assertEquals(false, deck.isEmpty()); 
	     

	     assertEquals(card_1,deck.get(1)); 

	}
	
	public void addCardsTest() {
	     List<Card> deck = new ArrayList<Card>();
	     List <Card> cardsToAdd = new ArrayList<Card>();
	     
	     Card card_1 = new Card("1","Spades"); 
	     Card card_2 = new Card("1","Spades"); 
	     Card card_3 = new Card("1","Spades"); 
	     Card card_4 = new Card("1","Spades"); 
	     
	     deck.cardsToAdd(card_1); 
	     deck.cardsToAdd(card_2); 
	     deck.cardsToAdd(card_3); 
	     deck.cardsToAdd(card_4); 
	     
	     deck.addCardsTest(cardsToAdd); 
	     
	     assertEquals(false, deck.isEmpty()); 

	     assertEquals(card_1,deck.get(1)); 

	}
	
	public void drawCardTest() {
	     List<Card> deck = new ArrayList<Card>();
	     Card cardToRemove = deck.get(deck.size()-1);  
	     
	     int deckSizeBefore; 
	     int deckSizeAfter; 
	     boolean removedCard = false; 
	     
	     deckSizeBefore = deck.size(); 
	     for(int i = 0; i < deckSize;i++ ) {
	    	 deck.remove(deck.size()-1); 
	    	 removedCard = true; 
	     }
	     deckSizeAfter = deck.size(); 
	     
	     assertEquals((deckSizeBefore - 1), deckSizeAfter);
	     
	     assertTrue(removedCard); 
	        
	}
    


}
