package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 


public class DeckTest extends TestCase{
	
    

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
		//read from a file 
	public void fileInputDeckTest() {
		
	}
}
