package uet.oop.bomberman.entities;

import java.util.ArrayList;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Entity {

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    public void setDirection(int num) {
        if (num == HORIZONTAL) {
            LEFT = true;
        } else {
            UP = true;
        }
    }

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLeft();
        setRight();
        this.speed = 1;
    }

    ArrayList<Image> left = new ArrayList<>();
    ArrayList<Image> right = new ArrayList<>();

    private void setLeft() {
        left.add(Sprite.balloom_left1.getFxImage());
        left.add(Sprite.balloom_left2.getFxImage());
        left.add(Sprite.balloom_left3.getFxImage());
    }

    private void setRight() {
        right.add(Sprite.balloom_right1.getFxImage());
        right.add(Sprite.balloom_right2.getFxImage());
        right.add(Sprite.balloom_right3.getFxImage());
    }

    @Override
    public void update() {
        spriteCounter++;
        if (spriteCounter > 20) {
            if (spriteNum == 0) {
                spriteNum = 1;
            } else if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
        collision = new Collision();
        if (collision.checkCollision(this) instanceof Explosion) {
            check = true;
        }
        if (LEFT) {
            this.x -= this.speed;
            this.img = left.get(spriteNum);
            if (!(collision.checkCollision(this) instanceof Grass)) {
                RIGHT = true;
                LEFT = false;
            }
        }
        if (RIGHT) {
            this.x += this.speed;
            this.img = right.get(spriteNum);
            if (!(collision.checkCollision(this) instanceof Grass)) {
                RIGHT = false;
                LEFT = true;
            }
        }
        if (UP) {
            this.y -= this.speed;
            this.img = left.get(spriteNum);
            if (!(collision.checkCollision(this) instanceof Grass)) {
                UP = false;
                DOWN = true;
            }
        }

        if (DOWN) {
            this.y += this.speed;
            this.img = right.get(spriteNum);
            if (!(collision.checkCollision(this) instanceof Grass)) {
                DOWN = false;
                UP = true;
            }
        }

    }
}
