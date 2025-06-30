package Especificion_Grafos;

import Interfaces.Arco;
import Interfaces.Grafo;
import Interfaces.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaDeArcos<E, T> implements Grafo<E, T> {

    private List<Vertice<E>> listVertices; //lista de certices
    private List<Arco<E, T>> listArcos; //cada arco tiene dos referencias, una al vertice origen y otra al vertice destino

    public ListaDeArcos() { //constructor de la lista de vertices vacio
        this.listVertices = new ArrayList<>();
        this.listArcos = new ArrayList<>();
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        return listVertices.contains(v);
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        return listArcos.contains(a);
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        return listVertices.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        return listArcos.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        if (!estaVertice(v)) {
            return null;
        }
        List<Vertice<E>> ady = new ArrayList(); //guardar todos los adyacentes en una lista
        for (Arco<E, T> arco : listArcos) { //for e para recorrer cada arco
            if (arco.getOrigen().equals(v)) { //si vertice origen es igual a v añadimos el destino ya q sera adyacente
                ady.add(arco.getDestino());
            }
        }
        return ady.iterator(); //devolvemos el iterador
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (!estaVertice(v)) {
            listVertices.add(v);
        }
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        if (!estaArco(a)) {
            insertarVertice(a.getDestino());
            insertarVertice(a.getOrigen());
            listArcos.add(a);
        }

    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        if (listVertices.remove(v)) {
            //Si se modifica una lista a la vez que se recorre con un forEach salta un error
            List<Arco<E,T>> aux = new ArrayList<>();
            for (Arco<E, T> arco : listArcos) {
                aux.add(arco);
            }
            for (Arco<E, T> arco : aux) {
                if (arco.getDestino().equals(v)) {
                    eliminarArco(arco);
                }
            }
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        listArcos.remove(a);
    }

}
