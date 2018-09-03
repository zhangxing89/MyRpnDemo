package com.utils;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.HashMap;
import java.util.Map;


public enum Operator {
  
    ADDITION("+",2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            return secondOperand + firstOperand;
        }

    },

    SUBTRACTION("-",2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand - firstOperand;
        }
    },

    MULTIPLICATION("*",2) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return secondOperand * firstOperand;
        }
    },

    DIVISION("/",2) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            if (firstOperand==0)
                throw new CalculatorException("Cannot divide by 0.");
            return secondOperand/firstOperand ;
        }
    },

    SQUAREROOT("sqrt",1) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
        	if(firstOperand <0) {
        		throw new CalculatorException("not can be sqrt negative.");
        	}
            return sqrt(firstOperand);
        }
    },

    POWER("pow",1) {
        public Double calculate(Double firstOperand, Double secondOperand) {
            return pow(firstOperand, 2.0);
        }
    },

    UNDO("undo",0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("Invalid operation");
        }
    },

    CLEAR("clear",0) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("Invalid operation");
        }
    },
    SHOW("show",1) {
        public Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException {
            throw new CalculatorException("Invalid operation");
        }
    };
	
	public String getSymbol() {
		return symbol;
	}

	private String symbol;
	private int operandsNumber;
    
    public int getOperandsNumber() {
		return operandsNumber;
	}

	private Operator(String symbol, int operandsNumber) {
		this.symbol = symbol;
		this.operandsNumber = operandsNumber;
	}

	// 静态方法
    private static final Map<String, Operator> Operators = new HashMap<String, Operator>();
    public static Map<String, Operator> getOperators() {
		return Operators;
	}
    
    public static Operator getEnum(String value) {
        return Operators.get(value);
    }
    public static void getAllOperatordetil() {
    	System.out.println("Available operators are:");
    	for(String key:Operators.keySet()) {
    		System.out.print(Operators.get(key)+" ");
    	}
    	System.out.println("");
    }
	//获取枚举值
    static {
        for (Operator o : values()) {
        	Operators.put(o.getSymbol(), o);
        }
    }
    
    @Override
    public String toString() {
        return symbol;
    }
    public abstract Double calculate(Double firstOperand, Double secondOperand) throws CalculatorException;
}
