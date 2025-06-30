package Interfaces;

public interface Polinomio {

    /**
     * Produce: Devuelve el grado del polinomio, es decir, el mayor exponente de
     * un término con coeficiente distinto de cero. Devuelve 0 si es el
     * polinomio cero.
     *
     * @return
     */
    public int grado();

    /**
     * Produce: si el exponente es negativo lanza la excepción
     * IllegalArgumentException; en otro caso, devuelve el coeficiente del
     * término con el exponente que se pasa como parámetro. Si el término no
     * existe, devuelve 0
     *
     * @param exponente
     * @return
     * @throws IllegalArgumentException
     */
    public double getCoeficiente(int exponente) throws IllegalArgumentException;

    /**
     * Modifica: this Produce: si el exponente es negativo o el coeficiente es 0
     * lanza la excepción IllegalArgumentException; en otro caso, añade el
     * termino (coeficiente x exponente) a this
     *
     * @param exponente
     * @param coeficiente
     * @throws IllegalArgumentException
     */
    public void añadirTermino(int exponente, double coeficiente) throws IllegalArgumentException;

    /**
     * Modifica: this Produce: si el exponente es negativo lanza la excepción
     * IllegalArgumentException en otro caso, elimina el término con el
     * exponente que se pasa como parámetro
     *
     * @param exponente
     * @throws IllegalArgumentException
     */
    public void eliminarTermino(int exponente) throws IllegalArgumentException;

    /**
     * Produce: si p == null lanza NullPointerException; en otro caso, crea un
     * nuevo polinomio que es la suma de los polinomios p y this
     *
     * @param p
     * @return
     * @throws NullPointerException
     */
    public Polinomio suma(Polinomio p) throws NullPointerException;

    /**
     * Produce: Dado el polinomio this de la forma anxn + an-1xn-1 + …+ a1x1 +
     * a0x0 devuelve su derivada: (n*an) xn-1 + (n-1 * an-1) xn-2 + …+ a1
     *
     * @return
     */
    public Polinomio derivada();
}
