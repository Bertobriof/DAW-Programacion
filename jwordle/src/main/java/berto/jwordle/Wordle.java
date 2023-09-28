package berto.jwordle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public final class Wordle {
//atributos
    private int intentos; //intento de juego
    private String palabra; //es la palabra a adivinar
    private WordleWord[] wordleWords = new WordleWord[intentos]; 
    private static final ArrayList<String> dictionary = new ArrayList<>();
    private final Random RANDOM_GENERATOR = new Random();
    private final String wordsFile = "palabrasGWordle.txt"; //por defecto "palabrasGWordle.txt".
    
//constantes
    private static final int DEFAULT_ATTEMPS = 6;
    private static final String DICTIONARY_DIRECTORY = "c:/diccionarios/";

    
//CONSTRUCTORES
    public Wordle(String palabra) throws WordleWordSizeException {
        this.palabra = palabra;
        initWords();
    }
    public Wordle(String palabra, int intentos) throws WordleWordSizeException {
        this.palabra = palabra;
        this.intentos = intentos;
        initWords();
    }
    public Wordle() throws FileNotFoundException, WordleWordSizeException, IOException {
        this.palabra = getRandomPalabra();
        initWords();
    }
    
//MÉTODOS
    public boolean checkWord(String word) throws WordleWordSizeException {
        boolean resultado;
        int i = 0;
        if(word.length()>=4) {
            resultado = true;
            /*for (int i = 0; i < wordleWords.length; i++) {
                if(wordleWords[i].isVoid() || wordleWords[i] == null && word.length() == palabra.length() /*&& isInDictionary(word)) {
                    
                }
            }*/
            do {
                if(wordleWords[i].isVoid() || wordleWords[i].getWord() == null && word.length() == palabra.length() && isInDictionary(word)) {
                wordleWords[i].setLetters(palabra); //asigno la palabra a testar
                wordleWords[i].checkWord(word); //compruebo si coincide con la que vamos a añadir
                i++;
                }
            } while(i == intentos);
        } else {
            resultado = false;
        }
        return resultado;
    }
    public String getRandomPalabra() throws FileNotFoundException, IOException {
        File file = new File(DICTIONARY_DIRECTORY.concat(wordsFile));
        String contenido = "Casas Perro Mosca Oveja Gatos";
        if(!file.exists()) { //crear el archino si no existe
            file.createNewFile();
            FileWriter fw = new FileWriter(file);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(contenido);
            }
        }
        String resultado;
        try (Scanner scArchivo = new Scanner(file).useDelimiter(" ") //el separador es el espacio
        ) {
            if(dictionary.isEmpty()) {
                int i = 0;
                while(scArchivo.hasNext()) {
                    dictionary.add(i, scArchivo.next());
                    i++;
                }
                resultado = dictionary.get(RANDOM_GENERATOR.nextInt(dictionary.size()));
            } else {
                resultado = dictionary.get(RANDOM_GENERATOR.nextInt(dictionary.size()));
            }
        }
        return resultado;
    }
    
    public String getWord() {
        return this.palabra.toUpperCase();
    }
    
    public boolean hasFinish() {
        boolean resultado = false;
        int contador = 0;
        do {
            for (int i = 0; i < wordleWords.length; i++) {
                if(!wordleWords[i].isVoid()) {
                    contador++;
                }
            } 
        } while (contador != wordleWords.length);
        if(contador == wordleWords.length) {
            resultado = true;
        }
        return resultado;
    }
    private void initWords() throws WordleWordSizeException {
        int size = palabra.length();
        if(palabra == null) {
                this.intentos = DEFAULT_ATTEMPS;
                this.wordleWords = new WordleWord[intentos];
            }
        else {
            if(size>=4) {
                this.wordleWords = new WordleWord[intentos];
            } else {
                throw new WordleWordSizeException(size);
            }
        }
    }
    public boolean isInDictionary(String palabra) {
        boolean resultado = false;
        for (int i = 0; i < dictionary.size(); i++) {
            if(palabra.equalsIgnoreCase(dictionary.get(i))) {
                resultado = true;
            }
        }
        return resultado;
    }
    public void setPalabra(String palabra) throws WordleWordSizeException {
        this.palabra = palabra;
        initWords();
    }
    @Override
    public String toString() {
        StringBuilder wordle = new StringBuilder();
        String s = System.lineSeparator();
        for (int i = 0; i < wordleWords.length; i++) {
            wordle.append(wordleWords[i])
                    .append(s);
        }
        return wordle.toString();
    }
}
