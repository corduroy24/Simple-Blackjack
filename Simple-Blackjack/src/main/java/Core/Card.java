package Core;

public class Card {

	private int value; 
	private String input; 
	private boolean aceIsSoft; 
	
	public Card(String input) {
		this.input = input; 
		this.value = determineCardValue(input); 
	}
	
	public int getValue() {
		
		return this.value; 
	}
	
	private int determineCardValue(String input) {
		
		return value; 
	}
}
