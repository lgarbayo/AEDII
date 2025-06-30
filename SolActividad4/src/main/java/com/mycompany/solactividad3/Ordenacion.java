/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solactividad3;

/**
 *
 * @author luis-garbayo
 */
public class Ordenacion {
    public static void heapSort(Integer[] elementos)
    {
        Heap<Integer> heap = new HeapBinario<>();
        
        for (int i = 0; i < elementos.length; i++)
            heap.introducir(elementos[i]);
        
        heap.arreglarHeap();
        
        for (int i = 0; i < elementos.length; i++)
            elementos[i] = heap.suprimirMax();
    }
}
