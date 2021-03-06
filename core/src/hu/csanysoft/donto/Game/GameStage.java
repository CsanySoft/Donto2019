package hu.csanysoft.donto.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import hu.csanysoft.donto.Actors.*;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.donto.MyBaseClasses.UI.MyLabel;

import static hu.csanysoft.donto.Global.Globals.random;


public class GameStage extends MyStage {

    public boolean left = false, right = false, forward = false, won = false;
    Robot robot;
    OneSpriteStaticActor background, closestArrow;
    MovingBackground waterBackground;
    public static int level = 1;
    public int badVirusCount, goodVirusCount;
    public float upgradeTimer = 0;
    public String halalOka;


    public static int WORLD_BOUND_X = 1920, WORLD_BOUND_Y = 1080;


    public GameStage(Donto game, boolean newGame) {
        super(new StretchViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        for (int i = 0; i < level; i++) {
            addActor(new BadVirus());
            //addActor(new BadVirus());
            addActor(new GoodVirus());
            badVirusCount++; goodVirusCount++;
        }
        if(newGame){
            robot.hasShield = false;
            robot.shieldTimeLeft = 0;
            robot.hasWeaponUpgrade = false;
            robot.speedUpgrade = 0;
            level = 1;
        }

    }




    @Override
    public void init() {
        Gdx.input.setCatchBackKey(true);
        //PLAYER HOZZÁADÁSA
        Image kek = new Image(Assets.manager.get(Assets.KEK));
        kek.setSize(3000, 2000);
        kek.setPosition(0 - (1920 - Globals.WORLD_WIDTH) / 1.5f, -500);
        addActor(kek, 0);
        robot = new Robot(50,50);
        addActor(robot);
        robot.setPosition(WORLD_BOUND_X/2, WORLD_BOUND_Y/2);
        robot.setZIndex(10);
        closestArrow = new OneSpriteStaticActor(Assets.manager.get(Assets.NAVIARROW_TEXTURE));
        closestArrow.setSize(120, 120);
        closestArrow.setOrigin(60,60);
        addActor(closestArrow);
        //HÁTTÉR
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        background.setSize(WORLD_BOUND_X, WORLD_BOUND_Y);
        background.setPositionCenterOfActorToCenterOfViewport();
        addActor(background);
        background.setZIndex(0);
        waterBackground = new MovingBackground(Assets.manager.get(Assets.BACKGROUNDWATER_TEXTURE), kek.getWidth()+200, kek.getHeight()+200, kek.getX(),kek.getY(), 200);
        addActor(waterBackground);
        waterBackground.setZIndex(1);
        //PIRULÁK SPAWNOLÁSA
        int pillCount = (int)Math.floor(level/2.0);
        for(int i = 0; i < pillCount; i++){
            addActor(new Pill(random(100,WORLD_BOUND_X-100), random(100, WORLD_BOUND_Y-100), random(0,365)));
        }
        //FEHÉRVÉRSEJTEK SPAWNOLÁSA
        int whiteCellCount = (int)Math.floor(level/3.0);
        for (int i = 0; i < whiteCellCount; i++) {
            WhiteBloodCell cell = new WhiteBloodCell(random(150, WORLD_BOUND_X-150), random(150, WORLD_BOUND_Y-150), robot);
            addActor(cell);
            while(cell.getX() > WORLD_BOUND_X/2-200 && cell.getX() < WORLD_BOUND_X/2+200) cell.setX(random(150, WORLD_BOUND_X-150));
            while(cell.getY() > WORLD_BOUND_Y/2-200 && cell.getY() < WORLD_BOUND_Y/2+200) cell.setY(random(150, WORLD_BOUND_Y-150));
        }


    }

    @Override
    public void act(float delta) {
        super.act(delta);
        upgradeTimer+=delta;
        //KARAKTER MOZGÁSA
        if(robot.isVisible() & !won) {
            if (forward || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                double rotation = Math.toRadians(robot.getRotation() + 90);
                robot.moveBy((robot.baseSpeed + robot.speedUpgrade) * (float) Math.cos(rotation), (robot.baseSpeed + robot.speedUpgrade) * (float) Math.sin(rotation));
                if (robot.getX() < 0) robot.setX(0);
                if (robot.getX() + robot.getWidth() > WORLD_BOUND_X)
                    robot.setX(WORLD_BOUND_X - robot.getWidth());
                if (robot.getY() < 0) robot.setY(0);
                if (robot.getY() + robot.getHeight() > WORLD_BOUND_Y)
                    robot.setY(WORLD_BOUND_Y - robot.getHeight());
            }
            if (left || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                robot.rotateBy(robot.baseSpeed + robot.speedUpgrade / 2);
            }
            if (right || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                robot.rotateBy(-(robot.baseSpeed + robot.speedUpgrade / 2));
            }

        }
        if(!robot.isVisible() || won)closestArrow.setVisible(false);
        closestArrow.setPosition(robot.getX()-33, robot.getY()-33);
        float min = 1000;
        BadVirus closest = null;
        for(Actor actor : getActors()){
            if(actor instanceof BadVirus){
                float distance = (float)Math.sqrt(Math.pow(Math.abs(robot.getX() - actor.getX()), 2)+Math.pow(Math.abs(robot.getY() - actor.getY()), 2));
                if(distance < min){
                    min = distance;
                    closest = (BadVirus)actor;
                }
            }
        }
        if(closest!= null){
            closestArrow.setRotation((float)Math.toDegrees(Math.atan2((closest.getY()-robot.getY()),(closest.getX()-robot.getX())))-90);
        }



        //KARAKTER MOZGÁSA VÉGE

        //ÜTKÖZÉSVIZSGÁLAT
        for (Actor actor : getActors()) {
            if(actor instanceof Virus) {
                Virus overlappedVirus = (Virus)actor;

                //VÍRUS  VÍRUSSAL
                /*
                for(Actor virus : getActors().toArray()) {
                    if(actor instanceof GoodVirus && virus instanceof BadVirus) {
                        if(((GoodVirus) actor).overlaps((BadVirus) virus)) {
                            spash((MyActor) actor);
                            goodVirusCount--;
                        }
                    } else if (actor instanceof BadVirus && virus instanceof GoodVirus) {
                        if(((BadVirus) actor).overlaps((GoodVirus) virus)) {
                            spash((MyActor) virus);
                            goodVirusCount--;
                        }
                    }
                    if(virus instanceof Pill){
                        if(((Virus) actor).overlaps((MyActor) virus)){
                            spash((MyActor) actor);
                            goodVirusCount--;
                        }
                    }
                } */
                //VÍRUS A VÍRUSSAL VÉGE

                //ROBOT A VÍRUSSAL
                if(robot.overlaps(overlappedVirus) & !won) {
                    if(overlappedVirus instanceof GoodVirus && !robot.hasShield) die("You tried to kill a good virus");
                    else if(overlappedVirus instanceof BadVirus) {
                        if(((BadVirus) overlappedVirus).needsABetterWeaponToDestroy && !robot.hasWeaponUpgrade) {
                            die("You tried to kill a shielded virus without a weapon");
                        } else {
                            overlappedVirus.die();
                            badVirusCount--;
                            spash(overlappedVirus);
                        }

                    }
                }
                //ROBOT A VÍRUSSAL VÉGE
            }

            //ROBOT AZ UPGRADEVAL
            else if (actor instanceof Upgrade) {
                Upgrade overlappedUpgrade = (Upgrade)actor;
                if(robot.overlaps(overlappedUpgrade) & !won) {
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
                if(robot.overlaps((MyActor) actor) & !won) {
                    actor.remove();
                }
            }

            //ROBOT A FEHÉR SEJTTEL
            else if (actor instanceof WhiteBloodCell){
                if(robot.overlaps((MyActor) actor) & !robot.hasShield & !won){
                    die("You got caught by a white blood cell");
                }
            }
        }
        //ÜTKÖZÉSVIZSGÁLAT VÉGE

        //BADVIRUS SZÁMOLÁS
        //badVirusCount = 0; //BEÁLLÍTANI NULLÁRA, FORCIKLUSBAN SZÁMOL
       /* for(Actor actor:getActors()) {
            if(actor instanceof BadVirus) badVirusCount++;
        } */
        if(badVirusCount == 0 & !won) {
            won = true;
            System.out.println("Következő szint");
            Assets.manager.get(Assets.WIN_SOUND).play();
            //todo oide
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    level++;
                    game.setScreen(new GameScreen(game, false));
                }
            }, 2);
        }
        //BADVIRUS SZÁMOLÁS VÉGE


