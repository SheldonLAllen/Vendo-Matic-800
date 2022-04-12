package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Menu menu;
	private VendingMachine vendoMatic;
	File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
		VendingMachine vendoMatic = new VendingMachine(vendingInventoryList);

	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
//				File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");
//				try(Scanner displayItems = new Scanner(vendingInventoryList)){
//					while(displayItems.hasNextLine()){
//						String inputLine = displayItems.nextLine();
//						System.out.println(inputLine);
//					}
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				}
				// display vending machine items
				vendoMatic.showInventory();


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do exit
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
