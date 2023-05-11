package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PopulateTest {



    @Test
    public void initialize_validData_returnsExpected() {
        //arrange
        Populate populate = new Populate(new File("vendingmachine.csv"));
        String[] firstLine = {"A1" , "Potato Crisps","3.05", "Chip"};
        String[] line6 = {"B3", "Wonka Bar", "1.50", "Candy"};
        String[] lastLine = {"D4", "Triplemint", "0.75", "Gum"};

        // act
        List<String[]> test = populate.initialize();

        //assert
        Assert.assertArrayEquals(firstLine, test.get(0));
        Assert.assertArrayEquals(line6, test.get(6));
        Assert.assertArrayEquals(lastLine, test.get(15));

    }

  @Test
    public void initialize_emptyFile_returnsEmpty()  {
        //arrange
        Populate populate = new Populate(new File("testFile"));
        List<String[]> expected = new ArrayList<>();

        //act
        List<String[]> test = populate.initialize();

        //assert
        Assert.assertEquals(expected, test);
    }
}
