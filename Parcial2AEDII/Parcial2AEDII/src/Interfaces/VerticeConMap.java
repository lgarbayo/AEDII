package Interfaces;

import Actividad_6_7.HashMap;

public class VerticeConMap<E, T> {

    private Vertice<E> origen; // vértice del grafo
    private Map<Vertice<E>, T> mapAdy; // mapa de adyacentes del vértice origen

    public VerticeConMap(Vertice<E> v) {
        origen = v;
        mapAdy = new HashMap<>();
    }

    public Vertice<E> getVertice() {
        return origen;
    }

    public Map<Vertice<E>, T> getAdy() {
        return mapAdy;
    }
}
