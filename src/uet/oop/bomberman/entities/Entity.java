package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.CollisionLeft;
import uet.oop.bomberman.CollisionRight;
import uet.oop.bomberman.CollisionTop;
import uet.oop.bomberman.CollisionBottom;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    // public abstract class Entity extends Rectangle {
    // Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;
    public boolean canMoveUp, canMoveDown, canMoveLeft, canMoveRight;
    public int speed = 1;
    // Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    public CollisionLeft collisionLeft;
    public CollisionRight collisionRight;
    public CollisionTop collisionTop;
    public CollisionBottom collisionBottom;

    // biến đếm thay đổi sprite
    public int count = 0;

    // Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.img, this.x, this.y);
    }

    public void update() {

    };

}
