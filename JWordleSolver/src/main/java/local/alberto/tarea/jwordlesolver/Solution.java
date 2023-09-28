/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package local.alberto.tarea.jwordlesolver;

/**
 *
 * @author alber
 */
public abstract class Solution  implements Comparable<Solution>{
//Atributos
    private int valor;
//constructor
        public Solution() {}
//metodos
    public int getValor() {
        return valor;
    }
    protected void setValor(int valor) {
        this.valor = valor;
    }
    public abstract void setValor();
    
    @Override
    public int compareTo(Solution solucion) {
        return this.valor - solucion.valor;
    }
}
