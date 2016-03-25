package com.michaelzanussi.neighbors;

import java.util.*;

/**
 * Generates a set of text statistics for a given document.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class TextStatistics {

	// Instance variables.
	private Map<String, Word> m;		// The map to hold the word list.
	private StopWords stop;				// The stop words.

	/**
	 * Default constructor.
	 */
	public TextStatistics() {
		
		// TreeMap sorted; HashMap for performance.
		m = new TreeMap<String, Word>();
		
		// Load the stop words.
		stop = new StopWords("StopWords.txt");
		
	}
	
	/**
	 * Process the document and collect statistics.
	 * 
	 * @param input The document to process.
	 */
	public void process( String input ) {
		
		String target = null;
		String previous = null;
		
		// Tokenize this line of input, ignore all punctuation (per specification). 
		// Note that the period is also ignored, thus we'll consider the first non stop 
		// word occurring in that sentence adjacent to the last one in the previous sentence.
		// We've also included a bunch of other punctuation that normally would not occur
		// in a text file, but will probably popup in a HTML file.
		StringTokenizer st = new StringTokenizer(input, " .,:;!?()[]&-=+*@#%^<>/\'\"\\\b\t\r\n\f|\u00A0\u00B7\u0096\u0093\u0094");
		
		// Process each word in this line of input.
		while (st.hasMoreTokens()) {
			
			target = st.nextToken().toLowerCase().trim();
			
			// Ignore any stop words.
			if (!stop.isStopWord(target)) {
				
				// Does the target word already exist in map?
				if (m.containsKey(target)) {
					// Target work exists, increment counter.
					Word word = m.get(target);
					word.count += 1;
					m.put(target, word);
				} else {
					// Add target word to map.
					m.put(target, new Word());
				}
				
				if (previous != null) {
					
					// Previous word is target word's left neighbor.
					// Note use of brackets forcing local scope.
					{
						Word word = m.get(target);
						// Does the neighbor word already exist in target's map?
						if (word.map.containsKey(previous)) {
							// Neighbor word exists, increment counter.
							Word existing = word.map.get(previous);
							existing.count += 1;
							word.map.put(previous, existing);
						} else {
							// Add neighbor word to target's map.
							word.map.put(previous, new Word());
						}
					}
					
					// Target word is previous word's right neighbor.
					// Note use of brackets forcing local scope.
					{
						Word word = m.get(previous);
						// Does the neighbor word already exist in previous' map?
						if (word.map.containsKey(target)) {
							Word existing = word.map.get(target);
							existing.count += 1;
							word.map.put(target, existing);
						} else {
							// Add neighbor word to previous' map.
							word.map.put(target, new Word());
						}
					}
					
				}
				
				// Reset previous word.
				previous = target;
				
			}
			
		}
		
	}
	
	/**
	 * Dump the results to a file.
	 * 
	 * @param output The name of the file to output to.
	 */
	public void output(String output) {
		
		TextFileWriter fw = new TextFileWriter();
		
		if (fw.open(output)) {
			
			// Walk through the word map.
			Iterator<String> keys = m.keySet().iterator();
			
			while (keys.hasNext()) {
				
				String curWord = keys.next();
				fw.write(curWord + ",");
				Word word = m.get(curWord);
				fw.write(new Integer(word.count).toString());
				
				// Walk through this word's neighbor map.
				Iterator<String> neighbors = word.map.keySet().iterator();
				
				while (neighbors.hasNext()) {
					
					String neighborWord = neighbors.next();
					fw.write("," + neighborWord + ",");
					Word neighborWordCount = word.map.get(neighborWord);
					fw.write(new Integer(neighborWordCount.count).toString());
				}
				
				fw.writeln();
			}
			
			fw.close();
			
		}
		
	}
	
}
