package com.mycompany.solactividad8;

import com.mycompany.solactividad8.Map;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMap<K, V> implements Map<K, V> {

    private List<Par<K, V>>[] array;
    private int capacidad;
    private int numElem;

    /**
     * Se crea un HashMap con capacidad por defecto
     */
    public HashMap() {
        this(10); //10 sera la capacidad por defecto
    }

    /**
     * Se crea un HashMap de la capacidad dada
     *
     * @param capacidad
     */
    public HashMap(int capacidad) {
        if (capacidad < 1) {
            throw new IllegalArgumentException();
        }
        this.capacidad = capacidad;
        numElem = 0;
        array = new List[capacidad];
        for (int i = 0; i < capacidad; i++) {
            array[i] = new ArrayList<>();
        }
    }

    /**
     * Funcion privada que dada una clave, retorna la posicion en el array de la
     * lista que debe contener a esa clave
     *
     * @param clave
     * @return
     */
    private int funcionHash(K clave) {
        int indice = clave.hashCode();
        indice = Math.abs(indice);
        return indice % capacidad;
    }

    @Override
    public int tamaño() {
        return numElem;
    }

    @Override
    public V get(K clave) {
        if (clave == null) {
            throw new NullPointerException();
        }
        int pos = funcionHash(clave);//Indice del array correspondiente a clave
        List<Par<K, V>> lista = array[pos];//lista que deberia contener a clave
        for (Par<K, V> par : lista) {//se recorren los pares de la lista en busqueda de clave
            if (par.getClave().equals(clave)) {
                return par.getValor();
            }
        }
        return null; //No esta en el Map

    }

    @Override
    public void insertar(K clave, V valor) {
        if (clave == null || valor == null) {
            throw new NullPointerException();
        }
        int indice = funcionHash(clave);
        List<Par<K, V>> lista = array[indice];
        Iterator<Par<K, V>> it = lista.iterator();
        int pos = 0;
        while (it.hasNext() && !it.next().getClave().equals(clave)) {
            pos++;
        }
        if (pos < lista.size()) {//La clave ya existia
            lista.get(pos).setValor(valor);
        } else { //La clave no existia
            lista.add(new Par<>(clave, valor));
        }
        numElem++;
    }

    @Override
    public V eliminar(K clave) {
        if (clave == null) {
            throw new NullPointerException();
        }
        V toRet = null;
        int indice = funcionHash(clave);
        int pos = 0;
        Iterator<Par<K, V>> it = array[indice].iterator();

        while (it.hasNext() && !it.next().getClave().equals(clave)) {
            pos++;
            //Buscamos el par de la clave en la lista correspondiente
        }
        if (pos < array[indice].size()) {//Si la posicion es valida
            //Eliminamos el par de la lista y devolvemossu valor
            toRet = array[indice].remove(pos).getValor();
            numElem--;
        }
        return toRet;
    }

    @Override
    public Iterator<K> getClaves() {
        List<K> listaClaves = new ArrayList<>(numElem);
        for (List<Par<K, V>> list : array) { //Recorro todas las listas del array
            for (Par<K, V> par : list) { //Recorro todos los pares de las listas
                listaClaves.add(par.getClave());
            }
        }
        return listaClaves.iterator();
    }

    @Override
    public Iterator<V> getValores() {
        List<V> listaValores = new ArrayList<>(numElem);
        for (List<Par<K, V>> list : array) { //Recorro todas las listas del array
            for (Par<K, V> par : list) { //Recorro todos los pares de las listas
                listaValores.add(par.getValor());
            }
        }
        return listaValores.iterator();
    }
    
    
    //Ejercicio de examen
    /**
     * Si la clave tiene asociado valorViejo, se cambia por valorNuevo. Si se cambia return true
     * @param clave
     * @param valorViejo
     * @param valorNuevo
     * @return 
     */
    private boolean modificarValor(K clave,V valorViejo,V valorNuevo){
        int indice = funcionHash(clave);
        for (Par<K, V> par : array[indice]) {
            if (par.getClave().equals(clave)) {
                if (par.getValor().equals(valorViejo)) {
                    par.setValor(valorNuevo);
                    return true;
                }
                return false;
            }
        }
        return false;
    
    }

    private class Par<K, V> implements Map.Par<K, V> {

        private K clave;
        private V valor;

        public Par(K clave, V valor) {
            this.clave = clave;
            this.valor = valor;
        }

        @Override
        public K getClave() {
            return clave;
        }

        @Override
        public V getValor() {
            return valor;
        }

        @Override
        public void setValor(V valor) {
            this.valor = valor;
        }

        public boolean equals(Par p) {
            return this.clave.equals(p.getClave()) && this.valor.equals(p.getValor());
        }

    }
}
