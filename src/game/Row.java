
package game;
import game.sounds.Sound;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;





/**
 * a class which handles zombies moving,planets shooting and death of zombies or plants
 * 
 */
public class Row {

    public Sky sky;
    private Rectangle[][] rectsOfHouses;
    public ArrayList<Zombie> zombies;
    public LawnMower lawnmower;
    public Plant[] planets;
    int closestPlanet = 0, rowNum;
    int[] zombiesInitLocation;
    int[] lawnmowerInitLocation;
    int[] planetLoc;

    public final int[] houseLocX = {185, 265, 345, 425, 505, 585, 665,745,825,905};
    public Zombie firstZombie;
    public boolean zombieIsInRow = false, gameOver = false;
    /**
     * 
     * @param rowNum
     * @param sky
     * @param rectsOfHouses 
     */
    public Row(int rowNum, Sky sky, Rectangle[][] rectsOfHouses) {

	this.sky = sky;
	this.rowNum = rowNum;
	this.rectsOfHouses = rectsOfHouses;
	zombies = new ArrayList<>();
	planets = new Plant[9];
	zombiesInitLocation = new int[2];
	zombiesInitLocation[0] = 900;
	lawnmowerInitLocation = new int[2];
	lawnmowerInitLocation[0] = 120;

	planetLoc = new int[2];
	if (rowNum == 0) {
	    zombiesInitLocation[1] = 27;
	    planetLoc[1] = 87;
	    lawnmowerInitLocation[1] = 163;
	}
	if (rowNum == 1) {
	    zombiesInitLocation[1] = 130;
	    planetLoc[1] = 180;
	    lawnmowerInitLocation[1] = 255;
	}
	if (rowNum == 2) {
	    zombiesInitLocation[1] = 218;
	    planetLoc[1] = 280;
	    lawnmowerInitLocation[1] = 360;
	}
	if (rowNum == 3) {
	    zombiesInitLocation[1] =327;
	    planetLoc[1] = 367;
	    lawnmowerInitLocation[1] = 450;
	}
	if (rowNum == 4) {
	    zombiesInitLocation[1] = 425;
	    planetLoc[1] = 465;
	    lawnmowerInitLocation[1] = 550;
	}

	lawnmower = new LawnMower(lawnmowerInitLocation);

    }

    public void makeAZombie(int kindOfZombie) {

	if (kindOfZombie == 1) {
	    zombies.add(new VinhZombie(zombiesInitLocation));
	}
	if (kindOfZombie == 2) {
	    zombies.add(new QuangZombie(zombiesInitLocation));
	}
	if (kindOfZombie == 3) {
	    zombies.add(new NghiaZombie(zombiesInitLocation));
	}
	
	
    }

    public void makeAPlanet(int kindOfPlanet, int locX) {

	for (int i = 0; i < 9; i++) {

	    if (locX == houseLocX[i]) {
		if (planets[i] == null) {

		    planetLoc[0] = houseLocX[i];
		    if (kindOfPlanet == 1) {
			if (sky.storedSun - 100 >= 0) {
			    sky.storedSun -= 100;
			    planets[i] = new SimpleShootPlant(planetLoc);
			}
		    }
		    if (kindOfPlanet == 2) {
			if (sky.storedSun - 50 >= 0) {
			    sky.storedSun -= 50;
			    planets[i] = new SunMakerPlant(planetLoc);
			}
		    }
		    if (kindOfPlanet == 3) {
			if (sky.storedSun - 175 >= 0) {
			    sky.storedSun -= 175;
			    planets[i] = new IceShootPlant(planetLoc);
			}
		    }
		    if (kindOfPlanet == 4) {
			if (sky.storedSun - 50 >= 0) {
			    sky.storedSun -= 50;
			    planets[i] = new WalnutPlant(planetLoc);
			}
		    }
		    if (kindOfPlanet == 5) {
			if (sky.storedSun - 150 >= 0) {
			    sky.storedSun -= 150;
			    int[] rectLoc = {rowNum, i};
			    planets[i] = new CherryPlanet(planetLoc, rectsOfHouses, rectLoc);
			}
		    }
		    
		    if (kindOfPlanet == 8) {
			if (sky.storedSun - 50 >= 0) {
			    sky.storedSun -= 50;
			    planets[i] = new GradelPlant(planetLoc);
			}
		    }
		    
		} else if (kindOfPlanet == 7) {
		    planets[i] = null;
		}
	    }
	}

    }

    public void update(Row[] arrOfRows) throws InterruptedException {

	//checking if any zombie walks into row
	if (zombies.size() == 0) {
	    zombieIsInRow = false;
	} else {
	    zombieIsInRow = true;
	}

	lawnmower.update(this);

	for (int i = 0; i < zombies.size(); i++) {

	    Zombie get = zombies.get(i);
	    get.update(this);

	}

	findTheClosestZombie();

	for (int i = 0; i < planets.length; i++) {

	    Plant planet = planets[i];
	    if (planet != null) {
		try {

		    if (planet instanceof CherryPlanet) {
			//performs special update for cheryPlanet
			((CherryPlanet) planet).update(arrOfRows);
			//checks if cheryPlanet is exploded then removes it
			if (((CherryPlanet) planet).isExploded) {
			    planets[i] = null;
			}

		    } 
		     else {
			planet.update(this);
		    }

		} catch (InterruptedException ex) {
		    Logger.getLogger(game.Row.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }

	}

    }

    /**
     * makes preview planet
     *
     * @param kindOfPlanet
     * @param locX
     * @return
     */
    public Plant makeAPreviewPlanet(int kindOfPlanet, int locX) {

	Plant planet = null;
	for (int i = 0; i < 9; i++) {

	    if (locX == houseLocX[i]) {
		if (planets[i] == null) {
		    planetLoc[0] = houseLocX[i];
		    if (kindOfPlanet == 1) {
			planet = new SimpleShootPlant(planetLoc);
		    }
		    if (kindOfPlanet == 2) {
			planet = new SunMakerPlant(planetLoc);
		    }
		    if (kindOfPlanet == 3) {
			planet = new IceShootPlant(planetLoc);
		    }
		    if (kindOfPlanet == 4) {
			planet = new WalnutPlant(planetLoc);
		    }
		    if (kindOfPlanet == 5) {
			int[] rectLoc = {rowNum, i};
			planet = new CherryPlanet(planetLoc, rectsOfHouses, rectLoc);
		    }
		    if (kindOfPlanet == 7) {
			planet = new Shovel(planetLoc);
		    }
		    if (kindOfPlanet == 8) {
			planet = new GradelPlant(planetLoc);
		    }
		    
		}
	    }

	}
	return planet;
    }

    /**
     * finds which zombie is the first zombie and gets the shots
     */
    private void findTheClosestZombie() {

	int locX = 850;
	for (int i = 0; i < zombies.size(); i++) {

	    Zombie get = zombies.get(i);
	    if (get.locX < locX && !get.zombieIsBurned) {
		firstZombie = get;
		locX = get.locX;
	    }

	}

    }

    public void gameOver(boolean bool) {
	gameOver = bool;
    }

    public boolean allZombiesAreDead() {

	return zombies.isEmpty();

    }

}
