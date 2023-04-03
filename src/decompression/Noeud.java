package decompression;

public class Noeud {

	private String label;
	private int freq;
	private Noeud leftChild;
	private Noeud rightChild;
	
	//constructeurs
	
	public Noeud(String label, int freq) {
		this.label = label;
		this.freq = freq;
	}
	
	public Noeud(String label, int freq, Noeud leftChild, Noeud rightChild) {
		this.label = label;
		this.freq = freq;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	//constructeur vide qui permet d'initialiser la liste de noeuds sans problèmes
	public Noeud() {
		
	}
	
	
	// getters & setters
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
	public Noeud getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Noeud leftChild) {
		this.leftChild = leftChild;
	}

	public Noeud getRightChild() {
		return rightChild;
	}

	public void setRightChild(Noeud rightChild) {
		this.rightChild = rightChild;
	}

	public String toString() {
		return "Noeud [label=" + label + ", freq=" + freq + "]";
	}
	
	/*
	 * Renvoie True si le noeud n'a pas d'enfants
	 */
	public boolean isLeaf() {
		return (leftChild == null & rightChild == null);
	}

	
	/*
	 * Parcours de l'arbre en suivant la chaine de bits bitString, en utilisant la récursivité
	 * Retour : chaine de caractères correspondant 
	 */
	
	public String bitToChar(String bitString, Noeud start, String res, int nbChar) {
		//on commence par vérifier que tous les caracteres n'ont pas déjà été convertis (pour éviter des caractères en trop)
		if (nbChar>0) {
			// le noeud est une feuille : represente un caractere
			if (this.isLeaf()) {
				res = res + this.getLabel().charAt(0);//ajout du caractere a la chaine 
				nbChar--;//mise a jour du nombre de caracteres restants
				return start.bitToChar(bitString, start, res, nbChar);//on repart de la racine
				
			// le noeud n'est pas une feuille : il faut continuer a avancer
			} else {
				if (bitString.charAt(0) == '0') {
					bitString = bitString.substring(1);//on enleve le chiffre qui vient d'etre lu
					return this.leftChild.bitToChar(bitString, start, res, nbChar);//on passe au noeud suivant
				}
				//meme principe mais dans le cas ou char = 1
				else if (bitString.charAt(0) == '1') {
					bitString = bitString.substring(1);
					return this.rightChild.bitToChar(bitString, start, res, nbChar);
				}
			}
		}
		return res;
	}
}
