package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

public class ItemSoundTest {

    @Test
    public void chip_getSound_returns_correct_string() {
        Item theItem = new Item("Test Item", 1.00, "chip", 1);
        String ItemTypeTest = theItem.getSound();
        Assert.assertEquals("Crunch Crunch, Yum!", ItemTypeTest);
    }

    @Test
    public void candy_getSound_returns_correct_string() {
        Item theItem = new Item("Test Item", 1.00, "candy", 1);
        String ItemTypeTest = theItem.getSound();
        Assert.assertEquals("Munch Munch, Yum!", ItemTypeTest);
    }

    @Test
    public void drink_getSound_returns_correct_string() {
        Item theItem = new Item("Test Item", 1.00, "drink", 1);
        String ItemTypeTest = theItem.getSound();
        Assert.assertEquals("Glug Glug, Yum!", ItemTypeTest);
    }

    @Test
    public void gum_getSound_returns_correct_string() {
        Item theItem = new Item("Test Item", 1.00, "gum", 1);
        String ItemTypeTest = theItem.getSound();
        Assert.assertEquals("Chew Chew, Yum!", ItemTypeTest);
    }

}
