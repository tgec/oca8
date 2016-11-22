package toge.test.java8.newFeatures;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( "classpath:config/test-config.xml")
public class Java8NewFeatures {

	@Test
	public void test(){
		System.out.println("test java 8 new features");
	}

	//LAMBDA EXPRESSIONS
	
	//Lambda expressions can be used anywhere the type is a functional interface
	// - A functional interface has only one abstract method
	//The Lambda expression provides the implementation of the single abstract method of the functional interface.
	
	//Syntax
	//(parameters) -> {lambda-body}
	
	//Example
	//  () -> System.out.println("L");
	//  x -> x + 10
	//  (int x, int y) -> {return x + y;}
	//  (String x, String y) -> x.length() - y.length()
	//  (String x) -> {
	//		listA.add(x);
	//		return listA.size();}
	
	@Test
	public void lambda_01(){
				
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation multiplication = (a, b) -> a * b;

		System.out.println("Sum: " + addition.operation(5, 10) );
		System.out.println("Product: " + multiplication.operation(5, 10) );
		
	}

	@Test
	public void lambda_02(){
		
		Arrays.asList( "a", "b", "d" ).forEach( varijablaIspis -> System.out.println( varijablaIspis ) );
		
	}

	@Test
	public void stringJoin(){
		
		String abc= String.join(" ", "Java", "8");
		System.out.println(abc);
		
	}
	
	@FunctionalInterface 
	interface MathOperation {
		int operation(int a, int b);
	}
	
}