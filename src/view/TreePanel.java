package view;

import structs.Element;
import structs.NTree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TreePanel<E> extends JPanel {

    private int ratio =15;
    private int verticalPadding =ratio*4;
    private NTree<E> nTree;
    private HashMap<Element<E>, Point> coords;
    private static final int FRAME_WIDTH = 800;

    public TreePanel(NTree<E> nTree) {
        this.nTree = nTree;
        this.coords =new HashMap<Element<E>, Point>();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!nTree.isEmpty()) {
            int width=800;
            if (nTree.getChilds(nTree.getRoot()).size()>1){
                draw(g, nTree.getRoot(),width/2,50,width/5,width-ratio*2,0);
            }else{
                draw(g, nTree.getRoot(),width-ratio*2,50,width/5,width-ratio*2,0);
            }
        }
    }

    private void draw(Graphics g, Element<E> node, int x, int y, int spaceH,int widthMax,int widthMin) {
        //g.drawOval(x- ratio,y- ratio,2* ratio,2* ratio);
        g.drawString(node.getElement() + "", x - 6, y + 4);
        ArrayList<Element<E>> childs= nTree.getChilds(node);
        coords.put(node,new Point(x,y));

        int temp=(widthMax-widthMin)/((childs.size()<2)?1:childs.size());
        boolean down=false;
        for (int i = childs.size()-1; i >=0 ; i--) {
           Element<E> child=childs.get(i);
           Point pointFather= coords.get(nTree.getFather(child));
           int t=widthMax-((childs.size()-1-i)*temp);
           int xY=(y+ verticalPadding);
            if (down){
               g.drawLine((pointFather.x),(pointFather.y+ratio),(t),(xY+ratio));
               draw(g,child,t,(xY+ratio*2),temp/childs.size(),t,t-temp);
            }else {
                g.drawLine((pointFather.x),(pointFather.y+ratio),(t),(xY-ratio));
                draw(g,child,t,xY,temp/childs.size(),t,t-temp);
            }
            down=!down;
        }
    }

    private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        double  d=Math.sqrt(verticalPadding * verticalPadding +(x2-x1)*(x2-x1));
        int xx1=(int)(x1- ratio *(x1-x2)/d);
        int yy1=(int)(y1- ratio *(y1-y2)/d);
        int xx2=(int)(x2+ ratio *(x1-x2)/d);
        int yy2=(int)(y2+ ratio *(y1-y2)/d);
        g.drawLine(xx1,yy1,xx2,yy2);
    }

}
