
package game;
import game.sounds.Sound;
import javax.swing.*;


import java.awt.*;


public class PlantInPlantBar extends Plant {

    int firstHouseLocX = 20;
    int firstHouseLocY = 50;
    Image firstImage, secondImage;
    int intLocX = 20, initLocY = 50;
    int kindOfPlanet;

    public PlantInPlantBar(int planetKind, int houseNum) {

	super(new int[7]);
	kindOfPlanet = planetKind;
	if (planetKind == 1) {

	    ii = new ImageIcon("PeashooterSeed.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("PeaShooter.png");
	    secondImage = ii.getImage();

	}
	if (planetKind == 2) {

	    ii = new ImageIcon("SunflowerSeed.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("Sunflower1.png");
	    secondImage = ii.getImage();

	}
	if (planetKind == 3) {

	    ii = new ImageIcon("SnowPea.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("Snowpea21.png");
	    secondImage = ii.getImage();

	}
	if (planetKind == 4) {

	    ii = new ImageIcon("WallNutSeed.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("walnut_full_life1.gif");
	    secondImage = ii.getImage();

	}
	if (planetKind == 5) {

	    ii = new ImageIcon("CherryBombSeed.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("PvZ_Pictures1.png");
	    secondImage = ii.getImage();

	}
	
	if (planetKind == 7) {

	    ii = new ImageIcon("shovel.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("shovel11.png");
	    secondImage = ii.getImage();

	}
	if (planetKind == 8) {

	    ii = new ImageIcon("gradle.PNG");
	    image = firstImage = ii.getImage();
	    ii = new ImageIcon("Agarliclol1.png");
	    secondImage = ii.getImage();

	}
	
	//counting planet location
	if (houseNum == 0) {
	    initLocY = locY = firstHouseLocY ;
		
	}
	if (houseNum == 1) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 2) {
	    initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 3) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 4) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 5) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 6) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	if (houseNum == 7) {
		initLocY = locY = firstHouseLocY+ houseNum*70;
	}
	locX = firstHouseLocX;
	makeRectangle();

    }

    public void makeRectangle() {

	rectangle = new Rectangle(locX, locY, 110, 90);

    }

    /**
     * changes the image when clicked or not
     *
     * @param bool
     */
    public void planetIsSelected(boolean bool) {

	if (bool) {
	    image = secondImage;
	} else {
	    image = firstImage;
	    locX = intLocX;
	    locY = initLocY;
	}

    }

    public int getKindOfPlanet() {
	return kindOfPlanet;
    }

}
