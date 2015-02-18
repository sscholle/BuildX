package biz.brainpowered.game.buildx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

/**
 * Created by Sebnic on 2015/01/01.
 */
public class Settings {
    public static boolean soundEnabled = true;
    public final static ArrayList<Score> highscores = new ArrayList<Score>(5);
    public final static String file = "settings";

    public static void load () {
        try {
            FileHandle filehandle = Gdx.files.external(file);

            String[] strings = filehandle.readString().split("\n");

            soundEnabled = Boolean.parseBoolean(strings[0]);
            String line;
            String[] split;
            for (int i = 1; i < 6; i++) {
                line = strings[i];
                split = line.split(" ");
                highscores.add(i-1, new Score(split[0].trim(), Integer.parseInt(split[1].trim())));
            }
        } catch (Throwable e) {
            // :( It's ok we have defaults
        }
    }

    public static void save () {
        try {
            FileHandle filehandle = Gdx.files.external(file);
            Score score;
            filehandle.writeString(Boolean.toString(soundEnabled)+"\n", false);
            for (int i = 0; i < 5; i=i+2) {
                score = highscores.get(i);
                filehandle.writeString(score.name+" "+score.score+"\n", true);
            }
        } catch (Throwable e) {
        }
    }

    public static void addScore (String name, int score) {
        for (int i = 0; i < 5; i++) {
            if (highscores.get(i).score < score) {
                for (int j = 4; j > i; j--)
                    highscores.set(j, highscores.get(j-1));
                highscores.set(i, new Score(name, score));
                break;
            }
        }
    }

    public static int getLastScore () {
        return highscores.get(highscores.size()-1).score;
    }
}