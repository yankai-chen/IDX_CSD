import idx_csd.Algo.Decomposition;
import idx_csd.Algo.Pro_Index.Pro_Index;
import idx_csd.Algo.SCSDQuery;
import idx_csd.Config;
import idx_csd.Util.DataReader;

import java.util.*;

/**
 * @author :Yankai CHEN
 * @class :main_f
 * @date :Created in 14/10/2019
 */

public class main_f {

    public static  void main(String[] args){
//
        String indexFile = "./test_data/index.dat";
        String degree1 = "./test_data/test_degree_1.dat";
        String graph1 = "./test_data/test_graph_1.dat";
        DataReader dataReader = new DataReader(degree1, graph1);
        dataReader.read_graph();
        Decomposition decomp = new Decomposition(dataReader.get_inGraph(), dataReader.get_outGraph());
        Pro_Index index = new Pro_Index(decomp);
        Config.WRITE2DISK = true;
        index.buildIndex(indexFile);

        Pro_Index load_index = new Pro_Index();
        load_index.readIndex(indexFile);
        load_index.printIndex();
        List<Integer> result = load_index.getCommunity(3,1,1);
        for(int x: result){
            System.out.print((char) (x+65) + " ");
        }
    }
}
