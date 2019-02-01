package hu.csanysoft.donto.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.donto.Actors.GoodVirus;
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


public class GameStage extends MyStage {

    public boolean left = false, right = false, forward = false;
    Robot robot;
    OneSpriteStaticActor background;
    public static int level = 1;
    public int badVirusCount = 0;
    public float upgradeTimer = 0;


    public GameStage(Donto game) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        addActor(new BadVirus());
    }


    @Override
    public void init() {
        //PLAYER HOZZÁADÁSA
        robot = new Robot(50,50);
        addActor(robot);
        robot.setPositionCenterOfActorToCenterOfViewport();
        robot.setZIndex(10);
        //HÁTTÉR
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        background.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
        addActor(background);
        background.setZIndex(0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        upgradeTimer+=delta;

        //KARAKTER MOZGÁSA
        if(forward || Gdx.input.isKeyPressed(Input.Keys.UP)){
            double rotation = Math.toRadians(robot.getRotation()+90);
            robot.moveBy((robot.baseSpeed+robot.speedUpgrade)*(float)Math.cos(rotation), (robot.baseSpeed+robot.speedUpgrade)*(float)Math.sin(rotation));
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
            addActor(new Upgrade(Globals.random.nextInt(4) - 1));
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
