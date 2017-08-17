package com.jukica.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.jukica.game.states.GameStateManager;
import com.jukica.game.states.PlayState;

public class Ball {
    private static final int GRAVITY = -17;
    private static final int MOVEMENT = 150;
    private Vector3 position;
    private Vector3 velocity;

    private Texture ball;
    private Rectangle boundsBall;

    public Ball(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        ball = new Texture("ball.png");
        boundsBall = new Rectangle(x, y, ball.getWidth() / 1.2f, ball.getHeight() / 1.2f);

    }

    public void update(float dt, float y) {
        if (position.y > y) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        if (position.y < y)
            position.y = y;

        velocity.scl(1 / dt);
        boundsBall.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return ball;
    }

    public void jump() {
        velocity.y = 400;
    }

    public Rectangle getBoundsBall() {
        return boundsBall;
    }

    public void dispose() {
        ball.dispose();
    }


}
