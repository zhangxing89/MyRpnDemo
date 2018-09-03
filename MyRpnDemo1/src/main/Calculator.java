package main;

import java.util.Arrays;
import java.util.LinkedList;

import com.utils.CalculatorException;
import com.utils.Operator;
public class Calculator {
	public Calculator() {
		valuestack = new LinkedList<Double>();
		commandstack= new LinkedList<String>();
		Message ="";
		tmp1 ="";
		tmp2 ="";
	}
	public String getMessage() {
		return Message;
	}
	private LinkedList<String> commandstack;
	private LinkedList<Double> valuestack;
	private String Message;
	private int count; 
	String tmp1;
	String tmp2;
	Double firstnum;
	Double secondnum;
	public LinkedList<String> getCommandstack() {
		return commandstack;
	}

	public void setCommandstack(LinkedList<String> commandstack) {
		this.commandstack = commandstack;
	}



	public  LinkedList<Double> getValuestack() {
		return  valuestack;
	}



    public void macher(String input) throws CalculatorException {
    	if(input.isEmpty()||input.trim().length()==0) {
    		throw new CalculatorException("Input cannot not be null.");
    	}
    	count=0;
    	String[] result = input.split(" ");
    	for(String temp : result) {
    		if (temp.trim().length()==0){
    			continue;
    		}
    		count++;
    		swich(temp);
    	}
    }
    
    public void swich(String temp) throws CalculatorException {
    	Double value =checkNumber(temp);
    	if (value==null) {
    		checkOperator(temp) ;
    		//commandstack.add(temp);
    	}else {
    		valuestack.add(value);
    		commandstack.add(temp);
    	}
    }
    
    public Double checkNumber(String str) {
    	try {
			return Double.parseDouble(str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		}
    	
    }
    
    public void checkOperator(String str) throws CalculatorException {
    	Operator operator=Operator.getEnum(str);
    	if (operator ==null ) {
    		throw new CalculatorException(str+" is a invalid Operator");
    	}
       	if(valuestack.isEmpty() && (operator == operator.ADDITION || operator == operator.DIVISION ||operator == operator.MULTIPLICATION ||operator == operator.SUBTRACTION ||operator == operator.POWER||operator == operator.SQUAREROOT)) {
    		throw new CalculatorException("empty stack cannot insert operator");
    	}
       	if(valuestack.isEmpty() && operator == operator.UNDO) {
    		throw new CalculatorException("nothing to undo");
    	}
    	
    	if(operator == Operator.CLEAR) {
    		clearstack();
    	}
    	if(operator.getOperandsNumber()>valuestack.size()) {
    		insucientException(operator.getSymbol());
    	}
    	
    	if(operator == operator.ADDITION || operator == operator.DIVISION ||operator == operator.MULTIPLICATION ||operator == operator.SUBTRACTION) {
    	  normethod(operator,str);		
    	}
    	if(operator == operator.POWER||operator == operator.SQUAREROOT) {
    		sqrtmethod(operator,str);
      	}
    	if(operator == operator.UNDO) {
    		undomethod();
      	}
       	if(operator == operator.SHOW) {
    		showmethod();
      	}
    }
    
    public void clearstack() {
    	valuestack.clear();
    	commandstack.clear();
    }
	
    private void insucientException(String operator) throws CalculatorException {
    	throw new CalculatorException(
        "operator "+operator+" (position: "+count+"): insucient parameters");
    }
    
    public void normethod(Operator operator,String str) throws CalculatorException {
      firstnum =valuestack.removeLast();
  	  secondnum = valuestack.removeLast();
      valuestack.addLast((operator.calculate(firstnum,secondnum)));
      commandstack.addLast(str);
      commandstack.addLast(valuestack.getLast().toString());
    }
    
    public void sqrtmethod(Operator operator,String str) throws CalculatorException {
    	 firstnum =valuestack.removeLast();
         valuestack.addLast((operator.calculate(firstnum,0.0)));
         commandstack.addLast(str);
         commandstack.addLast(valuestack.getLast().toString());
      }
    
    public void undomethod() throws CalculatorException {
    	commandstack.removeLast();
    	String ops = commandstack.getLast();
    	if(checkNumber(ops)!=null||ops==null) {
    		valuestack.removeLast();
    	}else if(ops.equals("+") || ops.equals("-") ||ops.equals("*") ||ops.equals( "/")) {
    		commandstack.removeLast();
    		tmp1= commandstack.removeLast();
    		tmp2= commandstack.getLast();
    		commandstack.addLast(tmp1);
    		valuestack.removeLast();
    		valuestack.addLast(Double.parseDouble(tmp2));
    		valuestack.addLast(Double.parseDouble(tmp1));
    	}else if(ops.equals("sqrt")||ops.equals("pow")) {
    		commandstack.removeLast();
    		valuestack.removeLast();
    		valuestack.addLast(Double.parseDouble(commandstack.getLast()));
    	}
    }
    
    public void showmethod() throws CalculatorException {
          System.out.println("valuestack: "+Arrays.toString(valuestack.toArray())+" commandstack: "+Arrays.toString(commandstack.toArray()));
     }
    
}
