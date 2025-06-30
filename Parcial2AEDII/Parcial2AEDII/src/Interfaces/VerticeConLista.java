package Interfaces;

import java.util.List;

public class VerticeConLista<E, T> {

    private Vertice<E> verticeOrigen;
    private List<VerticeAdyacente<E, T>> listAdyacentes;

    public VerticeConLista(Vertice<E> verticeOrigen, List<VerticeAdyacente<E, T>> listAdyacentes) {
        this.verticeOrigen = verticeOrigen;
        this.listAdyacentes = listAdyacentes;
    }

    public Vertice<E> getVertice() {
        return verticeOrigen;
    }

    public List<VerticeAdyacente<E, T>> getLista() {
        return listAdyacentes;
    }

    
}
