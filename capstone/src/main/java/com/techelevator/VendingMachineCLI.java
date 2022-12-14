package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.VendingMachine;

import java.io.File;


public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_PURCHASE = "Select Product";
	private static final String PURCHASE_MENU_OPTION_EXIT = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_PURCHASE, PURCHASE_MENU_OPTION_EXIT };

	private static final String FEED_MENU_OPTION_ONE = "$1";
	private static final String FEED_MENU_OPTION_TWO = "$5";
	private static final String FEED_MENU_OPTION_THREE = "$10";
	private static final String FEED_MENU_OPTION_FOUR = "$20";
	private static final String FEED_MENU_OPTION_FIVE = "Go Back";
	private static final String[] FEED_MENU_OPTIONS = { FEED_MENU_OPTION_ONE, FEED_MENU_OPTION_TWO, FEED_MENU_OPTION_THREE, FEED_MENU_OPTION_FOUR, FEED_MENU_OPTION_FIVE};


	private Menu menu;

	private File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");
	private VendingMachine vendoMatic = new VendingMachine(vendingInventoryList);


	public VendingMachineCLI(Menu menu) throws Exception {
		this.menu = menu;
	}


	public void run() throws Exception {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS, vendoMatic.getVendDisplay());
			vendoMatic.setVendDisplay("");
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(vendoMatic.showInventory());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				while (true) {
					String choicePurchase = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS,
							"\nCurrent Money Provided: $" + String.format("%.2f", vendoMatic.getCurrentMoney()));
					if (choicePurchase.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						// feeding money
						String choiceFeedMoney = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS, "");

						if (choiceFeedMoney.equals(FEED_MENU_OPTION_ONE)) {
							vendoMatic.feedMoney(1.0);
						} else if (choiceFeedMoney.equals(FEED_MENU_OPTION_TWO)) {
							vendoMatic.feedMoney(5.0);
						} else if (choiceFeedMoney.equals(FEED_MENU_OPTION_THREE)) {
							vendoMatic.feedMoney(10.0);
						} else if (choiceFeedMoney.equals(FEED_MENU_OPTION_FOUR)) {
							vendoMatic.feedMoney(20.0);
						} else if (choiceFeedMoney.equals(FEED_MENU_OPTION_FIVE)) {
							continue;
						}

					} else if (choicePurchase.equals(PURCHASE_MENU_OPTION_PURCHASE)) {
						// selecting product (by position)
						while(true) {
							//uses the menu class. sends in the slots from vendomatic and displays the inventory
							String display = "\n" + vendoMatic.getVendDisplay() +
									"\nCurrent Money Provided: $" + String.format("%.2f", vendoMatic.getCurrentMoney());
							String[] optionsList = (vendoMatic.showInventory(true) + "Go Back").split("\n");
							String[] optionsPositions = new String[vendoMatic.availableSlotLocations().length + 1];
							for (int i = 0; i < vendoMatic.availableSlotLocations().length; i++) {
								optionsPositions[i] = vendoMatic.availableSlotLocations()[i];
							}
							optionsPositions[vendoMatic.availableSlotLocations().length] = "BACK";

							String choiceProductSelect = (String) menu.getChoiceFromOptions(optionsList, display, optionsPositions);

							for (String slot : vendoMatic.availableSlotLocations()) {
								if (slot.equalsIgnoreCase(choiceProductSelect)) {
									vendoMatic.vend(slot);
								}
							}
							if ("back".equalsIgnoreCase(choiceProductSelect)) {
								vendoMatic.setVendDisplay("");
								break;
							}
						}
					} else if (choicePurchase.equals(PURCHASE_MENU_OPTION_EXIT)) {
						vendoMatic.giveChange();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				// do exit
				System.out.println("Good Bye");
				break;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
