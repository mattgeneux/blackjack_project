package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.blackjack.Blackjack;
import com.mygdx.blackjack.Objects.Card;

import java.util.Random;

public class GameScreen implements Screen {
    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;
    private int counter = 0;
    public GameScreen (final Blackjack game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    public void addCard(int counter){
        Random rand = new Random();
        int suit = rand.nextInt(3);
        int number = rand.nextInt(12);

        Card card = new Card(suit, number);

        Image cardImage = card.getCardImage();
        cardImage.setWidth(125);
        cardImage.setHeight(182);
        cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
        cardImage.setY(100);

        stage.addActor(cardImage);
    }
    @Override
    public void show() {
        Texture addButtonTexture = new Texture("addCard.png");
        Texture addButtonActiveTexture = new Texture("addCardActive.png");
        TextureRegion addButtonRegion = new TextureRegion(addButtonTexture);
        TextureRegionDrawable addButtonDraw = new TextureRegionDrawable(addButtonRegion);
        TextureRegion addButtonRegionActive = new TextureRegion(addButtonActiveTexture);
        TextureRegionDrawable  addButtonDrawActive = new TextureRegionDrawable(addButtonRegionActive);
        ImageButton.ImageButtonStyle addStyle = new ImageButton.ImageButtonStyle();
        addStyle.up = addButtonDraw;
        addStyle.over = addButtonDrawActive;
        Actor addButton = new ImageButton(addStyle); //Set the button up
        addButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
               addCard(counter);
               counter += 1;
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

        addButton.setPosition(
                Gdx.graphics.getWidth() - addButton.getWidth(),
                Gdx.graphics.getHeight() - addButton.getHeight());
        stage.addActor(addButton);

        Texture exitTexture = new Texture("exit.png");
        Texture exitTextureActive = new Texture("exit_hover.png");
        TextureRegion exitRegion = new TextureRegion(exitTexture);
        TextureRegion exitRegionActive = new TextureRegion(exitTextureActive);
        TextureRegionDrawable exitDraw = new TextureRegionDrawable(exitRegion);
        TextureRegionDrawable exitDrawActive = new TextureRegionDrawable(exitRegionActive);

        ImageButton.ImageButtonStyle exitStyle = new ImageButton.ImageButtonStyle();
        exitStyle.up = exitDraw;
        exitStyle.over = exitDrawActive;

        Actor exitButton = new ImageButton(exitStyle); //Set the button up

        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                dispose();
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

        exitButton.setPosition(
                Gdx.graphics.getWidth()  - exitButton.getWidth(),
                Gdx.graphics.getHeight() / 5f - exitButton.getHeight());


        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        game.batch.dispose();
        Gdx.app.exit();
    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0.5f, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
