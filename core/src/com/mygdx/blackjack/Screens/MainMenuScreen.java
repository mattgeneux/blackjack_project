package com.mygdx.blackjack.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.blackjack.Blackjack;
import com.mygdx.blackjack.Functions.Utilities;


public class MainMenuScreen implements Screen {

    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;

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



        Actor playButton = Utilities.createButton("start.png", "start_hover.png");
        //playButton = new ImageButton(playStyle); //Set the button up



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



        Actor exitButton = Utilities.createButton("exit.png", "exit_hover.png");

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
       // playButton.setPosition(
       //         Gdx.graphics.getWidth() / 2f - playButton.getWidth() / 2f,
       //         Gdx.graphics.getHeight() / 2f - playButton.getHeight() / 3f);

      //  exitButton.setWidth(Gdx.graphics.getWidth() / 2f);
      //  exitButton.setPosition(
      //          Gdx.graphics.getWidth() / 2f - exitButton.getWidth() / 2f,
      //          Gdx.graphics.getHeight() / 4f - exitButton.getHeight() / 2f);
      //  stage.addActor(playButton);
      //  stage.addActor(exitButton);
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        table.padTop(100);
        table.add(playButton);
        table.row().padTop(10);
        table.add(exitButton);
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
