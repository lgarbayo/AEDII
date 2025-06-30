/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package gal.uvigo.esei.aed2.solactividad11;

import static gal.uvigo.esei.aed2.solactividad11.SolucionSudoku.resolver;

/**
 *
 * @author vincenzogagliano
 */
public class SolActividad11 {

    public static final int DIMENSION = 9;

    public static void main(String[] args) {

        int[][] tablero = new int[][]{
            {0, 7, 0, 0, 0, 0, 0, 8, 0},
            {0, 5, 0, 6, 0, 0, 0, 0, 1},
            {0, 0, 3, 1, 4, 0, 0, 0, 0},
            {9, 0, 6, 0, 5, 0, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 5, 0, 2, 0, 1, 0, 7},
            {0, 0, 0, 0, 6, 5, 7, 0, 0},
            {3, 0, 0, 0, 0, 1, 9, 2, 0},
            {0, 4, 0, 0, 0, 0, 0, 1, 0},};

        imprimir(tablero);

        if (!resolver(tablero)) {
            System.out.println("El Sudoku no tiene solución");
        } else {
            System.out.println("Encontrada solución:");
            imprimir(tablero);
        }
    }

    private static void imprimir(int[][] tablero) {
        for (int i = 0; i < DIMENSION; i++) {
            if (i % 3 == 0) {
                System.out.println();
            }
            for (int j = 0; j < DIMENSION; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }
                System.out.print(tablero[i][j]);
            }
            System.out.println();
        }
    }
}
