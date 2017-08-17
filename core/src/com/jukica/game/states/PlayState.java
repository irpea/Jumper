package com.jukica.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jukica.game.Jumper;
import com.jukica.game.sprites.Ball;
import com.jukica.game.sprites.Obstacle;

public class PlayState extends State{
    private Ball ball;
    private Obstacle obstacle;
    private Texture bg;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        ball = new Ball(50, 1000);
        obstacle = new Obstacle(150);
        cam.setToOrtho(false, Jumper.WIDTH, Jumper.HEIGHT);
        bg = new Texture("background.jpg");
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            ball.jump();
            dispose();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        ball.update(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2),0);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        sb.draw(obstacle.getObstacle(), obstacle.getPosObstacle().x, obstacle.getPosObstacle().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
