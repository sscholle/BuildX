package biz.brainpowered.game.buildx.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sebastian on 2014/07/31.
 */
public class AssetLoader {
    public Map<String, Class> classMap = new HashMap<String, Class>();

    AssetManager manager = new AssetManager();
    /**
     * TextureParameter param = new TextureParameter();
     param.minFilter = TextureFilter.Linear;
     param.genMipMaps = true;
     manager.load("data/mytexture.png", Texture.class, param);
     */

    public boolean finishedLoading = false;

    public AssetLoader () {
        classMap.put("Texture", Texture.class);
        classMap.put("BitmapFont", BitmapFont.class);
        classMap.put("Music", Music.class);
    }

    public void render() {
        if(manager.update()) {
            finishedLoading = true;
        }

        // display loading information
        float progress = manager.getProgress();
        System.out.println("Loading Progress..."+progress);
    }

    public <T> void loadAsset( String path,  String type ){
        manager.load(path, classMap.get(type));
        finishedLoading = false;
    }

    public void unload(String filename) {
        manager.unload(filename);
    }

    public <T> T get ( String path,  String type ){
        T asset = (T)manager.get(path, classMap.get(type));
        return asset;
    }

    public void finishLoading() {
        manager.finishLoading();
    }

    public float getProgress(){
        return manager.getProgress();
    }
}