        if(goodVirusCount == 0) die("There are no more good viruses");
        if(robot.isVisible())setCameraZoomXY(robot.getX()+robot.getWidth()/2, robot.getY()+robot.getHeight()/2, 0.6f); //KAMERAMOZGÁS
        else{
            setCameraMoveToXY(WORLD_BOUND_X / 2, WORLD_BOUND_Y / 2 + 1, 1, .1f, 100);

        }

        if(upgradeTimer >= 10) {
            upgradeTimer = 0;
            if(level < 2 && !robot.hasWeaponUpgrade) addActor(new Upgrade(Globals.random.nextInt(2) +1));
            else addActor(new Upgrade(random.nextInt(3)));
        }
    }

    public void die(String s) {
        if(!robot.isVisible())
            return;
        robot.setVisible(false);
        halalOka = s;
        long f = Assets.manager.get(Assets.LOST_SOUND).play();
        Assets.manager.get(Assets.LOST_SOUND).setVolume(f, 50);
        setCameraMoveSpeed(.1f);


    }

    @Override
    public boolean keyDown(int keyCode) {
        if(keyCode == Input.Keys.S){
            robot.addUpgrade(Upgrade.SPEED);
        }
        if(keyCode == Input.Keys.D){
            robot.addUpgrade(Upgrade.SHIELD);
        }
        if(keyCode == Input.Keys.F){
            robot.addUpgrade(Upgrade.WEAPON);
        }
        if(keyCode == Input.Keys.W){
            WhiteBloodCell cell = new WhiteBloodCell(random(150, WORLD_BOUND_X-150), random(150, WORLD_BOUND_Y-150), robot);
            addActor(cell);
            while(cell.getX() > WORLD_BOUND_X/2-200 && cell.getX() < WORLD_BOUND_X/2+200) cell.setX(random(150, WORLD_BOUND_X-150));
            while(cell.getY() > WORLD_BOUND_Y/2-200 && cell.getY() < WORLD_BOUND_Y/2+200) cell.setY(random(150, WORLD_BOUND_Y-150));
        }

        if(keyCode == Input.Keys.BACK || keyCode == Input.Keys.ESCAPE){
            game.setScreenBackByStackPop();
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

    public void spash(MyActor a){
        addActor(new Spash(a));
        a.remove();
    }
}
