package Core;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	public void testHit() {
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
		Deck shuffleDeck = new Deck();
		//Hand playerHand = new Hand("Player Hand"); 
		Player player = new Player(); 
		shuffleDeck.createDefaultDeck(input);
		shuffleDeck.shuffleDeck();
		Card tempCard1, tempCard2; 
		player.getHand().addCard(shuffleDeck.drawCard());
		player.getHand().addCard(tempCard2= shuffleDeck.drawCard());
		
		tempCard1= player.hit(player.getHand(), shuffleDeck); 
		
		assertNotNull(tempCard1);
		assertEquals(tempCard1.getValue(),player.getHand().getCard(2).getValue());
		
	}
}
