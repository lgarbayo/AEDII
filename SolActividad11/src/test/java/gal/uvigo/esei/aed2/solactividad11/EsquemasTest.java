/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gal.uvigo.esei.aed2.solactividad11;

//import java.util.Iterator;
////import java.util.LinkedList;
//import java.util.List;
import gal.uvigo.esei.aed2.solactividad11.Esquemas;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Rosalia
 */
public class EsquemasTest {

private static final char[][] LAB = {{' ','T',' ','X'},
                               {' ',' ',' ',' '},
                               {' ','T','X',' '},
                               {' ','X',' ',' '}};
private static final char[][] LAB2 = {{'A','O','O','X','X'},
                                {'D','D','X','O','X'},
                                {'E','A','D','E','X'},
                                {'O','D','O','A','D'},
                                {'O','O','O','A','E'}};
 
     /**
     * Test of colocarReinas method, of class Esquemas.
     */
//    @Test
//    public void testColocarReinas() {
//        System.out.println("colocarReinas");
//        int[] tablero = new int[8];
//        Esquemas.colocarReinas(0, tablero);
//        int [] solEsperada = {0,4,7,5,2,6,1,3};
//
//        assertArrayEquals(tablero,solEsperada);
//    }
    
        
    

//    @Test
//    public void testEnsayarPalabras() {
//        System.out.println("ensayar palabras");
//        String cadena = "ADE";
//        Esquemas.ensayarPalabras(LAB2, 0, 0,cadena,0);
//			
//        char [][] solEsperada = {{' ','O','O','X','X'},
//                               {' ','D','X','O','X'},
//                               {' ',' ',' ',' ','X'},
//                               {'O','D','O',' ',' '},
//                               {'O','O','O','A',' '}};
//        
//        
//        assertArrayEquals(LAB2,solEsperada);
//    }
    
    /**
     * Test of ensayar method, of class Esquemas.
     */
    @Test
    public void testEnsayar() {
        System.out.println("ensayar");
        Esquemas.ensayar(LAB, 0, 0);
			
        char [][] solEsperada = {{'C','C',' ','X'},
                                 {'C','C','C','C'},
                                 {'C','C','X','C'},
                                 {'I','X',' ','C'}};
        
        
        assertArrayEquals(LAB,solEsperada);
    }
    
    
}
