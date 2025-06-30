/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad5;

public class NodoGeneral<E> {

    private E elemento; // referencia al elemento del nodo
    private NodoGeneral<E> hijo; // referencia al nodo hijo mas a la izquierda
    private NodoGeneral<E> hermano; // referencia al nodo hermano derecha

    public NodoGeneral(E e, NodoGeneral<E> hi, NodoGeneral<E> her) {
        elemento = e;
        hijo = hi;
        hermano = her;
    }

    public E getElemento() {
        return elemento;
    }

    public NodoGeneral<E> getHijo() {
        return hijo;
    }

    public NodoGeneral<E> getHer() {
        return hermano;
    }

    public void setElemento(E e) {
        elemento = e;
    }

    public void setHijo(NodoGeneral<E> hi) {
        hijo = hi;
    }

    public void setHermano(NodoGeneral<E> her) {
        hermano = her;
    }
}
