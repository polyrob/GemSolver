package com.robbomb.gemsolver.grabber;

import java.awt.image.BufferedImage;

/**
 * Created by NewRob on 4/26/2016.
 */
public interface Grabber {

    BufferedImage getFrame() throws GrabberException;
}
