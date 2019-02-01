package hu.csanysoft.donto.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;


public class Assets {
    public static AssetManager manager;

    public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

    static final FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();

    static {
        fontParameter.fontFileName = "arial.ttf";
        fontParameter.fontParameters.size = 30;
        fontParameter.fontParameters.characters = CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }

    public static final AssetDescriptor<BitmapFont> ARIAL_30_FONT
            = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> EMPTY_TEXTURE
            = new AssetDescriptor<Texture>("zolipls.png", Texture.class);

    public static final AssetDescriptor<Texture> START
            = new AssetDescriptor<Texture>("start_red.png", Texture.class);

    public static final AssetDescriptor<Texture> START_DOWN
            = new AssetDescriptor<Texture>("start_down_red.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT
            = new AssetDescriptor<Texture>("exit_red.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT_DOWN
            = new AssetDescriptor<Texture>("exit_down_red.png", Texture.class);

    //Ellenfelek

    public static final AssetDescriptor<Texture> BADVIRUS_TEXTURE
            = new AssetDescriptor<Texture>("animations/virus/red.png", Texture.class);

    public static final AssetDescriptor<Texture> GOODVIRUS_TEXTURE
            = new AssetDescriptor<Texture>("animations/virus/green.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> VIRUSTAIL_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/virus/tailani.atlas", TextureAtlas.class);

    //Chip fejlesztések

    public static final AssetDescriptor<Texture> CHIPSPEED_TEXTURE
            = new AssetDescriptor<Texture>("chips/speed.png", Texture.class);

    public static final AssetDescriptor<Texture> CHIPSHIELD_TEXTURE
            = new AssetDescriptor<Texture>("chips/shield.png", Texture.class);

    public static final AssetDescriptor<Texture> CHIPWEAPON_TEXTURE
            = new AssetDescriptor<Texture>("chips/weapon.png", Texture.class);

    //Buborék atlasz
    public static final AssetDescriptor<TextureAtlas> BUBBLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/bubbles/bubbleani.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> ROBOT_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/android.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> EXPLOSION_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/explosion2.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> BACKGROUND_TEXTURE
            = new AssetDescriptor<Texture>("background.png", Texture.class);

    public static final AssetDescriptor<Texture> ARROW_TEXTURE
            = new AssetDescriptor<Texture>("ui/arrow.png", Texture.class);

    public static final AssetDescriptor<Texture> GAMEOVER_TEXTURE
            = new AssetDescriptor<Texture>("gameover.png", Texture.class);

    public static final AssetDescriptor<Sound> SPLASH_SOUND
            = new AssetDescriptor<Sound>("sound/splash.mp3", Sound.class);

    public static final AssetDescriptor<Sound> WIN_SOUND
            = new AssetDescriptor<Sound>("sound/win.mp3", Sound.class);

    public static final AssetDescriptor<Sound> LOST_SOUND
            = new AssetDescriptor<Sound>("sound/lose.mp3", Sound.class);

    public static void prepare() {
        manager = new AssetManager();
        Texture.setAssetManager(manager);
    }


    public static void load() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        manager.load(ARIAL_30_FONT);
        manager.load(EMPTY_TEXTURE);
        manager.load(START);
        manager.load(START_DOWN);
        manager.load(EXIT);
        manager.load(EXIT_DOWN);
        //Ellenfelek
        manager.load(BADVIRUS_TEXTURE);
        manager.load(GOODVIRUS_TEXTURE);
        manager.load(VIRUSTAIL_ATLAS);
        //Chip fejlesztések
        manager.load(CHIPSPEED_TEXTURE);
        manager.load(CHIPSHIELD_TEXTURE);
        manager.load(CHIPWEAPON_TEXTURE);
        //buborék
        manager.load(BUBBLE_ATLAS);

        manager.load(ROBOT_ATLAS);
        manager.load(BACKGROUND_TEXTURE);
        manager.load(EXPLOSION_ATLAS);
        manager.load(ARROW_TEXTURE);
        manager.load(SPLASH_SOUND);
        manager.load(GAMEOVER_TEXTURE);
        manager.load(WIN_SOUND);
        manager.load(LOST_SOUND);
    }

    public static void afterLoaded() {

    }
    public static void unload() {
        manager.dispose();
    }
}
