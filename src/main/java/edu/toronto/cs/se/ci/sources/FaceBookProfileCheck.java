package edu.toronto.cs.se.ci.sources;

import java.lang.Math;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.toronto.cs.se.ci.helpers.GoogleSearchJSON;

import com.google.common.base.Optional;

import edu.toronto.cs.se.ci.UnknownException;
import edu.toronto.cs.se.ci.budget.Expenditure;
import edu.toronto.cs.se.ci.utils.BasicSource;

public class FaceBookProfileCheck extends BasicSource<String, Integer, Void> {

	/**
	 * How many Google Search results should be checked to see if they are
	 * the Facebook profile of the person whose name we search for.
	 */
	public int numResultsToCheck = 5;
	
	/**
	 * Should the search allow for public figures, places, etc.
	 */
	public boolean allowNonPeople = false;
	
	/**
	 * Returns true iff a google search for "<Name> Facebook" returned
	 * results and one result was a Facebook profile page.
	 */
	@Override
	public Integer getResponse(String name) {
		
		//get the json
		JSONObject json;
		try {
			json = GoogleSearchJSON.search(name + " " + "Facebook");
		} catch (UnknownException e1) {
			return -1;
		}
		JSONArray results;
		
		//try to get the results out of the json
		try{
			results = json.getJSONObject("responseData").getJSONArray("results");
		}
		catch (Exception e){
			return -1;
		}

		
		//check the search results
		for (int i = 0; i < Math.min(numResultsToCheck, results.length()); i++){
			JSONObject result = results.getJSONObject(i);
			String title = result.getString("titleNoFormatting");
			String content = result.getString("content");
			/*
			 * Check that the page title is the Facebook profile we are seeking
			 * It could either be "<Name> | Facebook" or "<Name> Profiles | Facebook",
			 * in the case where there are multiple matches (Seems to be how it works)
			 */
			if (title.equals(name + " | Facebook") || title.equals(name + " Profiles | Facebook")){
					if (isNonPerson(content)){
						return 0;
					}
					else{
						return 1;
					}
			}
		}
		return 0;
	}

	/**
	 * Returns true if the description contains something like
	 * "X talking about this" or "Y likes" or "Z were here"
	 */
	private boolean isNonPerson(String pageDescrption){
		
		/*
		 * create regular expressions which tell us a facebook
		 * page is not a person.
		 */
		Pattern p1 = Pattern.compile("[0-9]* likes");
		Pattern p2 = Pattern.compile("[0-9]* talking about this");
		Pattern p3 = Pattern.compile("[0-9]* were here");
		
		/*
		 * Check the patterns on the input. return true if theere was any match
		 */
		Pattern [] patterns = {p1, p2, p3};
		Matcher matcher;
		for (int i = 0; i < patterns.length; i++){
			matcher = patterns[i].matcher(pageDescrption);
			if (matcher.find()){
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Expenditure[] getCost(String args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void getTrust(String args, Optional<Integer> value) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumResultsToCheck() {
		return numResultsToCheck;
	}

	public void setNumResultsToCheck(int numResultsToCheck) {
		this.numResultsToCheck = numResultsToCheck;
	}
	
}
