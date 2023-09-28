/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package berto.tarefa0801;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author alber
 */
public class VisorAlberto extends JFrame implements ActionListener, ChangeListener, WindowListener{
//Constantes de clase Visor
    public static final ImageIcon ICO_VISOR;
    
    //¿serías capaz de capturar la excepción en el caso de que el icono no exista?
    //No puedo usar un try catch sobre la constante, pero si que puedo usar un bloque static y un bloque de inicialización.
    static {
        ImageIcon icon;
        try {
            icon = new ImageIcon(VisorAlberto.class.getResource("/imaxes/image.png"));
        } catch (NullPointerException e) {
            System.out.println("Imagen no disponible");
            icon = null;
        }
        ICO_VISOR = icon;
    }
    public static final int DEFAULT_ANCHO_IMAGEN = 800;
    public static final int DEFAULT_ALTO_IMAGEN = 400;
    public static final String RUTA_POR_DEFECTO = "C:\\TEMP";
    
//Atributos
    private JLabel etiquetaImaxe;
    private JMenuItem mnuAbrir,
            mnuPechar,
            mnuGardar,
            mnuSalir,
            mnuAbout;
    private ImageIcon imaxe;
    private JSlider jsZoom;
    private JFileChooser fcArquivo;
    
    
    //constructor
    public VisorAlberto(String titulo) throws HeadlessException {
        super(titulo);
        
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        if(ICO_VISOR != null) {
            setIconImage(ICO_VISOR.getImage());
        }
        JMenuBar mb = new JMenuBar(); //Crear barra de menú
        JMenu mnuArquivo = new JMenu("Archivo");
        JMenu menuAxuda = new JMenu("Ayuda");
        
        
        
        //Menú de ayuda:
        this.mnuAbrir = new JMenuItem("Abrir");
        mnuAbrir.addActionListener(this);
        mnuArquivo.add(mnuAbrir);
        this.mnuPechar = new JMenu("Pechar");
        mnuPechar.addActionListener(this);
        mnuArquivo.add(mnuPechar);
        mnuArquivo.addSeparator();
        this.mnuGardar = new JMenu("Guardar");
        mnuArquivo.add(mnuGardar);
        
        mnuGardar.addActionListener(this);
        mnuArquivo.addSeparator();
        this.mnuSalir = new JMenu("Salir");
        
        mnuSalir.addActionListener(this);
        mnuArquivo.add(mnuSalir);
        
        this.mnuAbout = new JMenu("Acerca de...");
        menuAxuda.add(mnuAbout);
        
        
        
        
        
        
        
        
        mb.add(mnuArquivo);
        mb.add(menuAxuda);
        
        //Asignar la barra al JFrame
        setJMenuBar(mb);
        this.etiquetaImaxe = new JLabel(ICO_VISOR, JLabel.CENTER);
        etiquetaImaxe.setPreferredSize(new Dimension(DEFAULT_ANCHO_IMAGEN,DEFAULT_ALTO_IMAGEN));
        JScrollPane panelCentral = new JScrollPane(etiquetaImaxe);
        JPanel centro = new JPanel();
        centro.add(panelCentral);
        add(panelCentral);
        
        JLabel lbZoom = new JLabel("Zoom",JLabel.CENTER);
        jsZoom = new JSlider(JSlider.HORIZONTAL,1,1000,100);
        jsZoom.addChangeListener(this);
        
        JPanel pSur = new JPanel();
        pSur.add(lbZoom);
        pSur.add(jsZoom);
        add(pSur, BorderLayout.PAGE_END);
        
        //Selector de archivos
        fcArquivo = new JFileChooser(RUTA_POR_DEFECTO);
        fcArquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtroImaxes = new FileNameExtensionFilter(
                "Imaxes JPG, GIF e PNG", "jpg","gif","png");
        fcArquivo.setFileFilter(filtroImaxes);
        setEnabledControls(false);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sair();
            }
        });
        
        //apartado 10
        
        
        
        
        
        //apartado 11:
        jsZoom.addChangeListener(this);
        
        //12:
        
    }
    
    private void setEnabledControls(boolean habilitar) {
        jsZoom.setEnabled(habilitar);
        jsZoom.setValue(100);
        mnuGardar.setEnabled(habilitar);
        mnuPechar.setEnabled(habilitar);
    }
    
    private void sair() {
        ImageIcon iconoSalir = new ImageIcon(getClass().getResource("/images/3937040.png"));
        int valor = JOptionPane.showConfirmDialog(this, "¿Quieres salir?",
                "Salir del visor",JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,iconoSalir);
        if(valor == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
    
    private void setPreferredSizeImaxe() {
        if(imaxe != null) {
            etiquetaImaxe.setPreferredSize(
            new Dimension(imaxe.getIconWidth(),imaxe.getIconHeight()));
        }
    }
    
    private void zoomImaxe(int valor) {
        if(imaxe != null) {
            double valorP = ((double) valor)/100; //porcentaje
            Image img = ((ImageIcon) imaxe).getImage();
            int nuevoAncho = (int) Math.round(imaxe.getIconWidth()*valorP);
            int nuevoAlto = (int) Math.round(imaxe.getIconHeight()*valorP);
            if(nuevoAncho != 0 && nuevoAlto != 0) {
                Image nova = img.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
                etiquetaImaxe.setIcon(new ImageIcon(nova));
            }
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object objeto = e.getSource();
        if(objeto == mnuAbrir) {
            if(fcArquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                imaxe = new ImageIcon(fcArquivo.getSelectedFile().getPath());
                etiquetaImaxe.setIcon(imaxe);
                setPreferredSizeImaxe();
                setEnabledControls(true);
            }
        } else if (objeto == mnuPechar) {
            etiquetaImaxe.setIcon(ICO_VISOR);
            imaxe = null;
            setEnabledControls(false);
        } else if (objeto == mnuSalir) {
            sair();
        } else if(objeto == mnuAbout) {
            JOptionPane.showMessageDialog(this, "Visor de programación v0.0", "Visor de Alberto", JOptionPane.INFORMATION_MESSAGE);
        } else if(objeto == mnuGardar) {          
            //a Obtener la imagen de la etiquetaImaxe:
            ImageIcon imgIcon = (ImageIcon) etiquetaImaxe.getIcon();
            BufferedImage bi = new BufferedImage(imgIcon.getIconWidth(), imgIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            //c:
            Graphics g = bi.getGraphics();
            Image img = imgIcon.getImage();
            g.drawImage(img, 0, 0, null);
            File archivoSalida = new File("C:\\TEMP/archivo.png");
            try {
                ImageIO.write(bi, "png", archivoSalida);
                JOptionPane.showMessageDialog(null, "Imagen guardada correctamente");
            } catch (IOException ex) {
                Logger.getLogger(VisorAlberto.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        if (imaxe != null && !slider.getValueIsAdjusting()) {
            int valor = (int) slider.getValue();
            if (valor != 0) {
                zoomImaxe(valor);
                if (etiquetaImaxe.getIcon() != null) {
                    etiquetaImaxe.setPreferredSize(
                            new Dimension(etiquetaImaxe.getIcon().getIconWidth(),
                                    etiquetaImaxe.getIcon().getIconHeight()));
                }
            }
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowClosing(WindowEvent e) {
       sair(); 
    }

    @Override
    public void windowClosed(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowIconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowActivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
