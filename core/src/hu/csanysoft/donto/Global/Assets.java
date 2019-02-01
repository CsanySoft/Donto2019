package hu.csanysoft.donto.Global;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
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
        fontParameter.fontFileName = "Squidgy Sweets.otf";
        fontParameter.fontParameters.size = 30;
        fontParameter.fontParameters.characters = CHARS;
        fontParameter.fontParameters.color = Color.WHITE;
    }

    public static final AssetDescriptor<BitmapFont> ARIAL_30_FONT
            = new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter);

    public static final AssetDescriptor<Texture> EMPTY_TEXTURE
            = new AssetDescriptor<Texture>("zolipls.png", Texture.class);

    //Gombok
    public static final AssetDescriptor<Texture> START
            = new AssetDescriptor<Texture>("ui/buttons/start.png", Texture.class);

    public static final AssetDescriptor<Texture> START_DOWN
            = new AssetDescriptor<Texture>("ui/buttons/start_down.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT
            = new AssetDescriptor<Texture>("ui/buttons/exit.png", Texture.class);

    public static final AssetDescriptor<Texture> EXIT_DOWN
            = new AssetDescriptor<Texture>("ui/buttons/exit_down.png", Texture.class);

    public static final AssetDescriptor<Texture> TUTORIAL
            = new AssetDescriptor<Texture>("ui/buttons/tutorial.png", Texture.class);

    public static final AssetDescriptor<Texture> TUTORIAL_DOWN
            = new AssetDescriptor<Texture>("ui/buttons/tutorial_down.png", Texture.class);

    public static final AssetDescriptor<Texture> BACK
            = new AssetDescriptor<Texture>("ui/buttons/back.png", Texture.class);

    public static final AssetDescriptor<Texture> BACK_DOWN
            = new AssetDescriptor<Texture>("ui/buttons/back_down.png", Texture.class);




    public static final AssetDescriptor<Texture> ANTIPILL_TEXTURE
            = new AssetDescriptor<Texture>("antipill.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> WHITEBLOOD_ATLAS//fehérvér sejt
            = new AssetDescriptor<TextureAtlas>("animations/whiteBloodCellsani.atlas", TextureAtlas.class);

    //Ellenfelek

    public static final AssetDescriptor<Texture> BADVIRUS_TEXTURE
            = new AssetDescriptor<Texture>("animations/virus/red.png", Texture.class);

    public static final AssetDescriptor<Texture> GOODVIRUS_TEXTURE
            = new AssetDescriptor<Texture>("animations/virus/green.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> VIRUSTAIL_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/virus/tailani.atlas", TextureAtlas.class);

    //Chip fejlesztések, páncél

    public static final AssetDescriptor<Texture> CHIPSPEED_TEXTURE
            = new AssetDescriptor<Texture>("chips/speed.png", Texture.class);

    public static final AssetDescriptor<Texture> CHIPSHIELD_TEXTURE
            = new AssetDescriptor<Texture>("chips/shield.png", Texture.class);

    public static final AssetDescriptor<Texture> CHIPWEAPON_TEXTURE
            = new AssetDescriptor<Texture>("chips/weapon.png", Texture.class);

    public static final AssetDescriptor<TextureAtlas> BUBBLE_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/bubbles/bubbleani.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> SHIELD_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/shield/shield.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> ROBOT_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/palyer/android.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> SPLASH_ATLAS
            = new AssetDescriptor<TextureAtlas>("animations/greenexplosion.atlas", TextureAtlas.class);

    public static final AssetDescriptor<Texture> BACKGROUND_TEXTURE
            = new AssetDescriptor<Texture>("background.png", Texture.class);

    public static final AssetDescriptor<Texture> BACKGROUNDWATER_TEXTURE
            = new AssetDescriptor<Texture>("water_bg.png", Texture.class);

    public static final AssetDescriptor<Texture> ARROW_TEXTURE
            = new AssetDescriptor<Texture>("ui/arrow.png", Texture.class);

    public static final AssetDescriptor<Texture> ARROWDOWN_TEXTURE
            = new AssetDescriptor<Texture>("ui/arrow_down.png", Texture.class);

    public static final AssetDescriptor<Texture> NAVIARROW_TEXTURE
            = new AssetDescriptor<Texture>("ui/navigation_arrow.png", Texture.class);

    public static final AssetDescriptor<Texture> GAMEOVER_TEXTURE
            = new AssetDescriptor<Texture>("gameover.png", Texture.class);

    public static final AssetDescriptor<Texture> KEK
            = new AssetDescriptor<Texture>("kek.png", Texture.class);

    public static final AssetDescriptor<Texture> WHITEBLOODCELL_TEXTURE
            = new AssetDescriptor<Texture>("animations/whiteBloodCells.png", Texture.class);

    public static final AssetDescriptor<Texture> ANDROID_TEXTURE
            = new AssetDescriptor<Texture>("actors/android.png", Texture.class);

    public static final AssetDescriptor<Texture> WON_TEXTURE
            = new AssetDescriptor<Texture>("won.png", Texture.class);

    public static final AssetDescriptor<Sound> SPLASH_SOUND
            = new AssetDescriptor<Sound>("sound/splash.mp3", Sound.class);

    public static final AssetDescriptor<Sound> WIN_SOUND
            = new AssetDescriptor<Sound>("sound/win.mp3", Sound.class);

    public static final AssetDescriptor<Sound> LOST_SOUND
            = new AssetDescriptor<Sound>("sound/lose.mp3", Sound.class);

    public static final AssetDescriptor<Music> MUS
            = new AssetDescriptor<Music>("sound/mus.mp3", Music.class);

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
        //Gombok
        manager.load(START);
        manager.load(START_DOWN);
        manager.load(EXIT);
        manager.load(EXIT_DOWN);
        manager.load(MUS);
        manager.load(TUTORIAL);
        manager.load(TUTORIAL_DOWN);
        manager.load(BACK);
        manager.load(BACK_DOWN);

        manager.load(ANTIPILL_TEXTURE);
        manager.load(WHITEBLOOD_ATLAS);
        manager.load(KEK);
        manager.load(WHITEBLOODCELL_TEXTURE);
        manager.load(ANDROID_TEXTURE);
        //Ellenfelek
        manager.load(BADVIRUS_TEXTURE);
        manager.load(GOODVIRUS_TEXTURE);
        manager.load(VIRUSTAIL_ATLAS);
        //Chip fejlesztések
        manager.load(CHIPSPEED_TEXTURE);
        manager.load(CHIPSHIELD_TEXTURE);
        manager.load(CHIPWEAPON_TEXTURE);
        manager.load(BUBBLE_ATLAS);
        manager.load(SHIELD_ATLAS);

        manager.load(ROBOT_ATLAS);
        manager.load(BACKGROUND_TEXTURE);
        manager.load(BACKGROUNDWATER_TEXTURE);

        manager.load(ARROW_TEXTURE);
        manager.load(ARROWDOWN_TEXTURE);
        manager.load(NAVIARROW_TEXTURE);
        manager.load(SPLASH_SOUND);
        manager.load(GAMEOVER_TEXTURE);
        manager.load(WIN_SOUND);
        manager.load(LOST_SOUND);
        manager.load(SPLASH_ATLAS);
        manager.load(WON_TEXTURE);
    }

    public static void afterLoaded() {

    }
    public static void unload() {
        manager.dispose();
    }
}
