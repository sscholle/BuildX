package biz.brainpowered.game.buildx.scene;

import biz.brainpowered.game.buildx.BuildX;
import biz.brainpowered.game.buildx.Core;
import biz.brainpowered.game.buildx.asset.Assets;
import biz.brainpowered.game.buildx.gameitem.GameItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.*;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class WinScene extends Scene {
    String verbNoun = "TALLY";
    String winText = "WINS: ";
    String looseText = "LOSSES: ";

    float elapsedTime;
    float elapsedRunTime;
    float elapsedEndTime;

    public int wins;
    public int losses;

    public WinScene(){
        super(3.0f);
        this.batch = Core.batcher;
        bmFont = Assets.font;
    }

    public void setWins(int wins){
        this.wins = wins;
    }

    public void setLosses(int losses){
        this.losses = losses;
    }

    public void reset(){
        elapsedTime = 0.0f;
        elapsedRunTime = 0.0f;
        elapsedEndTime = 0.0f;
        state = RUN;
    }

    public void setup(){
        super.setup();
        reset();
    }

    public void update(float delta){

        if(state == RUN){
            if (runTime >= elapsedRunTime) {

                String text = ""+(runTime-elapsedRunTime);
                if(text.length() > 4) text = text.substring(0,4);
                bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
                bmFont.draw(batch, text, 1, (BuildX.V_HEIGHT - 1));


                // tally
                float stringLength = bmFont.getBounds(verbNoun).width;
                float stringHeight = bmFont.getBounds(verbNoun).height;
                bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
                bmFont.draw(batch, verbNoun, (BuildX.V_WIDTH / 2) - stringLength / 2, (BuildX.V_HEIGHT - stringHeight));

                // wins

                // losses

                String winsText =  winText+wins;
                float stringLength2 = bmFont.getBounds(winsText).width;
                float stringHeight2 = bmFont.getBounds(winsText).height;
                bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
                bmFont.draw(batch, winsText, (BuildX.V_WIDTH / 2) - stringLength2 / 2, BuildX.V_HEIGHT - stringHeight2 * 3);


                String lossesText =  looseText+losses;
                float stringLength3 = bmFont.getBounds(lossesText).width;
                float stringHeight3 = bmFont.getBounds(lossesText).height;
                bmFont.setColor(new Color(1f, 1f, 1f, 1.0f));
                bmFont.draw(batch, lossesText, (BuildX.V_WIDTH / 2) - stringLength3 / 2, BuildX.V_HEIGHT - stringHeight3 * 5);


            }else{
                state = FINISHED;
            }
            elapsedRunTime += Gdx.graphics.getDeltaTime();

        }

        elapsedTime += Gdx.graphics.getDeltaTime();
    }
}
