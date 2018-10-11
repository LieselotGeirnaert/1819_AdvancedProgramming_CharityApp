/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo1a;

import java.util.Objects;

/**
 *
 * @author bavo.vancraeyenest
 */
public class Eindwerk implements Comparable<Eindwerk>{
    private String titel;
    private int jaartal;
    private String opleiding;
    private Student student;
    
    public Eindwerk(String titel, int jaartal, String opleiding, Student student){
        this.titel = titel;
        this.jaartal = jaartal;
        this.opleiding = opleiding;
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getOpleiding() {
        return opleiding;
    }

    public void setOpleiding(String opleiding) {
        this.opleiding = opleiding;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.titel);
        hash = 31 * hash + this.jaartal;
        hash = 31 * hash + Objects.hashCode(this.opleiding);
        hash = 31 * hash + Objects.hashCode(this.student);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Eindwerk other = (Eindwerk) obj;
        if (this.jaartal != other.jaartal) {
            return false;
        }
        if (!Objects.equals(this.titel, other.titel)) {
            return false;
        }
        if (!Objects.equals(this.opleiding, other.opleiding)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        return true;
    }

    
    @Override
    public int compareTo(Eindwerk e) {
       if(this.opleiding.compareTo(e.getOpleiding()) > 0){
           return 1;
       }else if (this.opleiding.compareTo(e.getOpleiding()) < 0){
           return -1;
       }
       
       else if (this.student.getAchternaam().compareTo(e.getStudent().getAchternaam()) > 0){
           return 1;
       }else if (this.student.getAchternaam().compareTo(e.getStudent().getAchternaam()) < 0){
           return -1;
       }
               
       else if(this.student.getVoornaam().compareTo(e.getStudent().getVoornaam()) > 0){
           return 1;
       } else if (this.student.getVoornaam().compareTo(e.getStudent().getVoornaam()) < 0) {
           return -1;
       } else {
           return 0;
       }
       
    }
}
