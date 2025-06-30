/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad5;

public interface ArbolGeneral<E> {

    public boolean esVacio();

    public E raiz() throws ArbolVacioException;

    public ArbolGeneral<E> hijoMasIzq() throws ArbolVacioException;

    public ArbolGeneral<E> hermanoDer() throws ArbolVacioException;

    public boolean esta(E elemento);

    public void setRaiz(E elemRaiz) throws ArbolVacioException;

    public void setHijo(ArbolGeneral<E> hijo) throws ArbolVacioException, NullPointerException;

    public void suprimir();
}

