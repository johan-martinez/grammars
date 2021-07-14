package view;

import structs.NTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Panel IoManager.
 * @version 1
 * @author Grupo
 */
public class IOManager extends JFrame {

    private ContainerPanel containerPanel;
    private PanelPrincipal panelPrincipal;


 /** 
     * Constructor.
     * @param l ActionListener de los botones.
     */
    public IOManager(ActionListener l){
        super();
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setTitle("GRAMMAR");
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents(l);
        this.setVisible(true);
    }


    /** 
     * Ejecucion inicial del panel ejecutando 
     * y agregando los paneles al contenedor.
     * @param l ActionListener de los botones.
     */
    private void initComponents(ActionListener l){
        this.containerPanel=new ContainerPanel(l);
        this.panelPrincipal=new PanelPrincipal(l);
        this.add(panelPrincipal,BorderLayout.CENTER);
    }


    /** 
     * Metodo que actualiza el arbol en el panel
     * @param tree Arbol creado con NTree el cual es un arbol NArio.
     */
    public void setTree(NTree<String> tree){
        this.containerPanel.setTree(tree);
        repaint();
    }

    /** 
     * Metodo que realiza una alerta de cualquier tipo.
     * @param  title con el titulo de la informacio que se quiere dar.
     * @param  message con la informacion a notificar.
     */
    public void showMessage(String message, String title){
        JOptionPane.showMessageDialog(null,message, title, JOptionPane.INFORMATION_MESSAGE);
    }


    /** 
     * Metodo que realiza una alerta de  error.
     * @param  title con el titulo de la informacion que se quiere dar.
     * @param  message con la informacion a notificar.
     */
    public void showError(String message, String title){
        JOptionPane.showMessageDialog(null,message, title, JOptionPane.ERROR_MESSAGE);
    }


/** 
     * Metodo que realiza una alerta de  error.
     * @param  title con el titulo de la informacion que se quiere dar.
     * @param  message con la informacion a notificar.
     */
    public ArrayList<Character> getAlphabet() throws Exception{
        return panelPrincipal.getAlphabet();
    }

/** 
     * Metodo que realiza una alerta de  error.
     * @param  title con el titulo de la informacion que se quiere dar.
     * @param  message con la informacion a notificar.
     */
    public ArrayList<Character> getNotTerminals() throws  Exception{
        return panelPrincipal.getNotTerminals();
    }


    /** 
     * Retorna el axioma del panel.
     * @return un char con el axioma proveniente del panelPrincipal.
    */
    public char getAxioma() throws Exception{
        return panelPrincipal.getAxioma();
    }

   /** 
     * Retorna El proceso del panel principal.
     * @return una lista ordenadamente con el groceso.
    */
    public ArrayList<String> getProccesses() throws Exception{
        return panelPrincipal.getProccesses();
    }

    /** 
     * Agrega el panel contenedor al jFrame.
    */
    public void showContainerPanel() {
        panelPrincipal.clear();
        this.panelPrincipal.setVisible(false);

        this.containerPanel.setVisible(true);
        this.add(containerPanel, BorderLayout.CENTER);
    }

    /* 
     * Metodo que retorna una palabra.
     * @return String con la palabra dentro de el.
     */
    public String getWord() throws Exception{
        return this.containerPanel.getWord();
    }
}
