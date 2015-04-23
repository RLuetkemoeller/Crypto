package ka2;

import java.io.UnsupportedEncodingException;

import ka1.Encryption;

public class Feistel {

	public static void main(String[] args) {
		
		encrypt("mamsa", "papa");
		
		
		
	}
	
	public static String encrypt(String str, String key) {
		
		String left=null, right=null, code=null;
		int i;
		
		if ((str.length()%2) != 0){
			i = str.length()/2 + 1;
		}
		else {
			i = str.length()/2;
		}
		
		
		left = str.substring(0, i);
		right = str.substring(i, str.length());
		
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
