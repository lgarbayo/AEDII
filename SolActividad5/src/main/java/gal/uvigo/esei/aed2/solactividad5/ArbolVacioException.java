/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package gal.uvigo.esei.aed2.solactividad5;

/**
 *
 * @author dfabi
 */
public class ArbolVacioException extends RuntimeException {

    /**
     * Creates a new instance of <code>ArbolVacioException</code> without detail
     * message.
     */
    public ArbolVacioException() {
    }

    /**
     * Constructs an instance of <code>ArbolVacioException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ArbolVacioException(String msg) {
        super(msg);
    }
}
