package edu.toronto.cs.se.ci.mains;

import edu.toronto.cs.se.ci.helpers.YelpSearchJSON;

public class queryyelp {

	public static void main(String[] args) {
		YelpSearchJSON.init();
		YelpSearchJSON.searchBusinessByLocation("Lee's Palace", "Toronto", 3);

	}

}
