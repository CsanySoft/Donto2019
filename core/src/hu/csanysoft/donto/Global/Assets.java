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

    public static final AssetDescriptor<TextureAtlas> MOSQUITO_TEXTURE
            = new AssetDescriptor<TextureAtlas>("actors/mosquito.atlas", TextureAtlas.class);

    public static final AssetDescriptor<TextureAtlas> BACKGROUND_TEXTURE
            = new AssetDescriptor<TextureAtlas>("bacground.png", TextureAtlas.class);


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
        manager.load(MOSQUITO_TEXTURE);
        manager.load(BACKGROUND_TEXTURE);
    }

    public static void afterLoaded() {

    }
    public static void unload() {
        manager.dispose();
    }
}
