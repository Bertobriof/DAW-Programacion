/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

import java.util.Random;

/**
 *
 * @author alber
 */
public class WordleSolution extends Solution{
    //atributos
    private String word;
    //constructor
    public WordleSolution(){};
//métodos
    //1.Getter y setters
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word.toUpperCase();
    }
    //2.Otros metodos
    @Override
    public void setValor() {
       Random r = new Random();      
       int valor = r.nextInt(500);
       super.setValor(valor);
    }
    
    @Override
    public String toString() {
        String resultado = null;
        if(!this.word.isEmpty()) {
            resultado = getWord();
        }
        return resultado;
    }
}
