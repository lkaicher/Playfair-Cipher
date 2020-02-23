/**
 * @author Luke Kaicher
 * ID: 112356778
 * luke.kaicher@stonybrook.edu
 * Assignment #4
 * CSE214
 * R01 Daniel Calabria
 * 
 * This class represents a bigram for use in encryption.
 */
public class Bigram {
	private char first, second;
/**
 * Default constructor
 */
	public Bigram() {		
	}
/**
 * Gets the first character of the bigram
 * @return
 * the first character
 */
	public char getFirst() {
		return this.first;
	}
/**
 * Gets the second character of the bigram
 * @return
 * the second character
 */
	public char getSecond() {
		return this.second;
	}
/**
 * Sets the first character of the bigram
 * @param c
 * The char that will be set to the first character
 */
	public void setFirst(char c) {
		this.first = Character.toUpperCase(c);
	}
/**
 * Sets the second character of the bigram
 * @param c
 * The char that will be set to the second character
 */
	public void setSecond(char c) {
		this.second = Character.toUpperCase(c);
	}
/**
 * Returns a string representation of a Bigram object
 * @custom.preconditions
 * The Bigram object has been created
 * @return
 * A string that represents the Bigram
 */
	public String toString() {
		String bigramString = "";
		bigramString += this.getFirst(); 
		bigramString += this.getSecond();
		return bigramString;
	}
}
