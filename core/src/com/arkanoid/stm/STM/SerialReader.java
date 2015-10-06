package com.arkanoid.stm.STM;

/**
 * Created by GrzegorzLap on 2015-10-05.
 */

import com.arkanoid.stm.ScreenProperties;

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
                        ScreenProperties.pipeVelocity = 200;
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);

                    }
                    else if(DIVIDE.substring(0, 1).equals("m"))
                    {
                        ScreenProperties.pipeVelocity = 400;
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                    }
                    else if(DIVIDE.substring(0, 1).equals("n"))
                    {
                        ScreenProperties.pipeVelocity = 600;
                        robot.keyPress(KeyEvent.VK_LEFT);
                        robot.keyRelease(KeyEvent.VK_RIGHT);
                    }
                    else if(DIVIDE.substring(0, 1).equals("r"))
                    {
                        ScreenProperties.pipeVelocity = 200;
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        robot.keyPress(KeyEvent.VK_RIGHT);
                      }
                    else if(DIVIDE.substring(0, 1).equals("s"))
                    {
                        ScreenProperties.pipeVelocity = 400;
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        robot.keyPress(KeyEvent.VK_RIGHT);
                    }
                    else if(DIVIDE.substring(0, 1).equals("t"))
                    {
                        ScreenProperties.pipeVelocity = 600;
                        robot.keyRelease(KeyEvent.VK_LEFT);
                        robot.keyPress(KeyEvent.VK_RIGHT);
                    }

                    else
                    {
                        ScreenProperties.pipeVelocity = 0;
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
