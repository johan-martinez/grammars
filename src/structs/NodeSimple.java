package structs;
/**
 * Una clase para representar un nodo de cualquier tipo de dato que puede tener
 * cualquier numero de hijos
 * @version 1.0, 05/07/21
 * @author Fabián Ojeda
 */
import java.util.ArrayList;

public class NodeSimple<T> {


    private T info;// atributo de tipo T, es decir que sirve para cualquier tipo de dato
    private ArrayList<NodeSimple<T>> branches;// Arreglo de nodos de tipo T, este arreglo contendra a los hijos del nodo

    /**
     * constructor que recibe el valor del nodo y lo agrega, a su vez asigna memoria al arreglo de hijos.
     * @param info valor del nodo.
     */

    public NodeSimple(T info) {
        this.info = info;
        this.branches = new ArrayList<NodeSimple<T>>();
    }

    /**
     * Constructor que asigna memoria al arreglo de hijos y deja en nulo el valor del nodo.
     */
    public NodeSimple() {
        this.info = null;
        this.branches = new ArrayList<NodeSimple<T>>();
    }
    /**
     * retorna el valor del nodo.
     * @return info valor del nodo.
     */
    public T getInfo() {
        return info;
    }

    /**
     * setea al valor del nodo
     * @param info valor del nodo.
     */
    public void settInfo(T info) {
        this.info = info;
    }

    /**
     * Agrega un nodo hijo
     * @param branch nodo para agregar a la lista de hijos
     */
    public void addBranch(NodeSimple<T> branch){
        this.branches.add(branch);
    }


    /**
     * @return this.branches.isEmpty()  retorna true si el nodo tiene nodos hijos y false en caso contrario
     */
    public boolean isNext(){
        return this.branches.isEmpty();
    }

    /**
     * @return  this.branches retorna la lista de nodos hijos
     */
    public ArrayList<NodeSimple<T>> getBranches(){
        return this.branches;
    }
}
