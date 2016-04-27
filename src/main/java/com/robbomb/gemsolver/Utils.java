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

/**
 * Created by NewRob on 4/26/2016.
 */
public class Utils {
    private static final String[] WIN_RUNTIME = {"cmd.exe", "/C"};
    private static final String[] OS_LINUX_RUNTIME = {"/bin/bash", "-l", "-c"};

    static String[] commands = {
            "adb shell screencap /sdcard/",
            "adb pull /sdcard/",
            "adb shell rm /sdcard/"};

//    "adb shell screencap /sdcard/screen.png"
//            "adb pull /sdcard/screen.png"
//            "adb shell rm /sdcard/screen.png"

//    "adb shell input tap x y"
//    "adb shell input swipe 10 500 600 500"

    private static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }

    public static List<String> runProcess(boolean isWin, String... command) {
        System.out.print("command to run: ");
        for (String s : command) {
            System.out.print(s);
        }
        System.out.print("\n");
        String[] allCommand = null;
        try {
            if (isWin) {
                allCommand = concat(WIN_RUNTIME, command);
            } else {
                allCommand = concat(OS_LINUX_RUNTIME, command);
            }
            ProcessBuilder pb = new ProcessBuilder(allCommand);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            p.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String _temp = null;
            List<String> line = new ArrayList<String>();
            while ((_temp = in.readLine()) != null) {
                System.out.println("temp line: " + _temp);
                line.add(_temp);
            }
            System.out.println("result after command: " + line);
            return line;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Color averageColor(BufferedImage bi, int x0, int y0, int w,
                                     int h) {
        int x1 = x0 + w;
        int y1 = y0 + h;
        long sumr = 0, sumg = 0, sumb = 0;
        for (int x = x0; x < x1; x++) {
            for (int y = y0; y < y1; y++) {
                Color pixel = new Color(bi.getRGB(x, y));
                sumr += pixel.getRed();
                sumg += pixel.getGreen();
                sumb += pixel.getBlue();
            }
        }
        int num = w * h;
        return new Color(Math.round(sumr / num), Math.round(sumg / num), Math.round(sumb / num));
    }

//    boolean similarTo(Color c){
//        double distance = (c.getRed() - this.getRed())*(c.getRed() - this.getRed()) + (c.g - this.g)*(c.g - this.g) + (c.b - this.b)*(c.b - this.b)
//        if(distance > X){
//            return true;
//        }else{
//            return false;
//        }
//    }

    public static void saveImage(BufferedImage capture) {
        try {
            ImageIO.write(capture, "bmp", new File("caputre.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

