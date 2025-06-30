/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad12;

import java.util.ArrayList;

/**
 *
 * @author vincenzogagliano
 */
public class DivideVenceras {

    public static int seleccionRapida(int[] array, int k_menor, int i, int j) {
        int indicePivote = buscaPivote(array, i, j);
        if (indicePivote != -1) {
            int pivote = array[indicePivote];
            intercambiar(array, indicePivote, j);
            int k = particion(array, i, j, pivote);
            if (k_menor <= k) {
                return seleccionRapida(array, k_menor, i, k - 1);

            } else {
                if (k_menor == k + 1) {
                    return pivote;
                } else {
                    seleccionRapida(array, k_menor, k, j);

                }

            }

        } else {
            return array[i];
        }
        return 0;

    }

    public static int buscarPosK(int[] v, int inicio, int fin) {
        int medio = 0;
        if (inicio >= fin) {
            return -1;
        }

        while (inicio < fin) {
            medio = (inicio + fin) / 2;
            if (v[medio - 1] > v[medio] && v[medio + 1] > v[medio]) {
                return v[medio];
            } else if (v[medio] > v[medio - 1]) {
                inicio = medio + 1;
            } else {
                fin = medio - 1;
            }
        }
        return -1;
    }

    public static int inversiones(int[] array, int inicio, int fin) {
        if (inicio < fin) {
            int medio = (inicio + fin) / 2;
            return inversiones(array, inicio, medio)
                    + inversiones(array, medio + 1, fin)
                    + mergeCount(array, inicio, medio, medio + 1, fin);
        }
        return 0;
    }

    private static int buscaPivote(int[] aux, int inicio, int fin) {
        int primer = aux[inicio];
        int k = inicio + 1;
        while (k <= fin) {
            if (aux[k] > primer) {
                return k;
            } else if (aux[k] < primer) {
                return inicio;
            } else {
                k++;
            }
        }
        return -1;
    }

    private static int particion(int[] aux, int inicio, int fin, int pivote) {
        int derecha = inicio;
        int izquierda = fin - 1;
        while (derecha <= izquierda) {
            while (aux[derecha] < pivote) {
                derecha++;
            }
            while (aux[izquierda] >= pivote) {
                izquierda--;
            }
            if (derecha < izquierda) {
                intercambiar(aux, derecha, izquierda);
            }
        }

        return derecha;
    }

    private static void intercambiar(int[] aux, int i, int j) {
        int temp = aux[i];
        aux[i] = aux[j];
        aux[j] = temp;
    }

    private static int mergeCount(int[] aux, int inicio1, int fin1, int inicio2, int fin2) {
        int i = inicio1;
        int j = inicio2;
        int cont = 0;
        int pos = inicio2 - inicio1; //número máximo de inversiones
        ArrayList<Integer> temp = new ArrayList<>();
        while (i <= fin1 && j <= fin2) {
            if (aux[i] <= aux[j]) {
                temp.add(aux[i++]);
                pos--;
            } else {
                temp.add(aux[j++]);
                cont += pos;
            }
        }
        while (i <= fin1) {
            temp.add(aux[i++]);
        }
        while (j <= fin2) {
            temp.add(aux[j++]);
        }
        for (i = inicio1; i <= fin2; i++) {
            aux[i] = temp.remove(0);
        }
        return cont;
    }

}
