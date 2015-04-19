package ka1;

import java.io.IOException;
import java.util.BitSet;



public class Main {

	public static void main(String[] args) throws IOException {
		/*
		Encryption.setKey("98721honcq90=_aat12");
		
		Encryption.ecbEncode("plaintext.txt", "outfile.txt");
		Encryption.ecbDecode("outfile.txt", "decrypt.txt");
		*/
		/*
		byte[] b = "A".getBytes();
		BitSet bs = Encryption.bytetobitset(b);
		byte[] b2 = bs.toByteArray();
	
		
		System.out.println((char)b[0]);
		System.out.println((char)b2[0]);
		*/
		Encryption.init();
		String str = "Hello";
		String cry = Encryption.xor(str, "12345");
		System.out.println(cry);
		System.out.println(Encryption.xor(cry, "12345"));
		
	}
	
	
	
	
}
