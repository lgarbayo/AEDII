/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gal.uvigo.esei.aedi.solactividad2;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *
 * @author lgfernandez23_esei.u
 */
public class SolActividad2 { //TODAS ACTIVIDADES ARBOL BINARIO
    
    //=======================================================================
    //                       EJERCICIOS TEORIA
    //=======================================================================
    
    //--------------------  PROFUNDIDAD  ------------------------------------
    
    public static void preorden (ArbolBinario<Integer> a) {
        if(!a.esVacio()) {
            System.out.println(a.raiz() + "");
            preorden(a.hijoIzq());
            preorden(a.hijoDer());
        }
    }
    
    public static <E> void inorden (ArbolBinario<E> a) {
        if(!a.esVacio()) {
            inorden(a.hijoIzq());
            System.out.println(a.raiz() + "");
            inorden(a.hijoDer());
        }
    }
    
    public static <E> void postorden (ArbolBinario<E> a) {
        if(!a.esVacio()) {
            postorden(a.hijoIzq());
            postorden(a.hijoDer());
            System.out.println(a.raiz() + "");
        }
    }
    
    //------------------------  ANCHURA  ------------------------------------
    
    public static <E> void anchura (ArbolBinario<E> a) {
        Queue<ArbolBinario<E>> c = new ArrayDeque<>();
        c.add(a);
        
        do {
            a = c.remove();
            if (!a.esVacio()) {
                System.out.println(a.raiz() + " ");
                c.add(a.hijoIzq());
                c.add(a.hijoDer());
            }
        } while (!c.isEmpty());
    }
    
    //Escribe un metodo que cuente el numero de nodos de un arbol binario
    public static <E> int num (ArbolBinario<E> a) {
        if (a.esVacio()) return 0;
        else {
            // recursividad con los hi y hd
            return 1 + num(a.hijoIzq()) + num(a.hijoDer()); // 1 es la raiz mas los numeros q tengan los hijos
        }
    }
    
    //Escribe un metodo que devuelva el numero de hojas de un arbol binario
    //Las hojas o terminales son aquellos nodos que no tienen descendientes
    public static <E> int hojas (ArbolBinario<E> a) {
        if (a.esVacio()) return 0;
        if (a.hijoDer().esVacio() && a.hijoIzq().esVacio()) return 1; //arbol con solo raiz
        else return hojas(a.hijoIzq()) + hojas(a.hijoDer());
    }
    
    //Un arbol degenerado es un arbol en el que cada nodo tiene solamente un subarbol. Escribe un metodo
    //que indique si un arbol es degenerado o no
    public static <E> boolean esDegenerado (ArbolBinario<E> a) {
        if (a.esVacio()) return true; //si esta vacio es degenerado
        else if (a.hijoDer().esVacio() && a.hijoIzq().esVacio()) return true; //si es una hoja es degenerado
        else if (!a.hijoDer().esVacio() && !a.hijoIzq().esVacio()) return false; //si tiene los dos subarboles no es degenerado
        else return esDegenerado(a.hijoIzq()) && esDegenerado(a.hijoDer()); //para cualquier otro caso cumple la condicion y
        //a su vez tiene que ser degenerado el hijo izquierdo y el hijo derecho
    }
    
    //Escribe un metodo que dado un arbol bianrio, realice una copia del mismo
    public static <E> ArbolBinario<E> copia (ArbolBinario<E> a) {
        if (a.esVacio()) return new EnlazadoArbolBinario<>();
        else return new EnlazadoArbolBinario<>(a.raiz(), copia(a.hijoIzq()), copia(a.hijoDer()));
    }
    
    //=======================================================================
    //                       EJERCICIOS ACTIVIDAD 2
    //=======================================================================
    
    //Escribe un metodo que devuelva si un arbol es completo (todos los arboles tienen 2
    //descendientes excepto las hojas
    public static <E> boolean completo (ArbolBinario<E> a) {
        if (a.esVacio()) return true;
        if (a.hijoIzq().esVacio() || a.hijoDer().esVacio()) {
            //Si algun hijo es vacio, ambos hijos deben ser vacios
            return a.hijoIzq().esVacio() && a.hijoDer().esVacio();
        }
        return completo(a.hijoIzq()) && completo(a.hijoDer());
    }
    
