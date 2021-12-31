
package game;
import game.sounds.Sound;

import javax.swing.*;


import java.awt.*;


public class IceShootPlant extends Plant {

    public IceShootPlant(int arr[]) {

        super(arr);
        shootingRate = 10;
        ii = new ImageIcon("Snow-Pea1.gif");
        image = ii.getImage();
        ii = new ImageIcon("Snowpea21.png");
        preViewImage = ii.getImage();
        makeRectangle();

    }

    private void makeRectangle() {

        height = image.getHeight(null);
        width = image.getWidth(null);
        rectangle = new Rectangle(locX, locY, 70, 150);

    }

    @Override
    public void update(Row row) throws InterruptedException {

        super.update(row);
        if (row.zombieIsInRow) {
            shotCounter += 1;
            if (shotCounter == 7 * shootingRate) {

                shots.add(new IceShoot(locX, locY));
                shotCounter = 0;

            }
        }
    }

}
