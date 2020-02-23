/**
 * @author Luke Kaicher
 * ID: 112356778
 * luke.kaicher@stonybrook.edu
 * Assignment #4
 * CSE214
 * R01 Daniel Calabria
 * 
 * This class extends LinkedList and acts as a wrapper for a queue, and 
 * represents a phrase that can be encrypted or decrypted using a playfair 
 * cypher.
 */
import java.util.LinkedList;
import java.util.Queue;
public class Phrase extends LinkedList<Bigram> implements Queue<Bigram> {
/**
 * Adds a bigram to the Phrase queue
 * @param b
 * the bigram that is to be added to the phrase
 */
	public void enqueue(Bigram b) {
		super.add(b);
	}
/**
 * Removes and returns the first bigram from the Phrase queue
 * @return
 * The bigram at the front of the queue.
 */
	public Bigram dequeue() {
		return super.remove();
	}
/**
 * @return 
 * the bigram at the front of the queue
 */
	public Bigram peek() {
		return super.peek();
	}
/**
 * Builds and returns a new Phrase object, which is a queue of Bigrams which 
 * represents s
 * @param s
 * The String that will be represented as a queue of Bigrams
 * @return
 * The new Phrase object which contains a queue of Bigrams representng s.
 */

	public static Phrase buildPhraseFromStringforEnc(String s) {
		Phrase newPhrase = new Phrase();
		s = s.toUpperCase();
		s = s.replaceAll(" ","");
		s = s.replaceAll("J","I");
		for (int i = 0; i < s.length();) {
			char c = s.charAt(i);
			if (c >= 48 && c <= 57) {
				String charString =  String.valueOf(c);
				s = s.replaceAll(charString,"");
			}
			else if ( c < 65 ||  c > 90) {
				String charString = "\\" + String.valueOf(c);
				s = s.replaceAll(charString,"");
			}
			else 
				i++;
		}
		char[] stringArray = s.toCharArray();
		for (int i = 0; i < stringArray.length;i++){
			Bigram b = new Bigram();
			b.setFirst(stringArray[i]);
			if (i == stringArray.length - 1) {
				b.setSecond('X');
				newPhrase.enqueue(b);
				break;
			}
			else if (stringArray[i + 1] == stringArray[i]) {
				b.setSecond('X');
				newPhrase.enqueue(b);
			}
			else {
				b.setSecond(stringArray[i+1]);
				newPhrase.enqueue(b);
				i++;
				
			}
		}
		return newPhrase;
	}
/**
 * Encrypts a Phrase using a KeyTable and returns the new encrypted Phrase
 * @param key
 * the KeyTable that will be used to encrypt the Phrase
 * @custom.preconditions
 * key is not null
 * @return
 * The new encrypted Phrase
 * @throws IllegalArgumentException
 * When the key is null
 */
	public Phrase encrypt(KeyTable key) {
		if (key == null)
			throw new IllegalArgumentException();
		Phrase encryptedPhrase = new Phrase();
		while (!this.isEmpty()) {
			Bigram unEncryptedBigram = this.dequeue();
			Bigram encryptedBigram = new Bigram();
			int firstRow = key.findRow(unEncryptedBigram.getFirst());
			int firstCol = key.findCol(unEncryptedBigram.getFirst());
			int secondRow = key.findRow(unEncryptedBigram.getSecond());
			int secondCol = key.findCol(unEncryptedBigram.getSecond());
			if (firstRow == secondRow) {
				if (firstCol == 4)
					firstCol = 0;
				else 
					firstCol++;
				if (secondCol == 4)
					secondCol = 0;
				else 
					secondCol++;				
				encryptedBigram.setFirst(key.getKeyTable()[firstRow][firstCol]);
				encryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][secondCol]);
			}
			else if (firstCol == secondCol) {
				if (firstRow == 4)
					firstRow = 0;
				else 
					firstRow++;
				if (secondRow == 4)
					secondRow = 0;
				else 
					secondRow++;				
				encryptedBigram.setFirst(key.getKeyTable()[firstRow][firstCol]);
				encryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][secondCol]);
			}
			else {
				encryptedBigram.setFirst(key.getKeyTable()
				  [firstRow][secondCol]);
				encryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][firstCol]);
			}
			encryptedPhrase.enqueue(encryptedBigram);
		}
		return encryptedPhrase;
	}
/**
 * Decrypts an encrypted Phrase using a KeyTable and returns the new decrypted 
 * Phrase
 * @param key
 * the KeyTable that will be used to decrypt the Phrase
 * @custom.preconditions
 * key is not null
 * @return
 * The new decrypted Phrase
 * @throws IllegalArgumentException
 * When the key is null
 */
	public Phrase decrypt(KeyTable key) {
		if (key == null)
			throw new IllegalArgumentException();
		Phrase decryptedPhrase = new Phrase();
		while (!this.isEmpty()) {
			Bigram encryptedBigram = this.dequeue();
			Bigram decryptedBigram = new Bigram();
			int firstRow = key.findRow(encryptedBigram.getFirst());
			int firstCol = key.findCol(encryptedBigram.getFirst());
			int secondRow = key.findRow(encryptedBigram.getSecond());
			int secondCol = key.findCol(encryptedBigram.getSecond());
			if (firstRow == secondRow) {
				if (firstCol == 0)
					firstCol = 4;
				else 
					firstCol--;
				if (secondCol == 0)
					secondCol = 4;
				else 
					secondCol--;				
				decryptedBigram.setFirst(key.getKeyTable()[firstRow][firstCol]);
				decryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][secondCol]);
			}
			else if (firstCol == secondCol) {
				if (firstRow == 0)
					firstRow = 4;
				else 
					firstRow--;
				if (secondRow == 0)
					secondRow = 4;
				else 
					secondRow--;				
				decryptedBigram.setFirst(key.getKeyTable()[firstRow][firstCol]);
				decryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][secondCol]);
			}
			else {
				decryptedBigram.setFirst(key.getKeyTable()
				  [firstRow][secondCol]);
				decryptedBigram.setSecond(key.getKeyTable()
				  [secondRow][firstCol]);
			}
			decryptedPhrase.enqueue(decryptedBigram);
		}
		return decryptedPhrase;
	}
/**
 * @return
 * A string representation of the Phrase	
 */
	public String toString() {
		String phraseString = "";		
		while(!this.isEmpty()) {
			phraseString += this.dequeue();
		}
		return phraseString;
	}
}

