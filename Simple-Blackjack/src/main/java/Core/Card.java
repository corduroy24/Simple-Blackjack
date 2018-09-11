package Core;

public class Card {

	private int value; 
	private String name; 
	private String suite; 
	private boolean ace; 
	
	public Card(String name, String suite) {
		this.name = name; 
		this.suite = suite; 
		this.value = determineCardValue(name); 
	}
	
	public int getValue(int playerTotal) {
		
		return this.value; 
	}
	
	private int determineCardValue(String name) {
		
		return value; 
	}
}
