package Especificion_Grafos;

import Interfaces.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListaDeAdyacencia<E, T> implements Grafo<E, T> {

    private List<VerticeConLista<E, T>> listVertices; //vertices con la lista de adys, no son vertices sin mas
    private int numVertices;

    public ListaDeAdyacencia() {
        listVertices = new ArrayList<>();
        numVertices = 0;

    }
    
    /*
    public class VerticeConLista <E,T>
        private Vertice<E> verticeOrigen;
        private Lista<VerticeAdyacente<E,T>> listAdys;
        // metodos
        public VerticeConLista (Vertice<E> verticeOrigen, Lista<VerticeAdyacente<E,T>> ady) {...}
        public Vertice<E> getVertice() {...}
        public Lista<Adyacente<E,T>> getLista {...}
    
    public class VerticeAdyacente<E,T>
        private Vertice<E> verticeDestino;
        private T etiquetaArco;
        // metodos
        public VerticeAdyacente(Vertice<E> vericeDestino, E etiquetaArco) {...}
        public Vertice<E> getVerticeDestino() {...}
        public getEtiqueta() {...}
    */
    
    /**
     * Busca la posicion del vertice en listVertice
     * @param v
     * @return 
     */
    private int buscarPosicion(Vertice<E> v) {
        Iterator<VerticeConLista<E, T>> it = listVertices.iterator();
        int pos = 0;
        while (it.hasNext() && !it.next().getVertice().equals(v)) {
            pos++;
        }
        return pos;
    }

    /**
     * Busca el vertice v en la lista asociada al vertive en la posicion pos de listVertices
     * @param v
     * @param pos
     * @return 
     */
    private int buscarPosicionAdyacente(Vertice<E> v, int pos) {
        Iterator<VerticeAdyacente<E, T>> it = listVertices.get(pos).getLista().iterator();
        int toRet = 0;
        while (it.hasNext() && !it.next().getDestino().equals(v)) {
            toRet++;
        }
        return toRet;
    }
    
    @Override
    public boolean estaVertice(Vertice<E> v) {
        return buscarPosicion(v) < numVertices;
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        if (a == null) {
            throw new NullPointerException();
        }
        int pos = buscarPosicion(a.getOrigen());
        if (pos < numVertices) {
            int pos2 = buscarPosicionAdyacente(a.getDestino(), pos);
            if (pos2 < listVertices.get(pos).getLista().size()) {
                return listVertices.get(pos).getLista().get(pos2).getEtiqueta().equals(a.getEtiqueta());
            }
        }
        return false;
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        List<Vertice<E>> list = new ArrayList<>(numVertices);
        for (VerticeConLista<E, T> Vertice : listVertices) {
            list.add(Vertice.getVertice());
        }
        return list.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        List<Arco<E, T>> arcos = new ArrayList<>();
        for (VerticeConLista<E, T> origen : listVertices) {
            for (VerticeAdyacente<E, T> destino : origen.getLista()) {
                arcos.add(new Arco(origen.getVertice(), destino.getDestino(), destino.getEtiqueta()));
            }
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        /*if (!estaVertice(v)) {
            return null;
        }
        List<Vertice<E>> ady = new ArrayList<>(); //nueva lista enlazada para devolverlo
        List<VerticeAdyacente<E, T>> lista = listVertices.get(buscarPosicion(v)).getLista();
        for (VerticeAdyacente<E, T> vAdy : lista) { 
            ady.add(vAdy.getDestino());
        }
        return ady.iterator();*/
        List<Vertice<E>> ady = new ArrayList<>();
        for (VerticeConLista<E,T> w : listVertices) {
            if (w.getVertice().equals(v)) {
                List<VerticeAdyacente<E,T>> l = w.getLista();
                for (VerticeAdyacente<E, T> q : l) {
                    ady.add(q.getDestino());
                }
            }
        }
        return ady.iterator();
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (!estaVertice(v)) {
            numVertices++;
            listVertices.add(new VerticeConLista<>(v, new ArrayList<>()));
        }
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        if (!estaArco(a)) {
            int pos = buscarPosicion(a.getDestino());
            if (pos >= numVertices) {
                insertarVertice(a.getDestino());
            }
            int pos2 = buscarPosicion(a.getOrigen());
            if (pos2 >= numVertices) {
                insertarVertice(a.getOrigen());
                pos2 = buscarPosicion(a.getOrigen());
            }
            List<VerticeAdyacente<E, T>> list = listVertices.get(pos2).getLista();
            int pos3 = buscarPosicionAdyacente(a.getDestino(), pos);
            if (pos3 < list.size()) {
                list.remove(pos3);
            }
            list.add(new VerticeAdyacente<>(a.getDestino(), a.getEtiqueta()));
        }
    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        int pos = buscarPosicion(v);
        if (pos < numVertices) {
            listVertices.remove(pos);
            numVertices--;
        }
        //Falta eliminar los arcos destino v
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        int pos = buscarPosicion(a.getOrigen());
        List<VerticeAdyacente<E,T>> list = listVertices.get(pos).getLista();
        int pos2 = buscarPosicionAdyacente(a.getDestino(), pos);
        if (pos2 < list.size() && list.get(pos2).getEtiqueta().equals(a.getEtiqueta())) {
            list.remove(pos2);
        }   
    }
    
}
