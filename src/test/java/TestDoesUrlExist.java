
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.se.ci.sources.DoesURLExist;


public class TestDoesUrlExist {

	DoesURLExist urlChecker;
	
	@Before
	public void setUp() throws Exception {
		urlChecker = new DoesURLExist();
	}
	
	@Test
	public void testRealURLs() {
		
		String url = "http://web.cs.toronto.edu/research/areas/se.htm";
		assertTrue(urlChecker.getResponse(url) != 0);
		
		url = "http://docs.oracle.com/javase/7/docs/api/java/net/HttpURLConnection.html";
		assertTrue(urlChecker.getResponse(url) != 0);
		
		url = "https://github.com/wginsberg/Small-Valued-CI";
		assertTrue(urlChecker.getResponse(url) != 0);
		
		url = "http://jsonformatter.curiousconcept.com/";
		assertTrue(urlChecker.getResponse(url) != 0);
	}
	
	@Test
	public void testMalformedURLs() {
		
		String url = "asdasdasdasdasd";
		assertTrue(urlChecker.getResponse(url) != 1);
		
		url = "swag.swag.swag.swag.swag.swag";
		assertTrue(urlChecker.getResponse(url) != 0);
		
		url = "htt.,456.,ps://,.,gith45654ub.456.com.,/wginsb67967erg/Sm4564all-=-09876Val;;`ued-CI";
		assertTrue(urlChecker.getResponse(url) != 0);
		
		url = "";
		assertTrue(urlChecker.getResponse(url) != 0);
	}

	@Test
	public void testInvalidURLs() {
		
		String url = "http://www.google.com/willginsberg";
		assertTrue(urlChecker.getResponse(url) != 1);
		
		url = "https://en.wikipedia.org/wiki/Collaborative_Implementation";
		assertTrue(urlChecker.getResponse(url) != 0);
	}
	
}
