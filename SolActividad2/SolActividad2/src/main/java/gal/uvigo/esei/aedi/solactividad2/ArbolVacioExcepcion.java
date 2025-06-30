/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gal.uvigo.esei.aedi.solactividad2;

/**
 *
 * @author lgfernandez23_esei.u
 */
public class ArbolVacioExcepcion extends RuntimeException {
    public ArbolVacioExcepcion() {
        super();
    }
    
    public ArbolVacioExcepcion(String msg) {
        super(msg);
    }
}
