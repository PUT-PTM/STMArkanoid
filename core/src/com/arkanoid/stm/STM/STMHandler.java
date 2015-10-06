package com.arkanoid.stm.STM;

/**
 * Created by GrzegorzLap on 2015-10-05.
 */

import gnu.io.*;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;

import java.io.InputStream;
import java.io.OutputStream;


public class STMHandler {

    SerialPort serialPort;

    public STMHandler() {
        super();
    }

    public void connect(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        } else {

            CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                OutputStream out = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();

            } else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public void disconnect() throws Exception {

        try {
            if (serialPort != null) {
                serialPort.removeEventListener();
                serialPort.close();
                serialPort.getInputStream().close();
                serialPort.getOutputStream().close();

            }

        } catch (Exception e) {
        }
    }

    public boolean isConnected(String portName) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portName == "COM4") return true;
        else return false;


    }


}

