
package game;
import game.sounds.Sound;

import javax.swing.*;



import java.awt.*;
import java.util.ArrayList;

/**
 * this is the sub class for all planets
 *
 * 
 */
public class Plant {

    public ArrayList<Shot> shots;
    protected Rectangle rectangle;
    protected ImageIcon ii;
    protected Image image, preViewImage;
    public int health = 4;
    protected Integer shootingRate = null;
    public int locX;
    public int locY;
    protected int height;
    protected int width;
    protected int shotCounter;

    public Plant(int arr[]) {

        locX = arr[0];
        locY = arr[1];
        shots = new ArrayList<>();

    }

    private void makeRectangle() {

    }

    public Image getPlanetImage() {
        return image;
    }

    public Image getPreviewPlanetImage() {
        return preViewImage;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * checks if the shots hit the closest zombie
     *
     * @param row
     * @throws InterruptedException
     */
    public void update(Row row) throws InterruptedException {

        for (int i = 0; i < shots.size(); i++) {
            Shot shot = shots.get(i);
            shot.move();
            try {

                if (!row.firstZombie.zombieIsBurned && row.firstZombie.health > 0
                        && row.firstZombie.getRectangle().intersects(shot.getRectangle())) {

                    if (shot instanceof IceShoot) {
                        row.firstZombie.zombieGotALowSpeedShot();
                    }
                    Sound.playSound("shoot.wav");
                    shots.remove(shot);
                    row.firstZombie.health -= 3;
                    //checking if zombie is dead
                    if (row.firstZombie.health <= 0) {
                        row.zombies.remove(row.firstZombie);
                        Sound.stopLoopingSounds(Zombie.clpMusicEating);
                        Zombie.FirstSound = true;
                    }

                }

            } catch (Exception e) {
            }
            //removes the shot that goes out of frame
            if (shot.locX > 900) {
                shots.remove(shot);
            }
        }

    }

}
