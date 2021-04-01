package idx_csd;

/**
 * @author :Yankai CHEN
 * @class :idx_csd.Config
 * @date :Created in 13/10/2019
 */

public class Config {
    public static boolean WRITE2DISK = false;


    public static String test_graph = "/Users/chenyankai/Workspace/IDX_CSD/test_data/Graph.dat";
    public static String test_degree = "/Users/chenyankai/Workspace/IDX_CSD/test_data/Degree.dat";
   public static String small_t_graph = "/Users/chenyankai/Workspace/IDX_CSD/test_data/test_graph.dat";
    public static String small_t_degree = "/Users/chenyankai/Workspace/IDX_CSD/test_data/test_degree.dat";
    public static String small_t_graph_1 = "/Users/chenyankai/Workspace/IDX_CSD/test_data/test_graph_1.dat";
    public static String small_t_degree_1 = "/Users/chenyankai/Workspace/IDX_CSD/test_data/test_degree_1.dat";


//    public static String test_graph = "C:\\workspace\\IDX_CSD\\test_data\\Graph.dat";
//    public static String test_degree = "C:\\workspace\\IDX_CSD\\test_data\\Degree.dat";
//    public static String small_t_graph = "C:\\workspace\\IDX_CSD\\test_data\\test_graph.dat";
//    public static String small_t_degree = "C:\\workspace\\IDX_CSD\\test_data\\test_degree.dat";
//    public static String small_t_graph_1 = "C:\\workspace\\IDX_CSD\\test_data\\test_graph_1.dat";
//    public static String small_t_degree_1 = "C:\\workspace\\IDX_CSD\\test_data\\test_degree_1.dat";
//    public static String test_graph_index = "C:\\workspace\\IDX_CSD\\test_data\\index.txt";
//    public static String home = "C:\\workspace\\IDX_CSD\\time.txt";

//    public static String logFilePath = "C:\\workspace\\IDX_CSD\\logs\\";
    public static String logFilePath = "/research/king3/ykchen/workspace/IDX_CSD/logs/";


    /*************  NOT USING  ************
    public static String Amazon_graph = "D:\\CSDDatasets\\Finish_Amazon0302\\OutInlink.dat";
    public static String Amazon_degree = "D:\\CSDDatasets\\Finish_Amazon0302\\OutDegree.dat";
    public static String Amazon_index =  "D:\\CSDDatasets\\Finish_Amazon0302\\index.txt";
    public static String Cit_graph = "D:\\CSDDatasets\\Finishi_Cit_Patents\\OutInlink.dat";
    public static String Cit_degree = "D:\\CSDDatasets\\Finishi_Cit_Patents\\outDegree.dat";
    public static String Cit_index = "D:\\CSDDatasets\\Finishi_Cit_Patents\\index.txt";
    public static String p2p_graph = "D:\\CSDDatasets\\Finish_p2p-Gnutella08\\OutInlink.dat";
    public static String p2p_degree = "D:\\CSDDatasets\\Finish_p2p-Gnutella08\\outDegree.dat";
    public static String p2p_index = "D:\\CSDDatasets\\Finish_p2p-Gnutella08\\index.txt";
    public static String DrugBank_nobi_graph = "D:\\CSDDatasets\\drugBank\\Graph_nobiE.txt";
    public static String DrugBank_nobi_degree = "D:\\CSDDatasets\\drugBank\\Degree_nobiE.txt";
    public static String DrugBank_nobi_index = "D:\\CSDDatasets\\drugBank\\nobi_index.txt";
    public static String DrugBank_bi_graph = "D:\\CSDDatasets\\drugBank\\Graph_biE.txt";
    public static String DrugBank_bi_degree = "D:\\CSDDatasets\\drugBank\\Degree_biE.txt";
    public static String DrugBank_bi_index = "D:\\CSDDatasets\\drugBank\\bi_index.txt";

    //for local testing
    public static String Twitter_graph = "D:\\CSDDatasets\\Finish_Twitter\\OutInlink.dat";
    public static String Twitter_degree = "D:\\CSDDatasets\\Finish_Twitter\\outDegree.dat";
    public static String Twitter_index = "D:\\CSDDatasets\\Finish_Twitter\\index.txt";

    public static String Wiki_graph = "D:\\CSDDatasets\\Finish_wiki-rfa\\OutInlink.dat";
    public static String Wiki_degree = "D:\\CSDDatasets\\Finish_wiki-rfa\\outDegree.dat";
    public static String Wiki_index = "D:\\CSDDatasets\\Finish_wiki-rfa\\index.txt";

    public static String EU_graph = "D:\\CSDDatasets\\Finish_eu2015\\OutInlink.dat";
    public static String EU_degree = "D:\\CSDDatasets\\Finish_eu2015\\outDegree.dat";
    public static String EU_index = "D:\\CSDDatasets\\Finish_eu2015\\index.txt";

    public static String IT_graph = "D:\\CSDDatasets\\Finish_it-2004\\OutInlink.dat";
    public static String IT_degree = "D:\\CSDDatasets\\Finish_it-2004\\outDegree.dat";
    public static String IT_index = "D:\\CSDDatasets\\Finish_it-2004\\index.txt";
     *************  NOT USING  ************/

