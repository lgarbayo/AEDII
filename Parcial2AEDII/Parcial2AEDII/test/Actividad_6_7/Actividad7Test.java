package Actividad_6_7;

import Interfaces.*;
import Especificion_Grafos.MapDeMap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class Actividad7Test {
    
    private static Grafo<String, Integer> g; 
    private static final Grafo<String, Integer> gVacio = new MapDeMap<>();
    private static Grafo<String, Integer> grafo; 

    private static final Vertice<String> v1 = new Vertice<>("Coruña");
    private static final Vertice<String> v2 = new Vertice<>("Lugo");
    private static final Vertice<String> v3 = new Vertice<>("Ourense");
    private static final Vertice<String> v4 = new Vertice<>("Pontevedra");
    
    private static final Vertice<String> va = new Vertice<>("7");
    private static final Vertice<String> vb = new Vertice<>("5");
    private static final Vertice<String> vc = new Vertice<>("3");
    private static final Vertice<String> vd = new Vertice<>("11");
    private static final Vertice<String> ve = new Vertice<>("8");
    private static final Vertice<String> vf = new Vertice<>("2");
    private static final Vertice<String> vg = new Vertice<>("9");
    private static final Vertice<String> vh = new Vertice<>("10");
    

    private static void rellenarGrafoG(){
        g = new MapDeMap<>();
        g.insertarArco(new Arco<>(v2, v1, 10));
        g.insertarArco(new Arco<>(v1, v3, 20));
        g.insertarArco(new Arco<>(v2, v4, 115));
        g.insertarArco(new Arco<>(v3, v4, 100));
        g.insertarArco(new Arco<>(v2, v3, 100));
        g.insertarArco(new Arco<>(v4, v2, 120));
        g.insertarArco(new Arco<>(v4, v3, 120));
        
        grafo = new MapDeMap<>();
        grafo.insertarArco(new Arco<>(va, vd, 0));
        grafo.insertarArco(new Arco<>(va, ve, 0));
        grafo.insertarArco(new Arco<>(vb, vd, 0));
        grafo.insertarArco(new Arco<>(vc, ve, 0));
        grafo.insertarArco(new Arco<>(vc, vh, 0));
        grafo.insertarArco(new Arco<>(vd, vf, 0));
        grafo.insertarArco(new Arco<>(vd, vg, 0));
        grafo.insertarArco(new Arco<>(vd, vh, 0));
        grafo.insertarArco(new Arco<>(ve, vf, 0));
        
    }
   
    @Before
    public void setUp() throws Exception {
            rellenarGrafoG();
    }
    
   
    /**
     * Test of predecesores method, of class SolActividad7.
     */
    @Test
    public void testPredecesores1() {
        System.out.println("Test predecesores");
        Iterator<Vertice<String>> itPred = Actividad7.predecesores(g, v4);
        List<String> result = new ArrayList<>();
        while (itPred.hasNext()) {
            result.add(itPred.next().getEtiqueta());
        }
        List<String> expResult = new ArrayList<>();
        expResult.add("Lugo");
        expResult.add("Ourense");
        assertArrayEquals(expResult.toArray(), result.toArray());
    }
    public void testPredecesores2() {
        System.out.println("Test 2 predecesores");
        Iterator<Vertice<String>> itPred = Actividad7.predecesores(g, v4);
        List<String> result = new ArrayList<>();
        while (itPred.hasNext()) {
            result.add(itPred.next().getEtiqueta());
        }
        List<String> expResult = new ArrayList<>();
        expResult.add("Lugo");
        expResult.add("Ourense");
        assertArrayEquals(expResult.toArray(), result.toArray());
    }

    /**
     * Test of sumidero method, of class solActividad7.
     */
    @Test
    public void testSumideroVacio() {
        System.out.println("Test grafo vacío esSumidero");
        boolean result = Actividad7.esSumidero(gVacio, v3);
        assertFalse(result);
    }
    @Test
    public void testNoSumidero() {
        System.out.println("Test No esSumidero");
        boolean result = Actividad7.esSumidero(g, v3);
        assertFalse(result);
    }
    @Test
    public void testSumidero() {
        System.out.println("Test esSumidero");
        g.eliminarArco(new Arco<>(v3,v4, 100));
        boolean result = Actividad7.esSumidero(g,v3);
        assertTrue(result);
    }

    /**
     * Test of regular method, of class solActividad7.
     */
    @Test
    public void testVacioRegular() {
        System.out.println("Test grafo vacío esRegular");
        boolean expResult = true;
        boolean result = Actividad7.esRegular(gVacio);
        assertEquals(expResult, result);
    }
    @Test
    public void testNORegular() {
        System.out.println("Test No esRegular");
        boolean expResult = false;
        boolean result = Actividad7.esRegular(g);
        assertEquals(expResult, result);
    }
    @Test
    public void testSiRegular() {
        System.out.println("Test esRegular");
        g.eliminarArco(new Arco<>(v2,v4, 115));
	g.insertarArco(new Arco<>(v1, v4, 200));
	g.insertarArco(new Arco<>(v3, v1, 120));
        boolean expResult = true;
        boolean result = Actividad7.esRegular(g);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of lemaApretonManos method, of class solActividad7.
     */
    @Test
    public void testLemaApretonManos() {
        System.out.println("Test lemaApretonManos");
        boolean expResult = true;
        boolean result = Actividad7.lemaApretonManos(gVacio);
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testLemaApretonManos2() {
        System.out.println("Test 2 lemaApretonManos");
        boolean expResult = true;
        boolean result = Actividad7.lemaApretonManos(g);
        assertEquals(expResult, result);   
    }

    /**
     * Test of hayCaminoEntreDos method, of class solActividad7.
     */
    @Test
    public void testHayCaminoEntreDos() {
        System.out.println("hayCaminoEntreDos");
        boolean expResult = false;
        boolean result = Actividad7.hayCaminoEntreDos(gVacio, v1, v2);
        assertEquals(expResult, result);   
    }

    

    /**
     * Test of esCiclo method, of class solActividad7.
     */
    @Test
    public void testEsCicloFalse() {
        System.out.println("Test esCiclo");
        List<Vertice<String>> camino = new ArrayList<>();
        camino.add(v1);
        camino.add(v2);
        boolean expResult = false;
        boolean result = Actividad7.esCiclo(gVacio, camino);
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testEsCicloTrue() {
        System.out.println("Test 2 esCiclo");
        List<Vertice<String>> camino = new ArrayList<>();
        camino.add(v2);
        camino.add(v3);
        camino.add(v4);
        camino.add(v2);
        boolean expResult = true;
        boolean result = Actividad7.esCiclo(g, camino);
        assertEquals(expResult, result);   
    }
    @Test
    public void testEsCicloTrue2() {
        System.out.println("Test 3 esCiclo");
        List<Vertice<String>> camino = new ArrayList<>();
        camino.add(v4);
        camino.add(v3);
        camino.add(v4);
        boolean expResult = true;
        boolean result = Actividad7.esCiclo(g, camino);
        assertEquals(expResult, result);   
    }
    @Test
    public void testEsCicloTrue3() {
        System.out.println("Test 4 esCiclo");
        List<Vertice<String>> camino = new ArrayList<>();
        camino.add(v2);
        camino.add(v1);
        camino.add(v3);
        camino.add(v4);
        camino.add(v2);
        boolean expResult = true;
        boolean result = Actividad7.esCiclo(g, camino);
        assertEquals(expResult, result);   
    }
    @Test
    public void testEsCicloFalse2() {
        System.out.println("Test 5 esCiclo");
        List<Vertice<String>> camino = new ArrayList<>();
        camino.add(v1);
        camino.add(v3);
        camino.add(v4);
        camino.add(v3);
        camino.add(v4);
        camino.add(v2);
        camino.add(v1);
        
        boolean expResult = false;
        boolean result = Actividad7.esCiclo(g, camino);
        assertEquals(expResult, result);   
    }

   
    
}
