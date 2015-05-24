import static org.junit.Assert.*;

import org.junit.Test;

import edu.toronto.cs.se.ci.eventObjects.Venue;
import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.sources.GMapsEstablishmentHasAddress;


public class TestGMapsEstHasAddr {

	private GMapsEstablishmentHasAddress gMaps;
	
	public TestGMapsEstHasAddr() {
		gMaps = new GMapsEstablishmentHasAddress();
	}
	
	@Test
	public void realCases(){
		
		Address address;
		Venue venue;
		int result;
		
		address = new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4");
		venue = new Venue("Bahen Centre for Information Technology", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 0);
		
		address = new Address("40", "Bay St", "Toronto", "ON", "Canada", "M5J 2X2");
		venue = new Venue("Air Canada Centre", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 0);
		
		address = new Address("529", "BLOOR STREET W","Toronto", "ON", "Canada", "M5S 1Y4");
		venue = new Venue("Lee's Palace", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 0);
	}
	
	@Test
	/**
	 * Real address and real venues but not matching
	 */
	public void wrongAddress(){
		
		Address address;
		Venue venue;
		int result;
		
		address = new Address("854", "Yonge Street", "Toronto", "Ontario", "Canada", "M4W 2J1");
		venue = new Venue("Bahen Centre for Information Technology", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 1);
		
		address = new Address("87", "Holland River Blvd.", "East Gwillimbury", "ON", "Canada", "L9N 1C3");
		venue = new Venue("Air Canada Centre", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 1);
		
		address = new Address("288", "Prospect Street","Newmarket", "ON", "Canada", "L3Y 3V3");
		venue = new Venue("Lee's Palace", address, null);
		result = gMaps.getResponse(venue);
		assertNotEquals(result, 1);
	}

}
