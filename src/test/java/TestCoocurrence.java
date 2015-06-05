import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.se.ci.sources.GoogleCoocurrence;
import edu.toronto.cs.se.ci.sources.SourceFactory;


public class TestCoocurrence {
	
	GoogleCoocurrence coocurrence;
	
	@Before
	public void setup() {
		coocurrence = (GoogleCoocurrence) SourceFactory.getSource(GoogleCoocurrence.class);
	}
	
	@Test
	public void testRealCase() {
		
		String [] search = {"iPad", "Apple"};
		int result = coocurrence.getResponse(search);
		assertNotEquals(result, 0);
		
		String [] search2 = {"uoft", "University of Toronto"};
		result = coocurrence.getResponse(search2);
		assertNotEquals(result, 0);
	}
	
	@Test
	public void testNegativeCase(){
		
		String [] search = {"McDonalds", "Dell"};
		int result = coocurrence.getResponse(search);
		assertNotEquals(result, 1);

		String [] search2 = {"archer netflix", "danny brown"};
		result = coocurrence.getResponse(search2);
		assertNotEquals(result, 1);
	}
	
	

}
