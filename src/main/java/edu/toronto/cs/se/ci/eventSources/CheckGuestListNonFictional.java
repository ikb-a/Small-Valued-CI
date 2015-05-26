package edu.toronto.cs.se.ci.eventSources;

import java.util.ArrayList;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.WikipediaNameisNotFictional;

public class CheckGuestListNonFictional extends EventSource {

	/**
	 * The minimum number of names to check
	 */
	public int minNamesToCheck = 1;
	/**
	 * The maximum number of names to check
	 */
	public int maxNamesToCheck = 10;
	/**
	 * How much of the guest list to check
	 */
	public double ratioGuestsToCheck = 0.25d;
	
	/**
	 * The percentage of guests who need to be verified to
	 * return a positive opinion
	 */
	public double successThreshold = 1.0;

	/**
	 * If more than this percentage of guests
	 * have an unknown result then the final result will
	 * be unknown, otherwise the known results will
	 * determine the final result.
	 */
	public double unknownAllowance = 0.33;
	
	private WikipediaNameisNotFictional wikipedia;
	
	public CheckGuestListNonFictional() {
		wikipedia = new WikipediaNameisNotFictional();
	}
	
	@Override
	public String getName(){
		return "Guests not fictional (Wikipedia)";
	}
	
	@Override
	public Integer getResponse(Event e) {
		
		try {
			//take a random sample of the guestlist
			ArrayList<String> sample = CheckGuestListFB.randomSample(e.getGuestNames(), minNamesToCheck, maxNamesToCheck, ratioGuestsToCheck);
			
			//verify the names
			int numVerified = 0;
			int numUnknown = 0;
			for (int i = 0; i < sample.size(); i++){
				int result = wikipedia.getResponse(sample.get(i));
				if (result == -1){
					numUnknown++;
				}
				else if (result == 1){
					numVerified++;
				}
			}
			
			//check if the results were good enough
			if (numUnknown > unknownAllowance * sample.size()){
				return -1;
			}
			else if (numVerified < successThreshold * sample.size()){
				return 0;
			}
			else{
				return 1;
			}
		} catch (Exception ex) {
			return -1;
		}
	}

	@Override
	public Expenditure[] getCost(Event args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getTrust(Event args, Optional<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

}
