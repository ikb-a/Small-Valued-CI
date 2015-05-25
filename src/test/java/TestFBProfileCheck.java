import static org.junit.Assert.*;

import org.junit.Test;

import edu.toronto.cs.se.ci.sources.FaceBookProfileCheck;


public class TestFBProfileCheck {

	static FaceBookProfileCheck fb;
	
	public TestFBProfileCheck(){
		fb = new FaceBookProfileCheck();
		fb.setNumResultsToCheck(5);
	}
	
	@Test
	public void testRealPeople() {
	
		String [] names = {"William Ginsberg", "Taylor Stinson", "Carina Conceicao", "Jesse Berlin", "Carter Tinney"};
		
		for (int i = 0; i < names.length; i++){
			int result;
			result = fb.getResponse(names[i]);
			//assertNotEquals(result, 0);
			assertFalse(result == 0);
		}
		
	}

	@Test
	public void testGibberish() {
	
		String [] names = {"123", "abc", "qwertyuiop", "asdasdasd"};
		
		for (int i = 0; i < names.length; i++){
			int result;
			result = fb.getResponse(names[i]);
			assertNotEquals(result, 1);
		}
	}
	
	@Test
	public void testCharacters() {
	
		String [] names = {"Homer Simpson", "Sterling Archer", "Jeff Winger", "Santa Claus"};
		
		for (int i = 0; i < names.length; i++){
			int result;
			result = fb.getResponse(names[i]);
			assertNotEquals(result, 1);
		}
	}
	
}
