/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aedi.solactividad2;

/**
 *
 * @author lgfernandez23_esei.u
 */
public class EnlazadoArbolBinario<E> implements ArbolBinario<E> {

    private NodoBinario<E> r; //r raiz, elemento del nodo binario
    
    public EnlazadoArbolBinario() {
        r = null;
    }
    
    public EnlazadoArbolBinario(E elemento, ArbolBinario<E> hi, ArbolBinario<E> hd) {
        if (elemento == null ||hi == null || hd == null)
            throw new NullPointerException();
        
        r = new NodoBinario<>(elemento, ((EnlazadoArbolBinario)hi).r, ((EnlazadoArbolBinario)hd).r);
    }
    
    public EnlazadoArbolBinario(NodoBinario<E> raiz) {
        r = raiz;
    }
    
    public boolean esVacio() {
        return r == null;
    }
    
    public E raiz() throws ArbolVacioExcepcion {
        if (esVacio())
            throw new ArbolVacioExcepcion("raiz arbol vacio");
        return r.getElemento();
    }
    
    public ArbolBinario<E> hijoIzq() throws ArbolVacioExcepcion {
        if (esVacio())
            throw new ArbolVacioExcepcion("hijoIzq arbol vacio");
        return new EnlazadoArbolBinario(r.getIzq());
    }
    
    public ArbolBinario<E> hijoDer() throws ArbolVacioExcepcion {
        if (esVacio())
            throw new ArbolVacioExcepcion("hijoDer arbol vacio");
        return new EnlazadoArbolBinario(r.getDer());
    }
    
    public boolean esta (E e) {
        if (esVacio()) 
            return false;
        if (r.getElemento().equals(e))
            return true;
        return hijoIzq().esta(e) || hijoDer().esta(e);
    }
    
    public void setRaiz(E elemento) throws ArbolVacioExcepcion, NullPointerException {
        if (esVacio())
            throw new ArbolVacioExcepcion("setRaiz arbol vacio");
        if (elemento == null) 
            throw new NullPointerException();
        r.setElemento(elemento);
    }
    
    public void setHijoIzq(ArbolBinario<E> hi) {
        if (esVacio())
            throw new ArbolVacioExcepcion("setHijoIzq arbol vacio");
        if (hi == null) 
            throw new NullPointerException();
        r.setIzq(((EnlazadoArbolBinario) hi).r);
    }
    
    public void setHijoDer(ArbolBinario<E> hd) {
        if (esVacio())
            throw new ArbolVacioExcepcion("setHijoDer arbol vacio");
        if (hd == null) 
            throw new NullPointerException();
        r.setDer(((EnlazadoArbolBinario) hd).r);
    }
    
    public void suprimir() {
        r = null;
    }
}
