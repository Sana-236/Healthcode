package com.healthycoderapp;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;


class BMICalculatorTest {
	//before all tests
	@BeforeAll
	static void beforeAll() {
		System.out.println(" test passed before all classes");
	}
	//after all tests used mostly to close the connections
	@AfterAll
	static void afterAll() {
		System.out.println(" test passed after all classes");
	}

    //parameterizedtesting
	@ParameterizedTest(name="weight={0},height={1}")
	//@ValueSource(doubles= {89.0,95.0,110.0}) for single values
	@CsvSource(value={"89.0,1.72", "95.0,1.75", "110.0,1.78"})//for 2 values
	//@CsvFileSource(resources="/file location",numLinesToSkip) from csv files and num lines is used to skip the names part in the file and start from the values that are needed
	void should_ReturnTrue_When_DietRecommended(Double coderWeight,Double coderHeight) {
		//to make code more interactive use the following 3 steps
		
		//given
		double weight=coderWeight;
		double height=coderHeight;
		
		
		//when
		boolean recommended=BMICalculator.isDietRecommended(weight, height);
		
		//then
		assertTrue(recommended);
		
	//assertTrue(BMICalculator.isDietRecommended(89.0, 1.82));
	}
		
		//test case for false
		 
	@Test
	void should_ReturnFalse_When_DietNotRecommended() {
			
			//given
			double weight=50.0;
			double height=1.92;
			
			
			//when
			boolean recommended=BMICalculator.isDietRecommended(weight, height);
			
			//then
			assertFalse(recommended);
		}	
	//exception case test
	
	/*@Test
	void should_ThrowException_When_HeightIsZero() {
			
			//given
			double weight=50.0;
			double height=0.0;
			
			
			//when
			//if boolean is used the then part wont be executed so we should use executable to throw an exception and need a lambda expression through empty pair of brackets
			Executable executable=() -> BMICalculator.isDietRecommended(weight, height);
			
			//then
			assertThrows(ArithmeticException.class,executable);
		}*/
	//
    @Test
    void should_returnCoderwithworstbmi_when_CoderListnotEmpty(){
    	//given
    	List<Coder>coders=new ArrayList<>();
    	coders.add(new Coder(1.80,60.0));
    	coders.add(new Coder(1.82,90.0));
    	coders.add(new Coder(1.82,64.7));
    	
    	//when
    	 Coder coderWorstBmi=BMICalculator.findCoderWithWorstBMI(coders);
    	 
    	//then
    	 //TO CHECK ALL THE CODERS WE USED ASSERTALL
    	 assertAll(
    	 ( )->assertEquals(1.82, coderWorstBmi.getHeight()),
    	 ( )->assertEquals(90.0, coderWorstBmi.getWeight())
    	 );
    }
   /* @Test
    void should_returnbmiin1ms_with10000elements() {
    	//given
    	List <Coder>coders=new ArrayList<>();
    	for(int i=0;i<10000;i++) {
    		coders.add(new Coder(1.0+i,10.0+i));
    	}
    	//when
    	Executable executable=()->BMICalculator.findCoderWithWorstBMI(coders);
    	//then
    	assertTimeout(Duration.ofMillis(500),executable);
    }*/
    //check null values
    
    @Test
    void should_returnNull_when_CoderListEmpty(){
    	//given
    	List<Coder>coders=new ArrayList<>();
 
    	
    	//when
    	 Coder coderWorstBmi=BMICalculator.findCoderWithWorstBMI(coders);
    	 
    	//then
    	//we have no coders so it would return null
    	 assertNull( coderWorstBmi);
    
    }
    @Test
    void should_ReturnCorrectBmiscore_when_CoderListnotEmpty(){
    	//given
    	List<Coder>coders=new ArrayList<>();
    	coders.add(new Coder(1.80,60.0));
    	coders.add(new Coder(1.82,90.0));
    	coders.add(new Coder(1.82,64.7));
    	double[] expected= {18.52,27.17,19.53};
    	
    	//when
    	 double[] bmiScores=BMICalculator.getBMIScores(coders);
    	 
    	//then
    	//we have to test array equality by comparing each pair of elements seperately
    	 //assertEquals( expected,bmiScores); it will fail as it is not comaparing each element
    	 assertArrayEquals( expected,bmiScores);
    }
}
