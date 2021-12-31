
package game;
import game.sounds.Sound;

import javax.sound.sampled.Clip;
import javax.swing.*;



import java.awt.*;
import java.util.ArrayList;

/**
 * this is the sub class for all zombies
 *
 * 
 */
public class Zombie {

    public int health = 4;
    protected int dps = 1;
    public int locX;
    public int locY;
    protected int height;
    protected int width;
    protected int initialSpeed = 1;
    public int speed = initialSpeed;
    protected double eatingStartTime = 1e-8;
    protected Rectangle rectangle;
    public Image image;
    protected boolean zombieReachedPlanet = false, firstBiteIsDone = false, zombieGotSlow = false;
    public boolean zombieIsBurned = false;
    public static boolean FirstSound = true;
    long getSlowStartTime, getBurnedStartTime;
    public ArrayList<Shot> shots;
    public static Clip clpMusicEating;

    public Zombie(int arr[]) {
        locX = arr[0];
        locY = arr[1];
        shots = new ArrayList<>();

    }

    private void makeRectangle() {

    }

    public Image getZombieImage() {
        return image;
    }

    public void move() {
    }

    public void zombieGotALowSpeedShot() {
        if (speed > 0)
            speed = 1;
        else
            speed = -1;
        getSlowStartTime = System.currentTimeMillis();
        zombieGotSlow = true;

    }

    public void zombieIsBurned() {

        zombieIsBurned = true;
        getBurnedStartTime = System.currentTimeMillis();
        ImageIcon ii = new ImageIcon("Incinerated_Zombie1.gif");
        image = ii.getImage();

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * this method performs the biting process and removes the eaten planets
     *
     * 
     */
    public void update(Row row) {
        //removes burned zombie after a few seconds
        if (zombieIsBurned && (System.currentTimeMillis() - getBurnedStartTime) / 1000 > 4.5) {
            row.zombies.remove(this);
        }

        int t = 0;
        for (int i = row.planets.length - 1; i >= 0; i--) {

            Plant planet = row.planets[i];
            if (planet != null) {

                if (planet.getRectangle().intersects(this.rectangle) && !zombieIsBurned) {

                    t++;
                    zombieReachedPlanet = true;
                    if (FirstSound) {
                        clpMusicEating = Sound.clipForLoopFactory("chomp.wav");
                        clpMusicEating.loop(Clip.LOOP_CONTINUOUSLY);
                        FirstSound = false;
                    }
                    if (!firstBiteIsDone) {

                        firstBiteIsDone = true;
                        planet.health -= dps;
                        eatingStartTime = System.currentTimeMillis();

                    } else if (((System.currentTimeMillis() - eatingStartTime) / 1000 > 1) && eatingStartTime != 1e-9) {
                        planet.health -= dps;
                        eatingStartTime = System.currentTimeMillis();
                    }
                    if (planet.health <= 0) {
                        Sound.stopLoopingSounds(clpMusicEating);
                        FirstSound = true;
                        row.planets[i] = null;
                        zombieReachedPlanet = false;
                        eatingStartTime = 999;
                        firstBiteIsDone = false;
                    }

                }

            }

        }
        if (t == 0) {
            zombieReachedPlanet = false;
        }

        if (!zombieReachedPlanet && !zombieIsBurned) {
            this.move();
        }
        if (zombieGotSlow) {
            if ((System.currentTimeMillis() - getSlowStartTime) / 1000 > 3) {
                zombieGotSlow = false;
                if (speed > 0)
                    speed = initialSpeed;
                else
                    speed = -1 * initialSpeed;
            }
        }
        /**
         * checking if a zombie could reach the house
         */
        if (this.locX <= 1) {
            row.gameOver(true);
            Sound.playSound("atebrains.wav");
        }

        if (this.locX > 1000) {
            row.zombies.remove(this);
        }

    }

}
