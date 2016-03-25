package com.michaelzanussi.neighbors;

/**
 * The driver.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.0 (5 Dec 2003)
 */
public class Driver {

	public static void main(String[] args) {
		
		// Create a text statistics object we'll use to store
		// the results in.
		TextStatistics ts = new TextStatistics();

		// There should only be one command line argument specified. 
		if (args.length != 1) {
			System.out.println("Usage: java Driver document.txt");
			System.out.println("   or: java Driver http://www.someURL.com");
			System.exit(1);
		} 

		String inputSource = args[0];
		String source = inputSource.toLowerCase();

		// Allow HTML source from a URL or from a .htm/.html source.
		if (source.startsWith("http://") || source.endsWith(".htm") || source.endsWith(".html")) {
			HTMLParser hp = new HTMLParser();
			ts.process(hp.parse(inputSource));
		} else { // Assume standard text input.
			// Open text file.
			TextFileReader fr = new TextFileReader();
			fr.open(inputSource);
			// Process the file.
			String input = null;
			while ((input = fr.readLine()) != null) {
				ts.process(input);
			}
			// Close the input file.
			fr.close();
		}
		
		// Generate the output file.
		String output = "output.txt";
		ts.output(output);
		System.out.println("Finished processing " + inputSource + ". Results located in " + output + ".");
		
	}
	
}
