package Actividad_6_7;

import Interfaces.Arco;
import Interfaces.Grafo;
import Interfaces.Map;
import Interfaces.Vertice;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Vector;

public class Actividad7 {

    //=======================================================================
    //                       EJERCICIOS TEORIA
    //=======================================================================
    
    //dado un grafo y un vertice quiero calcular el grado de salida de ese grafo
    public static <E,T> int gradoSalida (Grafo<E,T> g, Vertice<E> v) {
        //si quiero calcular el grado de salida supongo que el grafo es dirigido
        Iterator <Vertice<E>> adys = g.adyacentes(v); //llamar a adyacentes de v q me devuelve  un iterador sobre todos los adyacentes o sucesores
        int gs=0;
        while(adys.hasNext()) {
            adys.next(); //avanzo dentro del iterador
            gs++;
        }
        return gs;
    }
    
    /**
     * Produce: devuelve un iterador sobre los predecesores del vértice v en el
     * grafo g. Se dice que w es predecesor del vértice v si existe un arco que
     * tenga por origen w y por destino v, es decir, el arco (w,v) pertenece al
     * conjunto de arcos del grafo.
     */
    public static <E, T> Iterator<Vertice<E>> predecesores(Grafo<E, T> g, Vertice<E> v) {
        List<Vertice<E>> pred = new ArrayList<>();
        Iterator<Vertice<E>> itV = g.vertices(); //llamo al metodo vertices y los recorro como iterador para saber si cada uno es un predecesor
        while (itV.hasNext()) { //para saber si alguno es un predecesor, miro si de cada vertice su primer sucesor es el vertice v q me interesa como param
            Vertice<E> origen = itV.next(); //obtengo el vertice de itV
            Iterator<Vertice<E>> itD = g.adyacentes(origen);
            while (itD.hasNext()) { //si tiene adyacentes
                if (itD.next().equals(v)) { //primer adyacente es igual a v
                    pred.add(origen); //añado el primer vertice de itV a mi iterador
                }
            }
        }
        return pred.iterator();
    }

    // ---- RECORRIDO EN PROFUNDIDAD ----
    public static <E,T> void profundidad (Grafo<E,T> g, Vertice<E> v) {
        Vector<Vertice<E>> visitados = new Vector<>();
        profundidad(g,v,visitados);
    }
    
    private static <E,T> void profundidad (Grafo<E,T> g, Vertice<E> v, Vector<Vertice<E>> visitados) {
        System.out.println(v);
        visitados.add(v);
        Iterator <Vertice<E>> adys = g.adyacentes(v);
        while (adys.hasNext()) {
            Vertice <E> w = adys.next();
            if (!visitados.contains(w)) profundidad(g,w,visitados);
        }
    }
    
    // ---- RECORRIDO EN ANCHURA ----
    public static <E,T> void anchura (Grafo<E,T> g, Vertice<E> v) {
        Vector<Vertice<E>> visitados = new Vector<>();
        Queue<Vertice<E>> porEXplorar = new ArrayDeque<>();
        porEXplorar.add(v);
        visitados.add(v);
        do {
            v=porEXplorar.remove();
            System.out.println(v);
            Iterator<Vertice<E>> adys = g.adyacentes(v);
            while (adys.hasNext()) {
                Vertice <E> w = adys.next();
                if (!visitados.contains(w)) {
                    porEXplorar.add(w);
                    visitados.add(w);
                }
            }
        } while (!porEXplorar.isEmpty());
    }
    
    /**
     * Un vértice se dice sumidero si su grado de entrada es n-1 y su grado de
     * salida es 0. |V|=n, es decir, n es el número de vértices del grafo.
     *
     * @param <E>
     * @param <T>
     * @param g
     * @param v
     * @return
     */
    public static <E, T> boolean esSumidero(Grafo<E, T> g, Vertice<E> v) {
        Iterator<Vertice<E>> it = g.vertices();
        if (!it.hasNext()) { //Es vacio
            return false;
        }
        if (g.adyacentes(v).hasNext()) {//si v tiene adyacentes, grado de salida != 0
            return false;
        }

        while (it.hasNext()) {
            Vertice<E> actual = it.next();
            Iterator<Vertice<E>> it2 = g.adyacentes(actual);
            while (it2.hasNext() && !actual.equals(v)) {
                actual = it2.next();
                //Mientras existan adyacentes a actual, comprueba si son el vertice
                //Si actual es v, el bucle no se ejecuta
            }
            if (!actual.equals(v)) {
                //No se ha encontrado v, es decir, ni estamos en v ni adyacentes a v
                return false;
            }
        }
        return true;
    }

