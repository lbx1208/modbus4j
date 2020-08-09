import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ModbusRequest;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReadExceptionStatusRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.sero.messaging.MessageParser;
import com.serotonin.modbus4j.sero.messaging.RequestHandler;
import com.serotonin.modbus4j.sero.messaging.Transport;
import com.serotonin.modbus4j.sero.messaging.WaitingRoomKeyFactory;
import com.serotonin.modbus4j.sero.util.queue.ByteQueue;

public class Mytest {
    public static void main(String[] args) throws ModbusTransportException {
        ModbusFactory modbusFactory = new ModbusFactory();
        IpParameters ip = new IpParameters();
        ip.setHost("127.0.0.1");
        ModbusMaster tcpmaster = modbusFactory.createTcpMaster(ip,true);
        ModbusRequest modbusRequest = null;
        modbusRequest = new ReadExceptionStatusRequest(1);
        ModbusResponse modbusResponse = tcpmaster.send(modbusRequest);
        ByteQueue byteQueue= new ByteQueue(12);
        modbusResponse.write(byteQueue);
        System.out.println("功能码:"+modbusRequest.getFunctionCode());
        System.out.println("从站地址:"+modbusRequest.getSlaveId());
        System.out.println("收到的响应信息大小:"+byteQueue.size());
        System.out.println("收到的响应信息值:"+byteQueue);

    };

}



