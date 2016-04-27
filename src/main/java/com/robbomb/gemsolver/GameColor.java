package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class GameColor {

    public static final Color BOARD_BACKGROUND = new Color(26, 12, 40);

    public static final GameColor UNKNOWN =  new GameColor("UNKNOWN", Color.darkGray, 160, 90, 0);

    public static final GameColor COLOR_GREEN =  new GameColor("GREEN", Color.GREEN, 131, 84, 79);
    public static final GameColor COLOR_ORANGE =  new GameColor("ORANGE", Color.ORANGE, 28, 79, 95);
    public static final GameColor COLOR_RED = new GameColor("RED", Color.RED, 351, 92, 98);
    public static final GameColor COLOR_BLUE = new GameColor("BLUE", Color.BLUE, 213, 94, 89);
    public static final GameColor COLOR_YELLOW = new GameColor("YELLOW", Color.YELLOW, 56, 87, 99);
    public static final GameColor COLOR_WHITE = new GameColor("WHITE", Color.WHITE, 240, 0, 93);
    public static final GameColor COLOR_MAGENTA = new GameColor("MAGENTA", Color.MAGENTA, 300, 92, 93);

    public static final GameColor[] GAME_COLORS = {COLOR_BLUE, COLOR_GREEN, COLOR_MAGENTA, COLOR_ORANGE, COLOR_RED, COLOR_WHITE, COLOR_YELLOW};

    public static final GameColor[] HUE_COMPARE_COLORS = {COLOR_BLUE, COLOR_GREEN, COLOR_MAGENTA, COLOR_ORANGE, COLOR_YELLOW};

    String name;
    Color color;
    float hue;
    float sat;
    float bright;

    public GameColor(String name, Color color, float hue, float sat, float bright) {
        this.name = name;
        this.color = color;
        this.hue = hue/360;
        this.sat = sat/100;
        this.bright = bright/100;
    }

    public String getName() {
        return name;
    }

    public float getHue() {
        return hue;
    }

    public float getSat() {
        return sat;
    }

    public float getBright() {
        return bright;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "GameColor{" +
                "name='" + name + '\'' +
                '}';
    }
}
