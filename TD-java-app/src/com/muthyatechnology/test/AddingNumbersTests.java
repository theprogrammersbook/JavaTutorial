package com.muthyatechnology.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.muthyatechnology.main.Calculators;

public class AddingNumbersTests {

	private Calculators myCalculator =  null;
	 @Before
     public void setUp() {
		 myCalculator = new Calculators();
     }
	@Test
	public void addTwoPositiveNumbers()
	{
		int expectedResult = 30;
		int ActuaResult =myCalculator.Add(10, 20);
		Assert.assertEquals("The the sum of two positive numbers is correct" , expectedResult, ActuaResult);	
	}

	@Test
	public void addTwoNegativeNumbers()
	{
		int expectedResult = -30;
		int ActuaResult = myCalculator.Add(-10, -20);
		Assert.assertEquals("The the sum of two negative numbers is correct" , expectedResult, ActuaResult);	
	}	
}