import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import edu.toronto.cs.se.ci.random.EnglishEventGenerator;


public class TestEnglishEventGenerator {

	EnglishEventGenerator generator;
	
	public TestEnglishEventGenerator() throws IOException{
		generator = new EnglishEventGenerator();
	}
	
	@Test
	public void testWord() {
		
		//test 5 random words
		String [] words = new String [5];
		for (int i = 0; i < words.length; i++){
			String word = generator.word();
			//check the word is not empty or containing newline
			assertTrue(word.length() > 0);
			assertFalse(word.contains("\n"));
			words[i] = word;
		}
		
		//check the words weren't all the same
		assertFalse(words[0] == words[1] &&
				words[1] == words[2] &&
				words[2] == words[3] &&
				words[3] == words[4]);
	}
	@Test
	public void testEmail(){
		
		String email = generator.randomEmail();
		assertTrue(email.length() > 0);
		assertTrue(email.lastIndexOf(" ") == -1);
	}
	
}
