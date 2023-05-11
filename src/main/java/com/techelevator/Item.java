package com.techelevator;

import java.math.BigDecimal;

public class Item {

    private BigDecimal price;
    private String foodType;
    private String name;
    private int stock;

    public Item (String name, double price, String foodType, int stock){
        this.price = BigDecimal.valueOf(price);
        this.foodType = foodType;
        this.name = name;
        this.stock = stock;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public void itemDispensed() {
        stock--;
    }

    //derived getter for sound
    public String getSound() {
        if(this.foodType.equalsIgnoreCase("chip")) {
            return "Crunch Crunch, Yum!";
        } else if (this.foodType.equalsIgnoreCase("candy")) {
            return "Munch Munch, Yum!";
        } else if (this.foodType.equalsIgnoreCase("drink")) {
            return "Glug Glug, Yum!";
        } else {
            return "Chew Chew, Yum!";
        }
    }
}
