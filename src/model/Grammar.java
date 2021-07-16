package model;

import structs.Element;
import structs.NTree;
import structs.NodeNTree;
import structs.NodeSimple;

import java.util.ArrayList;

/**
 *Clase de representación de una gramática
 *@author Equipo de trabajo
 */

public class Grammar {
    private ArrayList<Character> alphabet; //Listado de símbolos terminales
    private ArrayList<Character> notTerminals; //Listado de símbolos no terminales
    private char axioma; //Símbolo que representa el elemento axioma
    private ArrayList<Proccess> proccesses; //Listado de Procesos

    /**
     *Crea una gramática a partir de un alfabeto, símbolos no terminales , un axioma y una lista de procesos, realizando las validaciones correspondientes a está
     * @param alph Listado de caracteres pertenecientes al alfabeto.
     * @param notTerm Listado de caracteres pertenecientes a los simbolos no terminales.
     * @param axio Carácter representación del axioma.
     * @param proccesses Listado de Cadenas de caracteres en formato A->T|B.
     * @throws Exception Error de contrucción de grámatica.
     */
    public Grammar(ArrayList<Character> alph, ArrayList<Character> notTerm, char axio, ArrayList<String> proccesses) throws Exception{
        this.alphabet = alph;
        this.notTerminals = notTerm;
        this.axioma = axio;
        this.proccesses = new ArrayList<>();
        validateAlphabet();
        validateNotTerminals();
        validateAxioma();
        for (String p: proccesses) {
            addProcess(p);
        }
    }

    /**
     * Valida el atributo del alfabeto
     * @return boolean que indica si es valido o no.
     * @throws Exception Alfabeto vacío o elemento duplicado en no terminales y alfabeto
     */
    public boolean validateAlphabet() throws  Exception{
        if (this.alphabet.isEmpty()){
            throw new NullPointerException("Alphabet is Empty");
        }else {
            for (Character a: this.alphabet) {
                for (Character nt: this.notTerminals) {
                    if (a.charValue()==nt.charValue())
                        throw new Exception("Element: ["+a.charValue()+"]  is found repeated in the non-terminals elements");
                }
            }
            return true;
        }
    }

    /**
     * Valida el atributo de no terminales
     * @return boolean que indica si es valido o no.
     * @throws Exception listado de no terminales vacío o elemento duplicado en no terminales y alfabeto
     */
    public boolean validateNotTerminals() throws  Exception{
        if (this.notTerminals.isEmpty()){
            throw new NullPointerException("Not Terminals are Empty");
        }else {
            for (Character a:  this.notTerminals) {
                for (Character nt: this.alphabet) {
                    if (nt.charValue()==a.charValue())
                        throw new Exception("Element: ["+nt.charValue()+"]  is found repeated in the alphabet elements");
                }
            }
            return true;
        }
    }

    /**
     * Valida el atributo del axioma
     * @return boolean que indica si es valido o no.
     * @throws Exception Axioma no definido o no encontrado en no terminales
     */
    private boolean validateAxioma()throws  Exception{
        for (Character nt: notTerminals) {
            if (nt.charValue() == axioma)
                return true;
        }
        throw new Exception("Axioma ["+axioma+"] is not defined in Not terminals.");
    }


    /**
     * Añade un proceso a la gramática
     * @param processText String en formato A->T|B. 
     * @return boolean que indica si es valido o no.
     */
    public void addProcess (String processText){
        String [] first = processText.replace(" " ,"").split("->");
        String [] results = first[first.length-1].split("\\|");
        this.proccesses.add(new Proccess(first[0].charAt(0),results));
    }

    @Override
    /**
     * Representación en texto de la gramática
     * @return String de representación de la clase gramática.
     */
    public String toString() {
        return "Grammar: \n" +
                "\talphabet= " + alphabet.toString() +
                "\n\tnotTerminals=" + notTerminals.toString() +
                "\n\taxioma=[" + axioma +
                "]\n\tprocess=" + proccesses.toString();
    }

    /**
     * Método que indica si una palabra está contenida en una gramática
     * @param word String de ingreso de una palabra 
     * @return boolean que indica si la palabra está contenida o no en la gramática.
     */
    public boolean contains(String word){
        //mirar si tiene no terminales
        for (Character nt: this.notTerminals) {
            if (word.contains(""+nt.charValue()))
                return false;
        }
        return  replace(""+this.axioma, word);
    }

    /**
     * Método recursivo para la búsqueda de la palabra, que va reemplazando y haciedno el árbol de derivación
     * @param tempWord String temporal en el que se va evaluando el árbol de derivación.
     * @param word String contenedor de la palabra a buscar. 
     * @return boolean que indica si la palabra está contenida o no en la gramática.
     */
    private boolean replace(String tempWord, String word) {
        if (tempWord.length()<=word.length()) {
            char ctw = containsNotTerminal(tempWord);
            if (ctw != ' ') {
                String[] results = getProccessResults(ctw);
                boolean isFind = false;
                for (int i = 0; i < results.length; i++) {
                    if (!isFind && i < results.length) {
                        String newWord = tempWord.replace("" + ctw, results[i]);
                        isFind = replace(newWord, word);
                    }
                }
                return isFind;
            } else if (tempWord.length() > word.length()) {
                return false;
            } else {
                return tempWord.compareTo(word) == 0;
            }
        }else{
            return false;
        }
    }

