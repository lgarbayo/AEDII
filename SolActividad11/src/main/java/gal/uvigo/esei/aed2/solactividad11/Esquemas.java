/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad11;

import java.util.Iterator;

import java.util.Map;

public class Esquemas {

    private static boolean buenSitio(int r, int col, int[] tabl) {
// ¿Es amenaza colocar la reina “r” en la columna “col”?
        int reiAnt = 0;
        boolean continuar = true;
        while (reiAnt < r && continuar) {
            if (tabl[reiAnt] == col) {
                continuar = false;
            } else if (Math.abs(reiAnt - r) == Math.abs(tabl[reiAnt] - col)) {
                continuar = false;
            }
            reiAnt++;
        }
        return continuar;
    }

    public static boolean colocarReinas(int reina, int[] tablero) {
        boolean objetivo = false;
        int columna = 0;
        while (columna < 8 && !objetivo) {
            if (buenSitio(reina, columna, tablero)) {
                tablero[reina] = columna;

                if (reina == 7) {
                    objetivo = true;
                } else {
                    objetivo = colocarReinas(reina + 1, tablero);
                    if (!objetivo) {
                        tablero[reina] = -1;
                    }
                }
            }
            columna++;

        }
        return objetivo;
    }

    public static boolean colocarReinas2(int reina, int[] tablero) {
        boolean objetivo = false;
        int columna = 0;

        while (columna < 8 && !objetivo) {
            if (buenSitio(reina, columna, tablero)) {
                tablero[reina] = columna;

                if (reina == 7) {
                    objetivo = true;
                } else {
                    objetivo = colocarReinas2(reina + 1, tablero);
                    if (!objetivo) {
                        tablero[reina] = -1;
                    }
                }
            }
            columna++;
        }
        return objetivo;

    }

    public static boolean ensayarPalabras(char[][] laberinto, int posicionX, int posicionY, String cadena, int posCad) {
        boolean objetivo = false;
        int n = laberinto.length;

        if (posicionX >= 0 && posicionX <= n - 1 && posicionY >= 0 && posicionY <= n - 1
                && laberinto[posicionX][posicionY] == cadena.charAt(posCad)) {
            laberinto[posicionX][posicionY] = ' ';

            if (posicionX == n - 1 && posicionY == n - 1) {
                objetivo = true;
            } else {
                int posAnt = posCad;
                posCad = (posCad == cadena.length() - 1) ? 0 : posCad + 1;
                objetivo = ensayarPalabras(laberinto, posicionX + 1, posicionY, cadena, posCad)
                        || ensayarPalabras(laberinto, posicionX, posicionY + 1, cadena, posCad)
                        || ensayarPalabras(laberinto, posicionX - 1, posicionY, cadena, posCad)
                        || ensayarPalabras(laberinto, posicionX, posicionY - 1, cadena, posCad);

                if (!objetivo) {
                    laberinto[posicionX][posicionY] = cadena.charAt(posAnt);
                }
            }
        }
        return objetivo;
    }

    public static boolean ensayarPalabras2(char[][] laberinto, int posicionX, int posicionY, String cadena, int posCad) {
        int n = laberinto.length;
        boolean objetivo = false;

        if (posicionX >= 0 && posicionX <= n - 1 && posicionY >= 0 && posicionY <= n - 1 && laberinto[posicionX][posicionY] == cadena.charAt(posCad)) {
            laberinto[posicionX][posicionY] = ' ';
            if (posicionX == n - 1 && posicionY == n - 1) {
                objetivo = true;
            } else {
                int posAnt = posCad;
                posCad = (posCad == cadena.length() - 1) ? 0 : posCad + 1;
                objetivo = ensayarPalabras2(laberinto, posicionX + 1, posicionY, cadena, posCad)
                        || ensayarPalabras2(laberinto, posicionX, posicionY + 1, cadena, posCad)
                        || ensayarPalabras2(laberinto, posicionX - 1, posicionY, cadena, posCad)
                        || ensayarPalabras2(laberinto, posicionX, posicionY - 1, cadena, posCad);

                if (!objetivo) {
                    laberinto[posicionX][posicionY] = cadena.charAt(posAnt);
                }
            }
        }
        return objetivo; 
    }

