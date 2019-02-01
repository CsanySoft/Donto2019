package hu.csanysoft.donto.Actors;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Upgrade extends OneSpriteStaticActor {

    public int type = 3;
    public static int WEAPON = 0, SPEED = 1, SHIELD = 2;

    public Upgrade(int type) {
        super(Assets.manager.get(Assets.START));
        switch (type) {
            case 0: {
                setTexture(Assets.manager.get(Assets.START));
                break;
            }
            case 1: {
                setTexture(Assets.manager.get(Assets.EXIT));
                break;
            }
            case 2 : {
                setTexture(Assets.manager.get(Assets.START_DOWN));
                break;
            }
        }
        addBaseCollisionRectangleShape();
        setPosition(Globals.random(100, Globals.WORLD_WIDTH - 100), Globals.WORLD_HEIGHT + 100);
        this.type = type;
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
        moveBy(0, -1);
    }
}
