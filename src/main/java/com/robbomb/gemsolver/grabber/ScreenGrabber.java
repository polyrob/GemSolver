package com.robbomb.gemsolver.grabber;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.robbomb.gemsolver.Constants.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class ScreenGrabber implements Grabber {

    private Rectangle screenRect;
    private Robot robot;

    public ScreenGrabber(Rectangle rect) {
        screenRect = rect;
    }

    public BufferedImage getFrame() throws GrabberException {
        if (robot == null) try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new GrabberException(e);
        }
        return robot.createScreenCapture(screenRect);
    }
}
