/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo2a;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;
import java.util.stream.Stream;

/**
 *
 * @author Bavo
 */
public class FilterStream {
    
    public void filterICT(){
        Stream<Eindwerk> eindwerkenStream = this.eindwerkenMaker().get();
        eindwerkenStream.filter(e -> e.getOpleiding().equals("ICT")).forEach(e -> System.out.println(e.toString()));
    }
    
    public void filterEndT(){
        Stream<Eindwerk> eindwerkenStream = this.eindwerkenMaker().get();

        eindwerkenStream.filter(e -> e.getOpleiding().endsWith("T")).collect(Collectors.groupingBy(Eindwerk::getOpleiding));
    }
    
    private Supplier<Stream<Eindwerk>> eindwerkenMaker(){
        NaamGenerator namen = new NaamGenerator();
        List<Student> studenten = new ArrayList<>();
        
        for(int i = 1; i <= 100; i++){
            Student student = new Student(namen.getFirstNamesList().get(new Random().nextInt(namen.getFirstNamesList().size())), namen.getLastNamesList().get(new Random().nextInt(namen.getLastNamesList().size())), i);
            studenten.add(student);
        }
        
        SortedSet<Eindwerk> eindwerken = new TreeSet<>();
        String[] opleidingen = {"ELO", "ICT", "OPT"};
        while(eindwerken.size() < 100) {
           Eindwerk eindwerk = new Eindwerk(namen.makeTitleWithRandomWords(namen.getTitlesList(), 3), 2018, opleidingen[new Random().nextInt(opleidingen.length)], studenten.get(new Random().nextInt(studenten.size())));
           eindwerken.add(eindwerk);
        }
        
        Supplier<Stream<Eindwerk>> sup = () -> eindwerken.stream();
        return sup;
    }
}
