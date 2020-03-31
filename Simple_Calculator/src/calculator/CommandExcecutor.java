package calculator;

import java.util.HashMap;
import java.util.Map;

import binary_tree.BinaryTree;
import commands.*;
import javafx.util.Pair;

/*
 * CommandExcecutor interprets and returns an appropriate command, also
 * executes any Command class implementing Executable. Finally it keeps track
 * of the registers.
* NOTE: we don't handle double over/under-flow in this code, since this seems superfluous
* 		for a simple Calculator
*/
public class CommandExcecutor {
	// each register consists of a access name and a binary tree structure.
	// Each end node is either the name of a register or a number.
	// All nodes that are not end nodes are operations(+,- or *).
	private Map<String, BinaryTree> registers; 

	public CommandExcecutor() {
		registers = new HashMap<String, BinaryTree>(); //create a new blank register
	}

	// Interprets and executes command, returns true if the program should
	// quit else false
	public boolean excecuteCommand(String command) { 
		Executable commandToRun = interpretCommand(command); //interprets command
		return commandToRun.excecute(); // executes command
	}

	// Interprets command, "command", and returns an appropriate Command Class (class that 
	// implements Excecutable)
	public Executable interpretCommand(String command) {
		// parse the string
		command = command.toLowerCase(); //set to lower case to be case insensitive
		String arr[] = command.split(" "); // split command string into array of stringsE

		if (arr.length == 1 && arr[0].equals("quit")) { // is it the quit command ?
			return new QuitCommand(); // return quit command
		} else if (arr.length == 2 && arr[0].equals("print")) { // is it the print command ?
			if (registers.containsKey(arr[1])) // yes, does the register exists ?
				return new PrintCommand(registers, arr[1]); //yes
			else
				return new ErrorCommand("Could not find register: \n" + arr[1]); //no
		} else if (arr.length == 3) { //try to interpret the command as a "add","mul" or "sub" command
			if(!registerNameIsAdmissable(arr[0])) { // is operand 1 admissible ?
				return new ErrorCommand("Non-admissable register name(not alphabetical): \n" + arr[0]);
			}
			if(!registerNameIsAdmissable(arr[2]) && !(arr[2].matches("[-+]?[0-9]*\\.?[0-9]+") 
					&& countChar(arr[2], '.') < 2)) { //is operand 2 admissible and not a number(float or int) ?
				return new ErrorCommand("Non-admissable register name(not alphabetical): \n" + arr[2]);
			}
			
			String operand = arr[1]; // save operand
			if (operand.equals("add")) { // is it the add operand ? 
				AddCommand add = new AddCommand(arr[0], arr[2], registers);
				this.registers = add.getRegisters(); // get updated register 
				return add;
			} else if (operand.equals("subtract")) { // is it the sub operand ? 
				SubtractCommand sub = new SubtractCommand(arr[0], arr[2], registers);
				this.registers = sub.getRegisters(); // get updated register 
				return sub;
			} else if (operand.equals("multiply")) { // is it the mul operand ? 
				MultiplyCommand mul = new MultiplyCommand(arr[0], arr[2], registers);
				this.registers = mul.getRegisters(); // get updated register 
				return mul;
			} else {
				// Could not parse the operand
				return new ErrorCommand("Could not interpret operand " + operand); 
			}
		}
		return new ErrorCommand("Could not interpret: \n" + command); // Could not parse
	}
	
	// print register
	public void printRegister(String register) {
		registers.get(register).printTree();
	}

	// check if register name is admissible(only contains letters and numbers)
	private boolean registerNameIsAdmissable(String str) {
		return str != null && str.matches("^[a-zA-Z0-9]*$");
	}
	
	// count how many times a char occur
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
