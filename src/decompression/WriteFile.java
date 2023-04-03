package decompression;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {
	private String initPath; //chemin vers le fichier compressé
	private String newPath; //chemin vers le fichier decompressé
	private Noeud root; //racine de l'arbre de recherche
	private int nbChar; //nombre total de caractères dans le fichier
	private String content; //contenu a ecrire dans le fichier décompressé

	public WriteFile(String initPath, String newPath) {
		this.initPath = initPath;
		this.newPath = newPath;
		this.initialization();
	}

	// getters & setters
	
	public Noeud getRoot() {
		return root;
	}

	public void setRoot(Noeud root) {
		this.root = root;
	}

	public int getNbChar() {
		return nbChar;
	}


	public void setNbChar(int nbChar) {
		this.nbChar = nbChar;
	}

	/*
	 * Construction de la liste de tous les noeuds triés par ordre de fréquence puis par ordre alphabétique, en se basant sur l'alphabet
	 */
	public ArrayList<Noeud> initListNoeud(ArrayList<ArrayList<Character>> alphabet) {
		ArrayList<Noeud> listNoeuds = new ArrayList<Noeud>(); // initialisation de la liste
		
		for (ArrayList<Character> list : alphabet) {
			String label = String.valueOf(list.get(0));
			int freq = Character.getNumericValue(list.get(1));
			Noeud n = new Noeud(label, freq); //creation du nouveau noeud
			listNoeuds.add(n); //ajout a la liste
		}
		return listNoeuds;
	}
	
	/*
	 * Construction de l'arbre de recherche a partir de la liste totale des noeuds
	 * Retourne la racine de l'arbre
	 */
	public Noeud constructionArbre(ArrayList<ArrayList<Character>> alphabet) {
		ArrayList<Noeud> listNoeuds = initListNoeud(alphabet);
		// repetition du processus jusqu'a obtention d'un unique arbre
		while (listNoeuds.size()>1) {
			Noeud t1 = listNoeuds.get(0);
			Noeud t2 = listNoeuds.get(1);
			int newFreq = t1.getFreq() + t2.getFreq();
			// creation du noeud parent
			Noeud n = new Noeud("noeud", newFreq, t1, t2); //ce noeud ne représente pas un caractère le label est donc juste "noeud"
			insertNewTree(listNoeuds, n);//insertion du nouvel arbre dans la liste
			// on enleve les 2 premiers noeuds 
			listNoeuds.remove(t1);
			listNoeuds.remove(t2);
		}
		return listNoeuds.get(0);
		
	}
	
	/*
	 * Permet d'insérer un nouvel arbre dans la liste de noeuds, en respectant l'ordre de fréquence et alphabétique
	 */
	public void insertNewTree(ArrayList<Noeud> listNoeuds, Noeud newTree) {
		
		int freq = newTree.getFreq();
		// le nouvel arbre a la plus haute fréquence : ajout a la fin
		if (freq > listNoeuds.get(listNoeuds.size()-1).getFreq()) {
			listNoeuds.add(newTree);
			
		} else {
			// le nouveau noeud doit etre inséré au milieu de la liste
			for(int i=0; i<listNoeuds.size()-1; i++) {
				Noeud tree = listNoeuds.get(i);
				Noeud nextTree = listNoeuds.get(i+1);
				
				if (tree.getFreq()<freq & nextTree.getFreq()>=freq) {
					// insertion entre le noeud de fréquence inférieure et celui de fréquence supérieure ou égale
					listNoeuds.add(i+1, newTree);
					break;
				}
			}
		}
		
	}
	
	/*
	 * Regroupe toutes les méthodes précédentes afin de créer le contenu du fichier .txt a ecrire
	 */
	public void initialization() {
		String freqPath = initPath.replace("_comp.bin", "_freq.txt");//chemin du fichier de fréquence des caracteres
		Alphabet alpha = new Alphabet(freqPath);//creation de l'alphabet
		this.root = this.constructionArbre(alpha.getAlphabet());//construction de l'arbre et récupération de sa racine
		this.nbChar = alpha.getNbChar(); //récupération du nombre total de caractères dans le fichier
		ReadBinFile file = new ReadBinFile(initPath);
		//lecture du fichier binaire et parcours de l'arbre en fonction, création du contenu a mettre dans le fichier final
		this.content = root.bitToChar(file.read(), root, "", alpha.getNbChar());
	}
	
	/*
	 * Création du fichier .txt décompressé
	 */
	public void createFile() {
		try {
			FileWriter finalFile = new FileWriter(newPath);
			finalFile.write(content);
			finalFile.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

