package ka3;

import java.math.BigInteger;

// Algorithmus verwendet von folgendem PDF, Seite 5
// http://ls11-www.cs.uni-dortmund.de/people/rahmann/teaching/ss2008/EleganteAlgorithmen/skript.pdf

public class ExtendedEuklid{
	public BigInteger g=null, u=null, v=null;
	
	public void calc(BigInteger a, BigInteger b){
	BigInteger q, r, s, t;
	u = t = new BigInteger("1");
	v = s = new BigInteger("0");
	
	while (b.compareTo(new BigInteger("0")) > 0){
	q = a.divide(b);
	r = a.subtract(q.multiply(b)); a=b; b=r;
	r=u.subtract(q.multiply(s)); u=s; s=r;
	r=v.subtract(q.multiply(t)); v=t; t=r;
	}
	g=a;
	}
}
