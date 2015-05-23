package ka1;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		// Initialwerte setzten ...
//		Encryption.setKey("98721honcq90=_aat12");
		Encryption.setKey("98qqqqqqqqqqqqqq90=_aat12");
		Encryption.setBlocksize(16);
		Encryption.init();

		// Die beiden Funktionen für die ECB Verschlüsselung.
		Encryption.ecbEncode("plaintext.txt", "outfile.txt");
		Encryption.ecbDecode("outfile.txt", "decrypt.txt");
		
		
		// CBC Encryption
		String str = "Eine Sensible Nachricht die hoffentlich länger als die Blocksize ist ... wird am ende mit Leerzeichen aufgefüllt";
		System.out.println("Klartext: " + str);
		String code = Encryption.cbcEncode(str);
		System.out.println("Verschlüsselt: " + code);
		str = Encryption.cbcDecode(code);
		System.out.println("Entschlüsselt: " + str);
		
		
		
	}
	
	
	
	
}
