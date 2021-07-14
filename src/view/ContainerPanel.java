package view;

import structs.NTree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Panel que contienen los paneles de la aplicacion.
 * @version 1
 * @author Grupo
 */
public class ContainerPanel extends JPanel {

    private TreePanel treePanel;
    private PanelWord panelWord;
    private GridSystem gridSystem;

    /** 
     * Constructor.
     * @param l ActionListener de los botones.
     */
    public ContainerPanel(ActionListener l) {
        gridSystem=new GridSystem(this);
        setBorder(new EmptyBorder(10,20,10,20));
        initComponents(l);
    }

    /** 
     * Ejecucion inicial del panel ejecutando 
     * y agregando los paneles al contenedor.
     * @param l ActionListener de los botones.
     */
    private void initComponents(ActionListener l){
        treePanel=new TreePanel(new NTree());

        this.add(treePanel,gridSystem.insertComponent(0,0,12,9));

        panelWord=new PanelWord(l);
        this.add(panelWord,gridSystem.insertComponent(9,0,12,3));
    }


    /** 
     * Metodo que actualiza el arbol en el panel
     * @param tree Arbol creado con NTree el cual es un arbol NArio.
     */
    public void setTree(NTree<String> tree) {
        treePanel=new TreePanel(tree);
        repaint();
    }

 /** 
     * Pinta Panel
     *  @Override paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (treePanel.isVisible())
            treePanel.paintComponent(g);
    }

   /** 
     * Metodo que retorna una palabra.
     * @return String con la palabra dentro de el.
     */
    public String getWord() throws Exception{
        return panelWord.getWord();
    }
}
