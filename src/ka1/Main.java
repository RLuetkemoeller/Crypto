package ka1;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;



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
		
		/*
		String test = RandomStringUtils.randomAscii(250);
		System.out.println("IV:" + Encryption.getIv());
		String t2;
		System.out.println(test);
		t2 = Encryption.xor(test,Encryption.getIv());
		System.out.println(t2);
		test = Encryption.xor(t2,Encryption.getIv());
		System.out.println(test);
		*/
		
		
		
		String str = RandomStringUtils.randomAscii(250);
		System.out.println(str);
		String cry = Encryption.cbcEncode(str);
		System.out.println(cry);
		System.out.println(Encryption.cbcDecode(cry));
		
		
		
	}
	
	
	
	
}
