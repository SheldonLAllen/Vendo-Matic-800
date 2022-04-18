package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class VendingMachineItemTest {
    //Arrange
    private VendingMachine testMachine;
    private File vendingInventoryList = new File("C:\\Users\\koopa\\Downloads\\module-1-capstone\\capstone\\vendingmachine.csv");


    @Before
    public void setTestMachine() throws Exception {
        this.testMachine = new VendingMachine(vendingInventoryList);
    }

    @Test
    public void show_vending_message() throws Exception {
        //Act
        testMachine.setCurrentMoney(3.05);
        testMachine.vend("A1");
        String expected = "Potato Crisps 3.05\nCrunch Crunch, Yum!";
        //Assert
        Assert.assertEquals(expected, testMachine.getVendDisplay());
    }

}