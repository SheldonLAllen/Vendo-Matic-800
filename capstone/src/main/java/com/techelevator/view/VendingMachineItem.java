package com.techelevator.view;

public class VendingMachineItem {
    private String slotLocation;
    private String productName;
    private double price;
    private String type;
    private boolean isBought;
    private String vendMessage = "";

    public VendingMachineItem(String slotLocation, String productName, double price, String type) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.type = type;
        this.isBought = false;
    }

    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isBought() {
        return isBought;
    }

    public void buy() {
        this.isBought = true;
        dispenseItem(this.getType());
    }

    private void dispenseItem(String type) {
        if(type.equalsIgnoreCase("Chip")){
            this.vendMessage = "Crunch Crunch, Yum!";
        }else if (type.equalsIgnoreCase("Candy")){
            this.vendMessage = "Munch Munch, Yum!";
        }else if (type.equalsIgnoreCase("Drink")){
            this.vendMessage = "Glug Glug, Yum!";
        }else if (type.equalsIgnoreCase("Gum")){
            this.vendMessage = "Chew Chew, Yum!";
        }
    }

    public String getVendMessage() {
        return vendMessage;
    }

    public void setVendMessage(String vendMessage) {
        this.vendMessage = vendMessage;
    }
}


//package com.techelevator.view;
//
//public class VendingMachineItem {
//    private String productName;
//    private double price;
//
//    public VendingMachineItem(String productName, double price) {
//        this.productName = productName;
//        this.price = price;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//}
//
