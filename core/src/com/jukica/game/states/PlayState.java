package com.jukica.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jukica.game.Jumper;
import com.jukica.game.sprites.Ball;
import com.jukica.game.sprites.Obstacle;

import java.util.Random;

public class PlayState extends State {
    private static final int OBSTACLE_SPACING = 200;
    private static final int OBSTACLE_COUNT = 3;
    public static final int GROUND_Y_OFFSET = -55;

    private Ball ball;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2, groundPos3;

    private int jumpCounter;

    private Array<Obstacle> obstacles;

    private int score;
    private String scoreCount;
    private BitmapFont bitmapFont;
    private Sound mali;
    private Sound veliki;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Jumper.WIDTH / 1.5f, Jumper.HEIGHT / 1.5f);
        bg = new Texture("background.jpg");
        ground = new Texture("ground.png");
        ball = new Ball(50, 100);
        groundPos1 = new Vector2(cam.position.x - (cam.viewportWidth / 1.5f), GROUND_Y_OFFSET);
        groundPos2 = new Vector2((cam.position.x - (cam.viewportWidth / 1.5f)) + ground.getWidth(), GROUND_Y_OFFSET);
        groundPos3 = new Vector2((cam.position.x - (cam.viewportWidth / 1.5f)) + (2 * ground.getWidth()), GROUND_Y_OFFSET);
        jumpCounter = 0;

        obstacles = new Array<Obstacle>();

        for (int i = 1; i <= OBSTACLE_COUNT; i++) {
            obstacles.add(new Obstacle(i * (OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH), getGroundHeight()));
        }
        mali = Gdx.audio.newSound(Gdx.files.internal("mali.mp3"));
        veliki = Gdx.audio.newSound(Gdx.files.internal("veliki.mp3"));
        score = 0;
        scoreCount = "Score: 0";
        bitmapFont = new BitmapFont();
    }

    public float getGroundHeight() {
        return ground.getHeight() + GROUND_Y_OFFSET;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (jumpCounter < 2) {
                ball.jump();
                jumpCounter++;
            }
        } else {
            if (ball.getPosition().y == (ground.getHeight() + GROUND_Y_OFFSET)) {
                jumpCounter = 0;
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        ball.update(dt, (ground.getHeight() + GROUND_Y_OFFSET));
        cam.position.x = ball.getPosition().x + 150;
        for (int i = 0; i < OBSTACLE_COUNT; i++) {
            Obstacle obstacle = obstacles.get(i);
            if (cam.position.x - (cam.viewportWidth / 1.5f) > obstacle.getPosObstacle().x + obstacle.getObstacle().getWidth()) {
                    obstacle.reposition(obstacle.getPosObstacle().x + ((OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH) * OBSTACLE_COUNT), getGroundHeight());
                score++;
                scoreCount = "score: " + score;
                if(score < 50 && score % 2 == 0){
                    mali.play();
                } else if (score >= 50 && score % 2 == 0){
                    veliki.play();
                }
            }
            if (obstacle.collides(ball.getBoundsBall())) {
                endgame();
            }
        }
        cam.update();
    }

    private void endgame() {
        gsm.set(new PlayState(gsm));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 1.5f), 0);
        sb.draw(ball.getTexture(), ball.getPosition().x, ball.getPosition().y);
        for (Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getObstacle(), obstacle.getPosObstacle().x, obstacle.getPosObstacle().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.draw(ground, groundPos3.x, groundPos3.y);
        bitmapFont.setColor(1, 1, 1, 1);
        bitmapFont.draw(sb, scoreCount, (cam.position.x + cam.viewportWidth / 15f), 400);
        bitmapFont.getData().setScale(2, 2);
        sb.end();
    }

    private void updateGround() {
        if (cam.position.x - (cam.viewportWidth / 1.5f) > groundPos1.x + ground.getWidth()) {
            groundPos1.add(ground.getWidth() * 3, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 1.5f) > groundPos2.x + ground.getWidth()) {
            groundPos2.add(ground.getWidth() * 3, 0);
        }
        if (cam.position.x - (cam.viewportWidth / 1.5f) > groundPos3.x + ground.getWidth()) {
            groundPos3.add(ground.getWidth() * 3, 0);
        }
    }

    @Override
    public void dispose() {
        bg.dispose();
        ball.dispose();
        for (Obstacle obstacle : obstacles)
            obstacle.dispose();
        mali.dispose();
        veliki.dispose();
        bitmapFont.dispose();
        System.out.println("Play State disposed");
    }
}
