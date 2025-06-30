package com.mycompany.solactividad8;

public class Arco<E, T> {

    private Vertice<E> origen, destino;
    private T etiqueta;

    public Arco(Vertice<E> o, Vertice<E> d, T e) {
        origen = o;
        destino = d;
        etiqueta = e;
    }

    public Vertice<E> getOrigen() {
        return origen;
    }

    public Vertice<E> getDestino() {
        return destino;
    }

    public T getEtiqueta() {
        return etiqueta;
    }
}
