package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.EnumSet;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionBottom;
import uet.oop.bomberman.CollisionLeft;
import uet.oop.bomberman.CollisionRight;
import uet.oop.bomberman.CollisionTop;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

    BombermanGame game;

    // * Hoạt ảnh bomber */
    public ArrayList<Image> leftSprite = new ArrayList<>();
    public ArrayList<Image> rightSprite = new ArrayList<>();
    public ArrayList<Image> topSprite = new ArrayList<>();
    public ArrayList<Image> bottomSprite = new ArrayList<>();

    private void setLeftSprite() {
        leftSprite.add(Sprite.player_left.getFxImage());
        leftSprite.add(Sprite.player_left_1.getFxImage());
        leftSprite.add(Sprite.player_left_2.getFxImage());
    }

    private void setRightSprite() {
        leftSprite.add(Sprite.player_right.getFxImage());
        leftSprite.add(Sprite.player_right_1.getFxImage());
        leftSprite.add(Sprite.player_right_2.getFxImage());
    }

    private void setTopSprite() {
        leftSprite.add(Sprite.player_up.getFxImage());
        leftSprite.add(Sprite.player_up_1.getFxImage());
        leftSprite.add(Sprite.player_up_2.getFxImage());
    }

    private void setBottomSprite() {
        leftSprite.add(Sprite.player_down.getFxImage());
        leftSprite.add(Sprite.player_down_1.getFxImage());
        leftSprite.add(Sprite.player_down_2.getFxImage());
    }

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        setLeftSprite();
        setRightSprite();
        setTopSprite();
        setBottomSprite();
        collisionTop = new CollisionTop();
        collisionRight = new CollisionRight();
        collisionBottom = new CollisionBottom();
        collisionLeft = new CollisionLeft();
    }

    @Override
    public void update() {
        // collision = new Collision();
        // collision.checkCollision(this);
        if (BombermanGame.move.contains("LEFT") && canMoveLeft) {
            setX(getX() - speed);
            // count++;
            // img = leftSprite.get(count % 2 + 1);
        }
        if (BombermanGame.move.contains("RIGHT") && canMoveRight) {
            setX(getX() + speed);
            // count++;
            // img = rightSprite.get(count % 2 + 1);
        }
        if (BombermanGame.move.contains("UP") && canMoveUp) {
            setY(getY() - speed);
            // count++;
            // img = topSprite.get(count % 2 + 1);
        }
        if (BombermanGame.move.contains("DOWN") && canMoveDown) {
            setY(getY() + speed);
            // count++;
            // img = bottomSprite.get(count % 2 + 1);
        }
        if ((collisionLeft.checkCollision(this) instanceof Wall)) {
            canMoveLeft = false;
        } else {
            canMoveLeft = true;
        }
        if ((collisionRight.checkCollision(this) instanceof Wall)) {
            canMoveRight = false;
        } else {
            canMoveRight = true;
        }
        if ((collisionTop.checkCollision(this) instanceof Wall)) {
            canMoveUp = false;
        } else {
            canMoveUp = true;
        }
        if ((collisionBottom.checkCollision(this) instanceof Wall)) {
            canMoveDown = false;
        } else {
            canMoveDown = true;
        }
    }
}