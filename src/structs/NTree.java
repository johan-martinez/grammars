package structs;

import java.util.ArrayList;

public class NTree<E> {

    private NodeNTree<E> root;
    private int amount;

    public NTree() {
        this.amount =0;
    }

    public Element<E> getRoot() {
        return root;
    }

    public int getAmount() {
        return amount;
    }

    private NodeNTree<E> createNode(E e, NodeNTree<E> father) {
        return new NodeNTree<>(e,father);
    }

    private NodeNTree<E> validate(Element<E> e) {
        if(!(e instanceof NodeNTree)) {
            return null;
        }
        NodeNTree<E> node = (NodeNTree<E>)e;
        return node.getFather()==node?null:node;
    }

    public boolean isRoot(Element<E> e) {
        return e== getRoot();
    }

    public boolean isEmpty() {
        return getAmount()==0;
    }

    public Element<E> getFather(Element<E> e) {
        return validate(e).getFather();
    }

    public int numberChildrens(Element<E> e) {
        NodeNTree<E> node= validate(e);
        return node.childsSize();
    }

    public int numberLevels(Element<E> e) {
        if(isRoot(e)) {
            return 0;
        } else {
            return 1+ numberLevels(getFather(e));
        }

    }


    public int high(Element<E> e) {
        int high=0;
        for (Element<E> hijo: getChilds(e)) {
            high=Math.max(high,1+ high(hijo));
        }
        return high;
    }

    public ArrayList<Element<E>> getChilds(Element<E> e) {
        NodeNTree<E> node= validate(e);
        return node.childs();
    }

    public Element<E> getElement(E e) {
        NodeNTree<Element<E>> node=new NodeNTree<Element<E>>();
        search(e, getRoot(),node);
        return node.getElement();
    }

    private void search(E e, Element<E> root, NodeNTree<Element<E>> node) {
        if(e.equals(root.getElement())) {
            node.setElement(root);
        }
        for(Element<E> x: getChilds(root)) {
            search(e,x,node);
        }
    }

    public Element<E> insertRoot(E info) {
        if(!isEmpty()) {
            return null;
        }
        root = createNode(info,null);
        amount =1;
        return root;
    }

    public Element<E> insertChild(Element<E> e, E info) {
        NodeNTree<E> node= validate(e);
        if(node==null) {
            return null;
        }
        NodeNTree<E> child= createNode(info, node);
        node.insertChild(child);
        amount++;
        return child;
    }

    public Iterable<Element<E>> preOrder() {
        ArrayList<Element<E>> pos=new ArrayList<Element<E>>();
        if(!isEmpty()) {
            preOrder(getRoot(),pos);
        }
        return pos;
    }

    private void preOrder(Element<E> root, ArrayList<Element<E>> pos) {
        pos.add(root);
        for(Element<E> child: getChilds(root)) {
            preOrder(child,pos);
        }
    }


    public Iterable<Element<E>> inOrder() {
        ArrayList<Element<E>> pos=new ArrayList<Element<E>>();
        if(!isEmpty()) {
            inOrder(getRoot(), pos);
        }
        return pos;
    }

    private void inOrder(Element<E> root, ArrayList<Element<E>> pos) {
        if(numberChildrens(root)==0) {
            pos.add(root);
        } else {
            inOrder(getChilds(root).get(0),pos);
            pos.add(root);
            for(int i = 1; i< numberChildrens(root); i++) {
                inOrder(getChilds(root).get(i),pos);
            }
        }

    }


    public Iterable<Element<E>> posOrder() {
        ArrayList<Element<E>> pos=new ArrayList<Element<E>>();
        if(!isEmpty()) {
            posOrder(getRoot(), pos);
        }
        return pos;
    }

    private void posOrder(Element<E> root, ArrayList<Element<E>> pos) {
        for(Element<E>child: getChilds(root)) {
            posOrder(child,pos);
        }
        pos.add(root);
    }

}
