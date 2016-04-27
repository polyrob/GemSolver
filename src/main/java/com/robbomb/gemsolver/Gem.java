package com.robbomb.gemsolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.robbomb.gemsolver.Constants.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Gem extends JLabel {

    Color[] gameColors = {GEM_GREEN, GEM_BLUE, GEM_MAGENTA, GEM_ORANGE, GEM_RED, GEM_WHITE, GEM_YELLOW};

    public Gem() {
        super("Gem");
        this.setOpaque(true);
//        this.setBackground(BOARD_BACKGROUND);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
    }


    @Override
    public String toString() {
        return getForeground().toString();
    }

    public void setColor(Color avg) {
        Color c = determineColor(avg);
        this.setBackground(c);
    }

    protected Color determineColor(Color avg) {
        for (Color gameColor : gameColors) {
            if (similarTo(avg, gameColor)) {
                return gameColor;
            }
        }
        System.out.println("Could not determine gameColor, " + avg.toString());
        return Color.BLACK;
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
}
