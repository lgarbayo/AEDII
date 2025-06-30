package Especificion_Grafos;

import Actividad_6_7.HashMap;
import Interfaces.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapDeMap<E, T> implements Grafo<E, T> {

    private Map<Vertice<E>, Map<Vertice<E>, T>> mapaVertices;

    public MapDeMap() {
        mapaVertices = new HashMap<>();
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        Iterator<Vertice<E>> it = mapaVertices.getClaves();
        while (it.hasNext()) {
            if (it.next().equals(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        if (a == null) {
            throw new NullPointerException();
        }
        Map<Vertice<E>, T> mapaArcos = mapaVertices.get(a.getOrigen());
        if (mapaArcos == null) {
            //El origen no esta en el mapa
            return false;
        }
        //Compara la etiqueta con el valor asociado al vertice destino en el mapa de los arcos
        //Si el destino no esta, se compara con null y retorna false
        return a.getEtiqueta().equals(mapaArcos.get(a.getDestino()));
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        return mapaVertices.getClaves();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        List<Arco<E, T>> arcos = new ArrayList<>();
        Vertice<E> destino;
        Vertice<E> origen;
        Iterator<Vertice<E>> itOrigen = mapaVertices.getClaves();
        Iterator<Vertice<E>> itDestino;
        while (itOrigen.hasNext()) {
            origen = itOrigen.next();
            itDestino = mapaVertices.get(origen).getClaves();
            while (itDestino.hasNext()) {
                destino = itDestino.next();
                arcos.add(new Arco(origen, destino, mapaVertices.get(origen).get(destino)));
            }
        }
        return arcos.iterator();
    }

    @Override
    /**
     * Produce: devuelve un iterador sobre los vértices del grafo adyacentes al
     * vértice v Si el vertice v no pertenece al grafo lanza una
     * IllegalArgumentException
     */
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        Map<Vertice<E>, T> map = mapaVertices.get(v);
        if (map == null) {
            throw new IllegalArgumentException();
        } else {
            return map.getClaves();
        }
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (!estaVertice(v)) {
            mapaVertices.insertar(v, new HashMap<>());
        }
    }

    //Al tener que en el hashMap no se puede insertar un valor null, supongo que la etiqueta de un arco no puede ser null
    @Override
    public void insertarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        //Si ya existe algun arco entre los vertices se sobreescribe
        //se insertan por si no estan
        insertarVertice(a.getOrigen());
        insertarVertice(a.getDestino());
        mapaVertices.get(a.getOrigen()).insertar(a.getDestino(), a.getEtiqueta());

    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        mapaVertices.eliminar(v);
        Iterator<Vertice<E>> it = mapaVertices.getClaves();
        //De cada vertice se elimina el arco que vaya a v
        while (it.hasNext()) {
            mapaVertices.get(it.next()).eliminar(v);
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        //No hace falta comprobar si es nul nadal, lo hace estaArco
        if (estaArco(a)) {
            mapaVertices.get(a.getOrigen()).eliminar(a.getDestino());
        }
    }
}
