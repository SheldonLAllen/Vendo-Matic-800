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

    //vending machine classes.

    public VendingMachine(File vendingInventoryList){
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

    public void vend(String slot){
        for(VendingMachineItem items : this.inventory) {
            if (slot.contains(items.getSlotLocation()) && !items.isBought()) {
                items.buy();
                this.vendDisplay = items.getVendMessage();
                return;
            }
        }
        this.vendDisplay = slot + " is sold out. Please make another selection.";
    }

    public ArrayList<VendingMachineItem> getInventory() {
        return inventory;
    }

    public void feedMoney(double feedAmount){
        this.currentMoney += feedAmount;

    }

    public String getVendDisplay() {
        return vendDisplay;
    }

    public void setVendDisplay(String vendDisplay) {
        this.vendDisplay = vendDisplay;
    }
}

