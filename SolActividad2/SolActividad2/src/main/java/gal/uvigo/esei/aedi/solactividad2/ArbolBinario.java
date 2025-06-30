/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gal.uvigo.esei.aedi.solactividad2;

/**
 *
 * @author lgfernandez23_esei.u
 */
public interface ArbolBinario<E> {

    public boolean esVacio();

    public E raiz() throws ArbolVacioExcepcion;

    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion;

    public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion;

    public boolean esta(E elemento);

    public void setRaiz(E elemRaiz)
            throws ArbolVacioExcepcion, NullPointerException;

    public void setHijoIzq(ArbolBinario<E> hi)
            throws ArbolVacioExcepcion, NullPointerException;

    public void setHijoDer(ArbolBinario<E> hd)
            throws ArbolVacioExcepcion, NullPointerException;

    public void suprimir();
}
