
package game;
import game.sounds.Sound;

import javax.swing.*;
import java.awt.*;


public class VinhZombie extends Zombie{
    
    public VinhZombie (int arr[]) {
	
	super(arr);
	health = 20;
	ImageIcon ii = new ImageIcon("zombie_normal.gif");
	image = ii.getImage();
	makeRectangle();
	
    }
    /**
     *
     * @return
     */
    public Image getZombieImage () {
	    return image;
    }
    
    private void makeRectangle () {

        rectangle = new Rectangle(locX + 25, locY + 28, 25, 150);
	
    }
    
    public void move() {
	    locX -= speed;
	    makeRectangle();
    }
    
}
