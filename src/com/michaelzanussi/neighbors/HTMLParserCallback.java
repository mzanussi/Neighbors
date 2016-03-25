package com.michaelzanussi.neighbors;

import javax.swing.text.html.HTMLEditorKit;

/**
 * Callback for ParserDelegator's parse method.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class HTMLParserCallback extends HTMLEditorKit.ParserCallback {

	// Instance variables.
	private StringBuffer buffer;			// buffer to hold parsed HTML body text
	
	/**
	 * Default constructor.
	 */
	public HTMLParserCallback() {
		
		buffer = new StringBuffer();
		
	}
	
	/**
	 * This method appends parsed data into a buffer.
	 * 
	 * @param data The parsed data.
	 * @param pos  Position.
	 */
	public void handleText(char[] data, int pos) {
		
		buffer.append(data);
		buffer.append("\n");
		
	}
	
	/**
	 * Provides public access to the buffer.
	 * 
	 * @return String The buffer data.
	 */
	public String getBuffer() {
		
		return buffer.toString();
		
	}

}
