package Core;

public class Card {

	private int value; 
	private String input; 
	private boolean aceIsSoft = false; 
	private boolean cardIsVisible = false;
	
	public Card(String input) {
		this.input = input; 
		//this.aceIsSoft = aceIsSoft; 
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
	
	      
		char extractValue; 
		String extractValueS; 
		int value = 0 ; 
		extractValue = input.charAt(1); 
		extractValueS = input.substring(1); 
		//if(extractValue == 'A') throw new IllegalArgumentException("check");
		
		//value = char.parseInt((String.valueOf(input)));
		
		
		//System.out.println(extractValueS);
		switch(extractValueS) {
			case "A": value = 11; break; 
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
}
