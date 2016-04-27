package com.robbomb.gemsolver;

import javax.swing.*;
import java.awt.*;

import static com.robbomb.gemsolver.Constants.BOARD_BACKGROUND;

/**
 * Created by NewRob on 4/26/2016.
 */
public class DisplayWindow extends JFrame {

    private final GridLayout gridLayout;

    public DisplayWindow(Board board) throws HeadlessException {
        super("Test Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gridLayout = new GridLayout(board.getWidth(), board.getHeight());
        getContentPane().setBackground(BOARD_BACKGROUND);
        gridLayout.setHgap(20);
        gridLayout.setVgap(20);
        setLayout(gridLayout);

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                add(board.getGemAt(x, y));
            }
        }
    }

    public void updateBoard() {
        this.pack();
        this.revalidate();
        this.repaint();
    }
}
