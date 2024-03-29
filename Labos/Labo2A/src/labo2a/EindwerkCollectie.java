/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo2a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author bavo.vancraeyenest
 */
public class EindwerkCollectie implements EindwerkCollectieInterface{

    SortedMap<String, SortedSet<Eindwerk>> eindwerken = new TreeMap<>();
    
    @Override
    public Eindwerk[] getEindwerkenVanOpleiding(String opleiding) {
//        List<Eindwerk> opl = new ArrayList<>();
//        Eindwerk[] eindw = null;
//       for(Eindwerk e : eindwerken){
//           if(e.getOpleiding().equals(opleiding)) {
//               opl.add(e);
//           }
//       }
//       if(opl.size() > 0) eindw = opl.toArray(new Eindwerk[opl.size()]);
//       return eindw;
        SortedSet<Eindwerk> eindwerkenVanOpleiding = eindwerken.get(opleiding);
        Eindwerk[] eindwerken = null;
        
        if (eindwerkenVanOpleiding.size() > 0) {
            eindwerken = eindwerkenVanOpleiding.toArray(new Eindwerk[eindwerkenVanOpleiding.size()]);
            
        }
        return eindwerken;
    }

    @Override
    public void verwijder(Eindwerk eindwerk) {
        if (eindwerken.containsKey(eindwerk.getOpleiding())) {
            SortedSet<Eindwerk> eindwerkenVanOpleiding = eindwerkenVanOpleiding = eindwerken.get(eindwerk.getOpleiding());
            eindwerkenVanOpleiding.remove(eindwerk);
            
            if (eindwerkenVanOpleiding.size() == 0) {
                eindwerken.remove(eindwerk.getOpleiding());
            }
            
            eindwerken.put(eindwerk.getOpleiding(), eindwerkenVanOpleiding);
        }
    }

    @Override
    public void voegToe(Eindwerk eindwerk) {
        
        SortedSet<Eindwerk> eindwerkenVanOpleiding = new TreeSet<>();
        
        if (eindwerken.containsKey(eindwerk.getOpleiding())) {
            eindwerkenVanOpleiding = eindwerken.get(eindwerk.getOpleiding());
        }
        
        eindwerkenVanOpleiding.add(eindwerk);
        eindwerken.put(eindwerk.getOpleiding(), eindwerkenVanOpleiding);
    }
}
