package Core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.application.Application;


public class Main {
	
	private static final Logger logger = Logger.getLogger("Main");

	static int DECK_SIZE = 52; 

	
	
	private static GameMaster game = new GameMaster(); 
	private static BlackjackGUI gui = new BlackjackGUI();
	
	public static void main(String[] arg) {
		logger.info("Game is about to start");
        //Application.launch(BlackjackGUI.class, arg);

		PromptUser();

	}

	


	public static void PromptUser () {
		System.out.println("Which input method are you using? (File = f or Console = c)");
		Scanner console = new Scanner(System.in); 
		char input = console.next().charAt(0);
				
		if(input == 'c') 
			ConsoleInput(); 
		else if (input == 'f')
			FileInput();
		else {
			System.out.println("Please enter a valid input method!");
		
			while(input!='c' | input !='f') {
				System.out.println("Which input method are you using? (File = f or Console = c)");
				console = new Scanner(System.in); 
				input = console.next().charAt(0);
				if(input == 'c') {
					ConsoleInput();
					break;
				}
				else if (input == 'f') {
					FileInput();
					break;
				}
				else
					System.out.println("Please enter a valid input method!");
			}
		}
			
		console.close();
	}
	
	public static void ConsoleInput() {
		System.out.println("ConsoleInput");
		
		game.consoleInput(); 

	}
	

	
	
	public static void FileInput() {
		
		String[] parseCommands = new String[DECK_SIZE ]; 
		parseCommands  = readFile(); 
		System.out.println("FileInput");
		
		game.fileInput(parseCommands);
	}
	//code inspired from:
		//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
	private static String[] readFile() {
	// TODO Auto-generated method stub
		System.out.println("Enter the filename: ");
		Scanner console = new Scanner(System.in); 
		String fileName = console.nextLine();
		String line = null; 
		BufferedReader reader = null; 
		String[] parseCommands = new String[DECK_SIZE]; 
		String delims = "[ ]+";
		//String filename = "File5.txt"; 
			

		try {
			FileReader fileReader =  new FileReader(fileName); 
		    BufferedReader bufferedReader = new BufferedReader(fileReader); 
				    
			while((line = bufferedReader.readLine()) != null){
				parseCommands = line.split(delims); 
			    System.out.println(line);

			}
			bufferedReader.close();
		console.close();

		}
		catch(FileNotFoundException ex) {
			System.out.println(  "Unable to open file '" +   fileName + "'"); 
		}
		catch(IOException ex) {
			System.out.println( "Error reading file '"  + fileName + "'");                  
		}		
		for (int i = 0; i < parseCommands.length; i++)
			System.out.println(parseCommands[i]);
				
		return parseCommands;
	}

}
