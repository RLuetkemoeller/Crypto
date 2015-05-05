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
		
		while ((str.length()%key.length()) != 0){
			rchar = rand.nextInt(alphabet.length());
			str += alphabet.charAt(rchar);
		}
		
		left = str.substring(0, (str.length()/2));
		right = str.substring((str.length()/2), str.length());
		
		System.out.println("Right:" + right);
		System.out.println("Key:" + key);
		
			code = Encryption.xor(right, key);
			System.out.println("ERROR: Unsupported Encoding Exception für UTF-8 -> XOR kann nicht durchgeführt werden.");
		
		
		
		
		System.out.println(code);
		
		return str;
	}

}
