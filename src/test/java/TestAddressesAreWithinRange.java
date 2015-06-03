import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.javadocmd.simplelatlng.util.LengthUnit;

import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.sources.AddressesAreWithinRange;


public class TestAddressesAreWithinRange {

	private AddressesAreWithinRange source;
	
	@Before
	public void setup(){
		source = new AddressesAreWithinRange(0, 1000);
	}
	
	@Test
	public void positiveCase() {

		source.setLengthUnit(LengthUnit.METER);
		source.setMinDistance(0);
		source.setMaxDistance(1000);
		
		ArrayList<Address> addresses = new ArrayList<Address>(2);
		addresses.add(new Address("75", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E5"));
		addresses.add(new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4"));
		
		int result = source.getResponse(addresses);
		
		assertFalse(result == 0);
	}

	@Test
	public void testTooFarApart() {

		source.setLengthUnit(LengthUnit.METER);
		source.setMinDistance(0);
		source.setMaxDistance(500);
		
		ArrayList<Address> addresses = new ArrayList<Address>(2);
		addresses.add(new Address("9580", "Jane St", "Vaughan", "ON", "Canada", "L6A 1S6"));
		addresses.add(new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4"));
		
		int result = source.getResponse(addresses);
		
		assertFalse(result == 1);
	}
	
	@Test
	public void testTooClose() {

		source.setLengthUnit(LengthUnit.METER);
		source.setMinDistance(1000);
		source.setMaxDistance(5000);
		
		ArrayList<Address> addresses = new ArrayList<Address>(2);
		addresses.add(new Address("75", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E5"));
		addresses.add(new Address("40", "St George Street", "Toronto", "Ontario", "Canada", "M5S 2E4"));
		
		int result = source.getResponse(addresses);
		
		assertFalse(result == 1);
	}
	
}
