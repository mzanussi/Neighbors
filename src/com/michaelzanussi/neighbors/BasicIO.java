package com.michaelzanussi.neighbors;

/**
 * Generic basic I/O interface.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public interface BasicIO {

	public boolean open(String inputSource);
	public boolean close();
	
}
