package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Populate {

    private File theGoodsFile;
//    private Scanner populate;

     public Populate (File theGoodsFile) {
        this.theGoodsFile = theGoodsFile;
    }

    public List<String[]> initialize() {
        List<String[]> itemAttributes = new ArrayList<>();
        try (Scanner populate = new Scanner(theGoodsFile)){
            while (populate.hasNextLine()) {
                String lineOfInput = populate.nextLine();
                String[] linda = lineOfInput.split("\\|");
                itemAttributes.add(linda);
            }
        } catch(FileNotFoundException ex) {
            System.err.println("Please enter valid inventory file path.");
        }
//         catch(NullPointerException ex) {
//            System.out.println("Values in file null");
//        }
        return itemAttributes;
    }
}
