package idx_csd.Algo.Basic_Index;

import idx_csd.Algo.DNode;
import idx_csd.Algo.Decomposition;
import idx_csd.Config;
import idx_csd.Util.DataReader;

import java.io.*;
import java.util.*;


/**
 * @author :Yankai CHEN
 * @class :Basic_Index
 * @date :Created in 04/12/2019
 */

public class Basic_Index {
    private int nodeIDIdx = 0;
    private int n = -1;
    private int kMax = -1;
    private int lMax = -1;
    private int[] core = null;
    private int[] idVec = null;
    private int[][] inGraph = null;
    private int[][] outGraph = null;

    //variables for building index
    private Decomposition decomp;
    private Map<Integer, Map<Integer, DNode>> forest = null;
    private Map<Integer, DNode> rootMap = null;

    public Basic_Index(){
        this.forest = new HashMap<>();
        this.rootMap = new HashMap<>();
    }

    public Basic_Index(Decomposition decomposition){
        this.decomp = decomposition;
    }


    /*********************** build the index ****************************/
    private void initialize(int[] core_p, int[] id_vec_p,int[][] inG, int[][] outG){
        this.core = core_p;
        this.idVec = id_vec_p;
        this.inGraph = inG;
        this.outGraph = outG;
        n = outGraph.length;
        lMax = core[idVec[0]];
    }


