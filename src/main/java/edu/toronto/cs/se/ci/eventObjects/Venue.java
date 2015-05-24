package edu.toronto.cs.se.ci.eventObjects;

import edu.toronto.cs.se.ci.playground.data.Address;
import edu.toronto.cs.se.ci.realWorldObjects.Establishment;

public class Venue implements Establishment {

	private String name;
	private Address address;
	private String description;
	
	public int capacity;
	
	public Venue(String name, Address address, String description) {
		this.name = name;
		this.address = address;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

}
