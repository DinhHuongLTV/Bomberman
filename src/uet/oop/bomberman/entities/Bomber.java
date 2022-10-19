package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {
    public boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;
    private int speed = 1;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

        // if (this.getX() <= Sprite.SCALED_SIZE) {
        // canMoveLeft = false;
        // } else {
        // canMoveLeft = true;
        // }
        if (this.getY() <= Sprite.SCALED_SIZE) {
            canMoveUp = false;
        } else {
            canMoveUp = true;
        }
        if (this.getY() >= 11 * Sprite.SCALED_SIZE) {
            canMoveDown = false;
        } else {
            canMoveDown = true;
        }
        if (this.getX() >= 29 * Sprite.SCALED_SIZE + Sprite.DEFAULT_SIZE / 2) {
            canMoveRight = false;
        } else {
            canMoveRight = true;
        }

        // if (entityLeft(this.x) instanceof Wall) {
        // canMoveLeft = false;
        // } else {
        // canMoveLeft = true;
        // }

        if (BombermanGame.move.contains("LEFT") && canMoveLeft) {
            setX(getX() - speed);
            img = Sprite.player_left.getFxImage();
        }
        if (BombermanGame.move.contains("RIGHT") && canMoveRight) {
            setX(getX() + speed);
            img = Sprite.player_right.getFxImage();
        }
        if (BombermanGame.move.contains("UP") && canMoveUp) {
            setY(getY() - speed);
            img = Sprite.player_up.getFxImage();
        }
        if (BombermanGame.move.contains("DOWN") && canMoveDown) {
            setY(getY() + speed);
            img = Sprite.player_down.getFxImage();
        }

        // if (BombermanGame.getEntityAt(getX() + Sprite.SCALED_SIZE, getY()) instanceof
        // Wall) {
        // canMoveRight = false;
        // System.out.println("wall right");
        // }
        // if (BombermanGame.getEntityAt(getX(), getY() + Sprite.SCALED_SIZE) instanceof
        // Wall) {
        // this.canMoveUp = false;
        // System.out.println("wall up");
        // }

        // if (BombermanGame.getEntityAt(this.getX(), this.getY()) instanceof Wall) {
        // System.out.println(
        // "wall x: " + this.getX() / Sprite.SCALED_SIZE + " wall y: " + this.getY() /
        // Sprite.SCALED_SIZE);
        // System.out.println("Bomber x: " + this.getX() + " Bomber y: " + this.getY());
        // }
    }
}