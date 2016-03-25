package com.michaelzanussi.neighbors;

import java.io.IOException;

import javax.swing.text.html.parser.ParserDelegator;

/**
 * Given a URL, parses the HTML and returns the body.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class HTMLParser {

	/**
	 * Default constructor.
	 */
	public HTMLParser() {
	}
	
	/**
	 * Parse the input source.
	 * 
	 * @param data The parsed data.
	 * @return buffer The body text.
	 * @throws IOException  If an I/O error occurs.
	 */
	public String parse(String inputSource) {
		
		String buffer = null;
		
		try {
			
			// Open up the input source.
			HTMLFileReader hr = new HTMLFileReader();
			hr.open(inputSource);
			
			// The callback object; will be used by the
			// parser to handle parsed text.
			HTMLParserCallback callback = new HTMLParserCallback();
			new ParserDelegator().parse(hr.getBuffer(), callback, true);
			
			// Close input source and store off the buffer.
			hr.close();
			buffer = callback.getBuffer();
			
		} catch (IOException e) {
			
			System.err.println("ERROR: " + e.getMessage());
			System.exit(1);
			
		}
		
		return buffer;
		
	}

}
