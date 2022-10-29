package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;

public class Collision {

    public Collision() {
    }

    public Entity checkCollision(Entity entity) {
        // kiểm tra va chạm nổ
        for (Entity e : BombermanGame.getExplosion()) {
            if (e.getX() - 16 <= entity.getX() && e.getX() + 16 >= entity.getX() && e.getY() - 24 <= entity.getY()
                    && e.getY() + 8 >= entity.getY()) {
                return e;
            }
        }
        // kiểm tra va chạm bomb
        for (Entity e : BombermanGame.getBomblist()) {
            if (e.getX() - 16 <= entity.getX() && e.getX() + 16 >= entity.getX() && e.getY() - 24 <= entity.getY()
                    && e.getY() + 8 >= entity.getY()) {
                return e;
            }
        }

        // kiểm tra va chạm brick và wall
        for (Entity e : BombermanGame.getStillObjects()) {
            if (e.getX() - 16 <= entity.getX() && e.getX() + 16 >= entity.getX() && e.getY() - 24 <= entity.getY()
                    && e.getY() + 8 >= entity.getY()) {
                return e;
            }
        }
        return null;
    }
}
