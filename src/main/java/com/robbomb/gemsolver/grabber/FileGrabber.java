package com.robbomb.gemsolver.grabber;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by NewRob on 4/26/2016.
 */
public class FileGrabber implements Grabber {

    File file;

    public FileGrabber(String filename) {
        file = new File(filename);
    }

    public BufferedImage getFrame() throws GrabberException {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {
            throw new GrabberException(e);
        }
    }
}
