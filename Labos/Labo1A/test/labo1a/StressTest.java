/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo1a;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bavo.vancraeyenest
 */
public class StressTest {
    
    public NaamGenerator namen;
    
    private final EindwerkCollectie collectie;
    
    public StressTest() {
        this.collectie = new EindwerkCollectie();
        namen = new NaamGenerator();
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEindwerkenVanOpleiding method, of class EindwerkCollectie.
     */
    @Test
    public void testGetEindwerkenVanOpleiding() {
         /* start timing */
        long startTime = (new Date()).getTime();
        System.out.println("getEindwerkenVanOpleiding");
        this.collectie.getEindwerkenVanOpleiding("ICT");  
        /* stop timing */
        long stopTime = (new Date()).getTime();
        long time = stopTime - startTime;
        System.out.println(time);
    }


    /**
     * Test of voegToe method, of class EindwerkCollectie.
     */
    @Test
    public void testVoegToe() {
        System.out.println("voegToe");
        List<Student> studenten = new ArrayList<Student>();
        
        for(int i = 0; i < 1000000; i++){
            Student student = new Student(namen.getFirstNamesList().get(new Random().nextInt(namen.getFirstNamesList().size())), namen.getLastNamesList().get(new Random().nextInt(namen.getLastNamesList().size())));
            studenten.add(student);
        }
        
        String[] opleidingen = {"ELO", "ICT", "OPT"};
        while(this.collectie.eindwerken.size() < 2000000) {
           Eindwerk eindwerk = new Eindwerk(namen.makeTitleWithRandomWords(namen.getTitlesList(), 3), 2018, opleidingen[new Random().nextInt(opleidingen.length)], studenten.get(new Random().nextInt(studenten.size())));
           this.collectie.voegToe(eindwerk);
        }
        
        assertEquals(2000000, this.collectie.eindwerken.size());
    }
    
}
