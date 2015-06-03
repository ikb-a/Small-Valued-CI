package edu.toronto.cs.se.ci.eventObjects;

public class EventContact {

	//raw strings to set when loading from Json with Gson
	private String twitter_handle, twitter_url, facebook_url, website, email, phone;
	
	public EventContact(){
		this.twitter_handle = "";
		this.twitter_url = "";
		this.facebook_url = "";
		this.website = "";
		this.email = "";
		this.phone = "";
	}	

	public EventContact(String twitter_handle, String twitter_url, String facebook_url, String website, String email, String phone){
		this.twitter_handle = twitter_handle;
		this.twitter_url = twitter_url;
		this.facebook_url = facebook_url;
		this.website = website;
		this.email = email;
		this.phone = phone;
	}

	public String getTwitterHandle(){
		return twitter_handle;
	}
	public String getTwitterURL(){
		return twitter_url;
	}
	public String getFacebookURL(){
		return facebook_url;
	}
	public String getWebsiteURL(){
		return website;
	}
	public String getEmailAddress(){
		return email;
	}
	public String getPhoneNumber(){
		return phone;
	}
}
