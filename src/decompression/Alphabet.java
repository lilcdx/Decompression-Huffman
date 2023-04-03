package decompression;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Alphabet {
	private ArrayList<ArrayList<Character>> alphabet;
	private int nbChar;

	public Alphabet(String path) {
		alphabet = new ArrayList<ArrayList<Character>>();
		this.init(path);
		this.initNbChar();
	}
		
	/**
	 * Construction de l'alphabet sous forme de liste de listes
	 * Format : [[caractere1, frequence1], [caractere2, frequence2],...]
	 */
	public void init(String path) {
		try {
			//Lecture du fichier ligne par ligne
			Scanner scanner = new Scanner(new File(path));
			scanner.nextLine();//ligne 1 : nombre de caracteres (pas nécessaire pour la création de la liste)
			
			while (scanner.hasNextLine()) {
				String line =scanner.nextLine();
				ArrayList<Character> charArray = new ArrayList<Character>();
				charArray.add(line.charAt(0));//ajout du caractere
				charArray.add(line.charAt(2));//ajout de la frequence
				this.alphabet.add(charArray);//ajout de la liste dans l'alphabet
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calcul du nombre total de caracteres dans le fichier
	 */
	public void initNbChar() {
		int allFreq = 0;
		//addition de toutes les fréquences
		for (ArrayList<Character> list : alphabet) {
			allFreq = allFreq + Character.getNumericValue(list.get(1));
		}
		nbChar = allFreq;
	}

	
	// getters & setters
	
	public int getNbChar() {
		return nbChar;
	}

	public void setNbChar(int nbChar) {
		this.nbChar = nbChar;
	}

	public ArrayList<ArrayList<Character>> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(ArrayList<ArrayList<Character>> alphabet) {
		this.alphabet = alphabet;
	}

}
