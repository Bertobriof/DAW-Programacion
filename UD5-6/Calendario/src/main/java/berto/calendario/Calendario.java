/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package berto.calendario;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;

/**
 *
 * @author alber
 */
public class Calendario {
    private LocalDate dia;
    private int numeroSemanas; 
/*Si el número de semanas es mayor que 6 o menor que 1 debe asignarle el número
  de semanas igual a 6 (valor por defecto), tanto en los constructores como en 
    el método setNumeroSemanas.
*/
    
//Constructores
    public Calendario(int ano,int mes,int diaMes) {
        //creo el Localdate con el prefijo of, que permite crear el objeto con los datos de entrada
        if(mes >=1 && mes <=12) {
            this.dia = LocalDate.of(ano, mes, diaMes);
        }
        this.numeroSemanas = 6; //6 por defecto
    }
    public Calendario (long milisegundos) {
        Instant fechaInstant = Instant.ofEpochMilli(milisegundos);
        ZonedDateTime zdt = fechaInstant.atZone(ZoneId.systemDefault());
        this.dia = zdt.toLocalDate();
        this.numeroSemanas = 6;
    }
    public Calendario (long ano, int mes, int diaMes) {       
        if(mes >=1 && mes <=12) {
            this.dia = LocalDate.of((int) ano, mes, diaMes);
        }
    }
    public Calendario (long ano, int mes, int diaMes, int numeroSemanas) {
        if(mes >=1 && mes <=12) {
            this.dia = LocalDate.of((int) ano, mes, diaMes);
        }
        if(numeroSemanas >6 || numeroSemanas<1) {
            this.numeroSemanas = 6;
        } else {
            this.numeroSemanas = numeroSemanas;
        }
    }
    
    public Calendario (long milisegundos, int numeroSemanas) {
        Instant fechaInstant = Instant.ofEpochMilli(milisegundos);
        ZonedDateTime zdt = fechaInstant.atZone(ZoneId.systemDefault());
        this.dia = zdt.toLocalDate();
        if(numeroSemanas >6 || numeroSemanas<1) {
            this.numeroSemanas = 6;
        } else {
            this.numeroSemanas = numeroSemanas;
        }
    }
   
    
    
//METODOS    
    //MAIN
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            int ano = sc.nextInt();
            int mes = sc.nextInt();
            int dia = sc.nextInt();
            if(sc.hasNextInt()) {
                int semanas = sc.nextInt();
                Calendario calendario = new Calendario(ano, mes, dia, semanas);
                System.out.println(calendario); 
            } else {
                Calendario calendario = new Calendario(ano, mes, dia);
                System.out.println(calendario); 
            }
           sc.close();
            
            
        } catch (Exception e) {
            System.out.println(e);
        }


        
    }
    //FIN MAIN
    
    
    //Getter & Setters:
    public int getAno() {
        return this.dia.getYear();
    }
    public void setAno(int ano) {
        this.dia = this.dia.withYear(ano); //Con withYear cambias el año pero mantienes lo demas
    }
    public int getMes() {
        return this.dia.getMonthValue();
    }
    public void setMes(int mes) {
        if(mes >=1 && mes <=12) {
            this.dia = this.dia.withMonth(mes);
        }
    }
    public int getDia() {
        return this.dia.getDayOfMonth();
    }
    public void setDia(int dia) {
        this.dia = this.dia.withDayOfMonth(dia);
    }
    public void setNumeroSemanas(int numeroSemanas) {
        if(numeroSemanas >6 || numeroSemanas<1) {
            this.numeroSemanas = 6;
        } else {
            this.numeroSemanas = numeroSemanas;
        }        
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(imprimirCalendario()).append(fechaInicioCalendario().getYear()).append("-").append(String.format("%02d", fechaInicioCalendario().getMonthValue())).append("-").append(fechaInicioCalendario().getDayOfMonth());
        return sb.toString();
    }
    
    //metodos para crear el calendario
    private String encabezado() {
        return "lun mar mié jue vie sáb dom";
    }
    private String imprimirSemana(LocalDate fecha) {
        String semana = "";
        for (int i = 0; i < 7; i++) {
            semana += String.format("%3d", fecha.getDayOfMonth()) + " "; //ir imprimiendo el día
            fecha = fecha.plusDays(1); //para que el día vaya cambiando día a dia
        }
        return semana; 
    }
    private LocalDate fechaInicioCalendario() {
        LocalDate fechaInicio;
        fechaInicio = dia.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return fechaInicio;
    }
    private String imprimirCalendario() {
        int contador = 0;
        String calendario = " "+encabezado() + "\n"; //sobre esta variable voy a imprimir todo el calendario
        LocalDate diaInicio = fechaInicioCalendario();
        while (contador < this.numeroSemanas) {            
            calendario += " "+imprimirSemana(diaInicio) + "\n";
            diaInicio = diaInicio.plusDays(7); //mover 7 días la fecha.
            contador++;
        }
        return calendario;
    }
}