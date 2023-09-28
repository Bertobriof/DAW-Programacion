/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class Ruta extends Path2D.Float implements Pintable  {
//1. Constantes:
    /**
     * Color por defecto para pintar el Path
     */
    public static final Color DEFAULT_COLOR = new Color(6,111,169);
    /**
     * Ancho por defecto en el Path. 
     */
    public static final int DEFAULT_WIDTH = 2;
    
//2. Atributos:
    private Color color;
    private int width;
    //private long idRuta; //Según la práctica, no lo usaremos mucho
    private int idRuta; //Cambio 9.2
//3. Constructores:
    /**
     * Constructor que recoge el punto inicial de la ruta, el color y la anchura
     * @param puntoInicial
     * @param cor
     * @param anchura 
     */
    public Ruta(Point puntoInicial, Color cor, int anchura) {
        this.color = cor;
        this.width = anchura;
        moveTo(puntoInicial.x, puntoInicial.y); //Es un método de Path3D.Float
    }
    
    /**
     * Constructor que recoge el color y la anchura.
     * @param cor
     * @param anchura 
     */
    public Ruta(Color cor, int anchura) {
        this.color = cor;
        this.width = anchura;
    }
    
    /**
     * Constructor que recoge el la anchura.
     * @param anchura 
     */
    public Ruta(int anchura) {
        this.width = anchura;
    }
    /**
     * Constructor por defecto, crea la ruta con la anchura y el color por defecto (constantes). 
     */
    public Ruta() {
        this(DEFAULT_COLOR,DEFAULT_WIDTH);
    }
    /**
     * Constructor que recoge la lista de puntos, el color y el ancho.
     * @param puntos
     * @param cor
     * @param grosor 
     */
    public Ruta(ArrayList<Point> puntos, Color cor, int grosor) {
        this.color = cor;
        this.width = grosor;
        if(puntos != null && !puntos.isEmpty()) {
            moveTo(puntos.get(0).x, puntos.get(0).y); //pone la posicion inicial de los puntos
            for(int i = 1; i< puntos.size(); i++) {
                lineTo(puntos.get(i).x,puntos.get(i).y); //traza la linea de la ruta a medida que recorre el for
            }
        }
    }
    
//4. Métodos:    
    //Métodos heredados de Pintable:

    @Override
    public Color getCor() {
        return this.color;
    }

    @Override
    public void setCor(Color cor) {
        this.color = cor;
    }

    @Override
    public void setCor(int r, int g, int b) {
        this.color = new Color(r,g,b);
    }

    @Override
    public int getWidth() {
        return this.width;
    }
    

    @Override
    public void setWidth(int grosor) {
        this.width = grosor;
    }

    @Override
    public Shape getShape() {
        return this;
    }

    @Override
    public String getTipoPintable() {
        // tarea 9.1 return getClass().getSimpleName();
        return PintableFactory.TipoPintable.RUTA.getTipo();
    }

    /**
     * añade un punto a la ruta. Recoge las coordenadas del punto. Si es el
primer punto a añadir (la posición actual es null en ese caso) hay que mover la ruta
a esa posición, en caso contrario crea una nueva línea.
     * @param x
     * @param y 
     */
    @Override
    public void addPunto(int x, int y) {
        Point2D actual = getCurrentPoint();
        if(actual == null) {
            moveTo(x,y);
        } else {
            lineTo(x,y);
        }
    }
    /**
     * método sobrecargado, recoge un objeto de tipo Point. Invoca al método
anterior. Comprueba que no es nulo antes de llamar al anterior.
     * @param p 
     */
    @Override
    public void addPunto(Point p) {
        if(p != null) {
            addPunto(p.x,p.y);
        }
    }
    /**
     * Devuelve la lista de puntos de la ruta
     * @return 
     */
    @Override
    public ArrayList<Point> getPuntos() {
        ArrayList<Point> puntos = new ArrayList<>();
        float[] coords = new float[6];
        PathIterator pathIterator = getPathIterator(new AffineTransform());
        while(!pathIterator.isDone()) {
            switch (pathIterator.currentSegment(coords)) {
                case PathIterator.SEG_MOVETO-> {
                    puntos.add(new Point((int) coords[0],(int) coords[1]));
                }
                case PathIterator.SEG_LINETO-> {
                    puntos.add(new Point((int) coords[0], (int) coords[1]));
                }
                case PathIterator.SEG_QUADTO-> {
                    puntos.add(new Point((int) coords[0], (int) coords[1]));
                }
                case PathIterator.SEG_CUBICTO-> {
                    puntos.add(new Point((int) coords[0], (int) coords[1]));
                }
                case PathIterator.SEG_CLOSE -> {
                    System.out.println("cerrar\n");
                }
            }
            pathIterator.next();
        }
        return puntos;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Ruta de cor RGB ");
        sb.append(color.getRed()).append(", ").append(color.getGreen()).append(", ").append(color.getBlue()).append(" e anchura ").append(width).append(" pixels. Puntos: ");
        ArrayList<Point> puntos = getPuntos();
        puntos.forEach(punto-> { //expresión lambda (función anónima)
            sb.append('(').append(punto.x).append(", ").append(punto.y).append(')').append(" ");
        });
        return sb.toString();
    }
    /**
     * Devuelve el valor de idRuta.
     * @return 
     */
    @Override
    public int getIdPintable() {
        return idRuta;
    }
    /**
     * Establece el idRuta que nosotros queramos.
     * @param idRuta 
     */
    @Override
    public void setIdPintable(int idRuta) {
        this.idRuta = idRuta;
    }
    
}
