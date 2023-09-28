/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.jwordle;

import java.io.Serializable;

/**
 *
 * @author alber
 */
public class WordleWordSizeException extends Exception implements Serializable {
    private int size;
    private static final int serialVersionUID = 0000001;
    
    //constructor por defecto
    public WordleWordSizeException() {
        super("El tamaño de la palabra no es correcto");
    }
    //constructor que recoge el tamaño
    public WordleWordSizeException(int size) {
        super("El tamaño de la palabra no es correcto");
        this.size = size;
    }
    
    //métodos de instancia
    public int getStize() {
        return size; //devuelve el size de un tamaño no válido.
    }
}
