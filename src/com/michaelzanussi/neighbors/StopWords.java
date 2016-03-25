package com.michaelzanussi.neighbors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * A wrapper for the Trie used to search for stop words.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class StopWords {
	
	protected Trie trie;
    
	/**
	 * Constructor.
	 * 
	 * @param filename name of stop words file
	 */
	public StopWords(String filename) {
		
		trie = new Trie();

		BufferedReader buffer;
		
		try {
			buffer = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file "+ filename + ".  Exiting.");
			System.exit(1);
			return;
		}
        
		try {
			// Add stop words to the trie.
			String line; 
			while ((line = buffer.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " \t");
				while (st.hasMoreTokens()) {
					String temp = st.nextToken();
					trie.add(temp);
				}
			}
			buffer.close();
		} catch (IOException e) {
			System.out.println("Error reading file.  Exiting.");
			System.exit(1);
		}

	}

	/**
	 * Return whether or not word is a stop word.
	 * 
	 * @param word
	 * @return
	 */
	public boolean isStopWord(String word) {
		return trie.contains(word);
	}
	
}





















