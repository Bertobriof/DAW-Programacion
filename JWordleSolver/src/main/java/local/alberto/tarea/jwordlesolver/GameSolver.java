/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author alber
 */
public abstract class GameSolver {
    //Atributos
    private ArrayList<Solution> soluciones;
    private String nombre;
    
    //Constructor
    public GameSolver(String nombre) {
        this.nombre = nombre;
        this.soluciones = new ArrayList<>();
    }
    
    //Métodos:
    //1.Getters:
    public ArrayList getSoluciones() {
        return soluciones;
    }
    public String getNombre() {
        return nombre;
    }
    //2. Otros:
    public boolean addSolucion(Solution solucion) {
        return soluciones.add(solucion);
    }
    public void clearSolucions() {
        this.soluciones.clear();
    }
    public abstract void solve();
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(soluciones);
        for (Solution solucion : soluciones) {
            sb.append(solucion);
            sb.append("\n");
        }
        
        /*for (int i = 0; i < soluciones.size(); i++) {
            System.out.println(soluciones.get(i));
        }*/
        return sb.toString();
    }
}
