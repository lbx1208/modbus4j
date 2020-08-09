package com.bande.modbustcp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.plugin2.main.client.PrintBandDescriptor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 该类主要用于从csv文件中读取到需要传送的信息，然后保存到一个地方
 *      1.获取的信息包括：modbus地址、数据库点名、数据类型、是否需要变比；
 *          a.需要找到用于处理csv文件的类
 *          b.使用该类把数据获取出来
 *      2.把获取到的信息传送到一个存储的地方，以便后面的操作能够使用；
 *          a、用什么结构存储数据 ---需要创建一个类用于专门存储数据---map<int,string></int,string>
 *
 */
public class ReadCsv {

    Log log = LogFactory.getLog(ReadCsv.class);
    private ArrayList<String[]> csvdata = new ArrayList<>();

    public ArrayList<String[]> getCsvdata() {
        return csvdata;
    }

    public ArrayList<String[]>  Readio1(){
        BufferedReader file;

        try {

            file = new BufferedReader(new FileReader("IO-1.csv"));
            file.readLine();
            String line;
            log.info("读取IO-1文件成功！");

            while (!(line = file.readLine()).equals("")){
                csvdata.add(line.split(","));
            }

        } catch (IOException e) {
            log.error(e);
        }
        return csvdata;
    }


 /*   public HashMap<Integer, String> readbool() throws Exception {

        HashMap<HashMap<Integer, String>,HashMap<Integer,String>> boolhashmap = new HashMap<>();
        HashMap<Integer , String > overheadmap = new HashMap<>();
        HashMap<Integer , String>  booldatamap = new HashMap<>();

        BufferedReader buffer = new BufferedReader(new FileReader("IO-1.csv"));
        buffer.readLine();
        System.out.println("读取csv文件成功！");

        String line;

        while (!(line = buffer.readLine()).equals("")){

            Integer address;
            String tagname ;
            String datatype;
            int ratio;
            String wr;

//            System.out.println(line);
            String[] data = line.split(",");
            address = Integer.parseInt(data[0]);
            tagname = data[1];
            datatype = data[2];
            ratio = Integer.parseInt(data[3]);
            wr = data[4];

            if(datatype.equals("BOOL")){
                booldatamap.put(address,tagname);
                overheadmap.put(ratio,wr);
//                boolhashmap.put(overheadmap,booldatamap);
            }
        }
        buffer.close();
        return booldatamap;
    }


    //重新构造一个处理体系，原代码过于冗余，复杂

    //处理日志文件
    Log log = LogFactory.getLog(ReadCsv.class);

    public ArrayList<String[]> getCsvdata() {
        return csvdata;
    }

    //存储csv读取文件的链表
    ArrayList<String[]> csvdata1 = new ArrayList<>();
    BufferedReader file;

    {
        try {

            file = new BufferedReader(new FileReader("IO-1.csv"));
            file.readLine();
            String line;

            while (!(line = file.readLine()).equals("")){
                csvdata1.add(line.split(","));
            }

        } catch (IOException e) {
            log.error(e);
        }
    }
*/


}
