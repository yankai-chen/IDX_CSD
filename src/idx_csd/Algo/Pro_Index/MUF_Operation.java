package idx_csd.Algo.Pro_Index;

/**
 * @author :Yankai CHEN
 * @class :MUF_Operation
 * @date :Created in 29/10/2019
 */

public class MUF_Operation {

    public MUFNode find(MUFNode x){
        if(x.getFather() != x)
            x.setFather(find(x.getFather()));
        return x.getFather();
    }


    public void union(MUFNode x, MUFNode y, int[] core){
        MUFNode xRoot = find(x);
        MUFNode yRoot = find(y);
        if(xRoot == yRoot) return;


        int xGroup = xRoot.getGroup();
        int yGroup = yRoot.getGroup();
        if(xRoot.getRank() < yRoot.getRank()){
            xRoot.setFather(yRoot);
            if(core[ xGroup] > core[ xGroup ])
                yRoot.setGroup(xGroup);

        }else if(xRoot.getRank() > yRoot.getRank()){
            yRoot.setFather(xRoot);
            if(core[ xGroup ] < core[ xGroup ])
                xRoot.setGroup(yGroup);
        }else{
            yRoot.setFather(xRoot);
            xRoot.addRank();
            if(core[ xGroup ] > core[ xGroup ])
                yRoot.setGroup(xGroup);
        }




    }
}
