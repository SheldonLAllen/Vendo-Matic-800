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

    //vending machine classes.

    public VendingMachine(File vendingInventoryList){
        currentMoney = 0.00;
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
                returnString += items.getProductName();
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

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public ArrayList<VendingMachineItem> getInventory() {
        return inventory;
    }

    public void feedMoney(double feedAmount){
        this.currentMoney += feedAmount;

    }
}

