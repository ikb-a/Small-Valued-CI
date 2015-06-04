import static org.junit.Assert.*;

import org.junit.Test;

import edu.toronto.cs.se.ci.eventObjects.Venue;
import edu.toronto.cs.se.ci.eventObjects.Address;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.eventSources.GMapsEstablishmentHasAddress;


public class TestGMapsEstHasAddr {

	private GMapsEstablishmentHasAddress gMaps;
	
	public TestGMapsEstHasAddr() {
		gMaps = new GMapsEstablishmentHasAddress();
	}
	
	@Test
	public void realCases(){

		Event event;
		Address address;
		Venue venue;
		int result;
		
		address = new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4");
		venue = new Venue("Bahen Centre for Information Technology", address);
		event = new Event("Real test 1", "", "real test", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 0);
		
		address = new Address("40", "Bay St", "Toronto", "ON", "Canada", "M5J 2X2");
		venue = new Venue("Air Canada Centre", address);
		event = new Event("Real test 2", "", "real test", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 0);
		
		address = new Address("529", "BLOOR STREET W","Toronto", "ON", "Canada", "M5S 1Y4");
		venue = new Venue("Lee's Palace", address);
		event = new Event("Real test 3", "", "real test", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 0);
	}
	
	@Test
	/**
	 * Real address and real venues but not matching
	 */
	public void wrongAddress(){
		
		Event event;
		Address address;
		Venue venue;
		int result;
		
		address = new Address("854", "Yonge Street", "Toronto", "Ontario", "Canada", "M4W 2J1");
		venue = new Venue("Bahen Centre for Information Technology", address);
		event = new Event("Wrong address test 1", "", "wrong address", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 1);
		
		address = new Address("87", "Holland River Blvd.", "East Gwillimbury", "ON", "Canada", "L9N 1C3");
		venue = new Venue("Air Canada Centre", address);
		event = new Event("Wrong address test 2", "", "wrong address", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 1);
		
		address = new Address("288", "Prospect Street","Newmarket", "ON", "Canada", "L3Y 3V3");
		venue = new Venue("Lee's Palace", address);
		event = new Event("Wrong address test 3", "", "wrong address", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 1);
	}

	@Test
	public void testRandomEnglish(){
		
		Event event;
		Address address;
		Venue venue;
		int result;
		
		address = new Address("83634", "random words", "these", "are", "words", "G0Y 6J9");
		venue = new Venue("red leadership dragon manage volume", address);
		event = new Event("random English test 1", "", "random english words", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 1);
		
		address = new Address("83634", "another random", "test", "refigerator", "wow", "G0Y 6J9");
		venue = new Venue("airplane sesame guava inversion hotter", address);
		event = new Event("random English test 2", "", "random english words", venue, null, null);
		result = gMaps.getResponse(event);
		assertNotEquals(result, 1);
		
	}
	
}
