package ka1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;


public class Encryption {

	//ECB encryption method
	private static String key = "Secrets Ahead";
	private static String iv = "ABCD1234";
	private static int blocksize = 16;
	
	// Set und Get Functinos ...
	public static void setKey(String _key){Encryption.key = _key;}	
	public static String getKey() { return Encryption.key;}
	public static void setIv(String _iv){Encryption.iv = _iv;}	
	public static String getIv(){return Encryption.iv;}
	public static void setBlocksize(int _size) {Encryption.blocksize = _size;}
	
	
	
	// Alles initialisieren ....
	public static void init(){
		iv = RandomStringUtils.randomAscii(blocksize);
		
		int len = key.length();
		if (key.length() < blocksize){		
			for (int i = key.length(); i < blocksize;i++){
				key += key.charAt(i-len);
			}
		} else {
			key = key.substring(0,blocksize);
		}
	}
	// Eine Funktion um einen String in gleich lange Stücke zu splitten
	public static List<String> splitInChunks(String str, int size) {
		// Länge der liste definieren
	    List<String> ret = new ArrayList<String>((str.length() + size - 1) / size);
	    // Stücke in die Liste kopieren
	    for (int start = 0; start < str.length(); start += size) {
	        ret.add(str.substring(start, Math.min(str.length(), start + size)));
	    }
	    // Den letzen String auffüllen
	    int idx = ret.size()-1;
	    String laststr = ret.get(idx);
	    while (laststr.length() < blocksize) {
	    	laststr = laststr.concat(" ");
	    }
	    ret.set(idx, laststr);
	    return ret;
	}
	
	// Constructor
	private Encryption(){
		
	}
	
	public static String xor(String str1, String str2) throws UnsupportedEncodingException {

		// Gucken ob die beiden Strings auch die selbe länge haben.
		if (str1.length() != str2.length() || str1.length() == 0){
			System.out.println("String1Length: " + str1.length() + " String2Length: " + str2.length());
			return null;
		}
		BitSet b1 = new BitSet(str1.length()*8);
		BitSet b2 = new BitSet(str2.length()*8);
		byte[] by1, by2;
		
		// Konvertieren für XOR später	
		
		by1 = str1.getBytes();
		by2 = str2.getBytes();
		
		b1= BitSet.valueOf(ByteBuffer.wrap(by1));
		b2= BitSet.valueOf(ByteBuffer.wrap(by2));
		
		// XOR
		b1.xor(b2);	
		
		// Falls das letze zeichen 0b00000000 war das nächste bit setzen damit die länge Stimmt...
		b1.set(blocksize*8);
		// Aus dem BitSet wieder einen String machen aber das letzte angefügte zeichen entfernen (b1.set(blocksize*8))
		return (new String(b1.toByteArray())).substring(0,blocksize);	
	}	
	
	// Anfangs selbst geschrieben aber durch valueof nichtmehr benötigt...
	public static BitSet bytetobitset(byte[] b) {

		int index = 0,
			length = b.length * 8;
		BitSet bs = new BitSet();
		bs.set(length-1,false);
		for (index=length-1; index>=0; index-=8){
			if ((b[(index-7)/8] & 1) > 0){
				bs.set((index-7));
				//System.out.println("Set Bitset on index "+(index-7));
			}
			if ((b[(index-6)/8] & 2) > 0){
				bs.set((index-6));
				//System.out.println("Set Bitset on index "+(index-6));
			}
			if ((b[(index-5)/8] & 4) > 0){
				bs.set((index-5));
				//System.out.println("Set Bitset on index "+(index-5));
			}
			if ((b[(index-4)/8] & 8) > 0){
				bs.set((index-4));
				//System.out.println("Set Bitset on index "+(index-4));
			}
			if ((b[(index-3)/8] & 16) > 0){
				bs.set((index-3));
				//System.out.println("Set Bitset on index "+(index-3));
			}
			if ((b[(index-2)/8] & 32) > 0){
				bs.set((index-2));
				//System.out.println("Set Bitset on index "+(index-2));
			}
			if ((b[(index-1)/8] & 64) > 0){
				bs.set((index-1));
				//System.out.println("Set Bitset on index "+(index-1));
			}
			if ((b[index/8] & 128) > 0){
				bs.set(index);
				//System.out.println("Set Bitset on index "+index );
			}
		}
		
		
		return bs;
	}

	// 
	public static String cbcEncode(String _input) throws UnsupportedEncodingException {
		int idx = 0;
		String _output = "";
		// String in Stücke zerlegen.
		List<String> in = splitInChunks(_input, blocksize);
		List<String> out = new ArrayList<String>(in);
		
		// Erste durchlauf mit Initialisierungsvektor anschließend mit dem Vorgänger
		out.set(idx, Encryption.xor(iv, in.get(idx)));
		out.set(idx, Encryption.xor(out.get(idx), Encryption.key));
		
		for (idx=1;idx < in.size(); idx++){
			out.set(idx, Encryption.xor(out.get(idx-1), in.get(idx)));
			out.set(idx, Encryption.xor(out.get(idx), Encryption.key));
		}
		
		// String wieder zusammensetzen und zurückgeben
		for (String s : out){
			_output += s;
		}		
		return _output;
	}
	public static String cbcDecode(String _input) throws UnsupportedEncodingException {
		int idx = 0;
		String _output = "";
		
		// Selbe logik wie cbcEncode nur ungedreht...
		List<String> in = splitInChunks(_input, blocksize);
		List<String> out = new ArrayList<String>(in);
		
		
		
		out.set(idx, Encryption.xor(in.get(idx), Encryption.key));
		out.set(idx, Encryption.xor(iv, out.get(idx)));
		
		
		for (idx=1;idx < in.size(); idx++){
			out.set(idx, Encryption.xor(in.get(idx), Encryption.key));
			out.set(idx, Encryption.xor(in.get(idx-1), out.get(idx)));
			
		}
		for (String s : out){
			_output += s;
		}		
		return _output;
	}
	public static int ecbEncode(String _inputfile, String _outputfile) throws IOException{
		
		FileInputStream InStream = null;
		FileOutputStream OutStream = null;
		// Nicht Weltbewegendes ....
		try {
			InStream = new FileInputStream(_inputfile);
			OutStream = new FileOutputStream(_outputfile);
			
			int c,index=0,keylen;
			keylen = Encryption.key.length();
			int outchar;
			byte[] keybytes = Encryption.key.getBytes();
		    while ((c = InStream.read()) != -1) {
		    	outchar = keybytes[index] ^ c;
		    	index = (index + 1)%keylen;
		    	
		    	OutStream.write(outchar);
		    }
		}finally {
			if (InStream != null) {
				InStream.close();
			}
			if (OutStream != null) {
				OutStream.close();
			}
		}
		
		return 1;
	}
	public static int ecbDecode(String _inputfile, String _outputfile) throws IOException{
		
		FileInputStream InStream = null;
		FileOutputStream OutStream = null;
		// Nicht Weltbewegendes ....

		try {
			InStream = new FileInputStream(_inputfile);
			OutStream = new FileOutputStream(_outputfile);
			
			int c,index=0,keylen;
			keylen = Encryption.key.length();
			int outchar;
			byte[] keybytes = Encryption.key.getBytes();
		    while ((c = InStream.read()) != -1) {
		    	outchar = keybytes[index] ^ c;
		    	index = (index + 1)%keylen;
		    	
		    	OutStream.write(outchar);
		    }
		}finally {
			if (InStream != null) {
				InStream.close();
			}
			if (OutStream != null) {
				OutStream.close();
			}
		}
		
		return 1;
	}
}
