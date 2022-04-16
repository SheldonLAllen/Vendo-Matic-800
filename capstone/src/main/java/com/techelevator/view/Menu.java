package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	public Object getChoiceFromOptions(Object[] options, String menuDisplay) {
		//special constructor for displaying menu Display
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options, menuDisplay);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

//*****	private Object getChoiceFromUserInputSlot(Object[] options) {
//		Object choice = null;
//		String userInput = in.nextLine();
//		try {
//			String selectedOption = (String) userInput;
//			if (selectedOption) {
//				choice = options[selectedOption - 1];
//			}
//		} catch (NumberFormatException e) {
//			// eat the exception, an error message will be displayed below since choice will be null
//		}
//		if (choice == null) {
//			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
//		}
//		return choice;
//	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			//adjusted out.println so that it would show the ( in the menu
			out.println("(" + optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	private void displayMenuOptions(Object[] options, String menuDisplay) {
		//special constructor for displaying a menu display
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println("(" + optionNum + ") " + options[i]);
		}
		out.println(menuDisplay);
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
