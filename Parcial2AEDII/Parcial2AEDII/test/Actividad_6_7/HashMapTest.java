package Actividad_6_7;

import Actividad_6_7.HashMap;
import Interfaces.Map;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HashMapTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalCapacity() {
        System.out.println("Test 'HashMap' con capacidad negativa");
        Map hashMap = new HashMap(-5);
    }

    @Test
    public void testTamañoVacio() {
        System.out.println("Test 'tamaño' con HashMap vacío");
        Map instance = new HashMap();
        int expResult = 0;
        int result = instance.tamaño();
        assertEquals(expResult, result);
    }

    @Test
    public void testTamaño() {
        System.out.println("Test 'tamaño' con valor 2");
        Map<String, String> instance = new HashMap<>();
        instance.insertar("c1", "v1");
        instance.insertar("c2", "v2");
        int expResult = 2;
        int result = instance.tamaño();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetAndInsertar() {
        System.out.println("Test 'get' e 'insertar' correctos");
        String clave = "clave1";
        String valor = "valor1";
        Map<String, String> instance = new HashMap<>();
        instance.insertar(clave, valor);
        instance.insertar("c2", "v2");
        String expResult = valor;
        String result = instance.get(clave);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNonExistent() {
        System.out.println("Test 'get' si clave no existe");
        Map<String, String> instance = new HashMap<>();
        instance.insertar("c2", "v2");
        String expResult = null;
        String result = instance.get("clave1");
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminar() {
        System.out.println("Test 'eliminar' clave existente");
        String clave = "c2";
        String valor = "v2";
        Map<String, String> instance = new HashMap<>();
        instance.insertar(clave, valor);
        String expResult = valor;
        String result = instance.eliminar(clave);
        assertEquals(expResult, result);
    }

    @Test
    public void testEliminarNonExistent() {
        System.out.println("Test 'eliminar' clave no existente");
        String clave = "c2";
        Map<String, String> instance = new HashMap<>();
        String expResult = null;
        String result = instance.eliminar(clave);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetValores() {
        System.out.println("Test 'getValores' ");
        Map<String, String> instance = new HashMap<>();
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        instance.insertar("c1", v1);
        instance.insertar("c2", v2);
        instance.insertar("c3", v3);
        List<String> expResult = new ArrayList<>();
        expResult.add(v1);
        expResult.add(v2);
        expResult.add(v3);
        Iterator<String> it = instance.getValores();
        List<String> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        assertArrayEquals(expResult.toArray(new String[0]), result.toArray(new String[0]));
    }

    @Test
    public void testGetClaves() {
        System.out.println("Test 'getClaves' ");
        Map<String, String> instance = new HashMap<>();
        String c1 = "c1";
        String c2 = "c2";
        String c3 = "c3";
        instance.insertar(c1, "v1");
        instance.insertar(c2, "v2");
        instance.insertar(c3, "v3");
        List<String> expResult = new ArrayList<>();
        expResult.add(c1);
        expResult.add(c2);
        expResult.add(c3);
        Iterator<String> it = instance.getClaves();
        List<String> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        assertArrayEquals(expResult.toArray(new String[0]), result.toArray(new String[0]));
    }
}
