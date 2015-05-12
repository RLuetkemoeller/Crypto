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
		e = getPrime();
		
		N = p.multiply(q);
		phi = (p.add(new BigInteger("-1"))).multiply((q.add(new BigInteger("-1"))));
		
		while (phi.gcd(e) == new BigInteger("1")){
			e = getPrime();
		}
		
		ExtendedEuklid eukl = new ExtendedEuklid();
		eukl.calc(phi, e);
		
		if (eukl.v.compareTo(new BigInteger("0")) < 0){
		d=phi.subtract(eukl.v);
		}
		
		else{
			d=eukl.v;
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
		int maxBitLength = 1024; // Größe der Primzahlen
		SecureRandom rand = new SecureRandom();
		int n;
		for (n = rand.nextInt(maxBitLength); n < 10; n = rand.nextInt(maxBitLength)){
		}
		BigInteger prime = BigInteger.probablePrime(n, rand);
		return prime;
	}
}