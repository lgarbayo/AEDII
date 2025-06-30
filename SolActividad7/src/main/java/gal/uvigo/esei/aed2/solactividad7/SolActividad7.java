/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gal.uvigo.esei.aed2.solactividad7;

import grafo.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import mapa.*;

/**
 *
 * @author nicol
 */
public class SolActividad7 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static <E, T> void profundidad(Grafo<E, T> g, Vertice<E> v) {
        Set<Vertice<E>> visitados = new HashSet<>();
        profundidad(g, v, visitados);
    }

    private static <E, T> void profundidad(Grafo<E, T> g, Vertice<E> v, Set<Vertice<E>> visitados) {
        System.out.println(v);
        visitados.add(v);
        Iterator<Vertice<E>> adys = g.adyacentes(v);
        while (adys.hasNext()) {
            Vertice<E> w = adys.next();
            if (!visitados.contains(w)) {
                profundidad(g, w, visitados);
            }
        }
    }

    public static <E, T> Iterator<Vertice<E>> predecesores(Grafo<E, T> g, Vertice<E> v) {

        Set<Vertice<E>> predecesor = new HashSet<>();
        Iterator<Arco<E, T>> iterArcos = g.arcos();

        while (iterArcos.hasNext()) {
            Arco<E, T> arco = iterArcos.next();
            if (arco.getDestino().equals(v)) {
                predecesor.add(arco.getOrigen());
            }
        }
        return predecesor.iterator();
    }

    public static <E, T> Iterator<Vertice<String>> predecesores2(Grafo<E, T> g, Vertice<E> v) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static <E> int contar(Iterator<E> iterador) {
        int cont = 0;
        while (iterador.hasNext()) {
            iterador.next();
            cont++;
        }
        return cont;
    }

    public static <E, T> boolean esSumidero(Grafo<E, T> g, Vertice<E> v) {
        int numeroAdyacentes = contar(g.adyacentes(v));

        if (numeroAdyacentes != 0) {
            return false;
        }
        int numVerticesGrafo = contar(g.vertices());
        int numPredecesores = contar(predecesores(g, v));
        return numVerticesGrafo - 1 == numPredecesores;
    }

    public static <E, T> boolean esRegular(Grafo<E, T> g) {
        if (g.esVacio()) {
            return true;
        }
        int adyacentes = contar(g.adyacentes(g.vertices().next()));
        Iterator ite = g.vertices();

        while (ite.hasNext()) {
            if (contar(g.adyacentes((Vertice<E>) ite.next())) != adyacentes) {
                return false;
            }
        }
        return true;
    }

    public static <E, T> boolean esConectadoDesdeVertice(Grafo<E, T> g, Vertice<E> vInicio) {
        Set<Vertice<E>> visitados = new HashSet<>();
        profundidad(g, vInicio, visitados);
        return contar(visitados.iterator()) == contar(g.vertices());
    }

    public static <E, T> List<Vertice<E>> getOrdenTopologico(Grafo<E, T> g) {
        List<Vertice<E>> toret = new ArrayList<>();

        //Se calcula el grado de cada vértice y se guarda dicha información en un Map<Vertice<E>, Integer>.
        Map<Vertice<E>, Integer> mapa = new HashMap<>();
        Iterator ite = g.vertices();
        while (ite.hasNext()) {
            Vertice<E> v = (Vertice<E>) ite.next();
            mapa.insertar(v, contar(predecesores(g, v)));
        }

        //Se crea una cola a la que se añaden los vértices con grado 0.
        Queue<Vertice<E>> cola = new ArrayDeque<>();
        ite = mapa.getClaves();
        while (ite.hasNext()) {
            Vertice<E> v = (Vertice<E>) ite.next();
            if (mapa.get(v) == 0) {
                cola.add(v);
                mapa.eliminar(v);
            }
        }

        //Se sacan vértices de la cola hasta que queda vacía. Para cada vértice que se saca, se
        //añade a la salida y se resta 1 al grado de los vértices adyacentes. Si el grado de alguno
        //de estos vértices adyacentes pasa a ser 0, se añade a la cola.
        while (!cola.isEmpty()) {
            Vertice<E> v = cola.poll();
            toret.add(v);

            ite = g.adyacentes(v);
            while (ite.hasNext()) {
                Vertice<E> vAdyacente = (Vertice<E>) ite.next();
                mapa.insertar(vAdyacente, mapa.get(vAdyacente) - 1);
                if (mapa.get(vAdyacente) == 0) {
                    cola.add(vAdyacente);
                    mapa.eliminar(vAdyacente);
                }
            }
        }
        return toret;
    }

    static <E, T> boolean hayCaminoEntreDos(Grafo<E, T> g, Vertice<E> v1, Vertice<E> v2) {
        //recursivo
        if (g.esVacio()) {
            return false;
        }
        Iterator ite = g.adyacentes(v1);
        while (ite.hasNext()) {
            Vertice<E> adyacente = (Vertice<E>) ite.next();
            if (adyacente == v2) {
                return true;
            } else {
                return hayCaminoEntreDos(g, adyacente, v2);
            }
        }
        return false;
    }

    public static <E, T> boolean esCiclo(Grafo<E, T> g, List<Vertice<E>> camino) {
        if (g.esVacio() || camino.isEmpty()) {
            return false;
        }
        //Primera condicion: v(1) = v(n),
        if (camino.getFirst() != camino.getLast()) {
            return false;
        }
        //Segunda condicion: (v(1),v(2)), (v(2),v(3)), (v(3),v(4)), ..., (v(n-1),v(n)) son aristas de G,
        boolean existe = false;
        for (int i = 0; i < camino.size() - 1; i++) {
            Iterator ite = g.adyacentes(camino.get(i));
            while (ite.hasNext()) {
                Vertice<E> v = (Vertice<E>) ite.next();
                if (v.equals(camino.get(i + 1))) {
                    existe = true;
                }
            }
            if (!existe) {
                return false;
            }
        }
        //Tercera condicion: salvo v(1) = v(n), todos los v(i) son distintos entre sí.
        for (int i = 0; i < camino.size() - 1; i++) {
            for (int j = 0; j < camino.size() - 1; j++) {
                if (i != j && camino.get(i).equals(camino.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
