package Core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

public class GameTest extends TestCase {
	
	public void drawCardTest() {
		
		
	}
	
	//input mode test
	
	//player turn 
	
	//draw 2 cards 
		//2 each for player and dealer
		//check card visibility 
			//both visible for player 
			//one/two visible for the dealer 
	
	//player gets to continuously choose hit or stand. Ended by stand 
		//if stand then their turn ends 
	
	
	//enforce dealer rules 
	
	//isGameWinner test
	
		//console input deck test
		//random deck 
	
	public void consoleInputDeckTest(){
		//boolean [] deckIsShuffled = new boolean[52]; 
		boolean isShuffled = false; 
		
		/*for(int i = 0; i < deckIsShuffled.length;i++) {
			deckIsShuffled[i] = false; 
		}*/
		
	    List<Integer> deck = new ArrayList<Integer>();
	    List<Integer> deckCopy = new ArrayList<Integer>();
	
	
	    for(int i = 0; i < deck.size(); i++){
	       deck.add(i);
	       deckCopy.add(i); 
	    }
	    assertFalse(isShuffled);
	
	    Collections.shuffle(deck);
	    
	    assertTrue(isShuffled);
	    assertEquals(52, deck.size());
	    
	    //isShuffled = true; 
	    
	    /*for(int i = 0; i < deck.size();i++) {
	    	if(deck.indexOf(i) != deckCopy.indexOf(i))
	    		deckIsShuffled[i] = true; 
	    }
	    
	    for(int i = 0; i < deck.size();i++) {
	    	if(deck.indexOf(i) != deckCopy.indexOf(i))
	    		deckIsShuffled[i] = true; 
	    }
	    
	    while(isShuffled == false) {
	        for(int i = 0; i < deck.size();i++) {
		        if(deckIsShuffled[i] == false) {
		        		deckIsShuffled[i] = true; 
		        		i = 0; 
		        }	
		        else
		        	isShuffled = true; 
	        }
	    }*/
	    
	}
	
	//file input deck test 
	
	public void fileInputDeckTest() {
		
	}
	
}
