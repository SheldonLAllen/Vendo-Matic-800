package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class VendingMachineTest {
    //Arrange
    private VendingMachine testMachine;
    private File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");
    String testString = "A1 Potato Crisps 3.05 5 remaining";
    String testStringProduct = "Potato Crisps";
    double currentMoney = 200000.0;

    @Before
    public void setTestMachine() throws Exception {
        this.testMachine = new VendingMachine(vendingInventoryList);
    }

    @Test
    public void show_inventory_item_sold_out() {
        //Act
        for (VendingMachineItem items : testMachine.getInventory()) {
            if (testStringProduct.equalsIgnoreCase(items.getProductName())) {
                items.buy(currentMoney);
            }
        }
        //Assert
        Assert.assertEquals(true,  testMachine.showInventory().contains("A1 Potato Crisps 3.05 SOLD OUT"));
    }

    @Test
    public void show_current_money() {
        //Act
        testMachine.feedMoney(2.00);
        String expected = "2.0";
        //Assert
        Assert.assertEquals(expected,  testMachine.getCurrentMoney() + "");
    }
    @Test
    public void attempt_vending_with_insufficient_funds() throws Exception {
        //Act
        testMachine.setCurrentMoney(0.00);
        testMachine.vend("A2");
        String expected = "Insufficient funds for: Stackers\nPlease make another selection or feed more money.";
        //Assert
        Assert.assertEquals(expected, testMachine.getVendDisplay());
    }
}
