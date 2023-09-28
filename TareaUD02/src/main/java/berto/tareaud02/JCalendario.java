package berto.tareaud02;

import java.lang.*;
import java.io.Console;


/**
 *
 * @author alber
 */

 
 
public class JCalendario {
    /* Apartado 1 enumeraciones. Enumerar meses en galego y mayúsculas
    debe contener el día de cada mes.
    Crear método getDias().    */
    static boolean bisiesto = false;
    static short anho;
    static byte numeroMes;
    static String nomeMes;
    static byte resultadoDia;
            
    public enum Mes {
        XANEIRO (31), //1
        FEBREIRO (28), //29 SI ES BISIESTO
        MARZO (31), //3
        ABRIL (30),
        MAIO (31), //5
        XUÑO (30), //
        XULLO(31), // 7
        AGOSTO (31), // 8
        SETEMBRO (30),
        OUTUBRO (31), //10
        NOVEMBRO (30),
        DECEMBRO (31); //12

   
        private final int dias;
        Mes(int  dias) {
            this.dias = dias;
        }
		
        public int getDias() {
            return dias;
        }
		//ejercicio 4
	public static String getNomeMes(byte numeroMes) {
            byte valor = 1;
            if(numeroMes >=1 && numeroMes <=12) {
                nomeMes = Mes.values()[numeroMes-valor].name();
                return nomeMes;
            }
            else {
                return "Introduce un valor entre o 1 e o 12.";
            }
        }
        //apartado 5
        public static String getDiasMes(byte numeroMes) {
            String resultado;
            if(!bisiesto) {
                switch(numeroMes) {
                    case 1,3,5, 7, 8, 10, 12 -> resultado ="31";
                    case 4,6,9,11 -> resultado = "30";
                    case 2 -> resultado = "28";
                    default -> resultado = "Introduce un número entre el 1 y el 12";
                }
            }
            else {
                switch(numeroMes) {
                    case 1,3, 5, 7, 8, 10, 12 -> resultado ="31";
                    case 4,6,9,11 -> resultado = "30";
                    case 2 -> resultado = "29";
                    default -> resultado = "Introduce un número entre el 1 y el 12";
                }
            }
            return resultado;
        }
       
        public static int getDiasMesEnum(byte numeroMes) {
            int resultado = 0;
            nomeMes = getNomeMes(numeroMes);
            if(!bisiesto) {
                resultado = Mes.valueOf(nomeMes).getDias();
            }
            else {
                switch(nomeMes) {
                    case "XANEIRO", "MARZO", "ABRIL", "MAIO", "XUÑO", "AGOSTO", "SETEMBRO", "OUTUBRO", "NOVEMBRO", "DECEMBRO" -> resultado = Mes.valueOf(nomeMes).getDias();
                    case "FEBREIRO" -> resultado = Mes.valueOf(nomeMes).getDias()+1;
                }
            }
            System.out.println(resultado);
            return resultado;
        }
        

        
    }
    
	
    public static void main(String[] args) {
		
        
        
        Console c = System.console();
                
        if (args == null || args.length != 2) { 
            System.out.print("Introduce o mes: ");
            numeroMes = Byte.parseByte(c.readLine());
            System.out.print("Introduce o ano: ");
            anho = Short.parseShort(c.readLine());
            printCalendario(anho,numeroMes);
        } else {

            numeroMes = Byte.parseByte(args[0]); 
            anho = Short.parseShort(args[1]); 
            printCalendario(anho,numeroMes);
        }
 /*               
		System.out.println("Introduce el dividendo: ");
		int a = Integer.parseInt(c.readLine());
		System.out.println("Introduce divisor: ");
                int b = Integer.parseInt(c.readLine());
		isMultiple(a,b);
                               
		System.out.println("Introduce un ano en formato numerico: ");
		//anho = Short.parseShort(c.readLine());
                anho =  Short.parseShort(c.readLine());

                System.out.println("Introduce un mes [1-12]: ");
		numeroMes = Byte.parseByte(c.readLine());
                nomeMes = Mes.getNomeMes(numeroMes);
                System.out.println(nomeMes);
                isLeapYearAND(anho);
                isLeapYearShift(anho);
                getDiasMes(numeroMes);
				                System.out.println("eunum:");
                getDiasMesEnum(numeroMes);
                System.out.println("Calendario:");
                printCalendario(anho,numeroMes);
                */
        

    }
    
