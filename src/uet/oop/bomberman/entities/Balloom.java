package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Collision;
import uet.oop.bomberman.CollisionBottom;
import uet.oop.bomberman.CollisionLeft;
import uet.oop.bomberman.CollisionRight;
import uet.oop.bomberman.CollisionTop;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Entity {

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        canMoveLeft = true;
        collisionTop = new CollisionTop();
        collisionRight = new CollisionRight();
        collisionBottom = new CollisionBottom();
        collisionLeft = new CollisionLeft();
    }

    @Override
    public void update() {
        if (canMoveUp) {
            this.setY(this.getY() - speed);
            img = Sprite.balloom_left2.getFxImage();
        }
        if (canMoveDown) {
            this.setY(this.getY() + speed);
            img = Sprite.balloom_right2.getFxImage();
        }
        if (canMoveLeft) {
            this.setX(this.getX() - speed);
            img = Sprite.balloom_left1.getFxImage();
        }
        if (canMoveRight) {
            this.setX(this.getX() + speed);
            img = Sprite.balloom_right1.getFxImage();
        }

    }
}
