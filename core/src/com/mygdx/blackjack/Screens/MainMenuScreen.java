package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.blackjack.Blackjack;
import com.mygdx.blackjack.Screens.GameScreen;

public class MainMenuScreen implements Screen {

    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;
    Texture playButtonTexture;
    Texture playButtonActiveTexture;
    TextureRegion playButtonRegion;
    TextureRegionDrawable playButtonDraw;
    Actor playButton;
    ImageButton.ImageButtonStyle playStyle;
    public MainMenuScreen(final Blackjack game) {
        this.game = game;

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);



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
    public void show() {

        Texture titleTexture = new Texture("title.png");
        Image title = new Image(titleTexture);
        //title.setAlign(Align.center);
        title.setX(Gdx.graphics.getWidth() / 4f);
        title.setY(Gdx.graphics.getHeight() * 2f / 3f);
        title.setWidth(Gdx.graphics.getWidth() / 2f);
        stage.addActor(title);

        playButtonTexture = new Texture("start.png");
        playButtonActiveTexture = new Texture("start_hover.png");
        playButtonRegion = new TextureRegion(playButtonTexture);
        playButtonDraw = new TextureRegionDrawable(playButtonRegion);
        TextureRegion playButtonRegionActive = new TextureRegion(playButtonActiveTexture);
        TextureRegionDrawable  playButtonDrawActive = new TextureRegionDrawable(playButtonRegionActive);
        playStyle = new ImageButton.ImageButtonStyle();
        playStyle.up = playButtonDraw;
        playStyle.over = playButtonDrawActive;

        playButton = new ImageButton(playStyle); //Set the button up



        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game)); // go to next screen (play screen)
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });



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
       // playButton.setWidth(Gdx.graphics.getWidth() / 2f);
        playButton.setPosition(
                Gdx.graphics.getWidth() / 2f - playButton.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - playButton.getHeight() / 3f);

      //  exitButton.setWidth(Gdx.graphics.getWidth() / 2f);
        exitButton.setPosition(
                Gdx.graphics.getWidth() / 2f - exitButton.getWidth() / 2f,
                Gdx.graphics.getHeight() / 4f - exitButton.getHeight() / 2f);
        stage.addActor(playButton);
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
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
