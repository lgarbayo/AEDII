/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aedi.solactividad2;

/**
 *
 * @author lgfernandez23_esei.u
 */
public class NodoBinario<E> {
    private E elemento; // referencia al elemento del nodo
    private NodoBinario<E> izq; // referencia al nodo izquierdo
    private NodoBinario<E> der; // referencia al nodo derecho

    public NodoBinario(E e, NodoBinario<E> hi, NodoBinario<E> hd) {
        elemento = e;
        izq = hi;
        der = hd;
    }

    public E getElemento() {
        return elemento;
    }

    public NodoBinario<E> getIzq() {
        return izq;
    }

    public NodoBinario<E> getDer() {
        return der;
    }

    public void setElemento(E e) {
        elemento = e;
    }

    public void setIzq(NodoBinario<E> hi) {
        izq = hi;
    }

    public void setDer(NodoBinario<E> hd) {
        der = hd;
    }
}
