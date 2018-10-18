/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo2a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author Bavo
 */
public class SortedStream {
    
<<<<<<< HEAD
    public void sort(){
        Stream<Student> sortedStream = this.studentenMaker().get();
        sortedStream.sorted(Comparator.comparing(Student::getAchternaam).thenComparing(Student::getVoornaam).thenComparing(Student::getStudentennummer)).forEach(s -> System.out.println(s.toString()));
    }
    
    private Supplier<Stream<Student>> studentenMaker(){
        NaamGenerator namen = new NaamGenerator();
        List<Student> studenten = new ArrayList<>();
        
        for(int i = 1; i <= 100; i++){
            Student student = new Student(namen.getFirstNamesList().get(new Random().nextInt(namen.getFirstNamesList().size())), namen.getLastNamesList().get(new Random().nextInt(namen.getLastNamesList().size())), i);
            studenten.add(student);
        }
        Supplier<Stream<Student>> sup = () -> studenten.stream();
        return sup;
    }
=======
//    public Supplier<Stream<Student>> studentenMaker(){
//        Supplier<Stream<Student>> sup = 
//    }
>>>>>>> 2e9e3d0a4d910a601f808ef8cf30e871ec5001a0
}
