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

    public int GetSize() {
    	return hand.size(); 
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
		int temp = 0; 
		
		if(numPoints > 21)
		{
			if(numAces > 0)
			{
		    	for(int i = 0; i < hand.size(); i++) {
		    		if(this.hand.get(i).getValue() == 11) {
		    			temp = hand.get(i).ChangeValue(true);
			    		//System.out.println("Ace is now: " + temp);
		    		}
		    	}
				numPoints = CountTotal();
				System.out.println(numPoints +" recount");

			}
			
			if(numPoints > 21)
				return true; 
		}
		return false;
    }
    public boolean isSplit() {
    	if(this.GetCard(0).getValue() == this.GetCard(1).getValue())
    		return true;
    	
    	return false;
    }
    
    public Card split() {
    	return hand.remove(1);
    }
    
    public boolean IsBlackJack() {
//		int numAces ; 
		int numPoints; 
		
	//	numAces = CountAces(); 
		numPoints = CountTotal();
		
		//System.out.println(CountTotal() +" ctotal");
		if(numPoints == 21)
		{
			return true; 
		}
		return false;
    }

	public boolean isSoft() {
		// TODO Auto-generated method stub
		int numPoints = CountTotal();
		int numAces = CountAces(); 
		
		if(numPoints == 17 & numAces > 0)
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
