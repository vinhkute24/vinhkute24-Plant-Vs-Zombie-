

package game;
import game.sounds.Sound;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * this is the sub class for all kind of shots
 *
 * 
 */
public class Shot {

    public int locX;
    public int locY;
    protected int height;
    protected int width;
    protected int speed = 12;
    protected Rectangle rectangle;
    protected Image image;

    public Shot(int locX, int locY) {

        this.locX = locX +50 ;
        this.locY = locY ;

    }
    /**
     * 
     * second constructor used by catapult zombie
     * 
     * @param locX
     * @param locY
     * @param kind 
     */
    public Shot(int locX, int locY,int kind) {

        this.locX = locX +50 ;
        this.locY = locY ;

    }

    private void makeRectangle() {

    }

    public Image getShotImage() {
        return image;
    }

    public void move() {
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
