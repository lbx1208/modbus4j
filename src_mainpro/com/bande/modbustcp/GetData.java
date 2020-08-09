package com.bande.modbustcp;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;

public class GetData {

    ArrayList<String[]> csvdata = new ArrayList<>();
    ReadCsv readCsv ;

    public void  delcsvdata(){
        Log log = LogFactory.getLog(GetData.class);

        readCsv = new ReadCsv();
        csvdata = readCsv.Readio1();

        int length = csvdata.size();
        String [] name = new  String[length];
        Integer [] offset = new  Integer[length];

        for (int i=0 ;i<length;i++){
            offset[i] = Integer.parseInt(csvdata.get(i)[0]);
            name[i] = csvdata.get(i)[1];
        }
    }

    public void booldata(String name){

    }

}
