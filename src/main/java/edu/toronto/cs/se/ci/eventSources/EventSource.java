package edu.toronto.cs.se.ci.eventSources;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.sources.OfflineSource;
import edu.toronto.cs.se.ci.utils.BasicSource;

public abstract class EventSource extends BasicSource<Event, Integer, Void> implements OfflineSource{

	private static String cacheDirectory = "./data/cache/";
	
	/**
	 * Cache responses at run time.
	 * The keys are event IDs and the values are this source's responses.
	 */
	protected HashMap<String, Integer> runTimeCache;
	protected boolean useRuntimeCache = true;
	public void stopRuntimeCaching(){
		useRuntimeCache = false;
	}
	public void startRuntimeCaching(){
		useRuntimeCache = true;
	}
	public boolean isRuntimeCaching(){
		return useRuntimeCache;
	}
	
	/**
	 * Access the internet for answers (in contrast to only using the cache).
	 */
	protected boolean isOnline = true;
	public void goOnline(){
		isOnline = true;
	}
	public void goOffline(){
		isOnline = false;
	}
	public boolean isOffline(){
		return !isOnline;
	}

	/**
	 * Use the local hard drive cache when the run time cache and internet are not used.
	 */
	protected boolean useLocalCache = true;
	public void stopLocalCaching(){
		useLocalCache = false;
	}
	public void startLocalCaching(){
		useLocalCache = true;
	}
	public boolean isLocalCaching(){
		return useLocalCache;
	}
	
	public EventSource(){
		runTimeCache = new HashMap<String, Integer>();
	}
	
	public Integer getResponse(Event e){
		//get the response
		Integer response;
		if (isOnline){
			response = getResponseOnline(e);
		}
		else{
			try {
				response = getResponseOffline(e);
			} catch (FileNotFoundException e1) {
				response = -1;
			}
		}
		//runtime cache the response
		if (useRuntimeCache){
			runTimeCache.put(e.getID(), response);
		}
		return response;
	}

	/**
	 * TODO: This method should be modified to record responses into the cache.
	 * @param e
	 * @return
	 */
	
	/**
	 * Closing the source to have the run time cache added to the local cache.
	 */
	public void close(){
		if (isRuntimeCaching() && isLocalCaching()){
			try {
				saveCache(new File(cacheDirectory + getName() + ".json"),
						new File(cacheDirectory + getName() + ".json"));
			} catch (IOException e) {
				System.err.printf("Warning : Could not save cache of %s\n", getName());
			}
		}
	}
	
	abstract protected Integer getResponseOnline(Event e);
	
	/**
	 * Looks in a file specified by cacheDirectory and this source's name.
	 * If there is a value stored for the supplied event then it is returned,
	 * otherwise -1 is returned.
	 * @throws FileNotFoundException
	 */
	protected Integer getResponseOffline(Event e) throws FileNotFoundException{

		//use the runtime cache if we can
		if (useRuntimeCache){
			if (runTimeCache.containsKey(e.getID())){
				return runTimeCache.get(e.getID());
			}
		}
		
		//don't use the local cache if we are told not to
		if (!useLocalCache){
			return -1;
		}
		
		File cacheFile = new File(cacheDirectory + getName() + ".json");
		JsonReader reader;
		reader = new JsonReader(new FileReader(cacheFile));
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject) parser.parse(reader);
		
		String key = e.getID();
		if (json.has(key)){
			return json.get(key).getAsInt();
		}
		else{
			return -1;
		}
		
	}
	
	/**
	 * Save the run time cache to a file.
	 * @param original - A previous cache file to append to.
	 * @param destination - The file to save the cache to.
	 * @throws IOException
	 */
	protected void saveCache(File original, File destination) throws IOException{
		
		//read the existing json if it exists
		JsonReader reader;
		JsonObject json;
		try{
			reader = new JsonReader(new FileReader(original));
			JsonParser parser = new JsonParser();
			json = (JsonObject) parser.parse(reader);	
			reader.close();
		}
		catch (FileNotFoundException ex){
			json = null;
		}

		//add the runtime cache to the json
		json = mergeWithRuntimeCache(json);

		//write
		BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
		writer.write(json.toString());
		writer.close();
	}
	
	/**
	 * Given a JsonObject, adds all of the key/value pairs in the runtime cache
	 * which do not exist in the supplied json. Returns the modified json.
	 */
	private JsonObject mergeWithRuntimeCache(JsonObject json){

		if (json == null){
			json = new JsonObject();
		}
		//add all of the key/value pairs which do not already exist
		for (String key : runTimeCache.keySet()){
			if (!json.has(key)){
				json.addProperty(key, runTimeCache.get(key));
			}
		}
		return json;
	}
	
}
