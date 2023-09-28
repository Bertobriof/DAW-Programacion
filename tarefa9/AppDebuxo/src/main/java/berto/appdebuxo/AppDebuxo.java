/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package berto.appdebuxo;

import static berto.appdebuxo.ConnectionManager.DB_PASS;
import static berto.appdebuxo.ConnectionManager.DB_URL;
import static berto.appdebuxo.ConnectionManager.DB_USER;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author alber
 */
public class AppDebuxo {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        
        /* Realiza una aplicación que:
- Cree un dibujo de un cuadrado y un rectángulo, le asigne los puntos, y muestre los
dibujos por pantalla (método toString).
- Guarde en dos archivos los dibujos.
- Carga los dibujos de nuevo y los muestre por pantalla. */
        
        //Cuadrado:
        /*
        Point puntoInicial = new Point(0,0);
        Ruta cuadrado = new Ruta(puntoInicial, Color.red, 2);
        cuadrado.addPunto(0, 10);
        cuadrado.addPunto(10,10);
        cuadrado.addPunto(10,0);
        cuadrado.addPunto(0,0);
        
        System.out.println(cuadrado);
       
        //Rectángulo:
        Ruta rectangulo = new Ruta(puntoInicial, Color.blue, 5);
        rectangulo.addPunto(0, 10);
        rectangulo.addPunto(20, 10);
        rectangulo.addPunto(20, 0);
        rectangulo.addPunto(0, 0);

        System.out.println(rectangulo);

        //Guardar dibujos:
        Debuxo debCuadrado = new Debuxo(0, "Cuadrado");
        debCuadrado.addPintable(cuadrado);
        Debuxo debRectangulo = new Debuxo(1,"Rectángulo");
        File fileCuadrado = new File("c:/tprog/cuadrado.obj");
        File fileRectangulo = new File("c:/tprog/rectangulo.obj");
        debCuadrado.savePintablesToFile(fileCuadrado);
        System.out.println("Cuadrado guardado");
        debRectangulo.savePintablesToFile(fileRectangulo);
        System.out.println("Rectángulo guardado");
        Debuxo cargarDatos = new Debuxo();
        try {
            cargarDatos.loadDebuxoFromFile(fileCuadrado);
            System.out.println("Cuadrado cargado\n");
            
            System.out.println(cargarDatos);
            
            cargarDatos.loadDebuxoFromFile(fileRectangulo);
            System.out.println("Rectángulo cargado\n");
            System.out.println(cargarDatos);

        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no se encontró: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Error al cargar el objeto desde el archivo: " + ex.getMessage());
        }*/

        Debuxo cuadrado = cuadrado();
        Debuxo rectangulo = rectangulo();
        //Debuxo rectangulo2 = rectangulo2();
        
        DebuxoDAO debuxoDAO = new DebuxoDAO(DriverManager.getConnection(DB_URL, DB_USER, DB_PASS));
        debuxoDAO.save(cuadrado, Optional.ofNullable(cuadrado.getIdDebuxo()));
        debuxoDAO.save(rectangulo, Optional.ofNullable(rectangulo.getIdDebuxo()));
        
        System.out.println("Están guardados en la base de datos:");
        System.out.println(debuxoDAO.getAll(Optional.ofNullable(cuadrado.getIdDebuxo())).get(0));
        System.out.println(debuxoDAO.getAll(Optional.ofNullable(rectangulo.getIdDebuxo())).get(0));
        
        System.out.println("Actualizamos el rectángulo, modificando los valores. Imprimimos: ");
        rectangulo = rectangulo2();
        debuxoDAO.update(rectangulo);
        try {
                    System.out.println(debuxoDAO.getAll(Optional.ofNullable(rectangulo.getIdDebuxo())).get(0));

        } catch (Exception e) {
            System.out.println("No hay registros. Mensaje de error: "+ e.getMessage());
        }
        
        System.out.println("Borramos el dibujo con ID 1");
        List<Debuxo> dibujos = debuxoDAO.getAll(Optional.ofNullable(cuadrado.getIdDebuxo()));
        debuxoDAO.deleteById(1);
        for (int i = 0; i < dibujos.size() ; i++) {
            System.out.println(dibujos.get(i));
            
        }
        File fileCuadrado = new File("c:/tprog/cuadrado92.obj");
        File fileRectangulo = new File("c:/tprog/rectangulo.obj");
        System.out.println("Guardar dibujo: ");
        cuadrado.saveDebuxoToFile(cuadrado, fileCuadrado);
        
        System.out.println("Cargamos archivo y guardamos en BD:");
        Debuxo cargar = new Debuxo();
        cargar.loadDebuxoFromFile(fileCuadrado);
        debuxoDAO.save(cargar, Optional.ofNullable(cargar.getIdDebuxo()));
        
        
        
        
        
        
    }
    
    public static Debuxo cuadrado() {
        Point puntoInicial = new Point(0,0);
        Ruta cuadrado = new Ruta(puntoInicial, Color.red, 2);
        cuadrado.addPunto(0, 10);
        cuadrado.addPunto(10,10);
        cuadrado.addPunto(10,0);
        cuadrado.addPunto(0,0);
        
        Debuxo debCuadrado = new Debuxo(0,"Cuadrado");
        debCuadrado.addPintable(cuadrado);
        return debCuadrado;
    }
    public static Debuxo  rectangulo() {
        Point puntoInicial = new Point(0,0);
        Ruta rectangulo = new Ruta(puntoInicial, Color.blue, 5);
        rectangulo.addPunto(0, 10);
        rectangulo.addPunto(20, 10);
        rectangulo.addPunto(20, 0);
        rectangulo.addPunto(0, 0);
        Debuxo debRectangulo = new Debuxo(1,"Rectángulo");
        debRectangulo.addPintable(rectangulo);
        return debRectangulo;
    }
        public static Debuxo  rectangulo2() {
        Point puntoInicial = new Point(0,0);
        Ruta rectangulo = new Ruta(puntoInicial, Color.blue, 5);
        rectangulo.addPunto(0, 10);
        rectangulo.addPunto(40, 10);
        rectangulo.addPunto(40, 0);
        rectangulo.addPunto(0, 0);
        Debuxo debRectangulo = new Debuxo(1,"Rectángulo");
        debRectangulo.addPintable(rectangulo);
        return debRectangulo;
    }
}
