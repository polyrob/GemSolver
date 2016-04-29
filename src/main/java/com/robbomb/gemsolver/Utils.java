package com.robbomb.gemsolver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.robbomb.gemsolver.Constants.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Utils {
    private static final String[] WIN_RUNTIME = {"cmd.exe", "/C"};
    private static final String[] OS_LINUX_RUNTIME = {"/bin/bash", "-l", "-c"};

    private static final String adbDragTemplate = "adb shell input swipe x1 y1 x2 y2";

    static String[] commands = {
            "adb shell screencap /sdcard/",
            "adb pull /sdcard/",
            "adb shell rm /sdcard/"};

//    "adb shell screencap /sdcard/screen.png"
//            "adb pull /sdcard/screen.png"
//            "adb shell rm /sdcard/screen.png"

//    "adb shell input tap x y"
//    "adb shell input swipe 10 500 600 500"

    public static void dragMove(int x1, int y1, int x2, int y2) {
        Point p1 = getPixelsFromGrid(new Point(x1, y1));
        Point p2 = getPixelsFromGrid(new Point(x2, y2));

        String command = adbDragTemplate
                .replace("x1", String.valueOf(p1.x))
                .replace("y1", String.valueOf(p1.y))
                .replace("x2", String.valueOf(p2.x))
                .replace("y2", String.valueOf(p2.y));

        runProcess(true, command);
    }

    protected static Point getPixelsFromGrid(Point grid) {
        Point p = new Point();
        p.x = BITMAP_TOP_LEFT.x + (grid.x * DEVICE_GEM_X_OFFSET);
        p.y = BITMAP_TOP_LEFT.y + (grid.y * DEVICE_GEM_Y_OFFSET);
        return p;
    }


protected static void runProcess(boolean isWin, String command) {
    String compiledCommand = null;
    try {
        compiledCommand = "cmd.exe " + "/C " + command;
        ProcessBuilder pb = new ProcessBuilder(compiledCommand);
        pb.redirectErrorStream(true);
        Process p = pb.start();
        p.waitFor();
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String temp;
        StringBuilder sb = new StringBuilder();
        List<String> line = new ArrayList<String>();
        while ((temp = in.readLine()) != null) {
            System.out.println("temp line: " + temp);
            sb.append(temp);
        }
        System.out.println("response: " + line);

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static Color averageColor(BufferedImage bi, int x, int y) {
        long sumr = 0, sumg = 0, sumb = 0;
        int num = 0;

            for (int i = -SAMPLE_RANGE; i < 5; i += SAMPLE_RANGE) {
                for (int j = -SAMPLE_RANGE; j < 5; j += SAMPLE_RANGE) {
                    Color pixel = null;
                    try {
                        pixel = new Color(bi.getRGB(x + i, y + j));
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
}

