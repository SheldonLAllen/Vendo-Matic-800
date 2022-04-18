package com.techelevator.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VendingMachine {
//ArrayList of Vendingmachine items, Number of the item
    //method showinventory() show us all of the Arraylist and indicate Sold out. maybe a ToString
    // for Vending
    private ArrayList<VendingMachineItem> inventory = new ArrayList<VendingMachineItem>();
    private double currentMoney;
    private String vendDisplay;
    private VendingMachineLog logger = new VendingMachineLog();


    //vending machine classes.

    public VendingMachine(File vendingInventoryList) throws Exception {
        this.currentMoney = 0.00;
        this.vendDisplay = "";
        try(Scanner displayItems = new Scanner(vendingInventoryList)){
            while(displayItems.hasNextLine()){
                String inputLine = displayItems.nextLine();
                //parsing each item into a vending machine object

                String[] itemArray = inputLine.split("\\|");
//                System.out.println(Arrays.toString(itemArray));
                for(int i = 0; i < 5; i++){
                    VendingMachineItem item = new VendingMachineItem(itemArray[0], itemArray[1], Double.valueOf(itemArray[2]), itemArray[3]);
                    inventory.add(item);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String showInventory(){
        String returnString = "";
        String quantityString = "";
        int quantity = 0;
        for(VendingMachineItem items : this.inventory) {
            if (!returnString.contains(items.getProductName())) {
                //formating so that position and price are shown
                returnString += items.getSlotLocation() + " " + items.getProductName() + " " +  String.format("%.2f", items.getPrice());
                quantityString = items.getProductName();
                for (VendingMachineItem itemsQuantity : this.inventory) {
                    if (quantityString.contains(itemsQuantity.getProductName()) && !itemsQuantity.isBought()) {
                        quantity++;
                    }
                }
                if (quantity > 0) {
                    returnString += " " + quantity + " remaining\n";
                    quantity = 0;
                } else if (quantity == 0) {
                    returnString += " SOLD OUT\n";
                }
            }
        } return returnString;
    }
    public String showInventory(boolean willNotShowSlot){
        String returnString = "";
        String quantityString = "";
        int quantity = 0;
        for(VendingMachineItem items : this.inventory) {
            if (!returnString.contains(items.getProductName())) {
                //formating so that position and price are shown
                returnString += items.getProductName() + " " +  String.format("%.2f", items.getPrice());
                quantityString = items.getProductName();
                for (VendingMachineItem itemsQuantity : this.inventory) {
                    if (quantityString.contains(itemsQuantity.getProductName()) && !itemsQuantity.isBought()) {
                        quantity++;
                    }
                }
                if (quantity > 0) {
                    returnString += " " + quantity + " remaining\n";
                    quantity = 0;
                } else if (quantity == 0) {
                    returnString += " SOLD OUT\n";
                }
            }
        } return returnString;
    }

    public String[] showInventoryAsArray() {
        ArrayList<String> returnArray = new ArrayList<String>();
        String returnString = "";
        String string1 = "";
        String quantityString = "";
        int quantity = 0;
        for (VendingMachineItem items : this.inventory) {
            if (!returnString.contains(items.getProductName())) {
                //formating so that position and price are shown
                returnString += items.getSlotLocation() + " " + items.getProductName() + " " + String.format("%.2f", items.getPrice());
                string1 = items.getProductName() + " " + String.format("%.2f", items.getPrice());
                quantityString = items.getProductName();
                for (VendingMachineItem itemsQuantity : this.inventory) {
                    if (quantityString.contains(itemsQuantity.getProductName()) && !itemsQuantity.isBought()) {
                        quantity++;
                    }
                }
                if (quantity > 0) {
                    returnString += " " + quantity + " remaining\n";
                    returnArray.add(string1 + " " + quantity + " remaining");
                    quantity = 0;
                } else if (quantity == 0) {
                    returnString += " SOLD OUT\n";
                    returnArray.add(string1 + " SOLD OUT");

                }
            }
        }String[] returnStringArray = new String[returnArray.size()];
            for(int i = 0; i < returnArray.size(); i++) {
                returnStringArray[i] = returnArray.get(i);
            }
            return returnStringArray;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }


    public String[] availableSlotLocations(){
        //uses a similar algorithm as show inventory to
        // show list of slots in a vending machine object
        ArrayList<String> availableSlotLocation = new ArrayList<>();

        for(VendingMachineItem items : this.inventory) {
            if (!availableSlotLocation.contains(items.getSlotLocation())) {
                availableSlotLocation.add(items.getSlotLocation());
            }
        }
        String[] vendingMachineSlots = new String[availableSlotLocation.size()];
        for(int i = 0; i < availableSlotLocation.size(); i++){
            vendingMachineSlots[i] =  availableSlotLocation.get(i);
        }
        return vendingMachineSlots;
    }

    public void vend(String slot) throws Exception {
        for(VendingMachineItem items : this.inventory) {
            if (slot.contains(items.getSlotLocation()) && !items.isBought()) {
                if(items.getPrice() < this.currentMoney){
                    String logDisplay = items.getProductName() + " " + items.getSlotLocation() +
                            "  $" + String.format("%.2f", this.currentMoney) + "  $"
                            + String.format("%.2f", this.currentMoney - items.getPrice());
                    currentMoney = items.buy(currentMoney);
                    this.logger.log(logDisplay);
                    this.vendDisplay = items.getProductName() + " " + items.getPrice() + "\n" + items.getVendMessage();
                }else{
                    this.vendDisplay = "Insufficient funds for: " + items.getProductName() + "\n" +
                             "Please make another selection or feed more money.";
                }
                return;
            }
        }
        this.vendDisplay = slot + " is sold out. Please make another selection.";
        return;
    }

    public ArrayList<VendingMachineItem> getInventory() {
        return inventory;
    }

    public void feedMoney(double feedAmount){
        String logDisplay = "FEED MONEY: $" + String.format("%.2f", this.currentMoney) +
                "  $" + String.format("%.2f", this.currentMoney + feedAmount);
        this.logger.log(logDisplay);
        this.currentMoney += feedAmount;
    }

    public String getVendDisplay() {
        return vendDisplay;
    }

    public void setVendDisplay(String vendDisplay) {
        this.vendDisplay = vendDisplay;
    }

    public void giveChange() {
        String logDisplay = "GIVE CHANGE: $" + String.format(
                "%.2f", this.currentMoney) + "  $0.00";
        this.logger.log(logDisplay);

        int change = (int) (this.currentMoney * 100);
        int quarters = change / 25;
        change %= 25;
        int dimes = change / 10;
        change %= 10;
        int nickels = change / 5;

        String[] changeAmounts = new String[3];
        changeAmounts[0] = Integer.toString(quarters);
        changeAmounts[1] =  Integer.toString(dimes);
        changeAmounts[2] =  Integer.toString(nickels);
        this.vendDisplay = "\nDispensing $" + String.format("%.2f", this.currentMoney) + " in " +
                changeAmounts[0] + " quarters " +
                changeAmounts[1] + " dimes and " +
                changeAmounts[2] + " nickels.";
        this.currentMoney = 0.0;
        return;

    }
}

