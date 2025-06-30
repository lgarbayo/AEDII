//package gal.uvigo.esei.aed2.solactividad5;
//
//import arbolGeneral.ArbolGeneral;
//import arbolGeneral.EnlazadoArbolGeneral;
//import java.io.*;
//import java.util.*;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class SolActividad5Test {
//
//    ArbolGeneral<Integer> arbol1 = new EnlazadoArbolGeneral<>(4, new EnlazadoArbolGeneral<>(2));
//    ArbolGeneral<Integer> arbol2 = new EnlazadoArbolGeneral<>(3, new EnlazadoArbolGeneral<>(1));
//    ArbolGeneral<Integer> arbol3 = new EnlazadoArbolGeneral<>(6, arbol1, arbol2, new EnlazadoArbolGeneral<>(5));
//    ArbolGeneral<Integer> arbol4 = new EnlazadoArbolGeneral<>(7, arbol3);
//
//    ArbolGeneral<Integer> arbolDos = new EnlazadoArbolGeneral<>(1, new EnlazadoArbolGeneral<>(2), new EnlazadoArbolGeneral<>(1));
//    ArbolGeneral<Integer> arbolTres = new EnlazadoArbolGeneral<>(1, arbolDos, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5));
//    ArbolGeneral<Integer> arbol = new EnlazadoArbolGeneral<>(4, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5), new EnlazadoArbolGeneral<>(6));
//    ArbolGeneral<Integer> arbolCuatro = new EnlazadoArbolGeneral<>(4, arbol, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(5), new EnlazadoArbolGeneral<>(6));
//
//    ArbolGeneral<Integer> no23 = new EnlazadoArbolGeneral<>(1, new EnlazadoArbolGeneral<>(2, new EnlazadoArbolGeneral<>(3), new EnlazadoArbolGeneral<>(4)));
//
//    /**
//     * Test of sumaNodos method, of class SolActividad5.
//     */
//    @Test
//    public void testGetSumaFalse() {
//        System.out.println("Test 'sumaNodos' de 'arbol4' con resultado 0");
//        int expResult = 0;
//        int result = SolActividad5.getSuma(arbol4);
//        assertNotEquals(expResult, result);
//    }
//
//    @Test
//    public void testGetSumaTrue() {
//        System.out.println("Test 'sumaNodos' de 'arbol4' con resultado 28");
//        int expResult = 28;
//        int result = SolActividad5.getSuma(arbol4);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of igualEstructura method, of class SolActividad5.
//     */
//    @Test
//    public void testEsIgualEstructuraTrue() {
//        System.out.println("Test 'igualEstructura' de 'arbol1' y 'arbol2'");
//        boolean result = SolActividad5.esIgualEstructura(arbol1, arbol2);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testIgualEstructuraFalse() {
//        System.out.println("Test 'igualEstructura' de 'arbol1' y 'arbol4'");
//        boolean result = SolActividad5.esIgualEstructura(arbol1, arbol4);
//        assertFalse(result);
//    }
//
//    /**
//     * Test of arbolDosTres method, of class SolActividad5.
//     */
//    @Test
//    public void testArbolDosTresTrue() {
//        System.out.println("Test 'arbolDosTres' de 'arbolTres'");
//        boolean result = SolActividad5.esArbolDosTres(arbolTres);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testArbolDosTresFalse() {
//        System.out.println("Test 'arbolDosTres' de 'arbol4'");
//        boolean result = SolActividad5.esArbolDosTres(arbol4);
//        assertFalse(result);
//    }
//
//    @Test
//    public void testArbolDosTresFalse2() {
//        System.out.println("Test 'arbolDosTres' de 'no23'");
//        boolean result = SolActividad5.esArbolDosTres(no23);
//        assertFalse(result);
//    }
//
//    /**
//     * Test of esSeleccion method, of class SolActividad5.
//     */
//    @Test
//    public void testEsSeleccionTrue() {
//        System.out.println("Test 'esSeleccion' de 'arbolTres'");
//        boolean result = SolActividad5.esSeleccion(arbolTres);
//        assertTrue(result);
//    }
//
//    @Test
//    public void testEsSeleccionFalse() {
//        System.out.println("Test 'esSeleccion' de 'arbol4'");
//        boolean result = SolActividad5.esSeleccion(arbol4);
//        assertFalse(result);
//    }
//
//    @Test
//    public void testEsSeleccionFalse2() {
//        System.out.println("Test 'esSeleccion' de 'no23'");
//        boolean result = SolActividad5.esSeleccion(no23);
//        assertFalse(result);
//    }
//
//    /**
//     * Test of nivel method, of class SolActividad5.
//     */
//    @Test
//    public void testNivelCero() {
//        System.out.println("Test 'nivel' de 'arbol4' y nivel 7");
//        int expResult = 0;
//        int result = SolActividad5.getNivel(arbol4, 7);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testNivelUno() {
//        System.out.println("Test 'nivel' de 'arbol4' y nivel 3");
//        int expResult = 2;
//        int result = SolActividad5.getNivel(arbol4, 3);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testNivelN() {
//        System.out.println("Test 'nivel' de 'arbol4' y nivel 1");
//        int expResult = 3;
//        int result = SolActividad5.getNivel(arbol4, 1);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of grado method, of class SolActividad5.
//     */
//    @Test
//    public void testGradoUno() {
//        System.out.println("Test 'grado' de 'arbol1'");
//        int expResult = 1;
//        int result = SolActividad5.getGrado(arbol1);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testGradoTres() {
//        System.out.println("Test 'grado' de 'arbol4'");
//        int expResult = 3;
//        int result = SolActividad5.getGrado(arbol4);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of altura method, of class SolActividad5.
//     */
//    @Test
//    public void testAlturaDos() {
//        System.out.println("Test 'altura' de 'arbolTres'");
//        int expResult = 2;
//        int result = SolActividad5.getAltura(arbolTres);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testAlturaTres() {
//        System.out.println("Test 'altura' de 'arbol4'");
//        int expResult = 3;
//        int result = SolActividad5.getAltura(arbol4);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of anchura method, of class SolActividad5.
//     */
//    @Test
//    public void testAnchuraTrue() {
//        System.out.println("Test 'anchura' de 'arbol4' con resultado '7 6 4 3 5 2 1'");
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream ps = new PrintStream(output);
//        System.setOut(ps); // Esto hace que System.out escriba en "output" en lugar de escribir por consola
//        SolActividad5.getAnchura(arbol4);
//        String written = output.toString(); // Este toString recupera lo escrito en "output"
//        assertEquals("7 6 4 3 5 2 1 ", written);
//    }
//
//    @Test
//    public void testAnchuraFalse() {
//        System.out.println("Test 'anchura' de 'arbol4' con resultado '7 6 4 3 2 1'");
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        PrintStream ps = new PrintStream(output);
//        System.setOut(ps); // Esto hace que System.out escriba en "output" en lugar de escribir por consola
//        SolActividad5.getAnchura(arbol4);
//        String written = output.toString(); // Este toString recupera lo escrito en "output"
//        assertNotEquals("7 6 4 3 2 1", written);
//    }
//
//    /**
//     * Test of numNodosPares method, of class SolActividad5.
//     */
//    @Test
//    public void testNumNodosParesTres() {
//        System.out.println("Test 'numNodosPares' en 'arbol4'");
//        int expResult = 3;
//        int result = SolActividad5.getNumNodosPares(arbol4);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testNumNodosParesUno() {
//        System.out.println("Test 'numNodosPares' en 'arbolTres'");
//        int expResult = 1;
//        int result = SolActividad5.getNumNodosPares(arbolTres);
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * Test of hojas method, of class SolActividad5.
//     */
//    @Test
//    public void testHojasUna() {
//        System.out.println("Test 'hojas' en 'arbol1'");
//        List<Integer> result = new LinkedList<>();
//        List<Integer> expResult = Arrays.asList(2);
//        SolActividad5.getListaHojas(arbol1, result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testHojasCuatro() {
//        System.out.println("Test 'hojas' en 'arbol4'");
//        List<Integer> result = new LinkedList<>();
//        List<Integer> expResult = Arrays.asList(2, 1, 5);
//        SolActividad5.getListaHojas(arbol4, result);
//        assertEquals(expResult, result);
//    }
//
//}
