package edu.toronto.cs.se.ci.mains;

import java.io.File;
import java.io.FileNotFoundException;

import edu.toronto.cs.se.ci.eventObjects.*;

public class loadChillWallEventsTrial {

	public static void main(String[] args) {
		
		String filePath = "./data/event data/chillwall.json";
		File eventFile = new File(filePath);
		Event [] events = null;
		try{
			events = Event.loadFromJsonFile(eventFile);
		}
		catch (FileNotFoundException ex){
			System.out.println("File not found.");
		}

		for (int i = 0; i < events.length; i++){
			System.out.println(events[i].getSynopsis());
		}
		
	}

}
