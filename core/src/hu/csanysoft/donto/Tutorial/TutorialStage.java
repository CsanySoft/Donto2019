package hu.csanysoft.donto.Tutorial;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanysoft.donto.Actors.MovingBackground;
import hu.csanysoft.donto.Global.Assets;
import hu.csanysoft.donto.Global.Globals;
import hu.csanysoft.donto.Donto;
import hu.csanysoft.donto.MyBaseClasses.MyTextButton;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.MyStage;
import hu.csanysoft.donto.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanysoft.donto.MyBaseClasses.UI.MyButton;
import hu.csanysoft.donto.MyBaseClasses.UI.MyLabel;

public class TutorialStage extends MyStage {

    Image badVirus, goodVirus, robot, pill, whiteBloodCell, upgradeShield, upgradeWeapon, upgradeSpeed;
    MyLabel lblBadVirus, lblGoodVirus, lblRobot, lblPill, lblWhiteBloodCell, lblUpgradeShield, lblUpgradeWeapon, lblUpgradeSpeed;

    public TutorialStage(Viewport viewport, Batch batch, Donto game) {
        super(viewport, batch, game);
        Gdx.input.setCatchBackKey(true);
        setDebugAll(Globals.DEBUG_ALL);
        Gdx.input.setInputProcessor(this);
    }

