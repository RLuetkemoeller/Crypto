package ka1;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// Initialwerte setzten ...
//		Encryption.setKey("98721honcq90=_aat12");
		Encryption.setKey("98qqqqqqqqqqqqqq90=_aat12");
		Encryption.setBlocksize(16);
		Encryption.init();

		// Die beiden Funktionen f�r die ECB Verschl�sselung.
		Encryption.ecbEncode("plaintext.txt", "outfile.txt");
		Encryption.ecbDecode("outfile.txt", "decrypt.txt");
		
		
		// CBC Encryption
		String str = "Eine Sensible Nachricht die hoffentlich l�nger als die Blocksize ist ... wird am ende mit Leerzeichen aufgef�llt";
		System.out.println("Klartext: " + str);
		String code = Encryption.cbcEncode(str);
		System.out.println("Verschl�sselt: " + code);
		str = Encryption.cbcDecode(code);
		System.out.println("Entschl�sselt: " + str);
		
		
		
	}
	
	
	
	
}
