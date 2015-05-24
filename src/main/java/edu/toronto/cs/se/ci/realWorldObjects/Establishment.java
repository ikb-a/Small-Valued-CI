package edu.toronto.cs.se.ci.realWorldObjects;

import edu.toronto.cs.se.ci.playground.data.Address;

public interface Establishment {

	public String getName();

	public abstract void setName(String name);

	public abstract Address getAddress();

	public abstract void setAddress(Address address);
}
