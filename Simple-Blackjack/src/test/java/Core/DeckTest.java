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
	
	public void drawCardTest() {
	    /* List<Card> deck = new ArrayList<Card>();
	     for(int i = 0; i < deckSize;i++ ) {
	    	 deck.add
	     }*/
	    
	}
    


}
