package ka3;

import java.math.BigInteger;

public class RSA {

	private BigInteger d=null;
	public BigInteger N=null, e=null;
	
	public static void main(String[] args) {
	
		RSA keyone = new RSA();
		keyone.createKeys();
		System.out.println("PublicKey: " + keyone.e + " " + keyone.N);
		
	}
	
	public void createKeys(){
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		//! Random function for p, q and e needed !
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		BigInteger p = new BigInteger("11"), q = new BigInteger("13"), phi=null;
		e = new BigInteger("23");
		
		N = p.multiply(q);
		phi = (p.add(new BigInteger("-1"))).multiply((q.add(new BigInteger("-1"))));
		
		while (phi.gcd(e) == new BigInteger("1")){
			//!!!!!!!!!!!!!!!!!!!!!!!!!!
			//! Random generator for e !
			//!!!!!!!!!!!!!!!!!!!!!!!!!!
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
}