package main;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import com.utils.CalculatorException;
import com.utils.Operator;

public class CalMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Operator.getAllOperatordetil();
        System.out.println("Available operators:");
		Calculator cal = new Calculator();
		System.out.println("waiting input:");
	    Scanner sc = new Scanner(System.in);
	    while (sc.hasNextLine()) {  
	        String input = sc.nextLine();
	        LinkedList<Double> stack = cal.getValuestack();	     
	        try {
				cal.macher(input);
			} catch (CalculatorException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	        DecimalFormat formt = new DecimalFormat("0.##########");
	        System.out.print("stack: ");
	        for(Double value: stack) {
	        	System.out.print(formt.format(value));
	        	System.out.print(" ");
	        }
	        System.out.printf("%n");
	        System.out.println("Continue input");

	    }
	}

}
