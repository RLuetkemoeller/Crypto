package ka3;

public class Kicker {

	public static void main(String[] args) {
		String message = "Dies ist eine sensible Nachricht."; // Erstellen einer Nachricht.
		byte[] code; // Initialisieren eines Byte Arrays für Übertragung der verschlüsselten Nachricht.
		
		System.out.println("Nachricht: " + message); // Ausgabe der Nachricht.
		
		RSA rsaKeyOne = new RSA(); // Erstellen eines RSA Pakets.
		
		code = rsaKeyOne.encrypt(message); // Verschlüsseln der Nachricht.
		
		System.out.println("Code Byte:"); // Ausgabe der verschlüsselten Nachricht.
		for (int i = 0; i < code.length; i++){
			System.out.print("[" + i + "]:" + code[i] + ", ");
		}
		System.out.println();
		
		message = rsaKeyOne.decrypt(code); // Entschlüsseln der Nachricht.
		
		System.out.println("Entschlüsselte Nachricht: " + message); // Ausgabe der entschlüsselten Nachricht.
	}

}
