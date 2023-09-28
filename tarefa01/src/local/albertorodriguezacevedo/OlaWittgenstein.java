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
        System.out.println("Hello World!");
        System.out.println("!No juegues con la profundidad de otro!");
        System.out.println("Revolucionario es aquel que podrá revolucionarse a si mismo.");
        System.out.println("Una proposición sólo puede decir cómo es una cosa, pero no que és ella.");
        System.out.println("Si la gente no hiciera tonterias de vez en cuando, nunca se haría nada inteligente.");
        Integer idade = Integer.parseInt("43");
        System.out.println("idade = " + idade);
		Alumno alumno = new Alumno("Xan", 20);
		System.out.println(alumno.nome + " de " + alumno.idade);
    }
}