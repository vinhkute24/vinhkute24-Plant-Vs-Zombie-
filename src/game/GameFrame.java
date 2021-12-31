package game;


import javax.swing.*;

import game.sounds.Sound;



import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


public class GameFrame extends JFrame {

    public static final int GAME_HEIGHT = 585;               
    public static final int GAME_WIDTH = 1000 ;  
    public String strpath = "1-1.png";
    private ArrayList<Float> fpsHistory;
    private ImageIcon ii;
    private Image bgImage, planetBarImage1, planetBarImage2, menuImage, shovelImage,lv;
    Font f;
    public static boolean ProgramStart = true;
    private BufferStrategy bufferStrategy;

    public GameFrame(String title) {

	super(title);
	setResizable(false);
	setSize(GAME_WIDTH, GAME_HEIGHT);
	ii = new ImageIcon("backGround1.png");
	bgImage = ii.getImage();
	ii = new ImageIcon("planetBar1.gif");
	planetBarImage2 = ii.getImage();
	ii = new ImageIcon("star.png");
	planetBarImage1 = ii.getImage();
	ii = new ImageIcon("shovel1.png");
	shovelImage = ii.getImage();
	f = new Font("", Font.BOLD, 20);

    }

    public void initBufferStrategy() {
	// Triple-buffering
	createBufferStrategy(3);
	bufferStrategy = getBufferStrategy();
    }

    /**
     * Game rendering with triple-buffering using BufferStrategy.
     */
    public void render(GameState state) {
	// Render single frame
	do {
	    // The following loop ensures that the contents of the drawing buffer
	    // are consistent in case the underlying surface was recreated
	    do {
		// Get a new graphics context every time through the loop
		// to make sure the strategy is validated
		Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
		try {
		    if (ProgramStart) {
			Menu();
		    } else {
			doRendering(graphics, state);
		    }
		} finally {
		    // Dispose the graphics
		    graphics.dispose();
		}
		// Repeat the rendering if the drawing buffer contents were restored
	    } while (bufferStrategy.contentsRestored());

	    // Display the buffer
	    bufferStrategy.show();
	    // Tell the system to do the drawing NOW;
	    // otherwise it can take a few extra ms and will feel jerky!
	    Toolkit.getDefaultToolkit().sync();

	    // Repeat the rendering if the drawing buffer was lost
	} while (bufferStrategy.contentsLost());
    }

    /**
     * Rendering all game elements based on the game state.
     */
    private void doRendering(Graphics2D g2d, GameState state) {

	// Draw background
	g2d.drawImage(bgImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
	//draw preview planet
	if (state.preViewPlanet != null) {
	    g2d.drawImage(state.preViewPlanet.getPreviewPlanetImage(), state.preViewPlanet.locX, state.preViewPlanet.locY , 60, 70, null);
	}
	//draw planet bar
	g2d.drawImage(planetBarImage1, 200, 35, 150, 50, null);
	//g2d.drawImage(planetBarImage2, 90, 40, 400, 60, null);
	for (int i = 0; i < state.planetsInTheBar.length; i++) {
	    if (state.planetsInTheBar[i] != null) {
		PlantInPlantBar object = state.planetsInTheBar[i];
		g2d.drawImage(object.getPlanetImage(), object.locX  , object.locY  , 100, 60, null);
	    }
	}
	//write number of stored suns
	g2d.setColor(Color.BLACK);
	g2d.setFont(f);
	g2d.drawString(state.sky.storedSun.toString(), 270, 70);
	// Draw planets and zombies
	for (int i = 0; i < state.rows.length; i++) {

	    Row row = state.rows[i];
	    //drawing zombies of the row
	    for (int k = 0; k < row.zombies.size(); k++) {

		Zombie get = row.zombies.get(k);
		g2d.drawImage(get.getZombieImage(), get.locX, get.locY, 90, 130, null);
		

	    }
	    //drawing plantes
	    for (int l = 0; l < row.planets.length; l++) {

		Plant planet = row.planets[l];
		if (planet != null) {
		    g2d.drawImage(planet.getPlanetImage(), planet.locX, planet.locY, 70, 70, null);
		}

	    }
	    //drawing shots of each planet
	    for (int l = 0; l < row.planets.length; l++) {

		Plant planet = row.planets[l];
		if (planet != null) {

		    for (int j = 0; j < planet.shots.size(); j++) {
			//drawing shots of planet
			Shot get = planet.shots.get(j);
			g2d.drawImage(get.getShotImage(), get.locX, get.locY, null);

		    }
		}
	    }
	    //drawing machins
	    g2d.drawImage(row.lawnmower.getLawnmowerImage(), row.lawnmower.locX , row.lawnmower.locY -60, 65, 60, null);

	}

	for (int i = 0; i < state.sky.suns.size(); i++) {
	    Sun get = state.sky.suns.get(i);
	    g2d.drawImage(state.sky.getSunImage(), get.locX, get.locY, 70, 70, null);
	}
	
	
    }
    public void Menu() {
	Graphics2D graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
	ii = new ImageIcon(strpath);
	menuImage = ii.getImage();
	graphics.drawImage(menuImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);

    }
	
}
