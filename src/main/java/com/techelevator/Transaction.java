package com.techelevator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private BigDecimal balance = BigDecimal.valueOf(0.00).setScale(2, RoundingMode.DOWN);
    private final BigDecimal QUARTERS = BigDecimal.valueOf(0.25);
    private final BigDecimal DIMES = BigDecimal.valueOf(0.10);
    private final BigDecimal NICKELS = BigDecimal.valueOf(0.05);
    private final BigDecimal PENNIES = BigDecimal.valueOf(0.01);
    private final BigDecimal[] COINS = {QUARTERS, DIMES, NICKELS, PENNIES};
    private Log theLog = new Log();

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean deposit(BigDecimal insertedMoney) {
            if (insertedMoney.compareTo(BigDecimal.valueOf(0)) >= 1 && insertedMoney.scale() < 3) {
                balance = balance.add(insertedMoney).setScale(2, RoundingMode.DOWN);
                theLog.depositLog(insertedMoney, balance);
                return true;
            }
            else {
                return false;
            }
    }

    public boolean Buy (BigDecimal price, String purchaseName, String transactionKey) {
        boolean goesThrough = false;
        if (price.compareTo(balance) <= 0) {
            balance = balance.subtract(price.setScale(2, RoundingMode.DOWN));
            theLog.buyLog(transactionKey, purchaseName, price, balance);
            goesThrough = true;
        }
        return goesThrough;
    }

    // exit method for getting change
    public List giveChange() {
        List <Integer> result = new ArrayList<>();
        for(int i = 0; i < 4; i++) {
            BigDecimal curFindCoin = balance.divide(COINS[i]);
            int curCoin = curFindCoin.intValue();
            balance = balance.subtract(COINS[i].multiply(BigDecimal.valueOf(curCoin)));
            result.add(curCoin);
        }
        return result;
    }
}
