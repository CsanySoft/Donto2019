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

    public ControlStage(Donto game, GameStage gs) {
        super(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        gameStage = gs;
    }

    @Override
    public void init() {
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
    }
}
