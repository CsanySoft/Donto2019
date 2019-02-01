package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;

public class BadVirus extends Virus {

    public boolean needsABetterWeaponToDestroy ;

    public BadVirus() {
        super(Assets.manager.get(Assets.EXPLOSION_ATLAS));
        needsABetterWeaponToDestroy = Globals.random(0, 20) < 4;
    }
}
