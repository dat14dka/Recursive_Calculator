package main;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import calculator.*;;

public class Main {
	/*
	 * Launches program and reads from specified input file, then continuously takes
	 * new arguments until user quits Syntax of commands: # <register> <operation>
	 * <value> # print <register> # quit
	 */
	
	private static final int NO_INPUT_ARGUMENT = -1;
	private static final int COULD_NOT_READ_FILEPATH = 0;
	private static final int FILEPATH_READ = 1;

			
	public static void main(String[] args) throws IOException{
		// set up to read from input file if argument is passed
		int couldReadInput = NO_INPUT_ARGUMENT; //start of assuming we have 0 arguments
		boolean shouldQuit = false; // to determine if we should exit
		CommandExcecutor cmdExe = new CommandExcecutor(); // to execute input

		
		if(args.length != 0) { // check if there is a argument to read
			File file = new File(args[0]);
			BufferedReader br = null;
			try { // try to read from specified file-path + file
				br = new BufferedReader(new FileReader(file));
				couldReadInput = FILEPATH_READ; 
			} catch (FileNotFoundException e) { // could not read file
				couldReadInput = COULD_NOT_READ_FILEPATH;
			}
			
			if(couldReadInput == FILEPATH_READ) { //if we could read the file
				String readCommand; 
				// set up program variables
				System.out.println("Reading input from file: " + "\"" + args[0] + "\"" + "...");
				
				//read commands row by row
				while ((readCommand = br.readLine()) != null) { 
					shouldQuit = cmdExe.excecuteCommand(readCommand);
				}
			}else { // if could not read file from specified file-path
				System.out.println("Could not read from file: " + "\"" + args[0] + "\"" + "...");
			}

		}else { // there is no argument to read, launch without reading input
			System.out.println("No input file defined");
		}

		// set up input scanner
		Scanner scanner = new Scanner(System.in);
	
		// continuously read next argument until user quits
		while (!shouldQuit) {
			System.out.print("Enter a command : ");
			String command = scanner.nextLine();
			shouldQuit = cmdExe.excecuteCommand(command);
		}
	}
}
