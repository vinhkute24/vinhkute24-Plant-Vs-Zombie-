

package game;
import game.sounds.Sound;

import javax.swing.*;



import java.awt.*;


public class SunMakerPlant extends Plant{

    long startTime;

    public SunMakerPlant (int[] arr) {

        super(arr);
        ii = new ImageIcon("SunFlower1.gif");
        image = ii.getImage();
        ii = new ImageIcon("Sunflower1.png");
        preViewImage = ii.getImage();
        startTime = System.currentTimeMillis();
        makeRectangle();

    }

    private void makeRectangle () {

        height = image.getHeight(null);
        width = image.getWidth(null);
        rectangle = new Rectangle(locX, locY, 50, 100);

    }

    public void update (Row row) {

        if ( (System.currentTimeMillis() - startTime) / 1000 > 7 ) {
            row.sky.addASun(new Sun(locX, locY));
            startTime = System.currentTimeMillis();
        }

    }

}
