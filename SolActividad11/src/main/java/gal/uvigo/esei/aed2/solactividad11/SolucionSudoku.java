/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad11;

/**
 *
 * @author vincenzogagliano
 */
public class SolucionSudoku {

    public static final int DIMENSION = 9;

    private static boolean esPosibleInsertar(int[][] tablero, int i, int j, int valor) {
        for (int a = 0; a < DIMENSION; a++) { //Comprueba columna
            if (a != i && tablero[a][j] == valor) {
                return false;
            }
        }
        for (int a = 0; a < DIMENSION; a++) { //Comprueba fila
            if (a != j && tablero[i][a] == valor) {
                return false;
            }
        }
//Comprueba cuadrado
        int y = (i / 3) * 3; // coloco en la primera fila del cuadrado a comprobar
        int x = (j / 3) * 3; // coloco en la primera columna del cuadrado a comprobar
        for (int a = 0; a < 3; a++) { //cuadrado tiene 3 filas
            for (int b = 0; b < 3; b++) { //cuadrado tiene 3 columnas
                if (a != i && b != j && tablero[y + a][x + b] == valor) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean resolver(int[][] tablero) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (tablero[i][j] == 0) { //aplicar esquema apartir de aqui
                    boolean objetivo = false;
                    for (int k = 1; k <= 9 && !objetivo; k++) {
                        if (esPosibleInsertar(tablero, i, j, k)) {
                            tablero[i][j] = k;//ANOTAR EL NUEVO PASO
                            if (i == 8 && j == 8) {
                                objetivo = true;
                            } else {
                                objetivo = resolver(tablero);
                                if (!objetivo) {
                                    tablero[i][j] = 0;
                                }
                            }
                        }//end if
                    }//end for
                    return objetivo;
                }
            }
        }
        return true;
    }

    public static boolean resolver2(int[][] tablero) {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                if (tablero[i][j] == 0) {
                    boolean objetivo = false;
                    for (int k = 0; k <= 9; k++) {
                        if (esPosibleInsertar(tablero, i, j, k)) {
                            tablero[i][j]=k;
                            if(i==8 && j== 8){
                                objetivo=true;
                            }else{
                                objetivo=resolver2(tablero);
                                if(!objetivo){
                                    tablero[i][j]=0;
                                }
                            }
                        }
                    }
                    return objetivo;
                }
            }

        }
        return true;
    }
    
    

}
