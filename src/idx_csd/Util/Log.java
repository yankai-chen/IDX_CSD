package idx_csd.Util;

import idx_csd.Config;

import java.util.Date;
import java.io.*;

/**
 * @author ：Yankai CHEN
 * @class ：Log
 * @date ：Created in 12/12/2019
 */

public class Log {
    private static String defaultFileName = Config.logFilePath + "log.txt";


    public static void log(String msg) {
        try {
            Date date = new Date();
            String time = date.toString();
            BufferedWriter stdout = new BufferedWriter(new FileWriter(defaultFileName, true));
            stdout.write("["+ time +"]\n");//output the log time for tracking
            stdout.write(msg);
            stdout.newLine();
            stdout.newLine();
            stdout.flush();
            stdout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void log(String logFileName, String msg){
        try {
            Date date = new Date();
            String time = date.toString();
            BufferedWriter stdout = new BufferedWriter(new FileWriter(Config.logFilePath + logFileName + ".txt", true));
            stdout.write("["+ time +"]\n");//output the log time for tracking
            stdout.write(msg);
            stdout.newLine();
            stdout.newLine();
            stdout.flush();
            stdout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * description: This static class provides methods to write community queries' result to file
     */
    public static void write(int queryNode, String fileName, int [] result){
        try{
            System.out.println("Writing Results...");
            FileWriter fileWritter = new FileWriter(fileName,true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.write("Query Node: " + queryNode +  " Component has following nodes: ");
            for (int value : result) {
                bufferWritter.write(value + ", ");
            }
            bufferWritter.newLine();
            bufferWritter.flush();
            bufferWritter.close();
            System.out.println("Done.");
        }catch (IOException e){
            e.printStackTrace();;
        }
    }


    public static void main(String args[]) {
        Log.log( "test","main_f logging. ");
    }

}
