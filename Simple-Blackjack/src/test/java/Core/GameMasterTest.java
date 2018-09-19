package Core;

import junit.framework.TestCase;

public class GameMasterTest extends TestCase {
	
	public void testEndOfTurn() {
		GameMaster game = new GameMaster();

		
		Card card1 = new Card("S4"); 
		Card card2 = new Card("D6"); 
		Card card3 = new Card("S8"); 
		Card card4 = new Card("SJ"); 
		Card card5 = new Card("S7"); 
		Card card6 = new Card("S3"); 

		
		

		game.player.getHand().addCard(card1);
		game.player.getHand().addCard(card2);
		
		game.dealer.getHand().addCard(card3);
		game.dealer.getHand().addCard(card4);
		
		game.deck.addCard(card5);
		game.deck.addCard(card6);
		
		game.player.getHand().turn = true; 
		game.bettingSequence('H');
		game.bettingSequence('S');
		assertFalse(game.player.getHand().turn); 
		
		System.out.println("----------");

	}
	
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
	
	public void testHitOnSplitPlayerDecks() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S3"); 
	    Card card_2 = new Card("S3"); 
	    Card card_3 = new Card("S4"); 
	    Card card_4 = new Card("S5"); 
	    Card card_5 = new Card("S6"); 
	    Card card_6 = new Card("S7"); 

	    
	    game.deck.addCard(card_3);
	    game.deck.addCard(card_4);
	    game.deck.addCard(card_5);
	    game.deck.addCard(card_6);
	    
	    game.deck.reverseDeck();


	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    
		game.playerSplit();
		
		game.player.hit(game.player.getHand(), game.deck); 
		assertEquals(card_3.getValue(), game.player.getHand().getCard(1).getValue()); 
		assertEquals(7, game.player.getHand().countTotal()); 
		
		game.player.hit(game.player.getHand(), game.deck); 
		assertEquals(card_4.getValue(), game.player.getHand().getCard(2).getValue()); 
		assertEquals(12, game.player.getHand().countTotal()); 
		
		game.player.hit(game.player.getSplitHand(), game.deck); 
		assertEquals(card_5.getValue(), game.player.getSplitHand().getCard(1).getValue()); 
		assertEquals(9, game.player.getSplitHand().countTotal()); 
		
