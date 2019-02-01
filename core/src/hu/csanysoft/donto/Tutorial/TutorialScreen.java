package hu.csanysoft.donto.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;

public class TutorialScreen extends MyScreen {
    TutorialStage stage;

    public TutorialScreen(Donto game) {
        super(game);
    }

    @Override
    public void init() {
        stage = new TutorialStage(new ExtendViewport(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT, new OrthographicCamera(Globals.WORLD_WIDTH, Globals.WORLD_HEIGHT)), new SpriteBatch(), game);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.resize(width, height);
    }

    @Override
    public void dispose() {
        stage.dispose();
        super.dispose();
    }
}
