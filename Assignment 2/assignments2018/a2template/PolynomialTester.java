package assignments2018.a2template;

import java.math.BigInteger;

public class PolynomialTester {
	
	/*
	 * Test whethere you clone method works efficiently
	 */
	private static void testClone()
	{	
		System.out.println("***TEST 1: testClone()***");
		
		Polynomial poly = new Polynomial();
		
		poly.addTermLast(new Term(0, new BigInteger("1")));
		poly.addTermLast(new Term(1, new BigInteger("2")));
		poly.addTermLast(new Term(2, new BigInteger("3")));
		poly.addTermLast(new Term(3, new BigInteger("4")));
		poly.addTermLast(new Term(4, new BigInteger("5")));
		
		System.out.println("Expected: " + poly);
		
		Polynomial clone = poly.deepClone();
		
		System.out.println("Obtained: " + clone);
		
		if (clone.checkEqual(poly))
		{
			System.out.println("Passed: deepClone()");
			System.out.println("***TEST ENDED***\n");
			
			return;
		}
		System.out.println("Failed: deepClone()");
		
		System.out.println("***TEST ENDED***\n");
	}
	
	private static void testClone_2()
	{	
		System.out.println("***TEST 1: testClone()***");
		
		Polynomial poly = new Polynomial();
		
		poly.addTermLast(new Term(0, new BigInteger("1")));
		poly.addTermLast(new Term(1, new BigInteger("-2")));
		poly.addTermLast(new Term(2, new BigInteger("3")));
		poly.addTermLast(new Term(3, new BigInteger("-4")));
		poly.addTermLast(new Term(4, new BigInteger("5")));
		
		System.out.println("Expected: " + poly);
		
		Polynomial clone = poly.deepClone();
		
		System.out.println("Obtained: " + clone);
		
		if (clone.checkEqual(poly))
		{
			System.out.println("Passed: deepClone()");
			System.out.println("***TEST ENDED***\n");
			
			return;
		}
		System.out.println("Failed: deepClone()");
		
		System.out.println("***TEST ENDED***\n");
	}
	
	// Tests if the terms are arranged in correct order.
	private static void testAddTerm_1()
	{
		System.out.println("TEST: testAddTerm_1");

		Polynomial p1 = new Polynomial(); // reference polynomial
		Polynomial p2 = new Polynomial();
		
		p1.addTermLast(new Term(4, new BigInteger("4")));
		p1.addTermLast(new Term(3, new BigInteger("3")));
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(1, new BigInteger("1")));
		
		p2.addTerm(new Term(1, new BigInteger("1")));
		p2.addTerm(new Term(2, new BigInteger("2")));
		p2.addTerm(new Term(3, new BigInteger("3")));
		p2.addTerm(new Term(4, new BigInteger("4")));
		
		System.out.println("Expected: " + p1);
		System.out.println("Obtained: " + p2);
				
		if (p1.size() != 0 && p2.size() != 0 && p1.checkEqual(p2))
		{
			System.out.println("Passed: addTerm() - Test1");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: addTerm() - Test1");
		System.out.println("***TEST ENDED***\n");

	}
	
	// Tests if the terms are arranged in correct order.
	private static void testAddTerm_2()
	{
		System.out.println("TEST: testAddTerm_2");
		
		Polynomial p1 = new Polynomial(); // reference polynomial
		Polynomial p2 = new Polynomial();
		
		String max = Integer.toString(Integer.MAX_VALUE); 
		String min = Integer.toString(Integer.MIN_VALUE); 
		
		p1.addTermLast(new Term(100, new BigInteger(max)));
		p1.addTermLast(new Term(4, new BigInteger("4")));
		p1.addTermLast(new Term(3, new BigInteger("3")));
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(1, new BigInteger("1")));
		p1.addTermLast(new Term(0, new BigInteger(min)));
		
		p2.addTerm(new Term(100, new BigInteger(max)));
		p2.addTerm(new Term(1, new BigInteger("1")));
		p2.addTerm(new Term(2, new BigInteger("2")));
		p2.addTerm(new Term(0, new BigInteger(min)));
		p2.addTerm(new Term(3, new BigInteger("3")));
		p2.addTerm(new Term(4, new BigInteger("4")));
		
