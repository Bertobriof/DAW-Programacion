/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author alber
 */
public class WordleSolver extends GameSolver{ 
//CONSTANTES
    private static final String SOLVER_NAME = "Wordle Solver";
    private final int DEFAULT_SIZE = 26;//26;
    private final String DEFAULT_WORDS_FILE = "english.txt";
    
//ATRIBUTOS
    private Letter[] letras;
    private String wordsFile;
    private ArrayList<String> diccionario;
    
//CONSTRUCTOR
    
    public WordleSolver() throws FileNotFoundException {
        super(SOLVER_NAME);
        this.letras = new Letter[DEFAULT_SIZE];
        String file = "C:/diccionarios/";
        Scanner sc = new Scanner (new File("C:/diccionarios/".concat(DEFAULT_WORDS_FILE)));
        sc.useDelimiter("/n");
        diccionario = new ArrayList<>();
        do {
            diccionario.add(sc.nextLine().toUpperCase());
        } while (sc.hasNext());
        
    }
    
//MÉTODOS
    public void listLetters() {
        for (int i = 0; i < letras.length; i++) {
            if(letras[i] != null) {
            System.out.println(letras[i].toString());
            }
        }
    }
    
    public boolean addLetter(Letter letra) {
        boolean resultado = false;
        for (int i = 0; i < letras.length; i++) {
            if(letras[i] == null /*&& letras[i] != letra*/) {
                letras[i] = letra;
                resultado = true;
                break;
            }
        }
        return resultado;
    }
    
    public boolean addLetters(String palabra, String estado) {
        Pattern patron = Pattern.compile("[vbcd]", Pattern.CASE_INSENSITIVE);
        boolean resultado = false;
        Matcher matcher = patron.matcher(estado);
        Letter letra;
        boolean matchFound = matcher.find(); //Para verificar que solo incluye las letras del patrón
        if(palabra.length() == estado.length() && matchFound) {
            for (int i = 0; i < palabra.length(); i++) {
               letra = new Letter(palabra.charAt(i),i,LetterStatus.getLetterStatus(estado.toLowerCase().charAt(i)));
               addLetter(letra);
               resultado = true;
            }
        }
        return resultado;
    }
    public boolean checkPalabra(String palabra) {
        /*Pattern patron = Pattern.compile("[a-zA-Z]");
        Matcher matcher = patron.matcher(palabra);
        boolean matchFound = matcher.find();
        boolean valida = true;
        //if(matchFound == true) {
            for (int i = 0; i < letras.length; i++) {
                for (int j = 0; j < palabra.length(); j++) {
                    if((letras[j].getEstado() != LetterStatus.DESELECT && letras[i] != null) || letras[i] != null) { // Si la letra no es nula y el estado no es DESELECT obtiene el estado:
                        resultado = switch (letras[j].getEstado()) {
                            case CORRECT -> letras[j].getPosicion() == j && letras[i].getLetter() == palabra.charAt(j);
                            case VALID -> letras[j].getLetter() == palabra.charAt(j) && letras[i].getPosicion() != j;
                            case BAD -> letras[j].getLetter() != palabra.charAt(j);
                            default -> false;
                        };
                    }
                }
                if(resultado == false) {
                    break;
                }
            }
        //}
        return resultado;*/
        if(palabra == null) {
            return false;
        }
        palabra = palabra.toUpperCase();
        boolean valida = true;
        for (int i = 0; valida == true && i < letras.length; i++) {
            Letter letra = letras[i];
            if(letra != null && letra.getEstado() == LetterStatus.DESELECT && letra.getPosicion()< palabra.length()) {
                switch (letra.getEstado()) {
                    case CORRECT ->
                        valida = palabra.charAt(letra.getPosicion()) == letra.getLetter();
                    case VALID ->
                        valida = palabra.contains(String.valueOf(letra.getLetter())) && palabra.charAt(letra.getPosicion()) != letra.getLetter();
                    case BAD ->
                        valida = !palabra.contains(String.valueOf(letra.getLetter()));
                    case DESELECT -> {
                    }   
                }
            }
        }
        return valida;
    }
    @Override
    public void solve() {
        clearSolucions();
        /*WordleSolution ws = new WordleSolution();
        for (int i = 0; i < diccionario.size(); i++) {
            if(checkPalabra(diccionario.get(i))) {
                ws.setWord(diccionario.get(i));
                ws.setValor();
                addSolucion(ws);
                ws.toString();
            }
        }*/
       /* for (String palabra : this.diccionario) {
            if(checkPalabra(palabra.toUpperCase())) {
                WordleSolution ws = new WordleSolution();
                ws.setWord(palabra.toUpperCase());
                ws.setValor();
                addSolucion(ws);
            }
        }
        //toString();*/
        for (String palabra : this.diccionario) {
            if(checkPalabra(palabra.toUpperCase())) {
                WordleSolution ws = new WordleSolution();
                ws.setWord(palabra.toUpperCase());
                ws.setValor();
                addSolucion(ws); 
            }
            
        }
    }
    
}
