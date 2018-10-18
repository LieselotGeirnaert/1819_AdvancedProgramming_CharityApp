/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo2a;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author bavo.vancraeyenest
 */
public class EindwerkCollectie implements EindwerkCollectieInterface{

    SortedSet<Eindwerk> eindwerken = new TreeSet<>();
    
    @Override
    public Eindwerk[] getEindwerkenVanOpleiding(String opleiding) {
        List<Eindwerk> opl = new ArrayList<>();
        Eindwerk[] eindw = null;
       for(Eindwerk e : eindwerken){
           if(e.getOpleiding().equals(opleiding)) {
               opl.add(e);
           }
       }
       if(opl.size() > 0) eindw = opl.toArray(new Eindwerk[opl.size()]);
       return eindw;
    }

    @Override
    public void verwijder(Eindwerk eindwerk) {
        if (eindwerken.contains(eindwerk)){
            eindwerken.remove(eindwerk);
        }
    }

    @Override
    public void voegToe(Eindwerk eindwerk) {
        eindwerken.add(eindwerk);
    }
}