    /*apartado 2 que recoja un número y un divisor, 
devolviendo verdadero cuando el número es divisible por el divisor*/
    public static void  isMultiple(int dividendo, int divisor) {
        //calculamos el modulo del numero
        int valorModulo = dividendo-(dividendo/divisor)*divisor; //calculo el módulo restando al dividendo los números obtenidos por multiplicar el cociente
		if(valorModulo==0) {
			System.out.println(dividendo+" é múltiplo de "+divisor+".");
		} else {
			System.out.println(dividendo+" non é múltiplo de "+divisor+".");
		}		
    }
    //apartado 3 
    public static boolean isLeapYearAND(short anho) {
	if(((anho&3) == 0 && anho%100 != 0 || anho%400 == 0) && anho >= 1583) {
            System.out.println("O ano "+anho+" é bisiesto");
            return bisiesto = true;
	}
	else if (((anho&3) != 0 && anho%100 == 0 || anho%400 !=0)&& anho >= 1583) {
            System.out.println("O ano "+anho+" non é bisiesto");
            return bisiesto = false;
	}
        else {
            System.out.println("Introduce un ano igual ou superior a 1584");
            return bisiesto = false;
        }
    }
    public static void isLeapYearShift(short anho) {
        if((anho == ((anho>>2)<<2) && anho%100 != 0 || anho%400 == 0) && anho >= 1583) {
            System.out.println("O ano "+anho+" é bisiesto");
            bisiesto = true;
        }
        else if ((anho != ((anho>>2)<<2) && anho%100 ==0 || anho%400 != 0) && anho >= 1583) {
            System.out.println("O ano "+anho+" non é bisiesto");
            bisiesto = false;
        }
        else {
            System.out.println("Introduce un ano igual ou superior a 1584");
            bisiesto = false;
        }
    }
    
    //ejercicio 6:
    public static byte getDayOfWeek(short anho,byte numeroMes,byte dia) {
        short varAnho = anho;
        short varE = 0;
        if(numeroMes <3) {
            varAnho = (short) (anho -1); //creo nueva variable para no perder el numeroMes global
        }
        switch(numeroMes) {
            case 1,5 -> varE=0;
            case 2,6 -> varE=3;
            case 3,11 -> varE=2;
            case 4,7 -> varE=5;
            case 8 -> varE=1;
            case 9,12 -> varE=4;
            case 10 -> varE=6;
        }
        short numero = (short) (varAnho + (varAnho/4) - (varAnho/100) + (varAnho/400)+varE+dia);
        resultadoDia = (byte) (numero%7);
        System.out.println("resultado dias " +resultadoDia); //recoge el número de la semana como número
        return resultadoDia;
    }
    
        //ejercicio 7, montar el calendario
    public static void printCalendario(short anho,byte numeroMes) {
        byte numeros = (byte) 0;          
        int contador = 0;
        bisiesto = isLeapYearAND(anho);
        int diasMes = Mes.getDiasMesEnum(numeroMes);
        int inicioMes = getDayOfWeek(anho, numeroMes, (byte) 1);
        nomeMes = Mes.getNomeMes(numeroMes);
        System.out.println(nomeMes+" de "+anho+":");
        
            //encabezado
                for (int i = 1; i <= 7; i++) {
                    System.out.print("------");
            }
            System.out.println("");
            System.out.println("| LU || MA || ME || XO || VE || SA || DO |");
                            for (int i = 1; i <= 7; i++) {
                    System.out.print("------");
            }
            System.out.println("");
   
           
//numeros en blanco
            //cuerpo    
                do {
                    for (int i = 0; i < inicioMes-1; i++) {
                        System.out.print("|    |");
                        contador++;
                    }

                        for (int j = 7; j > inicioMes-1 && contador <7; j--) {
                            numeros=(byte) (numeros+1);
                            System.out.printf("| %02d |",numeros);
                            contador++;
                        }
                } while (contador<7);
            System.out.println("");
            do {
            for (int i = 1; i <= 7; i++) {
                    System.out.print("------");
            }        
            System.out.println("");
            for (int j = 1; j <= 7; j++) {
                if ((contador-inicioMes)<diasMes-1) {
                    numeros=(byte) (numeros+1);
                    System.out.printf("| %02d |",numeros);
                    contador++;
                } else {
                        System.out.print("|    |");
                        contador++;
                }

                }
            System.out.println("");

            }
            while((contador-inicioMes)<=diasMes);
            //separador final
                for (int i = 1; i <= 7; i++) {
                    System.out.print("------");
            }

        }

}

