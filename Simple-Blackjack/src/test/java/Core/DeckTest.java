package Core;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;
import java.util.Random;
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
	
	//11
	public void test52Cards() {
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
		Deck deck = new Deck(); 
		
		assertTrue(deck.empty());
		deck.createDefaultDeck(input);
		assertFalse(deck.empty()); 
		
		assertEquals(52, deck.sizeOfDeck()); 
		
		assertEquals(11, deck.getCard(0).getValue());
		
	}
	
	//12
	public void testShuffleDeck() {
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
		Deck shuffleDeck = new Deck(); 
		Deck defaultDeck = new Deck(); 
		shuffleDeck.createDefaultDeck(input);
		defaultDeck.createDefaultDeck(input);
		shuffleDeck.shuffleDeck();
		Random rand = new Random();
		
		int value = rand.nextInt(52)+1;
		int shuffledCard = shuffleDeck.getCard(value).getValue(); 
		int defaultCard = defaultDeck.getCard(value).getValue(); 
		assert(shuffledCard != defaultCard); 
		
	}
   

}
