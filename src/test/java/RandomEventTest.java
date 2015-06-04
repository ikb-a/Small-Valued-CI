import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.random.EventObjectRandomizer;
import edu.toronto.cs.se.ci.random.GibberishEventGenerator;

public class RandomEventTest {

	EventObjectRandomizer r;
	
	@Before
	public void setUp() throws Exception {
		r = new EventObjectRandomizer(new GibberishEventGenerator());
	}

	@Test
	public void testEventEvent() {

		Event event = r.event();
		
		assertNotNull(event);
		assertNotNull(event.getTitle());
		assertNotNull(event.getUrl());
		assertNotNull(event.getDescription());

		assertNotNull(event.getOrganizer());
		assertNotNull(event.getOrganizer().getName());

		assertNotNull(event.getOrganizer().getContactInfo());
		assertNotNull(event.getOrganizer().getContactInfo().getEmailAddress());
		assertNotNull(event.getOrganizer().getContactInfo().getFacebookURL());
		assertNotNull(event.getOrganizer().getContactInfo().getPhoneNumber());
		assertNotNull(event.getOrganizer().getContactInfo().getTwitterHandle());
		assertNotNull(event.getOrganizer().getContactInfo().getTwitterURL());
		assertNotNull(event.getOrganizer().getContactInfo().getWebsiteURL());
		
		assertNotNull(event.getVenue());
		assertNotNull(event.getVenue().getName());
		
		assertNotNull(event.getVenue().getAddress());
		assertNotNull(event.getVenue().getAddress().getCity());
		assertNotNull(event.getVenue().getAddress().getCountry());
		assertNotNull(event.getVenue().getAddress().getPostalCode());
		assertNotNull(event.getVenue().getAddress().getProvince());
		assertNotNull(event.getVenue().getAddress().getRoute());
		assertNotNull(event.getVenue().getAddress().getStreetNumber());
		
		assertNotNull(event.getTime());
		assertNotNull(event.getTime().getStartDate());
		assertNotNull(event.getTime().getEndDate());
		
		
		
	}

}
