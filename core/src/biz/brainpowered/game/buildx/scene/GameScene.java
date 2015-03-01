package biz.brainpowered.game.buildx.scene;

import com.badlogic.gdx.Gdx;

/**
 * Created by Sebastian on 2015/03/01.
 */
public class GameScene extends Scene {
    public GameScene(float time) {
        super(time);
    }

    public void update(float delta) {
        super.update(delta);

        if(state == PRE_RUN){
            if(preRun >= elapsedTime) {
                drawVerb();
            }else {
                state = RUN;
            }

        }else if(state == RUN){
            checkConnections();
            for (int x = 0; x < drawables.size(); x++){
                drawables.get(x).update(delta, batch);
            }
            drawTimer();
            if (elapsedRunTime >= runTime)
                loose();
            elapsedRunTime += Gdx.graphics.getDeltaTime();

        }else if(state == END){
            drawWinState();
            if(elapsedEndTime >= endTime){
                state = FINISHED;
            }
            elapsedEndTime += Gdx.graphics.getDeltaTime();
        }
    }
}
