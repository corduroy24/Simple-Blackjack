package Core;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import junit.framework.TestCase; 


public class CardTest extends TestCase{

	//visible card test
	
	//card value test

	public void testCardValue() {
		//String [] input = new String [15]; 
		
		/*String [] input = {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
				"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
				"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "CH", "HK",
				"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
		}; */
		
		boolean aceIsSoft = false; 
		
		Card CA = new Card("CA"); 
		Card D2 = new Card("D2"); 
		Card D10 = new Card("D10"); 
		Card H7 = new Card("H7"); 
		Card SQ = new Card("SQ"); 

		assertEquals(1,CA.getValue());
		assertEquals(2,D2.getValue());
		assertEquals(10,D10.getValue());
		assertEquals(7,H7.getValue());
		assertEquals(10,SQ.getValue());
		
		aceIsSoft = true; 
		
		//CA.ChangeValue(CA, aceIsSoft); 
		
		//assertEquals(11, CA.getValue()); 
		
	}
	
		public void testCardVisibility () {
			
		}
}
