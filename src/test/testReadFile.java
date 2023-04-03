package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class testReadFile {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("C:\\Users\\lilac\\Documents\\Cours\\S6\\PROJ631\\Decompression Huffman\\donnees (2)\\exemple_freq.txt"));
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				System.out.println(scanner.nextLine().charAt(2));
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}