/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.jwordle;



/**
 *
 * @author alber
 */
public class WordleLetter extends Object {
    private char caracter;
    private WordleColor cor;
    
    //constructor por defecto
    public WordleLetter() {
        this.caracter = '\0';
        this.cor = WordleColor.VOID;
    }
    //constructor que recoge caracter
    public WordleLetter(char caracter) {
        if (Character.isLetter(caracter)) {
            this.caracter =  caracter;
        } else {
            this.caracter = '\0';
        }
        this.cor = WordleColor.VOID;
    }
    //constructor que recoge el carácter y elcolor.
    public WordleLetter(char caracter, WordleColor cor) {
        if (Character.isLetter(caracter)) {
            this.caracter =  caracter;
            this.cor = cor;
        } else {
            this.caracter = '\0';
            this.cor = WordleColor.VOID;
        }
    }
    
    //getter & setters
    public char getCaracter() {
        return caracter;
    }
    public void setCaracter(char caracter) {
        if (Character.isLetter(caracter)) {
            this.caracter =  caracter;
        } else {
            this.caracter = '\0';
        }
    }
    public WordleColor getCor() {
        return cor;
    }
    public void setCor(WordleColor cor) {
        this.cor = cor;
    }
    
    public boolean isVoid(char caracter) {
        return caracter == '\0'; //devuelve true si caracter vale 0.
    }
    
    @Override
    public String toString() {
        return  cor.getAnsiCodeFromRGB().concat(Character.toString(caracter).toUpperCase()).concat(WordleColor.RESET.getAnsiCodeFromRGB());
    }

}
