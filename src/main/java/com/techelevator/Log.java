package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private PrintWriter thePrintWriter;
    private File theLog;
    private String actionMessage;

    private String timeLog() {
        LocalDateTime rightNow = LocalDateTime.now();
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("MM/DD/YYYY hh:mm:ss a");
        String curTime = rightNow.format(myFormatter);
        return curTime;
    }

    public void depositLog(BigDecimal amountDeposited, BigDecimal newBalance) {
        try (PrintWriter logger = new PrintWriter(new FileOutputStream("log.txt", true))) {
            String curTime = timeLog();
            logger.println(curTime + " FEED MONEY: " + "$" + amountDeposited + " $" + newBalance);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void buyLog(String transactionKey, String itemName, BigDecimal itemPrice, BigDecimal newBalance){
        try (PrintWriter logger = new PrintWriter(new FileOutputStream("log.txt", true))) {
            String curTime = timeLog();
            logger.println(curTime + itemName + " " + transactionKey + " $" + itemPrice + " $" + newBalance);

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void exitLog() {

    }

    }
