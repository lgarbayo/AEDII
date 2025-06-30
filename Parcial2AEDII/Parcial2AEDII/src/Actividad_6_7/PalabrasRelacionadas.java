package Actividad_6_7;

import Especificion_Grafos.MapDeMap;
import Interfaces.Arco;
import Interfaces.Grafo;
import Interfaces.Map;
import Interfaces.Vertice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PalabrasRelacionadas {

    public static Grafo<String, Integer> construirGrafo(List<String> palabras) {
        return map2Grafo(list2Map(palabras));
    }

    private static Grafo<String, Integer> map2Grafo(Map<String, List<String>> diccionario) {
        Grafo<String, Integer> grafo = new MapDeMap<>();
        Iterator<String> itClaves = diccionario.getClaves();
        Iterator<String> itOrigen;
        Iterator<String> itDestino;
        String destino;
        String origen;
        while (itClaves.hasNext()) {
            itOrigen = diccionario.get(itClaves.next()).iterator();
            itDestino = itOrigen;
            while (itOrigen.hasNext()) {
                origen = itOrigen.next();
                while (itDestino.hasNext()) {
                    destino = itDestino.next();
                    if (!origen.equals(destino)) { //No se crearan arcos al propio vertice

                        grafo.insertarArco(new Arco<>(
                                new Vertice<>(origen),
                                new Vertice<>(destino),
                                0)
                        );
                    }
                }
            }
        }
        return grafo;
    }

    private static Map<String, List<String>> list2Map(List<String> palabras) {
        Map<String, List<String>> diccionario = new HashMap<>();

        String clave;

        for (String palabra : palabras) {//Para cada palabra en la lista
            for (int i = 0; i < palabra.length(); i++) {//Para cada letra de la palabra
                clave = funcionClave(palabra, i);
                List<String> lista = diccionario.get(clave);//Se recupera la lista asociada a la clave
                if (lista == null) { //si la clave no tiene lista asociada, se crea
                    lista = new ArrayList<>();
                }
                lista.add(palabra);
                diccionario.insertar(clave, lista);
            }
        }
        return diccionario;
    }

    private static String funcionClave(String palabra, int i) {
        return palabra.substring(0, i) + "_" + palabra.substring(i + 1);
    }
}
