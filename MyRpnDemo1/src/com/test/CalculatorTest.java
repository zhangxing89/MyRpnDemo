package com.test;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.utils.CalculatorException;

import main.Calculator;

class CalculatorTest {
	Calculator calculator;
	LinkedList<Double> checkstack;
	DecimalFormat formt = new DecimalFormat("0.##########");
	@BeforeEach
	void setUp() {
		calculator = new Calculator();
	}
	
	@Test
	@DisplayName("test add operation")
	void testAdd() throws CalculatorException {
		calculator.macher("7 8 +");
		assertEquals("15",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7 8 +");
		assertEquals("1",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7 -8 +");
		assertEquals("-15",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7.3 -8.6 +");
		assertEquals("-15.9",formt.format(calculator.getValuestack().getFirst()));
	}
	
	@Test
	@DisplayName("test subtract operation")
	void testSub() throws CalculatorException {
		calculator.macher("7 8 -");
		assertEquals("-1",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7 8 -");
		assertEquals("-15",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7 -8 -");
		assertEquals("1",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7.3 -8.6 -");
		assertEquals("1.3",formt.format(calculator.getValuestack().getFirst()));
	}
	
	@Test
	@DisplayName("test devide operation")
	void testdevide() throws CalculatorException {
		calculator.macher("7 8 /");
		assertEquals("0.875",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -8 7 /");
		assertEquals("-1.1428571429",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7.3 -8.6 /");
		assertEquals("0.8488372093",formt.format(calculator.getValuestack().getFirst()));
	}
	
	@Test
	@DisplayName("test Multiply operation")
	void testMult() throws CalculatorException {
		calculator.macher("7 8 *");
		assertEquals("56",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -8 7 *");
		assertEquals("-56",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear -7.3 -8.6 *");
		assertEquals("62.78",formt.format(calculator.getValuestack().getFirst()));
	}
	@Test
	@DisplayName("test sqrt operation")
	void testsqrt() throws CalculatorException {
		calculator.macher("7 sqrt");
		assertEquals("2.6457513111",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear 8.98 sqrt");
		assertEquals("2.9966648128",formt.format(calculator.getValuestack().getFirst()));
	}
	@Test
	@DisplayName("test undo operation")
	void testundo() throws CalculatorException {
		calculator.macher("7 sqrt undo");
		assertEquals("7",formt.format(calculator.getValuestack().getFirst()));
		calculator.macher("clear 8.98 7  + undo");
		assertEquals("8.98",formt.format(calculator.getValuestack().getFirst()));
		assertEquals("7",formt.format(calculator.getValuestack().getLast()));
	}
	@Test
	@DisplayName("test examples ")
	void testexamples() throws CalculatorException {
		calculator.macher("5 2");
		assertEquals("[5.0, 2.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("clear 5 4 3 2");
		assertEquals("[5.0, 4.0, 3.0, 2.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("undo undo *");
		assertEquals("[20.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("5 *");
		assertEquals("[100.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("undo");
		assertEquals("[20.0, 5.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("clear 7 12 2 /");
		assertEquals("[7.0, 6.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("clear 7 12 2 /");
		assertEquals("[7.0, 6.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("*");
		assertEquals("[42.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("4 /");
		assertEquals("[10.5]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("clear 1 2 3 4 5");
		assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("*");
		assertEquals("[1.0, 2.0, 3.0, 20.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("clear 1 2 3 4 5");
		assertEquals("[1.0, 2.0, 3.0, 4.0, 5.0]",Arrays.toString(calculator.getValuestack().toArray()));
		calculator.macher("* * * *");
		assertEquals("[120.0]",Arrays.toString(calculator.getValuestack().toArray()));
	}
    @Test
    @DisplayName("test divider 0 ")
    void exceptiondeviTest() throws CalculatorException {
        Throwable exception = assertThrows(CalculatorException.class, () -> {
        	calculator.macher("7 0 /");
        });
        assertEquals("Cannot divide by 0.", exception.getMessage());
    }
    
    @Test
    @DisplayName("test sqrt  negative ")
    void exceptionsqrtTest() throws CalculatorException {
        Throwable exception = assertThrows(CalculatorException.class, () -> {
        	calculator.macher("-8 sqrt");
        });
        assertEquals("not can be sqrt negative.", exception.getMessage());
    }
	
    @Test
    @DisplayName("test input null ")
    void exceptionnull() throws CalculatorException {
        Throwable exception = assertThrows(CalculatorException.class, () -> {
        	calculator.macher("");
        });
        assertEquals("Input cannot not be null.", exception.getMessage());
    }
    
    @Test
    @DisplayName("test invlid Operator ")
    void exceptionOperator() throws CalculatorException {
        Throwable exception = assertThrows(CalculatorException.class, () -> {
        	calculator.macher("70 ++");
        });
        assertEquals("++ is a invalid Operator", exception.getMessage());
    }
    
    @Test
    @DisplayName("test insucient parameters ")
    void exceptioninsucient() throws CalculatorException {
        Throwable exception = assertThrows(CalculatorException.class, () -> {
        	calculator.macher("1 2 3 * 5 + * * 6 5");
        });
        assertEquals("operator * (position: 8): insucient parameters", exception.getMessage());
    }
    
}
