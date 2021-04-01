package idx_csd.Algo.Pro_Index;

/**
 * @author :Yankai CHEN
 * @class :MUFNode
 * @date :Created in 29/10/2019
 */

public class MUFNode {
    private int id;
    private int core;
    private int rank;
    private int group;
    private int hook;
    private MUFNode father;

    public MUFNode(int id, int core){
        this.core = core;
        this.rank = 0;
        this.id = id;
        this.group = id;
        this.hook = id;
        this.father = this;
    }

    public void updateCore(int core){this.core = core;}

    public void resetRank(){this.rank = 0;}

    public void setFather(MUFNode x){this.father = x;}

//    public void setxAnchor(int id){this.xAnchor = id;}

    public void setGroup(int id){ this.group = id;}

    public void setHook(int x){this.hook = x;}

    public void addRank(){this.rank = this.rank + 1;}

    public int getID(){return this.id;}

    public int getCore(){return this.core;}

    public MUFNode getFather(){return this.father;}

    public int getRank(){return this.rank;}

    public int getGroup(){return this.group;}

    public int getHook(){return this.hook;}

}
