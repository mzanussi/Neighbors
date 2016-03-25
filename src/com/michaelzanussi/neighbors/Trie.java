package com.michaelzanussi.neighbors;

import java.util.ArrayList;

/**
 * This class is a trie that will be used as the data structure for stop words.
 * 
 * @author <a href="mailto:iosdevx@gmail.com">Michael Zanussi</a>
 * @version 1.1 (25 Mar 2016)
 */
public class Trie {
	
	private Node root = new Node(null);
	
	/**
	 * Returns true if the string is found in the trie.
	 * 
	 * @param string the string to search for
	 * @return true if found; otherwise, false
	 */
	public boolean contains(String string) {
		
		// Return false if null string or empty string.
		if (string == null || string.length() == 0) {
			return false;
		}
		
		Node curNode = root;
		char characters[] = string.toCharArray();
		
		// Locate each character in the trie.
		// If one is not found, return false.
		for (Character ch : characters) {
			if (curNode.next(ch) == null) {
				return false;
			} 
			curNode = curNode.next(ch);
		}

		// All characters have been found,
		// now see if terminating character
		// is set. If so, the string has
		// been located.
		if (curNode.terminating) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Add a string to the Trie.
	 * 
	 * @param string the string to add
	 */
	public void add(String string) {
		
		// Return if null string or empty string.
		if (string == null || string.length() == 0) {
			return;
		}
		
		// String already in trie? Then return.
		if (contains(string)) {
			return;
		}
		
		Node curNode = root;
		char characters[] = string.toCharArray();
		
		// Add the characters of the string
		// that don't exist already.
		for (Character ch : characters) {
			if (curNode.next(ch) == null) {
				curNode.nodes.add(new Node(ch));
			}
			curNode = curNode.next(ch);
		}
		
		// All characters have been added,
		// so set terminating character true.
		curNode.terminating = true;
		
	}
	
	/**
	 * Each node contains a character of a string.
	 * (The root note will be null.)
	 */
	private class Node {
		
		Character ch;
		ArrayList<Node> nodes = new ArrayList<Node>();
		boolean terminating = false;	// is this a terminating character?
		
		/**
		 * Constructor.
		 * 
		 * @param ch string char
		 */
		public Node(Character ch) {
			this.ch = ch;
		}
		
		/**
		 * Return the node that has specified Character.
		 * 
		 * @param ch the character to be located
		 * @return node containing the character, null otherwise
		 */
		public Node next(Character ch) {
			
			// If no character, stop and return null.
			if (ch == null) {
				return null;
			}
			
			// If no nodes, stop and return null.
			if (nodes == null || nodes.size() == 0) {
				return null;
			}
			
			// Step through the nodes looking for char.
			// If char found, return the node that contains it.
			for (Node node : nodes) {
				if (node.ch == ch) {
					return node;
				}
			}
			
			// Nodes exhausted, return null.
			return null;
			
		}
		
	}
	
}
