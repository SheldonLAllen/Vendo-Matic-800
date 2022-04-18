package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class VendingMachineTest {
    //Arrange
    private VendingMachine testoMatic;
    private File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");
    String testString = "Potato Crisps";
    double currentMoney = 200000.0;

    @Before
    public void setTestoMatic() throws Exception {
        this.testoMatic = new VendingMachine(vendingInventoryList);
    }

    @Test
    public void showInventorySoldOut() {
        //Act
        for (VendingMachineItem items : testoMatic.getInventory()) {
            if (testString.equalsIgnoreCase(items.getProductName())) {
                items.buy(currentMoney);
            }
        }
        //Assert
            Assert.assertEquals(true,  testoMatic.showInventory().contains("SOLD OUT"));
        }
    }

