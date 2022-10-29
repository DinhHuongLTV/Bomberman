package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionTop extends Collision {
    public CollisionTop() {

    }

    @Override
    public Entity checkCollision(Entity entity) {
        int entityLeftX = entity.getX() + 4;
        int entityRightX = entityLeftX + Sprite.DEFAULT_SIZE - 4;
        int entityTopY = entity.getY() + Sprite.DEFAULT_SIZE;
        Entity obj1 = BombermanGame.getUpEntity(entityRightX, entityTopY);
        Entity obj2 = BombermanGame.getUpEntity(entityLeftX, entityTopY);
        if (!(obj1 instanceof Grass)) {
            return obj1;
        } else if (!(obj2 instanceof Grass)) {
            return obj2;
        }
        return null;
    }
}
