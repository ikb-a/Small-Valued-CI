import static org.junit.Assert.*;

import org.junit.Test;

import edu.toronto.cs.se.ci.helpers.MalformedInputException;
import edu.toronto.cs.se.ci.helpers.ParsePhoneNumber;


public class PhoneNumberParsing {

	@Test
	public void test() {
		
		String [] numbers = {"905 412 6901", "647-145 6341", "+1 523-636.7777"};
		String [] expectedResults = {"905", "647", "523"};
		String result;
		
		for (int i = 0; i < numbers.length; i++){
			try {
				result = ParsePhoneNumber.getAreaCode(numbers[i]);
				assertEquals(result, expectedResults[i]);
			} catch (MalformedInputException e) {
				fail();
			}
			
		}

		
	}

}
