/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public interface Pintable {
    
    //Color
    public Color getCor();
    public void setCor(Color cor);
    public void setCor(int r, int g, int b);
    
    //Anchura
    public int getWidth();
    public void setWidth(int grosor);
    
    //Figura
    public Shape getShape();
    public String getTipoPintable();
    //Puntos
    public void addPunto(int x,int y);
    public void addPunto(Point p);
    public ArrayList<Point> getPuntos();
    
    //Tarea 9.2:
    //identificador:
    public int getIdPintable();
    public void setIdPintable(int id);
}
