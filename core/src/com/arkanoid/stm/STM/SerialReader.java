package com.arkanoid.stm.STM;

/**
 * Created by GrzegorzLap on 2015-10-05.
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.InputStream;

public class SerialReader implements Runnable {
    InputStream in;

    public SerialReader(InputStream in) {
        this.in = in;
    }

    public void run() {
        byte[] buffer = new byte[1024];
        String READ;
        String DIVIDE;
        int len = -1;

        try {
            Robot robot = new Robot();

            while ((len = this.in.read(buffer)) > -1) {
                READ = new String(buffer, 0, len);

                if (READ.length() > 0) {

                    DIVIDE = READ.substring(0, 1);
                    if (DIVIDE.substring(0, 1).equals("l")) {
                      //  System.out.println("l");
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                      //  robot.keyPress(KeyEvent.VK_RIGHT);
                       // robot.keyRelease(Input.Keys.RIGHT);
                        //robot.keyRelease(KeyEvent.VK_LEFT);
                    }
                    else if(DIVIDE.substring(0, 1).equals("r"))
                    {
                        //System.out.println("r");
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        robot.keyPress(KeyEvent.VK_RIGHT);
                        //robot.keyRelease(KeyEvent.VK_RIGHT);
                    }
                    else
                    {
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                        robot.keyRelease(KeyEvent.VK_LEFT);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
