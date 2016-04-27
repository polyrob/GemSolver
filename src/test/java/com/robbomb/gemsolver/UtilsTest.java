package com.robbomb.gemsolver;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertTrue;

/**
 * Created by NewRob on 4/26/2016.
 */
public class UtilsTest {

    @Test
    public void getPixelsFromGridTest() {
        Point origin = new Point(0,0);
        Point result = Utils.getPixelsFromGrid(origin);
        assertTrue(result.x == Constants.BITMAP_TOP_LEFT.x);
        assertTrue(result.y == Constants.BITMAP_TOP_LEFT.y);

        Point rnd = new Point(2,5);
        result = Utils.getPixelsFromGrid(rnd);
        assertTrue(result.x == Constants.BITMAP_TOP_LEFT.x + (2*Constants.DEVICE_GEM_X_OFFSET));
    }
}
