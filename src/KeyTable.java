/**
 * @author Luke Kaicher
 * ID: 112356778
 * luke.kaicher@stonybrook.edu
 * Assignment #4
 * CSE214
 * R01 Daniel Calabria
 * 
 * This class represents a key table for a playfair cypher.
 */
public class KeyTable {
	private char[][] key = new char[5][5];
/**
 * Default constructor
 * Creates an empty keytable.
 */
	public KeyTable() {		
	}
/**
 * Gets the key matrix	
 * @return
 * The 5 x 5 key matrix of the KeyTable object
 */
	public char[][] getKeyTable(){
		return this.key;
	}
/**
 * Creates a keytable using the input String	
 * @param keyphrase
 * The String that will be used as the key phrase.
 * @custom.preconditions
 * keyphrase is not null
 * @return
 * The created KeyTable object
 * @throws IllegalArgumentException
 * When keyphrase is null
 */
	public static KeyTable buildFromString(String keyphrase) {
		if (keyphrase == null)
			throw new IllegalArgumentException();
		keyphrase = keyphrase.toUpperCase();
		KeyTable resultKeyTable = new KeyTable();
		char[] keyphraseArray = keyphrase.toCharArray();
		int column = 0, row = 0;
		for (int i = 0; i < keyphraseArray.length; i++) {
			boolean duplicate = false;
			if ((int) keyphraseArray[i] < 65 || (int) keyphraseArray[i] > 90 ||
			  (int) keyphraseArray[i] == 74)
				continue;
			for (int j = i - 1; j >= 0; j--) {
				if (keyphraseArray[i] == keyphraseArray[j]) {
					duplicate = true;
					break;
				}
			}
			if (duplicate == false) {
				resultKeyTable.key[row][column] = keyphraseArray[i];
				column++;
				if (column > 4) {
					column = 0;
					row++;
				}
			}
		}
		for (int c = 65; c <= 90; c++) {
			boolean appears = false;
			if (c == 74)
				continue;
			char letter = (char) c;
			for (int i = 0; i < keyphraseArray.length; i++) {
				if (keyphraseArray[i] == letter) {
					appears = true;
					break;
				}	
			}
			if (appears == false) {
				resultKeyTable.key[row][column] = letter;
				column++;
				if (column > 4) {
					column = 0;
					row++;
				}
			}
		}
		return resultKeyTable;
	}
/**
 * Finds the row in which a character c occurs in the key matrix	
 * @param c
 * The character being searched for
 * @custom.preconditions
 * The character c is a valid letter
 * @return
 * The index of the row in which c occurs
 * @throws IllegalArgumentException
 * if c is not found in the matrix
 */
	public int findRow(char c) {
		c = Character.toUpperCase(c);
		int foundRow = 0;
		boolean found = false;
		for (int row = 0; row <= 4; row++ ) {
			for (int column = 0; column <= 4; column++) {
				if (c == this.key[row][column]) {
					foundRow = row;
					found = true;
					break;
				}
			}
		}
		if (!found)
			throw new IllegalArgumentException();
		return foundRow;
	}
/**
 * Finds the column in which a character c occurs in the key matrix	
 * @param c
 * The character being searched for
 * @custom.preconditions
 * The character c is a valid letter
 * @return
 * The index of the column in which c occurs
 * @throws IllegalArgumentException
 * if c is not found in the matrix
 */	
	public int findCol(char c) {
		c = Character.toUpperCase(c);
		boolean found = false;
		int foundCol = 0;
		for (int row = 0; row <= 4; row++ ) {
			for (int column = 0; column <= 4; column++) {
				if (c == this.key[row][column]) {
					foundCol = column;
					found = true;
					break;
				}
			}
		}
		if (!found)
			throw new IllegalArgumentException();
		return foundCol;
	}
/**
 * Returns a String representation of a KeyTable
 * @custom.preconditions
 * The KeyTable object has been created
 * @return
 * A string that represents the key matrix
 */
	public String toString() {
		String keyTableString = "";
		for (int row = 0; row <= 4; row++ ) {
			for (int column = 0; column <= 4; column++) {
				keyTableString += this.key[row][column];
				if (column < 4)
					keyTableString += " ";
			}
			keyTableString += "\n";
		}
		return keyTableString;
	}
	
}
