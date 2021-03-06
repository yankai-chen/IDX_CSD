package idx_csd.Algo;

import java.util.Arrays;

/**
 * @author :Yankai CHEN
 * @class :idx_csd.Algo.Decomposition
 * @date :Created in 13/10/2019
 */

public class Decomposition {
    private int nodesNum, Kmd;
    private int kCoreMax = 0;

    private int[] Kdeg, kBin; //used to calculate all (k,0)-cores
    //Kdeg stores all vertices's max possible K, where there is a (K,0)-core contains it
    //kBin is used to do a bucket sort for all vertices in ascending order of their Kdeg

    private int [] bin, pos, vert;//used to calculate rows
    //give a certain K,
    //bin is a bucked sort in ascending number of vertices' max L where there is a (K,L)-core contains it
    //pos: which bin a vertex is in
    //vert: the array of all vertices in this row(namely in (K,0)-core)

    private int[][] inGraph, outGraph;            //inGraph records a vertex v 's neighbor u which a in edge is from u to v -->v's in edge
    private int[] subLDegree, subKDegree;           //record a vertex 's out and in degree in current subgraph
    private boolean [] visited;

    //we do D-core decomposition row by row
    //one row is, a certain K, all the (K, l) cores sorted by ascending order of l
    private int[] rowVert;                          //store vertices in one row
    private boolean [] isInSub;                       //used in getRow, judge whether a vetex satisfy the basic k constraint (namely in (k,0)-core)
    private int rowResultPos;                    //a mark variable used to fill a row result into rowResult[]
    private int [] sorted_ID;                //a strictly sorted array, sorted by out-core number
    private int [] compulsoryVisit;
    private int compulsorySubL,compulsoryNum;


    public Decomposition(int[][] inG, int[][] outG) {
        this.inGraph = inG;
        this.outGraph = outG;
        this.nodesNum = inGraph.length;
        visited = new boolean[nodesNum];
        isInSub = new boolean[nodesNum];
        bin = new int[nodesNum + 1];
        pos = new int[nodesNum + 1];   // the position of a vertex in vert
        vert = new int[nodesNum];  //the sorted nodes array
        subLDegree = new int[nodesNum];
        subKDegree = new int[nodesNum];
        compulsoryVisit = new int[nodesNum];

        kBin = new int[nodesNum];
        Kdeg = new int[nodesNum];

        Kmd = -1;                     //max K degree of the whole graph;
        // start from index 0
        for(int i = 0;i < nodesNum; i++){
            subLDegree[i] = outGraph[i].length;
            subKDegree[i] = inGraph[i].length;
            if(subKDegree[i] > Kmd) Kmd = subKDegree[i];
            visited[i] = false;
            isInSub[i] = true;
        }
    }



    public void calculateInfo(){
        System.out.println("NodesNum: " + this.nodesNum);
        int numOfEdge = 0;;
        for(int i = 0;i<inGraph.length;i++){
            numOfEdge += inGraph[i].length;
        }
        double degree = numOfEdge * 1.0 / nodesNum ;
        System.out.println("EdgeNum: " + numOfEdge);
        System.out.println("Degree: " + degree);
        System.out.println("kCoreMax: " + this.kCoreMax);
    }



    /**
    * description: This function is used to calculate all nodes's Max K-core-number when there is no out degree constraint;
     * Namely, calculate all (k,0)-core for any possible k
    */
    public int[] calculate_K0() {
        for (int i = 0; i < nodesNum; i++) {
            Kdeg[i] = inGraph[i].length;
            if (Kdeg[i] > Kmd) Kmd = Kdeg[i];
        }
        for (int i = 0; i < nodesNum; i++) {
            kBin[Kdeg[i]] += 1;
        }
        int start = 0;       //initialize each bin's start position
        for (int i = 0; i <= Kmd; i++) {
            int num = kBin[i];
            kBin[i] = start;
            start += num;
        }
        //fill bucket's with nodes
        for (int i = 0; i < nodesNum; i++) {
            pos[i] = kBin[Kdeg[i]];
            vert[pos[i]] = i;
            kBin[Kdeg[i]] += 1;
        }
        //re-update bin to store each bucket's start position
        for (int i = Kmd; i > 0; i--) {
            kBin[i] = kBin[i - 1];
        }
        kBin[0] = 0;
        // decompose

        for (int i = 0; i < nodesNum; i++) {
            int v = vert[i];
            int [] vNeighbor = outGraph[v];
            for (int u : vNeighbor) {  //Note there we should use out link to find (v,u) and reduce u's Kdeg
                if (Kdeg[u] > Kdeg[v]) {
                    int du = Kdeg[u], pu = pos[u], pw = kBin[du], w = vert[pw];
                    pos[u] = pw;    vert[pu] = w;
                    pos[w] = pu;    vert[pw] = u;
                    kBin[du] += 1;           //pull that higher-order vertex back, and the start position consequently +1
                    Kdeg[u] -= 1;
                }
            }
        }
        kCoreMax = Kdeg[vert[vert.length-1]]; //record the max k where there is a (k,0) core. It is actually the end element of vert
        return Kdeg;
    }



