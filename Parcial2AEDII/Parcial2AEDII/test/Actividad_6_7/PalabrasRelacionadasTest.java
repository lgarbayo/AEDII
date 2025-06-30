
package Actividad_6_7;

import Actividad_6_7.PalabrasRelacionadas;
import Especificion_Grafos.MapDeMap;
import Interfaces.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author pavon
 */
public class PalabrasRelacionadasTest {
    
    private static Grafo<String, Integer> g; 
    private static final Grafo<String, Integer> GVACIO = new MapDeMap<>();

    private static final Vertice<String> V1 = new Vertice<>("FOOL");
    private static final Vertice<String> V2 = new Vertice<>("FOIL");
    private static final Vertice<String> V3 = new Vertice<>("FOUL");
    private static final Vertice<String> V4 = new Vertice<>("COOL");
    private static final Vertice<String> V5 = new Vertice<>("POOL");
    
    private static void rellenarGrafoG(){
        g = new MapDeMap<>();
        g.insertarArco(new Arco<>(V1, V2, 0));
        g.insertarArco(new Arco<>(V1, V3, 0));
        g.insertarArco(new Arco<>(V1, V4, 0));
        g.insertarArco(new Arco<>(V1, V5, 0));
        g.insertarArco(new Arco<>(V2, V1, 0));
        g.insertarArco(new Arco<>(V3, V1, 0));
        g.insertarArco(new Arco<>(V4, V1, 0));
        g.insertarArco(new Arco<>(V5, V1, 0));
        g.insertarArco(new Arco<>(V2, V3, 0));
        g.insertarArco(new Arco<>(V3, V2, 0));
        g.insertarArco(new Arco<>(V4, V5, 0));
        g.insertarArco(new Arco<>(V5, V4, 0));
    }
   
    @Before
    public void setUp() throws Exception {
            rellenarGrafoG();
    }
    
    
    private <E,T> boolean grafosIguales(Grafo<E,T> g1, Grafo<E,T> g2){ 
    
        Iterator<Vertice<E>> ver = g1.vertices();
        while(ver.hasNext()){
            Vertice<E> v = ver.next();
            boolean continuar = true;
            Iterator<Vertice<E>> ver2 = g2.vertices();
            while(ver2.hasNext() && continuar){
                if (v.equals(ver2.next())){
                    continuar = false;
                }
            }
            if (continuar) return false;
        }
        ver = g2.vertices();
        while(ver.hasNext()){
            Vertice<E> v = ver.next();
            boolean continuar = true;
            Iterator<Vertice<E>> ver1 = g1.vertices();
            while(ver1.hasNext() && continuar){
                if (v.equals(ver1.next())){
                    continuar = false;
                }
            }
            if (continuar) return false;
        }
        
        
        Iterator<Arco<E,T>> arc = g1.arcos();

        while (arc.hasNext()) {
            Iterator<Arco<E,T>> arcExpected = g2.arcos();
            boolean continuar = true;
            Arco<E,T> a1 = arc.next();
            Vertice<E> w1 = a1.getDestino();
            Vertice<E> v1 = a1.getOrigen();

            while (arcExpected.hasNext() && continuar){
                Arco<E,T> a2 = arcExpected.next();
                Vertice<E> w2 = a2.getDestino();
                Vertice<E> v2 = a2.getOrigen(); 

                if (a1.getEtiqueta().equals(a2.getEtiqueta()) && w1.equals(w2) && v1.equals(v2)) 
                    continuar = false;
                }

            if (continuar) 
                return false;
        }

        // Se comprueba en el otro sentido para garantizar que tienen exactamente los mismos arcos y no más
        arc = g2.arcos();

        while (arc.hasNext()) {
            Iterator<Arco<E,T>> arcActual = g1.arcos();
            boolean continuar = true;
            Arco<E,T> a1 = arc.next();
            Vertice<E> w1 = a1.getDestino();
            Vertice<E> v1 = a1.getOrigen();

            while (arcActual.hasNext() && continuar){
                Arco<E,T> a2 = arcActual.next();
                Vertice<E> w2 = a2.getDestino();
                Vertice<E> v2 = a2.getOrigen(); 

                if (a1.getEtiqueta().equals(a2.getEtiqueta()) && w1.equals(w2) && v1.equals(v2)) 
                    continuar = false;
            }

            if (continuar) 
                return false;
        }
        return true;
   }

    /**
     * Test of construirGrafo method, of class PalabrasRelacionadas.
     */
    @Test
    public void testConstruirGrafo() {
        System.out.println("construirGrafo bien");
        String [ ] arrayPalabras = {"FOOL", "FOUL", "FOIL", "POOL", "COOL"};
        List<String> palabras = Arrays.asList(arrayPalabras);
        Grafo<String, Integer> expResult = g;
        Grafo<String, Integer> result= PalabrasRelacionadas.construirGrafo(palabras);
        boolean eq = grafosIguales(expResult, result);
        assertTrue(eq);
    }
    @Test
    public void testConstruirGrafo2() {
        System.out.println("construirGrafo mal");
        String [ ] arrayPalabras = {"FOOL", "FOUL", "FOIL", "COLD", "COOL"};
        List<String> palabras = Arrays.asList(arrayPalabras);
        Grafo<String, Integer> expResult = g;
        Grafo<String, Integer> result = PalabrasRelacionadas.construirGrafo(palabras);
        boolean eq = grafosIguales(expResult, result);
        assertFalse(eq);
    }
    @Test
    public void testConstruirGrafo3() {
        System.out.println("construirGrafo mal");
        String [ ] arrayPalabras = {"FOOL", "FOUL", "FOIL", "POOL"};
        List<String> palabras = Arrays.asList(arrayPalabras);
        Grafo<String, Integer> expResult = g;
        Grafo<String, Integer> result = PalabrasRelacionadas.construirGrafo(palabras);
        boolean eq = grafosIguales(expResult, result);
        assertFalse(eq);
    }
    
}
