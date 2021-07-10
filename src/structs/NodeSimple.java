package structs;

import java.util.ArrayList;

public class NodeSimple<T> {
    private T info;
    private ArrayList<NodeSimple<T>> branches;

    public NodeSimple(T info) {
        this.info = info;
        this.branches = new ArrayList<NodeSimple<T>>();
    }

    public NodeSimple() {
        this.info = null;
        this.branches = new ArrayList<NodeSimple<T>>();
    }

    public T getInfo() {
        return info;
    }

    public void settInfo(T info) {
        this.info = info;
    }

    public void addBranch(NodeSimple<T> branch){
        this.branches.add(branch);
    }

    public boolean isNext(){
        return this.branches.isEmpty();
    }

    public ArrayList<NodeSimple<T>> getBranches(){
        return this.branches;
    }
}
