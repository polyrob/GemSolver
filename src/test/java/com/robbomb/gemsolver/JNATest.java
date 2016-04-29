package com.robbomb.gemsolver;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import org.junit.Test;

/**
 * Created by x789997 on 4/29/2016.
 */
public class JNATest {

    @Test
    public void getWindow(final String title) throws Exception {

        User32.INSTANCE.EnumWindows(new WinUser.WNDENUMPROC() {
            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                char[] windowText = new char[512];
                User32.INSTANCE.GetWindowText(hWnd, windowText, 512);
                String windowName = Native.toString(windowText);

                if (title.equalsIgnoreCase(windowName)) {
                    WinDef.RECT rect = new WinDef.RECT();
                    User32.INSTANCE.GetWindowRect(hWnd, rect);
                    System.out.println("rect = " + rect);

                }
                return true;
            }
        }, null);

    }
}