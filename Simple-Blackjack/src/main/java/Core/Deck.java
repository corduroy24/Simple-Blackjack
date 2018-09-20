package Core;


import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import javafx.scene.image.Image;

public class Deck {
	
    private List<Card> deck = new ArrayList<Card>();

	
	static int deckSize = 52; 
    
    public void addCard(Card cardToAdd)
    {
        deck.add((cardToAdd)); //we are going to add each card with a loop, in another class 
    }

    public void addCards(List<Card> cardsToAdd) //adds a list of cards to the deck i.e, game setup, 8 weapons cards
    {
        deck.addAll((cardsToAdd)); 
        //for (int i = 0; i < cardsToAdd.Count; i++) ;
    }
    
    public Card drawCard() //removes the card from the story deck, and returns the top of the deck (last card in the list)
    {
        Card playCard = deck.get(deck.size()-1);
        this.deck.remove(deck.get(deck.size()-1)); //removes by 1st occurence of obejct 
        //this.deck.remove((deck.size()-1)); // removes by index 
        return playCard; 
    }


    public int sizeOfDeck() //for testing purposes only
    {
        return this.deck.size();
    }

    public boolean empty() //return 1 if empty = true , 0 if it has cards = false 
    {
            return deck.isEmpty(); 
    }
    
    public void reverseDeck() {
    	Collections.reverse(deck);
    }
    
    public Card getCard(int index) {
    	return deck.get(index); 
    }
    //do tests for correct number of each card and total number in deck 
    /*public void defaultDeck() {
		for(int i = 0; i < deckSize; i++) {
			AddCard(input[i]);
    }*/
    
    public boolean findCard(Card card) {
    	return deck.contains(card); 
    }
    
    public void shuffleDeck() {
    	Collections.shuffle(deck);
    }
    
    @SuppressWarnings("restriction")
	public void createDefaultDeck(String[] input) {
    	//Card [] Card = new Card[deckSize];
    	Card card; 
    	for(int i = 0; i < input.length; i++) {
			card = new Card (input[i], new Image(Card.getFileName(input[i])));
			addCard(card);
    	}
    }
    
    /*public Card[] createCards(String[] input, Card[] cards) {
		for(int i = 0; i < deckSize; i++) {
			cards[i] = new Card (input[i]);
		}
		return cards;
    }*/
    
    public Card createCard(String input, Card card) {
			card = new Card (input);

		return card;
    }

}
