package com.robbomb.gemsolver;

import com.robbomb.gemsolver.grabber.FileGrabber;
import com.robbomb.gemsolver.grabber.Grabber;
import com.robbomb.gemsolver.grabber.GrabberException;
import com.robbomb.gemsolver.grabber.ScreenGrabber;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by NewRob on 4/26/2016.
 */
public class Main {

    private static final String WINDOW_NAME = "SAMSUNG SM_G900A";
    private static Rectangle vysorRect;
    private static boolean debug;

    public static void main(String[] args) {
        if (args.length > 0) debug = true;


        try {
            //  Get Vysor Window Rectangle
            vysorRect = JNAUtil.getWindowRect(WINDOW_NAME);
        } catch (JNAUtil.GetWindowRectException | JNAUtil.WindowNotFoundException e) {
            e.printStackTrace();
        }



        // If there is an argument, use it for a filename to load locally for testing
        Grabber grabber = null;
        if (debug) {
            System.out.println("DEBUG, getting frames from file.");
            grabber = new FileGrabber(args[0]);
        } else {
            // Init ScreenGrabber with the window location
            grabber = new ScreenGrabber(vysorRect);
        }

//        JFrame frame = new JFrame();
//        JLabel label = new JLabel();
//
//        frame.getContentPane().setLayout(new FlowLayout());
//        frame.getContentPane().add(label);
//
//        frame.setVisible(true);

        long totalTime = 0;
        while (totalTime < 60000) {
            long startTime = System.currentTimeMillis();

            // Get a frame
            BufferedImage capture = null;
            try {
                capture = grabber.getFrame();
            } catch (GrabberException e) {
                e.printStackTrace();
            }

            // Build gameboard
            Board gameboard = buildGameboard(capture);

            // Solve for best move
            Move move = Solver.solve(gameboard);
            System.out.println(move);

            if (move != null && !debug) {
                Utils.dragMove(vysorRect, gameboard, move);
            }

            long deltaTime = System.currentTimeMillis() - startTime;
            System.out.println("Iteration solved in: " + deltaTime + "ms.");

            try {
                Thread.sleep(500);
                deltaTime = System.currentTimeMillis() - startTime;
                totalTime += deltaTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static Board buildGameboard(BufferedImage capture) {
        Board board = new Board(8, 8);

        int startX = (int) (capture.getWidth() * Constants.GEM_X_OFFSET);
        int startY = (int) (capture.getHeight() * Constants.GEM_Y_TOP_OFFSET);

        float deltaX = (capture.getWidth() - 2 * startX) / 7;
        float deltaY = ((capture.getHeight() * Constants.GEM_Y_BOTTOM_OFFSET) - startY) / 7;

        board.setStartX(startX);
        board.setStartY(startY);
        board.setDeltaX(deltaX);
        board.setDeltaY(deltaY);

        int posX;
        int posY;

        for (int y = 0; y < 8; y++) {
            posY = (int) (startY + y * deltaY);
            for (int x = 0; x < 8; x++) {
                posX = (int) (startX + x * deltaX);
                capture.createGraphics().drawLine(posX - 5, posY, posX + 5, posY);
                capture.createGraphics().drawLine(posX, posY - 5, posX, posY + 5);
                Color avg = Utils.averageColor(capture, posX, posY);
                GameColor gem = board.getGemAt(x, y);
                try {
                    GameColor color = gem.determineColor(avg);
                    board.setGemAt(color, x, y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        File outputfile = new File("saved.png");
        try {
            ImageIO.write(capture, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return board;
    }

}
