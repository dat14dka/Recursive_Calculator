package commands;

public class QuitCommand implements Executable{
	public boolean excecute() {
		System.out.println("Exiting...");
		return true; // return true, program should quit
	}
}
