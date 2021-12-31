
package game;
import game.sounds.Sound;

import javax.sound.sampled.Clip;



public class Level1 extends Level {

    private boolean bool = true, bool2 = true, firstSound = true;
    public static Clip clpMusicStart;

    public Level1(GameState state) {

        super(state);
        state.sky.storedSun = 600;
        state.planetsInTheBar[0] = new PlantInPlantBar(1, 0);
	    state.planetsInTheBar[1] = new PlantInPlantBar(2, 1);
        state.planetsInTheBar[2] = new PlantInPlantBar(4, 2);
        state.planetsInTheBar[3] = new PlantInPlantBar(5, 3);
        state.planetsInTheBar[4] = new PlantInPlantBar(8, 4);
        state.planetsInTheBar[5] = new PlantInPlantBar(3, 5);
        state.planetsInTheBar[6] = new PlantInPlantBar(7, 6);
    }

    public void update() {

        super.update();

        if (firstSound) {

            if (GameState.clpMusicStart != null)
                Sound.stopLoopingSounds(GameState.clpMusicStart);
            clpMusicStart = Sound.clipForLoopFactory("level2.wav");
            clpMusicStart.loop(Clip.LOOP_CONTINUOUSLY);
            firstSound=false;
        }

        if (!roundOneIsDone) {

            if ((System.currentTimeMillis() - startTime) / 1000 > 15 - counter) {
                if (FirstSound) {
                    Sound.playSound("ZombieComing.wav");
                    FirstSound = false;
                }
                i = random.nextInt(4);
                if (counter > 1) {
                    while (true) {
                        k = random.nextInt(5);
                        if (k != i) {
                            state.rows[k].makeAZombie(random.nextInt(4)+1);
                            break;
                        }
                    }
                }
                state.rows[i].makeAZombie(1);
                startTime = System.currentTimeMillis();
                counter++;

            }
            if (counter > 4) {
                roundOneIsDone = true;
            }

        }  /**
         * performing huge wave
         */
        else {
            if (FirstSoundH) {
                Sound.playSound("Hojoom.wav");
                FirstSoundH = false;
            }
            if (counter > 50) {

                if (bool2) {
                    for (int i = 0; i < state.rows.length; i++) {
                        state.rows[i].makeAZombie(1 + random.nextInt(4));
                    }
                    bool2 = false;
                }

                if (state.allZombiesAreDead()) {
                    levelIsDone = true;
                }

            } else if ((System.currentTimeMillis() - startTime) / 1000 > 17 - counter && !levelIsDone) {

                i = random.nextInt(5);
                state.rows[i].makeAZombie(1 + random.nextInt(4));

                while (true) {
                    j = random.nextInt(5);
                    if (j != i) {
                        state.rows[j].makeAZombie(1 + random.nextInt(4));
                        break;
                    }
                }
                while (true) {
                    k = random.nextInt(5);
                    if (k != i && k != j) {
                        state.rows[k].makeAZombie(1 + random.nextInt(4));
                        break;
                    }
                }

                startTime = System.currentTimeMillis();
                counter += 2;

            }

        }

    }

}
