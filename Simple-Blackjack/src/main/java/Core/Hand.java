package Core;


import java.util.ArrayList;

import java.util.List;
import java.util.Collections;

public class Hand {
    private List<Card> hand = new ArrayList<Card>();
    private boolean isSoft; 
    public int numAces = 0; 
    
    public void AddCard(Card cardToAdd)
    {
        hand.add((cardToAdd)); //we are going to add each card with a loop, in another class 
    }
    
    public Card GetCard(int index) {
    	return hand.get(index); 
    }
    
    
    public int CountTotal() {
    	int total  = 0; 
    	for(int i = 0; i < hand.size(); i++) {
    		
    		total += hand.get(i).getValue();
    	}
    	
    	return total; 
    }
    
    public boolean Empty() //return 1 if empty = true , 0 if it has cards = false 
    {
            return hand.isEmpty(); 
    }
    
    public int CountAces () {
    	int numAces = 0; 
    	for(int i = 0; i < hand.size(); i++) {
    		if(this.hand.get(i).getValue() == 11)
    			numAces++; 
    	}
    	return numAces; 
    }
    
    public boolean IsBust() {
		int numAces ; 
		int numPoints; 
		
		numAces = CountAces(); 
		numPoints = CountTotal();
		
		if(numPoints > 21)
		{
			numPoints = numPoints - (numAces*10); 
			return true; 
		}
		return false;
    }
    
    public boolean IsBlackJack() {
//		int numAces ; 
		int numPoints; 
		
	//	numAces = CountAces(); 
		numPoints = CountTotal();
		
		if(numPoints == 21)
		{
			return true; 
		}
		return false;
    }
    
    /*public boolean isSoft() {
    	if ((CountTotal() + 10) == 17)
    		this.isSoft = true; 
    }*/
}
