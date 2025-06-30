package Interfaces;

import java.util.Iterator;

public interface Grafo<E, T> {
// Declaración de tipos: Vertice<E>, Arco<E,T>
// Características: Es un conjunto de vértices y un conjunto de arcos. Los objetos son modificables.

    // public Grafo() 
    /**
     * Produce: cierto si el vértice v está en this, falso en caso contrario
     *
     * @param v
     * @return
     */
    public boolean estaVertice(Vertice<E> v);

    /**
     * Produce: cierto si el arco a está en this, falso en caso contrario
     *
     * @param a
     * @return
     */
    public boolean estaArco(Arco<E, T> a);

    /**
     * Produce: devuelve un iterador sobre los vértices del grafo
     *
     * @return
     */
    public Iterator<Vertice<E>> vertices();

    /**
     * Produce: devuelve un iterador sobre los arcos del grafo
     *
     * @return
     */
    public Iterator<Arco<E, T>> arcos();

    /**
     * Produce: devuelve un iterador sobre los vértices del grafo adyacentes al
     * vértice v
     *
     * @param v
     * @return
     */
    public Iterator<Vertice<E>> adyacentes(Vertice<E> v);

    /**
     * Modifica: this Produce: inserta el vértice v en this
     *
     * @param v
     */
    public void insertarVertice(Vertice<E> v);

    /**
     * Modifica: this Produce: inserta el arco a en this
     *
     * @param a
     */
    public void insertarArco(Arco<E, T> a);

    /**
     * Modifica: this Produce: elimina el vértice v de this y todos los arcos
     * que salen y llegan a v
     *
     * @param v
     */
    public void eliminarVertice(Vertice<E> v);

    /**
     * Modifica: this Produce: elimina el arco a de this
     *
     * @param a
     */
    public void eliminarArco(Arco<E, T> a);

}