    public void init() {
    /*    OneSpriteStaticActor background = new OneSpriteStaticActor(Assets.manager.get(Assets.SPACE_TEXTURE)){
            @Override
            public void act(float delta) {
                super.act(delta);
                rotateBy(delta*20);
            }

        };
        addActor(background);
*/
        addActor(new MovingBackground(Assets.manager.get(Assets.BACKGROUNDWATER_TEXTURE),Globals.WORLD_WIDTH + 600, Globals.WORLD_HEIGHT + 600, -500, -500, 100), 5);
        badVirus = new Image(Assets.manager.get(Assets.BADVIRUS_TEXTURE));
        goodVirus = new Image(Assets.manager.get(Assets.GOODVIRUS_TEXTURE));
        robot = new Image(Assets.manager.get(Assets.ANDROID_TEXTURE));
        pill = new Image(Assets.manager.get(Assets.ANTIPILL_TEXTURE));
        whiteBloodCell = new Image(Assets.manager.get(Assets.WHITEBLOODCELL_TEXTURE));
        upgradeShield = new Image(Assets.manager.get(Assets.CHIPSHIELD_TEXTURE));
        upgradeSpeed = new Image(Assets.manager.get(Assets.CHIPSPEED_TEXTURE));
        upgradeWeapon = new Image(Assets.manager.get(Assets.CHIPWEAPON_TEXTURE));

        badVirus.setSize(64/1.5f, 64/1.5f);
        goodVirus.setSize(64/1.5f, 64/1.5f);
        robot.setSize(50/1.5f, 64/1.5f);
        pill.setSize(50/1.5f, 100/1.5f);
        whiteBloodCell.setSize(64/1.5f, 64/1.5f);
        upgradeShield.setSize(64/1.5f,64/1.5f);
        upgradeSpeed.setSize(64/1.5f,64/1.5f);
        upgradeWeapon.setSize(64/1.5f,64/1.5f);

        badVirus.setPosition(100, Globals.WORLD_HEIGHT - badVirus.getHeight() - 15);
        goodVirus.setPosition(100, badVirus.getY() - goodVirus.getHeight() - 15);
        robot.setPosition(100, goodVirus.getY() - robot.getHeight() - 15);
        pill.setPosition(100, robot.getY() - pill.getHeight() - 15);
        whiteBloodCell.setPosition(100, pill.getY() - whiteBloodCell.getHeight() - 15);
        upgradeShield.setPosition(100, whiteBloodCell.getY() - upgradeShield.getHeight() - 15);
        upgradeSpeed.setPosition(100, upgradeShield.getY() - upgradeSpeed.getHeight() - 15);
        upgradeWeapon.setPosition(100, upgradeSpeed.getY() - upgradeWeapon.getHeight() - 15);

        addActor(badVirus);
        addActor(goodVirus);
        addActor(robot);
        addActor(pill);
        addActor(whiteBloodCell);
        addActor(upgradeShield);
        addActor(upgradeSpeed);
        addActor(upgradeWeapon);

        lblBadVirus = new MyLabel("Bad virus. It kills good viruses. You need to kill it!", Donto.getColorLabelStyle(Color.WHITE));
        lblGoodVirus = new MyLabel("Good virus. Don't kill it! It is killed by Pills", Donto.getColorLabelStyle(Color.WHITE));
        lblRobot = new MyLabel("This is you. You move with the buttons at the bottom of the screen", Donto.getColorLabelStyle(Color.WHITE));
        lblPill = new MyLabel("Pill. It kills viruses. You can destroy it", Donto.getColorLabelStyle(Color.WHITE));
        lblWhiteBloodCell = new MyLabel("White blood cell. It follows you and it will kill you", Donto.getColorLabelStyle(Color.WHITE));
        lblUpgradeShield = new MyLabel("This upgrade gives you a shield for 10 seconds", Donto.getColorLabelStyle(Color.WHITE));
        lblUpgradeSpeed = new MyLabel("This upgrade increases your speed", Donto.getColorLabelStyle(Color.WHITE));
        lblUpgradeWeapon = new MyLabel("This upgrade lets you kill stronger viruses", Donto.getColorLabelStyle(Color.WHITE));



        lblBadVirus.setPosition(badVirus.getX() + badVirus.getWidth() + 20, badVirus.getY() + badVirus.getHeight() / 2 - 15);
        lblGoodVirus.setPosition(goodVirus.getX() + goodVirus.getWidth() + 20, goodVirus.getY() + goodVirus.getHeight() / 2 - 15);
        lblRobot.setPosition(goodVirus.getX() + goodVirus.getWidth() + 20, robot.getY() + robot.getHeight() / 2 - 15);
        lblPill.setPosition(goodVirus.getX() + goodVirus.getWidth() + 20, pill.getY() + pill.getHeight() / 2 - 15);
        lblWhiteBloodCell.setPosition(whiteBloodCell.getX() + whiteBloodCell.getWidth() + 20, whiteBloodCell.getY() + whiteBloodCell.getHeight() / 2 - 15);
        lblUpgradeShield.setPosition(upgradeShield.getX() + upgradeShield.getWidth() + 20, upgradeShield.getY() + upgradeShield.getHeight() / 2 - 15);
        lblUpgradeSpeed.setPosition(upgradeSpeed.getX() + upgradeSpeed.getWidth() + 20, upgradeSpeed.getY() + upgradeSpeed.getHeight() / 2 - 15);
        lblUpgradeWeapon.setPosition(upgradeWeapon.getX() + upgradeWeapon.getWidth() + 20, upgradeWeapon.getY() + upgradeWeapon.getHeight() / 2 - 15);


        addActor(lblBadVirus);
        addActor(lblGoodVirus);
        addActor(lblRobot);
        addActor(lblPill);
        addActor(lblWhiteBloodCell);
        addActor(lblUpgradeShield);
        addActor(lblUpgradeSpeed);
        addActor(lblUpgradeWeapon);

        MyLabel tutorial = new MyLabel("Try to kill all the bad viruses before the time runs out or the good viruses die!", Donto.getColorLabelStyle(Color.WHITE));
        tutorial.setPosition(100, upgradeWeapon.getY() - 100);
        addActor(tutorial);


        TextButton back = new MyButton("", game.btnBack());
        back.setSize(back.getWidth()/2, back.getHeight()/2);
        back.setPosition(Globals.WORLD_WIDTH/2-back.getWidth()/2, 50);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
        addActor(back, 100);



        float size = (float)Math.sqrt(Globals.WORLD_WIDTH*Globals.WORLD_WIDTH + Globals.WORLD_HEIGHT*Globals.WORLD_HEIGHT);
       // background.setSize(size,size);
       // background.setPositionCenterOfActorToCenterOfViewport();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK || keycode == Input.Keys.ESCAPE) {
            game.setScreenBackByStackPop();
        }
        return false;
    }
}
