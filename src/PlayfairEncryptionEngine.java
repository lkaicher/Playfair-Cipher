/**
 * @author Luke Kaicher
 * ID: 112356778
 * luke.kaicher@stonybrook.edu
 * Assignment #4
 * CSE214
 * R01 Daniel Calabria
 * 
 * This class provides a menu interface for Playfair encryption. The user first
 * gives a key for the KeyTable, and is then able to enter commands to print the
 *  KeyTable, encrypt a phrase, decrypt a phrase, change the key, and quit. 
*/
import java.util.Scanner;
public class PlayfairEncryptionEngine {
	public static void main(String[] args) {
		Scanner stdin = new Scanner(System.in);
		System.out.print("Enter key phrase: ");
		String keyphrase = stdin.nextLine();
		if (keyphrase == null)
			keyphrase = "";
		KeyTable key = KeyTable.buildFromString(keyphrase);
		System.out.println("\nGeneration success!");
		while (true) {
			System.out.println("Menu: \n(CK) - Change Key\n(PK) - Print Key"+
			  "\n(EN) - Encrypt\n(DE) - Decrypt\n(Q)  - Quit");
			System.out.print("Please select an option: ");
			String inputCommand = stdin.nextLine();
			inputCommand = inputCommand.toUpperCase();
			switch(inputCommand) {
			case "CK":
				System.out.print("Enter key phrase:");
				keyphrase = stdin.nextLine();
				try {
					key = KeyTable.buildFromString(keyphrase);
				} catch (IllegalArgumentException ex) {
					System.out.println("\nInvalid key phrase");
					break;
				}
				System.out.println("\nGeneration success!");
				break;
			case "PK":
				System.out.println(key.toString());
				break;
			case "EN":
				System.out.print("Please enter a phrase to encrypt: ");
				String inputString = stdin.nextLine();
				int letterCount = 0;
				inputString = inputString.toUpperCase();
				for (int i = 0; i < inputString.length(); i++) {
					char c = inputString.charAt(i); 
					if ((int) c >= 65 && (int) c <= 90 && (int) c != (int) 'J')
						letterCount++;
				}
				if (letterCount == 0 ) {
					System.out.println("Error: Phrase must contain letters.");
					break;
				}
				Phrase inputPhrase = Phrase.buildPhraseFromStringforEnc
				  (inputString);
				Phrase encryptedPhrase = new Phrase();
				try {
					encryptedPhrase = inputPhrase.encrypt(key);
				} catch (IllegalArgumentException ex) {
					System.out.println("\nError: key is null");
					break;
				}
				System.out.println("Encrypted text is: " + 
				  encryptedPhrase.toString());
				break;
			case "DE":
				System.out.print("Please enter a phrase to decrypt: ");
				inputString = stdin.nextLine();
				letterCount = 0;
				inputString = inputString.toUpperCase();
				for (int i = 0; i < inputString.length(); i++) {
					char c = inputString.charAt(i); 
					if ((int) c >= 65 && (int) c <= 90 && (int) c != (int) 'J')
						letterCount++;
				}
				if (letterCount % 2 == 1 ) {
					System.out.println("Error: Phrase must have an even number"
					  + " of letters.");
					break;
				}
				if (letterCount == 0 ) {
					System.out.println("Error: Phrase must contain letters.");
					break;
				}
				inputPhrase = Phrase.buildPhraseFromStringforEnc(inputString);
				Phrase decryptedPhrase = new Phrase();
				try {
					decryptedPhrase = inputPhrase.decrypt(key);
				} catch (IllegalArgumentException ex) {
					System.out.println("\nError: key is null");
					break;
				}
				System.out.println("Decrypted text is: " + 
				  decryptedPhrase.toString());
				break;
			case "Q":
				System.out.println("Program terminating...");
				stdin.close();
				System.exit(0);
				break;
			default: System.out.println("Invalid input.");
				break;				
			}
		}
	}
}
