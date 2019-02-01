package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class BadVirus extends Virus {

    public boolean needsABetterWeaponToDestroy ;

    OffsetSprite body;

    public BadVirus() {
        super();
        needsABetterWeaponToDestroy = Globals.random(0, 20) < 4;
        if(needsABetterWeaponToDestroy) addSprite(new OffsetSprite(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE), 15, 20, 30,30));
        body = new OffsetSprite(Assets.manager.get(Assets.BADVIRUS_TEXTURE), 0,0,64,64);
        addSprite(body);
        setPosition(Globals.random.nextInt(Globals.WORLD_WIDTH / 2 - (int)getWidth()) + Globals.random.nextFloat(), Globals.random.nextInt(Globals.WORLD_HEIGHT - (int)getHeight()) + Globals.random.nextFloat());
    }
}
