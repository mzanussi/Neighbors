package com.michaelzanussi.neighbors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Wrapper class around Java's BufferedReader class.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class HTMLFileReader implements BasicIO {

	// Instance variables.
	private BufferedReader buffer;
	
	/**
	 * Default constructor.
	 */
	public HTMLFileReader() {
		
		buffer = null;
		
	}
	
	/**
	 * Open an HTML file to read from.
	 * 
	 * @param filename The file to open.
	 * @throws IOException If an I/O error occurs.
	 */
	public boolean open(String inputSource) {

		try {
			
			String source = inputSource.toLowerCase();
			// Open a URL...
			if (source.startsWith("http://")) {
				URL url = new URL(inputSource);
				buffer = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
			} else if (source.endsWith(".htm") || source.endsWith(".html")) { // ...or, open a .htm or .html
				File file = new File(inputSource);
				buffer = new BufferedReader(new FileReader(file));
			}
			
			// Attempt to open the URL.
			return true;
			
		} catch (IOException e) {
			
			System.err.println("ERROR: Unable to access " + e.getMessage());
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
	 * Returns a pointer to the output buffer.
	 * 
	 * @return Pointer to the buffer.
	 */
	protected BufferedReader getBuffer() {
		
		return buffer;
		
	}
	
}
