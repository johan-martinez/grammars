package structs;
/**
 * Una clase Node que contiene información y mas nodos hijos
 * Esta clase representa la estructura de un arbol enario.
 * @version 1.0, 06/07/21
 * @author Johan Martinez
 */

import java.util.ArrayList;

public class NodeNTree<E> implements Element<E>{
    private E element; //Elemento de tipo E que representa el valor el nodo
    private NodeNTree<E> father;// Nodo padre del nodo
    private ArrayList<NodeNTree<E>> childs;// Lista de hijos del nodo

    /**
    *Constructor vacio
     */
    public NodeNTree() {

    }
    /**
     * Constructor con parametros
     * @param e valor del nodo
     * @param father nodo padre del nodo
     */
    public NodeNTree(E e,NodeNTree<E>father) {
        this.element =e;
        this.father =father;
        this.childs =new ArrayList<NodeNTree<E>>();
    }
    /**
     * retorna el valor el nodo
     * @return e valor del nodo
     */
    public E getElement() {
        return element;
    }

    /**
     * Setea el valor del elemento al nodo
     * @param element valor del nodo
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Retorna el nodo del padre
     * @param father retorna el padre del nodo
     */
    public NodeNTree<E> getFather() {
        return father;
    }

    /**
     * Setea el padre al nodo
     * @param father valor del nodo
     */
    public void setFather(NodeNTree<E> father) {
        this.father = father;
    }

    /**
     * Retorna un elemento de la lista de hijos
     * @param index indice de la lista
     * @return this.childs.get(index) returna el elemento en un respetivo indice de la lista de hijos
     */
    protected NodeNTree<E> getChild(int index) {
        return this.childs.get(index);
    }

    /**
     * Asigna un nuevo hijo al nodo
     * @param index indice de la lista donde ira el nuevo elemento
     * @param e valor nuevo del valor
     */
    protected void setChild(int index,E e) {
        childs.get(index).setElement(e);
    }

    /**
     * Asigna un nuevo hijo al nodo
     * @param e nodo que se agrega a la lista de hijos
     */
    public void insertChild(NodeNTree<E> e) {
        childs.add(e);
    }

    /**
     * Retorna la cantidad de hijos
     * @return childs.size() retorna la cantida dde hijos del nodo
     */
    public int childsSize() {
        return childs.size();
    }

    /**
     * Crea una nueva lista de hijos
     * @return new ArrayList<Element<E>>(childs) retorna una nueva lista de hijos
     */
    public ArrayList<Element<E>> childs() {
        return new ArrayList<Element<E>>(childs);
    }

    /**
     * retorna el valor del nodo
     * @return getElement().toString() retorna el valor del nodo para poder ser impreso por pantalla
     */
    public String toString() {
        return getElement().toString();
    }

}
