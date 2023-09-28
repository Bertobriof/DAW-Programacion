/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package berto.numero;

/**
 *
 * @author alber
 */
public class Numero {
    
//Atributos
    private Number numero;
//CONSTRUCTORES
    public Numero(Number numero) {
        this.numero = numero;
    }
    public Numero(String cadena) {
        this.numero = Double.parseDouble(cadena);
    }
//ENUM
    public enum TipoNumero {
        BYTE,
        SHORT,
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE,
        UNDEFINED;  
    }
//Método main
    public static void main(String[] args) {

    }
//Fin método main
    
//MÉTODOS
    public Number getNumber() {
        return this.numero;
    }
    public TipoNumero getTipo() {
        switch (this.numero.getClass().getSimpleName() ) { //también podría hacerlo con condicionales e instanceof
            case "Integer":
                return TipoNumero.INTEGER;
            case "Short":
                return TipoNumero.SHORT;
            case "Byte":
                return TipoNumero.BYTE;
            case "Long":
                return TipoNumero.LONG;
            case "Float":
                return TipoNumero.FLOAT;
            case "Double":
                return TipoNumero.DOUBLE;
            default:
                return TipoNumero.UNDEFINED;
        }    
    }
    
    public Number getValor(TipoNumero valor){
        switch (valor) {
            case BYTE:
                return this.numero.byteValue();
            case SHORT:
                return this.numero.shortValue();

            case INTEGER:
                return this.numero.intValue();
            case DOUBLE:
                return this.numero.doubleValue();
            case FLOAT:
                return this.numero.floatValue();
            case LONG:
                return this.numero.longValue();
            default:
                    throw new AssertionError();
        }
    }
    public Numero sqrt() {
        Double raiz = Math.sqrt(this.numero.doubleValue()) ;
        TipoNumero tipo = getTipo(); //tipo original
        Numero resultado = new Numero(raiz);
        return resultado;
    }
    public Numero pow(Numero num) {
        Double powDouble = Math.pow(this.numero.doubleValue(), num.getNumber().doubleValue());
        Numero resultado = new Numero(powDouble);
        return resultado;
    }
    public Numero sum(Numero num) {
        TipoNumero tipoOriginal = getTipo();
        
        Numero resultado1 = new Numero(this.numero.doubleValue() + num.getNumber().doubleValue());
        Numero resultado2 = new Numero(resultado1.getValor(tipoOriginal));
        return resultado2;
    }
    
    @Override
    public String toString() {
        return this.getNumber().toString();
    }
    public void printValues() {
        double doubleValue = this.numero.doubleValue();
        int intValue = this.numero.intValue();
        System.out.println("Double: " + doubleValue);
        System.out.println("Int: " + intValue);
    }
}
