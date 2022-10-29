package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Balloom;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

// import javax.sound.midi.Sequence;

public class BombermanGame extends Application {

    Button buttonStart;
    Button buttonExit;

    Scene sceneGame;
    Scene sceneMenu;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    private GraphicsContext gc;
    private GridPane menu;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    public static List<String> move = new ArrayList<>();
    // private Collision collisionChecker = new Collision();

    Level1 level1 = new Level1();

    public static Entity getStillEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getX() < x && x < e.getX() + Sprite.SCALED_SIZE && e.getY() < y
                    && y < e.getY() + Sprite.SCALED_SIZE) {
                return e;
            }
        }
        return null;
    }

    public static Entity getLeftEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getY() < y && y < e.getY() + Sprite.SCALED_SIZE && x == e.getX() + Sprite.SCALED_SIZE) {
                return e;
            }
        }
        return null;
    }

    public static Entity getRightEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getY() < y && y < e.getY() + Sprite.SCALED_SIZE && x == e.getX()) {
                return e;
            }
        }
        return null;
    }

    public static Entity getUpEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getX() < x && x < e.getX() + Sprite.SCALED_SIZE && y == e.getY() + Sprite.SCALED_SIZE) {
                return e;
            }
        }
        return null;
    }

    public static Entity getDownEntity(int x, int y) {
        for (Entity e : stillObjects) {
            if (e.getX() < x && x < e.getX() + Sprite.SCALED_SIZE && y == e.getY()) {
                return e;
            }
        }
        return null;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public Entity getEntityAt(int x, int y) {
        for (Entity entity : stillObjects) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao button
        buttonStart = new Button();
        buttonStart.setText("Start");
        buttonExit = new Button();
        buttonExit.setText("Exit");

        buttonStart.setOnAction(e -> {
            stage.setScene(sceneGame);
        });

        buttonExit.setOnAction(e -> {
            System.exit(0);
        });
        menu = new GridPane();
        menu.setPadding(new Insets(10, 10, 10, 10));
        menu.setVgap(10);
        menu.setHgap(10);
        menu.setAlignment(Pos.CENTER);
        menu.add(buttonStart, 1, 0);
        menu.add(buttonExit, 1, 1);
        sceneMenu = new Scene(menu, 360, 360);
        sceneMenu.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        // Tao scene chứa game chính
        sceneGame = new Scene(root);

        // Then scene vao stage
        stage.setResizable(false);
        stage.setScene(sceneMenu);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap(root);

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        Balloom balloom1 = new Balloom(13, 1, Sprite.balloom_right1.getFxImage());
        // todo: thêm code di chuyển
        sceneGame.setOnKeyPressed(e -> {
            String code = e.getCode().toString();
            if (!move.contains(code)) {
                move.add(code);
            }
        });
        sceneGame.setOnKeyReleased(e -> {
            String code = e.getCode().toString();
            move.remove(code);
        });

        entities.add(balloom1);
        entities.add(bomberman);
    }

    public void createMap(Group root) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (Level1.map[i][j] == 1) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (Level1.map[i][j] == 2) {
                    object = new Brick(j, i, Sprite.brick.getFxImage());
                } else {
                    object = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

}
