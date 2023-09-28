/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package berto.encriptacion;

import java.util.Scanner;
public class Encriptacion {
//ATRIBUTOS
    private StringBuilder cadena = new StringBuilder();
    private boolean encriptada;
    private int desplazamiento;
    
    private final int DEFAULT_DESPLAZAMIENTO = 6;
    
    //recoger stringbuilder
    public static char caracter;
    
//CONSTRUCTOR
    public Encriptacion(String texto) {
        this.cadena = cadena.append(texto.trim());
        this.encriptada = false;
        this.desplazamiento = DEFAULT_DESPLAZAMIENTO;
    }

//MÉTODOS
    //GETTERS Y SETTERS:
    public void setDesplazamiento(int desplazamiento) {
        if(this.encriptada == false) {
            this.desplazamiento = desplazamiento;
        }
    }
    
    public String toMayus() {
        return this.cadena.toString().toUpperCase();
    }
    public String toMinus() {
        return this.cadena.toString().toLowerCase();
    }     
    public int count(char c) {
        int contador = 0;
        
        for (int i = 0; i < cadena.length(); i++) {
            if(toMayus().charAt(i) == Character.toUpperCase(c)) {
                contador++;
            }
        }
        return contador;
    }
    private void reverse() {
        cadena.reverse();
    }
    public void encriptar() {
        StringBuilder resultado = new StringBuilder();
        reverse();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int asciiEncriptado = (int) c + desplazamiento;
            resultado.append((char) asciiEncriptado);
        }
        this.encriptada = true;
        this.cadena = resultado;
    }
    public void desencriptar() {
        StringBuilder resultado = new StringBuilder();
        reverse();
        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            int asciiEncriptado = (int) c - desplazamiento;
            resultado.append((char) asciiEncriptado);
        }
        this.encriptada = false;
        this.cadena = resultado;
    }
    
    @Override
    public String toString() {
        StringBuilder resultado = new StringBuilder();
        resultado.append("O caracter aparece ").append(count(caracter)).append(" veces").append('\n');
        resultado.append("UpperCase: ").append(toMayus()).append('\n');
        resultado.append("LowerCase: ").append(toMinus()).append('\n');
        resultado.append("Orixinal: ").append(this.cadena.toString()).append('\n');
        encriptar();
        resultado.append("Encriptada: ").append(cadena.toString());
        return resultado.toString();
        
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();
        Encriptacion enc = new Encriptacion(texto);
        if(sc.hasNextInt()) {
            int desplazar = sc.nextInt();
            enc.setDesplazamiento(desplazar);
            sc.nextLine(); //para saltarme a la siguiente línea
            caracter = sc.nextLine().charAt(0);
        } else {
            sc.nextLine(); //para saltarme la línea que no es int
            caracter = sc.nextLine().charAt(0);
        }
        System.out.println(enc);
    }
}
