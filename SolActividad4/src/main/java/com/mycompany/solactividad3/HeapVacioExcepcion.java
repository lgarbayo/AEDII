/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.solactividad3;

/**
 *
 * @author Luis
 */
public class HeapVacioExcepcion extends RuntimeException {
    public HeapVacioExcepcion(){
        super();
    }
    
    public HeapVacioExcepcion(String msg){
        super(msg);
    }
}
