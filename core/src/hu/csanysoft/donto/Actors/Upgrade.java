package hu.csanysoft.donto.Actors;

import com.badlogic.gdx.Game;

import hu.csanysoft.donto.Game.GameStage;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Upgrade extends OneSpriteStaticActor {

    public int type;
    public static final int WEAPON = 0, SPEED = 1, SHIELD = 2;

    public Upgrade(int type) {
        super(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE));
        setSize(64,64);
        this.type = type;
        switch (type) {
            case 0: {
                setTexture(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE));
                break;
            }
            case 1: {
                setTexture(Assets.manager.get(Assets.CHIPSPEED_TEXTURE));
                break;
            }
            case 2 : {
                setTexture(Assets.manager.get(Assets.CHIPSHIELD_TEXTURE));
                break;
            }
        }
        addBaseCollisionRectangleShape();
        setPosition(Globals.random(100, GameStage.WORLD_BOUND_X - 100), GameStage.WORLD_BOUND_Y + 100);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(0, -0.5f);
        if(elapsedTime>20) remove();
    }
}
