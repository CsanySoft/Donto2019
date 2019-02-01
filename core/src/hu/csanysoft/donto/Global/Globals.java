package hu.csanysoft.donto.Global;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.Random;

public class Globals {

    public static Random random = new Random();

    public static int random(int min, int max){return (int)(Math.random()*(max-min+1)+min);}

    public static final int WORLD_WIDTH = 1280;
    public static final int WORLD_HEIGHT = 720;

    public static final boolean DEBUG_ALL = true;

    public static boolean ACCELEROMETER_AVAILABLE = Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer);
}
