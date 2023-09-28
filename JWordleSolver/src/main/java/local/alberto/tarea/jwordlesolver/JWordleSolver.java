/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package local.alberto.tarea.jwordlesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class JWordleSolver {

    public static void main(String[] args) throws FileNotFoundException {   
        WordleSolver ws = new WordleSolver();
        
        int continuar = 1;
        
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce una palabra:");
            String palabra = sc.nextLine();
            System.out.println("Introduce una el estado de cada letra (solo validos caracteres vbcd):");
            String estado = sc.nextLine();
            ws.addLetters(palabra, estado);  
            //ws.listLetters();    
            System.out.println("Las posibles solucciones son:");
            ws.solve();
            ws.toString(); 
            System.out.println("¿Deseas introducir otra palabra? Y/N");
            switch (sc.next().toUpperCase()) {
                case "Y":
                    continuar = 1;
                    break;
                case "N":
                    continuar = 0;
                    break;
                default:
                    throw new AssertionError();
            }
        } while (continuar == 1);

    }
}
