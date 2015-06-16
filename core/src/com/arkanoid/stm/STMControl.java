package com.arkanoid.stm;

import com.sun.swing.internal.plaf.synth.resources.synth_sv;
import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

/**
 * Created by Grzegorz on 2015-06-03.
 */
public class STMControl implements SerialPortEventListener
{
    
        //for containing the ports that will be found
        private Enumeration ports = null;
        //map the port names to CommPortIdentifiers
        private HashMap portMap = new HashMap();

        //this is the object that contains the opened port
        private CommPortIdentifier selectedPortIdentifier = null;
        private SerialPort serialPort = null;

        //input and output streams for sending and receiving data
        private InputStream input = null;
        private OutputStream output = null;

        //just a boolean flag that i use for enabling
        //and disabling buttons depending on whether the program
        //is connected to a serial port or not
        private boolean bConnected = false;

        //the timeout value for connecting with the port
        final static int TIMEOUT = 2000;

        //some ascii values for for certain things
        final static int SPACE_ASCII = 32;
        final static int DASH_ASCII = 45;
        final static int NEW_LINE_ASCII = 10;

        private CommPortIdentifier comPort= null;

        public void searchForPorts()
        {
            ports = CommPortIdentifier.getPortIdentifiers();

            while (ports.hasMoreElements())
            {
                CommPortIdentifier curPort = (CommPortIdentifier)ports.nextElement();

                //get only serial ports
                if (curPort.getPortType() == CommPortIdentifier.PORT_SERIAL)
                {
                    System.out.print(curPort.getName() + " ");
                    comPort= curPort;
                   // portMap.put(curPort.getName(), curPort);
                }
            }
        }

    public void connect()
    {
        String selectedPort = comPort.getName();
       selectedPortIdentifier = (CommPortIdentifier) comPort;

        CommPort commPort = null;

        try
        {
            //the method below returns an object of type CommPort
            commPort = selectedPortIdentifier.open("COM_PORT", TIMEOUT);
            //the CommPort object can be casted to a SerialPort object
            serialPort = (SerialPort)commPort;

             System.out.println(comPort.getName() + " opened successfully.");

        }
        catch (PortInUseException e)
        {
            System.err.println(selectedPort + " is in use. (" + e.toString() + ")");

        }
        catch (Exception e)
        {
            System.err.println("Failed to open " + selectedPort + "(" + e.toString() + ")");
        }
    }

    //post: initialized input and output streams for use to communicate data
    public boolean initIOStream()
    {
        //return value for whether opening the streams is successful or not
        boolean successful = false;

        try {
            //
            //input = serialPort.getInputStream();
            output = serialPort.getOutputStream();
     //       writeData(0, 0);

            successful = true;
            return successful;
        }
        catch (IOException e) {
            System.out.println( "I/O Streams failed to open. (" + e.toString() + ")");
            return successful;
        }
    }

    //post: an event listener for the serial port that knows when data is received
    public void initListener()
    {
        try
        {
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        }
        catch (TooManyListenersException e)
        {
           System.err.println("Too many listeners. (" + e.toString() + ")");

        }
    }

    public void disconnect()
    {
        //close the serial port
        try
        {
         //   writeData(0, 0);

            serialPort.removeEventListener();
            serialPort.close();
            input.close();
            output.close();
            System.err.println("Disconnected").
        }
        catch (Exception e)
        {
            System.out.println( "Failed to close " + serialPort.getName()
                    + "(" + e.toString() + ")");

        }
    }

    //post: processing on the data it reads
    public void serialEvent(SerialPortEvent evt) {
        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE)
        {
            try
            {
                byte singleData = (byte)input.read();

                if (singleData != NEW_LINE_ASCII)
                {
                  //  logText = new String(new byte[] {singleData});
                    System.out.println("Odebrano: "+ new String(new byte[] {singleData}));
                }
                else
                {
                    System.out.println("Odebrano: EMPTY");
                }
            }
            catch (Exception e)
            {
                System.err.println("Failed to read data. (" + e.toString() + ")");

            }
        }
    }
    
}
