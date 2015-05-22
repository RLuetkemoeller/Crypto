package ka3;

public class Kicker {

	public static void main(String[] args) {
		String message = "Dies ist eine sensible Nachricht."; // Erstellen einer Nachricht.
		byte[] code; // Initialisieren eines Byte Arrays f�r �bertragung der verschl�sselten Nachricht.
		
		System.out.println("Nachricht: " + message); // Ausgabe der Nachricht.
		
		RSA rsaKeyOne = new RSA(); // Erstellen eines RSA Pakets.
		
		code = rsaKeyOne.encrypt(message); // Verschl�sseln der Nachricht.
		
		System.out.println("Code Byte:"); // Ausgabe der verschl�sselten Nachricht.
		for (int i = 0; i < code.length; i++){
			System.out.print("[" + i + "]:" + code[i] + ", ");
		}
		System.out.println();
		
		message = rsaKeyOne.decrypt(code); // Entschl�sseln der Nachricht.
		
		System.out.println("Entschl�sselte Nachricht: " + message); // Ausgabe der entschl�sselten Nachricht.
	}

}
