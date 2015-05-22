package ka3;

import java.math.BigInteger;
import java.security.SecureRandom;


public class RSA {

	// Es sollte für jede Verschlüsselung ein Keypaar erstellt werden.
	// Um dies zu ermöglichen wird pro Keypaar eine RSA Klasse davon instanziert
	private BigInteger d=null, N=null, e=null;
	
	/*
	// Funktion zum Bugfixen, sobald Verschlüsselung läuft wird diese gelöscht
	public void bugfix() {
		String str = "Solo";
		byte[] code;
		
		this.createKeys();
		System.out.println("N: " + this.N + "; e: " + this.e + "; d: " + this.d);
		
		System.out.println("Message: " + str);
		code = this.encrypt(str);
		str = this.decrypt(code);
		System.out.println("Decrypted: " + str);
		
	}
	*/
	
	public RSA(){
		this.createKeys();
	}
	
	// Funktion zum Erstellen der Keys innerhalb dieser Instanz
	public void createKeys(){
		BigInteger p = getPrime(), q = getPrime(), phi=null; // p und q initialisieren
		
		N = p.multiply(q); // N berechnen
		phi = (p.add(new BigInteger("-1"))).multiply((q.add(new BigInteger("-1")))); // Phi berechnen
		
		// ###########################################################
		//System.out.println("p: " + p + " q: " + q); // Debug Zeile #
		// ###########################################################
		
		do{ // e erstellen, falls ein falsches erstellt wird, erneut abfragen (Vorgabe 1 < e < phi, sowie gcd(e, phi) == 1)
			e = getPrime();
		} while(e.compareTo(new BigInteger("1")) <= 0 || e.compareTo(phi) > 0 || e.gcd(phi).compareTo(new BigInteger("1")) != 0);
		
		// ##########################################################################
		//System.out.println("phi: " + phi + " gcd: " + e.gcd(phi)); // Debug Zeile #
		// ##########################################################################
		
		BigInteger[] erg = new BigInteger[2];
		erg = ExtEukl(phi, e); // Euklidscher Algorithmus durchlaufen
		
		if (erg == null){ // Check ob erg != null
			System.out.println("ERROR: Euklid got wrong value. Pop out ...");
			return;
		}
		
		if (erg[1].multiply(phi).add(erg[0].multiply(e)) == new BigInteger("1")){ // Die übergebenen Werte sortieren, um die richtige Zahl für d zu bekommen
		}
		else{
			BigInteger cache;
			cache = erg[1];
			erg[1] = erg[0];
			erg[0] = cache;
		}
		
		if (erg[0].compareTo(new BigInteger("0")) < 0){ // Abfrage ob d negativ, wenn ja, dann -d + phi
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
	
	// Primzahlgenerator
	private static BigInteger getPrime(){
		int maxBitLength = 1024; // Größe der Primzahlen
		SecureRandom rand = new SecureRandom();
		int n;
		for (n = rand.nextInt(maxBitLength); n < 2; n = rand.nextInt(maxBitLength)){ // n darf nicht kleiner als 2 sein für probablePrime
		}
		BigInteger prime = BigInteger.probablePrime(n, rand); // Generierung der Primzahl
		return prime;
	}
	
	// Selbstgeschriebener Euklid Algorithmus
	private static BigInteger[] ExtEukl(BigInteger a, BigInteger b){
		BigInteger[] arr = new BigInteger[2];
		BigInteger cache;
		
		if (a.compareTo(b) < 0 || a.compareTo(new BigInteger("0")) < 0 || b.compareTo(new BigInteger("0")) < 0){ // Check für unpassende Parameter in der 1. Funktion
			System.out.println("ERROR: Wrong parameter values.");
			return null;
		}
		
		if(b.compareTo(new BigInteger("0")) <= 0){ // Abbruchbedingung für die rekursive Funktion
			arr[0] = new BigInteger("1");
			arr[1] = new BigInteger("0");
			return arr;
		}
		
		arr = ExtEukl(b, a.mod(b)); // Start der Rekursion
		arr[0] = arr[1].multiply(a.divide(b).negate()).add(arr[0]); // Berechnung der Parameter d und k pro Step
		cache = arr[1]; // Vertauschen der Variablen für rekursive Übergabe.
		arr[1] = arr[0];
		arr[0] = cache;
		
		return arr;
	}
	
	// VerschlüsselungsFunktion
	public byte[] encrypt(String str){
		BigInteger message = new BigInteger(str.getBytes()), code;
		code = message.modPow(e, N);
		return code.toByteArray();
	}
	
	// Entschlüsselungsfunktion
	public String decrypt(byte[] str){
		BigInteger code = new BigInteger(str), message;
		message = code.modPow(d, N);
		String out = new String(message.toByteArray());
		return out;
	}
}