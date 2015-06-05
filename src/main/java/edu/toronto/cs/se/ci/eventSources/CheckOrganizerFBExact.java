package edu.toronto.cs.se.ci.eventSources;

import edu.toronto.cs.se.ci.sources.FBProfileExactlyExists;
import edu.toronto.cs.se.ci.sources.SourceFactory;

/**
 * Checks that the exact name of the organizer exists on facebook
 * @author wginsberg
 *
 */
public class CheckOrganizerFBExact extends CheckOrganizerFB {

	public CheckOrganizerFBExact(){
		//facebook = new FBProfileExactlyExists();
		facebook = (FBProfileExactlyExists) SourceFactory.getSource(FBProfileExactlyExists.class);
	}
	
	@Override
	public String getName(){
		return "Organization name (exactly) is on facebook";
	}
	
}
