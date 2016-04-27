package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class GameColor {

    public static final Color BOARD_BACKGROUND = new Color(26, 12, 40);

    public static final GameColor[] GAME_COLORS = {
            new GameColor(new Color(5, 149, 24), "GREEN"),
            new GameColor(new Color(221, 88, 25), "ORANGE"),
            new GameColor(new Color(253, 17, 53), "RED"),
            new GameColor(new Color(9, 118, 237), "BLUE"),
            new GameColor(new Color(254, 248, 35), "YELLOW"),
            new GameColor(new Color(237, 236, 237), "WHITE"),
            new GameColor(new Color(237, 3, 238), "MAGENTA")};

    String name;
    Color color;

    public GameColor(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
