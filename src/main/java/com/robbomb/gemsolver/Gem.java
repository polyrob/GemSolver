package com.robbomb.gemsolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.robbomb.gemsolver.GameColor.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Gem extends JLabel {

    GameColor gameColor; // just for easy reference

    public Gem() {
        super("Gem");
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setGameColor(UNKNOWN);
    }


    public void determineColor(Color avg) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(avg.getRed(), avg.getGreen(), avg.getBlue(), hsv);
        if (hsv[1] < 0.2f) {
            // it's white - can't use hue for white - using saturation
            this.gameColor = COLOR_WHITE;
            this.setBackground(avg);
            return;
        } else if (hsv[0] < 0.042 || hsv[0] > 0.917) {
            this.gameColor = COLOR_RED;
            this.setBackground(avg);
            return;
        } else {
            for (GameColor gameColor : HUE_COMPARE_COLORS) {
                if (similarTo(hsv, gameColor)) {
                    this.gameColor = gameColor;
                    this.setBackground(avg);
                    return;
                }
            }
        }
        System.out.println("Could not determine gameColor, " + avg.toString());
        this.gameColor = UNKNOWN;
        this.setBackground(avg);
    }


    protected boolean similarTo(float[] inputHsv, GameColor gameColor) {

        double distance = Math.abs(inputHsv[0] - gameColor.getHue());

        if (distance < Constants.COLOR_DELTA) {
            return true;
        } else {
            return false;
        }
    }

    public GameColor getGameColor() {
        return gameColor;
    }

    public void setGameColor(GameColor gameColor) {
        this.gameColor = gameColor;
        this.setBackground(gameColor.getColor());
    }

    @Override
    public String toString() {
        return gameColor.getName();
    }

}
