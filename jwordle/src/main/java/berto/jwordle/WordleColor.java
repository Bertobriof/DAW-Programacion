/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package berto.jwordle;

/**
 *
 * @author alber
 */
public enum WordleColor {
    GRAY(120,124,126),
    GREEN(0,170,100),
    RESET(0,0,0),
    VOID(180,180,180),
    YELLOW(201,180,88);
    //atributos
    private final int r;
    private final int g;
    private final int b;
    //constructor
    WordleColor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
    
    //getters
    public int getRed() {
        return r;
    }
    public int getGreen() {
        return g;
    }
    public int getBlue() {
        return b;
    }
    //método de instancia
    public String getAnsiCodeFromRGB()   {
        return "\033[48;2;"+r+";"+g+";"+b+"m";
    }
}