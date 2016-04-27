package com.robbomb.gemsolver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static com.robbomb.gemsolver.GameColor.BOARD_BACKGROUND;


/**
 * Created by NewRob on 4/26/2016.
 */
public class DisplayWindow extends JFrame {

    private GridLayout gridLayout;
    JPanel gridPanel;
    Button startStopButton;

    public DisplayWindow(Board board) throws HeadlessException {
        super("Test Window");

        initLayouts(board);

        // Load Gem labels
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                gridPanel.add(board.getGemAt(x, y));
            }
        }
    }

    private void initLayouts(Board board) {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel toolbarPanel = new JPanel(new GridLayout(0, 1));
        toolbarPanel.add(new Button("adf"));
        toolbarPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        gridLayout = new GridLayout(board.getWidth(), board.getHeight());
        gridLayout.setHgap(20);
        gridLayout.setVgap(20);

        gridPanel = new JPanel(gridLayout);
        gridPanel.setBackground(BOARD_BACKGROUND);
        gridPanel.setLayout(gridLayout);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;

        setLayout(new GridBagLayout());
        add(gridPanel);
        add(toolbarPanel);

    }

    public void updateBoard() {
        this.pack();
        this.revalidate();
        this.repaint();
    }
}