		System.out.println("Expected: " + p1);
		System.out.println("Obtained: " + p2);
				
		if (p1.size() != 0 && p2.size() != 0 && p1.checkEqual(p2))
		{
			System.out.println("Passed: addTerm() - Test2");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: addTerm() - Test2");
		System.out.println("***TEST ENDED***\n");
	}
	
	
	// Check if adding a new term updates/removes an existing term in the polynomial.  
	private static void testAddTerm_3()
	{
	
	System.out.println("TEST: testAddTerm_3");
		
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial(); // reference polynomial
		
		p1.addTerm(new Term(4, new BigInteger("4")));
		p1.addTerm(new Term(3, new BigInteger("3")));
		p1.addTerm(new Term(2, new BigInteger("2")));
		p1.addTerm(new Term(1, new BigInteger("1")));
		p1.addTerm(new Term(2, new BigInteger("-1")));
		
		p2.addTermLast(new Term(4, new BigInteger("4")));
		p2.addTermLast(new Term(3, new BigInteger("3")));
		p2.addTermLast(new Term(2, new BigInteger("1")));
		p2.addTermLast(new Term(1, new BigInteger("1")));
		
		System.out.println("Expected: " + p1);
		System.out.println("Obtained: " + p2);
						
		if (p1.size() != 0 && p2.size() != 0 && p1.checkEqual(p2))
		{
			System.out.println("Passed: addTerm() - Test3");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: addTerm() - Test3");
		System.out.println("***TEST ENDED***\n");

	}
	
	// Check if adding a new term updates/removes an existing term in the polynomial.  
	// CHecks for invalid exponents
	private static void testAddTerm_4()
	{
	
		System.out.println("TEST: testAddTerm_3");
		
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial(); // reference polynomial
		
		p1.addTerm(new Term(10, new BigInteger("100000")));
		p1.addTerm(new Term(4, new BigInteger("4")));
		p1.addTerm(new Term(3, new BigInteger("3")));
		p1.addTerm(new Term(2, new BigInteger("2")));
		p1.addTerm(new Term(1, new BigInteger("1")));
		p1.addTerm(new Term(2, new BigInteger("-1")));
		p1.addTerm(new Term(10, new BigInteger("-1"))); // this shouldn't be added

		
		p2.addTermLast(new Term(10, new BigInteger("99999")));
		p2.addTermLast(new Term(4, new BigInteger("4")));
		p2.addTermLast(new Term(3, new BigInteger("3")));
		p2.addTermLast(new Term(2, new BigInteger("1")));
		p2.addTermLast(new Term(1, new BigInteger("1")));
		
		
		System.out.println("Expected: " + p1);
		System.out.println("Obtained: " + p2);
						
		if (p1.size() != 0 && p2.size() != 0 && p1.checkEqual(p2))
		{
			System.out.println("Passed: addTerm() - Test3");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: addTerm() - Test4");
		System.out.println("***TEST ENDED***\n");

	}
	
	// Test case when there are no terms with same exponent in two polynomial
	private static void testAdd_1()
	{
		System.out.println("TEST: testAdd_1");

		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		p1.addTerm(new Term(2, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		
		p2.addTerm(new Term(3, new BigInteger("1")));
		p2.addTerm(new Term(4, new BigInteger("2")));
		
		Polynomial sum = Polynomial.add(p1, p2);
		Polynomial expectedSum = new Polynomial(); // reference
		
		System.out.println("Polynomial 1: " + p1);
		System.out.println("Polynomial 2: " + p2);
		
		expectedSum.addTermLast(new Term(4, new BigInteger("2")));
		expectedSum.addTermLast(new Term(3, new BigInteger("1")));
		expectedSum.addTermLast(new Term(2, new BigInteger("2")));
		expectedSum.addTermLast(new Term(0, new BigInteger("1")));
		
		System.out.println("Expected sum: " + expectedSum);
		System.out.println("Obtained sum: " + sum);
		
		if (sum != null && expectedSum.checkEqual(sum))
		{
			System.out.println("Passed: add() - Test 1");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: add() - Test 1");
		System.out.println("***TEST ENDED***\n");

	}
	
	// Test case when there are terms with same exponent in two polynomial
	private static void testAdd_2()
	{
		System.out.println("TEST: testAdd_2");
		
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
			
		p1.addTerm(new Term(2, new BigInteger("2")));
		p1.addTerm(new Term(0, new BigInteger("1")));
		p1.addTerm(new Term(3, new BigInteger("1")));
		p1.addTerm(new Term(4, new BigInteger("2")));
		
		p2.addTerm(new Term(3, new BigInteger("2")));
		p2.addTerm(new Term(2, new BigInteger("5")));
		
		System.out.println("Polynomial 1: " + p1);
		System.out.println("Polynomial 2: " + p2);
			
		Polynomial sum = Polynomial.add(p1, p2);
		Polynomial expectedSum = new Polynomial(); //reference
		expectedSum.addTermLast(new Term(4, new BigInteger("2")));
		expectedSum.addTermLast(new Term(3, new BigInteger("3")));
		expectedSum.addTermLast(new Term(2, new BigInteger("7")));
		expectedSum.addTermLast(new Term(0, new BigInteger("1")));
		
		System.out.println("Expected sum: " + expectedSum);
		System.out.println("Obtained sum: " + sum);
			
		if (sum != null && expectedSum.checkEqual(sum))
		{
			System.out.println("Passed: add() - Test 2");
			System.out.println("***TEST ENDED***\n");
			
			return;
		}
		System.out.println("Failed: add() - Test 2");
		System.out.println("***TEST ENDED***\n");

		}
	
	// Small polynomial test
	private static void testEval_1()
	{
		
		System.out.println("TEST: testEval_1");
		Polynomial p1 = new Polynomial();
		
		p1.addTermLast(new Term(4, new BigInteger("2")));
		p1.addTermLast(new Term(3, new BigInteger("1")));
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(0, new BigInteger("1")));
				
	
		System.out.println("Polynomial = " + p1);
		System.out.println("x = 2");
		System.out.println("Obtained: " + p1.eval(new BigInteger("2")));
		System.out.println("Expected: 49");
		
		if (p1.eval(new BigInteger("2")).compareTo(new BigInteger("49")) == 0)
		{
			System.out.println("Passed: eval() - Test 1");
			System.out.println("***TEST ENDED***\n");

			return;
		}
		System.out.println("Failed: eval()- Test 1");
		System.out.println("***TEST ENDED***\n");

	}
	
	
	
	// Gaps Polynomial Test
	// Small polynomial test
	private static void testEval_2()
	{
		
		System.out.println("TEST: testEval_2");
		Polynomial p1 = new Polynomial();
		
		p1.addTermLast(new Term(6, new BigInteger("-10")));
		p1.addTermLast(new Term(4, new BigInteger("2")));
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(0, new BigInteger("1")));
				
	
		System.out.println("Polynomial = " + p1);
		System.out.println("x = 2");
		System.out.println("Obtained: " + p1.eval(new BigInteger("2")));
		System.out.println("Expected: -599");
		
		if (p1.eval(new BigInteger("2")).compareTo(new BigInteger("-599")) == 0)
		{
			System.out.println("Passed: eval() - Test 1");
			System.out.println("***TEST ENDED***\n");

			return;
		}
		System.out.println("Failed: eval()- Test 1");
		System.out.println("***TEST ENDED***\n");

	}
	
	
	// Long polynomial test.   Creates a polynomial with a large number of terms and each term has 
	//  coefficient 1 and we are evaluating at x=1,  so c_i x^i = 1 for each of these terms.
	//  Thus the polynomial would have value equal to the number of terms, i.e.  1 + 1 + .... +  1 = number of terms
	private static void testEval_3()
	{
		System.out.println("TEST: testEval_3");
		Polynomial p1 = new Polynomial();
		
		for (int i = 0; i < 1000000; i++)
			p1.addTermLast(new Term(1000000 - i - 1, new BigInteger("1")));
		
		if (p1.eval(new BigInteger("1")).compareTo(new BigInteger("1000000")) == 0)
		{
			System.out.println("Passed: eval() - Test 3");
			System.out.println("***TEST ENDED***\n");

			return;
		}
		System.out.println("Failed: eval()- Test 3");
		System.out.println("***TEST ENDED***\n");

	}
	
	// easy term
	private static void testMultiplyTerm_1()
	{	
		System.out.println("TEST: testMultiplyTerm_1");
		Polynomial p1 = new Polynomial();
				
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(0, new BigInteger("1")));
		System.out.println("Polynomial: " + p1);
		p1.multiplyTermTest(new Term(1, new BigInteger("3")));
		
		
		Polynomial result = new Polynomial();
		result.addTermLast(new Term(3, new BigInteger("6")));
		result.addTermLast(new Term(1, new BigInteger("3")));
		
		System.out.println("Obtained: " + p1);
		System.out.println("Expected: " + result);
		
		if (p1.size() != 0 && p1.checkEqual(result))
		{
			System.out.println("Passed: multiplyTerm()");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: multiplyTerm()");
		System.out.println("***TEST ENDED***\n");
	}
	
	// 0 term 
	private static void testMultiplyTerm_2()
	{	
		System.out.println("TEST: testMultiplyTerm_2");
		Polynomial p1 = new Polynomial();
		
		String max = Integer.toString(Integer.MAX_VALUE); 
		String min = Integer.toString(Integer.MIN_VALUE);
				
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(1, new BigInteger(max)));
		p1.addTermLast(new Term(0, new BigInteger(min)));
		
		System.out.println("Polynomial: " + p1);

		p1.multiplyTermTest(new Term(1, new BigInteger("0")));
				
		Polynomial result = new Polynomial();
		
		System.out.println("Obtained: " + p1);
		System.out.println("Expected: " + result);
		
		if (p1.size() == 0 && p1.checkEqual(result))
		{
			System.out.println("Passed: multiplyTerm() Test 2");
			System.out.println("***TEST ENDED***\n");
			return;
		}
		System.out.println("Failed: multiplyTerm() Test 2");
		System.out.println("***TEST ENDED***\n");
	}
	
	private static void testMultiply()
	{
		System.out.println("TEST: testMultiply");

		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		p1.addTermLast(new Term(2, new BigInteger("2")));
		p1.addTermLast(new Term(0, new BigInteger("1")));
		
		System.out.println("Polynomial 1: " +p1);
		
		p2.addTermLast(new Term(2, new BigInteger("2")));
		p2.addTermLast(new Term(0, new BigInteger("1")));
		
		System.out.println("Polynomial 2: " + p2);
		
		Polynomial product = Polynomial.multiply(p1, p2);
		System.out.println("Product: " + product);
		Polynomial expectedProduct = new Polynomial();
		
		expectedProduct.addTermLast(new Term(4, new BigInteger("4")));
		expectedProduct.addTermLast(new Term(2, new BigInteger("4")));
		expectedProduct.addTermLast(new Term(0, new BigInteger("1")));
		System.out.println("Expected product: " + expectedProduct);
		
		if (product != null && product.checkEqual(expectedProduct))
		{
			System.out.println("Passed: multiply()");
			System.out.println("***TEST ENDED***\n");

			return;
		}
		System.out.println("Failed: multiply()");
		System.out.println("***TEST ENDED***\n");

	}
	
	public static void main(String[] args) 
	{
		testClone();
		testClone_2(); 
		testAddTerm_1();
		testAddTerm_2();
		testAddTerm_3(); 
		testAddTerm_4(); 
		testAdd_1();
		testAdd_2();
		testEval_1();
		testEval_2();
		testEval_3();
		testMultiplyTerm_1();
		testMultiplyTerm_2(); 
		testMultiply();
	}
}
