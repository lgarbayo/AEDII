package Actividad_6_7;

import Interfaces.Map;
import Interfaces.Polinomio;
import java.util.Iterator;

public class MapPolinomio implements Polinomio {

    private Map<Integer, Double> mapa;

    //Voy a suponer que un termino no puede tener coeficiente 0 por facilidad
    public MapPolinomio() {
        mapa = new HashMap<>();
    }

    @Override
    public int grado() {
        int max = 0;
        Iterator<Integer> it = mapa.getClaves();
        int valorActual;
        while (it.hasNext()) {
            valorActual = it.next();
            if (valorActual > max) {
                max = valorActual;
            }
        }
        return max;
    }

    @Override
    public double getCoeficiente(int exponente) throws IllegalArgumentException {
        if (exponente < 0) {
            throw new IllegalArgumentException();
        }
        Double coeficiente = mapa.get(exponente);
        //Si el coeficiente es null (es decir, no existe el exponente en el polinomio, devuelve 0)
        return (coeficiente == null) ? 0 : coeficiente;
    }

    @Override
    public void añadirTermino(int exponente, double coeficiente) throws IllegalArgumentException {
        if (exponente < 0 || coeficiente == 0) {
            throw new IllegalArgumentException();
        }
        //El valor del termino sera el coeficiente introducido mas el coeficiente actual, si existe
        double valor = getCoeficiente(exponente) + coeficiente;
            mapa.insertar(exponente, valor);
        if (valor == 0) { //Si el coeficiente es 0 lo elimino
            mapa.eliminar(exponente);
        } 
    }

    @Override
    public void eliminarTermino(int exponente) throws IllegalArgumentException {
        if (exponente < 0) {
            throw new IllegalArgumentException();
        }
        mapa.eliminar(exponente);
    }

    @Override
    public Polinomio suma(Polinomio p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException();
        }
        Polinomio suma = new MapPolinomio();
        double coeficiente;
        //Comenzando en el maximo exponente del polinomio, inserto el termino de exponente i y decremento el exponente
        for (int exponente = this.grado(); exponente >= 0; exponente--) {
            coeficiente = this.getCoeficiente(exponente);
            if (coeficiente != 0) {
                suma.añadirTermino(exponente, coeficiente);
            }
        }
        for (int exponente = p.grado(); exponente >= 0; exponente--) {
            coeficiente = p.getCoeficiente(exponente);
            if (coeficiente != 0) {
                suma.añadirTermino(exponente, coeficiente);
            }
        }
        return suma;
    }

    @Override
    public Polinomio derivada() {
        double coeficiente;
        Polinomio derivada = new MapPolinomio();
        for (int exponente = grado(); exponente >= 1; exponente--) {
            coeficiente = getCoeficiente(exponente);
            if (coeficiente != 0) {
                derivada.añadirTermino(exponente - 1, coeficiente * exponente);
            }
        }
        return derivada;
    }

}
