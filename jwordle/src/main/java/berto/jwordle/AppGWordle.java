/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.jwordle;

//import java.util.Random;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class AppGWordle {
    private static final Scanner KEYWORD_SCAN = new Scanner(System.in);
    private static final Console CONSOLA = null;
    private static final String     BARRA_HORIZONTAL = "====================";
    
    public static void main(String[] args) throws WordleWordSizeException, IOException {
        Wordle wordle = new Wordle();
        do {
            System.out.println("Introduce una palabra para adivinar de 5 letras: ");
            String palabra = KEYWORD_SCAN.next();
            System.out.println(BARRA_HORIZONTAL);
            if(wordle.checkWord(palabra) == true) {
                System.out.println("true");
                            System.out.println(wordle.toString());
                            System.out.println(wordle.getWord());
            } else {
                System.out.println("false");
            }

            
            System.out.println(BARRA_HORIZONTAL);
        } while (wordle.hasFinish() == true);

    }
}
