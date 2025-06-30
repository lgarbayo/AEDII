package Especificion_Grafos;

import Interfaces.Arco;
import Interfaces.Grafo;
import Interfaces.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MatrizDeAdyacencia<E, T> implements Grafo<E, T> {

    private Vertice<E>[] vertices;
    private T[][] matAdy; //se almacena etiqueta de tipo T, entonces matriz es de tipo T
    private int numVertices;
    
    /*
    vertices             matAdy     0   1   2   3
    0 C                     0      --- --- --- ---
    1 LU                    1      98  --- --- ---
    2 OU                    2      173 95  --- 102
    3 PO                    3      121 148 --- ---
    
    P.ej. esto significa que entre Lugo y Coruña hay un arco con etiqueta 98, [1,0], [origen, destino]
    */

    public MatrizDeAdyacencia() {
        vertices = new Vertice[20]; //Capacidad maxima de 20
        matAdy = (T[][]) new Object[20][20];
        numVertices = 0;
    }

    private int buscarVertice(Vertice<E> v) {
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean estaVertice(Vertice<E> v) {
        return buscarVertice(v) != -1;
    }

    @Override
    public boolean estaArco(Arco<E, T> a) {
        int posOrg = buscarVertice(a.getOrigen());
        int posDes = buscarVertice(a.getDestino());
        if (posOrg == -1 || posDes == -1) {
            return false;
        }
        return a.getEtiqueta().equals(matAdy[posOrg][posDes]);
    }

    @Override
    public Iterator<Vertice<E>> vertices() {
        List<Vertice<E>> list = new ArrayList<>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            list.add(vertices[i]);
        }
        return list.iterator();
    }

    @Override
    public Iterator<Arco<E, T>> arcos() {
        T tag;
        List<Arco<E, T>> arcos = new ArrayList();
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                tag = matAdy[i][j];
                if (tag != null) {
                    arcos.add(new Arco(vertices[i], vertices[j], tag));
                }
            }
        }
        return arcos.iterator();
    }

    @Override
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v) {
        /*int pos = buscarVertice(v);
        if (pos == -1) {
            return null;
        }
        List<Vertice<E>> ady = new ArrayList<>(); //lo q devolvemos de lista
        for (int i = 0; i < numVertices; i++) {
            if (matAdy[pos][i] != null) {
                ady.add(vertices[i]);
            }
        }
        return ady.iterator();*/
        List<Vertice<E>> vertAdys = new ArrayList<>();
        if (estaVertice(v)) {
            int ivo = posicionVertice(v);
            for (int ivd = 0; ivd < numVertices; ivd++) {
                if (matAdy[ivo][ivd] != null)
                    vertAdys.add(vertices[ivd]);
            }
        }
        return vertAdys.iterator();
    }
    
    private int posicionVertice (Vertice<E> v) {
        for (int i = 0; i < numVertices; i++) {
            if(vertices[i].equals(v)) return i;
        }
        return -1;
    }

    @Override
    public void insertarVertice(Vertice<E> v) {
        if (v == null) {
            throw new NullPointerException();
        }
        if (numVertices == vertices.length) {
            throw new RuntimeException("No hay mas espacio en el grafo");
        }
        if (buscarVertice(v) == -1) {
            vertices[numVertices] = v;
            numVertices++;
        }
    }

    @Override
    public void insertarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        if (!estaArco(a)) {
            int i = buscarVertice(a.getOrigen());
            if (i == -1) {
                insertarVertice(a.getOrigen());
                i = buscarVertice(a.getOrigen());
            }
            int j = buscarVertice(a.getDestino());
            if (j == -1) {
                insertarVertice(a.getDestino());
                j = buscarVertice(a.getDestino());
            }
            matAdy[i][j] = a.getEtiqueta();
        }
    }

    @Override
    public void eliminarVertice(Vertice<E> v) {
        int pos = buscarVertice(v);
        if (pos != -1) {
            for (int i = pos; i < numVertices - 1; i++) {
                vertices[i] = vertices[i + 1];
                matAdy[i] = matAdy[i + 1]; //Elimina la fila de ese vertice
                for (int j = 0; j < numVertices; j++) { //Elimina la columna del vertice
                    matAdy[j][i] = matAdy[j][i + 1];
                }
            }
            numVertices--;
        }
    }

    @Override
    public void eliminarArco(Arco<E, T> a) {
        if (a == null || a.getEtiqueta() == null || a.getOrigen() == null || a.getDestino() == null) {
            throw new NullPointerException();
        }
        int i = buscarVertice(a.getOrigen());
        int j = buscarVertice(a.getDestino());
        if (i != -1 && j != -1) {
            if (a.getEtiqueta().equals(matAdy[i][j])) {
                matAdy[i][j] = null;
            }
        }
    }
}