    public static boolean ensayar(char[][] laberinto, int posicionX, int posicionY) {
        boolean objetivo = false;
        int n = laberinto.length;

        if (posicionX >= 0 && posicionX <= n - 1 && posicionY >= 0 && posicionY <= n - 1
                && ((laberinto[posicionX][posicionY] == ' ') || (laberinto[posicionX][posicionY] == 'T'))) {
            laberinto[posicionX][posicionY] = 'C';

            if (laberinto[posicionX][posicionY] == 'T') {
                laberinto[posicionX][posicionY] = 'C';
                int posX = 0;
                int posY = 0;
                boolean encontrada = false;

                while (posX <= n - 1 && !encontrada) {
                    posY = 0;
                    while (posY <= n - 1 && !encontrada) {
                        if (laberinto[posicionX][posicionY] == 'T') {
                            encontrada = true;
                            posY++;
                        }
                    }
                    posX++;
                }
                posicionX = posX - 1;
                posicionY = posY - 1;
            }

            if (posicionX == n - 1 && posicionY == n - 1) {
                objetivo = true;

            } else {
                objetivo = ensayar(laberinto, posicionX + 1, posicionY)
                        || ensayar(laberinto, posicionX, posicionY + 1)
                        || ensayar(laberinto, posicionX - 1, posicionY)
                        || ensayar(laberinto, posicionX, posicionY - 1);

                if (!objetivo) {
                    laberinto[posicionX][posicionY] = 'I';
                }
            }

        }
        return objetivo;
    }
    
    
    
    public static boolean ensayar2(char[][] laberinto, int posicionX, int posicionY){
        boolean objetivo=false;
        int n=laberinto.length;
        
        if(posicionX >=0 && posicionX <= n-1 && posicionY >= 0 && posicionY <= n-1 && (laberinto[posicionX][posicionY]==' ' || laberinto[posicionX][posicionY]=='T')){
            laberinto[posicionX][posicionY]='C';
            
            if(laberinto[posicionX][posicionX]=='T'){
                laberinto[posicionX][posicionY]='C';
                int posX=0;
                int posY=0;
                boolean encontrada=false;
                
                while(posX <= n-1 && !encontrada){
                    posY=0;
                    while(posY <= n-1 && !encontrada){
                        if(laberinto[posicionX][posicionX]=='T'){
                            encontrada=true;
                            posY++;
                        }
                    }
                       posX++; 
                }
                posicionX=posX-1;
                posicionY=posY-1;
            }
            
            if(posicionX==n-1 && posicionY == n-1){
                objetivo=true;
            }else{
                 objetivo = ensayar2(laberinto, posicionX + 1, posicionY)
                        || ensayar2(laberinto, posicionX, posicionY + 1)
                        || ensayar2(laberinto, posicionX - 1, posicionY)
                        || ensayar2(laberinto, posicionX, posicionY - 1);
                 if(!objetivo){
                     laberinto[posicionX][posicionY]='I';
                 }
            }
        }
        return objetivo;
        
    }

    public static boolean darCambio(int importeDevolver, Map<Integer, Integer> mapCanti, Map<Integer, Integer> mapSol) {
        boolean objetivo = false;
        Iterator<Integer> it = mapCanti.keySet().iterator();

        while (it.hasNext() && !objetivo) {
            int billete = it.next();
            if (mapCanti.get(billete) > 0 && importeDevolver >= billete) {
                mapCanti.put(billete, mapCanti.get(billete) - 1);
                if (mapSol.get(billete) == null) {
                    mapSol.put(billete, 1);
                } else {
                    mapSol.put(billete, mapSol.get(billete) + 1);
                }
                if (billete == importeDevolver) {
                    objetivo = true;
                } else {
                    objetivo = darCambio(importeDevolver - billete, mapCanti, mapSol);
                    if (!objetivo) {
                        mapCanti.put(billete, mapCanti.get(billete) + 1);
                        mapSol.put(billete, mapSol.get(billete) - 1);

                    }
                }
            }
        }
        return objetivo;
    }

}
