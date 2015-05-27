package edu.toronto.cs.se.ci.helpers;

import java.net.URL;
import org.json.JSONObject;

import edu.toronto.cs.se.ci.UnknownException;

public class GoogleSearchJSON {

	private static String prefix =
			"https://www.googleapis.com/customsearch/v1?";
	
	private static String APP_ID;
	private static String API_KEY;
	
	/**
	 * Returns the JSONObject of search results for a given search
	 * @param searchFor - a string to search for
	 * @return
	 * @throws UnknownException
	 */
	public static JSONObject search(String searchFor) throws UnknownException{
		
		/*
		 * Set the API access if it has not been done already
		 */
		if (APP_ID == null){
			APP_ID = System.getenv("GOOGLE_CSE_ID");
		}
		if (API_KEY == null){
			API_KEY = System.getenv("GOOGLE_API_KEY");
		}
		
		//format the search
		String parameters = String.format("q=%s&cx=%s&key=%s", searchFor.replace(" ", "-"), APP_ID, API_KEY);
		
		try {
			//create the url
			URL url = new URL(prefix + parameters);
			//grab the json
			JSONObject json = URLtoJSON.getJSONfromURL(url);
			
			return json;
		} catch (Exception e) {
			throw new UnknownException();
		} 
		
	}

}
