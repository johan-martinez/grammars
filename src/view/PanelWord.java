package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class PanelWord extends JPanel {

    private JLabel labelTitle;
    private JTextField text;
    private JButton buttonConfirm;
    private GridSystem gridSystem;

    public PanelWord(ActionListener l) {
        this.gridSystem=new GridSystem(this);
        initComponents();
        setActionsCommands(l);
    }

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


    private void setActionsCommands(ActionListener l) {
        buttonConfirm.setActionCommand(CommandsGUI.ANALIZE_WORD.name());
        buttonConfirm.addActionListener(l);
    }



    public void clear() {
        this.text.setText("");
    }

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
