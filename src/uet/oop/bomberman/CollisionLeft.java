package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionLeft extends Collision {
    public CollisionLeft() {
    }

    @Override
    public Entity checkCollision(Entity entity) {
        int entityLeftX = entity.getX() + 4;
        int entityTopY = entity.getY() + Sprite.DEFAULT_SIZE;
        int entityBottomY = entityTopY + Sprite.DEFAULT_SIZE;
        Entity obj1 = BombermanGame.getRightEntity(entityLeftX, entityTopY);
        Entity obj2 = BombermanGame.getRightEntity(entityLeftX, entityBottomY);
        if (!(obj1 instanceof Grass)) {
            return obj1;
        } else if (!(obj2 instanceof Grass)) {
            return obj2;
        }
        return null;
    }
}