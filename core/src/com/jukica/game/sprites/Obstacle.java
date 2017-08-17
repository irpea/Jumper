package com.jukica.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {
    public static final int OBSTACLE_WIDTH = 40;
    public static final int FLUCTUATION = 100;
    private Texture obstacle;
    private Rectangle boundsObstacle;
    private Vector2 posObstacle;
    private Random rand;

    public Obstacle(float x, float y){
        obstacle = new Texture("obstacle.png");
        rand = new Random();
        posObstacle = new Vector2(x + rand.nextInt(FLUCTUATION), y);
        boundsObstacle = new Rectangle(posObstacle.x, posObstacle.y, getObstacle().getWidth() / 1.2f, getObstacle().getHeight() / 1.2f);
    }

    public Texture getObstacle() {
        return obstacle;
    }

    public Vector2 getPosObstacle() {
        return posObstacle;
    }

    public void reposition(float x, float y){
        posObstacle.set(x + rand.nextInt(FLUCTUATION), y);
        boundsObstacle.setPosition(posObstacle.x, posObstacle.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsObstacle);
    }

    public void dispose(){
        obstacle.dispose();
    }
}
