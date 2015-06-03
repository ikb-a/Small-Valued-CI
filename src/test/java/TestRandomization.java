import static org.junit.Assert.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.se.si.random.Randomization;


public class TestRandomization {

	Randomization r;
	
	@Before
	public void setUp() throws Exception {
		r = new Randomization();
	}

	@Test
	public void testCharacter() {
		r.character();
	}

	@Test
	public void testString() {
		
		String s = r.string(0, 1);
		assert(s.length() == 0 || s.length() == 1);
		
		s = r.string(3, 7);
		assertTrue(s.length() >= 3);
		assertTrue(s.length() <= 7);
		
		s = r.string(100, 500);
		assertTrue(s.length() >= 100);
		assertTrue(s.length() <= 500);
		
	}

	@Test
	public void testIntegerInt() {
	
		assert(r.integer(0) == 0);

		assert(r.integer(5) <= 5);

		assert(r.integer(1) >= 0);
	}

	@Test
	public void testIntegerIntInt() {
		
		int i = r.integer(10, 560);
		assert(i >= 10);
		assert(i <= 560);
	}

	@Test
	public void testPhoneNumberString() {
		
		String phone = r.phoneNumber();
		Pattern pattern = Pattern.compile("[1-9][0-9][0-9]-[1-9][0-9][0-9]-[1-9][0-9][0-9][0-9]");
		Matcher matcher = pattern.matcher(phone);
		
		assert(matcher.matches());
	}

	@Test
	public void testPhoneNumber() {
		
		//test a 416 area code number
		String phone = r.phoneNumber("416");
		Pattern pattern = Pattern.compile("416-[1-9][0-9][0-9]-[1-9][0-9][0-9][0-9]");
		Matcher matcher = pattern.matcher(phone);
		
		assert(matcher.matches());
	}

	@Test
	public void testEmailAddressString() {
		String email = r.emailAddress();
		Pattern pattern = Pattern.compile(".*@*\\..*");
		Matcher matcher = pattern.matcher(email);
		
		assert(matcher.matches());
	}

	@Test
	public void testEmailAddress() {

		//test with a cs.toronto.edu email
		String email = r.emailAddress("cs.toronto.edu");
		Pattern pattern = Pattern.compile(".*@cs.toronto.edu");
		Matcher matcher = pattern.matcher(email);
		
		assert(matcher.matches());
	}

	@Test
	public void testDomain() {
		String domain = r.domain();
		Pattern pattern = Pattern.compile(".*\\..*");
		Matcher matcher = pattern.matcher(domain);
		assert(matcher.matches());
	}
}
