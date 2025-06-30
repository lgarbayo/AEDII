package Especificion_Grafos;

import Actividad_6_7.HashMap;
import Interfaces.Arco;
import Interfaces.Grafo;
import Interfaces.Map;
import Interfaces.Vertice;
import Interfaces.VerticeConMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapAdyacencia<E, T> implements Grafo<E, T> {

    private List<VerticeConMap<E, T>> listVertices; //lista en donde en cada objeto se guarda el vertice y el mapa

    public MapAdyacencia() {
        listVertices = new ArrayList<>();
    }

    /**
     * Busca la posicion del vertice en listVertice
     *
     * @param v
     * @return
     */
    private int buscarPosicion(Vertice<E> v) {
        Iterator<VerticeConMap<E, T>> it = listVertices.iterator();
        int pos = 0;
        while (it.hasNext() && !it.next().getVertice().equals(v)) {
            pos++;
        }
        return pos;
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        return buscarPosicion(v) < listVertices.size();
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        int pos = buscarPosicion(a.getOrigen());
        if (pos < listVertices.size()) {
            Map<Vertice<E>, T> map = listVertices.get(pos).getAdy();
            if (map != null && map.get(a.getDestino()) != null) {
                return map.get(a.getDestino()).equals(a.getEtiqueta());
            }
        }
        return false;
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        List<Vertice<E>> list = new ArrayList<>();
        for (VerticeConMap<E, T> v : listVertices) {
            list.add(v.getVertice());
        }
        return list.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        List<Arco<E, T>> arcos = new ArrayList<>();
        for (VerticeConMap<E, T> origen : listVertices) {
            Iterator<Vertice<E>> it = origen.getAdy().getClaves();
            while (it.hasNext()) {
                Vertice<E> destino = it.next();
                arcos.add(new Arco<>(origen.getVertice(), destino, origen.getAdy().get(destino)));
            }
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        int pos = buscarPosicion(v);
        if (pos >= listVertices.size()) {
            return null;
        }
        return listVertices.get(pos).getAdy().getClaves();

    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        int pos = buscarPosicion(v);

        if (pos >= listVertices.size()) {
            listVertices.add(new VerticeConMap<>(v));
        }
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        if (!estaArco(a)) {
            if (buscarPosicion(a.getOrigen()) >= listVertices.size()) {
                listVertices.add(new VerticeConMap<>(a.getOrigen()));
            }

            if (buscarPosicion(a.getDestino()) >= listVertices.size()) {
                listVertices.add(new VerticeConMap<>(a.getDestino()));
            }
            listVertices.get(buscarPosicion(a.getOrigen())).getAdy().insertar(a.getDestino(), a.getEtiqueta());
        }
    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        int pos = buscarPosicion(v);
        if (pos < listVertices.size()) {
            listVertices.remove(pos);
        }
        for (VerticeConMap<E, T> vAdy : listVertices) {
            vAdy.getAdy().eliminar(v);
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        if (estaArco(a)) {
            listVertices.get(buscarPosicion(a.getOrigen())).getAdy().eliminar(a.getDestino());
        }
    }
}
