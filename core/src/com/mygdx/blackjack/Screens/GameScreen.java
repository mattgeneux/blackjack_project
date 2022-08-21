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
    public GameScreen (final Blackjack game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    public void addCard(){
        Random rand = new Random();
        int suit = rand.nextInt(3);
        int number = rand.nextInt(12);

        Card card = new Card(suit, number);

        Image cardImage = card.getCardImage();
        cardImage.setX(Gdx.graphics.getWidth() / 4f);
        cardImage.setY(Gdx.graphics.getHeight() * 2f / 3f);
        cardImage.setWidth(Gdx.graphics.getWidth() / 2f);
        stage.addActor(cardImage);
    }
    @Override
    public void show() {
        Texture addButtonTexture = new Texture("start.png");
        Texture addButtonActiveTexture = new Texture("start_hover.png");
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
               addCard();
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

        addButton.setPosition(
                Gdx.graphics.getWidth() / 2f - addButton.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - addButton.getHeight() / 3f);
        stage.addActor(addButton);
        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

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
