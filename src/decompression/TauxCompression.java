package decompression;

import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TauxCompression {
	
	private Path initFile;
	private Path finalFile;
	
	public TauxCompression(String initFile, String finalFile) {
		this.initFile = Paths.get(initFile);
		this.finalFile = Paths.get(finalFile);
	}
	
	/*
	 * Methode qui permet de calculer le nombre moyen de bits de stockage d’un caractère du texte compressé
	 */
	public double calculTaux() throws IOException {
		long initSize = Files.size(initFile);
		long finalSize = Files.size(finalFile);
		double taux = 1 - (double)initSize/finalSize;
		return taux;
	}
	
	/*
	 * Methode qui permet de calculer le taux de compression
	 */
	public double nbBits(int nbChar) throws IOException {
		long initSize = Files.size(initFile);
		double res = (double)initSize*8/nbChar;
		return res;
	}
}
