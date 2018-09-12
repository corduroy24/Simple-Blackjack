package Core;

public class Card {

	private int value; 
	private String input; 
	private boolean aceIsSoft = false; 
	private boolean cardIsVisible = true;
	
	public Card(String input) {
		this.input = input; 
		//this.aceIsSoft = aceIsSoft; 
		this.value = SetValue(input); 
	}
	
	public int getValue() {
		
		return this.value; 
	}
	
	private int SetValue(String input) {
		
		return value; 
	}
	
	public int ChangeValue(Card card, boolean aceIsSoft) {
		
		return value; 
	}
	
	public boolean SetVisibility(boolean isVisible) {
		return this.cardIsVisible = isVisible; 
	}
	
}
