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

    public static float shieldTimeLeft = 0;

    float lastX = 0, lastY = 0;

    public Robot(float width, float height) {
        super(width, height);
        body = new AnimatedOffsetSprite(Assets.manager.get(Assets.ROBOT_ATLAS), 0, 0, 50, 75);
        addSprite(body);
        trail = new AnimatedOffsetSprite(Assets.manager.get(Assets.BUBBLE_ATLAS), 17, -20, 15, 25);
        trail.setFps(90);
        addSprite(trail);
        shieldSprite = new OffsetSprite(Assets.manager.get(Assets.EXIT), -25,-20,100,100);
        addSprite(shieldSprite);
        addBaseCollisionRectangleShape();
        if(hasWeaponUpgrade) addSprite(new OffsetSprite(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE), 10, 20, 30,30));
    }

    public void addUpgrade(int type) {
        switch (type) {
            case 0: {
                hasWeaponUpgrade = true;
                addSprite(new OffsetSprite(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE), 10, 20, 30,30));
                break;
            }
            case 1: {
                speedUpgrade++;
                break;
            }
            case 2: {
                hasShield = true;
                shieldTimeLeft+=10;
                break;
            }
        }
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
            }
        }else shieldSprite.setVisible(false);
    }
}
