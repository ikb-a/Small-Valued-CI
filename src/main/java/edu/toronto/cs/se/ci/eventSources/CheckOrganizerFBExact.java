package edu.toronto.cs.se.ci.eventSources;

import edu.toronto.cs.se.ci.sources.FBProfileExactlyExists;

public class CheckOrganizerFBExact extends CheckOrganizerFB {

	public CheckOrganizerFBExact(){
		facebook = new FBProfileExactlyExists();
	}
	
	@Override
	public String getName(){
		return "Organization name (exactly) is on facebook";
	}
	
}
