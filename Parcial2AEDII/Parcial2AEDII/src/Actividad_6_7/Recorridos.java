package Actividad_6_7;

import Interfaces.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Recorridos {

    public static <E, T> void recorridoProfundidad(Grafo<E, T> g, Vertice<E> v) {
        recorridoProfundidad(g, v, new ArrayList());
    }

    private static <E, T> void recorridoProfundidad(Grafo<E, T> g, Vertice<E> v, List<Vertice<E>> visitados) {
        System.out.println(v);
        visitados.add(v);
        Iterator<Vertice<E>> it = g.adyacentes(v);
        while (it.hasNext()) {
            Vertice<E> w = it.next();
            if (!visitados.contains(w)) {
                recorridoProfundidad(g, w, visitados);
            }
        }
    }

    public static <E, T> void recorridoAnchura(Grafo<E, T> g, Vertice<E> v) {
        Queue<Vertice<E>> porVisitar = new LinkedList<>();
        List<Vertice<E>> visitados = new ArrayList<>();
        porVisitar.add(v);
        visitados.add(v);

        do {
            Vertice<E> actual = porVisitar.remove();
            Iterator<Vertice<E>> it = g.adyacentes(actual);
            while (it.hasNext()) {
                Vertice<E> w = it.next();
                if (!visitados.contains(w)) {
                    porVisitar.add(w);
                    visitados.add(actual);

                }
            }

        } while (!porVisitar.isEmpty());

    }
}
