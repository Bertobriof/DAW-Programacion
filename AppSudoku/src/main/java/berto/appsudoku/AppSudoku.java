/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package berto.appsudoku;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class AppSudoku {

    public static void main(String[] args) throws ExceptionTamano, Exception {
        Scanner sc = new Scanner(System.in);
        char[][] matrizSudoku = new char[9][9];
        
        String ejemplo1 ="""
                         513876492
                         789243651
                         246915783
                         328654917
                         974182536
                         165397248
                         697438125
                         851729364
                         432561879""" ; 
        
        String ejemplo2 = "5 3876492\n" +
"789243 5 \n" +
"246 15783\n" +
"328654917\n" +
"974182536\n" +
"1 5397 48\n" +
"697438125\n" +
"851729364\n" +
"4325 1879";
        
        
        String[] fila = ejemplo2.split("\n");
        for (int i = 0; i < 9; i++) {
            
            for (int j = 0; j < 9; j++) {
                if(fila[i].charAt(j) == ' ') {
                   matrizSudoku[i][j] = '0'; 
                } else {
                   matrizSudoku[i][j] = fila[i].charAt(j);
                }
                
            }
        }

        /*System.out.println("Introduce nombre de archivo: ");
        String ruta = sc.nextLine();
        sc.close();*/
        System.out.println("El sudoku es: ");
        Sudoku sdk = new Sudoku(matrizSudoku);
        System.out.println(sdk);
        /*if(sdk.saveSudoku(ruta) == true) {
            sdk.saveSudoku(ruta);
            System.out.println("Sudoku guardado");
        }
        
        System.out.println(sdk.saveSudoku(ruta));*/
        //System.out.println(sdk.getNextVoidCell().getX());
        //System.out.println(sdk.getNextVoidCell().getY());
        List<Sudoku> hijos = sdk.getChildren();
        
       for (int i = 0; i < hijos.size(); i++) {
            System.out.println(hijos.get(i));
            
        }
        /*System.out.println("Is completed: ");
        System.out.println(sdk.isCompleted());
        System.out.println("Is valid: ");
        System.out.println(sdk.isValid());
        System.out.println("Check filas, cuadrantes, columnas: ");
        System.out.println(sdk.checkFilas(matrizSudoku));
        System.out.println(sdk.checkCuadrantes(matrizSudoku));
        System.out.println(sdk.checkColumnas(matrizSudoku));*/
        
        
        
        
    }
    
}
