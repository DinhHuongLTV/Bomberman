package uet.oop.bomberman.entities;

import java.util.ArrayList;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Entity {
    ArrayList<Image> left = new ArrayList<>();
    ArrayList<Image> right = new ArrayList<>();
    // private boolean detect;
    Bomber bomber;

    public Oneal(int xUnit, int yUnit, Image img, Bomber bomber) {
        super(xUnit, yUnit, img);
        setLeft();
        setRight();
        this.speed = 1;
        this.bomber = bomber;
    }

    public void setLeft() {
        left.add(Sprite.oneal_left1.getFxImage());
        left.add(Sprite.oneal_left2.getFxImage());
        left.add(Sprite.oneal_left3.getFxImage());
    }

    public void setRight() {
        right.add(Sprite.oneal_right1.getFxImage());
        right.add(Sprite.oneal_right2.getFxImage());
        right.add(Sprite.oneal_right3.getFxImage());
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
        if ((collision.checkCollision(this) instanceof Explosion)) {
            this.check = true;
        }
        if (this.getY() < bomber.getY() && DOWN) {
            this.setY(this.getY() + this.speed);
            img = left.get(spriteNum);
        }
        if (this.getY() > bomber.getY() && UP) {
            this.setY(this.getY() - this.speed);
            img = right.get(spriteNum);
        }
        if (this.getX() > bomber.getX() && LEFT) {
            this.setX(this.getX() - this.speed);
            img = left.get(spriteNum);
        }
        if (this.getX() < bomber.getX() && RIGHT) {
            this.setX(this.getX() + this.speed);
            img = right.get(spriteNum);
        }

        if (!(BombermanGame.getEntity(this.getX(), this.getY() - 8) instanceof Grass)) {
            this.UP = false;
        } else {
            this.UP = true;
        }

        if (!(BombermanGame.getEntity(this.getX(), this.getY() + 8) instanceof Grass)) {
            this.DOWN = false;
        } else {
            this.DOWN = true;
        }

        if (!(BombermanGame.getEntity(this.getX() + 8, this.getY()) instanceof Grass)) {
            this.RIGHT = false;
        } else {
            this.RIGHT = true;
        }

        if (!(BombermanGame.getEntity(this.getX() - 8, this.getY()) instanceof Grass)) {
            this.LEFT = false;
        } else {
            this.LEFT = true;
        }
    }

}
