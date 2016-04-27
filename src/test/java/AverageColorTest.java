import com.robbomb.gemsolver.DisplayWindow;
import com.robbomb.gemsolver.Utils;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.robbomb.gemsolver.Constants.GEM_X_OFFSET;
import static com.robbomb.gemsolver.Constants.GEM_Y_OFFSET;
import static com.robbomb.gemsolver.Constants.TOP_LEFT;

/**
 * Created by NewRob on 4/26/2016.
 */
public class AverageColorTest {

    @Test
    public void testGrid() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("capture.bmp"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        DisplayWindow window = new DisplayWindow();


        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Color avg = Utils.averageColor(img, TOP_LEFT.x + (x * GEM_X_OFFSET), TOP_LEFT.y + (y * GEM_Y_OFFSET), 5, 5);
                System.out.print(" " + avg.toString());
                JLabel l = new JLabel("Gem");
                l.setForeground(avg);
                window.add(l);
            }
            System.out.print("\n");
        }
        window.pack();
        window.setVisible(true);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
