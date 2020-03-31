package commands;

import java.awt.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import binary_tree.*;


public class PrintCommand implements Executable{
	private Map<String, BinaryTree> registers;
	private String key;
	private static final double RECURSION_DETECTED = Double.POSITIVE_INFINITY;
	
	// creates a print command
	public PrintCommand(Map<String, BinaryTree> registers, String key) {
		this.registers = registers;
		this.key = key;
	}
	
	// excecutes the print command, returns false
	public boolean excecute() {
		Set listOfRegistersThatOccured = new HashSet(); //list to detect recursions(not allowed)
		listOfRegistersThatOccured.add(key); //add the current register we are evaluating
				
		double registerValue = evaluateExpression(registers.get(key).getRoot(), listOfRegistersThatOccured); // get value of register
		
		if(registerValue == RECURSION_DETECTED) { // was a recursion detected ?
			System.out.println("Recursion detected, cannot resolve " + "\"" + key + "\"" + " to a value"); 
			return false; // return false, program should still run
		}
		
		if((registerValue-(int)registerValue)==0) // Does registerValue have decimals ?
			System.out.println((int)registerValue); // yes, print as int(ex: 1)
		else 
			System.out.println(registerValue); // no, print as float(ex: 0.5)
		return false; // return false, program should still run
	}
	
	// recursively evaluates the binary tree with root node: root
	// 1. if node is an number , return the value of that number
	// 2. if the node is an operand(ex: +,-), evaluate the value of its left and right child
	//    , then add, mul or sub those values together
	// 3. if its not 1 or 2, it's a register, recursively evaluate the value of that register
	private double evaluateExpression(Node root, Set listOfRegistersThatOccured) {
		String value = root.getValue();
		if(value.matches("[-+]?[0-9]*\\.?[0-9]+") && countChar(value, '.') < 2) { // check if node value is a number (float or int) 
			return Double.valueOf(value);
		}else if(checkIfStringIsOperand(value)){ //check if node value is a operation
			double leftValue = evaluateExpression(root.getLeftNode(), listOfRegistersThatOccured);
			double rightValue = evaluateExpression(root.getRightNode(), listOfRegistersThatOccured);
			
			// if recursion is detected , just return that
			if(leftValue == RECURSION_DETECTED || rightValue == RECURSION_DETECTED) {
				return RECURSION_DETECTED;
			}
			
			double valueToReturn = -1; 
			
			if(value.equals("+")) {
				valueToReturn = leftValue +  rightValue;
			}
			if(value.equals("-")) {
				valueToReturn = leftValue - rightValue;
			}
			if(value.equals("*")) {
				valueToReturn = leftValue * rightValue;
			}
			return valueToReturn;	
		}else { //treat as register
			if(listOfRegistersThatOccured.contains(value)) { // have we visited this register before ? 
				return RECURSION_DETECTED; // yes, return recursion detected
			}else { //no 
				listOfRegistersThatOccured.add(value); // add variable to registers visited
				return evaluateExpression(registers.get(value).getRoot(), listOfRegistersThatOccured); //evaluate register
			}
		}
	}
	
	// check if str is a operand(+,-,*)
	private boolean checkIfStringIsOperand(String str) {
		if(str.equals("+") || str.equals("*") || str.equals("-"))
			return true;
		return false;
	}
	
	// count number of times c occurs in str
	private int countChar(String str, char c)
	{
	    int count = 0;

	    for(int i=0; i < str.length(); i++)
	    {    if(str.charAt(i) == c)
	            count++;
	    }

	    return count;
	}

}
