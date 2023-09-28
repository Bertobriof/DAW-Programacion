/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author alber
 */
public interface Dao<T, K> {
    List<T> getAll(Optional<K> idClaveForanea);
    
    int save(T t, Optional<K> idClaveForanea);
    
    int delete(T t);
    
    public int deleteById(K id);
    
    int deleteAll();
    
    boolean update(T t);
    
    public Optional<T> get(K tt);
    
}
