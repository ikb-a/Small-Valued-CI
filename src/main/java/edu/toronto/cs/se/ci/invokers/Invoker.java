package edu.toronto.cs.se.ci.invokers;

import java.util.List;

import weka.core.Instances;

/**
 * 
 * @author wginsberg
 *
 * @param <S> source type
 * @param <I> input type
 * @param <O> output type
 */
public interface Invoker <S, I, O>{

	public void invoke(List<S> sources, List<I> inputs);
	
	public O [][] getResults();
	
	public Instances getResultInstances() throws Exception;
}
