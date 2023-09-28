/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author alber
 */
public class DebuxoDAO implements Dao<Debuxo, Integer>{
//Atributo
    private final Connection conexion;
    
//Constructor:
    /**
     * Recoge la conexión y se la asigna al atributo. Dicha conexión se creará con
ConnectionManager cuando necesitemos crear un objeto de acceso a datos.
     * @param con 
     */
    public DebuxoDAO(Connection con) {
        this.conexion = con;
    }
    

    
    @Override
    public List<Debuxo> getAll(Optional<Integer> idDebux) {
        List<Debuxo> debuxos = new ArrayList<>();
        try (Statement stDebuxo = conexion.createStatement();) {
            ResultSet rsDebuxos = stDebuxo.executeQuery("Select " + "idDebuxo, nome FROM Debuxo" +
                    idDebux.map(id->" WHERE idDebuxo="+ id).orElse(""));
            while(rsDebuxos.next()) {
                int idDebuxo = rsDebuxos.getInt(1);
                Debuxo debuxo = new Debuxo(idDebuxo, rsDebuxos.getString("nome"));
                //Collemos os Pintables
                PintableDAO pintableDAO = new PintableDAO(conexion);
                debuxo.setFiguras((ArrayList<Pintable>) pintableDAO.getAll(Optional.of(idDebuxo)));
                debuxos.add(debuxo);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al leer los dibujos: "+ ex.getMessage());
        }
        return debuxos;
    }

    @Override
    public int save(Debuxo debuxo, Optional<Integer> id) {
        int idDebuxo = 0;
        if(debuxo == null) {
            return 0;
        }
        try(PreparedStatement stDebuxo = conexion.prepareStatement("INSERT "+ "INTO Debuxo (nome) VALUES (?)", Statement.RETURN_GENERATED_KEYS);) {
            //Se asigna el nombre del dibujo al parámetro de la consulta:
            stDebuxo.setString(1, debuxo.getNome());
            //Se inserta el dibujo en la base de datos.
            stDebuxo.executeUpdate();
            ResultSet ids = stDebuxo.getGeneratedKeys();
            if(ids.next()) { //Solo hay uno, por eso if y no while
                idDebuxo = ids.getInt(1);
                System.out.println("idDebuxo: "+ idDebuxo);
                debuxo.setIdDebuxo(idDebuxo);
                //Se guardan los pintables del dibujo
                PintableDAO pintableDAO = new PintableDAO(conexion);
                List<Pintable> pintables = debuxo.getFiguras();
                for(Pintable pintable : pintables) {
                    pintableDAO.save(pintable, Optional.of(idDebuxo));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al guardar el dibujo "+ debuxo + "\n"+ ex.getMessage());
        }
        return idDebuxo;

    }
/**
 * igual que el método anterior, pero recogiendo el Debuxo,
por ello, simplemente hace una invocación al anterior pasándole el idDebuxo
 * @param debuxo
 * @return 
 */
    @Override
    public int delete(Debuxo debuxo) {
        if(debuxo == null) {
            return 0;
        }
        return deleteById(debuxo.getIdDebuxo());
    }
/**
 * recoge el idDebuxo y borra el dibujo y los pintables
asociados 
 * @param idDebuxo
 * @return 
 */
    @Override
    public int deleteById(Integer idDebuxo) {
        int borradas = 0;
        try (PreparedStatement stDebuxo = conexion.prepareStatement("DELETE from Debuxo "+ "where idDebuxo=?");){
            //Borramos figuras:
            PintableDAO pintableDAO = new PintableDAO(conexion);
            pintableDAO.deleteById(idDebuxo);
            stDebuxo.setLong(1, idDebuxo);
            borradas = stDebuxo.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar el dibujo con id: "+ idDebuxo+ "\n"+ ex.getMessage());
        }
        return borradas;

    }

    @Override
    public int deleteAll() {
        int r = 0;
        try (Statement st = conexion.createStatement()){
            st.executeUpdate("DELETE FROM Point"); //Aunque en cascada
            st.executeUpdate("DELETE FROM Pintable");
            r = st.executeUpdate("DELETE FROM Debuxo");
        } catch (SQLException ex) {
            System.out.println("Error al borrar los Dibujos: " + ex.getMessage());
        }
        return r;
    }

    @Override
    public boolean update(Debuxo debuxo) {
        int actualizada = 0;
        if(debuxo == null) {
            return false;
        }
        try(PreparedStatement stDebuxo = conexion.prepareStatement("UPDATE "+" Debuxo SET nome = ? WHERE idDebuxo=?");) {
            conexion.setAutoCommit(false);
            int idDebuxo = debuxo.getIdDebuxo();
            //Se signa el nombre del dibujo al parámetro de la consulta.
            stDebuxo.setString(1, debuxo.getNome());
            stDebuxo.setLong(2, debuxo.getIdDebuxo());
            //actualiza el dibujo en la BD.
            actualizada = stDebuxo.executeUpdate();
            if(actualizada != 0) {
                //Borrado de los Pintable de la base de datos:
                PintableDAO pintableDAO = new PintableDAO(conexion);
                pintableDAO.deleteById(debuxo.getIdDebuxo());
                
                ArrayList<Pintable> figuras = debuxo.getFiguras();
                for(Pintable figura : figuras) {
                    pintableDAO.save(figura, Optional.of(idDebuxo));
                }
            }
            conexion.commit();
        } catch (SQLException ex) {
            System.out.println("Error al guardar el círculo "+ debuxo + "\n"+ ex.getMessage());
            try {
                conexion.rollback();
            } catch (Exception e) {
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (Exception e) {
            }
        }
        return actualizada != 0;
    }

    @Override
    public Optional<Debuxo> get(Integer idDebuxo) {
        Debuxo debuxo = null;
        try(Statement stDebuxos
                = conexion.createStatement();) {
            ResultSet rsDebuxos = stDebuxos.executeQuery("Select "+ "nome FROM Debuxo WHERE idDebuxo="+idDebuxo);
            if(rsDebuxos.next()) {
                debuxo = new Debuxo(idDebuxo, rsDebuxos.getString(1));
                //Collemos os Pintable do Debuxo
                PintableDAO pintableDAO = new PintableDAO(conexion);
                debuxo.setFiguras((ArrayList<Pintable>) pintableDAO.getAll(Optional.of(idDebuxo)));
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL "+ ex.getMessage());
        }
        return Optional.ofNullable(debuxo);
    }
    
}