    //for remote evaluation
    public static String r_TwitterGraph_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-20.dat";
    public static String r_TwitterDegree_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutDegree-20.dat";
    public static String r_TwitterGraph_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-40.dat";
    public static String r_TwitterDegree_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutDegree-40.dat";
    public static String r_TwitterGraph_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-60.dat";
    public static String r_TwitterDegree_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutDegree-60.dat";
    public static String r_TwitterGraph_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-80.dat";
    public static String r_TwitterDegree_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutDegree-80.dat";
    public static String r_TwitterGraph_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink.dat";
    public static String r_TwitterDegree_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutDegree.dat";
    public static String query_twitter_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery-20.dat";
    public static String query_twitter_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery-40.dat";
    public static String query_twitter_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery-60.dat";
    public static String query_twitter_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery-80.dat";
    public static String query_twitter_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery.dat";
    public static String twitterIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-20-Index.txt";
    public static String twitterIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-40-Index.txt";
    public static String twitterIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-60-Index.txt";
    public static String twitterIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-80-Index.txt";
    public static String twitterIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/OutInlink-Index.txt";
    public static String negative_twitter_query = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/Outquery-negative.dat";
    public static String log_twitter = "/research/king3/ykchen/datasets/IDX-CSD/Finish_Twitter/log.txt";

//    public static String r_WikiGraph_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink-20.dat";
//    public static String r_WikiDegree_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutDegree-20.dat";
//    public static String r_WikiGraph_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink-40.dat";
//    public static String r_WikiDegree_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutDegree-40.dat";
//    public static String r_WikiGraph_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink-60.dat";
//    public static String r_WikiDegree_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutDegree-60.dat";
//    public static String r_WikiGraph_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink-80.dat";
//    public static String r_WikiDegree_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutDegree-80.dat";
//    public static String r_WikiGraph_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink.dat";
//    public static String r_WikiDegree_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutDegree.dat";
//    public static String wikiIndex = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/OutInlink-Index.txt";
//    public static String query_wiki = "/research/king3/ykchen/datasets/IDX-CSD/Finish_wiki-rfa/Outquery.dat";

    public static String r_eu2015Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-20.dat";
    public static String r_eu2015Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutDegree-20.dat";
    public static String r_eu2015Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-40.dat";
    public static String r_eu2015Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutDegree-40.dat";
    public static String r_eu2015Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-60.dat";
    public static String r_eu2015Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutDegree-60.dat";
    public static String r_eu2015Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-80.dat";
    public static String r_eu2015Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutDegree-80.dat";
    public static String r_eu2015Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink.dat";
    public static String r_eu2015Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutDegree.dat";
    public static String query_eu_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery-20.dat";
    public static String query_eu_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery-40.dat";
    public static String query_eu_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery-60.dat";
    public static String query_eu_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery-80.dat";
    public static String query_eu_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery.dat";
    public static String euIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-20-Index.txt";
    public static String euIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-40-Index.txt";
    public static String euIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-60-Index.txt";
    public static String euIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-80-Index.txt";
    public static String euIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/OutInlink-Index.txt";
    public static String negative_query_eu = "/research/king3/ykchen/datasets/IDX-CSD/Finish_eu2015/Outquery-negative.dat";

    public static String r_arabicGraph_20 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-20.dat";
    public static String r_arabicDegree_20 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-degree-20.dat";
    public static String r_arabicGraph_40 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-40.dat";
    public static String r_arabicDegree_40 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-degree-40.dat";
    public static String r_arabicGraph_60 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-60.dat";
    public static String r_arabicDegree_60 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-degree-60.dat";
    public static String r_arabicGraph_80 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-80.dat";
    public static String r_arabicDegree_80 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-degree-80.dat";
    public static String r_arabicGraph_100 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph.dat";
    public static String r_arabicDegree_100 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-degree.dat";
    public static String query_arabic_20 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query-20.dat";
    public static String query_arabic_40 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query-40.dat";
    public static String query_arabic_60 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query-60.dat";
    public static String query_arabic_80 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query-80.dat";
    public static String query_arabic_100 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query.dat";
    public static String arabicIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-20-Index.txt";
    public static String arabicIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-40-Index.txt";
    public static String arabicIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-60-Index.txt";
    public static String arabicIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-80-Index.txt";
    public static String arabicIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-graph-Index.txt";
    public static String negative_query_arabic = "/research/king3/ykchen/datasets/IDX-CSD/arabic-2005/arabic-2005-hc-query-negative.dat";

//    public static String r_uk2005Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-graph-20.dat";
//    public static String r_uk2005Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-degree-20.dat";
//    public static String r_uk2005Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-graph-40.dat";
//    public static String r_uk2005Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-degree-40.dat";
//    public static String r_uk2005Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-graph-60.dat";
//    public static String r_uk2005Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-degree-60.dat";
//    public static String r_uk2005Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-graph-80.dat";
//    public static String r_uk2005Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-degree-80.dat";
//    public static String r_uk2005Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-graph.dat";
//    public static String r_uk2005Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2005/uk-2005-hc-degree.dat";


