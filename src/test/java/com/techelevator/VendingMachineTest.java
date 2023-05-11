package com.techelevator;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;

public class VendingMachineTest {

    VendingMachine vendingMachine;

    @BeforeEach
    public void vending_machine_setup() {
        File testFile = new File("vendingmachinetest.csv");
        vendingMachine = new VendingMachine(testFile);
        vendingMachine.feedMoney("100");
    }

    @Test
    public void vend_wrong_codeCase_returns_correctItem () {
        String testCode = "a1";
        String temp = vendingMachine.vend(testCode);
        Assert.assertEquals("Crunch Crunch, Yum!", temp);
    }

    @Test
    public void vend_wrong_code_input_returns_reenterString () {
        String testCode = "Z1";
        String temp = vendingMachine.vend(testCode);
        Assert.assertEquals("Please enter a correct (case-sensitive) item code", temp);
    }

    @Test
    public void vend_item_sold_out_returns_soldoutString () {
        String testCode = "A1";
        for (int i = 0; i < 5; i++) {
            vendingMachine.vend(testCode);
        }
        String temp = vendingMachine.vend(testCode);
        Assert.assertEquals("Item is SOLD OUT", temp);
    }

}
