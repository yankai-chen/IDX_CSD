package idx_csd.Algo.Pro_Index;

import idx_csd.Algo.Basic_Index.Basic_Index;
import idx_csd.Algo.DNode;
import idx_csd.Algo.Decomposition;
import idx_csd.Config;
import idx_csd.Util.DataReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

/**
 * @author :Yankai CHEN
 * @class :Pro_Index
 * @date :Created in 29/10/2019
 */

public class Pro_Index {
    //temporary variable for building one tree
    private int n = -1;
    private int kMax = -1;
    private int[] core = null;
    private int[] idVec = null;
    private int[][] outGraph = null;
    private Set<Integer> tmpPool = null;
    private Map<Integer, DNode> DNodeTrack = null;
    private MUF_Operation MUF = null;
    private int reprNum = 0;



    //variables for building index
    private Decomposition decomp = null;
    private Map<Integer, MUFNode> MUFMap = null;
    private Map<Integer, DNode> rootMap = null;
    private Map<Integer, Map<Integer, DNode>> forest = null;

    public Pro_Index(){
        this.forest = new HashMap<>();
        this.rootMap = new HashMap<>();
    }


    public Pro_Index(Decomposition decomp){
        this.decomp = decomp;
        this.MUFMap = new HashMap<>();
    }


    /************************** build the index ***********************************/
    private void initialize(int[] core_p, int[] id_vec_p, int[][] outG){
        this.core = core_p;
        this.idVec = id_vec_p;
        this.outGraph = outG;
        this.n = idVec.length;
        this.tmpPool = new HashSet<>();
        for(int x: idVec) tmpPool.add(x);
        this.MUF = new MUF_Operation();
        this.DNodeTrack = new HashMap<>();
    }


