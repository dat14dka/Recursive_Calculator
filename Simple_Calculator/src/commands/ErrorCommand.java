package commands;

public class ErrorCommand implements Executable{
	private String errorMessage;
	
	public ErrorCommand(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public boolean excecute() {
		System.out.println(errorMessage);
		return false; // return false, program should still run
	}
}
