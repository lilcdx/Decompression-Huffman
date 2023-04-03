package decompression;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadBinFile {
	private String fileName; //chemin vers le fichier compressé

	public ReadBinFile(String fileName) {
		this.fileName = fileName;
	}
	
	/*
	 * Permet de lire le contenu d'un fichier binaire
	 * Renvoie une chaine de caracteres binaire, correspondant au contenu du fichier .bin
	 */
	public String read() {
		
        File file = new File(this.fileName);
        String res ="";
        
        try (FileInputStream input = new FileInputStream(file)) {
        	
            byte[] data = new byte[(int) file.length()];
            input.read(data);
            String binString = ""; //initialisation de la chaine binaire
            
            //lecture du fichier octet par octet
            for (byte b : data) {
                String bitString = Integer.toBinaryString(b & 0xFF);
                // convertir b en string enleve les premiers 0 s'il y en a 
                // on ajoute donc des 0 au début de la chaine jusqu'a atteindre une taille egale a 8 
                while (bitString.length() < 8) {
                    bitString = "0" + bitString;
                }
                
                binString = binString + bitString;
            }
            
            res = binString.toString();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

}
