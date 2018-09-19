package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 

public class HandTest extends TestCase{
	
	public void testaddCard() {
		Deck deck = new Deck(); 
		Hand hand = new Hand("Hand"); 
		
	    Card card_1 = new Card("S2"); 
	    Card card_2 = new Card("S2"); 
	    Card card_3 = new Card("S2"); 
	    Card card_4 = new Card("S2"); 
	    
	    deck.addCard(card_1);
	    deck.addCard(card_2);
	    deck.addCard(card_3);
	    deck.addCard(card_4);
	    
	    assertTrue(hand.empty());
	    
		Card cardToaddCard1 = deck.drawCard(); 
		hand.addCard(cardToaddCard1); 
	    assertFalse(hand.empty());

		assertEquals(2, hand.getCard(0).getValue()); 
		
		Card cardToaddCard2 = deck.drawCard(); 
		hand.addCard(cardToaddCard2); 
		assertEquals(2, hand.getCard(1).getValue()); 
		
	}
	

	
	public void testSplit() {
		Deck deck = new Deck(); 
		Hand playerHand = new Hand("Player"); 
		
	    Card card_1 = new Card("SA"); 
	    Card card_2 = new Card("SA"); 

	    
	    deck.addCard(card_1);
	    deck.addCard(card_2);
	    /*deck.addCardCard(card_3);
	    deck.addCardCard(card_4);*/
	    
	   assertTrue(playerHand.empty());
	   //assertTrue(playerHand.get(1).Empty());

		Card cardToaddCard1 = deck.drawCard(); 
		Card cardToaddCard2 = deck.drawCard(); 
		
		playerHand.addCard(cardToaddCard1); 
		playerHand.addCard(cardToaddCard2); 
		
		assertTrue(playerHand.isSplit()); 
		
		Hand splitPlayer = new Hand("Split Player");
		
		splitPlayer.addCard(playerHand.split());
		
		assertEquals(cardToaddCard1.getValue(), playerHand.getCard(0).getValue());
		
		assertEquals(cardToaddCard2.getValue(), splitPlayer.getCard(0).getValue());

	}
	
	public void testCountPoints() {
		Deck deck = new Deck(); 
		Hand hand = new Hand("Hand"); 
		
	    Card card_1 = new Card("S2"); 
	    Card card_2 = new Card("S2"); 
	    Card card_3 = new Card("S2"); 
	    Card card_4 = new Card("S2"); 
	    
	    deck.addCard(card_1);
	    deck.addCard(card_2);
	    deck.addCard(card_3);
	    deck.addCard(card_4);

		Card cardToaddCard1 = deck.drawCard(); 
		hand.addCard(cardToaddCard1); 
		
		Card cardToaddCard2 = deck.drawCard(); 
		hand.addCard(cardToaddCard2); 
		
		assertEquals(4, hand.countTotal()); 
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


		hand1.addCard(card_1); 
		hand1.addCard(card_9); 
		
		assertTrue(hand.IsSoft())
		
		hand2.addCard(card_1); 
		hand2.addCard(card_2); 
		hand2.addCard(card_3); 
		
		hand3.addCard(card_1); 
		hand3.addCard(card_4); 
		hand3.addCard(card_5); 
		
		hand4.addCard(card_1); 
		hand4.addCard(card_6); 
		hand4.addCard(card_7); 
		hand4.addCard(card_8); 

		assertEquals(4, hand.CountTotal()); 
	}*/
	
	public void testInitialPlayerHand() {
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
		Deck shuffleDeck = new Deck();
		Hand playerHand = new Hand("Player Hand"); 
		shuffleDeck.createDefaultDeck(input);
		shuffleDeck.shuffleDeck();
		Card tempCard1, tempCard2; 
		playerHand.addCard(tempCard1= shuffleDeck.drawCard());
		playerHand.addCard(tempCard2= shuffleDeck.drawCard());
		
		assertTrue(tempCard1.getVisibility()); 
		assertTrue(tempCard2.getVisibility()); 
		
	}
	
	public void testInitialDealerHand() {
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
		Deck shuffleDeck = new Deck();
		Hand dealerHand = new Hand("Dealer Hand"); 
		shuffleDeck.createDefaultDeck(input);
		shuffleDeck.shuffleDeck();
		Card tempCard1, tempCard2; 
		dealerHand.addCard(tempCard1= shuffleDeck.drawCard());
		dealerHand.addCard(tempCard2= shuffleDeck.drawCard());
		
		dealerHand.showFirstCard();
		assertTrue(tempCard1.getVisibility()); 
		assertFalse(tempCard2.getVisibility()); 
		
	}
	public void testTwoAcesInHand() {
		Deck deck = new Deck(); 
		Hand hand = new Hand("Hand"); 
		
	    Card card_1 = new Card("SA"); 
	    Card card_2 = new Card("SA"); 
	    Card card_3 = new Card("SA"); 
	    Card card_4 = new Card("SA"); 
	    
	    card_2.ChangeValue(true); 
	    
	    hand.addCard(card_1);
	    hand.addCard(card_2);
	    
	    assertEquals(11, hand.getCard(0).getValue()); 
	    assertEquals(1, hand.getCard(1).getValue()); 
	    
	}
	
	public void testAceAsElevenThenOne() {
		Deck deck = new Deck(); 
		Hand hand = new Hand("Hand"); 
		
	    Card card_1 = new Card("SA"); 
	    Card card_2 = new Card("SA"); 
	    Card card_3 = new Card("SA"); 
	    Card card_4 = new Card("SA"); 
	    
	    hand.addCard(card_1);
	    hand.addCard(card_2);
	    
	    assertEquals(11, hand.getCard(0).getValue()); 
	    assertEquals(11, hand.getCard(1).getValue()); 
	    
	    assertFalse(hand.isBust()); 
	    
	    assertEquals(1, hand.getCard(0).getValue()); 
	    assertEquals(11, hand.getCard(1).getValue()); 
		
	}
	
	public void testHandCountsTwoAcesAsOneEach() {
		Deck deck = new Deck(); 
		Hand hand = new Hand("Hand"); 
		
	    Card card_1 = new Card("SJ"); 
	    Card card_2 = new Card("S7"); 
	    Card card_3 = new Card("SA"); 
	    Card card_4 = new Card("SA"); 
	    
	    hand.addCard(card_1);
	    hand.addCard(card_2);
	    hand.addCard(card_3);
	    hand.addCard(card_4);
	    

	    
	    assertFalse(hand.isBust()); 
	    
	    assertEquals(1, hand.getCard(2).getValue()); 
	    assertEquals(1, hand.getCard(3).getValue()); 
		
	}
	

}