    /**
     * Escribe un método que devuelva cierto si un grafo es regular. Un grafo es
     * regular si todos sus vértices tienen el mismo número de vértices
     * adyacentes.
     *
     * @param <E>
     * @param <T>
     * @param g
     * @return
     */
    public static <E, T> boolean esRegular(Grafo<E, T> g) {
        Iterator<Vertice<E>> it = g.vertices();
        int num = 0xCAFE;
        if (it.hasNext()) {
            num = numAdyacentes(g, it.next());
        }
        while (it.hasNext()) {
            if (num != numAdyacentes(g, it.next())) {
                return false;
            }
        }

        return true;
    }

    private static <E, T> int numAdyacentes(Grafo<E, T> g, Vertice<E> v) {
        Iterator<Vertice<E>> it = g.adyacentes(v);
        int num = 0;
        while (it.hasNext()) {
            num++;
            it.next();
        }
        return num;
    }
    
    //---------------------
    
    public static <E, T> boolean esConectadoDesdeVertice(Grafo<E, T> g, Vertice<E> v) {
        List<Vertice<E>> porAnalizar = new LinkedList<>();
        List<Vertice<E>> analizados = new LinkedList<>();

        porAnalizar.add(v);
        while (!porAnalizar.isEmpty()) {
            Iterator<Arco<E, T>> itr = g.arcos(); // pillamos todos los arcos
            while (itr.hasNext()) {
                Arco<E, T> aux = itr.next();
                if (aux.getDestino().equals(porAnalizar.get(0))//sabemos que va al que estamos analizando
                        && !analizados.contains(aux.getOrigen())) {//y no lo hemos analizado
                    porAnalizar.add(aux.getOrigen());
                }
            }
            analizados.add(porAnalizar.remove(0));
        }
        return analizados.size() == contarVertices(g.vertices());
    }
    
    private static <E, T> int contarVertices(Iterator<Vertice<E>> itr) {

        int toret = 0;
        while (itr.hasNext()) {
            toret++;
            itr.next();
        }
        return toret;
    }
    
    //------------------
    
    public static <E> List<Vertice<String>> getOrdenTopologico(Grafo<String, Integer> g) {
        List<Vertice<String>> toret = new ArrayList<>();
        Queue<Vertice<String>> cola = new ArrayDeque<>();
        Map<Vertice<String>, Integer> mapa = new HashMap<>();

        // Paso 1: calcular grado de ENTRADA de cada vértice
        Iterator<Vertice<String>> itVertices = g.vertices();
        while (itVertices.hasNext()) {
            Vertice<String> v = itVertices.next();
            mapa.insertar(v, 0); // inicializamos con grado 0
        }

        // Paso 2: recorrer los arcos y aumentar grado de entrada de los destinos
        Iterator<Arco<String, Integer>> itArcos = g.arcos();
        while (itArcos.hasNext()) {
            Arco<String, Integer> arco = itArcos.next();
            Vertice<String> destino = arco.getDestino();
            Integer grado = mapa.get(destino);
            mapa.insertar(destino, grado + 1);
        }

        // Paso 3: añadir a la cola los vértices con grado 0
        Iterator<Vertice<String>> it = mapa.getClaves();
        while (it.hasNext()) {
            Vertice<String> v = it.next();
            if (mapa.get(v) == 0) {
                cola.add(v);
            }
        }

        // Paso 4: proceso principal de Kahn
        while (!cola.isEmpty()) {
            Vertice<String> v = cola.remove();
            toret.add(v);

            // Para cada adyacente (v → w), reducir su grado de entrada
            Iterator<Vertice<String>> adyacentes = g.adyacentes(v);
            while (adyacentes.hasNext()) {
                Vertice<String> w = adyacentes.next();
                int nuevoGrado = mapa.get(w) - 1;
                mapa.insertar(w, nuevoGrado);
                if (nuevoGrado == 0) {
                    cola.add(w);
                }
            }
        }

        return toret;
    }

