package edu.toronto.cs.se.ci.invokers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

import edu.toronto.cs.se.ci.Source;
import weka.core.Instances;

/**
 * 
 * @author wginsberg
 *
 * @param <S> source type
 * @param <I> input type
 * @param <O> output type
 */
public abstract class Invoker <S extends Source<I, ?, ?>, I, O>{

	protected String name;
	
	abstract public int numSources();
	
	abstract public S getSource(int i);
	
	abstract public void invoke(List<S> sources, List<I> inputs);
	
	abstract public O [][] getResults();
	
	abstract public Instances getResultInstances() throws Exception;
	
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Saves the invoker's results in ARFF format.
	 * @param destination - an open file to write to
	 * @throws IOException - if there was any error writing to the file
	 */
	public void saveToArff(File destination) throws IOException{
		
		saveToArff(destination, null);
		
	}

	/**
	 * Saves the invoker's results in ARFF format.
	 * @param destination - an open file to write to
	 * @param comment - A string to include as a comment at the top of the file.
	 * 					Newline characters are safe to use.
	 * @throws IOException - if there was any error writing to the file
	 */
	public void saveToArff(File destination, String comment) throws IOException{
		
		FileWriter writer = new FileWriter(destination);
		BufferedWriter buf = new BufferedWriter(writer);
		
		//write the file's header
		String header = getFileHeader();
		buf.write(header);
		buf.write("\n");

		//write the supplied comment, adding comment characters after newlines
		buf.write("% ");
		buf.write(comment.replace("\n", "\n% "));
		buf.write("\n\n");
		
		//write the name of the relation
		buf.write("@relation " + getName().replace(" ", "-") + "\n");
		buf.write("\n");

		//write the relation's attributes
		String attributeType = getSourceArffType();
		for (int i = 0; i < numSources(); i++){
			buf.write(
					String.format("@attribute %s %s\n",
					getSource(i).getName().replace(" ", "-"),
					attributeType));
		}
		buf.write("\n");
		
		//get the data
		O [][] data = getResults();
		
		//write the data
		buf.write("@data\n");
		for (int i = 0; i < data.length; i++){
			for (int j = 0; j < data[0].length; j++){
				buf.write(data[i][j].toString());
				if (j + 1 < data[0].length){
					buf.write(",");
				}
			}
			buf.write("\n");
		}

		buf.close();
		writer.close();
	}
	
	/**
	 * Returns a string which can be used as the header
	 * for a file containing the results of the invocation.
	 * Contains the date and time of creation.
	 */
	private String getFileHeader(){
				
		StringBuilder sb = new StringBuilder();
		
		//string the current date and time
		SimpleDateFormat sdf = new SimpleDateFormat("'Generated at' h:mm a 'on' EEE, MMM d, yyyy");
		Date date = new Date();
		date.setTime(System.currentTimeMillis());
		sb.append("% ");
		sb.append(sdf.format(date));
		sb.append("\n");
		
		return sb.toString();
	}
	
	/**
	 * Return a string to use as the attribute type
	 * when saving to ARFF format.
	 * @return
	 */
	abstract protected String getSourceArffType();
}
