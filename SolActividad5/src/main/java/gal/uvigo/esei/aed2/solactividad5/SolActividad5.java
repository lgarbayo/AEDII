/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gal.uvigo.esei.aed2.solactividad5;

import arbolGeneral.*;
import java.util.*;

/**
 *
 * @author nicol
 */
public class SolActividad5 {
    
    //=======================================================================
    //                       EJERCICIOS TEORIA
    //=======================================================================
    
    //Recorridos en profundidad:
    public static <E> void preorden (ArbolGeneral<E> a) {
        if (!a.esVacio()) {
            System.out.println(a.raiz() + " "); //mostrar contenido raiz
            //recorrido preorden de los sub arboles
            ArbolGeneral<E> hijo = a.hijoMasIzq();
            while(!hijo.esVacio()) {
                preorden(hijo);
                hijo = hijo.hermanoDer();
            } //hasta que hijo este vacio, iterar sobre la lista de sub arboles
        }
    }
    
    public static <E> void postorden (ArbolGeneral<E> a) {
        if (!a.esVacio()) {
            ArbolGeneral<E> aux = a.hijoMasIzq();
            while(!aux.esVacio()) {
                postorden(aux);
                aux = aux.hermanoDer();
            }
            System.out.println(" " + a.raiz());
            //post de izq, post de der, raiz
        }
    }

    // Cuenta el numero de nodos de un arbol general
    public static <E> int contarNodos(ArbolGeneral<E> a) {
        if (a.esVacio()) {
            return 0;
        } else {
            int cont = 1; //si no esta vacio inicalizamos a 1, se corresponde con el nodo raiz
            //ahora necesitamos recorrer los subarboles y llamada recursiva
            ArbolGeneral<E> hijo = a.hijoMasIzq();
            while (!hijo.esVacio()) {
                cont += contarNodos(hijo);
                hijo = hijo.hermanoDer();
            }
            return cont;
        }
    }

    // Contar el numero de hojas de un arbol general
    public static <E> int numHojas(ArbolGeneral<E> a) {
        if (a.esVacio()) {
            return 0;
        } else if (a.hijoMasIzq().esVacio()) { //si esta vacio su hijo mas a la izquierda, la raiz ya no tiene hijos, entonces es hoja
            return 1;
        } else { //por lo menos tiene un subarbol
            int cont = 0;
            ArbolGeneral<E> hijo = a.hijoMasIzq(); //me coloco en el hijo mas a la izq y llamda recursiva
            while (!hijo.esVacio()) {
                cont += numHojas(hijo);
                hijo = hijo.hermanoDer();
            }
            return cont;
        }
    }

    // Determina si dos arboles generales son identicos
    public static <E> boolean identicos(ArbolGeneral<E> a, ArbolGeneral<E> b) {
        if (a.esVacio() && b.esVacio()) {
            return true; //vacios -> los dos son identicos
        }
        //ahora, ninguno esta vacio, por lo menos tienen nodo raiz
        if (!a.esVacio() && !b.esVacio()) {
            if (!a.raiz().equals(b.raiz())) { //pregunto si ese nodo raiz es igual, si no lo es ya son false
                return false;
            } else { //son iguales las raices, ahora tienen q ser iguales los subarboles 2 a 2
                // recorrer LOS 2 SUBARBOLES (A Y B)
                ArbolGeneral<E> ha = a.hijoMasIzq();
                ArbolGeneral<E> hb = b.hijoMasIzq();
                while (!ha.esVacio() && !hb.esVacio() && identicos(ha, hb)) {
                    ha = ha.hermanoDer();
                    hb = hb.hermanoDer();
                    //avanzo
                }
                return ha.esVacio() && hb.esVacio();
            }
        }
        return false;
    }

    // Contar el numero de hijos de un arbol
    public static <E> int contarHijos(ArbolGeneral<E> a) {
        if (a.esVacio()) {
            return 0;
        } else {
            int cont = 0;
            ArbolGeneral<E> hijo = a.hijoMasIzq();
            while (!hijo.esVacio()) {
                cont += 1;
                hijo = hijo.hermanoDer();
            }
            return cont;
        }
    }