    /**
     * Escribe un método que demuestre el teorema del apretón de manos. Dicho
     * teorema dice que la suma de los grados de un grafo es igual al doble del
     * número de aristas. También dice que en un grafo siempre hay un número par
     * de vértices de grado impar
     *
     * @param <E>
     * @param <T>
     * @param g
     * @return
     */
    public static <E, T> boolean lemaApretonManos(Grafo<E, T> g) {
        int numGradoImpar = 0;
        int sumaGrados = 0;
        int numAristas = 0;
        int gradoActual = 0;
        Iterator<Arco<E, T>> itArco = g.arcos();
        List<Arco<E, T>> arcos = new ArrayList<>();
        while (itArco.hasNext()) {
            //Cuenta las aristas y las guarda para usarlas
            arcos.add(itArco.next());
            numAristas++;
        }

        Iterator<Vertice<E>> it = g.vertices();
        //Recorre cada vertice, calculando su grado
        while (it.hasNext()) {
            Vertice<E> v = it.next();
            gradoActual = 0;
            for (Arco<E, T> arco : arcos) {
                if (arco.getOrigen().equals(v)) {
                    gradoActual++; //Aumenta el grado de salida
                }
                if (arco.getDestino().equals(v)) {
                    gradoActual++; //Aumenta el grado de llegada
                }
            }
            numGradoImpar = gradoActual % 2;
            sumaGrados += gradoActual;
        }

        return numGradoImpar % 2 == 0 && sumaGrados == 2 * numAristas;
    }

    /**
     * Escribe un método que dado un grafo dirigido y dos vértices devuelva
     * cierto si existe un camino simple de un vértice a otro.
     *
     * @param <E>
     * @param <T>
     * @param g
     * @param v1
     * @param v2
     * @return
     */
    public static <E, T> boolean hayCaminoEntreDos(Grafo<E, T> g, Vertice<E> v1, Vertice<E> v2) {
        if (!g.estaVertice(v2) || !g.estaVertice(v1)) {
            return false;
        }
        return hayCamino(g, v1, v2, new ArrayList());
    }

    private static <E, T> boolean hayCamino(Grafo<E, T> g, Vertice<E> v1, Vertice<E> v2, List<Vertice<E>> visitados) {
        visitados.add(v1);
        Iterator<Vertice<E>> it = g.adyacentes(v1);
        Vertice<E> destino;
        while (it.hasNext()) {
            destino = it.next();
            if (destino == v2) {
                return true;
            }
            if (!visitados.contains(destino) && hayCamino(g, destino, v2, visitados)) {
                //Si el destno no ha sido visitado, se comprueba si tiene camino a v2
                //Si un vertice adyacente al actual tiene camino, hay camino
                return true;
            }
        }
        return false;
    }

    /**
     * Dado un grafo y una secuencia de vértices, escribe un método que
     * determine si dicha secuencia de vértices es un ciclo del grafo. Dado un
     * grafo G, un ciclo en G es una secuencia de nodos de G
     * [v(1),v(2),v(3),...,v(n)] tal que: i) v(1) = v(n), ii)
     * (v(1),v(2)),(v(2),v(3)), (v(3),v(4)), ..., (v(n-1),v(n)) son aristas de
     * G, ii) salvo v(1) = v(n), todos los v(i) son distintos entre sí.
     *
     * @param <E>
     * @param <T>
     * @param g
     * @param camino
     * @return
     */
    public static <E, T> boolean esCiclo(Grafo<E, T> g, List<Vertice<E>> camino) {
        if (!g.vertices().hasNext()) {
            return false; //grafo vacio
        }
        if (camino.isEmpty() || !camino.get(0).equals(camino.get(camino.size()-1))) {
        //si esta vacio o             
        //si el primero y el ultimo de camino son distintos, no es un ciclo
            return false;
        }
        return esCiclo(g, camino, new ArrayList());
    }

    private static <E, T> boolean esCiclo(Grafo<E, T> g, List<Vertice<E>> camino, List<Vertice<E>> visitados) {
        
        Vertice<E> actual = camino.remove(0);
        if (visitados.contains(actual)) {
            //si actual ya visitado, tiene que ser el ultimo
            return camino.isEmpty();
        }
        visitados.add(actual);
        if (!g.estaVertice(actual)) {
            return false;
            //no esta en el grafo el vertice actual
        }
      
        Iterator<Vertice<E>> it = g.adyacentes(actual);
        Vertice<E> destino;
        while (it.hasNext()) {
            destino = it.next();
            if (destino.equals(camino.get(0))) { //destino es el siguiente del camino
                return esCiclo(g, camino, visitados);
            }
        }

        return false; //No quedan adyacentes 
    }

}
