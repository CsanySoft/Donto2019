package hu.csanysoft.donto.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.donto.Actors.GoodVirus;
import hu.csanysoft.donto.Actors.Pill;
import hu.csanysoft.donto.Actors.Popup;
import hu.csanysoft.donto.Actors.Robot;
import hu.csanysoft.donto.Actors.BadVirus;
import hu.csanysoft.donto.Actors.Upgrade;
import hu.csanysoft.donto.Actors.Virus;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;

import static hu.csanysoft.donto.Global.Globals.random;


public class GameStage extends MyStage {

    public boolean left = false, right = false, forward = false;
    Robot robot;
    OneSpriteStaticActor background;
    public static int level = 1;
    public int badVirusCount = 0;
    public float upgradeTimer = 0;


    public GameStage(Donto game) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        for (int i = 0; i < level; i++) {
            addActor(new BadVirus());
            addActor(new BadVirus());
            addActor(new GoodVirus());
        }
    }


    @Override
    public void init() {
        //PLAYER HOZZÁADÁSA
        robot = new Robot(50,50);
        addActor(robot);
        robot.setPosition(Globals.WORLD_WIDTH/2, Globals.WORLD_HEIGHT/2);
        robot.setZIndex(10);
        //HÁTTÉR
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        background.setSize(Globals.WORLD_WIDTH+1000, Globals.WORLD_HEIGHT+990);
        background.moveBy(-500,-500);
        addActor(background);
        background.setZIndex(0);
        int pillCount = (int)Math.floor(level/5.0);
        for(int i = 0; i < pillCount; i++){
            addActor(new Pill(random(100,Globals.WORLD_WIDTH-100), random(100, Globals.WORLD_HEIGHT-100), random(0,365)));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        upgradeTimer+=delta;
        //KARAKTER MOZGÁSA
        if(forward || Gdx.input.isKeyPressed(Input.Keys.UP)){
            double rotation = Math.toRadians(robot.getRotation()+90);
            robot.moveBy((robot.baseSpeed+robot.speedUpgrade)*(float)Math.cos(rotation), (robot.baseSpeed+robot.speedUpgrade)*(float)Math.sin(rotation));
            if(robot.getX() < 0) robot.setX(0);
            if(robot.getX()+robot.getWidth() > Globals.WORLD_WIDTH) robot.setX(Globals.WORLD_WIDTH-robot.getWidth());
            if(robot.getY() < 0) robot.setY(0);
            if(robot.getY()+robot.getHeight() > Globals.WORLD_HEIGHT) robot.setY(Globals.WORLD_HEIGHT-robot.getHeight());
        }
        if(left  || Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            robot.rotateBy(robot.baseSpeed+robot.speedUpgrade/2);
        }
        if(right  || Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            robot.rotateBy(-(robot.baseSpeed+robot.speedUpgrade/2));
        }
        //KARAKTER MOZGÁSA VÉGE

        //ÜTKÖZÉSVIZSGÁLAT
        for (Actor actor : getActors()) {
            if(actor instanceof Virus) {
                Virus overlappedVirus = (Virus)actor;

                //VÍRUS  VÍRUSSAL
                for(Actor virus : getActors().toArray()) {
                    if(actor instanceof GoodVirus && virus instanceof BadVirus) {
                        if(((GoodVirus) actor).overlaps((BadVirus) virus)) actor.remove();
                    } else if (actor instanceof BadVirus && virus instanceof GoodVirus) {
                        if(((BadVirus) actor).overlaps((GoodVirus) virus)) virus.remove();
                    }
                    if(virus instanceof Pill){
                        if(((Virus) actor).overlaps((MyActor) virus)){
                            actor.remove();
                        }
                    }
                }
                //VÍRUS A VÍRUSSAL VÉGE

                //ROBOT A VÍRUSSAL
                if(robot.overlaps(overlappedVirus)) {
                    if(overlappedVirus instanceof GoodVirus && !robot.hasShield) die();
                    else if(overlappedVirus instanceof BadVirus) {
                        if(((BadVirus) overlappedVirus).needsABetterWeaponToDestroy && !robot.hasWeaponUpgrade) {
                            robot.remove();
                        } else {
                            overlappedVirus.die();
                            long f = Assets.manager.get(Assets.SPLASH_SOUND).play();
                            Assets.manager.get(Assets.SPLASH_SOUND).setVolume(f, 40);
                            overlappedVirus.remove();
                        }

                    }
                }
                //ROBOT A VÍRUSSAL VÉGE
            }

            //ROBOT AZ UPGRADEVAL
            else if (actor instanceof Upgrade) {
                Upgrade overlappedUpgrade = (Upgrade)actor;
                if(robot.overlaps(overlappedUpgrade)) {
                    robot.addUpgrade(overlappedUpgrade.getType());
                    String message = "";
                    switch(overlappedUpgrade.getType()){
                        case Upgrade.SHIELD: message = "Shield module installed"; break;
                        case Upgrade.SPEED: message = "Speed increased"; break;
                        case Upgrade.WEAPON: message = "Weapon upgrade installed"; break;
                    }
                    addActor(new Popup(message, (int)robot.getX(), (int)robot.getY(), Color.YELLOW));
                    overlappedUpgrade.remove();
                }
            }
            //ROBOT AZ UPGRADEVAL VÉGE

            //ROBOT A PIRULÁVAL
            else if (actor instanceof Pill){
                if(robot.overlaps((MyActor) actor)) {
                    actor.remove();
                }
            }
        }
        //ÜTKÖZÉSVIZSGÁLAT VÉGE

        //BADVIRUS SZÁMOLÁS
        badVirusCount = 0; //BEÁLLÍTANI NULLÁRA, FORCIKLUSBAN SZÁMOL
        for(Actor actor:getActors()) {
            if(actor instanceof BadVirus) badVirusCount++;
        }
        if(badVirusCount == 0) {
            System.out.println("Következő szint");
            level++;
            game.setScreen(new GameScreen(game));
            this.dispose();
        }
        //BADVIRUS SZÁMOLÁS VÉGE

        setCameraZoomXY(robot.getX()+robot.getWidth()/2, robot.getY()+robot.getHeight()/2, 0.6f); //KAMERAMOZGÁS

        if(upgradeTimer >= 10) {
            upgradeTimer = 0;
            if(level < 5 && !robot.hasWeaponUpgrade) addActor(new Upgrade(Globals.random.nextInt(2) +1));
            else addActor(new Upgrade(random.nextInt(3)));
        }
    }

    public void die() {
        robot.setVisible(false);
    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode == Input.Keys.S){
            robot.speedUpgrade++;
        }
        if(keyCode == Input.Keys.D){
            robot.hasShield = true;
            robot.shieldTimeLeft += 10;
        }


        return super.keyDown(keyCode);
    }

    @Override
    public boolean keyUp(int keyCode) {

        return super.keyUp(keyCode);
    }

    @Override
    public void dispose() {
        super.dispose();
    }



}
