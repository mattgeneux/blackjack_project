package com.mygdx.blackjack.Functions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Utilities {
    public static Actor createButton(String filename, String filename_active){
        Texture texture = new Texture(filename);
        Texture textureActive = new Texture(filename_active);
        TextureRegion region = new TextureRegion(texture);
        TextureRegion regionActive = new TextureRegion(textureActive);
        TextureRegionDrawable draw = new TextureRegionDrawable(region);
        TextureRegionDrawable drawActive = new TextureRegionDrawable(regionActive);

        ImageButton.ImageButtonStyle exitStyle = new ImageButton.ImageButtonStyle();
        exitStyle.up = draw;
        exitStyle.over = drawActive;

        Actor button = new ImageButton(exitStyle); //Set the button up


        return button;
    }
}
