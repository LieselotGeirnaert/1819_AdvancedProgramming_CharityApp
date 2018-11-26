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
public class TicTacToes {
    public int nr1 = 3;
    public int nr2 = 5;
    public int nr3 = 7;
    public static String val1 = "Tic";
    public static String val2 = "Tac";
    public static String val3 = "Toe";
    
    public String converter(int nr) {
        if (nr <= 0 || nr >= 101){
            throw new IllegalArgumentException();
        }
        String output = "";
        
        output += convertDivide(nr);
        output += convertNumber(nr);
        
        output = output.substring(0, output.length() -1);
        return output;
    }
    
    private String convertNumber(int nr){
        String nrStr = Integer.toString(nr);
        String outputTic = "";
        for (int i = 0; i < nrStr.length(); i++){
            if (nrStr.charAt(i) == '3'){
                outputTic += val1 + " ";
            } else if (nrStr.charAt(i) == '5'){
                outputTic += val2 + " ";
            } else if (nrStr.charAt(i) == '7'){
                outputTic += val3 + " ";
            } 
        }
        return outputTic;
    }
    
    private String convertDivide(int nr){
        String outputTic = "";
        if (nr % nr1 == 0){
            outputTic += val1 + " ";
        }
        if (nr % nr2 == 0){
            outputTic += val2 + " ";
        }
         if (nr % nr3 == 0){
            outputTic += val3 + " ";
        }
        return outputTic;
    }
}
