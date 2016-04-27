package com.robbomb.gemsolver.grabber;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.robbomb.gemsolver.Constants.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class ScreenGrabber implements Grabber {

    private static final Rectangle screenRect = new Rectangle(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);

    private Robot robot;

    public BufferedImage getFrame() throws GrabberException {
        if (robot == null) try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new GrabberException(e);
        }
        return robot.createScreenCapture(screenRect);
    }
}
