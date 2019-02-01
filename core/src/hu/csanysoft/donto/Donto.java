package hu.csanysoft.donto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Loading.LoadingScreen;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyScreen;

public class Donto extends Game {

	public static final int eletErtelme = 42;

	public final Stack<Class> backButtonStack = new Stack();

	@Override
	public void create () {
		Assets.prepare();
		setScreen(new LoadingScreen(this));
	}

	@Override
	public void resume() {
		super.resume();
		Assets.manager.update();
	}

	@Override
	public void dispose () {
		super.dispose();
		Assets.unload();
	}

	@Override
	public void pause() {
		super.pause();
	}


	public Label.LabelStyle getLabelStyle() {
		Label.LabelStyle style;
		style = new Label.LabelStyle();
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.fontColor = Color.YELLOW;
		Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
		p.setColor(0.4f, 0.2f, 0.8f, 0.5f);
		p.fill();
		return style;
	}

	public static Label.LabelStyle getColorLabelStyle(Color c) {
		Label.LabelStyle style;
		style = new Label.LabelStyle();
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.fontColor = c;
		Pixmap p = new Pixmap(1, 1, Pixmap.Format.RGB888);
		p.setColor(0.4f, 0.2f, 0.8f, 0.5f);
		p.fill();
		return style;
	}


	public TextField.TextFieldStyle getTextFieldStyle_White() {
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		//style.background.setLeftWidth(style.background.getLeftWidth()+20);
		//style.background.setRightWidth(style.background.getRightWidth()+20);
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.font.getData().setScale(1.2f);

		style.fontColor = Color.WHITE;
		return style;
	}
	public TextField.TextFieldStyle getTextFieldStyle_Red() {
		TextField.TextFieldStyle style = new TextField.TextFieldStyle();
		//style.background.setLeftWidth(style.background.getLeftWidth()+20);
		//style.background.setRightWidth(style.background.getRightWidth()+20);
		style.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		style.font.getData().setScale(1.2f);
		style.fontColor = Color.RED;
		return style;
	}

	public TextButton.TextButtonStyle btnStart() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.START)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.START_DOWN)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.START_DOWN)));
		return textButtonStyle;
	}

	 public TextButton.TextButtonStyle btnExit() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.EXIT)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.EXIT)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.EXIT_DOWN)));
		return textButtonStyle;
	}

	public TextButton.TextButtonStyle btnTutorial() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TUTORIAL)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TUTORIAL)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.TUTORIAL_DOWN)));
		return textButtonStyle;
	}

	public TextButton.TextButtonStyle btnBack() {
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = Assets.manager.get(Assets.ARIAL_30_FONT);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BACK)));
		textButtonStyle.over = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BACK)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(Assets.manager.get(Assets.BACK_DOWN)));
		return textButtonStyle;
	}

	public void setScreenBackByStackPop() {
		if (backButtonStack.size() > 1) {
			try {
				setScreen((MyScreen) backButtonStack.pop().getConstructor(Donto.class).newInstance(this), false);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			Gdx.app.exit();
		}
	}

	public void setScreen(Screen screen) {
		setScreen(screen, true);
	}

	public void setScreen(Screen screen, boolean pushToStack) {
		Screen prevScreen = getScreen();
		if (prevScreen != null) {
			if (pushToStack) {
				backButtonStack.push(prevScreen.getClass());
			}
			prevScreen.dispose();
		}
		super.setScreen(screen);
	}
}
