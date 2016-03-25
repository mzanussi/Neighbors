package com.michaelzanussi.neighbors;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Wrapper class around Java's BufferedWriter class.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class TextFileWriter implements BasicIO {

	// Instance variables.
	private BufferedWriter buffer;
		 
	/**
	 * Default constructor.
	 */
	public TextFileWriter() {
		
		buffer = null;
		
	}
	
	/**
	 * Open a text file to write to.
	 * 
	 * @param filename The file to create.
	 * @throws IOException If an I/O error occurs.
	 */
	public boolean open(String inputSource) {

		File file = new File(inputSource);
		
		try {
			
			buffer = new BufferedWriter(new FileWriter(file));
			return true;
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			System.exit(1);
			
		}
		
		return false;
		
	}

	/**
	 * Method close() replaces BufferedWriter's close(), flushing
	 * the buffer and closing the file.
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
	 * Method write replaces BufferedWriter's write, writing
	 * a string to the buffer.
	 * 
	 * NOTE: This method can be customized, if needed, to fit
	 * our specifications for the project. It doesn't have to
	 * be this generic.
	 * 
	 * @param string The string to output to the buffer.
	 * @throws IOException If an I/O error occurs.
	 */
	public void write(String string) {
		
		try {
			
			buffer.write(string);
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			
		}
		
	}

	/**
	 * Method writeLine() combines BufferedWriter's write()
	 * method and newLine() method, writing a string to the
	 * followed by a newline.
	 * 
	 * @param string The string to output to the buffer.
	 * @throws IOException If an I/O error occurs.
	 */
	public void writeln(String string) {
		
		try {
			
			buffer.write(string);
			buffer.newLine();
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			
		}
		
	}

	/**
	 * Method writeLine() outputs a newline.
	 * 
	 * @throws IOException If an I/O error occurs.
	 */
	public void writeln() {
		
		try {
			
			buffer.newLine();
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			
		}
		
	}

}
