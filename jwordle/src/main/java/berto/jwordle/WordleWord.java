/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.jwordle;

/**
 *
 * @author alber
 */
public class WordleWord implements Comparable<WordleWord> {
//wordleWord es la palabra que se muestra en el juego de wordle. Formada por un array de letras
//ATRIBUTOS
    private WordleLetter[] letras;
    
//CONSTANTES
    private final int MIN_SIZE = 4;
    private final int DEFAULT_SIZE = 5;
//CONSTRUCTORES
    //1. Constructor que recoge el tamaño
    public WordleWord(int size) throws WordleWordSizeException {
        if(size<MIN_SIZE) {
            throw new WordleWordSizeException(size);
        } else {
            this.letras = new WordleLetter[size]; //ponemos el size en el array
        }
    }
    //2. Constructor por defecto:
    public WordleWord() {
        this.letras = new WordleLetter[DEFAULT_SIZE]; //pone el valor default_size como tamaño del array
    }
    //3. Constructor que recoge una palabra:
    public WordleWord(String word) throws WordleWordSizeException {
        setLetters(word.toUpperCase());
    }
    
//MÉTODOS
    public final void setLetters(String word) throws WordleWordSizeException {
        if(word.length()<MIN_SIZE) {
            throw new WordleWordSizeException(word.length());
        } else 
            this.letras = new WordleLetter[word.length()];
            for (int i = 0; i < word.length(); i++) {
                letras[i] = new WordleLetter(word.charAt(i));
            }
        }
    
    public boolean addLetra(WordleLetter letra) {
        boolean resultado = false;
        for (int i = 0; i < letras.length; i++) {
            if(letras[i]==null || letras[i].getCor() == WordleColor.VOID ) {
                letras[i] = letra;
                resultado = true;
            } else {
                resultado = false;
            }
        }
        return resultado;
    }
    
    public boolean addletra(char letra) {
        boolean resultado = false;
        WordleLetter insertar = new WordleLetter(letra);
        for (int i = 0; i < letras.length; i++) {
            if(letras[i] == null || letras[i].getCor() == WordleColor.VOID) {
                letras[i] = insertar;
                resultado = true;
            } else {
                resultado = false;
            }
        }
        return resultado;
    }
    
    public void checkWord(String word) {
        WordleLetter insertar;
        if(word==null) {
            System.out.println("Introduce una palabra válida.");
        } else if(word.length() == letras.length) {
            word = word.toUpperCase();
            for (int i = 0; i < letras.length; i++) {
                if(letras[i].getCaracter() == word.charAt(i)) {
                    insertar = new WordleLetter(word.charAt(i),WordleColor.GREEN);
                    letras[i] = insertar;
                } else if(word.indexOf(letras[i].toString()) != i) {
                    insertar = new WordleLetter(word.charAt(i), WordleColor.YELLOW);
                    letras[i] = insertar;
                } else {
                    insertar = new WordleLetter(word.charAt(i),WordleColor.GRAY);
                    letras[i] = insertar;
                }
            }
        }
    }
    
    public boolean isVoid() {
        int contador = 0;
        boolean resultado = false;
        for (WordleLetter letra : letras) {
            if (letra.isVoid(letra.getCaracter())) {
                contador++;
            }
        }
        if(contador == letras.length) {
            resultado = true;
        }
        return resultado;
    }
    //entiendo que solo debe mostrar las letras sin pintar, con getWord()
    public String getWord() {
        StringBuilder palabra = new StringBuilder();
        for (WordleLetter letra : letras) {
            palabra = palabra.append(letra.getCaracter());
        }
        return palabra.toString();
    }
    
    
    @Override
    public int compareTo(WordleWord o) {
        return this.getWord().compareToIgnoreCase(o.getWord());
    }
    //entiendo que toString() debe mostrar las letras pintadas
    @Override
    public String toString() {
       StringBuilder palabra = new StringBuilder();
        for (int i = 0; i < letras.length; i++) {
            palabra = palabra.append(letras[i].toString());
        }
        return palabra.toString();
    }
}