    //Escribe un metodo que dado dos arboles vea si son identicos o no
    public static <E> boolean identicos (ArbolBinario<E> a, ArbolBinario<E> b) {
        if (a.esVacio() || b.esVacio()) {
            return a.esVacio() && b.esVacio();
        }
        if (!a.raiz().equals(b.raiz())) {
            return false;
        }
        return identicos(a.hijoIzq(), b.hijoIzq()) && identicos(a.hijoDer(), b.hijoDer());
    }
    
    //metodo que cuenta el numero de nodos en el nivel n
    public static <E> int contarNivel(ArbolBinario<E> a, int nivel) {
        if (a.esVacio() /*|| nivel < 0*/) {
            return 0;
        }
        if (nivel == 0) {
            return 1;
        }
        return contarNivel(a.hijoIzq(), nivel - 1) + contarNivel(a.hijoDer(), nivel - 1);
    }
    
    //metodo que copie A pero sin las hojas
    public static <E> ArbolBinario<E> ñopia (ArbolBinario<E> a) {
        if (a.esVacio()) return new EnlazadoArbolBinario<>();
        if (a.hijoDer().esVacio() && a.hijoIzq().esVacio()) {
            return new EnlazadoArbolBinario<>();
        }
        return new EnlazadoArbolBinario<>(a.raiz(),
                ñopia(a.hijoIzq()), ñopia(a.hijoDer()));
    }
    
    //metodo para calcular altura arbol
    public static <E> int altura (ArbolBinario<E> a) {
        if (a.esVacio()) {
            return -1;
        }
        return 1 + Math.max(altura(a.hijoIzq()), altura(a.hijoDer()));
    }
    
    //arbol de seleccion: cada nodo representa al menor de sus dos hijos excepto hojas
    public static <E extends Comparable<E>> boolean esArbolSeleccion (ArbolBinario<E> arbol) {
        if (arbol.esVacio()) {
            return true;
        }
        if (arbol.hijoDer().esVacio() && arbol.hijoIzq().esVacio()) {
            return true; //es hoja
        }
        if (!esArbolSeleccion(arbol.hijoIzq()) || !esArbolSeleccion(arbol.hijoDer())) {
            return false;
        }
        
        E menor;
        if (arbol.hijoDer().esVacio()) {
            menor = arbol.hijoIzq().raiz();
        } else if (arbol.hijoIzq().esVacio()) {
            menor = arbol.hijoDer().raiz();
        } else {
            if (arbol.hijoDer().raiz().compareTo(arbol.hijoIzq().raiz()) < 0) {
                menor = arbol.hijoDer().raiz();
            } else {
                menor = arbol.hijoIzq().raiz();
            }
        }
        return arbol.raiz().compareTo(menor) == 0;
    }
    
    //existe suma devuelve cierto si existe al menos un nodo del arbol tal que la suma de
    //sus descendientes es igual al parametro suma
    public static <E> boolean existeSuma (ArbolBinario<Integer> arbol, Integer suma) {
        if (arbol.esVacio()) {
            return false;
        }

        // Calcula la suma de todos los nodos descendientes del nodo actual
        int sumaActual = sumaNodos(arbol);

        // Verifica si la suma actual es igual a la suma buscada
        if (sumaActual == suma) {
            return true;
        }

        // Verifica recursivamente en los subárboles izquierdo y derecho
        return existeSuma(arbol.hijoIzq(), suma) || existeSuma(arbol.hijoDer(), suma);
    }
    
    // Método auxiliar para calcular la suma de los nodos descendientes de un nodo dado
    private static int sumaNodos(ArbolBinario<Integer> arbol) {
        if (arbol.esVacio()) {
            return 0;
        }
        return arbol.raiz() + sumaNodos(arbol.hijoIzq()) + sumaNodos(arbol.hijoDer());
    }
    
