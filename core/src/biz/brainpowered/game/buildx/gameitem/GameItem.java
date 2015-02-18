package biz.brainpowered.game.buildx.gameitem;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;

/**
 * Created by Sebnic on 2015/02/14.
 */
public class GameItem implements InputProcessor{
    private Vector<Vector2> connectionPoints;
    private Vector<Vector2> receptionPoints;
    private Sprite sprite;
    private Animation animation;
    private boolean isConnecting;
    private boolean isReceiving;

    public GameItem(Texture texture, int x, int y){
        sprite = new Sprite(texture);
        sprite.setX(x);
        sprite.setY(y);
    }

    public void update(float delta, SpriteBatch batch){
        sprite.draw(batch);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
