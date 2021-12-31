package game;
import game.sounds.Sound;
import game.GameFrame;
import javax.swing.*;



import java.awt.*;


public class LawnMower {

    ImageIcon ii;
    Image image;
    Rectangle rectangle;
    public int locX;
    public int locY;
    int height;
    int width;
    int intLocY, initLocX = 100;
    int speed = 7;
    public boolean zombieRich = false, firstSound = true;


    public LawnMower(int arr[]) {

        locX = arr[0];
        locY = arr[1];

        ii = new ImageIcon("Lawn_Mower1.png");
        image = ii.getImage();
        makeRectangle();

    }

    public Image getLawnmowerImage() {
        return image;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    private void makeRectangle() {

        rectangle = new Rectangle(locX, locY, 100, 150);

    }

    public void move() {
        locX += speed;
        makeRectangle();
    }

    public void update(Row row) {

        for (int i = 0; i < row.zombies.size(); i++) {
            // intersects la function của lớp rectangle trong java.awt để phát hiện va chạm
            if (this.rectangle.intersects(row.zombies.get(i).getRectangle())) {
                row.zombies.remove(row.zombies.get(i));
                i -= 1;
                zombieRich = true;
                ii = new ImageIcon("lawn_mower1.gif");
                image = ii.getImage();
                if (firstSound) {
                    Sound.playSound("lawnmower.wav");
                    firstSound = false;
                }
            }

        }

        if (zombieRich) {
            this.move();
        }

    }
}
