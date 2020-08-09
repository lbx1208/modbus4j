import com.serotonin.modbus4j.*;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.ip.tcp.TcpMaster;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReportSlaveIdRequest;
import com.serotonin.modbus4j.sero.messaging.MessageControl;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

public class TestSlave {

    public static void main(String[] args) throws InterruptedException {

        ByteQueue byteQueue = new ByteQueue();
        ModbusFactory modbusFactory = new ModbusFactory();
//        IpParameters ipParameters;
//        ModbusMaster master =  modbusFactory.createTcpMaster(ipParameters,true);
        ModbusSlaveSet modbusSlaveSet = modbusFactory.createTcpSlave(false);
        MessageControl messageControl = new MessageControl();

        Thread.sleep(300);
        modbusSlaveSet.addProcessImage(getModscanProcessImage(1));

            new Thread(()->{
                try {
                    modbusSlaveSet.start();
                } catch (ModbusInitException e) {
                    e.printStackTrace();
                }
            }).start();;

        byte bytes[]=new  byte[12];
//        messageControl.data(bytes,12);
//        System.out.println(bytes.toString());

        while (true){
            try {
                ReportSlaveIdRequest reportSlaveIdRequest = new ReportSlaveIdRequest(1);
                System.out.println("功能码是："+reportSlaveIdRequest.getFunctionCode());
                reportSlaveIdRequest.write(byteQueue);
                System.out.println("收到的请求信息是："+byteQueue);
            } catch (ModbusTransportException e) {
                e.printStackTrace();
            }
        }












/*        ModbusResponse modbusResponse = null;
        ModbusRequest modbusRequest = null;
        BasicProcessImage processImage = (BasicProcessImage)modbusSlaveSet.getProcessImage(1);

        try {
            modbusResponse = modbusRequest.handle(processImage);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        }
        modbusResponse.write(byteQueue);
        System.out.println(byteQueue.size());
        System.out.println("收到的信息是："+byteQueue);*/



/*            processImage.writeCoil(1,true);
            ReportSlaveIdRequest slaveIdRequest = new ReportSlaveIdRequest(1);
            slaveIdRequest.write(byteQueue);
            System.out.println(byteQueue);

        byte[] by = processImage.getReportSlaveIdData();
        System.out.println("111"+by.toString());
        System.out.println(by.length);*/

    }
    static BasicProcessImage getModscanProcessImage(int slaveId) {
        BasicProcessImage processImage = new BasicProcessImage(slaveId);
        processImage.setAllowInvalidAddress(true);
        processImage.setInvalidAddressValue(Short.MIN_VALUE);
        processImage.setExceptionStatus((byte) 151);

        processImage.setCoil(10, true);
        processImage.setCoil(11, false);
        processImage.setCoil(12, true);
        processImage.setCoil(13, true);
        processImage.setCoil(14, false);
        return processImage;
    }
}
