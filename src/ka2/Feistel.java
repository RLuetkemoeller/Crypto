package ka2;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import ka1.Encryption;

public class Feistel {

	public static void main(String[] args) {
		
		String str=encrypt("mama", "papa");
		System.out.println(str);
		str=encrypt(str, "papa");
		System.out.println(str);
		
	}
	
	public static String encrypt(String str, String key) {
		
		if (str == null || key == null){
			System.out.println("ERROR: Uebergebener String oder Key nicht vorhanden!");
			return null;
		}
		
		String left=null, right=null, code=null, code2=null;
		String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ123456789";
		Random rand = new Random();
		int rchar;
		
		while ((str.length()%key.length()) != 0 || str.length() <= key.length()){
			rchar = rand.nextInt(alphabet.length());
			str += alphabet.charAt(rchar);
		}
		
		left = str.substring(0, str.length()/2);
		right = str.substring(str.length()/2, str.length());
		
		for (int round=1; round <= 2; round++){
			try {
				code = Encryption.xor(right, key);
				if (code == null){
					System.out.println("ERROR: Unequal lenght of strings for XOR function." + round);
				}
			} catch (UnsupportedEncodingException e) {
				System.out.println("ERROR: Unsupported Encoding Exception in the 1st XOR function." + round);
				return null;
			}
			
			try {
				code2 = Encryption.xor(left, code);
				if (code2 == null){
					System.out.println("ERROR: Unequal lenght of strings for 2nd XOR function." + round);
				}
			} catch (UnsupportedEncodingException e) {
				System.out.println("ERROR: Unsupported Encoding Exception in the 2nd XOR function." + round);
				return null;
			}
			
			left=right;
			right=code2;
			code=key.substring(1) + key.charAt(0);
			key=code;
		}
		
		return code2 + left;
	}

}
