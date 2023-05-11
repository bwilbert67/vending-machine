package com.techelevator;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachine {
    private Map<String, Item> inventory = new TreeMap<>();
    private Transaction theTransactor = new Transaction();
//    private Log theLog;
    private File theGoodsFile;
    private Populate populate;
    private List<String[]> itemList;

    public VendingMachine(File theGoodsFile) {
        this.theGoodsFile = theGoodsFile;
        populate = new Populate(theGoodsFile);
        itemList = populate.initialize();

        for (String[] temp : itemList) {
            inventory.put(temp[0], new Item(temp[1], Double.parseDouble(temp[2]), temp[3], 5));
        }
    }

    public String feedMoney(String insertedMoney) {
        try {
            if (!theTransactor.deposit(new BigDecimal(insertedMoney))) {
                return "Please insert valid amount (non negative, minimum of penny, using only number keys";
            }
            return "Success!";
        } catch (NumberFormatException ex) {
            return "Use only number inputs!";
        }
    }

    public String vend (String inventoryKey) {
        inventoryKey = inventoryKey.toUpperCase(Locale.ROOT);
        String output = "Item is SOLD OUT";
        if (inventory.containsKey(inventoryKey)) {
            if (inventory.get(inventoryKey).getStock() > 0) {
                Item curItem = inventory.get(inventoryKey);
                if (theTransactor.Buy(curItem.getPrice(), curItem.getName(), inventoryKey)) {
                    output = curItem.getSound();
                    curItem.itemDispensed();
                } else {
                    output = "Insufficient funds";
                }
            }
        } else {
            output = "Please enter a correct (case-sensitive) item code";
        }
        return output;
    }

    public List<String> printItemList() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < itemList.size(); i++) {
            String [] getItemName = itemList.get(i);
            String itemName = getItemName[1];
            String stock = "";
            String code = itemList.get(i)[0];
            if(inventory.get(code).getStock() <= 0) {
                stock = "SOLD OUT";
            } else {
               stock += inventory.get(code).getStock();
            }
            String price = itemList.get(i)[2];
            String curLine = "(" + code + ") " + itemName + " $" + price +  " ||  Quantity: " + stock;
            output.add(curLine);
        }
        return output;
    }

    public String finishTransaction() {
        List<Integer> result = theTransactor.giveChange();
        return "Quarters: " + result.get(0) + " | Dimes: " + result.get(1) + " | Nickels: " + result.get(2) + " | Pennies: " + result.get(3);
    }

    public BigDecimal getCurBalance() {
        return theTransactor.getBalance();
    }

    public Map<String, Item> getInventory() {
        return inventory;
    }

    public Populate getPopulate() {
        return populate;
    }

    public File getTheGoodsFile() {
        return theGoodsFile;
    }
}
