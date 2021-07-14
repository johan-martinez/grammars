package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

/**
 * Panel de las palabras encargado de todo lo que trata de la misma.
 * @version 1
 * @author Grupo
 */

public class PanelWord extends JPanel {

    private JLabel labelTitle;
    private JTextField text;
    private JButton buttonConfirm;
    private GridSystem gridSystem;

     /** 
     * Constructor.
     * @param l ActionListener de los botones.
     */
    public PanelWord(ActionListener l) {
        this.gridSystem=new GridSystem(this);
        initComponents();
        setActionsCommands(l);
    }

    /** 
     * Ejecucion inicial del panel ejecutando 
     * y agregando los paneles al contenedor.
     */
    private void initComponents() {
        this.labelTitle = new JLabel("Ingrese una Palabra", SwingConstants.CENTER);
        this.labelTitle.setHorizontalTextPosition(JLabel.CENTER);
        this.add(labelTitle, gridSystem.insertComponent(0, 5, 2, 1));


        this.text=new JTextField();
        this.text.setBorder(new EmptyBorder(5,5,5,5));
        this.add(text,gridSystem.insertComponent(1,0,12,1));


        this.buttonConfirm=new JButton("Buscar");
        this.add(buttonConfirm,gridSystem.insertComponent(2,5,2,1));

    }

    /** 
     * Escucha al boton de analizar palabra 
     * @param l ActionListener de los botones.
     */
    private void setActionsCommands(ActionListener l) {
        buttonConfirm.setActionCommand(CommandsGUI.ANALIZE_WORD.name());
        buttonConfirm.addActionListener(l);
    }



    /** 
     * Limpia el formulario
     */
    public void clear() {
        this.text.setText("");
    }
    /** 
     * Retorna la palabra
     * Retorna un string  con la palabra
     */
    public String getWord() throws Exception{
        String temp=this.text.getText();
        if (temp.compareTo("")==0) {
            throw new Exception("La palabra a buscar se encuentra vacía");
        }else{
            clear();
            return temp;
        }
    }
}
