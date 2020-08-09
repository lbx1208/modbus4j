package com.bande.modbustcp;


import com.rtdb.model.IntData;
import org.apache.commons.logging.impl.Log4JLogger;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Mainprocess  {

    public static void main(String[] args) throws Exception {

/*
        Goldencnn cnn = new Goldencnn();
        List<IntData> list ;
        String[] name = Mainprocess.getrtdbdata();

        while (true){
            list = cnn.getboolorint(name);

            while (list.listIterator().hasNext()){
                long valuedata = list.listIterator().next().getState();
                System.out.println(valuedata);
            }
            Thread.sleep(1000);
        }
*/
        Listinter listinter = new Listinter();
        listinter.start();

    }







    /**
     * 利用该方法获取数据库点名和modbus对应地址的一个map<int,string[]>
     * @throws Exception
     */
    /*public static String[] getrtdbdata() throws Exception {
        String[] name = new  String[1000];
        int i=0;
        ReadCsv readCsv = new ReadCsv();
        HashMap<Integer, String> csvdataMap = new HashMap<>();

        csvdataMap = readCsv.readbool();
//        name =  csvdataMap.values().toArray(name);
//        System.out.println();
        for (Map.Entry<HashMap<Integer,String>> map:csvdataMap.entrySet()){

                name[i] = mapvalue.getValue();
                i++;

        }
        return name;
    }*/
}
