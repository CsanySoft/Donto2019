package hu.csanysoft.donto.Game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.donto.MyBaseClasses.UI.MyLabel;

public class ControlStage extends MyStage {

    GameStage gameStage;
    OneSpriteStaticActor left, right, forward;
    MyLabel speedUpgrades;
    MyLabel shieldTime;
    MyLabel goodViruses;
    MyLabel badViruses;
    OneSpriteStaticActor gameover;
    OneSpriteStaticActor won;

    public ControlStage(Donto game, GameStage gs) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        gameStage = gs;
    }

    @Override
    public void init() {
        gameover = new OneSpriteStaticActor(Assets.manager.get(Assets.GAMEOVER_TEXTURE));
        gameover.magnify(.5f);
        gameover.setPositionCenterOfActorToCenterOfViewport();
        gameover.moveBy(8,Globals.WORLD_HEIGHT/2);
        gameover.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(event.getTarget().isVisible())
                    game.setScreenBackByStackPop();
                return super.touchDown(event, x, y, pointer, button);
            }
        });
        addActor(gameover);
        won = new OneSpriteStaticActor(Assets.manager.get(Assets.WON_TEXTURE));
        won.magnify(.5f);
        won.setPositionCenterOfActorToCenterOfViewport();
        won.moveBy(8,Globals.WORLD_HEIGHT/2);
        addActor(won);
        addActor(left = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(100, 100);
                setPosition(10,10);
                rotateBy(180);
                addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.left = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.left = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });
        addActor(right = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(100, 100);
                setPosition(120,10);
                addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.right = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.right = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });
        addActor(forward = new OneSpriteStaticActor(Assets.manager.get(Assets.ARROW_TEXTURE)){
            @Override
            public void init() {
                super.init();
                setSize(100, 100);
                setPosition(Globals.WORLD_WIDTH-getWidth()-10,10);
                rotateBy(90);
                addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.forward = true;
                        return super.touchDown(event, x, y, pointer, button);
                    }

                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        gameStage.forward = false;
                        super.touchUp(event, x, y, pointer, button);
                    }
                });
            }
        });
        addActor(speedUpgrades = new MyLabel("speed upgrades: 0", Donto.getColorLabelStyle(Color.WHITE)){
            @Override
            public void init() {
                super.init();
                setPosition(10, Globals.WORLD_HEIGHT-getHeight()-10);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                setText("speed upgrades: "+gameStage.robot.speedUpgrade);
            }
        });
        addActor(shieldTime = new MyLabel("", Donto.getColorLabelStyle(Color.WHITE)){
            @Override
            public void init() {
                super.init();
                setPosition(10, Globals.WORLD_HEIGHT-getHeight()-60);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                if(gameStage.robot.hasShield){
                    setText("shield time left: "+((int)(gameStage.robot.shieldTimeLeft*10) / 10.0));
                }else setText("");
            }
        });
        addActor(goodViruses = new MyLabel("", Donto.getColorLabelStyle(Color.WHITE)){
            @Override
            public void init() {
                super.init();
                setPosition(10, Globals.WORLD_HEIGHT-getHeight()-110);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                    setText("good viruses left: "+gameStage.goodVirusCount);
            }
        });
        addActor(badViruses = new MyLabel("", Donto.getColorLabelStyle(Color.WHITE)){
            @Override
            public void init() {
                super.init();
                setPosition(10, Globals.WORLD_HEIGHT-getHeight()-160);
            }

            @Override
            public void act(float delta) {
                super.act(delta);
                    setText("bad viruses left: "+gameStage.badVirusCount);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        gameover.setVisible(!gameStage.robot.isVisible());
        won.setVisible(gameStage.won);
        if(!gameStage.robot.isVisible()) {
            MyLabel halalOka = new MyLabel(gameStage.halalOka, Donto.getColorLabelStyle(Color.WHITE));
            halalOka.setPosition(Globals.WORLD_WIDTH / 2 - halalOka.getWidth() / 2, gameover.getY() - 50);
            addActor(halalOka);
        }

    }
}
