

package game;
import game.sounds.Sound;

import javax.swing.*;



import java.awt.*;


public class SimpleShootPlant extends Plant {

    public SimpleShootPlant(int arr[]) {

        super(arr);
        shootingRate = 7;
        ii = new ImageIcon("PeaShooter.gif");
        image = ii.getImage();
        ii = new ImageIcon("PeaShooter.png");
        preViewImage = ii.getImage();
        makeRectangle();

    }

    private void makeRectangle() {

        height = image.getHeight(null);
        width = image.getWidth(null);
        rectangle = new Rectangle(locX, locY, 60, 150);

    }

    @Override
    public void update(Row row) throws InterruptedException {

        super.update(row);
        if (row.zombieIsInRow) {
            ii = new ImageIcon("PeaShooter1.gif");
            image = ii.getImage();
            shotCounter += 1;
            if (shotCounter == 7 * shootingRate) {
                shots.add(new SimpleShot(locX, locY));
                shotCounter = 0;
            }
        }
        else{
            ii = new ImageIcon("PeaShooter.gif");
            image = ii.getImage();
        }
        

    }

}
