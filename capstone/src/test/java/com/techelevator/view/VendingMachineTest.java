package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class VendingMachineTest {
    //Arrange
    private VendingMachine testoMatic;
    private File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");
    String testString = "Potato Crisps";

    @Before
    public void setTestoMatic() {
        this.testoMatic = new VendingMachine(vendingInventoryList);
    }

    @Test
    public void showInventorySoldOut() {
        //Act
        for (VendingMachineItem items : testoMatic.getInventory()) {
            if (testString.equalsIgnoreCase(items.getProductName())) {
//                System.out.println(items.getProductName());
                items.buy();
            }
        }
        //Assert
            Assert.assertEquals(true,  testoMatic.showInventory().contains("Potato Crisps SOLD OUT"));
        }
    }