    /**
     * Busca los resultados descritos en los procesos por un símbolo no terminal
     * @param nt char del no terminal al que se le buscara sus procesos
     * @return Array de Strings con los reemplazos que se deben realizar.
     */
    private String[] getProccessResults (char nt){
        for (Proccess p: this.proccesses) {
            if (p.getNotTerminal()==nt)
                return p.getResults();
        }
        return null;
    }

    /**
     * Evalua si una palabra contiene un símbolo no terminal
     * @param word String contenedor de la palabra. 
     * @return char que representa el símbolo no terminal encontrado.
     */
    private char containsNotTerminal(String word){
        for (Character nt: this.notTerminals) {
            if (word.contains(""+nt.charValue()))
                return nt.charValue();
        }
        return ' ';
    }

    /**
     * Obtener un arbol de la gramática hasta cierto nivel
     * @param maxLevel int que indica el número de niveles máximos que se desea explorar.
     * @return NTree<String> estructura de árbol n-ario contenedora del árbol de derivación de la gramática.
     */
    public NTree<String> getTree(int maxLevel){
        NTree<String> tree=new NTree<>();
        Element<String> root= tree.insertRoot(""+this.axioma);
        return createNode(root.getElement(),root,tree,maxLevel,1);
    }

    /**
     * Método recursivo para la creación del árbol de derivación
     * @param tempWord String temporal en el que se va evaluando el árbol de derivación.
     * @param father Elemento apuntador a un nodo padre.
     * @param tree árbol en el que se está construyendo.
     * @param maxLevel nivel máximo al que se va a explorar.
     * @param high altura en la que va el método recursivo.
     * @return NTree<String> estructura de árbol n-ario contenedora del árbol de derivación de la gramática.
     */
    private NTree<String> createNode(String tempWord, Element<String> father , NTree<String> tree, int maxLevel, int high){
        if (high<=maxLevel){
            char ctw = containsNotTerminal(tempWord);
            if (ctw != ' ') {
                String[] results = getProccessResults(ctw);
                for (int i = 0; i < results.length; i++) {
                    String newWord = tempWord.replace("" + ctw, results[i]);
                    Element<String> e=tree.insertChild(father,newWord);
                    createNode(newWord,e, tree, maxLevel, (high+1));
                }
            }
            return tree;
        }else{
            return tree;
        }
    }

    /**
     * Obtener los caminos de una palabra en una gramática
     * @param word String de la palabra a buscar.
     * @return String contenedor del camino de la palabra.
     * @throws Exception palabra no contenida en la gramática.
     */
    public String analizeWord(String word) throws Exception{
        if (contains(word)){
            NodeSimple<String> nodo = new NodeSimple<>();
            this.verifyWord(word, "" + this.axioma, nodo);
            return this.buildWays("", nodo, word);
        }else {
            throw new Exception("No se encuentra la palabra");
        }


    }

    /**
     * Método para verificar una palabra e ir armando los caminos
     * @param word String palabra a buscar.
     * @param construction String de la cadena a retornar.
     * @param construction Nodo que indica dónde vamos.
     */
    private void verifyWord(String word, String construction, NodeSimple<String> nodo) {
        String pro = construction;
        nodo.settInfo(construction);
        if (construction.length() <= word.length()) {
            ArrayList<String> ways = replaceTerminal(construction);
            for (String iterator : ways) {
                NodeSimple<String> newNodo = new NodeSimple<>();
                nodo.addBranch(newNodo);
                this.verifyWord(word, iterator,newNodo);
            }
        }
    }


    /**
     * Método para reemplazar un terminal en una palabra
     * @param word String palabra a reemplazar.
     * @return Lista de palabras que se generan al reemplazar el no terminal.
     */
    private ArrayList<String> replaceTerminal(String word){

        ArrayList<String> toReturn =new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {

            if (isTerminal(word.charAt(i))) {
                for (Proccess p: proccesses) {
                    if (p.getNotTerminal()==word.charAt(i)){
                        String [] results = p.getResults();

                        for (int j = 0; j < results.length; j++) {
                            toReturn.add(word.replace(""+word.charAt(i),results[j]));
                        }
                    }
                }
            }

        }
        return  toReturn;
    }

    /**
     * Método para validar si un caracter es no terminal
     * @param a Character a validar.
     * @return boolean que indica si es no terminal o no lo es.
     */
    private boolean isTerminal (Character a) {
        for (Character iterator: notTerminals) {
            if (iterator.charValue() == a.charValue())
                return true;
        }
        return  false;
    }

    /**
     * Método para construir los caminos de una palabra
     * @param constr String construido con los caminos.
     * @param nodo Nodo en el que se va construyendo la palabra.
     * @param word String palabra a buscar.
     * @return String con el camino para la construcción de  la palabra.
     */
    private String buildWays(String constr, NodeSimple<String> nodo, String word){
        String pro ="";
        if (!nodo.isNext()){
            pro =constr+=nodo.getInfo()+"→";
            ArrayList<NodeSimple<String>> arrayTemp = new ArrayList<>();

            arrayTemp = nodo.getBranches();
            for (int i = 0; i < arrayTemp.size(); i++) {
                String answer = buildWays(pro,arrayTemp.get(i),word);
                if (answer.length()>0){
                    if (answer.charAt(answer.length()-1)!='→'){
                        return answer;
                    }
                }
            }
        }else{
            if(nodo.getInfo().equals(word)){
                pro=constr+=nodo.getInfo();
            }
        }
        return pro;
    }


}
