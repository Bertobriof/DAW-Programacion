/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appsudoku;

import java.awt.Point;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author alber
 */
public class Sudoku implements Serializable {
    //Atributos
    public Set<Character> alfabeto;
    private char[][] celas;
    
    //Constantes
    private final Set<Character> ALFABETO_POR_DEFECTO = Set.of('1','2','3','4','5','6','7','8','9');
    
    //CONSTRUCTORES
    public Sudoku() {
        this.alfabeto = Set.copyOf(ALFABETO_POR_DEFECTO);
        this.celas = new char[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.celas[i][j] = '0';
            } 
        }
    }
    public Sudoku(char[] alfabeto) throws ExceptionTamano{
        double raiz = Math.sqrt(alfabeto.length);
        if((raiz == (int)raiz)) {
            this.alfabeto=new HashSet<>();
            for (int i = 0; i < alfabeto.length; i++) {
                this.alfabeto.add(alfabeto[i]);
            }
        } else {
            throw new ExceptionTamano();
        }
        this.celas = new char[alfabeto.length][alfabeto.length];
        for (int z = 0; z < alfabeto.length; z++) {
            for (int j = 0; j < alfabeto.length; j++) {
                this.celas[z][j] = '0';
            } 
        }
    }
    
    public Sudoku(char[][] celas) throws ExceptionTamano {
        double raiz = Math.sqrt(celas.length);
        if(raiz == (int)raiz) {
            this.celas = celas;
            this.alfabeto = new HashSet<>();
            for (char i = 0; i < celas.length; i++) {
                //this.alfabeto=ALFABETO_POR_DEFECTO;
                this.alfabeto.add((char) (i + '1')); //haciendo eso partimos del ascii 0, que es 48 y vamos sumando los caracteres
            } 
        } else {
            throw new ExceptionTamano();
        }
    }
