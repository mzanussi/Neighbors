package com.michaelzanussi.neighbors;

import java.util.Map;
import java.util.TreeMap;

/**
 * Represents a unique word encountered in a document.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class Word {
	
	// Instance variables.
	public int count;				// word count
	public Map<String, Word> map;	// this word's neighbors
	
	/**
	 * Default constructor.
	 */
	public Word() {
		
		count = 1;
		map = new TreeMap<String, Word>();
		
	}
	
}
