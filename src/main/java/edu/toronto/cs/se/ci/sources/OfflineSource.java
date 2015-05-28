package edu.toronto.cs.se.ci.sources;

/**
 * An interface to have sources which can operate offline.
 * By default a source implementing this interface is online.
 * @author wginsberg
 *
 */
public interface OfflineSource {
	
	public void goOnline();
	
	public void goOffline();
	
	public boolean isOffline();
	
}
