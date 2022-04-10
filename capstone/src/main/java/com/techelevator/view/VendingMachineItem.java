package com.techelevator.view;

public class VendingMachineItem {
    private String productName;
    private double price;

    public VendingMachineItem(String slotLocation, String productName, double price, String type) {
        this.productName = productName;
        this.price = price;
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
