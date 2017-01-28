package com.robbomb.gemsolver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.robbomb.gemsolver.Constants.SAMPLE_RANGE;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Utils {


    public static Color averageColor(BufferedImage bi, int x, int y) {
        long sumr = 0, sumg = 0, sumb = 0;
        int num = 0;

            for (int i = -SAMPLE_RANGE; i < 5; i += SAMPLE_RANGE) {
                for (int j = -SAMPLE_RANGE; j < 5; j += SAMPLE_RANGE) {
                    Color pixel = null;
                    try {
                        pixel = new Color(bi.getRGB(x + i*2, y + j*2));
                    }catch (Exception e) {
                            e.printStackTrace();
                        }
                    sumr += pixel.getRed();
                    sumg += pixel.getGreen();
                    sumb += pixel.getBlue();
                    num++;
                }
            }

        return new Color(Math.round(sumr / num), Math.round(sumg / num), Math.round(sumb / num));
    }


    public static void saveImage(BufferedImage capture, String filename) {
        try {
            ImageIO.write(capture, "bmp", new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void dragMove(Rectangle vysorRect, Board gameboard, Move move) {
        try {
            Robot robot = new Robot();

            // drag
            int x = (int) (vysorRect.x + gameboard.getStartX() + (move.getFrom().x * gameboard.getDeltaX()));
            int y = (int) (vysorRect.y + gameboard.getStartY() + (move.getFrom().y * gameboard.getDeltaY()));
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            // drop
            x = (int) (vysorRect.x + gameboard.getStartX() + (move.getTo().x * gameboard.getDeltaX()));
            y = (int) (vysorRect.y + gameboard.getStartY() + (move.getTo().y * gameboard.getDeltaY()));
            robot.mouseMove(x, y);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}

