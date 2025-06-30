/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad5;


public class EnlazadoArbolGeneral<E> implements ArbolGeneral<E> {

    private NodoGeneral<E> nodoRaiz;

    public EnlazadoArbolGeneral() {
        nodoRaiz = null;
    }

    public EnlazadoArbolGeneral(E elemento, ArbolGeneral<E>... hijos) {
        nodoRaiz = new NodoGeneral<>(null, null, null);
        setRaiz(elemento);
        for (ArbolGeneral<E> hijo : hijos) {
            setHijo(hijo);
        }
    }

    private EnlazadoArbolGeneral(NodoGeneral<E> nodo) {
        nodoRaiz = nodo;
    }

    private NodoGeneral<E> arbolANodo(ArbolGeneral<E> a) {
        return ((EnlazadoArbolGeneral) a).nodoRaiz;
    }

    @Override
    public boolean esVacio() {
       return  nodoRaiz == null;
    }

    @Override
    public E raiz() throws ArbolVacioException {
        if (esVacio()) {
            throw new ArbolVacioException();
        }
        return nodoRaiz.getElemento();
    }

    @Override
    public ArbolGeneral<E> hijoMasIzq() throws ArbolVacioException {
        if (esVacio()) {
            throw new ArbolVacioException();
        }
        return new EnlazadoArbolGeneral<>(nodoRaiz.getHijo());
    }

    @Override
    public ArbolGeneral<E> hermanoDer() throws ArbolVacioException {
        if (esVacio()) {
            throw new ArbolVacioException();
        }
        return new EnlazadoArbolGeneral(nodoRaiz.getHer());
    }

    @Override
    public boolean esta(E elemento) {
        if (esVacio()) {
            return false;
        }
        if (raiz().equals(elemento)) {
            return true;
        }
        return hijoMasIzq().esta(elemento) || hermanoDer().esta(elemento);
    }

    @Override
    public void setRaiz(E elemRaiz) throws ArbolVacioException {
        if (esVacio()) {
            throw new ArbolVacioException();
        }
        nodoRaiz.setElemento(elemRaiz);
    }

    @Override
    public void setHijo(ArbolGeneral<E> hijo) throws ArbolVacioException, NullPointerException {
        if (esVacio()) {
            throw new ArbolVacioException();
        }
        if(hijo == null)
            throw new NullPointerException();
        if(hijoMasIzq().esVacio()){
            nodoRaiz.setHijo(arbolANodo(hijo));
        }else{
            NodoGeneral<E> hijoUltimo = nodoRaiz.getHijo();
            while (hijoUltimo.getHer() != null) {
                hijoUltimo = hijoUltimo.getHer();
            }
            hijoUltimo.setHermano(arbolANodo(hijo));
        }
    }

    @Override
    public void suprimir() {
        nodoRaiz = null;
    }

}
