package ka1;

import java.io.*;
import java.util.BitSet;
import java.util.Random;

public class Encryption {

	//ECB encryption method
	private static String key = "Secrets Ahead";
	private static String iv = "ABCD1234";
	private static int blocksize = 16;
	
	public static void setKey(String _key){Encryption.key = _key;}	
	public static String getKey() { return Encryption.key;}
	public static void setIv(String _iv){Encryption.iv = _iv;}	
	public static String getIv(){return Encryption.iv;}
	public static void setBlocksize(int _size) {Encryption.blocksize = _size;}
	
	public static void init(){
		String s = "";
		
		for (int i=0;i<Encryption.blocksize;i++){
			s += "X";
			//s = s.concat(Math.round(Math.random()*256));
			System.out.println(Math.round(Math.random()*256));
			
			
		}
		Encryption.iv = s;			
	}
	
	
	// Constructor
	private Encryption(){
		
	}
	
	public static String xor(String str1, String str2) throws UnsupportedEncodingException {
		// Check if strings are same length if not exit.
		if (str1.length() != str2.length() || str1.length() == 0)
			return null;
		BitSet b1,b2;
		// convert String into BitSet		
		b1 = BitSet.valueOf(str1.getBytes("UTF-8"));
		b2 = BitSet.valueOf(str2.getBytes("UTF-8"));
		// xor Bitwise Bitset1 with 2
		b1.xor(b2);		
		return new String(b1.toByteArray() , "UTF-8");	
	}	
	public static BitSet bytetobitset(byte[] b) {

		int index = 0,
			length = b.length * 8;
		BitSet bs = new BitSet();
		bs.set(length-1,false);
		for (index=length-1; index>=0; index-=8){
			if ((b[(index-7)/8] & 1) > 0){
				bs.set((index-7));
				System.out.println("Set Bitset on index "+(index-7));
			}
			if ((b[(index-6)/8] & 2) > 0){
				bs.set((index-6));
				System.out.println("Set Bitset on index "+(index-6));
			}
			if ((b[(index-5)/8] & 4) > 0){
				bs.set((index-5));
				System.out.println("Set Bitset on index "+(index-5));
			}
			if ((b[(index-4)/8] & 8) > 0){
				bs.set((index-4));
				System.out.println("Set Bitset on index "+(index-4));
			}
			if ((b[(index-3)/8] & 16) > 0){
				bs.set((index-3));
				System.out.println("Set Bitset on index "+(index-3));
			}
			if ((b[(index-2)/8] & 32) > 0){
				bs.set((index-2));
				System.out.println("Set Bitset on index "+(index-2));
			}
			if ((b[(index-1)/8] & 64) > 0){
				bs.set((index-1));
				System.out.println("Set Bitset on index "+(index-1));
			}
			if ((b[index/8] & 128) > 0){
				bs.set(index);
				System.out.println("Set Bitset on index "+index );
			}
		}
		
		
		return bs;
	}
	public static int cbcEncode(String _inputfile, String _outputfile) throws IOException{
		
		FileInputStream InStream = null;
		FileOutputStream OutStream = null;
		
		try {
			InStream = new FileInputStream(_inputfile);
			OutStream = new FileOutputStream(_outputfile);
			byte[] rBlock = Encryption.iv.getBytes();
			int c;
			int index=0;
			int keylen = Encryption.key.length();
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
	public static int ecbEncode(String _inputfile, String _outputfile) throws IOException{
		
		FileInputStream InStream = null;
		FileOutputStream OutStream = null;
		
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
