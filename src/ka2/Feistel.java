package ka2;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ka1.Encryption;

public class Feistel {
	static int Maxrounds = 3;
	static int blocksize = 16;
	static List<String> keys = new ArrayList<String>();

	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str = "Dies ist eine Sehr Wichtige Nachricht. der Sack Reis in China steht noch !!", key="Schluessel";
		System.out.println("Nachricht: " + str);
		
		str=encrypt(str, key);
		System.out.println("Verschlüsselt: " + str);
		str=decrypt(str, key);
		System.out.println("Entschlüsselt: " + str);
		

		
	}
	
	public static String encrypt(String str, String key) throws UnsupportedEncodingException {
		// Fehlerkontrolle
		
		
		if (str == null || key == null){
			System.out.println("ERROR: Uebergebener String oder Key nicht vorhanden!");
			return null;
		}
		
		// Ein und Ausgabestringliste
		List<String> in = Encryption.splitInChunks(str, blocksize);;
		List<String> out = new ArrayList<String>();
		
		// Key auf passende länge bearbeiten
		Encryption.setKey(key);
		Encryption.setBlocksize(blocksize/2);
		Encryption.init();
		key = Encryption.getKey();
		int idx = 0;
		String left=null, right=null, code="", cache=null, output="";
		
		
		// Schlüssel erstellen
		keys.clear();
		for (idx=0; idx < Maxrounds; idx++){
			keys.add(idx, key);
			key = key.substring(1) + key.charAt(0);
		}

		// Für jedes Schnipsel in Arraylist in ...
		for (String piece : in) {
			
			// String splitten
			left = piece.substring(0,blocksize/2);
			right = piece.substring(blocksize/2,blocksize);
			for (int round = 1; round <= Maxrounds; round++){
				key = keys.get(round-1);
				
				// xOhrn
				code = Encryption.xor(key, right);
				code = Encryption.xor(left,code);
				
				// Vertauschen außer im letzten Schritt
				if (round < 3){
					left = right;
					right = code;
					
				} else {
					left = code;
				}
			}
			// In die Outliste hinzufügen
			out.add(left+right);
		}
		// String zusammensetzen
		for (String s : out){
			output += s;
		}		
		return output;
		
	}
	
	public static String decrypt(String str, String key) throws UnsupportedEncodingException {
		// Fehlerkontrolle
		
		
		if (str == null || key == null){
			System.out.println("ERROR: Uebergebener String oder Key nicht vorhanden!");
			return null;
		}
		
		// Ein und Ausgabestringliste
		List<String> in = Encryption.splitInChunks(str, blocksize);;
		List<String> out = new ArrayList<String>();
		
		// Key auf passende länge bearbeiten
		Encryption.setKey(key);
		Encryption.setBlocksize(blocksize/2);
		Encryption.init();
		key = Encryption.getKey();
		int idx = 0;
		String left=null, right=null, code="", cache=null, output="";
		
		
		// Schlüssel erstellen
		keys.clear();
		for (idx=0; idx < Maxrounds; idx++){
			keys.add(idx, key);
			key = key.substring(1) + key.charAt(0);
		}



		
		for (String piece : in) {
			
			left = piece.substring(0,blocksize/2);
			right = piece.substring(blocksize/2,blocksize);
			for (int round = 1; round <= Maxrounds; round++){
				key = keys.get(Maxrounds-round);

				code = Encryption.xor(key, right);
				code = Encryption.xor(left,code);
				
				if (round < 3){
					left = right;
					right = code;
					
				} else {
					left = code;
				}
			}
			out.add(left+right);
		}
		
		for (String s : out){
			output += s;
		}		
		return output;
		
	}

}
