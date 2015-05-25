import static org.junit.Assert.*;

import org.junit.Test;
import edu.toronto.cs.se.ci.sources.LinkedInProfileCheck;


public class TestLinkedIn {

	private LinkedInProfileCheck linkedIn;
	
	public TestLinkedIn(){
		linkedIn = new LinkedInProfileCheck();
	}
	
	@Test
	public void testRealPeople() {
		String [] names = {"William Ginsberg", "Rick Salay", "Marsha Chechik"};
		int result;
		for (int i = 0; i < names.length; i++){
			result = linkedIn.getResponse(names[i]);
			assertNotEquals(result, 0);
		}
	}

	@Test
	public void testFakePeople() {
		String [] names = {"Barney the Dinosaur", "Santa Claus", "Homer Simpson", "your mom"};
		int result;
		for (int i = 0; i < names.length; i++){
			result = linkedIn.getResponse(names[i]);
			assertNotEquals(result, 1);
		}
	}
	
	@Test
	public void testGibberish() {
		String [] names = {"123", "abc", "lalala", "asdasdasdasd"};
		int result;
		for (int i = 0; i < names.length; i++){
			result = linkedIn.getResponse(names[i]);
			assertNotEquals(result, 1);
		}
	}
	
}
