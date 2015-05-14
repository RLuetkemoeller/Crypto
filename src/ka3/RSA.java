package ka3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private BigInteger d=null;
	public BigInteger N=null, e=null;
	
	public static void main(String[] args) {
		String str = "Solo";
		
		RSA keyone = new RSA();
		keyone.createKeys();
		System.out.println("N: " + keyone.N + "; e: " + keyone.e + "; d: " + keyone.d);
		
		
		System.out.println("Message: " + str);
		str = keyone.encrypt("Solo");
		System.out.println("Encrypted: " + str);
		str = keyone.decrypt(str);
		System.out.println("Decrypted: " + str);
		
	}
	
	public void createKeys(){
		BigInteger p = getPrime(), q = getPrime(), phi=null;
		
		N = p.multiply(q);
		phi = (p.add(new BigInteger("-1"))).multiply((q.add(new BigInteger("-1"))));
		
		do{
			e = getPrime();
		} while(e.compareTo(new BigInteger("0")) < 0 || e.compareTo(phi) >= 0);
		
		while (phi.gcd(e) == new BigInteger("1")){
			e = getPrime();
		}
		
		
		
		System.out.println("phi: " + phi);
		
		
		
		BigInteger[] erg = new BigInteger[2];
		erg = ExtEukl(phi, e);
		
		if (erg == null){
			System.out.println("ERROR: Euklid got wrong value. Pop out ...");
			return;
		}
		
		if (erg[1].multiply(phi).add(erg[0].multiply(e)) == new BigInteger("1")){
		}
		else{
			BigInteger cache;
			cache = erg[1];
			erg[1] = erg[0];
			erg[0] = cache;
		}
		
		if (erg[0].compareTo(new BigInteger("0")) < 0){
		d=erg[0].add(phi);
		}
		
		else{
			d=erg[0];
		}
	}
	
	// Checkfunktion, nicht benötigt, da die Probability für probablePrime bei 2^-100 liegt
	/*
	private static boolean isPrime(BigInteger n){
		for(BigInteger i = new BigInteger("2"); i.compareTo(n) < 0; i.add(new BigInteger("1"))){
			if(n.mod(i) == new BigInteger("0")){
				return false;
			}
			System.out.println("i: " + i + "; mod: " + n.mod(i));
		}
		return true;
	}
	*/
	
	private static BigInteger getPrime(){
		int maxBitLength = 10; // Größe der Primzahlen
		SecureRandom rand = new SecureRandom();
		int n;
		for (n = rand.nextInt(maxBitLength); n < 2; n = rand.nextInt(maxBitLength)){
		}
		BigInteger prime = BigInteger.probablePrime(n, rand);
		return prime;
	}
	
	private static BigInteger[] ExtEukl(BigInteger a, BigInteger b){
		BigInteger[] arr = new BigInteger[2];
		BigInteger cache;
		
		if (a.compareTo(b) < 0 || a.compareTo(new BigInteger("0")) < 0 || b.compareTo(new BigInteger("0")) < 0){
			System.out.println("ERROR: Wrong parameter values.");
			return null;
		}
		
		if(b.compareTo(new BigInteger("0")) <= 0){
			arr[0] = new BigInteger("1");
			arr[1] = new BigInteger("0");
			return arr;
		}
		
		arr = ExtEukl(b, a.mod(b));
		arr[0] = arr[1].multiply(a.divide(b).negate()).add(arr[0]);
		cache = arr[1];
		arr[1] = arr[0];
		arr[0] = cache;
		
		return arr;
	}
	
	public String encrypt(String str){
		BigInteger strInt = new BigInteger(str.getBytes()), code;
		code = strInt.modPow(e, N);
		str = new String(code.toByteArray());
		return str;
	}
	
	public String decrypt(String str){
		BigInteger strInt = new BigInteger(str.getBytes()), message;
		message = strInt.modPow(d, N);
		str = new String(message.toByteArray());
		return str;
	}
}