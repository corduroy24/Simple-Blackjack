package Core;


import java.util.ArrayList;

import java.util.List;
import java.util.Collections;

public class Deck {
	
    private List<Card> deck = new ArrayList<Card>();
    
    
    public void AddCard(Card cardToAdd)
    {
        deck.add((cardToAdd)); //we are going to add each card with a loop, in another class 
    }

    public void AddCards(List<Card> cardsToAdd) //adds a list of cards to the deck i.e, game setup, 8 weapons cards
    {
        deck.addAll((cardsToAdd)); 
        //for (int i = 0; i < cardsToAdd.Count; i++) ;
    }
    
    public Card DrawCard() //removes the card from the story deck, and returns the top of the deck (last card in the list)
    {
        Card playCard = deck.get(deck.size()-1);
        this.deck.remove(deck.get(deck.size()-1)); //removes by 1st occurence of obejct 
        //this.deck.remove((deck.size()-1)); // removes by index 
        return playCard; 
    }


    public int SizeOfDeck() //for testing purposes only
    {
        return this.deck.size();
    }

    public boolean Empty() //return 1 if empty = true , 0 if it has cards = false 
    {
            return deck.isEmpty(); 
    }
    
    public Card topCard(int index) {
    	return deck.get(index); 
    }

}
