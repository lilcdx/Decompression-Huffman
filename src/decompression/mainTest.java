package decompression;

import java.io.IOException;

public class mainTest {
	public static void main(String[] args) throws IOException {
		
		String path = "C:\\Users\\lilac\\Documents\\Cours\\S6\\PROJ631\\Decompression Huffman\\donnees\\exemple_comp.bin";
		String finalPath = path.replace("_comp.bin", ".txt");
		
		//Ecriture du fichier décompressé
		WriteFile f = new WriteFile(path, finalPath);
		f.createFile();
		
		//Taux de compression
		TauxCompression taux = new TauxCompression(path, finalPath);
		System.out.println("Taux de compression : \n" + taux.calculTaux());
		
		// Nombre moyen de bits de stockage 
		int nbChar =  f.getNbChar();
		System.out.println("Nombre moyen de bits de stockage d’un caractère du texte compressé : \n" + taux.nbBits(nbChar) + " bits");
		
	}

}