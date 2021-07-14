package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Panel principal donde se ejecutan los arboles.
 * @version 1
 * @author Grupo
 */


public class PanelPrincipal extends JPanel {

    private JLabel labelTitle,labelText, labelAlphabet, labelNotTerminals, labelAxioma, labelProcceses;
    private JTextArea areaAlphabet, areaNotTerminals, areaProcceses;
    private JTextField textAxioma;
    private JButton buttonConfirm;
    private GridSystem gridSystem;

     /** 
     * Constructor.
     * @param l ActionListener de los botones.
     */

    public PanelPrincipal(ActionListener l) {
        this.gridSystem=new GridSystem(this);
        setBorder(new EmptyBorder(10,20,10,20));
        initComponents();
        setActionsCommands(l);
    }

    /** 
     * Ejecucion inicial del panel ejecutando 
     * y agregando los paneles al contenedor.
     * @param l ActionListener de los botones.
     */
    private void initComponents() {
        this.labelTitle=new JLabel("GRAMÁTICAS",SwingConstants.CENTER);
        this.labelTitle.setHorizontalTextPosition(JLabel.CENTER);
        this.add(labelTitle, gridSystem.insertComponent(0,5,2,1));

        this.labelText=new JLabel("Para el caracter vacío utilice (*)");
        this.add(labelText, gridSystem.insertComponent(1,0,10,1));

        this.labelAlphabet=new JLabel("Alfabeto:");
        this.add(labelAlphabet, gridSystem.insertComponent(2,0,1,1));

        this.areaAlphabet=new JTextArea();
        this.add(areaAlphabet, gridSystem.insertComponent(3,0,12,2));

        this.labelNotTerminals=new JLabel("No terminales:");
        this.add(labelNotTerminals, gridSystem.insertComponent(5,0,2,1));

        this.areaNotTerminals=new JTextArea();
        this.add(areaNotTerminals,gridSystem.insertComponent(6,0,12,2));

        this.labelAxioma=new JLabel("Axioma");
        this.add(labelAxioma,gridSystem.insertComponent(8,0,2,1));

        this.textAxioma=new JTextField();
        this.textAxioma.setBorder(new EmptyBorder(5,5,5,5));
        this.add(textAxioma,gridSystem.insertComponent(10,0,12,1));

        this.labelProcceses=new JLabel("Procesos:");
        this.add(labelProcceses,gridSystem.insertComponent(11,0,12,1));

        this.areaProcceses=new JTextArea();
        this.add(areaProcceses,gridSystem.insertComponent(12,0,12,1));

        this.buttonConfirm=new JButton("CREAR");
        this.add(buttonConfirm,gridSystem.insertComponent(13,5,2,1));
    }

    /** 
     * Metodo de escucha del boton de crear gramatica
     * @param  l ActionListener.
     */
    private void setActionsCommands(ActionListener l) {
        buttonConfirm.setActionCommand(CommandsGUI.CREATE_GRAMMAR.name());
        buttonConfirm.addActionListener(l);
    }


    /** 
     * Metodo que retorna una lista con el alfabeto.
     * @return Arraylist de caracteres que contiene el alfabeto dentro de ella.
     */
    public ArrayList<Character> getAlphabet() throws Exception{
        String temp=this.areaAlphabet.getText();
        if (temp.compareTo("")==0){
            throw new Exception("El alfabeto se encuentra vacío");
        }else{
            String [] l=temp.split(",");
            ArrayList<Character> array=new ArrayList<>();
            for (int i = 0; i < l.length; i++) {
                if (l[i].length()==1&&(l[i].compareTo("")!=0&&l[i].compareTo(" ")!=0)){
                    array.add(l[i].charAt(0));
                }
            }
            if (array.size()==0){
                throw new Exception("El alfabeto se encuentra vacío");
            }else{
                return array;
            }
        }
    }


    /** 
     * Metodo que retorna la lisa con los caracteres no terminales.
     * @return Arraylist de caracteres que contiene una lista de caracteres no terminales.
     */
    public ArrayList<Character> getNotTerminals() throws Exception{
        String temp=this.areaNotTerminals.getText();
        if (temp.compareTo("")==0){
            throw new Exception("Los no terminales se encuentran vacíos");
        }else{
            String [] l=temp.split(",");
            ArrayList<Character> array=new ArrayList<>();
            for (int i = 0; i < l.length; i++) {
                if (l[i].length()==1&&(l[i].compareTo("")!=0&&l[i].compareTo(" ")!=0)){
                    array.add(l[i].charAt(0));
                }
            }
            if (array.size()==0){
                throw new Exception("Los no terminales se encuentran vacíos");
            }else{
                return array;
            }
        }
    }


    /** 
     * Metodo que retorna  un axioma.
     * @return Char con el axioma.
     */
    public char getAxioma() throws Exception{
        String temp=textAxioma.getText();
        if (temp.length()<2&&temp.charAt(0)!=' '){
            return temp.charAt(0);
        }else{
            throw new Exception("El axioma se encuentra vacío.");
        }
    }


    /** 
     * Metodo que busca y retorna los procesos .
     * @return Arrylist de string con los procesos.
     */
    public ArrayList<String> getProccesses() throws Exception{
        String temp=this.areaProcceses.getText();
        if (temp.compareTo("")==0){
            throw new Exception("Los procesos se encuentran vacíos");
        }else {
            String[] l = temp.split(",");
            ArrayList<String> array = new ArrayList<>();
            for (int i = 0; i < l.length; i++) {
                if (l[i].compareTo("")!=0&&l[i].compareTo(" ")!=0) {
                    array.add(l[i]);
                }
            }
            if (array.size() == 0) {
                throw new Exception("Los procesos se encuentran vacíos");
            } else {
                return array;
            }
        }
    }

     /** 
     * Metodo que limpia el formulario
     */
    public void clear() {
        this.areaAlphabet.setText("");
        this.areaNotTerminals.setText("");
        this.areaProcceses.setText("");
        this.textAxioma.setText("");
    }
}
