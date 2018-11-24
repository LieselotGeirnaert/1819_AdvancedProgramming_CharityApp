/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo2a;

/**
 *
 * @author bavo.vancraeyenest
 */
public class Student {
    private String voornaam;
    private String achternaam;
    private int studentennummer;
    
    public Student(String voornaam, String achternaam, int studentennummer){
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.studentennummer = studentennummer;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getStudentennummer() {
        return studentennummer;
    }

    public void setStudentennummer(int studentennummer) {
        this.studentennummer = studentennummer;
    }

    @Override
    public String toString() {
        return "Student{" + "voornaam= " + voornaam + ", achternaam= " + achternaam + ", studentennummer= " + studentennummer + '}';
    }
   

}
