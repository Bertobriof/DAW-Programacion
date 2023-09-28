/*
* Autor: Alberto Rodríguez
*/
package local.albertorodriguezacevedo.tarea01;
/**
* Este programa muestra una serie de líneas de texto.
* @author Berto
*/
public class OlaWittgenstein {
 public static void main(String[] args) {
	 System.out.println("¡No juegues con las profundidades de otro!");
	 System.out.println("Revolucionario será aquel que pueda revolucionarse a sí mismo.");
	 System.out.println("Una proposición sólo puede decir cómo es una cosa, pero no qué es ella.");
	 System.out.println(" Si la gente no hiciera tonterías de vez en cuando, nunca se haría nada inteligente.");
	 Integer idade = Integer.parseInt("40"); 
	 System.out.println("idade = " + idade);
	 Alumno alumno = new Alumno("Xan", 20);
	 System.out.println(alumno.nome + " de " + alumno.idade);
 }
}