    public static String r_it2014Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-20.dat";
    public static String r_it2014Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutDegree-20.dat";
    public static String r_it2014Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-40.dat";
    public static String r_it2014Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutDegree-40.dat";
    public static String r_it2014Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-60.dat";
    public static String r_it2014Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutDegree-60.dat";
    public static String r_it2014Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-80.dat";
    public static String r_it2014Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutDegree-80.dat";
    public static String r_it2014Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink.dat";
    public static String r_it2014Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutDegree.dat";
    public static String query_it_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery-20.dat";
    public static String query_it_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery-40.dat";
    public static String query_it_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery-60.dat";
    public static String query_it_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery-80.dat";
    public static String query_it_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery.dat";
    public static String itIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-20-Index.txt";
    public static String itIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-40-Index.txt";
    public static String itIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-60-Index.txt";
    public static String itIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-80-Index.txt";
    public static String itIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/OutInlink-Index.txt";
    public static String negative_query_it = "/research/king3/ykchen/datasets/IDX-CSD/Finish_it-2004/Outquery-negative.dat";


    public static String r_sk2005Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-20.dat";
    public static String r_sk2005Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-degree-20.dat";
    public static String r_sk2005Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-40.dat";
    public static String r_sk2005Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-degree-40.dat";
    public static String r_sk2005Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-60.dat";
    public static String r_sk2005Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-degree-60.dat";
    public static String r_sk2005Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-80.dat";
    public static String r_sk2005Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-degree-80.dat";
    public static String r_sk2005Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph.dat";
    public static String r_sk2005Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-degree.dat";
    public static String query_sk_20 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query-20.dat";
    public static String query_sk_40 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query-40.dat";
    public static String query_sk_60 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query-60.dat";
    public static String query_sk_80 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query-80.dat";
    public static String query_sk_100 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query.dat";
    public static String skIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-20-Index.txt";
    public static String skIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-40-Index.txt";
    public static String skIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-60-Index.txt";
    public static String skIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-80-Index.txt";
    public static String skIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-graph-Index.txt";
    public static String negative_query_sk = "/research/king3/ykchen/datasets/IDX-CSD/sk-2005/sk-2005-hc-query-negative.dat";



    public static String r_uk2007Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-20.dat";
    public static String r_uk2007Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-degree-20.dat";
    public static String r_uk2007Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-40.dat";
    public static String r_uk2007Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-degree-40.dat";
    public static String r_uk2007Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-60.dat";
    public static String r_uk2007Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-degree-60.dat";
    public static String r_uk2007Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-80.dat";
    public static String r_uk2007Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-degree-80.dat";
    public static String r_uk2007Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph.dat";
    public static String r_uk2007Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-degree.dat";
    public static String query_Uk_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query-20.dat";
    public static String query_Uk_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query-40.dat";
    public static String query_Uk_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query-60.dat";
    public static String query_Uk_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query-80.dat";
    public static String query_Uk_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query.dat";
    public static String ukIndex_20 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-20-Index.txt";
    public static String ukIndex_40 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-40-Index.txt";
    public static String ukIndex_60 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-60-Index.txt";
    public static String ukIndex_80 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-80-Index.txt";
    public static String ukIndex_100 = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-graph-Index.txt";
    public static String negative_query_uk = "/research/king3/ykchen/datasets/IDX-CSD/uk-2007-02/uk-2007-02-hc-query-negative.dat";



    //33 billion edges
//    public static String r_gsh2015Graph_20 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-graph-20.dat";
//    public static String r_gsh2015Degree_20 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-degree-20.dat";
//    public static String r_gsh2015Graph_40 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-graph-40.dat";
//    public static String r_gsh2015Degree_40 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-degree-40.dat";
//    public static String r_gsh2015Graph_60 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-graph-60.dat";
//    public static String r_gsh2015Degree_60 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-degree-60.dat";
//    public static String r_gsh2015Graph_80 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-graph-80.dat";
//    public static String r_gsh2015Degree_80 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-degree-80.dat";
//    public static String r_gsh2015Graph_100 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-graph.dat";
//    public static String r_gsh2015Degree_100 = "/research/king3/ykchen/datasets/IDX-CSD/gsh-2015/gsh-2015-host-hc-degree.dat";


}
