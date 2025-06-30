package com.mycompany.solactividad8;

import java.util.Iterator;

public interface Map<K, V> {

    /**
     * Interface de la clase Par, que sera usada para representar un par (Clave Valor)
     */
    public static interface Par<K,V> {

        public K getClave();

        public V getValor();

        public void setValor(V v);
    }

    /**
     *
     * @return Devuelve el numero de Pares (Clave Valor) del Mapa
     */
    public int tamaño();

    /**
     * Devuelve el valor asociado a la clave, o null si la clave no estaba en el mapa
     * @param clave
     * @return 
     */
    public V get(K clave);

    /**
     * Inserta el par (clave,valor al mapa).
     * Lo siguiente deberian explicarlo pero: Considero que no se puede insertar null como clave ni value y que si ya existe la clave se sobreescribe su valor
     * @param clave
     * @param valor 
     */
    public void insertar(K clave, V valor);

    /**
     * Elimina el par representado por la clave y devuelve el valor del par
     * @param clave
     * @return 
     */
    public V eliminar(K clave);

    /**
     * Devuelve un iterator sobre todas las claves del mapa
     * @return 
     */
    public Iterator<K> getClaves();

    /**
     * Devuelve un iterador sobre todos los valores contenidos en el mapa (voy a considerar que SI se pueden repetir, pero esto deberian especificarlo)
     * @return 
     */
    public Iterator<V> getValores();
}
