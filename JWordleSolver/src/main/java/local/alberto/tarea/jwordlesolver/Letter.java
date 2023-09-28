/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

/**
 *
 * @author alber
 */
public class Letter implements Comparable<Letter>{
//CONSTANTES
    private final int DEFAULT_POSITION = -1;
    
//ATRIBUTOS
    private char letter;
    private LetterStatus estado;
    private int posicion;
    
//CONSTRUCTORES
    public Letter(char letra, int posicion, LetterStatus estado) {
        if(Character.isLetter(letra)) {
            this.letter = Character.toUpperCase(letra);
        } else {
            this.letter = 0;
        }
        
        if(estado == LetterStatus.DESELECT || posicion <0) {
            this.posicion = DEFAULT_POSITION;
        } else {
            this.posicion = posicion;
        }
        this.estado = estado;
    }
    public Letter(char letra) {
        if(Character.isLetter(letra)) {
            this.letter = Character.toUpperCase(letra);
        } else {
            this.letter = 0;
        }
        this.posicion = DEFAULT_POSITION;
        this.estado = LetterStatus.DESELECT;
    }
//MÉTODOS:
    //GETTER Y SETTERS
    public char getLetter() {
        return letter;
    }

    public LetterStatus getEstado() {
        return estado;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setLetter(char letter) {
        this.letter = Character.toUpperCase(letter);
    }

    public void setEstado(LetterStatus estado) {
        this.estado = estado;
    }

    public void setPosicion(int posicion) {
        if(posicion <0) {
            this.posicion = DEFAULT_POSITION;
        } else {
            this.posicion = posicion;
        }
    }   
    
    //OTROS MÉTODOS


    @Override
    public boolean equals(Object objeto) {
        boolean resultado = false;
        if(Character.valueOf(letter).equals(objeto) && Integer.valueOf(posicion).equals(objeto)) {
            resultado = true;
        }
        return resultado;
    }
    @Override
    public int compareTo(Letter letra) {
        int resultado;
        if(Character.valueOf(letter).equals(letra.getLetter())) {
            resultado = this.posicion - letra.posicion;
        } else {
            resultado = this.letter - letra.letter;
        }
        return resultado;
    }
    @Override
    public String toString() {
        String resultado = null;
        switch (this.estado) { //busco que en base del estado devuelva un string distinto u otro.
            case BAD,DESELECT -> resultado = getLetter()+" ("+getEstado().toString()+")";
            case CORRECT,VALID -> resultado = getLetter()+"["+getPosicion()+"] ("+getEstado().toString()+")";
            default -> throw new AssertionError();
        }
        return resultado;
    }

   
}
