package ka2;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import ka1.Encryption;

public class Feistel {

	public static void main(String[] args) {
		
		encrypt("mamsa", "papa");
		
		
		
	}
	
	public static String encrypt(String str, String key) {
		
		String left=null, right=null, code=null;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random rand = new Random();
		int rchar;
		
		if ((str.length()%key.length()) != 0){
			rchar = rand.nextInt(128);
			str += Integer.toString(rchar);
		}
		
		
		
		left = str.substring(0, (str.length()/2));
		right = str.substring((str.length()/2), str.length());
		
		System.out.println(right);
		System.out.println(key);

		
		while ((right.length()%key.length()) != 0){
			
		}
		
		try {
			code = Encryption.xor(right, key);
		} catch (UnsupportedEncodingException e) {
			System.out.println("ERROR: Unsupported Encoding Exception für UTF-8 -> XOR kann nicht durchgeführt werden.");
			return null;
		}
		
		
		
		
		System.out.println(code);
		
		return str;
	}

}
