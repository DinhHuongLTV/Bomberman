package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static int level = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    private static List<Entity> explosion = new ArrayList<>();

    private static List<Entity> bomblist = new ArrayList<>();

    public static List<Entity> getBomblist() {
        return bomblist;
    }

    public Bomber bomberman;

    public static void setBomblist(List<Entity> bomblist) {
        BombermanGame.bomblist = bomblist;
    }

    public static List<Entity> getExplosion() {
        return explosion;
    }

    public static void setExplosion(List<Entity> explosion) {
        BombermanGame.explosion = explosion;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static void setStillObjects(List<Entity> stillObjects) {
        BombermanGame.stillObjects = stillObjects;
    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static void setEntities(List<Entity> entities) {
        BombermanGame.entities = entities;
    }

    public static Entity getEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getX() - 16 <= x && e.getX() + 16 >= x && e.getY() - 24 <= y && e.getY() + 8 >= y) {
                return e;
            }
        }
        return null;
    }

    public int pos(int x) {
        int temp = x / 32;
        int start = temp * 32;
        int end = (temp + 1) * 32;
        if ((end - x) > (x - start)) {
            return start;
        }
        return end;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        scene.setOnKeyReleased(e -> {
            String move = e.getCode().toString();
            bomberman.bomberMove.remove(move);
        });

        scene.setOnKeyPressed(e -> {
            String move = e.getCode().toString();
            if (!bomberman.bomberMove.contains(move)) {
                bomberman.bomberMove.add(move);
            }
            if (move.equals("S")) {
                Bomb bomb = new Bomb(1, 1, Sprite.bomb.getFxImage());
                bomb.setX(pos(bomberman.getX()));
                bomb.setY(pos(bomberman.getY()));
                if (!(getEntity(bomb.getX(), bomb.getY()) instanceof Wall)
                        && !(getEntity(bomb.getX(), bomb.getY()) instanceof Brick)) {
                    bomblist.add(bomb);
                }
            }
        });
        createMap("./src/Level/level" + level + ".txt");
    }

    public void createMap(String url) {
        entities.clear();
        stillObjects.clear();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        try (FileInputStream fileInputStream = new FileInputStream(url)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            int rowCount = 0;
            while (line != null) {
                System.out.println(rowCount + " " + line);
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '1':
                            int randNum = ThreadLocalRandom.current().nextInt(0, 2);
                            Balloom balloom = new Balloom(i, rowCount, Sprite.balloom_left1.getFxImage());
                            balloom.setDirection(randNum);
                            entities.add(balloom);
                            break;
                        case '2':
                            entities.add(new Oneal(i, rowCount, Sprite.oneal_left1.getFxImage(), bomberman));
                            break;

                    }
                }
                for (int i = 0; i < line.length(); i++) {
                    switch (line.charAt(i)) {
                        case '#':
                            stillObjects.add(new Wall(i, rowCount, Sprite.wall.getFxImage()));
                            break;
                        case '*':
                            stillObjects.add(new Brick(i, rowCount, Sprite.brick.getFxImage()));
                            break;
                        case 'x':
                            stillObjects.add(new Portal(i, rowCount, Sprite.portal.getFxImage()));
                            break;
                        default:
                            stillObjects.add(new Grass(i, rowCount, Sprite.grass.getFxImage()));
                            break;
                    }
                }
                line = bufferedReader.readLine();

                rowCount++;

            }
            entities.add(bomberman);

        } catch (

        IOException e) {
            e.getStackTrace();
        }
    }

    public void update() {
        entities.forEach(Entity::update);

        for (int i = 0; i < bomblist.size(); i++) {
            bomblist.get(i).update();
            if (bomblist.get(i).check) {
                bomblist.remove(i);
            }
        }

        for (int i = 0; i < explosion.size(); i++) {
            explosion.get(i).update();
            if (explosion.get(i).check) {
                explosion.remove(i);
            }
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
            if (stillObjects.get(i).check) {
                if (stillObjects.get(i) instanceof Brick) {
                    Entity entity = new Grass(1, 1, Sprite.grass.getFxImage());
                    entity.setX(stillObjects.get(i).getX());
                    entity.setY(stillObjects.get(i).getY());
                    stillObjects.add(entity);
                    stillObjects.remove(i);
                } else if (stillObjects.get(i) instanceof Portal) {
                    level++;
                    if (level == 2) {
                        createMap("./src/Level/level" + level + ".txt");
                    }
                }
            }
        }
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).check && !(entities.get(i) instanceof Bomber)) {
                entities.remove(i);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        explosion.forEach(g -> g.render(gc));
        bomblist.forEach(g -> g.render(gc));
    }

}
