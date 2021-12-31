
package game;
import game.sounds.Sound;
import javax.sound.sampled.Clip;



import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class holds the state of game and all of its elements. This class also
 * handles user inputs, which affect the game state.
 *
 * 
 */
public class GameState {

    public int locX, locY, diam;
    public boolean gameOver;
    private GameFrame frm;
    private boolean mousePress, mouseOnIcon = false;
    Integer kindOfSelectedIcon = null;
    PlantInPlantBar selectedPlanetSeed = null;
    public Plant preViewPlanet = null;
    public int mouseX, mouseY;
    Rectangle mouseRect = null;
    private MouseHandler mouseHandler;
    PlantInPlantBar[] planetsInTheBar;
    Rectangle[][] rectsOfHouses;
    Sky sky;
    public static Clip clpMusicStart;


    int test1 = 0;
    int[] f = {200, 200};
    Row[] rows;
    int[] locXArr = {182, 260, 411, 491, 573, 653, 724,796,880,972,1000};
	
    Image planet1, planet2;

    public GameState(GameFrame frm) {
	
		this.frm = frm;
		rectsOfHouses = new Rectangle[5][10];
		makeRectsOfHouses();
		planetsInTheBar = new PlantInPlantBar[8];
		rows = new Row[5];
		sky = new Sky();
	

	for (int i = 0; i < 5; i++) {
	    rows[i] = new Row(i, sky, rectsOfHouses);
	}
	mouseHandler = new MouseHandler();
	
    }

    /**
     * The method which updates the game state.
     */
    public void update() throws InterruptedException {

	for (int i = 0; i < rows.length; i++) {
	    rows[i].update(rows);
	}
	sky.update();
    }

    public void refreshRows() {

	planetsInTheBar = new PlantInPlantBar[8];
	rows = new Row[5];
	sky = new Sky();
	for (int i = 0; i < 5; i++) {
	    rows[i] = new Row(i, sky, rectsOfHouses);
	}

    }

    public boolean allZombiesAreDead() {

	boolean bool = true;
	for (int i = 0; i < rows.length; i++) {
	    if (!rows[i].allZombiesAreDead()) {
		bool = false;
	    }
	}

	return bool;

    }

    public MouseListener getMouseListener() {
		return mouseHandler;
    }

    public MouseMotionListener getMouseMotionListener() {
		return mouseHandler;
    }

    /**
     * The mouse handler.
     */
    class MouseHandler extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
	    mouseX = e.getX();
	    mouseY = e.getY();
	    if (GameFrame.ProgramStart && mouseX > 340 && mouseX < 650
		&& mouseY > 505 && mouseY < 544) {
		Sound.stopLoopingSounds(Main.clpMusicBackground);
		clpMusicStart = Sound.clipForLoopFactory("level1.wav");
		clpMusicStart.loop(Clip.LOOP_CONTINUOUSLY);
		GameFrame.ProgramStart = false;
		frm.render(GameState.this);


	    } else if (GameFrame.ProgramStart && mouseX > 750 && mouseX < 850
		    && mouseY > 500 && mouseY < 560) {
		frm.dispose();
	    }

	    mousePress = true;
	    mouseRect = new Rectangle(mouseX, mouseY, 1, 1);
	    checkPlanetIsSelected(mousePress, mouseRect);
	    checkIfSunIsSelected(mouseRect);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	    mouseX = e.getX();
	    mouseY = e.getY();
	    if (GameFrame.ProgramStart && mouseX > 340 && mouseX < 650
		    && mouseY > 505 && mouseY < 544) {
			frm.strpath = "1-2.png";
			frm.Menu(); 
	    } else if (GameFrame.ProgramStart) {
			frm.strpath = "1-1.png";
			frm.Menu();
	    }
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 9; j++) {
		    if ((rectsOfHouses[i][j].intersects(mouseRect)) && (mouseOnIcon)) {
			rows[i].makeAPlanet(kindOfSelectedIcon, rows[i].houseLocX[j]);
		    }
		}
	    }
	    mousePress = false;
	    mouseOnIcon = false;
	    preViewPlanet = null;
	    if (selectedPlanetSeed != null) {
		selectedPlanetSeed.planetIsSelected(false);
	    }
	    selectedPlanetSeed = null;

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	    mouseX = e.getX();
	    mouseY = e.getY();
	    mouseRect = new Rectangle(mouseX, mouseY, 1, 1);
	    if (mouseOnIcon) {
		selectedPlanetSeed.locX = mouseX-10;
		selectedPlanetSeed.locY = mouseY-10 ;
	    }
	    for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 9; j++) {
		    if ((rectsOfHouses[i][j].intersects(mouseRect)) && (mouseOnIcon)) {
			preViewPlanet = rows[i].makeAPreviewPlanet(kindOfSelectedIcon, rows[i].houseLocX[j]);
		    }
		}
	    }

	}
    }

    /**
     * changes the planet image if its clicked or released
     *
     * @param bool
     */
    private void checkPlanetIsSelected(boolean bool, Rectangle mouseRect) {

	for (int i = 0; i < planetsInTheBar.length; i++) {
	    if (planetsInTheBar[i] != null) {

		PlantInPlantBar planetInPlanetBar = planetsInTheBar[i];
		//updating planet's rectangle
		planetInPlanetBar.makeRectangle();
		if (planetInPlanetBar.getRectangle().intersects(mouseRect)) {

		    planetInPlanetBar.planetIsSelected(bool);
		    mouseOnIcon = bool;
		    Sound.playSound("select.wav");
		    selectedPlanetSeed = planetInPlanetBar;
		    kindOfSelectedIcon = planetInPlanetBar.getKindOfPlanet();

		}

	    }
	}

    }

    /**
     * checks if a sun is selected
     *
     * @param rect
     */
    public void checkIfSunIsSelected(Rectangle rect) {
		sky.checkIfASunIsSelected(rect);
    }

    /**
     * making rectangles of each house on ground
     */
    private void makeRectsOfHouses() {

	int locX = 185;
	int locY = 84;

	for (int i = 0; i < 5; i++) {
	    for (int j = 0; j < 10; j++) {
		rectsOfHouses[i][j] = new Rectangle(locX, locY, 79, 88);
		locX += 79;
	    }
	    locX = 185;
	    locY += 90;
	}

    }

}
