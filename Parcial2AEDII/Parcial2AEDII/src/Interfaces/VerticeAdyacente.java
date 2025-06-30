package Interfaces;

public class VerticeAdyacente<E, T> {

    private Vertice<E> verticeDestino;
    private T etiquetaArco;

    public VerticeAdyacente(Vertice<E> verticeDestino, T etiquetaArco) {
        this.verticeDestino = verticeDestino;
        this.etiquetaArco = etiquetaArco;
    }

    public Vertice<E> getDestino() {
        return verticeDestino;
    }

    public T getEtiqueta() {
        return etiquetaArco;
    }

   
}
