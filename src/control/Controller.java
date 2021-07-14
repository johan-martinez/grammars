package control;

import model.Grammar;
import view.CommandsGUI;
import view.IOManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *Controlador de la logica del programa.
 *@author Equipo de trabajo
 */

public class Controller implements ActionListener {
    private IOManager io;
    private Grammar grammar;

    public Controller(){
        try {
            this.grammar=query();
            this.io=new IOManager(this);
            //io.setTree(this.grammar.getTree(4));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Crea una nueva clase de la gramatica
     * @return g1 con una gramatica.
     */
    private Grammar query() throws Exception{
        ArrayList<Character> alph = new ArrayList<>();
        alph.add('m');
        alph.add('n');

        ArrayList<Character> nt = new ArrayList<>();
        nt.add('S');
        nt.add('A');
        nt.add('B');

        ArrayList<String> p = new ArrayList<>();
        p.add("B-> *|m");
        p.add("A-> Bm|Am|n");
        p.add("S-> Am");

        Grammar g1=new Grammar(alph, nt, 'S', p);
        return g1;
    }

    /**
     * analizeWord 
     * Analiza la palabra si existe y notifica al usuario si se encontro o no .
     */
    private void analizeWord(){
        try {
            String word=io.getWord(); //sacarlo dela GUI
            io.showMessage(grammar.analizeWord(word), "Camino encontrado");
        }catch (Exception e){
            io.showError(e.getMessage(),"Error");
        }
    }

    /**
     * Controlador de los botones que indica que funcion deben ejecutar.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (CommandsGUI.valueOf(e.getActionCommand())){
            case CREATE_GRAMMAR:
                createGrammar();
                break;
            case ANALIZE_WORD:
                analizeWord();
                break;
        }
    }

     /**
     * createGrammar 
     * Crea una gramarica y retorna una notificacion al usuario
     */
    private void createGrammar() {
        try {
            ArrayList<Character> alphabet=io.getAlphabet();
            ArrayList<Character> notTerminals=io.getNotTerminals();
            char axioma= io.getAxioma();
            ArrayList<String> proccesses= io.getProccesses();
            if (alphabet.size()>1&&notTerminals.size()>2&&proccesses.size()>3) {
                this.grammar=new Grammar(alphabet,notTerminals,axioma,proccesses);
                io.setTree(grammar.getTree(4));
                io.showContainerPanel();
            }else{
                io.showError("Debe Ingresar:\n\t1)Mínimo dos símbolos terminales\n\t2)Minimo tres símbolos terminales\n\t3)Mínimo tres procesos","Error al crear gramática");
            }
        }catch (Exception e){
            this.grammar=null;
            io.showError(e.getMessage(), "Error al crear una gramática");
        }
    }
}