    /**
     * description: build the index
     */
    public void buildIndex(String indexFile){
        decomp.calculate_K0();
        int[] ks = decomp.inDependentRows();
        kMax = ks[ks.length - 1];
        forest = new HashMap<>();
        forest.put(kMax, new HashMap<Integer, DNode>());

        try {
            BufferedWriter writer = null;
            if(indexFile != null)
                writer = new BufferedWriter(new FileWriter(indexFile, true));

            int[] sortedID = decomp.calculate_kl(kMax);
            int[] subLTemp = decomp.getSubL();
            int[][] outGraph = decomp.getOutGraph();
            initialize(subLTemp, sortedID, outGraph);
            DNode root = MUFInit(kMax);
            if (Config.WRITE2DISK && writer != null) {
                writer.write("k " + kMax);
                writer.newLine();
                traverseWriteFile(root, writer);
                writer.write("#\n");
                writer.flush();
            }

            if (ks.length == 1) return;

            for (int k = ks[ks.length - 2]; k >= 0; k--) {
//                System.out.println("k: " + k);
                forest.put(k, new HashMap<Integer, DNode>());
                sortedID = decomp.calculate_kl(k);
                subLTemp = decomp.getSubL();
                initialize(subLTemp, sortedID, outGraph);
                root = MUFUpdate(k);
                if (Config.WRITE2DISK && writer != null) {
                    writer.write("k " + k);
                    writer.newLine();
                    traverseWriteFile(root, writer);
                    writer.write("#\n");
                    writer.flush();
                }
            }
            if(writer != null){
                writer.flush();
                writer.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    /**
    * description: union vertices in [startIdx, endIdx] for initializing MUFF
    */
    private void initUnionVertices(int startIdx, int endIdx){
        int curCore = core[ idVec[startIdx] ];
        for(int i = startIdx; i < endIdx; i++){
            int id = idVec[i];
            MUFNode x = MUFMap.get(id);
            for(int nghID: outGraph[id]){
                if(!tmpPool.contains(nghID)) continue;
                if(core[nghID] >= curCore){
                    MUFNode y = MUFMap.get(nghID);
                    MUF.union(x, y, core);
                }
            }
        }
    }



    /**
     * description: find all child nodes of each vertices in advance
     */
    private Map<Integer, Set<DNode>> findChildNode(int k, int startIdx, int endIdx){
        Map<Integer, Set<DNode>> childMap = new HashMap<>();
        for(int i = startIdx; i < endIdx; i++) {
            int id = idVec[i];
            childMap.put(id, new HashSet<DNode>());

            for(int nghID: outGraph[id]){
                if(!tmpPool.contains(nghID)) continue;
                if(core[nghID] > core[id]) {
                    MUFNode MUFnghRoot = MUF.find( MUFMap.get(nghID) );
                    DNode child = forest.get(k).get(MUFnghRoot.getHook());
                    childMap.get(id).add(child);
                }
            }
        }
        return childMap;
    }



    /**
     * description: union vertices in [startIdx, endIdx] for updating MUFF
     */
    private void updateUnionVertices(int startIdx, int endIdx){
        //restore the memory of previous vertices
        List<Integer> V = new ArrayList<>();
        List<Integer> Vminus = new ArrayList<>();
         for(int i = startIdx; i < endIdx; i++){
            int id = idVec[i];
            int curCore = core[id];

            if(MUFMap.containsKey(id)){
                MUFNode mufNode = MUFMap.get(id);
                if(curCore == mufNode.getCore()){
                    mufNode.setHook(id);
                    mufNode.resetRank();
                    mufNode.setFather(mufNode);
                    V.add(id);
                }
                else {
                    mufNode.setHook(id);
                    mufNode.resetRank();
                    mufNode.setFather(mufNode);
                    mufNode.setGroup(id);
                    mufNode.updateCore(curCore);
                    Vminus.add(id);
                }
            }else{
                Vminus.add(id);
                MUFMap.put(id, new MUFNode(id, curCore));
            }
        }

         //union
        for(int id: V){
            MUFNode MUFID = MUFMap.get(id);
            MUFNode groupMUF = MUFMap.get( MUFID.getGroup() );
            MUF.union(MUFID, groupMUF, core);
        }

        for(int id: Vminus){
            MUFNode x = MUFMap.get(id);
            for(int nghID: outGraph[id]){
                if(!tmpPool.contains(nghID)) continue;
                if(core[nghID] >= core[id]) {
                    MUFNode y = MUFMap.get(nghID);
                    MUF.union(x, y, core);
                }
            }
        }
    }



    /**
     * description: handle vertices in range [startIdx, endIdx] with the same core number for updating MUF
     */
    private void processVertices(int k, int startIdx, int endIdx, Set<Integer> restDNodeSet, Map<Integer, Set<DNode>>childMap){
        int curCore = core[ idVec[startIdx] ];
//        System.out.println(k+  "  current core: "+ curCore);
        Map<Integer, DNode> tmpRootDNodeMap = new HashMap<>();
        Set<Integer> toDelete = new HashSet<Integer>();
        for(int i = startIdx; i < endIdx; i++){
            int id = idVec[i];
            MUFNode curMUFNode = MUFMap.get(id);
            MUFNode MUFRoot = MUF.find(curMUFNode);
            int MUFRootID = MUFRoot.getID();
            //step 1: create nodes
            if(!tmpRootDNodeMap.containsKey(MUFRootID)) {
                DNode node = new DNode(curCore, reprNum);
                tmpRootDNodeMap.put(MUFRootID, node);
                DNodeTrack.put(reprNum, node);
                restDNodeSet.add(reprNum);
                reprNum++;
            }
            DNode node = tmpRootDNodeMap.get(MUFRootID);
            forest.get(k).put(id, node);
            node.addVertices(id);

            //step 2: find child nodes and link them up by using element "anchor"
            for(DNode child: childMap.get(id)){
                node.addChild(child);
                child.setFather(node);
                restDNodeSet.remove(child.getNodeID());
            }
        }


        //update CUF and restNodeSet
        for(int i = startIdx; i < endIdx; i++) {
            int id = idVec[i];
            MUFNode curMUFNode = MUFMap.get(id);
            MUFNode MUFRoot = MUF.find(curMUFNode);
            curMUFNode.setGroup(MUFRoot.getGroup());

            if(core[MUFRoot.getHook()] > core[id]){
                MUFRoot.setHook(id);
            }
        }
        restDNodeSet.removeAll(toDelete);
    }

    private String convet(int x){
        if(x>24) return x+"";
        return (char) (x+65) + "";
    }

    /**
    * description: build the initial MUF forest and build the initial index
    */
    private DNode MUFInit(int k){
        //step 1: initialize the MUF forest
        for(int i = 0; i < n; i++){
            int id = idVec[i];
            int coreNum = core[id];
            MUFNode mufNode = new MUFNode(id, coreNum);
            MUFMap.put(id, mufNode);
        }
        //step 2: buiLd the initial MUF forest
        Set<Integer> restNodeSet = new HashSet<>();
        int startIdx = 0;
        for(int idx = 0; idx < n; idx++) {
            int curID = idVec[idx];
            int curCore = core[curID];
            int nextIdx = idx + 1;
            if (nextIdx < n) {
                int nextId = idVec[nextIdx];
                int nextCore = core[nextId];
                if (nextCore < curCore) {
                    Map<Integer, Set<DNode>> childMap = findChildNode(k, startIdx, nextIdx);
                    initUnionVertices(startIdx, nextIdx);
                    processVertices(k, startIdx, nextIdx,restNodeSet, childMap);
                    startIdx = nextIdx;
                }
            } else if (nextIdx == n) {
                Map<Integer, Set<DNode>> childMap = findChildNode(k, startIdx, nextIdx);
                initUnionVertices(startIdx, nextIdx);
                processVertices(k, startIdx, nextIdx,restNodeSet, childMap);
            }
        }

        //step 3: create root index node
        DNode root = new DNode(-1, reprNum);

        for(int x: restNodeSet){
            DNode node = DNodeTrack.get(x);
            root.addChild(node);
            node.setFather(root);
        }
        reprNum = 0;
        return root;
    }


    /**
    * description: based on existing MUF structure, update it and create according index
    */
    private DNode MUFUpdate(int k){
        //step 1: restart the temporary variables
        int startIdx = 0;
        Set<Integer> restNodeSet = new HashSet<>();
        //step 2: update the MUFF
        for(int idx = 0; idx < n; idx++){
            int curID = idVec[idx];
            int curCore = core[curID];
            int nextIdx = idx + 1;
            if(nextIdx < n){
                int nextID = idVec[nextIdx];
                int nextCore = core[nextID];
                if(nextCore < curCore){
                    Map<Integer, Set<DNode>> childMap = findChildNode(k, startIdx, nextIdx);
                    updateUnionVertices(startIdx, nextIdx);
                    processVertices(k, startIdx, nextIdx,restNodeSet, childMap);
                    startIdx = nextIdx;
                }
            }else if(nextIdx == n){
                Map<Integer, Set<DNode>> childMap = findChildNode(k, startIdx, nextIdx);
                updateUnionVertices(startIdx, nextIdx);
                processVertices(k, startIdx, nextIdx,restNodeSet, childMap);
                }
            }


        //step 3: create root node
        DNode root = new DNode(-1, reprNum);

        for(int x: restNodeSet){
            DNode node = DNodeTrack.get(x);
            root.addChild(node);
            node.setFather(root);
        }
        reprNum = 0;
        return root;

    }



    /***************************functions for output and input the index********************************/
    private void traversePrint(DNode node, String indent){
        System.out.print(indent + "l="+ node.getlCore() + " V:");
        for(int x: node.getVertices()) {
            System.out.print((char) (x+65) + " ");
        }
        System.out.println();

        for(DNode x: node.getChildList())
            traversePrint(x, indent+"\t");
    }
    public void printIndex(){
        for(Map.Entry<Integer, DNode> entry: rootMap.entrySet()){
            int k = entry.getKey();
            DNode root = entry.getValue();
            System.out.println(k+"-th tree: ");
            traversePrint(root, "\t");
        }

        System.out.println();
        System.out.println();

        for(Map.Entry<Integer, Map<Integer, DNode>> entry: forest.entrySet()){
            int k = entry.getKey();
            Map<Integer, DNode> map = entry.getValue();
            System.out.println("invertedMap of " + k +"-th tree: ");
            for(Map.Entry<Integer,DNode> mapEntry: map.entrySet()){
                System.out.println(mapEntry.getKey() + " "+ mapEntry.getValue().getVertices().size());
            }
            System.out.println();
        }
    }


    /**
     * description: print index to the file
     */
    private void traverseWriteFile(DNode node,  BufferedWriter stdNodeOut){
        try {
            int lcore = node.getlCore();
            int nodeID = node.getNodeID();
            Set<Integer> vertices = node.getVertices();
            int fatherNodeID = node.getFather().getNodeID();
            String line = lcore + "\t" + nodeID + "\t" + fatherNodeID;
            stdNodeOut.write(line);
            stdNodeOut.flush();
            if(vertices != null && vertices.size() > 0) {
                int first = vertices.iterator().next();
                stdNodeOut.write("\t" + first);
                stdNodeOut.flush();
                for(int x : vertices) {
                    if(x == first) continue;
                    stdNodeOut.write(" " + x);
                    stdNodeOut.flush();
                }
            }
            stdNodeOut.newLine();
            stdNodeOut.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(DNode child: node.getChildList())
            traverseWriteFile(child, stdNodeOut);
    }


    /**
     * description: load an index from the local file
     */
    public void readIndex(String indexFile){
        try{
            BufferedReader stdIn = new BufferedReader(new FileReader(indexFile));
            String line;

            int k = -1;
            List<String> strList = new ArrayList<>();
            while((line = stdIn.readLine()) != null){
                if(!line.contains("#"))
                    strList.add(line);
                else{
                    //for records
                    Map<Integer, DNode> nodeIDMap = new HashMap<>();
                    Map<Integer, Integer> childFatherMap = new HashMap<>();

                    Map<Integer, DNode> tree = new HashMap<>();
                    for(String lineStr : strList) {
                        if (lineStr.contains("k"))
                            k = Integer.parseInt(lineStr.split(" ")[1]);
                        else {
                            String[] tmp = lineStr.split("\t");
                            int lcore = Integer.parseInt(tmp[0]);
                            int nodeID = Integer.parseInt(tmp[1]);
                            int fatherID = Integer.parseInt(tmp[2]);

                            if (tmp.length > 3) {
                                String[] vertexStrArr = tmp[3].split(" ");
                                DNode node = new DNode(lcore, vertexStrArr.length);
                                for (int i = 0; i < vertexStrArr.length; i++) {
                                    int x = Integer.parseInt(vertexStrArr[i]);
                                    node.addVertices(x);
                                }
                                nodeIDMap.put(nodeID, node);
                                childFatherMap.put(nodeID, fatherID);
                            }
                            else{
                                DNode node = new DNode(lcore, 0);
                                nodeIDMap.put(nodeID, node);
                                childFatherMap.put(nodeID, fatherID);
                            }
                        }

                    }
                    //link nodes up
                    for(int key: childFatherMap.keySet()){
                        int value = childFatherMap.get(key);
                        DNode child = nodeIDMap.get(key);
                        for(int x: child.getVertices()) tree.put(x, child);
                        if( key == value )
                            rootMap.put(k, child);
                        else{
                            DNode father = nodeIDMap.get(value);
                            child.setFather(father);
                            father.addChild(child);
                        }
                    }
                    forest.put(k, tree);
                    strList.clear();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<Integer, Map<Integer, DNode>> getForest(){ return this.forest; }



    /******************** query communities from the index ***********************/
    /**
     * description: get the (k,l)-core from the index
     */
    public List<Integer> getCommunity(int queryVertex, int k, int l){
        List<Integer> result = new LinkedList<Integer>();
        int idxK = k;
        while(!forest.containsKey(idxK)){
            if(idxK < kMax) idxK ++;
            else return null;
        }
        if(!forest.get(idxK).containsKey(queryVertex)) return null;
        DNode root = forest.get(idxK).get(queryVertex);
        if(root.getlCore() < l) return null;
        while (root.getlCore() != 0 && root.getFather().getlCore() >= l)
            root = root.getFather();

        Queue<DNode> queue = new LinkedList<DNode>();
        queue.add(root);
        while(!queue.isEmpty()){
            DNode node = queue.poll();
            for(int x: node.getVertices())
                result.add(x);
            queue.addAll(node.getChildList());
        }
        return result;
    }



    /******************** get funtions ***********************/
    public Pro_Index getIndex(){
        this.core = null;
        this.idVec = null;
        this.tmpPool.clear();
        this.DNodeTrack.clear();
        this.MUFMap.clear();
        return this;
    }

    public int[][] getOutGraph(){return this.outGraph;}


}
