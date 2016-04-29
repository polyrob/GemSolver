package com.robbomb.gemsolver;

import com.robbomb.gemsolver.grabber.FileGrabber;
import com.robbomb.gemsolver.grabber.Grabber;
import com.robbomb.gemsolver.grabber.GrabberException;
import com.robbomb.gemsolver.grabber.ScreenGrabber;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.robbomb.gemsolver.Constants.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Main {


    public static void main(String[] args) {

        Board board = new Board(8,8);
        DisplayWindow window = new DisplayWindow(board);

        // If there is an argument, use it for a filename to load locally for testing
        Grabber grabber = null;
        if (args.length > 0) {
            System.out.println("DEBUG, getting frames from file.");
            grabber = new FileGrabber(args[0]);
        } else {
            grabber = new ScreenGrabber();
        }

        window.setVisible(true);


        while (true) {
            long startTime = System.currentTimeMillis();

            // Get a frame
            BufferedImage capture = null;
            try {
                capture = grabber.getFrame();
//                Utils.saveImage(capture, "test.bmp");
            } catch (GrabberException e) {
                e.printStackTrace();
            }

            // Construct grid
            for (int y = 0; y < 8; y++) {
                for (int x = 0; x < 8; x++) {
                    Color avg = Utils.averageColor(capture, TOP_LEFT.x + (x * GEM_X_OFFSET), TOP_LEFT.y + (y * GEM_Y_OFFSET));
                    Gem gem = board.getGemAt(x, y);
                    try {
                        gem.determineColor(avg);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("Position: " + (TOP_LEFT.x + (x * GEM_X_OFFSET)) + ", " + (TOP_LEFT.y + (y * GEM_Y_OFFSET)));

                    }
                }
            }

            // Solve for best move
            Solver.solve(board);


            window.updateBoard();

            System.out.println("Iteration solved in: " + (System.currentTimeMillis() - startTime) + "ms.");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }



    }

}
