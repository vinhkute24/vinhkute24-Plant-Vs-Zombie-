package game;
import game.sounds.Sound;

import javax.swing.*;
import java.awt.*;

public class NghiaZombie  extends Zombie{

    public NghiaZombie (int arr[]) {

        super(arr);
        health = 30;
        ImageIcon ii = new ImageIcon("Buckethead_Zombie1.gif");
        image = ii.getImage();
        makeRectangle();

    }

    private void makeRectangle () {

        rectangle = new Rectangle(locX + 25, locY + 28, 50, 150);

    }

    public void move() {
        locX -= speed;
        makeRectangle();
    }

}