//MÉTODOS
    public int getSize() {
        return this.celas.length;
    }
    public char[][] getCelas() {
        return celas;
    }

    public void setCelas(int i, int j, char c) {
        if(celas.length > i) { 
            this.celas[i][j] = c;
        }
        
    }
    public void setCeldas(char[][] celdas) throws ExceptionTamano {
        if(Math.sqrt(celdas.length) == (int) Math.sqrt(celdas.length)) {
            this.celas = celdas;
        } else {
            throw new ExceptionTamano();
        }
    }
    public char getCelda(int i, int j) {
        if(i < 0 || i >= celas.length || j < 0 || j >= celas[i].length) {
            throw new IndexOutOfBoundsException("Valores de i o j fuera de los límites del array");
        }
        return this.celas[i][j];
    }
    public boolean isCompleted() {
        for (int i = 0; i < celas.length; i++) {
            for (int j = 0; j < celas[i].length; j++) {
                if (celas[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid() throws Exception {
        return checkFilas(celas) && checkColumnas(celas);// && checkCuadrantes(celas);    
    }
    
    public boolean checkFilas(char[][] matrizSudoku) {
        for (int i = 0; i < matrizSudoku.length; i++) {
            Set<Character> filas = new HashSet<>();
            for (int j = 0; j < matrizSudoku[i].length; j++) {
                if(filas.contains(matrizSudoku[i][j])) {
                    return false;
                } else {
                    filas.add(matrizSudoku[i][j]);
                }
            }
            filas.clear();
        }
        return true;
    }
    public boolean checkColumnas(char[][] matrizSudoku) {
        for (int j = 0; j < matrizSudoku[0].length; j++) {
            Set<Character> columnas = new HashSet<>();
            for (int i = 0; i < matrizSudoku.length; i++) {
                if(columnas.contains(matrizSudoku[i][j])) {
                    return false;
                } else {
                    columnas.add(matrizSudoku[i][j]);
                }
            }
            columnas.clear();
        }
        return true;
    }
    public int getCuadrante() throws Exception {
        double raiz = Math.sqrt(getSize());
        if(raiz != (int) raiz) {
            throw new Exception("El tamaño del sudoku debe ser un cuadrado perfecto");
        }
        return (int) raiz;
    }
    public boolean checkCuadrantes(char[][] matrizSudoku)throws Exception{ //creo que este metodo sobra
        int tamanoCuadrante = getCuadrante();
        
        for (int a = 0; a < matrizSudoku.length; a += tamanoCuadrante) { //nos desplazamos en cuadrantes con este for
            Set<Character> cuadrante = new HashSet<>();
            for (int b = 0; b < matrizSudoku[a].length; b += tamanoCuadrante) { //nos desplazamos en los valores del cuadrante
                cuadrante.clear();
                // Set<Character> cuadrante = new HashSet<>(); //registro los cuadrantes en el collection
                for (int i = a; i < a+tamanoCuadrante; i++) { //ahora recorro los valores del cuadrante
                    for (int j = b; j < b+tamanoCuadrante; j++) {
                        char celda = matrizSudoku[i][j];
                        if(celda != '0' || celda != '\0') {
                            if(cuadrante.contains(celda)) {
                                return false; //paramos el bucle
                            } else {
                                cuadrante.add(celda); //añadimos la celda
                            }
                        }
                    }
                }
            }

        }
        return true;
    }
    public Point getNextVoidCell() {
        for (int i = 0; i < celas.length; i++) {
            for (int j = 0; j < celas[i].length; j++) {
                if(celas[i][j] == '0' || celas[i][j] == '\0' || celas[i][j] == ' ') {
                    return new Point(i,j);
                }
            }
        }
        return null; //Si no hay ningún valor nulo
    }
    public char[][] copiaSudoku(char[][] array) {
        char[][] copia = array;
        return copia;
    }
    
    
    
    public List<Sudoku> getChildren() throws ExceptionTamano, Exception {
        ArrayList<Sudoku> children = new ArrayList<>();
        
        Point celdaVacia = getNextVoidCell();
        Sudoku hijo = new Sudoku(copiaSudoku(celas));
        while(celdaVacia != null) {
            int x = celdaVacia.x;
            int y = celdaVacia.y;
            
            if (celas[x][y] == '0' ||celas[x][y] == '\0' || celas[x][y] == ' ') {
                for (int i = 0; i < alfabeto.size(); i++) {
                    hijo.setCelas(x, y, (char) alfabeto.toArray()[i]);
                    //try {
                      if(!hijo.isValid()) {
                            children.add(hijo);  
                        }
                    //} catch (Exception e) {
                      //  System.out.println(e);
                    //}
                }
            }
            celdaVacia = hijo.getNextVoidCell();
        }
        return children;  /*
        for (int i = 0; i < celas.length; i++) {
            Point celdaVacia = getNextVoidCell();
            for (int j = 0; j < celas[i].length; j++) {
                
                if(celas[celdaVacia.x][celdaVacia.y] == '0' ||celas[celdaVacia.x][celdaVacia.y] == '\0') {
                    for (Character caracter : alfabeto) {
                        Sudoku child = new Sudoku(celas);
                        child.setCelas(celdaVacia.x, celdaVacia.y, caracter);
                        try {
                            if(child.isValid() && child.isCompleted()) {
                                children.add(child);
                            }
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                }
             }
        }*/
    }
    
    public boolean saveSudoku(String ruta) throws FileNotFoundException, IOException {
        try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(ruta)))) {
            out.writeObject(this);
            out.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    public Set<Character> getAlfabeto() {
        return this.alfabeto;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < celas.length; i++) {
            for (int j = 0; j < celas[i].length; j++) {
                if(celas[i][j] == '\0' || celas[i][j] == ' ')  { // '\0' caracter nulo
                    sb.append(' ');
                } else {
                  sb.append(celas[i][j]);  
                }
                if(j == (celas[i].length-1)){ //finaliza la linea y pone un salto de linea
                    sb.append('\n');
                }
            }
        }
        return sb.toString();
    }
}
