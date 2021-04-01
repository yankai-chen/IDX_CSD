package idx_csd.Util;
import idx_csd.Algo.Decomposition;

import java.io.*;
import java.util.*;

/**
 * @author :Yankai CHEN
 * @class :DataReader
 * @date :Created in 13/10/2019
 */
public class DataReader {
    private String degree_file;
    private String graph_file;
    //used in reading data, to create an array with exact length of neighbor's number
    private int numOfNodes, numOfEdges;
    private int [][] inGraph, outGraph;
    private int [] inNum, outNum;
    private BufferedReader graphReader;



    /**
    * description: take $sampleRate$ percent vertices from the data
     * when sampling, we need do a reflect to make vertices ID begins from 0 and keep continuous
    */
    public DataReader(String degreeFile, String graphFile){
        this.degree_file = degreeFile;
        this.graph_file = graphFile;
        numOfNodes = 0;
        numOfEdges = 0;
        try{
            graphReader = new BufferedReader(new FileReader(graph_file));
            String tempString = graphReader.readLine();
            String []splitString = tempString.split(" ");
            this.numOfNodes =Integer.parseInt( splitString[0] );
            inGraph = new int[numOfNodes][];
            outGraph = new int[numOfNodes][];
            inNum = new int[numOfNodes];
            outNum = new int [numOfNodes];
        }
        catch(IOException e){
            e.printStackTrace();
            return;
        }
    }

    //fast read file to load the outGraph matrix
    public DataReader(String graphFile){

        try{
            graphReader = new BufferedReader(new FileReader(graphFile));
            String tempString = graphReader.readLine();
            String []splitString = tempString.split(" ");
            this.numOfNodes =Integer.parseInt( splitString[0] );
            outGraph = new int[numOfNodes][];
            outNum = new int [numOfNodes];

            String tempString2;
            String[] split2;
            while((tempString2 = graphReader.readLine()) != null){
                split2 = tempString2.split(" "); // nodeId outNeigh1 outNeigh2 ..
                int nid = Integer.parseInt(split2[0]);
                outGraph[nid] = new int[split2.length - 1];

                for(int k = 1 ; k < split2.length; k++){
                    int neighborId = Integer.parseInt(split2[k]);
                    if(neighborId == nid ) continue;
                    outGraph[nid][outNum[nid]++] = neighborId;
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }

    }



    /**
    * description: read the data from the graph
    */
    public void read_graph(){
        String tempString1, tempString2;
        String [] split1, split2;
        try {
            BufferedReader edgeReader = new BufferedReader(new FileReader(degree_file));
            while((tempString1 = edgeReader.readLine())!=null){
                split1 = tempString1.split(" "); // nodeId indegree outdegree
                int nid = Integer.parseInt(split1[0]);
                inGraph[nid] = new int[Integer.parseInt(split1[1])];
                outGraph[nid] = new int[Integer.parseInt(split1[2])];
            }
            while((tempString2 = graphReader.readLine()) != null){
                split2 = tempString2.split(" "); // nodeId outNeigh1 outNeigh2 ..
                int nid = Integer.parseInt(split2[0]);
                for(int k = 1 ; k < split2.length; k++){
                    int neighborId = Integer.parseInt(split2[k]);
                    if(neighborId == nid) continue;
                    outGraph[nid][outNum[nid]++] = neighborId;
                    inGraph[neighborId][inNum[neighborId]++] = nid;
                }
                outGraph[nid] = Arrays.copyOfRange(outGraph[nid], 0, outNum[nid]);
            }
            for(int k = 0; k< numOfNodes; k++){
                if(inGraph[k] != null)
                    inGraph[k] = Arrays.copyOfRange(inGraph[k], 0, inNum[k]);
            }
            edgeReader.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public int[][] get_inGraph(){ return inGraph; }



    public int[][] get_outGraph(){ return outGraph; }



    public int getNumOfEdges(){
        for(int i = 0;i<numOfNodes;i++)
            numOfEdges += inGraph[i].length;
        System.out.println("NumOfEdegs "+ numOfEdges);
        return numOfEdges;
    }


    public void getCoreInfo(){
        int kmax = 0;
        int lmax = 0;
        for(int[] x: inGraph)
            kmax = Math.max(x.length, kmax);
        for(int[] x: outGraph)
            lmax = Math.max(x.length, lmax);

        System.out.println("kmax: " + kmax + " lmax: " + lmax);
    }


    public static void main(String[] args){
        String drugbank_biGraph = "D:\\Medical Dataset\\DrugBank\\Graph_biE.txt";
        String drugbank_biDegree = "D:\\Medical Dataset\\DrugBank\\Degree_biE.txt";
        String drugbank_nobiGraph = "D:\\Medical Dataset\\DrugBank\\Graph_nobiE.txt";
        String drugbank_nobiDegree = "D:\\Medical Dataset\\DrugBank\\Degree_nobiE.txt";
        DataReader reader = new DataReader(drugbank_biDegree, drugbank_biGraph);
        reader.read_graph();
        Decomposition decomp = new Decomposition(reader.get_inGraph(), reader.get_outGraph());
        decomp.calculateInfo();

        reader = new DataReader(drugbank_nobiDegree, drugbank_nobiGraph);
        reader.read_graph();
        decomp = new Decomposition(reader.get_inGraph(), reader.get_outGraph());
        decomp.calculateInfo();

    }
}
