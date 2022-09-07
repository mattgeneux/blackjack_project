package com.mygdx.blackjack.Screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.blackjack.Blackjack;
import com.mygdx.blackjack.Functions.Utilities;
import com.mygdx.blackjack.Objects.Card;
import com.mygdx.blackjack.Objects.Player;

import java.util.ArrayList;
import java.util.List;


public class GameScreen implements Screen {
    final Blackjack game;
    final Stage stage;
    OrthographicCamera camera;

    public List<List<Integer>> picked;

    public boolean gameover = false;

    enum Turn{
        PLAYER,
        DEALER
    }

    public Turn turn;
    public GameScreen (final Blackjack game){
        this.game = game;

        stage = new Stage(new ScreenViewport());
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        picked = new ArrayList<>();

        game.p1 = new Player(picked);
        game.p2 = new Player(picked);
        turn = Turn.PLAYER;
        setup(game.p1, game.p2);
        playerLoop(game.p1);

    }

    public void playerLoop(Player p1){
        if (!gameover) {
            // player turn
            if (turn == Turn.PLAYER){
                if (p1.getTotal() >= 21){

                    gameover = true;
                }
            }
        }
        else{

            System.out.println("gameover" + p1.getTotal() + game.p2.getTotal());
            game.p1Score = p1.getTotal();
            game.p2Score = game.p2.getTotal();


        }
    }

    public void dealerLoop(Player p2){
        // dealer turn
        while (!gameover){
            if  (p2.getTotal() < 17){
                p2.addCard(picked);
                displayDeck(p2, true);

            }
            else{

                gameover = true;
                System.out.println("gameover" + game.p1.getTotal() + p2.getTotal());

            }
        }
        game.p1Score = game.p1.getTotal();
        game.p2Score = p2.getTotal();

    }

    public void setup(Player p1, Player p2){
        displayDeck(p1, false);
        displayDeck(p2, true);
    }
    public void displayDeck(Player p, Boolean dealer){
        List<Card> pDeck = p.getDeck();
        int counter = 0;
        for (Card card : pDeck){
            Image cardImage = card.getCardImage();

            if (dealer) {
                if (counter == 0) {
                    if (turn == turn.PLAYER) {
                        Texture backTexture = new Texture("card_back.png");
                        cardImage = new Image(backTexture);
                    }

                }
                cardImage.setY(400);
            }
              else{
                cardImage.setY(100);
            }
            cardImage.setWidth(125);
            cardImage.setHeight(182);
            cardImage.setX(cardImage.getWidth() * 3 * counter / 2);
            stage.addActor(cardImage);
            counter++;
        }



    }
    public void addCard(){
        game.p1.addCard(picked);
        displayDeck(game.p1, false);
        playerLoop(game.p1);
    }


    @Override
    public void show() {

        Actor addButton = Utilities.createButton("addCard.png", "addCardActive.png");
        addButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
              // addCard(counter);
              // counter += 1;
                addCard();
                playerLoop(game.p1);
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

       // addButton.setPosition(
       //         Gdx.graphics.getWidth() - addButton.getWidth(),
       //         Gdx.graphics.getHeight() - addButton.getHeight());
       // stage.addActor(addButton);

        Actor exitButton = Utilities.createButton("exit.png", "exit_hover.png"); //Set the button up

        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {
                dispose();
                // game.setScreen(new EndScreen(game));
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

     //   exitButton.setPosition(
     //           Gdx.graphics.getWidth()  - exitButton.getWidth(),
     //           Gdx.graphics.getHeight() / 5f - exitButton.getHeight());


     //   stage.addActor(exitButton);


        Actor changeButton = Utilities.createButton("stay.png", "stay_hover.png"); //Set the button up
        changeButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent ev, float x, float y, int pointer, int button) {

               turn = Turn.DEALER;
               dealerLoop(game.p2);
            }

            @Override
            public boolean touchDown(InputEvent ev, float x, float y, int pointer, int button) {
                return true;
            }

        });

      //  changeButton.setPosition(
      //          Gdx.graphics.getWidth()  - changeButton.getWidth(),
      //          Gdx.graphics.getHeight() / 3f + 0.4f* changeButton.getHeight());


      //  stage.addActor(changeButton);
        Table table = new Table();
        //table.setFillParent(true);
        stage.addActor(table);
        table.setPosition(0.85f*Gdx.graphics.getWidth(), 0.5f*Gdx.graphics.getHeight());
        table.add(addButton);
        table.row().padTop(100);
        table.add(changeButton);
        table.row().padTop(100);
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
    public void render(float delta) {
        ScreenUtils.clear(0, 0.5f, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        stage.act();
        stage.draw();
        game.batch.end();

        if (gameover){

            game.setScreen(new EndScreen(game));
        }


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void hide() {

    }
}
