package Core;

import junit.framework.TestCase;

public class GameMasterTest extends TestCase {
	
	/*public void testEndOfTurn() {
		GameMaster game = new GameMaster();
		String [] input = { "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		
	//	Deck shuffleDeck = new Deck();
		Player player = new Player(); 
		game.deck.createDefaultDeck(input);
		game.deck.shuffleDeck();
		char inputH = 'H'; 
		char inputS = 'S'; 
		
		game.bettingSequence(inputH);
		game.bettingSequence(inputS);
		
		assertFalse(game.player.getHand().turn);
		
	}*/
	
	public void testPlayerBustDealerWins() {
		GameMaster game = new GameMaster();
		
		Card card1 = new Card("S10"); 
		Card card2 = new Card("D10"); 
		Card card3 = new Card("S5"); 
		Card card4 = new Card("SJ"); 
		Card card5 = new Card("S3"); 

		game.player.getHand().addCard(card1);
		game.player.getHand().addCard(card2);
		game.player.getHand().addCard(card3);
		game.dealer.getHand().addCard(card4);
		game.dealer.getHand().addCard(card5);
		
		game.checkBust(); 
		
		assertTrue(game.dealerIsWinner); 
	}
	
	public void testDealerBustPlayerWins() {
		GameMaster game = new GameMaster();
		
		Card card1 = new Card("S3"); 
		Card card2 = new Card("D5"); 
		Card card3 = new Card("S6"); 
		Card card4 = new Card("S6"); 
		Card card5 = new Card("S10"); 
		Card card6 = new Card("DJ"); 


		game.player.getHand().addCard(card1);
		game.player.getHand().addCard(card2);
		game.dealer.getHand().addCard(card3);
		game.dealer.getHand().addCard(card4);
		
		game.deck.addCard(card5);
		game.deck.addCard(card6);

		game.dealer.hitOrStand(game.dealer.getHand(), game.deck);
		//game.dealer.getHand().addCard(card5);
		
		game.checkBust(); 
		
		assertTrue(game.playerIsWinner); 
	}
	
	public void testPlayerHasBlacjackNotDealer() {
		GameMaster game  = new GameMaster();

		
	    Card card_1 = new Card("SJ"); 
	    Card card_2 = new Card("SA"); 
	    Card card_3 = new Card("S3"); 
	    Card card_4 = new Card("S5"); 
	    
	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    game.dealer.getHand().addCard(card_3);
	    game.dealer.getHand().addCard(card_4);
	    
	    assertTrue(game.player.getHand().isBlackJack()); 
	    	    
	    assertEquals(21, game.player.getHand().countTotal()); 
	    
	    game.checkBlackjack(game.player.getHand(), game.dealer.getHand()); 
	    
	    assertTrue(game.playerIsWinner); 

	}
	
	public void testDealerHasBlackjack() {
		GameMaster game  = new GameMaster();

	    Card card_1 = new Card("SQ"); 
	    Card card_2 = new Card("SA"); 
	    Card card_3 = new Card("SJ"); 
	    Card card_4 = new Card("SA"); 
	    
	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    game.dealer.getHand().addCard(card_3);
	    game.dealer.getHand().addCard(card_4);
	    
	    assertTrue(game.dealer.getHand().isBlackJack()); 
	    assertTrue(game.player.getHand().isBlackJack()); 

	    	    
	    assertEquals(21, game.dealer.getHand().countTotal()); 
	    
	    game.checkBlackjack(game.player.getHand(), game.dealer.getHand()); 
	    
	    assertTrue(game.dealerIsWinner); 
		
	}
	
	public void testNoBusts() {
		GameMaster game1  = new GameMaster();
		GameMaster game2 = new GameMaster();


	    Card card_1 = new Card("S7"); 
	    Card card_2 = new Card("S4"); 
	    Card card_3 = new Card("S6"); 

	    Card card_4 = new Card("SJ"); 
	    Card card_5 = new Card("S8"); 
	    
	    game1.player.getHand().addCard(card_1);
	    game1.player.getHand().addCard(card_2);
	    game1.player.getHand().addCard(card_3);

	    game1.dealer.getHand().addCard(card_4);
	    game1.dealer.getHand().addCard(card_5);
	    
	    assertFalse(game1.dealer.getHand().isBlackJack()); 
	    assertFalse(game1.player.getHand().isBlackJack()); 
	    
	    game1.checkBlackjack(game1.player.getHand(), game1.dealer.getHand()); 
	    
	    assertFalse(game1.checkBust()); 
	    
	    assertTrue(game1.checkWinner()); 

	    assertTrue(game1.dealerIsWinner); 
	    
	    Card card_6 = new Card("S7"); 
	    Card card_7 = new Card("S4"); 
	    Card card_8 = new Card("S9"); 

	    Card card_9 = new Card("SJ"); 
	    Card card_10 = new Card("S8"); 
	    
	    game2.player.getHand().addCard(card_6);
	    game2.player.getHand().addCard(card_7);
	    game2.player.getHand().addCard(card_8);

	    game2.dealer.getHand().addCard(card_9);
	    game2.dealer.getHand().addCard(card_10);
	    
	    assertFalse(game2.dealer.getHand().isBlackJack()); 
	    assertFalse(game2.player.getHand().isBlackJack()); 
	    
	    game2.checkBlackjack(game2.player.getHand(), game2.dealer.getHand()); 
    
	    assertFalse(game2.checkBust()); 
	    
	    assertTrue(game2.checkWinner()); 
	    assertTrue(game2.playerIsWinner); 
		
	}
	
	public void testPlayerSplitting() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S7"); 
	    Card card_2 = new Card("S7"); 

	    
	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    
		game.playerSplit();
		
		assertNotNull(game.player.getSplitHand()); 
		assertEquals(game.player.getHand().getCard(0).getValue(), game.player.getSplitHand().getCard(0).getValue());
		
		assertTrue(game.splitting);
	}
	
	public void testDealerSplitting() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S7"); 
	    Card card_2 = new Card("S7"); 

	    
	    game.dealer.getHand().addCard(card_1);
	    game.dealer.getHand().addCard(card_2);
	    
		game.dealerSplit();
		
		assertNotNull(game.dealer.getSplitHand()); 
		assertEquals(game.dealer.getHand().getCard(0).getValue(), game.dealer.getSplitHand().getCard(0).getValue());
		
		assertTrue(game.dealerSplitting);
	}
	
}
