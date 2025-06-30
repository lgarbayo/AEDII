package Interfaces;

public class Vertice<E> {

    private E etiqueta;

    public Vertice(E o) {
        etiqueta = o;
    }

    public E getEtiqueta() {
        return etiqueta;
    }
}
