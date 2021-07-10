package view;

import structs.NTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class IOManager extends JFrame {

    private ContainerPanel containerPanel;
    private PanelPrincipal panelPrincipal;


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

    private void initComponents(ActionListener l){
        this.containerPanel=new ContainerPanel(l);
        this.panelPrincipal=new PanelPrincipal(l);
        this.add(panelPrincipal,BorderLayout.CENTER);
    }

    public void setTree(NTree<String> tree){
        this.containerPanel.setTree(tree);
        repaint();
    }

    public void showMessage(String message, String title){
        JOptionPane.showMessageDialog(null,message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public void showError(String message, String title){
        JOptionPane.showMessageDialog(null,message, title, JOptionPane.ERROR_MESSAGE);
    }


    public ArrayList<Character> getAlphabet() throws Exception{
        return panelPrincipal.getAlphabet();
    }

    public ArrayList<Character> getNotTerminals() throws  Exception{
        return panelPrincipal.getNotTerminals();
    }

    public char getAxioma() throws Exception{
        return panelPrincipal.getAxioma();
    }

    public ArrayList<String> getProccesses() throws Exception{
        return panelPrincipal.getProccesses();
    }

    public void showContainerPanel() {
        panelPrincipal.clear();
        this.panelPrincipal.setVisible(false);

        this.containerPanel.setVisible(true);
        this.add(containerPanel, BorderLayout.CENTER);
    }

    public String getWord() throws Exception{
        return this.containerPanel.getWord();
    }
}
