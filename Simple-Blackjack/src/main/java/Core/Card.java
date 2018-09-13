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
	
	      
		char extractValue; 
		String extractValueS; 
		int value = 0 ; 
		extractValue = input.charAt(1); 
		extractValueS = input.substring(1); 
		//if(extractValue == 'A') throw new IllegalArgumentException("check");
		
		//value = char.parseInt((String.valueOf(input)));
		
		
		
		switch(extractValueS) {
			case "A": value = 1; break; 
			case "2": value = 2; break; 
			case "3": value = 3; break; 
			case "4": value = 4; break; 
			case "5": value = 5; break; 
			case "6": value = 6; break; 
			case "7": value = 7; break; 
			case "8": value = 8; break; 
			case "9": value = 9; break; 
			case "10": value = 10; break; 
			case "J": value = 10; break; 
			case "Q": value = 10; break; 
			case "K": value = 10; break; 
			default: throw new IllegalArgumentException("Invalid input");			
			//or i can parse the char for values 1-10,  but check if it a AJQK
		}
		return value; 
	}
	
	public int ChangeValue(Card card, boolean aceIsSoft) {
		
		card.aceIsSoft = aceIsSoft;
		card.value = 11;
		
		return card.value; 
	}
	
	public boolean SetVisibility(boolean isVisible) {
		return this.cardIsVisible = isVisible; 
	}
	
}
