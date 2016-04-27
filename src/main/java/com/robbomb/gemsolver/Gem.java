package com.robbomb.gemsolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.robbomb.gemsolver.Constants.BOARD_BACKGROUND;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Gem extends JLabel {

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

    private Color determineColor(Color avg) {
        //TODO determine
        return avg;
    }
}
