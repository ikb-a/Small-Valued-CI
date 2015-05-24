package edu.toronto.cs.se.ci.helpers;

import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONObject;

import edu.toronto.cs.se.ci.UnknownException;

public class GoogleSearchJSON {

	private static String prefix =
			"http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
	
	/**
	 * Returns the JSONObject of search results for a given search
	 * @param searchFor - a string to search for
	 * @return
	 * @throws UnknownException
	 */
	public static JSONObject search(String searchFor) throws UnknownException{
		
		try {
			//create the url
			URL url = new URL(prefix + URLEncoder.encode(searchFor, "UTF-8"));
			//grab the json
			JSONObject json = URLtoJSON.getJSONfromURL(url);
			
			return json;
		} catch (Exception e) {
			throw new UnknownException();
		} 
		
	}

}
