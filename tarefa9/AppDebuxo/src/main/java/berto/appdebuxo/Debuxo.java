/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alber
 */
public class Debuxo {
//1. Constantes:
    public static final String DEFAULT_NAME = "Dibujo.png";
    public static final char LINE_COMMENT = '#';
//2. Atributos:
    private ArrayList<Pintable> figuras;
    private String nome;
    private Integer idDebuxo; // Antes: private long idDebuxo;
//3. Constructores:
    /**
     * Constructor que recoge idDebuxo y nombre.
     * @param idDebuxo
     * @param nome 
     */
    public Debuxo(Integer idDebuxo, String nome) {
        figuras = new ArrayList<>();
        this.idDebuxo=idDebuxo;
        this.nome = nome;
    }
    /**
     * Constructor que recoge nombre únicamente.
     * @param nome 
     */
    public Debuxo(String nome) {
        this(0,nome);
    }
    /**
     * Constructor por defecto, aplica el nombre por defecto.
     */
    public Debuxo() {
        this(DEFAULT_NAME);
    }
//4. Métodos:

    public String getNome() {
        return nome;
    }
    /**
     * Devuelve el idDebuxo
     * @return 
     */
    public Integer getIdDebuxo() {
        return idDebuxo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdDebuxo(int idDebuxo) {
        this.idDebuxo = idDebuxo;
    }
    /**
     * Limpia la lista de figuras si esta contiene algún dato.
     */
    public void clear() {
        if(figuras != null) {
            figuras.clear();
        }
    }
    /**
     * Devuelve la lista de Shapes de dibujo.
     * @return 
     */
    public ArrayList<Shape> getShapes() {
        ArrayList<Shape> shapes = new ArrayList<>();
        for (Pintable figura : figuras) {
            shapes.add(figura.getShape());
        }
        return shapes;
    }
    /**
     * Devuelve la lista de figuras Pintable de dibujo.
     * @return 
     */
    public ArrayList<Pintable> getFiguras() {
        return figuras;
    }

    public void setFiguras(ArrayList<Pintable> figuras) {
        this.figuras = figuras;
    }
    
    /**
     * Devuelve la lista de colores de cada figura del dibujos.
     * @return 
     */
    public ArrayList<Color> getColors() {
        ArrayList<Color> cores = new ArrayList<>();
        for (Pintable p : figuras) {
            cores.add(p.getCor());
        }
        return cores;
    }
    /**
     * Devuelve un listado de Integer con las anchuras asociadas a cada figura.
     * @return 
     */
    public ArrayList<Integer> getWidths() {
        ArrayList<Integer> grosores = new ArrayList<>();
        for (Pintable figura : figuras) {
            grosores.add(figura.getWidth());
        }
        return grosores;
    }
    /**
     * Devuelve la figura que está en la posición i si el valor de i está dentro de los valores permitidos.
     * @param i
     * @return 
     */
    public Shape getShape(int i) {
        if(figuras != null && i>=0 && i<figuras.size()) {
            return figuras.get(i).getShape();
        }
        return null;
    }
    /**
     * Añade un pintable al dibujo.
     * @param figura 
     */
    public void addPintable(Pintable figura) {
        if(figuras != null) {
            figuras.add(figura);
        }
    }
    /**
     * Añade una figura pintable recogiendo el tipo e invocando al PintableFactory. Permite añadir cualquier figura pasándole los puntos, el color y la anchura. Se encarga la clase Factory en crearla.
     * @param tipo
     * @param puntos
     * @param cor
     * @param grosor 
     */
    public void addPintable(String tipo, ArrayList<Point> puntos, Color cor, int grosor) {
        if(figuras != null) {
            figuras.add(PintableFactory.getPintable(tipo, puntos, cor, grosor));
        }
    }
    /**
     * Compara que dos objetos sean iguales.
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if(this == obj) { //Todo objeto es igual a si mismo
            return true;
        }
        final Debuxo outro = (Debuxo) obj;
        return this.idDebuxo == outro.idDebuxo;
    }
    /**
     * Dos objetos con el mismo idDebuxo devolverán el mismo hashCode.
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13*hash+(int) (this.idDebuxo ^ (this.idDebuxo >>> 32));
        return hash;
    }
    /**
     * Devuelve una cadena con el nombre del dibujo, su idDibujo y la lista de rutas.
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(nome);
        sb.append(" [").append(idDebuxo).append("]: ").append(System.lineSeparator());
        figuras.forEach(figura -> {
            sb.append(figura).append(System.lineSeparator());
        });
        return sb.toString();
    }
    /**
     * recoge el archivo (File) y lee el dibujo de un archivo de texto
en el que se guardan el nombre del dibujo y los datos de los objetos Pintable, en las
que para cada una se guarda el tipo de Pintable, la anchura, el color (como
valores RGB) y la lista de puntos.
     * @param f
     * @throws IOException 
     */
    public Debuxo loadDebuxoFromFile(File f) throws IOException {
        Debuxo d = new Debuxo();
        //Cargo los ppuntos desde un archivo:
        try (BufferedReader br = new BufferedReader(new FileReader(f))){
            //Creamos la colección de figuras:
            ArrayList<Pintable> pintables = new ArrayList<>();
            Pintable pintable = null;
            boolean nameReaded = false;
            String linha;
            while((linha = br.readLine()) != null) {
                linha = linha.trim(); //borramos espacios de la línea
                if(linha.length() >0 && (linha.charAt(0) != LINE_COMMENT)) {
                    if(!nameReaded) { //Primera línea en el comentario es el nombre.
                        //nome = linha;
                        d.setNome(linha);
                        nameReaded = true;
                    } else {
                        //Ahora todo son figuras pintables.
                        String[] campos = linha.split(",");
                        switch (campos.length) {
                            case 1 -> { //No tiene comas -> tipo o anchura
                                try {
                                    int anchura = Integer.parseInt((linha));
                                    if(pintable != null) {
                                        pintable.setWidth(anchura);
                                        System.out.println("Anchura "+ Integer.valueOf(linha));
                                    }
                                } catch (NumberFormatException e) {
                                    pintable = PintableFactory.getPintable(linha);
                                    pintables.add(pintable);
                                    System.out.println("Tipo: "+ linha); 
                                }
                            }
                            case 3 -> { //Cor tiene 3 campos separados con comas
                                if(pintable != null) {
                                    pintable.setCor(
                                    new Color(Integer.parseInt(campos[0].trim()),
                                            Integer.parseInt(campos[1].trim()),
                                            Integer.parseInt(campos[2].trim())));
                                }
                            }
                            case 2 -> { //puntos de la figura pintable
                                if(pintable != null) {
                                    pintable.addPunto(Integer.parseInt(
                                            campos[0].trim()),
                                            Integer.parseInt(campos[1].trim())
                                    );
                                }
                                
                            }
                        }
                    }
                }
            }
            //Añadimos las figuras al dibujo, sin borrar las que tenia
            //figuras.addAll(pintables); Novo:
            d.setFiguras(figuras);
            
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo non atopado: " + ex.getMessage());
            return null;
        }
        catch (IOException ex) {
            System.out.println("Erro E/S: "+ ex.getMessage());
            return null;
        }
        return d;
    }
    /**
     *  recoge el archivo (File) y escribe el dibujo de un archivo de texto
en el que se guardan el nombre del dibujo y las figuras, en las que para cada una
se guarda el tipo, la anchura, el color (como valores RGB) y la lista de puntos
     * @param f 
     */
    public void savePintablesToFile(File f) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(LINE_COMMENT + " Nome debuxo:" + System.lineSeparator());
            bw.write(nome + System.lineSeparator());
            int i = 0;
            for(var figura : figuras) { //Variable local: puede ser "var".
                bw.write(LINE_COMMENT + " Tipo pintable "+ (++i) + ":" + System.lineSeparator());
                bw.write(figura.getTipoPintable()+System.lineSeparator());
                bw.write(LINE_COMMENT + " Anchura " + (++i) + ":" + System.lineSeparator());
                bw.write(figura.getWidth() + System.lineSeparator());
                Color cor = figura.getCor();
                bw.write(LINE_COMMENT + " Cor "+(i) + ":"+System.lineSeparator());
                bw.write(cor.getRed() + ","+cor.getGreen()+","+cor.getBlue() + System.lineSeparator());
                bw.write(LINE_COMMENT+" Puntos "+(i)+":"+System.lineSeparator());
                ArrayList<Point> puntos = figura.getPuntos();
                for (Point punto : puntos) {
                    bw.write(punto.x+","+punto.y+System.lineSeparator());
                }
            }
        } catch(IOException ex) {
            System.out.println("Erro de E/S:" + ex.getMessage());
        }
    }
    /**
     * Recoge un archivo y guarda el objeto es ese archivo binario
     * @param f
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveObjectToFile(File f) throws FileNotFoundException, IOException {
        try(ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f))
        );) {
            oos.writeObject(this);
        } catch (IOException ex) {
            System.out.println("Erro de E/S: " + ex.getMessage());
        }
    }
    
    public void loadObjectFromFile(File f) throws FileNotFoundException {
        try(ObjectInputStream oos = new ObjectInputStream(new BufferedInputStream(new FileInputStream(f)));) {
            Debuxo debuxo = (Debuxo) oos.readObject();
            if(debuxo != null) {
                this.nome = debuxo.getNome();
                this.idDebuxo = debuxo.getIdDebuxo();
                figuras.clear();
                //figuras.addAll(debuxo.getShapes());
                //Error: private ArrayList<Pintable> figuras;
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Erro ó cargar o debuxo: "+ ex.getMessage());
        }
    }
    
    public void saveDebuxoToFile(Debuxo d, File f) {
        if(d==null) {
            return;
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(LINE_COMMENT+" Nome debuxo:"+System.lineSeparator());
            bw.write(getNome()+System.lineSeparator());
            int i = 0;
            List<Pintable> figuras = d.getFiguras();
            for(var figura : figuras) {
                bw.write(LINE_COMMENT + " Tipo pintable "+ (++i) + ":" + System.lineSeparator());
                bw.write(figura.getTipoPintable()+System.lineSeparator());
                bw.write(LINE_COMMENT + " Anchura " + (++i) + ":" + System.lineSeparator());
                bw.write(figura.getWidth() + System.lineSeparator());
                Color cor = figura.getCor();
                bw.write(LINE_COMMENT + " Cor "+(i) + ":"+System.lineSeparator());
                bw.write(cor.getRed() + ","+cor.getGreen()+","+cor.getBlue() + System.lineSeparator());
                bw.write(LINE_COMMENT+" Puntos "+(i)+":"+System.lineSeparator());
                ArrayList<Point> puntos = figura.getPuntos();
                for (Point punto : puntos) {
                    bw.write(punto.x+","+punto.y+System.lineSeparator());
                }
            }
        } catch(IOException ex) {
            System.out.println("Erro de E/S:" + ex.getMessage());
        }
    }
}
