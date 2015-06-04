package edu.toronto.cs.se.ci.mains;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

import edu.toronto.cs.se.ci.eventObjects.Event;
import edu.toronto.cs.se.ci.random.EnglishEventGenerator;
import edu.toronto.cs.se.ci.random.EventObjectRandomizer;

public class GenerateRandomEvents {

	public static void main(String[] args) throws IOException {
		
		int numEvents = 35;
		String destination = "./data/event data/random2.json";
		
		//generate the events
		Event [] events = new Event [numEvents];
		//EventObjectRandomizer randomizer = new EventObjectRandomizer(new GibberishEventGenerator());
		EventObjectRandomizer randomizer = new EventObjectRandomizer(new EnglishEventGenerator());
		for (int i = 0; i < numEvents; i++){
			events[i] = randomizer.event();
		}
		
		//send them to json
		Gson gson = new Gson();
		String jsonEvents = gson.toJson(events);
		String toWrite = String.format("{\"events\":%s}", jsonEvents);
		
		//save to a file
		BufferedWriter buf = new BufferedWriter(new FileWriter(destination));
		buf.write(toWrite);
		buf.close();
		
		System.out.println("Saved to " + destination);
	}

}
