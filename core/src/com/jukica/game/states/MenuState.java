package com.jukica.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jukica.game.Jumper;

public class MenuState extends State {
    private Texture backgroud;
    private Texture playBtn;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        backgroud = new Texture("background.jpg");
        playBtn = new Texture("playBtn.png");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new PlayState(gsm));
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(backgroud, 0, 0, Jumper.WIDTH, Jumper.HEIGHT);
        sb.draw(playBtn, (Jumper.WIDTH / 2) - (playBtn.getWidth() / 2), (Jumper.HEIGHT / 2));
        sb.end();
    }

    @Override
    public void dispose() {
        backgroud.dispose();
        playBtn.dispose();
    }
}
