package Core;

public class Card {

	private int value; 
	private String input; 
	private boolean aceIsSoft = false; 
	private boolean cardIsVisible = false;
	private String name;
	private String suit;
	private String rank;
	
	public Card(String input) {
		this.input = input; 
		this.value = SetValue(input); 
		this.cardIsVisible = false;
	}
	
	public int getValue() {
		
		return this.value; 
	}
	
	public String getInput() {
		
		return this.input; 
	}
	
	private int SetValue(String input) {
	
		//String extractValueRank= null; 
		char extractValueSuit; 
		String extractValueS= null; 
		extractValueS = input.substring(1); 
		extractValueSuit = input.charAt(0); 
		int value = 0 ; 
		extractValueS = input.substring(1); 
		
		//if(extractValue == 'A') throw new IllegalArgumentException("check");
		
		//value = char.parseInt((String.valueOf(input)));
		switch(extractValueS) {
			case "A": value = 11; rank = "Ace"; break; 
			case "2": value = 2; rank = "2";break; 
			case "3": value = 3; rank = "3";break; 
			case "4": value = 4; rank = "4";break; 
			case "5": value = 5; rank = "5";break; 
			case "6": value = 6; rank = "6";break; 
			case "7": value = 7; rank = "7";break; 
			case "8": value = 8; rank = "8"; break; 
			case "9": value = 9; rank = "9";break; 
			case "10": value = 10; rank = "10";break; 
			case "J": value = 10; rank = "Jack";break; 
			case "Q": value = 10; rank = "Queen";break; 
			case "K": value = 10; rank = "King"; break; 
			default: throw new IllegalArgumentException("Invalid input");			
			//or i can parse the char for values 1-10,  but check if it a AJQK
		}
		LetterToWordSuit(extractValueSuit);

		return value; 
	}
	
	public int ChangeValue(boolean aceIsSoft) {
		
		this.aceIsSoft = aceIsSoft;
		
		if(aceIsSoft == true)
			value = 1;
		else
			value = 11; 
		
		return this.value; 
	}
	
	public void SetVisibility(boolean isVisible) {
		 this.cardIsVisible = isVisible; 
	}
	
	public boolean getVisibility() {
		return cardIsVisible; 
	}
	
	public void LetterToWordSuit(char input) {


		if(input == 'S') {						
			this.suit = "Spades";
		}
		else if(input == 'H') {
			this.suit = "Hearts";

		}
		else if(input == 'C') {
			this.suit = "Clovers";

		}
		else if(input == 'D') {
			this.suit = "Diamonds";
			
		}
		//System.out.println(suit);
		
		nameCards();
	}

	private void nameCards() {
		// TODO Auto-generated method stub
		this.name = this.rank + " of " + this.suit; 
	}
	public String getName() {
		return this.name; 
	}
}
