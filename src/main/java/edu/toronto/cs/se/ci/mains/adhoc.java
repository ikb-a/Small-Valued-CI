package edu.toronto.cs.se.ci.mains;

import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.sources.GMapsEstablishmentHasAddress;
import edu.toronto.cs.se.ci.eventObjects.Venue;

public class adhoc {

	private static GMapsEstablishmentHasAddress gMaps;
	
	private static Address address1 = new Address("51", "Baldwin Street", "Toronto", "Ontario", "Canada", "M5T 1L1");
	private static Address address2 = new Address("52", "Holland River Blvd.", "East Gwillimbury", "Ontario", "Canada", "L9N 1C3");
	private static Address address3 = new Address("8725", "Yonge St", "Richmond Hill", "ON", "Canada", "L4C 6Z1");
	private static Address address4 = new Address("235", "Queens Quay W", "Toronto", "ON", "canada", "M5J 2G8");
	private static Address address5 = new Address("123", "Fake Street", "Toronto", "ON", "canada", "M5J 2G8");
	
	private static Venue venue1;
	private static Venue venue2;
	private static Venue venue3;
	
	private static Venue fakeVenue1;
	private static Venue fakeVenue2;
	private static Venue fakeVenue3;
	private static Venue fakeVenue4;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		gMaps = new GMapsEstablishmentHasAddress();
		
		venue1 = new Venue("Kinton Ramen", address1, null);
		venue2 = new Venue("SilverCity Richmond Hill", address3, null);
		venue3 = new Venue("Harbourfront Centre", address4, null);
		
		fakeVenue1 = new Venue("Kinton Ramen", address2, null);
		fakeVenue2 = new Venue("SilverCity Richmond Hill", address2, null);
		fakeVenue3 = new Venue("Harbourfront Centre", address3, null);
		fakeVenue4 = new Venue("The Met", address5, null);
		
		System.out.println("Real venues --------------------\n");
		verify(venue1);
		verify(venue2);
		verify(venue3);

		System.out.println("\nFake venues --------------------\n");
		verify(fakeVenue1);
		verify(fakeVenue2);
		verify(fakeVenue3);
		verify(fakeVenue4);
		
	}
	
	private static void verify(Venue v){
		
		System.out.printf("Verifying venue : %s\nDoes it have the address : %s, %s\n", v.getName(), v.getAddress().getStreetNumber(), v.getAddress().getRoute());
		
		int answer;
		answer = gMaps.getResponse(v);
		
		System.out.println(answer);
	}

}