    //realizar copia hasta cierto nivel n de un arbol binario
    public static <E> ArbolBinario<E> copiaHastaNivel(ArbolBinario<E> a, int nivel) {
        if (a.esVacio()) return new EnlazadoArbolBinario<>();
        if (nivel == 0) {
            return new EnlazadoArbolBinario<>(a.raiz(), new EnlazadoArbolBinario<>(), 
                    new EnlazadoArbolBinario<>());
        }
        return new EnlazadoArbolBinario<>(a.raiz(), copiaHastaNivel(a.hijoIzq(), nivel - 1),
                copiaHastaNivel(a.hijoDer(), nivel - 1));
    }
    
    //un elemento de un arbol se dira de nivel k si aparece en el arbol a distancia k de la raiz
    //escribe un metodo que determine si un elemento esta a distancia k
    public static <E> boolean esDistanciaK(ArbolBinario<E> a, E elem, int k) {
        if(a.esVacio() /*|| k < 0*/) return false;
        if (k == 0) {
            return a.raiz().equals(elem);
        }
        return esDistanciaK(a.hijoDer(), elem, k-1) || esDistanciaK(a.hijoIzq(), elem, k-1);
    }
    
    //=======================================================================
    //                       EJERCICIOS ACTIVIDAD 3
    //=======================================================================

    //Escribe un método que dados los recorridos en preoden e inorden de un árbol binario, 
    //reconstruya el árbol. Suponemos que los recorridos son String y que no hay caracteres repetidos
    public static ArbolBinario<Character> construir(String preord, String inord) {
        if (preord.length() == 0) {
            return new EnlazadoArbolBinario();
        } else {
            char raiz = preord.charAt(0); //la raiz del arbol es el primero de preord
            int p = inord.indexOf(raiz); //encuentra la posicion de esa raiz en inord
            //construye el subarbol izquierdo recursivamente
            ArbolBinario hi = construir(preord.substring(1, p + 1),
                    //los siguientes p caracteres despues de la raiz en preord
                    inord.substring(0, p));
                    //los p caracteres antes de la raiz en inorden
            //construye el arbol derecho recursivamente
            ArbolBinario hd = construir(preord.substring(p + 1),
                    inord.substring(p + 1));
            
            //Crea el arbol con la raiz y los 2 subarboles hi, hd
            ArbolBinario arbol = new EnlazadoArbolBinario(raiz, hi, hd);
            return arbol;
        }
    }
    
    //determinar si cierto camino existe en el arbol, el camino debe comenzar en la raiz
    public static boolean esCamino(ArbolBinario<Character> arbol, String camino) {
        if (camino.length() == 0)
            return false;
        else if (arbol.esVacio())
            return false;
        else if (camino.length() == 1 && arbol.raiz().equals(camino.charAt(0))
             && arbol.hijoIzq().esVacio() && arbol.hijoDer().esVacio()) {
            return true;
        }
        else if (arbol.raiz().equals(camino.charAt(0))) {
            return esCamino(arbol.hijoIzq(), camino.substring(1)) || esCamino(arbol.hijoDer(), camino.substring(1));
        }
        else
            return false;
    }
    
    //dado un arbol y un elemento devolver el padre de ese elemento sin haber repetidos
    //si no esta o es raiz devolver null
    public static <E> E getPadre (ArbolBinario<E> a, E elemento) {
        /*if (elemento == null)
            return null;*/
        if (a.esVacio())
            return null;
        if (a.raiz().equals(elemento))
            return null;
        
        //elemento o como hijo izquierdo o como hijo derecho
        if(!a.hijoIzq().esVacio() && a.hijoIzq().equals(elemento) || !a.hijoDer().esVacio() && a.hijoDer().equals(elemento))
            return a.raiz();
        
        //buscar recursivamente por la izquierda
        E padreIzq = getPadre(a.hijoIzq(), elemento);
        if(padreIzq == null) //buscar recursivamente por la derecha
            return getPadre(a.hijoDer(), elemento);
        return padreIzq;
    }
}
