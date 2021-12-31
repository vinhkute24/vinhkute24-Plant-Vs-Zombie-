package game;


public class GameLoop implements Runnable {

    public static final int FPS = 30;

    private GameFrame canvas;
    private GameState state;
    Level currentLevel;
    boolean lvl1IsDone = false;

    public GameLoop(GameFrame frame) {
        canvas = frame;
    }

    /**
     * This must be called before the game loop starts.
     */
    public void init() {

        final GameFrame frm = canvas;
        state = new GameState(frm);
        canvas.addMouseListener(state.getMouseListener());
        canvas.addMouseMotionListener(state.getMouseMotionListener());
        currentLevel = new Level1(state);

    }
    @Override
    public void run() {

        while (true) {
            try {
                long start = System.currentTimeMillis();
                //
                if (!canvas.ProgramStart) {
                    currentLevel.update();
                }
                state.update();
                canvas.render(state);
                checkCurrentLevel();
                //
                long delay = (1000 / FPS) - (System.currentTimeMillis() - start);
                if (delay > 0) {
                    Thread.sleep(delay);
                }
            } catch (InterruptedException ex) {
            }
        }

    }

    /**
     * checks if game is over or game should go to next level
     */
    private void checkCurrentLevel() {

        if (currentLevel.gameOver) {
            state.refreshRows();
            if (currentLevel instanceof Level1) {
                currentLevel = new Level1(state);
                System.err.println("level1");
            }
        }

        if (currentLevel.levelIsDone) {
            state.refreshRows();
            if (currentLevel instanceof Level1 ) {
               //victory!!!
            }
           

        }
    }
}
    


