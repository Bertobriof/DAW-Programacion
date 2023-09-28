/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Color;
import java.util.List;
import java.awt.Point;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;


/**
 *
 * @author alber
 */
public class PintableDAO implements Dao<Pintable, Integer> {
//Atributos:
    private final Connection conexion;
    
//Constructor:
    public PintableDAO(Connection conexion) {
        this.conexion = conexion;
    }
    
    
    
    
    @Override
    public List<Pintable> getAll(Optional<Integer> idDebuxo) {
        List<Pintable> pintables = new ArrayList<>();
        try (Statement stPintable
                = conexion.createStatement(); Statement stPoint = conexion.createStatement()){
            ResultSet rsPintable = stPintable.executeQuery("SELECT * FROM"+" Pintable"+ idDebuxo.map(id -> " WHERE idDebuxo="+ id).orElse(""));
            while(rsPintable.next()) {
                ArrayList<Point> puntos = new ArrayList<>();
                int idPintable = rsPintable.getInt("idPintable");
                //Los puntos para cada Pintable.
                ResultSet rsPoint = stPoint.executeQuery("SELECT x, y FROM"+ " Point WHERE idPintable="+ idPintable);
                while(rsPoint.next()) {
                    puntos.add(new Point(rsPoint.getInt(1), rsPoint.getInt(2)));
                }
                Pintable pintable = PintableFactory.getPintable(
                        rsPintable.getString("tipo"),
                        puntos, new Color(rsPintable.getInt("red"),rsPintable.getInt("green"),rsPintable.getInt("blue")), rsPintable.getInt("width"));
                pintables.add(pintable);
            }
        } catch (SQLException ex) {
            System.out.println("Error al cargar Pintables: "+ ex.getMessage());
        }
        return pintables;
    }

    @Override
    public int save(Pintable t, Optional<Integer> idDebuxoFK) {
        int idPintable = 0;
        if(t == null || idDebuxoFK.isEmpty()) {
            System.out.println("Nulo ou baleiro");
            return 0;
        }
        try(PreparedStatement psPintable = conexion.prepareStatement("INSERT INTO Pintable" + " (idDebuxo, tipo, width, red, green, blue)"+" VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS); PreparedStatement psPuntos = conexion.prepareStatement("INSERT INTO Point" + " (idPintable, x, y)" + " VALUES (?, ?, ?)");) {
            psPintable.setInt(1,idDebuxoFK.get());
            psPintable.setString(2, t.getTipoPintable());
            psPintable.setInt(3, t.getWidth());
            psPintable.setInt(4, t.getCor().getRed());
            psPintable.setInt(5, t.getCor().getGreen());
            psPintable.setInt(6, t.getCor().getBlue());
            psPintable.executeUpdate();
            
            //Cursor de idPintable generado (Será uno)
            ResultSet ids = psPintable.getGeneratedKeys();
            if(ids.next()) { // Solo hay un nuev pintable, por eso "if"
                idPintable = ids.getInt(1); //Empieza en 1
                ArrayList<Point> puntos = t.getPuntos();
                for (Point punto : puntos) {
                    psPuntos.setInt(1, idPintable);
                    psPuntos.setInt(2, punto.x);
                    psPuntos.setInt(3, punto.y);
                    psPuntos.executeUpdate();
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Erro ó gardar o pintable" + ex.getMessage());
        }
        return idPintable;
    }
/**
 * recoge el objeto Pintable a borrar. Aunque es el borrado en
cascada, es mejor borrar antes los puntos asociados.
 * @param t
 * @return 
 */
    @Override
    public int delete(Pintable t) {
        int r = 0;
        try(Statement st = conexion.createStatement()) {
            // Borra los Puntos en cascada
            r = st.executeUpdate("DELETE FROM Point WHERE idPintable="+t.getIdPintable());
            r = st.executeUpdate("DELETE FROM Pintable WHERE idPintable="+t.getTipoPintable());
        } catch (SQLException ex) {
            System.out.println("Erro ó borrar os Pintables con id: " + ex.getMessage());
        }
        return r;
    }
    
    /**
     * recoge el idDebuxo de los Pintables asociados y los
borra en cascada.
     * @param idDebuxo
     * @return 
     */
    @Override
    public int deleteById(Integer idDebuxo) {
        int r = 0;
        try(Statement st = conexion.createStatement()) {
            st.executeUpdate("DELETE FROM Pintable WHERE idDebuxo="+idDebuxo);
        } catch (SQLException ex) {
            System.out.println("Erro ó borrar os Pintables do debuxo con id: "+ idDebuxo + ex.getMessage());
        }
        return r;
    }
    /**
     * borra todos los Pintable.
     * @return 
     */
    @Override
    public int deleteAll() {
        int r = 0;
        try(Statement st = conexion.createStatement()) {
            st.executeLargeUpdate("DELETE FORM Point"); // Aunque es cascaa...
            r = st.executeUpdate("DELETE FROM Pintable");
        }catch(SQLException ex) {
            System.out.println("Error al borrar los Pintable: "+ex.getMessage());
        }
        return r;
    }
    
    

    @Override
    public boolean update(Pintable t) {
        int actualizada = 0;
        if(t == null) {
            return false;
        }
        try (PreparedStatement psPintable = conexion.prepareStatement("UPDATE "
            +" Pintable SET tipo=?, width =?, red=?, green=?,blue=? "); Statement st = conexion.createStatement();) {
            int idPintable = t.getIdPintable();
            psPintable.setString(1, t.getTipoPintable());
            psPintable.setInt(2, t.getWidth());
            psPintable.setInt(3, t.getCor().getRed());
            psPintable.setInt(4, t.getCor().getGreen());
            psPintable.setInt(5, t.getCor().getBlue());
            psPintable.setInt(6, idPintable);
            
            actualizada = psPintable.executeUpdate();
            
            if(actualizada != 0) {
                //Borrado de los Puntos de la base de datos:
                st.executeUpdate("DELETE FROM Point");
                //Nuevos puntos
                ArrayList<Point> puntos = t.getPuntos();
                for (Point punto : puntos) {
                    st.executeQuery("INSET INTO Point" + " (idPintable, x, y)"
                            + " VALUES ("+ idPintable + ", "
                            + punto.y + ", " + punto.y + ")");
                }
            }
            
        } catch (Exception ex) {
            System.out.println(t+ ". Error al actualizar el pintable: "+ ex.getMessage());
        }
        return actualizada != 0;
    }

    @Override
    public Optional<Pintable> get(Integer idPintable) {
        Pintable pintable = null;
        try (Statement stPintable = conexion.createStatement(); Statement stPoint = conexion.createStatement()) {
            ResultSet rsPintable = stPintable.executeQuery("SELECT * FROM"+ " Pintable WHERE idPintable="+ idPintable);
            
            if(rsPintable.next()) { //Sólo hay uno
                ArrayList<Point> puntos = new ArrayList<>();
                // Los puntos para cada Pintable.
                ResultSet rsPoint = stPoint.executeQuery("SELECT x, y FROM"+ "Point WHERE idPintable="+ idPintable);
                while (rsPoint.next()) {
                    puntos.add(new Point(rsPoint.getInt(1), rsPoint.getInt(2)));
                }
                pintable = PintableFactory.getPintable(rsPintable.getString("Tipo"),puntos, new Color(rsPintable.getInt("red"),
                        rsPintable.getInt("green"),
                        rsPintable.getInt("blue")),
                        rsPintable.getInt("width"));
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL: "+ ex.getMessage());
        }
        return Optional.ofNullable(pintable);
    }
    
}
