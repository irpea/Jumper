package com.jukica.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {
    private Texture obstacle;
    private Vector2 posObstacle;

    public Obstacle(float x){
        obstacle = new Texture("obstacle.png");
        posObstacle = new Vector2(x, 0);
    }


    public Texture getObstacle() {
        return obstacle;
    }

    public Vector2 getPosObstacle() {
        return posObstacle;
    }
}
