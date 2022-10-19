package uet.oop.bomberman.entities;

import javax.swing.plaf.SeparatorUI;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity extends Rectangle {
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    // public double getX() {
    // return x;
    // }

    public void setX(int x) {
        this.x = x;
    }

    // public int getY() {
    // return y;
    // }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.img, this.x, this.y);
    }

    // public abstract String toString();

    public void update() {

    };

    public Entity entityLeft() {
        // for (Entity obj : BombermanGame.getStillObjects()) {
        // // if (obj.getX() == ((this.getX() + Sprite.SCALED_SIZE) /
        // Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE
        // // && (this.getY() <= obj.getY() + Sprite.SCALED_SIZE && this.getY() >=
        // obj.getY()
        // // || this.getY() + Sprite.SCALED_SIZE <= obj.getY() + Sprite.SCALED_SIZE
        // // && this.getY() + Sprite.SCALED_SIZE >= obj.getY())) {
        // // return obj;
        // // }
        // if ()
        // }
        if (this.getBoundsInParent().intersects(getBoundsInLocal()))
            return null;
        return null;
    };

    public Entity entityRight(int x) {
        for (Entity obj : BombermanGame.getStillObjects()) {
            if (obj.getX() == x && obj.getY() == this.getY()) {
                return obj;
            }
        }
        return null;
    };

    public Entity entityUp(int y) {
        for (Entity obj : BombermanGame.getStillObjects()) {
            if (obj.getY() == (y / Sprite.SCALED_SIZE) * Sprite.SCALED_SIZE - Sprite.SCALED_SIZE
                    && obj.getX() == this.getX()) {
                return obj;
            }
        }
        return null;
    };

    public Entity entityDown(int y) {
        for (Entity obj : BombermanGame.getStillObjects()) {
            if (obj.getY() == y && this.getX() == obj.getX()) {
                return obj;
            }
        }
        return null;
    };

}
