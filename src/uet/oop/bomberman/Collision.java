package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

// import javafx.scene.Scene;

public abstract class Collision {

    public Collision() {
    }

    public abstract Entity checkCollision(Entity entity);
    // {
    // int entityLeftX = entity.getX() + 4;
    // int entityRightX = entityLeftX + Sprite.DEFAULT_SIZE - 4;
    // int entityTopY = entity.getY() + Sprite.DEFAULT_SIZE;
    // int entityBottomY = entityTopY + Sprite.DEFAULT_SIZE;
    // if (!(BombermanGame.getUpEntity(entityLeftX, entityTopY) instanceof Wall) &&
    // !(BombermanGame.getUpEntity(entityRightX, entityTopY) instanceof Wall)) {
    // entity.canMoveUp = true;
    // } else {
    // entity.canMoveUp = false;
    // }
    // // down
    // if (!(BombermanGame.getDownEntity(entityLeftX, entityBottomY) instanceof
    // Wall) &&
    // !(BombermanGame.getDownEntity(entityRightX, entityBottomY) instanceof Wall))
    // {
    // entity.canMoveDown = true;
    // } else {
    // entity.canMoveDown = false;
    // }
    // if (!(BombermanGame.getLeftEntity(entityLeftX, entityTopY) instanceof Wall)
    // &&
    // !(BombermanGame.getLeftEntity(entityLeftX, entityBottomY) instanceof Wall)) {
    // entity.canMoveLeft = true;
    // } else {
    // entity.canMoveLeft = false;
    // }
    // if (!(BombermanGame.getRightEntity(entityRightX, entityTopY) instanceof Wall)
    // &&
    // !(BombermanGame.getRightEntity(entityRightX, entityBottomY) instanceof Wall))
    // {
    // entity.canMoveRight = true;
    // } else {
    // entity.canMoveRight = false;
    // }
    // return null;
    // };
}
