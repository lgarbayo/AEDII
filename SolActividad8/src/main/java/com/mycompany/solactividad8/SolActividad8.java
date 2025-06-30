/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.solactividad8;

import com.mycompany.solactividad8.Map;
import com.mycompany.solactividad8.Grafo;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author luis-garbayo
 */
public class SolActividad8 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    /*public static <E> Grafo<E, Integer> viajante(Grafo<E, Integer> g, Vertice<E> v) {
        List<Vertice<E>> porVisitar = new ArrayList<>();
        Iterator<Vertice<E>> it = g.vertices();
        while (it.hasNext()) {

            Vertice<E> ve = it.next();
            porVisitar.add(ve);

        }
        Grafo<E, Integer> solucion = new ListaAdyacencia<>();

        Vertice<E> ciudadActual = v;
        porVisitar.remove(v);

        while (!porVisitar.isEmpty()) {
            Arco<E, Integer> u = minimoArco(g.arcos(), ciudadActual, porVisitar);
            porVisitar.remove(u.getDestino());
            solucion.insertarArco(u);
            ciudadActual = u.getDestino();

        }

        return solucion;

    }

    public static <E> Arco<E, Integer> minimoArco(Iterator<Arco<E, Integer>> it, Vertice<E> v, List<Vertice<E>> paraVisitar) {

        Arco<E, Integer> actual;
        int minimo = Integer.MAX_VALUE;
        Arco<E, Integer> toret = it.next();
        while (it.hasNext()) {
            actual = it.next();
            E origen = actual.getOrigen().getEtiqueta();
            int distancia = actual.getEtiqueta();
            if (origen.equals(v.getEtiqueta()) && paraVisitar.contains(actual.getDestino())
                    && distancia < minimo) {
                minimo = distancia;
                toret = actual;
            }

        }
        return toret;*/


    /**
     *
     * @author faublak
     */
    public class Dijkstra {

        public static <E> Map<Vertice<E>, Integer> dijkstra(Grafo<E, Integer> g, Vertice<E> v) {

            final Integer infinito = Integer.MAX_VALUE; //para comparar las distancias
            Map<Vertice<E>, Integer> distancia = new HashMap<>(); //para devolver
            Set<Vertice<E>> porVisitar = new HashSet<>();//lista de vertices por visitar
            Iterator<Vertice<E>> itv = g.vertices(); //iterador para recorrer los vertices del grafo pasado

            while (itv.hasNext()) {//se rellena la lista de vertices por visitar
                Vertice<E> vert = itv.next();
                porVisitar.add(vert);
                distancia.insertar(vert, infinito);
            }

            distancia.insertar(v, 0);//primer vertice tendra distancia cero

            while (!porVisitar.isEmpty()) {//bucle para modificar los valores de distancias de cada map

                Vertice<E> vMin = minimo(distancia, porVisitar.iterator()); //obtener vetice con dist min
                porVisitar.remove(vMin);//quitarlo de los por visitar
                Integer dis_vMin = distancia.get(vMin); //distancia del minimo a modif

                if (!dis_vMin.equals(infinito)) { //comprobar cual es la menor distancia para asignar al map
                    Iterator<Arco<E, Integer>> ita = g.arcos();//valores d elos arcos del grafo

                    while (ita.hasNext()) {
                        Arco<E, Integer> arco = ita.next();
                        //me interesan aquellos arcos cuyo valor sea el min y que vertices queden por visitar
                        if (arco.getOrigen().equals(vMin) && porVisitar.contains(arco.getDestino())) {
                            Integer pesoElegido = arco.getEtiqueta();

                            if (dis_vMin + pesoElegido < distancia.get(arco.getDestino()))//actualizar en el map
                            {
                                distancia.insertar(arco.getDestino(), dis_vMin + pesoElegido);
                            }
                        }
                    }
                }
            }
            return distancia;
        }

        private static <E> Vertice<E> minimo(Map<Vertice<E>, Integer> map, Iterator<Vertice<E>> iterador) { //recorrer el map y devolver el vertice con dist minima
            Vertice<E> v, minV = iterador.next();//vertice actual y vert minimo
            Integer d, minD = map.get(minV);//distancia del actual y la min

            while (iterador.hasNext()) {
                v = iterador.next();
                d = map.get(v);

                if (d < minD) {//comprobacion de los menores
                    minD = d;
                    minV = v;
                }
            }
            return minV;
        }
    }

    public class ColorearMapa {

        public static <E> Map<Vertice<E>, String> colorearMapa(Grafo<E, Integer> g, String[] colores) {
            Map<Vertice<E>, String> mapaColores = new HashMap<>(); //mapa para introducir colores
            Iterator<Vertice<E>> itv = g.vertices();
            //SEA EL ESQUEMA VORAZ
            while (itv.hasNext()) {//seleccionar el vertice a colorear
                Vertice<E> actual = itv.next(); //para cada vertice, seleccionar un color 
                String colorAdecuado = selecColor(mapaColores, actual, g, colores);
                mapaColores.insertar(actual, colorAdecuado);
            }
            return mapaColores;
        }

        private static <E> String selecColor(Map<Vertice<E>, String> mc, Vertice<E> v, Grafo<E, Integer> g, String[] c) {
            int i = 0;
            String color = "";
            boolean colorAsignado = false;

            while (i < c.length && !colorAsignado) {
                color = c[i];
                colorAsignado = true;
                Iterator<Vertice<E>> itv = g.adyacentes(v);

                while (itv.hasNext() && colorAsignado) {
                    Vertice<E> adyacente = itv.next();
                    if (mc.get(adyacente) != null && mc.get(adyacente).equals(color)) {
                        i++;
                        colorAsignado = false;
                    }
                }
            }
            return color;
        }
    }

    public class EsquemaVoraz {

        public static Map<Integer, Integer> darCambio(int importeDevolver, Map<Integer, Integer> cambioDisponible) {
            Map<Integer, Integer> aDevolver = new HashMap<>();
            int importeDevuelto = 0;

            while (importeDevuelto < importeDevolver) {
                Integer tipoBilleteSelec = mayorBillete(importeDevolver - importeDevuelto, cambioDisponible);

                if (tipoBilleteSelec == 0) {
                    return new HashMap<>();
                } else {
                    int cantNecesaria = (importeDevolver - importeDevuelto) / tipoBilleteSelec;
                    int cantDisponible = cambioDisponible.get(tipoBilleteSelec);

                    if (cantNecesaria > cantDisponible) {
                        cantNecesaria = cantDisponible;
                    }

                    aDevolver.insertar(tipoBilleteSelec, cantDisponible);
                    cambioDisponible.insertar(tipoBilleteSelec, cantDisponible - cantNecesaria);
                }
            }

            return aDevolver;
        }

        private static Integer mayorBillete(int importeADevolver, Map<Integer, Integer> cambioDisponible) {
            Iterator<Integer> it = cambioDisponible.getClaves();
            Integer mayor = 0;

            while (it.hasNext()) {
                Integer billete = it.next();
                if (billete <= importeADevolver && billete > mayor && cambioDisponible.get(billete) > 0) {
                    mayor = billete;
                }
            }

            return mayor;
        }

        public static List<String> llenarCDVoraz(int capMax, Map<String, Integer> espacioProgramas) {
            List<String> CD = new ArrayList<>();
            int espacioOcupado = 0;

            while (espacioOcupado < capMax) {
                String progAGrabar = mayorPrograma(capMax - espacioOcupado, espacioProgramas);

                if (progAGrabar == null) {
                    return CD;
                } else {
                    CD.add(progAGrabar);
                    espacioOcupado += espacioProgramas.get(progAGrabar);
                    espacioProgramas.eliminar(progAGrabar);
                }
            }

            return CD;
        }

        private static String mayorPrograma(int espacioDisponible, Map<String, Integer> espacioProgramas) {
            String maxProg = null;
            int maxEsp = 0;
            Iterator<String> it = espacioProgramas.getClaves();
            while (it.hasNext()) {
                String programas = it.next();
                int espacio = espacioProgramas.get(programas);
                if (espacio > maxEsp) {
                    maxEsp = espacio;
                    maxProg = programas;
                }
            }

            return maxProg;
        }

        /*
        public static Map<String, Integer> llenarMochila (int volumenMaximo, Map<String,Integer> cantidades, Map<String, Integer> volumenes) {
            List<String> mochila = new ArrayList<>();
        }
         */
        public static <T> Map<Vertice<String>, String> horarioExamenes(Grafo<String, T> g, String[] diasSemana) {
            //vertices = asignaturas -> tener en cuenta la implementación de grafo
            //asignar = dias de la semana -> en base a prueba y error, comprobando uno a uno
            //no se debe permitir = coincidan los dias
            //para cada vertice => se le asigna un dia de la semana

            Map<Vertice<String>, String> mapaDias = new HashMap<>(); //mapa para introducir colores
            Iterator<Vertice<String>> itv = g.vertices();
            //SEA EL ESQUEMA VORAZ
            while (itv.hasNext()) {//seleccionar el dia asignatura
                Vertice<String> actual = itv.next(); //para cada vertice, seleccionar un dia 
                String diasAdecuado = selecDia(mapaDias, actual, g, diasSemana);
                mapaDias.insertar(actual, diasAdecuado);
            }
            return mapaDias;
        }

        private static <T> String selecDia(Map<Vertice<String>, String> mapHorario, Vertice<String> v, Grafo<String, T> g, String[] dias) {
            int i = 0;
            String dia = "";
            boolean diaAsignado = false;

            while (i < dias.length && !diaAsignado) {
                dia = dias[i];
                diaAsignado = true;
                Iterator<Vertice<String>> itv = g.adyacentes(v);

                while (itv.hasNext() && diaAsignado) {
                    Vertice<String> adyacente = itv.next();
                    if (mapHorario.get(adyacente) != null && mapHorario.get(adyacente).equals(dia)) {
                        i++;
                        diaAsignado = false;
                    }
                }
            }
            return dia;
        }

    }
}
