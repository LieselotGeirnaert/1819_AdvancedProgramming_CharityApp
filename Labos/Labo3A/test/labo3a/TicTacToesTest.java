/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3a;

import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author Bavo
 */
@RunWith(Parameterized.class)
public class TicTacToesTest {
    private TicTacToes ticTacToe;
    private int nr;
    private String result;
    public TicTacToesTest(int nr, String result) {
        this.nr = nr;
        this.result = result;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ticTacToe = new TicTacToes();
    }
    
    @Parameterized.Parameters
    public static Collection nrs() {
        return Arrays.asList(new Object[][] {
            { 3, TicTacToes.val1 + " " + TicTacToes.val1 },
            { 13, TicTacToes.val1 },
            { 33, TicTacToes.val1 + " " + TicTacToes.val1 + " " + TicTacToes.val1},
            {5, TicTacToes.val2 + " " + TicTacToes.val2} ,
            {15, TicTacToes.val1 + " " + TicTacToes.val2 + " " + TicTacToes.val2} ,
            {25, TicTacToes.val2 + " " + TicTacToes.val2} ,
            {7, TicTacToes.val3 + " " + TicTacToes.val3} ,
            {17, TicTacToes.val3} ,
            {21, TicTacToes.val1 + " " + TicTacToes.val3} ,
        });
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestBounderies() {
        ticTacToe.converter(0);
        ticTacToe.converter(101);
    }
    
    @Test
    public void TestNumbers() {
        assertEquals(result, ticTacToe.converter(nr));
    }  
}
