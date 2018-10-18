/**
 * Created by jingchun.zhang on 2018/6/20.
 */
package com.jc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author jingchun.zhang
 * @version 1.0.0
 * @date 2018/6/20
 */
public class Main {

    public static void main(String[] args) throws Exception {
        captureScreen("d:\\test2.png");
    }
    public static void captureScreen(String fileName) throws Exception {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        ImageIO.write(image,"jpg", new File(fileName));

    }
}
