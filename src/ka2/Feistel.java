package ka2;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import ka1.Encryption;

public class Feistel {

	public static void main(String[] args) {
		
		String str = "Dies ist eine wichtige Nachricht.", key="Schluessel";
		System.out.println("Nachricht: " + str);
		
		str=encrypt(str, key);
		System.out.println("Verschlüsselt: " + str);
		str=encrypt(str, key);
		System.out.println("Entschlüsselt: " + str);
		
	}
	
	public static String encrypt(String str, String key) {
		if (str == null || key == null){
			System.out.println("ERROR: Uebergebener String oder Key nicht vorhanden!");
			return null;
		}
		
		String left=null, right=null, code="", code2=null, subRight=null, subCode=null, cache=null;
		String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ123456789";
		Random rand = new Random();
		int rchar;
		
		while (((str.length()) % key.length()) != 0 || str.length() <= key.length() || ((str.length() / 2) % key.length()) != 0){
			rchar = rand.nextInt(alphabet.length());
			str += alphabet.charAt(rchar);
		}
		
//		System.out.println("Keylength: " + key.length());
//		System.out.println("Stringlength: " + str.length());
		
		left = str.substring(0, str.length()/2);
		right = str.substring(str.length()/2, str.length());
		
//		System.out.println("calc: " + (right.length()/key.length()));
		
		for (int round = 1; round <= 3; round++){
			for (int sub = 0; sub < ((right.length()/key.length())); sub++){
				if (sub == 0){
					subRight = right.substring(0, key.length());
//					System.out.println("SubRight: " + subRight);
				}
				else{
					subRight = right.substring((key.length()*sub), (key.length()*(sub+1)));
//					System.out.println("SubRight[" + sub + "]: " + subRight);
				}
				try {
					subCode = Encryption.xor(subRight, key);
					if (subCode == null){
						System.out.println("ERROR: Unequal lenght of strings for XOR function in round: " + round);
						return null;
					}
				} catch (UnsupportedEncodingException e) {
					System.out.println("ERROR: Unsupported Encoding Exception in the 1st XOR function in round: " + round);
					return null;
				}
				code = code.concat(subCode);
//				System.out.println("Code: " + code);
			}
			
			try {
				code2 = Encryption.xor(left, code);
				if (code2 == null){
					System.out.println("ERROR: Unequal lenght of strings for 2nd XOR function in round: " + round);
				}
			} catch (UnsupportedEncodingException e) {
				System.out.println("ERROR: Unsupported Encoding Exception in the 2nd XOR function in round: " + round);
				return null;
			}
			
			left = right;
			right = code2;
			cache = code;
			code = key.substring(1) + key.charAt(0);
			key = code;
			code = "";
		}
		
		return right + left;
	}

}
