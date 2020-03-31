package commands;

import java.util.Map;

import binary_tree.BinaryTree;

/*
 * AddCommand performs the add operation like so: <reg> <operation> <operand>
 * 
 */
public class AddCommand implements Executable {
	private String operand;
	private Map<String, BinaryTree> registers;
	private String register;

	
	public AddCommand(String register, String operand, Map<String, BinaryTree> registers) {
		this.register = register;
		this.operand = operand;
		this.registers = registers;
	}

	// executes the add operation 
	public boolean excecute() {
		if (!registers.containsKey(register)) // is there a register with that name?
			registers.put(register, new BinaryTree("+", operand)); // no, create one
		else
			registers.get(register).addNode(operand, "+"); // yes, add the nodes "operand" and "+" to the binaryTree of "register"
		return false; // return false, program should still run
	}
	
	// returns current register
	public Map<String, BinaryTree> getRegisters(){
		return registers;
	}
}