		game.player.hit(game.player.getSplitHand(), game.deck); 
		assertEquals(card_6.getValue(), game.player.getSplitHand().getCard(2).getValue()); 
		assertEquals(16, game.player.getSplitHand().countTotal()); 

	}
	
	public void testHitOnSplitDealerDecks() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S3"); 
	    Card card_2 = new Card("S3");
	    
	    Card card_3 = new Card("S4"); 
	    Card card_4 = new Card("S5"); 
	    Card card_5 = new Card("S6"); 
	    
	    Card card_6 = new Card("S7"); 
	    Card card_7 = new Card("S5"); 
	    Card card_8 = new Card("S4"); 

	    game.deck.addCard(card_3);
	    game.deck.addCard(card_4);
	    game.deck.addCard(card_5);
	    game.deck.addCard(card_6);
	    game.deck.addCard(card_7);
	    game.deck.addCard(card_8);

	    
	    game.deck.reverseDeck();


	    game.dealer.getHand().addCard(card_1);
	    game.dealer.getHand().addCard(card_2);
	    
		game.dealerSplit();
		
		game.dealer.hitOrStand(game.dealer.getHand(), game.deck); 
		assertEquals(card_3.getValue(), game.dealer.getHand().getCard(1).getValue()); 
		assertEquals(card_4.getValue(), game.dealer.getHand().getCard(2).getValue()); 
		assertEquals(card_5.getValue(), game.dealer.getHand().getCard(3).getValue()); 
		assertEquals(18, game.dealer.getHand().countTotal()); 
		
		game.dealer.hitOrStand(game.dealer.getSplitHand(), game.deck); 
		assertEquals(card_6.getValue(), game.dealer.getSplitHand().getCard(1).getValue()); 
		assertEquals(card_7.getValue(), game.dealer.getSplitHand().getCard(2).getValue()); 
		assertEquals(card_8.getValue(), game.dealer.getSplitHand().getCard(3).getValue()); 
		assertEquals(19, game.dealer.getSplitHand().countTotal()); 

	}
	
	
	public void testPlayerSplitsChooseBestHand() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S3"); 
	    Card card_2 = new Card("S3");
	    
	    Card card_3 = new Card("S4"); 
	    Card card_4 = new Card("S5"); 
	    Card card_5 = new Card("S6"); 
	    
	    Card card_6 = new Card("S7"); 
	    Card card_7 = new Card("S5"); 
	    Card card_8 = new Card("S4"); 

	    game.deck.addCard(card_3);
	    game.deck.addCard(card_4);
	    game.deck.addCard(card_5);
	    game.deck.addCard(card_6);
	    game.deck.addCard(card_7);
	    game.deck.addCard(card_8);

	    game.deck.reverseDeck();
	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    
		game.playerSplit();
		
		game.player.hit(game.player.getHand(), game.deck); 
		game.player.hit(game.player.getSplitHand(), game.deck); 

		Hand betterHand = game.betterHand(game.player.getHand(), game.player.getSplitHand());
		assertEquals(game.player.getSplitHand().getName(), betterHand.getName()); 
		
	}
	
	public void testDealerSplitsChooseBestHand() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S3"); 
	    Card card_2 = new Card("S3");
	    
	    Card card_3 = new Card("S4"); 
	    Card card_4 = new Card("S5"); 
	    Card card_5 = new Card("S6"); 
	    
	    Card card_6 = new Card("S7"); 
	    Card card_7 = new Card("S5"); 
	    Card card_8 = new Card("S4"); 

	    game.deck.addCard(card_3);
	    game.deck.addCard(card_4);
	    game.deck.addCard(card_5);
	    game.deck.addCard(card_6);
	    game.deck.addCard(card_7);
	    game.deck.addCard(card_8);

	    game.deck.reverseDeck();
	    game.dealer.getHand().addCard(card_1);
	    game.dealer.getHand().addCard(card_2);
	    
		game.dealerSplit();
		
		game.dealer.hitOrStand(game.dealer.getHand(), game.deck); 
		game.dealer.hitOrStand(game.dealer.getSplitHand(), game.deck); 

		Hand betterHand = game.betterHand(game.dealer.getHand(), game.dealer.getSplitHand());
		assertEquals(game.dealer.getSplitHand().getName(), betterHand.getName()); 
		
	}
	
	public void testSplittingAndChoosenWinner() {
		GameMaster game = new GameMaster();
		
	    Card card_1 = new Card("S3"); 
	    Card card_2 = new Card("S3");
	    
	    Card card_3 = new Card("S4"); 
	    Card card_4 = new Card("S5"); 
	    Card card_5 = new Card("S6"); 
	    
	    Card card_6 = new Card("S7"); 
	    Card card_7 = new Card("S5"); 
	    Card card_8 = new Card("S4"); 

	    game.deck.addCard(card_3);
	    game.deck.addCard(card_4);
	    game.deck.addCard(card_5);
	    game.deck.addCard(card_6);
	    game.deck.addCard(card_7);
	    game.deck.addCard(card_8);

	    game.deck.reverseDeck();
	    game.player.getHand().addCard(card_1);
	    game.player.getHand().addCard(card_2);
	    
	    game.dealer.getHand().addCard(card_2);
	    game.dealer.getHand().addCard(card_3);
		game.playerSplit();
		
		game.player.hit(game.player.getHand(), game.deck); 
		game.player.hit(game.player.getSplitHand(), game.deck); 
		game.dealer.hitOrStand(game.dealer.getHand(), game.deck); 

		assertTrue(game.checkWinner());
		assertTrue(game.dealerIsWinner); 
		
	}
}
