package biz.brainpowered.game.buildx.asset;

import biz.brainpowered.game.buildx.Settings;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Sebnic on 2015/01/01.
 */

public class Assets {
    //chuck

    // keep
    public static Texture menu;
    public static Texture ground;
    public static Texture roof;
    public static Texture walls;
    public static Texture robotHead;
    public static Texture robotBody;




    public static Texture items;
    public static TextureRegion mainMenu;
    public static TextureRegion pauseMenu;
    public static TextureRegion ready;
    public static TextureRegion gameOver;
    public static TextureRegion highScoresRegion;
    public static TextureRegion logo;
    public static TextureRegion soundOn;
    public static TextureRegion soundOff;
    public static TextureRegion arrow;
    public static TextureRegion pause;
    public static TextureRegion spring;
    public static TextureRegion castle;
    public static Animation coinAnim;
    public static Animation bobJump;
    public static Animation bobFall;
    public static Animation bobHit;
    public static Animation squirrelFly;
    public static Animation platform;
    public static Animation breakingPlatform;
    public static BitmapFont font;
    public static BitmapFont titleFont;

    public static Music music;
    public static Sound jumpSound;
    public static Sound highJumpSound;
    public static Sound hitSound;
    public static Sound coinSound;
    public static Sound clickSound;

    public static boolean isInited = false;
    public static AssetLoader loader;
    static HashMap<String, String> allAssets = new HashMap<String, String>();
    static HashMap<String, TextBounds> tbMap = new HashMap<String, TextBounds>();

    public static void init(){
        allAssets.put("items/Menu.png", "Texture");
        allAssets.put("items/ground.png", "Texture");
        allAssets.put("items/roof.png", "Texture");
        allAssets.put("items/walls.png", "Texture");
        allAssets.put("items/robot-head.png", "Texture");
        allAssets.put("items/robot-body.png", "Texture");
        allAssets.put("font/small.fnt", "BitmapFont");

        isInited = true;
    }

    static Iterator<String> keySetIterator = allAssets.keySet().iterator();
    public static void loadAssets(){
        for (String key : allAssets.keySet()) {
            loader.loadAsset(key, allAssets.get(key));
            System.out.println("loading: " + key);
        }
    }

    public static boolean isLoaded(){
        if(loader.finishedLoading){
            menu = getTexture("items/Menu.png");
            ground = getTexture("items/ground.png");
            roof = getTexture("items/roof.png");
            walls = getTexture("items/walls.png");
            robotHead = getTexture("items/robot-head.png");
            robotBody = getTexture("items/robot-body.png");
            font = getFont("font/small.fnt");
            return true;
        }
        return false;
    }

    public static void createFontBounds(String key, BitmapFont fnt, String text){
        tbMap.put(key, fnt.getBounds(text)); // storing bounds of fonts.. experimantal
    }

    public static TextBounds getFontBounds(String key){
        return tbMap.get(key);
    }

    public static void unloadAssets(){
        while(keySetIterator.hasNext()){
            String key = keySetIterator.next();
            loader.unload(key);
            System.out.println("unload asset key: " + key + " value: " + allAssets.get(key));
        }
    }
    public static Texture getTexture(String key){
        return loader.get(key, "Texture");
    }
    public static BitmapFont getFont(String key){
        return loader.get(key, "BitmapFont");
    }
    /**
     * TODO: develop assets
     */
//    public static void loadSplash (AssetLoader loader) {
//        background = loadTexture("images/background.png");
//        //emblem = loadTexture("images/emblem.png");
//        title = loadTexture("images/title.png");
//        menu = loadTexture("images/menu.png");
//        progressbar = loadTexture("progressbar.png");
//        font = new BitmapFont(Gdx.files.internal("font/Reload.fnt"), Gdx.files.internal("font/Reload.png"), false);
//    }



   // public void test()
//    public static void load (AssetLoader loader) {
//        // something to simulate a load
//        loader.loadAsset("gods_rays-wallpaper-1920x1080.jpg", "Texture");
//        loader.loadAsset("god-rays-pano-along-the-tok-cutoff.jpg", "Texture");
//        loader.loadAsset("god-rays.jpg", "Texture");
//        background = loadTexture("data/background.png");
//        backgroundRegion = new TextureRegion(background, 0, 0, 320, 480);
//
//        items = loadTexture("data/items.png");
//        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
//        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
//        ready = new TextureRegion(items, 320, 224, 192, 32);
//        gameOver = new TextureRegion(items, 352, 256, 160, 96);
//        highScoresRegion = new TextureRegion(Assets.items, 0, 257, 300, 110 / 3);
//        logo = new TextureRegion(items, 0, 352, 274, 142);
//        soundOff = new TextureRegion(items, 0, 0, 64, 64);
//        soundOn = new TextureRegion(items, 64, 0, 64, 64);
//        arrow = new TextureRegion(items, 0, 64, 64, 64);
//        pause = new TextureRegion(items, 64, 64, 64, 64);
//
//        spring = new TextureRegion(items, 128, 0, 32, 32);
//        castle = new TextureRegion(items, 128, 64, 64, 64);
//        coinAnim = new Animation(0.2f, new TextureRegion(items, 128, 32, 32, 32), new TextureRegion(items, 160, 32, 32, 32),
//                new TextureRegion(items, 192, 32, 32, 32), new TextureRegion(items, 160, 32, 32, 32));
//        bobJump = new Animation(0.2f, new TextureRegion(items, 0, 128, 32, 32), new TextureRegion(items, 32, 128, 32, 32));
//        bobFall = new Animation(0.2f, new TextureRegion(items, 64, 128, 32, 32), new TextureRegion(items, 96, 128, 32, 32));
//        bobHit = new Animation(0.2f, new TextureRegion(items, 128, 128, 32, 32));
//        squirrelFly = new Animation(0.2f, new TextureRegion(items, 0, 160, 32, 32), new TextureRegion(items, 32, 160, 32, 32));
//        platform = new Animation(0.2f, new TextureRegion(items, 64, 160, 64, 16));
//        breakingPlatform = new Animation(0.2f, new TextureRegion(items, 64, 160, 64, 16), new TextureRegion(items, 64, 176, 64, 16),
//                new TextureRegion(items, 64, 192, 64, 16), new TextureRegion(items, 64, 208, 64, 16));
////
//        font = new BitmapFont(Gdx.files.internal("font/Reload.fnt"), Gdx.files.internal("font/Reload.png"), false);
//
//        music = Gdx.audio.newMusic(Gdx.files.internal("data/music.mp3"));
//        music.setLooping(true);
//        music.setVolume(0.5f);
//        if (Settings.soundEnabled) music.play();
//        jumpSound = Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
//        highJumpSound = Gdx.audio.newSound(Gdx.files.internal("data/highjump.wav"));
//        hitSound = Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
//        coinSound = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
//        clickSound = Gdx.audio.newSound(Gdx.files.internal("data/click.wav"));
//
//        coinAnim.setPlayMode(Animation.PlayMode.LOOP);
//        bobJump.setPlayMode(Animation.PlayMode.LOOP);
//        bobFall.setPlayMode(Animation.PlayMode.LOOP);
//        bobHit.setPlayMode(Animation.PlayMode.LOOP);
//        squirrelFly.setPlayMode(Animation.PlayMode.LOOP);
//        platform.setPlayMode(Animation.PlayMode.LOOP);
//    }

    public static void playSound (Sound sound) {
        if (Settings.soundEnabled) sound.play(1);
    }

    public static void dispose(){

        menu.dispose();
        ground.dispose();
        roof.dispose();
        walls.dispose();
        font.dispose();
    }
}
