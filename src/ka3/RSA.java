package ka3;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RSA {

	private BigInteger d=null;
	public BigInteger N=null, e=null;
	
	public static void main(String[] args) {
	
		RSA keyone = new RSA();
		keyone.createKeys();
		System.out.println("PublicKey: " + keyone.e + " " + keyone.N);
		
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
		System.out.println("e: " + e);
		
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
		
		System.out.println("d: " + erg[0]);
		System.out.println("k: " + erg[1]);
		
		if (erg[0].compareTo(new BigInteger("0")) < 0){
		d=phi.subtract(erg[0]);
		}
		
		else{
			d=erg[0];
		}
	}
	
	// Checkfunktion, nicht ben�tigt, da die Probability f�r probablePrime bei 2^-100 liegt
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
		int maxBitLength = 128; // Gr��e der Primzahlen
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
}