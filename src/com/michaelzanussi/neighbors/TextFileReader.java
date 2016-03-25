package com.michaelzanussi.neighbors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Wrapper class around Java's BufferedReader class.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class TextFileReader implements BasicIO {

	// Instance variables.
	private BufferedReader buffer;
	
	/**
	 * Default constructor.
	 */
	public TextFileReader() {
		
		buffer = null;
		
	}
	
	/**
	 * Open a text file to read from.
	 * 
	 * @param filename The file to open.
	 * @throws IOException If an I/O error occurs.
	 */
	public boolean open(String inputSource) {
		
		File file = new File(inputSource);

		try {
			
			buffer = new BufferedReader(new FileReader(file));
			return true;
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			System.exit(1);
			
		}
		
		return false;

	}

	/**
	 * Method close() replaces BufferedReader's close().
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public boolean close() {
		
		try {
			
			if (buffer != null) {
				buffer.close();
				return true;
			}
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			
		}
		
		return false;
		
	}

	/**
	 * Method readLine() replaces BufferedReader's readLine().
	 * 
	 * @return A line read from the buffer.
	 * @throws IOException If an I/O error occurs.
	 */
	public String readLine() {
		
		String string = null;
		
		try {
			
			string = buffer.readLine();
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			
		}

		return string;
		
	}
	
}