    /**
    * description:  Based on a list (sorted by out degree) of the nodes in (K,0) subgraph, begin to process this K-th row
    */
    public int [] calculate_kl(int k) {
        rowResultPos = 0;
        rowVert = Arrays.copyOfRange(vert,kBin[k],vert.length);
        sorted_ID = new int [rowVert.length];
        for(int i = 0;i<= nodesNum;i++)  {
            bin[i] = 0;       //re- initialize bin for D-core use
            pos[i] = 0;
        }
        for(int i=0;i<nodesNum;i++){
            visited[i] = false;
            isInSub[i] = true;
        }

        for(int i = 0;i<kBin[k];i++){                           //record subgraph,
            isInSub[vert[i]] = false;
        }
        rowVert = sort_degree();
        for (int value : rowVert) {  //decompose
            compulsoryNum = 0; compulsorySubL = -1;
            visit(k, value, -1);
            for (int q = 0; q < compulsoryNum; q++) {
                visited[compulsoryVisit[q]] = false;
                visit(k, compulsoryVisit[q], compulsorySubL);
            }
        }
        return sorted_ID;
    }



    /**
    * description: This function get rows' No. for those rows that are independent
     *   Namely, some vertex(es) exist in this row but not any row with higher row's No.
     *   This is used to do a prune operation, avoid calculating some rows which are exactly same, mentioned in CSD paper
    */
    public int [] inDependentRows(){
        int record = -1;
        int [] result = new int [nodesNum];
        int resultPos = 0;
        for(int i = 0; i< vert.length;i++){
            if(Kdeg[vert[i]] != record){
                result[resultPos] = Kdeg[vert[i]];
                resultPos ++;
                record  = Kdeg[vert[i]];
            }
        }
        result[0] = -1;
        int maxK = record;
        for(int i = 1;i<resultPos;i++){
            result[i] = result[i-1]+1;
        }
        result[resultPos] = maxK;
        return Arrays.copyOfRange(result,1,resultPos+1);
    }



    /**
    * description: Visit and report and delete
    */
    private void  visit(int k, int v, int compulsory) {
        int vid = v;
        if(visited[vid]) return;
        else
        {
            if(compulsory != -1) subLDegree[vid] = compulsory;
            compulsorySubL = subLDegree[vid];
            visited[vid] = true;
            sorted_ID[ rowVert.length - 1 - rowResultPos] = vid;
            rowResultPos++;
            for(int i = 0;i<inGraph[vid].length;i++) {             //v's inLink is its neighbor's outlink

                int neighV = inGraph[vid][i];
                if(!isInSub[neighV]) continue;
                if (!visited[neighV] && subLDegree[neighV] > subLDegree[vid]) {
                    int du = subLDegree[neighV], pu = pos[neighV], pw = bin[du], w = rowVert[pw];
                    pos[neighV] = pw;   rowVert[pw]= neighV;
                    pos[w] = pu;    rowVert[pu] = w;
                    subLDegree[neighV] -= 1;
                    bin[du] += 1;
                }
            }
            for(int i = 0;i<outGraph[vid].length;i++) {
                int neighV = outGraph[vid][i];
                if(!isInSub[neighV]) {
                    continue;
                }
                //Here maintain a compulsory queue, all vertices in this queue should be visited and deleted before we enter net visit()method.
                if(!visited[neighV]){
                    if(subLDegree[vid] < subLDegree[neighV])
                        subKDegree[neighV] -= 1;
                    if(subKDegree[neighV] < k) {
                        subKDegree[neighV]++;
                        compulsoryVisit[compulsoryNum] = neighV;
                        compulsoryNum++;
                        subLDegree[neighV] = subLDegree[vid];
                        visited[neighV] = true;
                    }
                }
            }
        }
    }


    /**
    * description: When process the K-th row of D-core Table, this fucntion is used to sort all nodes by their l degree in (K,0) core SUBGRAPH
    */
    private int [] sort_degree(){
        int []tempVert = new int [rowVert.length];
        int subMaxL = 0;
        for(int i = 0;i<= nodesNum;i++)  bin[i] = 0;       //re- initialize bin for D-core use
        //calculate all vertex's subK&L Degree in current subgraph
        for (int v : rowVert) {
            int subL = outGraph[v].length;
            int subK = inGraph[v].length;
            for (int l = 0; l < outGraph[v].length; l++) {
                if (!isInSub[outGraph[v][l]]) subL--;
            }
            for (int k = 0; k < inGraph[v].length; k++) {
                if (!isInSub[inGraph[v][k]]) subK--;
            }
            subLDegree[v] = subL;
            subKDegree[v] = subK;
            if (subL > subMaxL) subMaxL = subL;
            bin[subL] += 1;

        }
        int start = 0;
        for(int d = 0; d <= subMaxL;d ++){
            int num = bin[d];
            bin[d] = start;
            start += num;
        }
        for (int v : rowVert) {   //fill the array tempVert with sorted vertex
            pos[v] = bin[subLDegree[v]];
            tempVert[pos[v]] = v;
            bin[subLDegree[v]] += 1;
        }

        for(int d = subMaxL ; d > 0; d--) bin[d] = bin[d - 1];

        bin[0] = 0;
        return tempVert;
    }



    public int[] getSubL(){ return subLDegree; }

    public int getNodesNum() {return nodesNum;}

    public int[] getSubK(){ return subKDegree; }

    public int[] getSortedID(){return sorted_ID;}

    public int getKCoreMax() {return kCoreMax;}

    public int[][] getInGraph(){ return  inGraph;}

    public int[][] getOutGraph(){return  outGraph;}

}

