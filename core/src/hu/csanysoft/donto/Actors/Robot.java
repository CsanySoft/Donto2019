package hu.csanysoft.donto.Actors;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class Robot extends MultiSpriteActor {

    OffsetSprite body;
    AnimatedOffsetSprite propeller, trail;

    public boolean hasSpeedUpgrade = false;
    public boolean hasWeaponUpgrade = false;
    public boolean hasShield = false;

    float shieldTimeLeft = 30;

    float lastX = 0, lastY = 0;

    public Robot(float width, float height) {
        super(width, height);
        body = new OffsetSprite(Assets.manager.get(Assets.EXIT), 0, 0, 50, 50);
        addSprite(body);
        propeller = new AnimatedOffsetSprite(Assets.manager.get(Assets.EXPLOSION_ATLAS), 5, -40, 40, 40);
        addSprite(propeller);
        trail = new AnimatedOffsetSprite(Assets.manager.get(Assets.EXPLOSION_ATLAS), 10, -80, 30, 80);
        addSprite(trail);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(lastX != getX() || lastY != getY()){
            trail.setVisible(true);
            propeller.start();
        }
        else{
            trail.setVisible(false);
            propeller.stop();
        }
        lastX = getX();
        lastY = getY();

        if(hasShield){
            shieldTimeLeft -= delta;
            if(shieldTimeLeft < 0){
                hasShield = false;
                shieldTimeLeft = 30;
            }
        }
    }
}
