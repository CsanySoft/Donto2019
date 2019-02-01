package hu.csanysoft.donto.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import hu.csanysoft.donto.Actors.GoodVirus;
import hu.csanysoft.donto.Actors.Robot;
import hu.csanysoft.donto.Actors.BadVirus;
import hu.csanysoft.donto.Actors.Virus;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;

import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;


public class GameStage extends MyStage {

    public boolean left = false, right = false, forward = false;
    Robot robot;
    OneSpriteStaticActor background;
    public static int level = 1;
    public int badVirusCount = 0;


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
        background = new OneSpriteStaticActor(Assets.manager.get(Assets.BACKGROUND_TEXTURE));
        //HÁTTÉR
        background.setSize(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT);
        background.setZIndex(0);
        //addActor(background);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
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
                if(robot.overlaps(overlappedVirus)) {
                    if(overlappedVirus instanceof GoodVirus) die();
                    else if(overlappedVirus instanceof BadVirus) {
                        overlappedVirus.die();
                        overlappedVirus.remove();
                    }
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
