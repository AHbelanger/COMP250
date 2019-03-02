/*	Name: Antoine Hamasaki-Belanger
 * 	Id: 260800991
 */
package assignments2018.a2template;
import java.math.BigInteger;
import java.util.Iterator;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}
	
	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}
	
	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t) {	
	
		//discard terms with either 0 coefficient or negative exponent
		if ((t.getCoefficient().compareTo(new BigInteger("0")) == 0) || (t.getExponent() < 0) )
			return;
		//add element to first index if polynomial is empty
		else if (this.polynomial.size() == 0) 
			this.polynomial.addFirst(t);
		
		else {
			int index = 0;
			for (Term currentTerm : this.polynomial) {
				if (t.getExponent() == currentTerm.getExponent()) {
					//add coefficients together for terms with same exponent
					currentTerm.setCoefficient(currentTerm.getCoefficient().add(t.getCoefficient())); 
					//remove term if coefficient equals 0
					if (currentTerm.getCoefficient().equals(new BigInteger("0"))) 
						this.polynomial.remove(index);
					return;
				}
				
				if (t.getExponent() > currentTerm.getExponent()) {
					this.polynomial.add(index, t);
					return;
				}
				//keep track of currentTerm index
				index++;
			}
			this.polynomial.addLast(t);
		}
	}
	
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2) {
		Polynomial sum = new Polynomial();
		Polynomial temp1 = p1.deepClone();
		Polynomial temp2 = p2.deepClone();
		
		Iterator<Term> iterator1 = temp1.polynomial.iterator();
		Iterator<Term> iterator2 = temp2.polynomial.iterator();
		
		Term t1 = iterator1.next(); 
		Term t2 = iterator2.next(); 
		
		while (true) {
				if (t1.getExponent() == (t2.getExponent())) {
					//if coefficients for both is 0 which means exponents for both is 0 too, quit adding
					if (t2.getCoefficient().equals(new BigInteger("0")) && t1.getCoefficient().equals(new BigInteger("0")) )
						break;
					//if sum of coefficients equals 0, don't add to result polynomial
					else if ((t1.getCoefficient().add(t2.getCoefficient())).equals(new BigInteger("0"))){
						t1 = (iterator1.hasNext()) ? iterator1.next() : new Term(0, new BigInteger("0"));
						t2 = (iterator2.hasNext()) ? iterator2.next() : new Term(0, new BigInteger("0"));
					}
					//sum of coefficients not equal to 0
					else {	
						sum.addTermLast(new Term(t1.getExponent(), t1.getCoefficient().add(t2.getCoefficient())));
						t1 = (iterator1.hasNext()) ? iterator1.next() : new Term(0, new BigInteger("0"));
						t2 = (iterator2.hasNext()) ? iterator2.next() : new Term(0, new BigInteger("0"));
					}
				}
				//exponent of t1 is larger than t2
				else if (t1.getExponent() > t2.getExponent()) {
					sum.addTermLast(t1);
					t1 = (iterator1.hasNext()) ? iterator1.next() : new Term(0, new BigInteger("0"));
				}
				else if (t1.getExponent() < t2.getExponent()) {
					sum.addTermLast(t2);
					t2 = (iterator2.hasNext()) ? iterator2.next() : new Term(0, new BigInteger("0"));
				}
			}
		return sum;
	}
	
	
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t) {	
		
		if (t.getCoefficient().equals(new BigInteger("0"))) 
			this.polynomial.clear();
		
		else {
			for (Term currentTerm : this.polynomial) {
				currentTerm.setCoefficient(currentTerm.getCoefficient().multiply(t.getCoefficient()));
				currentTerm.setExponent(t.getExponent() + currentTerm.getExponent());
			}
		}	
	}
	
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2) {
		Polynomial product = new Polynomial();
		
		if ((p1.size() == 0) || (p2.size() == 0)) 
			return product;
		
		else {
			product = p1.deepClone();
			Polynomial temp2 = p2.deepClone();
			product.multiplyTerm(temp2.polynomial.get(0));
			temp2.polynomial.removeFirst();

			Polynomial temp1;
			
			for (Term currentTerm : temp2.polynomial) {
				temp1 = p1.deepClone();
				temp1.multiplyTerm(currentTerm);
				product = Polynomial.add(product, temp1);
			}
		}
		return product;
	}
	
	
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x) {
		//Declare and initializing variables
		BigInteger result, temp; 
		result = new BigInteger("0");
		int index, exp, horner;
		index = exp = 0;
		
		Iterator<Term> iterator = this.polynomial.iterator();

		for (Term currentTerm : this.polynomial) {
			if (index > 0) {
				horner = (exp - currentTerm.getExponent());
				for (int i = 0; i < horner - 1; i++) 
					result = result.multiply(x);
			}
			iterator.next();
			temp = result.multiply(x);
			result = temp.add(currentTerm.getCoefficient());
			exp = currentTerm.getExponent();
			index++;
			
			
			if (iterator.hasNext() == false) {
				for (int i = 0; i < currentTerm.getExponent(); i++) 
					result = result.multiply(x);	
			}
		}
		return result;
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}
	
	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
