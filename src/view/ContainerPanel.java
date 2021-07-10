package view;

import structs.NTree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class ContainerPanel extends JPanel {

    private TreePanel treePanel;
    private PanelWord panelWord;
    private GridSystem gridSystem;

    public ContainerPanel(ActionListener l) {
        gridSystem=new GridSystem(this);
        setBorder(new EmptyBorder(10,20,10,20));
        initComponents(l);
    }

    private void initComponents(ActionListener l){
        treePanel=new TreePanel(new NTree());

        this.add(treePanel,gridSystem.insertComponent(0,0,12,9));

        panelWord=new PanelWord(l);
        this.add(panelWord,gridSystem.insertComponent(9,0,12,3));
    }

    public void setTree(NTree<String> tree) {
        treePanel=new TreePanel(tree);
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (treePanel.isVisible())
            treePanel.paintComponent(g);
    }

    public String getWord() throws Exception{
        return panelWord.getWord();
    }
}
