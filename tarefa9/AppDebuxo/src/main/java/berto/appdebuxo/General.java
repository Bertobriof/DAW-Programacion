/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.appdebuxo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author alber
 */
public class General {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
     
        Marco miMarco=new Marco();
        
    }
    
}

class Marco extends JFrame{

    public Marco() {
        
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setSize(600,400);
        
        setLocationRelativeTo(null);
        
        setVisible(true);
        
        add(new Panel());
    }
    
    private class Panel extends JPanel{

        public Panel() {
            
            
        }

        @Override
        protected void paintComponent(Graphics g) {
            
            super.paintComponent(g); 
            
            //clase para pintar las figuras
            
           GeneralPath a=new GeneralPath();
           
           a.moveTo(10, 10);
           
           a.lineTo(20, 10);
           a.lineTo(20, 20);
           a.lineTo(10, 20);
           a.lineTo(10, 10);
           
            Graphics2D pintor=(Graphics2D)g;
            
            pintor.draw(a);
            
        }
        
        
        
    }
    
}