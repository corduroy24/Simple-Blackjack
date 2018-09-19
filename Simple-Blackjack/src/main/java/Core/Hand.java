package Core;


import java.util.ArrayList;

import java.util.List;
import java.util.Collections;

public class Hand {
    private List<Card> hand = new ArrayList<Card>();
    private boolean isSoft; 
    public int numAces = 0; 
    public boolean turn; 
    private String printHand = ""; 
    private String name; 
    
    public Hand(String name) {
    	this.name = name; 
    }
    public void addCard(Card cardToAdd)
    {
        hand.add((cardToAdd)); //we are going to add each card with a loop, in another class 
    }
    public String getName() {
    	return this.name; 
    }
    
    public Card getCard(int index) {
    	return hand.get(index); 
    }

    public int getSize() {
    	return hand.size(); 
    }
    
    
    
    public int countTotal() {
    	int total  = 0; 
    	for(int i = 0; i < hand.size(); i++) {
    		
    		total += hand.get(i).getValue();
    	}
    	
    	return total; 
    }
    
    public boolean empty() //return 1 if empty = true , 0 if it has cards = false 
    {
            return hand.isEmpty(); 
    }
    
    public int countAces () {
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
		
		numAces = countAces(); 
		numPoints = countTotal();
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
				numPoints = countTotal();
				System.out.println(numPoints +" recount");

			}
			
			if(numPoints > 21)
				return true; 
		}
		return false;
    }
    public boolean isSplit() {
    	if(this.getCard(0).getValue() == this.getCard(1).getValue())
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
		numPoints = countTotal();
		
		//System.out.println(CountTotal() +" ctotal");
		if(numPoints == 21)
		{
			return true; 
		}
		return false;
    }

	public boolean isSoft() {
		// TODO Auto-generated method stub
		int numPoints = countTotal();
		int numAces = countAces(); 
		
		if(numPoints == 17 & numAces > 0)
		{
			return true; 
		}
		return false; 
	}
	
	public void showHand() {
		for(int i = 0; i < getSize(); i++) {
			if(getCard(i).getVisibility())
				printHand += getCard(i).getInput() + " "; 
	}
	System.out.println(name + " Hand: "+ printHand);
	printHand = ""; 
		
	}
	public void showFirstCard() {
		if(getSize() == 2)
			getCard(1).SetVisibility(false); 
		
	}
    
    /*public boolean isSoft() {
    	if ((CountTotal() + 10) == 17)
    		this.isSoft = true; 
    }*/
}
