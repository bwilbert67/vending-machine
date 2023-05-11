package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TransactionTest {

    @Test
    public void giveChange_validData_returnsExpectedValue(){
        //arrange
        Transaction testTransaction = new Transaction();
        testTransaction.deposit(BigDecimal.valueOf(13.37));
        List <Integer> expected = Arrays.asList(53, 1, 0, 2);

        //act
        List<Integer> actual = testTransaction.giveChange();

        //assert
        Assert.assertEquals(expected, actual);

    }
    @Test
public void Buy_validData_returnsExpectedValue() {
        //arrange
        Transaction testTransaction = new Transaction();
        testTransaction.deposit(BigDecimal.valueOf(10));
        BigDecimal expectedNewBalance = BigDecimal.valueOf(9.00).setScale(2);

        //act
        // purchaseName and transactionKey are not applicable to this test
        testTransaction.Buy(BigDecimal.valueOf(1), "test", "test");

        //assert
        Assert.assertEquals(expectedNewBalance, testTransaction.getBalance());
        Assert.assertTrue(testTransaction.Buy(BigDecimal.valueOf(1), "test", "test"));

    }
    @Test
    public void Buy_insufficientFunds_returnsFalse(){

        //arrange
        BigDecimal insufficientBalance = new BigDecimal(1);
        Transaction testTransaction = new Transaction();

        //act and assert: run method that reutrns boolean
        Assert.assertFalse(testTransaction.Buy(new BigDecimal(1.50), "test", "test"));
    }

    @Test
    public void Buy_insufficientFunds_doesNotChangeBalance() {
        //arrange
        BigDecimal expectedBalance = new BigDecimal(1).setScale(2);
        Transaction testTransaction = new Transaction();
        testTransaction.deposit(BigDecimal.valueOf(1));

        //act
        testTransaction.Buy(new BigDecimal(2), "test", "test");

        //assert
        Assert.assertEquals(expectedBalance, testTransaction.getBalance());
    }

    @Test
    public void deposit_validData_returnsExpectedValue(){
        //arrange
        BigDecimal expectedBalanceDecimalValue = BigDecimal.valueOf(5.05);
        Transaction decimalTest = new Transaction();
        BigDecimal expectedBalanceWholeNumber = BigDecimal.valueOf(5.00).setScale(2);
        Transaction wholeNumberTest = new Transaction();


        //act
        decimalTest.deposit(BigDecimal.valueOf(5.05));
        wholeNumberTest.deposit(BigDecimal.valueOf(5.00));

        //assert
        Assert.assertEquals(expectedBalanceDecimalValue, decimalTest.getBalance());
        Assert.assertEquals(expectedBalanceWholeNumber, wholeNumberTest.getBalance());

    }
    @Test
    public void deposit_invalid_returnsFalse(){
        //arrange
        Transaction test = new Transaction();



        //act, assert
        Assert.assertFalse(test.deposit(BigDecimal.valueOf(-5)));
        //run BigDecimal.scale in deposit method
       Assert.assertFalse(test.deposit(BigDecimal.valueOf(5.005)));
    }

}
