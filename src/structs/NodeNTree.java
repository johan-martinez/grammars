package structs;

import java.util.ArrayList;

public class NodeNTree<E> implements Element<E>{
    private E element;
    private NodeNTree<E> father;
    private ArrayList<NodeNTree<E>> childs;


    public NodeNTree() {

    }

    public NodeNTree(E e,NodeNTree<E>father) {
        this.element =e;
        this.father =father;
        this.childs =new ArrayList<NodeNTree<E>>();
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public NodeNTree<E> getFather() {
        return father;
    }

    public void setFather(NodeNTree<E> father) {
        this.father = father;
    }

    protected NodeNTree<E> getChild(int index) {
        return this.childs.get(index);
    }

    protected void setChild(int index,E e) {
        childs.get(index).setElement(e);
    }

    public void insertChild(NodeNTree<E> e) {
        childs.add(e);
    }

    public int childsSize() {
        return childs.size();
    }

    public ArrayList<Element<E>> childs() {
        return new ArrayList<Element<E>>(childs);
    }

    public String toString() {
        return getElement().toString();
    }

}
