/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.solactividad3;

/**
 *
 * @author Luis
 */
public interface Heap<E extends Comparable<E>> {

    public boolean esVacio();

    public E recuperarMax() throws HeapVacioExcepcion;

    public E suprimirMax() throws HeapVacioExcepcion;

    public void insertar(E e) throws NullPointerException;

    public void anular();

    public void introducir(E e) throws NullPointerException;
    
    public void arreglarHeap();
}