/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author alber
 */
public class PintableFactory {
    //ENUM
    public static enum TipoPintable {
        RUTA("Ruta"), LINEA("Line"), RECANGULO("Rectangle"), RECANGULO_REDONDEADO("RoundRectangle"),
        ELIPSE("Ellipse"), ARCO("Arc"), CURVA_CUADRATICA("QuadCurve"),
        CURVA_CUBICA("Cubiccurve"), CIRCULO("Circle");
        private final String tipo;
        
        private TipoPintable(String tipo) {
            this.tipo = tipo;
        }
        public String getTipo() {
            return tipo;
        }
    }
    //FIN DEL ENUM
    public static Pintable getPintable(String tipoFigura, ArrayList<Point> puntos, Color cor, int anchura) {
        if(tipoFigura == null) {
            return null;
        }
        if(tipoFigura.equalsIgnoreCase(TipoPintable.RUTA.tipo)) {
            return new Ruta(puntos,cor,anchura);
            //Invoca al constructor.
        } else if(tipoFigura.equalsIgnoreCase((TipoPintable.CIRCULO.tipo))) {
            //en un futuro
        } else if(tipoFigura.equalsIgnoreCase((TipoPintable.RECANGULO.tipo))) {
            //en un futuro
        } else {
            //por defecto
        }
        return null;
    }
    
        public static Pintable getPintable(String tipoFigura) {
        if(tipoFigura == null) {
            return null;
        }
        if(tipoFigura.equalsIgnoreCase(TipoPintable.RUTA.tipo)) {
            return new Ruta();
            //Invoca al constructor.
        } else if(tipoFigura.equalsIgnoreCase((TipoPintable.CIRCULO.tipo))) {
            //en un futuro
        } else if(tipoFigura.equalsIgnoreCase((TipoPintable.RECANGULO.tipo))) {
            //en un futuro
        } else {
            //por defecto
        }
        return null;
    }
}
