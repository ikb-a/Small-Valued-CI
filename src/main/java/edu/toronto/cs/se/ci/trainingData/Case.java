package edu.toronto.cs.se.ci.trainingData;

import edu.toronto.cs.se.ci.eventObjects.*;

/**
 * A training case, or a test case can be held in an instance of Case
 */
public class Case {

	public Event event;
	public int classification;
	
	public Case(Event event, int classification){
		this.event = event;
		this.classification = classification;
	}
	
}