    //=======================================================================
    //                       EJERCICIOS ACTIVIDAD
    //=======================================================================
    
    // DADO UN ARBOL DE ENTEROS CALCULAR LA SUMA DE SUS NODOS
    public static int getSuma(ArbolGeneral<Integer> arbol) {
        if (arbol.esVacio()) {
            return 0;
        }

        int suma = arbol.raiz();
        ArbolGeneral<Integer> hijo = arbol.hijoMasIzq();
        while (!hijo.esVacio()) {
            suma += getSuma(hijo);
            hijo = hijo.hermanoDer();
        }
        return suma;

    }

    // DADO DOS ARBOLES GENERALES DETERMINAR SI TIENEN MISMA ESTRUCTURA
    public static <E> boolean esIgualEstructura(ArbolGeneral<E> arbol1, ArbolGeneral<E> arbol2) {
        if (arbol1.esVacio() && arbol2.esVacio()) {
            return true;    // LOS DSO VACIOS -> SI
        }
        if (!arbol1.esVacio() && !arbol2.esVacio()) {
                // RECORRER LOS DOS A LA VEZ PARA COMPROBAR
            ArbolGeneral<E> hijo1 = arbol1.hijoMasIzq();
            ArbolGeneral<E> hijo2 = arbol2.hijoMasIzq();

            while (!hijo1.esVacio() && !hijo2.esVacio() && esIgualEstructura(hijo1, hijo2)) {
                    // IGUAL Q IDENTICOS
                hijo1 = hijo1.hermanoDer();
                hijo2 = hijo2.hermanoDer();
            }
            return hijo1.esVacio() && hijo2.esVacio();
        }
        return false;
    }

    // ARBOL 2-3 ES AQUEL EN EL QUE CADA NODO TIENE EXACTAMENTE 2 O 3 HIJOS, EXCEPTO LAS HOJAS
    public static <E> boolean esArbolDosTres(ArbolGeneral<E> a) {
        if (a.esVacio()) {
            return true;
        }
        if (a.hijoMasIzq().esVacio()) {
            return true;
        }
        if (contarHijos2(a) != 2 && contarHijos2(a) != 3) {
            return false;
        }
        ArbolGeneral<E> hijo = a.hijoMasIzq();
        while (!hijo.esVacio() && esArbolDosTres(hijo)) {
            hijo = hijo.hermanoDer();
        }
        return hijo.esVacio();
//        int hijos = 1;
//        ArbolGeneral<E> hijo = arbol.hijoMasIzq();
//        ArbolGeneral<E> hermano = hijo.hermanoDer();
//
//        while (!hermano.esVacio()) {
//            hijos++;
//            hermano = hermano.hermanoDer();
//        }
//        if (hijos != 2 && hijos != 3) {
//            return false;
//        }
//        while (!hijo.esVacio()) {
//            if (!esArbolDosTres(hijo)) {
//                return false;
//            }
//            hijo = hijo.hermanoDer();
//        }
//        return true;
    }
    private static <E> int contarHijos2(ArbolGeneral<E> a) {
        if (a.esVacio()) {
            return 0;
        } else {
            int cont = 0;
            ArbolGeneral<E> hijo = a.hijoMasIzq();
            while (!hijo.esVacio()) {
                cont += 1;
                hijo = hijo.hermanoDer();
            }
            return cont;
        }
    }

    // SELECCION ES UN ARBOL DONDE CADA NODO REPRESENTA AL MENOR DE SUS HIJOS, EXCEPTO HOJASHHH
    public static boolean esSeleccion(ArbolGeneral<Integer> arbol) {
        if (arbol.esVacio()) {
            return true;
        }
        if (arbol.hijoMasIzq().esVacio()) {
            return true;
        }
        ArbolGeneral<Integer> hijo = arbol.hijoMasIzq();
        ArbolGeneral<Integer> hermano = hijo.hermanoDer();
        int menor = hijo.raiz();

        while (!hermano.esVacio()) {
            if (hermano.raiz() < menor) {
                menor = hermano.raiz();
            }
            hermano = hermano.hermanoDer();
        }
        if (menor != arbol.raiz()) {
            return false;
        }
        while (!hijo.esVacio()) {
            if (!esSeleccion(hijo)) {
                return false;
            }
            hijo = hijo.hermanoDer();
        }
        return true;
    }

