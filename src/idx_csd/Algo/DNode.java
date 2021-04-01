package idx_csd.Algo;

import java.util.*;

/**
 * @author :Yankai CHEN
 * @class :DNode
 * @date :Created in 17/10/2019
 */

public class DNode {
    private int lCore = -1;
    private int nodeID = -1;
//    private int[] vertices = null;
    private Set<Integer> vertices = null;
    private Set<DNode> childList = null;
    private DNode father = null;

    public DNode(int core){
        this.lCore = core;
        this.father = this;
        childList = new HashSet<DNode>();
        vertices = new HashSet<Integer>();
    }

    public DNode(int core, int nodeID){
        this.lCore = core;
        this.father = this;
        this.nodeID = nodeID;
        childList = new HashSet<DNode>();
        vertices = new HashSet<Integer>();
    }


    public void setNodeID(int x){ this.nodeID = x; }

    public void setFather(DNode father){ this.father = father;}


    public void addVertices(int x){this.vertices.add(x);}

    public void addChild(DNode node){ this.childList.add(node); }

    public void setChildList(List<DNode> list){ this.childList.addAll(list); }

    public int getlCore(){ return this.lCore; }

    public int getNodeID(){ return this.nodeID; }

    public DNode getFather(){ return this.father; }

    public Set<Integer> getVertices(){ return vertices; }

    public Set<DNode> getChildList(){ return childList; }

}


