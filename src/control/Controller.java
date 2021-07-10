package control;

import model.Grammar;
import view.CommandsGUI;
import view.IOManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private void analizeWord(){
        try {
            String word=io.getWord(); //sacarlo dela GUI
            io.showMessage(grammar.analizeWord(word), "Camino encontrado");
        }catch (Exception e){
            io.showError(e.getMessage(),"Error");
        }
    }

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

    private void createGrammar() {
        try {
            ArrayList<Character> alphabet=io.getAlphabet();
            ArrayList<Character> notTerminals=io.getNotTerminals();
            char axioma= io.getAxioma();
            ArrayList<String> proccesses= io.getProccesses();
            this.grammar=new Grammar(alphabet,notTerminals,axioma,proccesses);
            io.setTree(grammar.getTree(4));
            io.showContainerPanel();
        }catch (Exception e){
            this.grammar=null;
            io.showError(e.getMessage(), "Error al crear una gramática");
        }
    }
}