    // DETERMINA EL NIVEL DE UN ELEMENTO CONCRETO EN EL ARBOL GENERAL
    public static <E> int getNivel(ArbolGeneral<E> arbol, E i) {
        if (arbol.esVacio()) {
            return -1;
        }
        if (arbol.raiz() == i) {
            return 0;
        }
        if (arbol.hijoMasIzq().esVacio()) {
            return 0;
        }
        int nivel = 1;
        ArbolGeneral<E> hijo = arbol.hijoMasIzq();
        ArbolGeneral<E> hermano = hijo;

        while (!hermano.esVacio()) {
            if (hermano.raiz() == i) {
                return nivel;
            }
            hermano = hermano.hermanoDer();
        }
        return nivel + getNivel(hijo, i);
    }

    public static <E> int getGrado(ArbolGeneral<E> arbol) {
        if (arbol.esVacio()) {
            return -1;
        }

        int maxGrado = contarHijos(arbol);
        ArbolGeneral<E> hijo = arbol.hijoMasIzq();
        while (!hijo.esVacio()) {
            int grado = getGrado(hijo);
            if (grado > maxGrado) {
                maxGrado = grado;
            }
            hijo = hijo.hermanoDer();
        }
        return maxGrado;

    }

    public static <E> int getAltura(ArbolGeneral<E> arbol) {
        if (arbol.esVacio()) {
            return -1;
        }
        if (arbol.hijoMasIzq().esVacio()) {
            return 0;
        }
        int maxAltura = 0;
        ArbolGeneral<E> hijo = arbol.hijoMasIzq();
        while (!hijo.esVacio()) {
            int altura = getAltura(hijo);
            if (altura > maxAltura) {
                maxAltura = altura;
            }
            hijo = hijo.hermanoDer();
        }
        return ++maxAltura;
    }

    public static <E> void getAnchura(ArbolGeneral<E> arbol) {
        Queue<ArbolGeneral<E>> cola = new LinkedList<>();

        if (!arbol.esVacio()) {

            cola.add(arbol); // Añadimos la raíz a la cola

            while (!cola.isEmpty()) {
                ArbolGeneral<E> nodoActual = cola.poll(); // Desencolamos el primer elemento
                System.out.print(nodoActual.raiz() + " "); // Mostramos el dato del nodo

                // Añadimos los hijos del nodo actual a la cola
                ArbolGeneral<E> hijo = nodoActual.hijoMasIzq();
                while (!hijo.esVacio()) {
                    cola.add(hijo); // Agregamos el hijo a la cola
                    hijo = hijo.hermanoDer(); // Pasamos al siguiente hermano
                }
            }
        }
    }

    // CUENTE EL NUMERO DE NODOS PARES EN UN ARBOL DE ENTEROS COMO @PARAM
    public static int getNumNodosPares(ArbolGeneral<Integer> arbol) {
        if (arbol.esVacio()) {
            return 0;
        }
        int cont = 0;

        if (arbol.raiz() % 2 == 0) {
            cont++;
        }
        ArbolGeneral<Integer> hijo = arbol.hijoMasIzq();
        while (!hijo.esVacio()) {
            cont += getNumNodosPares(hijo);
            hijo = hijo.hermanoDer();
        }
        return cont;
    }

    // GUARDAR EN LA LISTA LAS HOJAS DEL ARBOL GENERAL
    public static <E> void getListaHojas(ArbolGeneral<E> arbol, List<E> result) {
        if (!arbol.esVacio()) {
            if (arbol.hijoMasIzq().esVacio()) {
                result.add(arbol.raiz());
            }
            ArbolGeneral<E> hijo = arbol.hijoMasIzq();
            while (!hijo.esVacio()) {
                getListaHojas(hijo, result);
                hijo = hijo.hermanoDer();
            }
        }
    }
}