    /**
     * description: build the index
     */
    public void buildIndex(String indexFile){
        decomp.calculate_K0();
        int[] row = decomp.inDependentRows();
        kMax = row[row.length - 1];
        try {
            BufferedWriter writer = null;
            if(indexFile != null)
                writer = new BufferedWriter(new FileWriter(indexFile, true));

            for (int k : row) {
                int[] sortedID = decomp.calculate_kl(k);
                int[] subLTemp = decomp.getSubL();
                int[][] inGrpah = decomp.getInGraph();
                int[][] outGraph = decomp.getOutGraph();
                initialize(subLTemp, sortedID, inGrpah, outGraph);
                DNode root = buildTree(k);
                if (Config.WRITE2DISK && writer != null) {
                    System.out.println(k);
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
        }catch(Exception e){
            e.printStackTrace();
        }
    }



    /**
     * description: Basic tree construction algorithm
     */
    private DNode buildTree(int k){
        //step 1: build the root node
        DNode root = new DNode(-1);
        Set<Integer> restNodeSet = new HashSet<>();

        for (int id : idVec) {
            restNodeSet.add(id);
        }

        //step 2: recursively build the tree
        buildChild(root, restNodeSet, k,0);

        //step 3: delete empty nodes
        compress(root);

        //step 4: attach node id for writing the index
        attachNodeID(root);

        return root;
    }



    /**
     * description: recursively build the tree nodes
     */
    private void buildChild(DNode root, Set<Integer> restNodeSet, int k, int l){
        List<Set<Integer>> rsList = findCC(restNodeSet, k, l);

        for(Set<Integer>set: rsList){
            DNode node = new DNode(l);
            Set<Integer> newRestVertexSet = new HashSet<>();
            List<Integer> list = new ArrayList<>();
            for(int x: set){
                if(core[x] == l) {
                    node.addVertices(x);
                }
                else
                    newRestVertexSet.add(x);
            }
            root.addChild(node);
            node.setFather(root);
            if(!newRestVertexSet.isEmpty() && l < lMax)
                buildChild(node, newRestVertexSet, k,l + 1);
        }
    }


    /**
     * description: find the connected component of a given vertexSet
     */
    private List<Set<Integer>> findCC(Set<Integer> vertexSet, int k, int l){
        List<Set<Integer>> rsList = new ArrayList<>();

        boolean[] visit = new boolean[n];

        while(!vertexSet.isEmpty()){
            Set<Integer> ccSet = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            Iterator<Integer> iter = vertexSet.iterator();
            int seedID = iter.next();

            //initialize
            queue.add(seedID);
            iter.remove();
            ccSet.add(seedID);
            visit[seedID] = true;

            //search
            while(!queue.isEmpty()){
                int id = queue.poll();
                for(int i = 0; i < outGraph[id].length; i++){
                    int outNgh = outGraph[id][i];
                    if(!visit[outNgh] && core[outNgh] >= l && vertexSet.contains(outNgh)){
                        queue.add(outNgh);
                        vertexSet.remove(outNgh);
                        ccSet.add(outNgh);
                        visit[outNgh] = true;
                    }
                }
                for(int i = 0; i < inGraph[id].length; i++){
                    int inNgh = inGraph[id][i];
                    if(!visit[inNgh] && core[inNgh] >= k && vertexSet.contains(inNgh)){
                        queue.add(inNgh);
                        vertexSet.remove(inNgh);
                        ccSet.add(inNgh);
                        visit[inNgh] = true;
                    }
                }
            }
            rsList.add(ccSet);
        }
        return rsList;
    }


    /**
     * description: if the tree node contains no vertex, we delete it from the tree
     */
    private void compress(DNode root){
        for(DNode child: root.getChildList()){
            compress(child);
        }
        if(!root.getChildList().isEmpty()){
            List<DNode> newChildList = new ArrayList<>();
            for(DNode child: root.getChildList()){
                if(child.getVertices() == null || child.getVertices().size() == 0){
                    newChildList.addAll(child.getChildList());
                }else
                    newChildList.add(child);
            }
            root.setChildList(newChildList);
            for(DNode newChild: newChildList)
                newChild.setFather(root);
        }
    }



    /**
     * description: attach Node ID for write the index
     */
    private void attachNodeID(DNode root){
        root.setNodeID(nodeIDIdx++);
        for(DNode child: root.getChildList())
            attachNodeID(child);
    }



    /***************************functions for output and input the index********************************/
    /**
     * description: recursively print the index
     */
    private void traversePrint(DNode node, String indent){
        System.out.print(indent + "l="+ node.getlCore() + " V size (" + node.getVertices().size() + "): ");
        for(int x: node.getVertices()) {
            System.out.print((char) (x+65) + " ");
        }
        System.out.println();

        for(DNode x: node.getChildList())
            traversePrint(x, indent+"\t");
    }


    private void printIndex(){
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
            for(Map.Entry<Integer, DNode> mapEntry: map.entrySet()){
                if(mapEntry.getValue().getVertices() == null)
                    System.out.println(mapEntry.getKey() + " 0" );
                else
                    System.out.println(mapEntry.getKey() + " " + (mapEntry.getValue().getVertices()).size());
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
            String line = "";

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
                        else{
                            String[] tmp = lineStr.split("\t");
                            int lcore = Integer.parseInt( tmp[0] );
                            int nodeID = Integer.parseInt( tmp[1] );
                            int fatherID = Integer.parseInt( tmp[2] );

                            if(tmp.length > 3) {
                                String[] vertexStrArr = tmp[3].split(" ");
                                DNode node = new DNode(lcore);
                                for (int i = 0; i < vertexStrArr.length; i++){
                                    int x =  Integer.parseInt(vertexStrArr[i]);
                                    node.addVertices(x);
                                }
                                nodeIDMap.put(nodeID, node);
                                childFatherMap.put(nodeID, fatherID);
                            }else{
                                DNode node = new DNode(lcore);
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



    /****************************** query communities from the index *************************************/
    /**
     * description: recursively add new vertices into the subgraph
     */
    private void recursiveGetVertex(DNode root, Set<Integer> subgraph){
        for(int x: root.getVertices()) subgraph.add(x);
        for(DNode child: root.getChildList())
            recursiveGetVertex(child, subgraph);
    }
    /**
     * description: get the (k,l)-core from the index
     */
    public Set<Integer> getCommunity(int queryVertex, int k, int l){
        Set<Integer> result = new HashSet<>();
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
        recursiveGetVertex(root,result);
        return result;
    }






    public static void main(String[] args){
//        Basic_Index index = new Basic_Index();
//        index.test();

//        String indexFile = "C:\\workspace\\IDX_CSD\\test_data\\index.txt";
        String indexFile = "/Users/chenyankai/Workspace/IDX_CSD/test_data/basic_index.txt";

        DataReader dataReader = new DataReader(Config.small_t_degree_1, Config.small_t_graph_1);
        dataReader.read_graph();
        Decomposition decomp = new Decomposition(dataReader.get_inGraph(), dataReader.get_outGraph());
        Basic_Index BSCIndex = new Basic_Index(decomp);
        Config.WRITE2DISK = true;
        BSCIndex.buildIndex(indexFile);
        System.out.println("done");

        Basic_Index index = new Basic_Index();
        index.readIndex(indexFile);
        index.printIndex();
//        Set<Integer> result = index.getCommunity(3,1,1);
//        for(int x: result){
//            System.out.print((char) (x+65) + " ");
//        }

    }



}
