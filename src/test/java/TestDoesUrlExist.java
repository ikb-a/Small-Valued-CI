
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.se.ci.sources.DoesURLExist;
import edu.toronto.cs.se.ci.sources.SourceFactory;


public class TestDoesUrlExist {

	DoesURLExist urlChecker;
	
	@Before
	public void setUp() throws Exception {
		urlChecker = (DoesURLExist) SourceFactory.getSource(DoesURLExist.class);
	}
	
	@Test
	public void testRealURLs() {
		
		String [] urls = {"http://web.cs.toronto.edu/research/areas/se.htm",
				"http://docs.oracle.com/javase/7/docs/api/java/net/HttpURLConnection.html",
				"https://github.com/wginsberg/Small-Valued-CI",
				"http://jsonformatter.curiousconcept.com/",
				"http://jsonformatter.curiousconcept.com/"
		};
		
		int response;
		String url;
		for (int i =0; i < urls.length; i++){
			url = urls[i];
			response = urlChecker.getResponse(url);
			assertTrue(response != 0);
		}
	}
	
	@Test
	public void testMalformedURLs() {
		
		String [] urls = {"asdasdasdasdasd",
				"swag.swag.swag.swag.swag.swag",
				"",
				"htt.,456.,ps://,.,gith45654ub.456.com.,/wginsb67967erg/Sm4564all-=-09876Val;;`ued-CI",
				"http://\"o zwKrr D/",
				"http://e\"j zfossbeyowNiwA /",
				"http://x\"nabgw hohbkDyvv /"
		};
		
		int response;
		String url;
		for (int i =0; i < urls.length; i++){
			url = urls[i];
			response = urlChecker.getResponse(url);
			assertTrue(response != 1);
		}
	}

	@Test
	public void testInvalidURLs() {
		
		String [] urls = {"http://www.google.com/willginsberg",
				"https://en.wikipedia.org/wiki/Collaborative_Implementation"
		};
		
		int response;
		String url;
		for (int i =0; i < urls.length; i++){
			url = urls[i];
			response = urlChecker.getResponse(url);
			assertTrue(response != 1);
		}
		
	}
	
}
