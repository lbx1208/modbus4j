package com.bande.modbustcp;


import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ProcessImage;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.tcp.TcpSlave;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Listinter {

    Goldencnn goldencnn = new Goldencnn();
    ReadCsv readCsv = new ReadCsv();
    HashMap<Integer,String> csvdata = new HashMap<>();
    HashMap<Integer[], Long[]> rtdbdata = new HashMap<>();

    Log log = LogFactory.getLog(Listinter.class);


    private ModbusFactory modbusFactory = new ModbusFactory();
    private TcpSlave tcpSlave = (TcpSlave) modbusFactory.createTcpSlave(false);

    public void init() throws Exception {
//        csvdata = readCsv.readbool();
        goldencnn.init();
    }

    public void  start() throws Exception {
        //把传进来的int型数据转化成boolean型
//        boolean ret=(value==1)?true:false;
        init();
        tcpSlave.addProcessImage(getModscanProcessImage(1));
        ProcessImage processImage = tcpSlave.getProcessImage(1);
        //启动监听线程，但是不明白监听和不监听的区别
        new Thread(()->{
            try {
                tcpSlave.start();
            } catch (ModbusInitException e) {
                e.printStackTrace();
            }
        }).start();
//        tcpSlave.start();
        while (true) {
            if (goldencnn.isOpen()) {
                this.rtdbdata = goldencnn.getboolorint(csvdata);
                updateProcessImageboolean((BasicProcessImage) processImage, rtdbdata);
                Thread.sleep(800);
            }else
            {
                log.error("数据库不可用！");
                break;
            }
        }
    }

    static void updateProcessImageboolean(BasicProcessImage processImage,HashMap<Integer[],Long[]> hashMap){
//        processImage.setCoil(offset,value);
        for (Map.Entry<Integer[],Long[]> map:hashMap.entrySet()){
            Integer[] address = map.getKey();
            Long[] value = map.getValue();
            for (int i=0;i<address.length;i++){
//                System.out.println(address[i]+value[i]);
                processImage.setCoil(address[i],(value[i]==1)?true:false);
            }
        }
    }


    static BasicProcessImage getModscanProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setAllowInvalidAddress(true);
        processImage.setInvalidAddressValue(Short.MIN_VALUE);
        processImage.setExceptionStatus((byte) 151);

        return processImage;
    }
}