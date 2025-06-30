/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solactividad3;

//public interface Heap<E extends Comparable<E>> {
//
//    public boolean esVacio();
//
//    public E recuperarMax() throws HeapVacioExcepcion;
//
//    public E suprimirMax() throws HeapVacioExcepcion;
//
//    public void insertar(E e) throws NullPointerException;
//
//    public void anular();
//
//    public void introducir(E e) throws NullPointerException;
//    
//    public void arreglarHeap();
//}

/**
 *
 * @author Luis
 */
public class HeapBinario<E extends Comparable<E>> implements Heap<E> {

    private static final int CAPACIDAD = 50;
    private E[] array; // el array del montículo
    private int numElem; // número de elementos del montículo
    
    //hi=pos*2
    //hd=pos*2 +1
    //padre=pos/2
    
    public HeapBinario() //CONSTRUCTOR VACIO
    {
        this(CAPACIDAD);
    }
    
    public HeapBinario(int capacidad) //CONSTRUCTOR
    {
        if (capacidad < 0)
            throw new IllegalArgumentException("HeapBinario: la capacidad no puede ser negativa");
        array = (E[]) new Comparable[capacidad+1];    //no se puede crear arrays de genéricos, casting de E pq lo de la izquierda es un array de E
        numElem = 0;
    }
    
    public boolean esVacio()
    {
        return numElem == 0;
    }
    
    public E recuperarMax() throws HeapVacioExcepcion
    {
        if (esVacio())
            throw new HeapVacioExcepcion("recuperarMax: Heap Vacío");
        return array[1]; //se guarda en la posicion 1
    }
    
    public E suprimirMax() throws HeapVacioExcepcion 
    //siempre raiz, se deja hueco en raiz
    // buscar elemento mayor iremos empujandolo hasta la raiz
    {
        if (esVacio())
            throw new HeapVacioExcepcion("suprimirMax: Heap Vacio");
        E e = array[1]; //nos ponemos en la raiz
        array[1] = array[numElem];
        array[numElem] = null;
        //con esto hemos borrado la raiz
        numElem--;
        hundir(1);
        return e;
    }
    
    private void hundir(int hueco)
    {
        int hijo = hueco * 2; //por def el hijo es asi
        E temp = array[hueco];
        boolean fin = false;
        
        while (hijo <= numElem && !fin) //siempre q se pueda
        {
            if (hijo < numElem && array[hijo+1].compareTo(array[hijo])>0)
                hijo++;
            
            if (array[hijo].compareTo(temp) > 0)
            {
                array[hueco] = array[hijo];
                hueco = hijo;
                hijo = hueco * 2;
            }
            else
                fin = true;
        }
        
        array[hueco] = temp;
    }
//        E hI = array[2 * hueco];
//        E hD = array[(2 * hueco) + 1];
//        if (hI == null) 
//        {
//        } 
//        else if(hD == null)
//        {
//        }
//        else
//        {
//        }

    public void insertar(E e) throws NullPointerException
    {
        if (e == null)
            throw new NullPointerException("insertar: no se permiten valores null");

        if (numElem == array.length-1)
            duplicarVector(); //si el array esta lleno ya se añade un nuevo vector
        
        int hueco = ++numElem; //primer paso, se crea un hueco en la sig pos disponible del arbol binario
        while ((hueco) > 1 && array[hueco/2].compareTo(e) < 0)
        {
            array[hueco] = array[hueco/2]; //huevo/2 es el padre
            hueco /= 2;
        }
        array[hueco] = e; //si se puede colocar se añade y termina la insercion
    }
    
    private void duplicarVector()
    {
        E[] nuevoVector = (E[]) new Comparable[array.length*2];
        
        //copiar elementos del array viejo -> se puede hacer con for
        System.arraycopy(array, 0, nuevoVector, 0, array.length);
        
        array = nuevoVector;
    }

    public void anular()
    {
        for (int i = 1; i <= numElem; i++)
            array[i] = null;
        numElem = 0;
    }
    
    public void introducir(E e) throws NullPointerException
    {
        if (e == null)
            throw new NullPointerException("introducir: no se permiten valores null");

        if (numElem == array.length-1)
            duplicarVector();
        
        array[++numElem] = e;
    }    
    
    public void arreglarHeap()
    {
        for (int i = numElem/2; i > 0; i--)
            hundir(i);
    }
}
