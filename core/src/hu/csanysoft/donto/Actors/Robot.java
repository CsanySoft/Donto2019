package hu.csanysoft.donto.Actors;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class Robot extends MultiSpriteActor {

    AnimatedOffsetSprite body;
    AnimatedOffsetSprite trail;
    OffsetSprite shieldSprite;

    public final int baseSpeed = 2;
    public static int speedUpgrade = 0;
    public static boolean hasWeaponUpgrade = false;
    public static boolean hasShield = false;

    public static float shieldTimeLeft = 30;

    float lastX = 0, lastY = 0;

    public Robot(float width, float height) {
        super(width, height);
        body = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROBOT_ATLAS), 0, 0, 50, 75);
        addSprite(body);
        trail = new AnimatedOffsetSprite(Assets.manager.get(Assets.EXPLOSION_ATLAS), 10, -60, 30, 80);
        addSprite(trail);
        shieldSprite = new OffsetSprite(Assets.manager.get(Assets.EXIT), -25,-20,100,100);
        addSprite(shieldSprite);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(lastX != getX() || lastY != getY()){
            trail.setVisible(true);
            body.start();
        }
        else{
            trail.setVisible(false);
            body.stop();
        }
        lastX = getX();
        lastY = getY();

        if(hasShield){
            shieldSprite.setVisible(true);
            shieldTimeLeft -= delta;
            if(shieldTimeLeft < 0){
                hasShield = false;
                shieldTimeLeft = 30;
            }
        }else shieldSprite.setVisible(false);
    }
}
