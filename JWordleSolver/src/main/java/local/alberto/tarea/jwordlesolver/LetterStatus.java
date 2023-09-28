/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

/**
 *
 * @author alber
 */
public enum LetterStatus {
    CORRECT('c'),
    VALID('v'),
    BAD('b'),
    DESELECT('d');
    
    private char caracter;
    LetterStatus(char caracter) {
        this.caracter = caracter;
    }
//Métodos
//Getter y setters
    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
//otro metodos
       //devuelve el LetterStatus asociado al carácter que recoge como argumento
    public static LetterStatus getLetterStatus(char c) {
        switch (c) {
            case 'b' -> {
                return BAD;
            }
            case 'c' -> {
                return CORRECT;
            }
            case 'd' -> {
                return DESELECT;
            }
            case 'v' -> {
                return VALID;
            }
            default -> throw new AssertionError();
        }
    }
}
