package hu.csanysoft.donto.Actors;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.AnimatedOffsetSprite;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MultiSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyRectangle;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OffsetSprite;

public class Robot extends MultiSpriteActor {

    AnimatedOffsetSprite body;
    AnimatedOffsetSprite trail, trail2, trail3;
    AnimatedOffsetSprite shieldSprite;

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
        trail2 = new AnimatedOffsetSprite(Assets.manager.get(Assets.BUBBLE_ATLAS), 7, -30, 15, 25);
        trail3 = new AnimatedOffsetSprite(Assets.manager.get(Assets.BUBBLE_ATLAS), 27, -35, 15, 25);
        trail.setFps(50 + baseSpeed+speedUpgrade);
        trail2.setFps(35 + baseSpeed+speedUpgrade);
        trail3.setFps(65 + baseSpeed+speedUpgrade);
        addSprite(trail);
        addSprite(trail2);
        addSprite(trail3);
        shieldSprite = new AnimatedOffsetSprite(Assets.manager.get(Assets.SHIELD_ATLAS),-25,-20,100,100);
        addSprite(shieldSprite);
        shieldSprite.setFps(15);
        addBaseCollisionRectangleShape();
        addCollisionShape("fej", new MyRectangle(50,25, 0,50, 25,25));
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

        trail.setFps(50 + baseSpeed+speedUpgrade*30);
        trail2.setFps(35 + baseSpeed+speedUpgrade*30);
        trail3.setFps(65 + baseSpeed+speedUpgrade*30);

        if(lastX != getX() || lastY != getY()){
            trail.setVisible(true);
            trail2.setVisible(true);
            trail3.setVisible(true);
            body.start();
        }
        else{
            trail.setVisible(false);
            trail2.setVisible(false);
            trail3.setVisible(false);
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
