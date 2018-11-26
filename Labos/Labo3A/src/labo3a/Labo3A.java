/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo3a;

/**
 *
 * @author Bavo
 */
public class Labo3A {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       TicTacToes ticTacToe = new TicTacToes();
       System.out.println(ticTacToe.converter(3));
       System.out.println(ticTacToe.converter(13));
       System.out.println(ticTacToe.converter(33));
    }
    
}
