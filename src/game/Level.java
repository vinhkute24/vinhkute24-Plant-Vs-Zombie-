
package game;
import game.sounds.Sound;

import java.util.Random;




public class Level {
    
    protected Random random;
    protected boolean roundOneIsDone = false;
    protected long startTime;
    protected int counter = 0;
    public GameState state;
    protected int i = 1, j = 2, k = 3;
    protected boolean FirstSound = true, FirstSoundH = true;
    public boolean levelIsDone = false, gameOver = false;

    public Level (GameState state) {
	
	random = new Random();
	startTime = System.currentTimeMillis();
	this.state = state;
	
	
    }
    
    public void update () {
	//cheks if game is over
	for (int l = 0; l < 5; l++) {
	    
	    if (state.rows[l].gameOver) {
		this.gameOver = true;
	    }
		
	}

    }
    
}
