package model;

import structs.Element;
import structs.NTree;
import structs.NodeNTree;
import structs.NodeSimple;

import java.util.ArrayList;

public class Grammar {
    private ArrayList<Character> alphabet;
    private ArrayList<Character> notTerminals;
    private char axioma;
    private ArrayList<Proccess> proccesses;

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

    private boolean validateAxioma()throws  Exception{
        for (Character nt: notTerminals) {
            if (nt.charValue() == axioma)
                return true;
        }
        throw new Exception("Axioma ["+axioma+"] is not defined in Not terminals.");
    }


    public void addProcess (String processText){
        String [] first = processText.replace(" " ,"").split("->");
        String [] results = first[first.length-1].split("\\|");
        this.proccesses.add(new Proccess(first[0].charAt(0),results));
    }

    @Override
    public String toString() {
        return "Grammar: \n" +
                "\talphabet= " + alphabet.toString() +
                "\n\tnotTerminals=" + notTerminals.toString() +
                "\n\taxioma=[" + axioma +
                "]\n\tprocess=" + proccesses.toString();
    }


    public boolean contains(String word){
        //mirar si tiene no terminales
        for (Character nt: this.notTerminals) {
            if (word.contains(""+nt.charValue()))
                return false;
        }
        return  replace(""+this.axioma, word);
    }

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

    private String[] getProccessResults (char nt){
        for (Proccess p: this.proccesses) {
            if (p.getNotTerminal()==nt)
                return p.getResults();
        }
        return null;
    }

    private char containsNotTerminal(String word){
        for (Character nt: this.notTerminals) {
            if (word.contains(""+nt.charValue()))
                return nt.charValue();
        }
        return ' ';
    }

    public NTree<String> getTree(int maxLevel){
        NTree<String> tree=new NTree<>();
        Element<String> root= tree.insertRoot(""+this.axioma);
        return createNode(root.getElement(),root,tree,maxLevel,1);
    }

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

    //
    public String analizeWord(String word) throws Exception{
        if (contains(word)){
            NodeSimple<String> nodo = new NodeSimple<>();
            this.verifyWord(word, "" + this.axioma, nodo);
            return this.buildWays("", nodo, word);
        }else {
            throw new Exception("No se encuentra la palabra");
        }


    }

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

    private boolean isTerminal (Character a) {
        for (Character iterator: notTerminals) {
            if (iterator.charValue() == a.charValue())
                return true;
        }
        return  false;
    }

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
                        //System.out.println(answer);
                        return answer;
                    }
                }
            }
        }else{
            if(nodo.getInfo().equals(word)){
                pro=constr+=nodo.getInfo();
                //System.out.println(pro);
            }
        }
        return pro;
    }

    /*
    private String buildWays(String constr, NodeSimple<String> nodo, String word){
        String pro ="";
        if (!nodo.isNext()){
            pro =constr+=nodo.getInfo()+"->";
            ArrayList<NodeSimple<String>> arrayTemp = new ArrayList<>();
            arrayTemp = nodo.getBranches();
            for (int i = 0; i < arrayTemp.size(); i++) {
                String v= buildWays(pro,arrayTemp.get(i),word);
                if (v.compareTo("")!=0){
                    constr=v;
                    break;
                }
            }
            return constr;
        }else{
            if(nodo.getInfo().equals(word)){
                constr+=nodo.getInfo();
                return constr;
            }else{
                return "";
            }
        }
    }*/

    /*
    private void buildWays(String constr, NodeSimple<String> nodo, String word){
        String pro ="";
        if (!nodo.isNext()){
            pro =constr+=nodo.getInfo()+"->";
            ArrayList<NodeSimple<String>> arrayTemp = new ArrayList<>();

            arrayTemp = nodo.getBranches();
            for (int i = 0; i < arrayTemp.size(); i++) {
                buildWays(pro,arrayTemp.get(i),word);
            }
        }else{
            if(nodo.getInfo().equals(word)){
                pro =constr+=nodo.getInfo();
                System.out.println(pro);
            }
        }
    }
    * */


}
