package edu.toronto.cs.se.ci.eventSources;

import java.util.ArrayList;
import java.util.Random;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.FaceBookProfileCheck;

public class CheckGuestListFB extends EventSource {

	/**
	 * The minimum number of profiles to check
	 */
	public int minGuestsToCheck = 1;
	/**
	 * The maximum number of profiles to check
	 */
	public int maxGuestsToCheck = 10;
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
	
	private static Random random;
	
	private FaceBookProfileCheck facebook;
	
	public CheckGuestListFB(){
		
		if (random == null){
			random = new Random();
		}
		facebook = new FaceBookProfileCheck();
	}
	
	@Override
	public String getName(){
		return "Guests exist (FB)";
	}
	
	@Override
	public Integer getResponse(Event e) {
		
		try{
			
			//get a random sampling of the guest list
			ArrayList<String> names = e.getGuestNames();
			ArrayList<String> subset = randomSample(names, maxGuestsToCheck, maxGuestsToCheck, ratioGuestsToCheck);
		
			//verify facebook profiles
			int numVerified = 0;
			int numUnknown = 0;
			int result;
			for (int i = 0; i < subset.size(); i++){
				result = facebook.getResponse(subset.get(i));
				if (result == 1){
					numVerified++;
				}
				else if (result == -1){
					numUnknown++;
				}
			}
		
			//check if there were too many unknown results
			if (numUnknown > unknownAllowance * names.size()){
				return -1;
			}
			//check if there were enough positive results
			else if (numVerified < successThreshold * names.size()){
				return 1;
			}
			else{
				return 0;
			}
			
		}
		catch (Exception ex){
			return -1;
		}
	}

	/**
	 * Returns a random subset of a list of names, according to the
	 * properties in minGuestsToCheck, maxGuestsToCheck, ratioGuestsToCheck
	 * @param guestList
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<String> randomSample(ArrayList<String> guestList, int minGuestsToCheck, int maxGuestsToCheck, double ratioGuestsToCheck){
		
		//copy the guest list
		guestList = (ArrayList<String>) guestList.clone();
		
		/*
		 * Calculate the size of the random subset
		 */
		int sampleSize = (int) ratioGuestsToCheck * guestList.size();
		//within the min and max range specified
		if (sampleSize < minGuestsToCheck){
			sampleSize = minGuestsToCheck;
		}
		else if (sampleSize > maxGuestsToCheck){
			sampleSize = maxGuestsToCheck;
		}
		//not more than the size of the list
		if (sampleSize > guestList.size()){
			sampleSize = guestList.size();
		}
		
		/*
		 * The sample of names to check.
		 */
		ArrayList<String> sample = new ArrayList<String>(sampleSize);
		
		/*
		 * Randomly select sampleSize number of indices, and pop
		 * the corresponding entries from the guest list into
		 * the sample list.
		 */
		int guestNumber;
		for (int i = 0; i < sampleSize; i++){
			guestNumber = (int) random.nextFloat() * guestList.size();
			sample.add(i, guestList.remove(guestNumber));
		}
		
		return sample;
	}
	
	@Override
	public Expenditure[] getCost(Event arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getTrust(Event arg0, Optional<Integer> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
