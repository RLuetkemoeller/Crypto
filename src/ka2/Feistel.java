package ka2;

import java.util.Random;

import ka1.Encryption;

public class Feistel {

	public static void main(String[] args) {
		
		encrypt("mamsa", "papa");
		
		
		
	}
	
	public static String encrypt(String str, String key) {
		
		if (str == null || key == null){
			System.out.println("ERROR: Uebergebener String oder Key nicht vorhanden!");
			return null;
		}
		
		String left=null, right=null, code=null;
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		Random rand = new Random();
		int rchar;
		
		while ((str.length()%key.length()) != 0){
			rchar = rand.nextInt(alphabet.length());
			str += alphabet.charAt(rchar);
		}
		
		left = str.substring(0, (str.length()/2));
		right = str.substring((str.length()/2)+1, str.length());
		
		System.out.println("Right:" + right);
		System.out.println("Key:" + key);
		
			code = Encryption.xor2(right, key);
			System.out.println("ERROR: Unsupported Encoding Exception für UTF-8 -> XOR kann nicht durchgeführt werden.");
		
		
		
		
		System.out.println(code);
		
		return str;
	}

}
