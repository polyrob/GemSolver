package com.robbomb.gemsolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.robbomb.gemsolver.GameColor.GAME_COLORS;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Gem extends JLabel {

    String gemName; // just for easy reference

    public Gem() {
        super("Gem");
        this.setOpaque(true);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
    }


    public void determineColor(Color avg) {
        for (GameColor gameColor : GAME_COLORS) {
            if (similarTo(avg, gameColor.getColor())) {
                gemName = gameColor.getName();
                this.setBackground(gameColor.getColor());
                return;
            }
        }
        System.out.println("Could not determine gameColor, " + avg.toString());
    }


    protected boolean similarTo(Color avg, Color gameColor) {
        double dRed = Math.pow(avg.getRed() - gameColor.getRed(), 2);
        double dBlue = Math.pow(avg.getBlue() - gameColor.getBlue(), 2);
        double dGreen = Math.pow(avg.getGreen() - gameColor.getGreen(), 2);
        double distance = dRed + dBlue + dGreen;

        if (distance < Constants.COLOR_DELTA) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return gemName;
    }

}
