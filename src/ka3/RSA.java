package ka3;

import java.math.BigInteger;

public class RSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RSA x = new RSA();
		x.init();
		x.gcd();
		
	}
	
	public void init(){
		p = new BigInteger("11");
		q = new BigInteger("13");
		N = p.multiply(q);
		phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
	}
	public BigInteger p,q,N,phi;
	
	public BigInteger gcd(){
		BigInteger a,ax,b,bx,c;
		c = p;
		p = q;


		
		return c;
	};
	
	public void setp(BigInteger s){p = s;};
	public void setq(BigInteger s){q = s;};
	

}
