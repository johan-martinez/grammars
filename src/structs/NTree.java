package structs;
/**
 * Una clase Arbol que contiene un nodo raiz de cualquie tipo
 * Esta clase representa una estructura de almacenamiento en la cual el nodo raiz puede tener varias conecciones
 * que se llamaran nodos hijos, westos nodos a su vez pueden tener mas hijos, creando la estructua de datos
 * que representtara el arbol de derivación de la gramatica, en el cual se buscara si un apalabra existe o no en ella.
 * @version 1.0, 06/07/21
 * @author Johan Martinez
 */
import java.util.ArrayList;

public class NTree<E> {


    private NodeNTree<E> root;//Nodo raiz de tipo E
    private int amount;//profundidad del arbol

    /**
     * constructor vacio que inicializa amount en 0
     */
    public NTree() {
        this.amount =0;
    }

    /**
     * @return root retorna el nodo raiz del arbol
     */
    public Element<E> getRoot() {
        return root;
    }

    /**
     * @return amount retorna la profundidad del arbol
     */
    public int getAmount() {
        return amount;
    }

    /**
     * crea un nuevo nodo
     * @param e valor del nuevo nodo
     * @param father nodo padre del nuevo nodo
     * @return new NodeNTree<>(e,father) retorna un nuevo nodo creado
     */
    private NodeNTree<E> createNode(E e, NodeNTree<E> father) {
        return new NodeNTree<>(e,father);
    }



    //-----------------------------Parce no entendi un culo jajajaja XD---------------------------------------------//








    /**
     *Verifica si el elemento e es una instancia de la interfaz Element
     * @param e Objeto de la interfaz e
     * @return null retorna nulo si el nodo es su mismo padre
     * @return node si el nodo no es su mismo arbol
     */
    private NodeNTree<E> validate(Element<E> e) {
        if(!(e instanceof NodeNTree)) {
            return null;
        }
        NodeNTree<E> node = (NodeNTree<E>)e;
        return node.getFather()==node?null:node;
    }








    //-----------------------------------------------------------------------------------------

    /**
     *Verifica si el elemento e es el nodo raiz del arbol
     * @param e Objeto de la interfaz e
     * @return e== getRoot() retorna si el objeto e es la raiz del arbol
     */
    public boolean isRoot(Element<E> e) {
        return e== getRoot();
    }

    /**
     *Verifica si el arbol esta vacio
     * @return getAmount()==0 retorna si la profundidad del arbol es 0
     */
    public boolean isEmpty() {
        return getAmount()==0;
    }

    //---------------------------------------------aucsilio, no me acuerdo de valero------------------------------------//




    public Element<E> getFather(Element<E> e) {
        return validate(e).getFather();
    }





    //-------------------------------------------------------------------------------------------------------------------//
    /**
     *Verifica algo que no se que es porque no entendi bien el metodo validate
     * @return node.childsSize() retorna la cantidad de hijos del nodo
     */
    public int numberChildrens(Element<E> e) {
        NodeNTree<E> node= validate(e);
        return node.childsSize();
    }


    /**
     *Retorna el numero de niveles de un nodo
     * @return 0 retorna 0 si e es el nodo raiz
     * @return 1+ numberLevels(getFather(e)) retorna 1 + el numero de niveles del padre si no es en nodo padre
     */
    public int numberLevels(Element<E> e) {
        if(isRoot(e)) {
            return 0;
        } else {
            return 1+ numberLevels(getFather(e));
        }

    }

    /**
     *Retorna la cantidad de hijos de un nodo
     * @param e nodo para contar los hijos
     * @return high retorna la cantida de hijos del nodo e
     */
    public int high(Element<E> e) {
        int high=0;
        for (Element<E> hijo: getChilds(e)) {
            high=Math.max(high,1+ high(hijo));
        }
        return high;
    }

    /**
     *Retorna la lista de hijos de un nodo
     * @param e nodo del cual se retornara la lista de hijos
     * @return node.childs() retorna la lista de hijos de un nodo
     */
    public ArrayList<Element<E>> getChilds(Element<E> e) {
        NodeNTree<E> node= validate(e);
        return node.childs();
    }

    //------------------------------------------------cheems: no puede ser---------------------------------------------//







    /**
     *Retorna el valor de un nodo
     * @param e nodo del cual se retornara la lista de hijos
     * @return node.childs() retorna la lista de hijos de un nodo
     */
    public Element<E> getElement(E e) {
        NodeNTree<Element<E>> node=new NodeNTree<Element<E>>();
        search(e, getRoot(),node);
        return node.getElement();
    }

    /**
     *
     */
    private void search(E e, Element<E> root, NodeNTree<Element<E>> node) {
        if(e.equals(root.getElement())) {
            node.setElement(root);
        }
        for(Element<E> x: getChilds(root)) {
            search(e,x,node);
        }
    }






    //------------------------------------------------------------------------------------------------------------------//


    /**
     *Inserta una nueva raiz
     * @param info valor de la raiz
     * @return root la nueva raiz
     */
    public Element<E> insertRoot(E info) {
        if(!isEmpty()) {
            return null;
        }
        root = createNode(info,null);
        amount =1;
        return root;
    }

    /**
     *Insertaun nuevo hijo a un nodo
     * @return child nuevo hijo creado
     */
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

    /**
     *Ordena de manera anterior
     * @return pos retirna la posición mas hacia la derecha
     */
    public Iterable<Element<E>> preOrder() {
        ArrayList<Element<E>> pos=new ArrayList<Element<E>>();
        if(!isEmpty()) {
            preOrder(getRoot(),pos);
        }
        return pos;
    }

    /**
     *Ordena de manera anterior
     * @return pos retirna la posición mas hacia la derecha
     */
    private void preOrder(Element<E> root, ArrayList<Element<E>> pos) {
        pos.add(root);
        for(Element<E> child: getChilds(root)) {
            preOrder(child,pos);
        }
    }

    /**
     *Ordena de manera anterior
     * @return pos retirna la posición mas hacia la izquierda
     */
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
