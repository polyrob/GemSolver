package com.robbomb.gemsolver;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;

import java.awt.*;

/**
 * help from:
 * http://stackoverflow.com/questions/6091531/how-to-get-the-x-and-y-of-a-program-window-in-java
 *
 * Created by Rob on 1/27/2017.
 */
public class JNAUtil {

    public interface User32 extends StdCallLibrary {
        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class,
                W32APIOptions.DEFAULT_OPTIONS);

        WinDef.HWND FindWindow(String lpClassName, String lpWindowName);
        int GetWindowRect(WinDef.HWND handle, int[] rect);
    }

    public static Rectangle getWindowRect(String windowName) throws WindowNotFoundException,
            GetWindowRectException {
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, windowName);
        if (hwnd == null) {
            throw new WindowNotFoundException("", windowName);
        }

        int[] rect = {0, 0, 0, 0};
        int result = User32.INSTANCE.GetWindowRect(hwnd, rect);
        if (result == 0) {
            throw new GetWindowRectException(windowName);
        }

        Rectangle rectangle = new Rectangle(rect[0], rect[1], rect[2] - rect[0], rect[3] - rect[1]);
        System.out.println(rectangle);

        return rectangle;
    }

    @SuppressWarnings("serial")
    public static class WindowNotFoundException extends Exception {
        public WindowNotFoundException(String className, String windowName) {
            super(String.format("Window null for className: %s; windowName: %s",
                    className, windowName));
        }
    }

    @SuppressWarnings("serial")
    public static class GetWindowRectException extends Exception {
        public GetWindowRectException(String windowName) {
            super("Window Rect not found for " + windowName);
        }
    }


}
