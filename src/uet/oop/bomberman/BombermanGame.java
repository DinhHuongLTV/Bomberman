package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level1;

import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

// import javax.sound.midi.Sequence;

public class BombermanGame extends Application {

    Button buttonStart;
    Button buttonExit;

    Scene sceneGame;
    Scene sceneMenu;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    public static List<String> move = new ArrayList<>();

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    // public static Entity getEntityAt(int x, int y) {
    // // int realX = x - 1 / Sprite.SCALED_SIZE;
    // // int realY = y - 1 / Sprite.SCALED_SIZE;
    // // for (Entity res : stillObjects) {
    // // if (res.getX() == realX && res.getY() == realY) {
    // // return res;
    // // }
    // // }
    // // return null;
    // }

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

        buttonStart.setStyle("-fx-font-size: 24px;");
        buttonExit.setStyle("-fx-font-size: 24px;");

        buttonStart.setOnAction(e -> {
            stage.setScene(sceneGame);
        });

        buttonExit.setOnAction(e -> {
            System.exit(0);
        });
        HBox layout = new HBox(20);
        layout.getChildren().addAll(buttonStart, buttonExit);
        sceneMenu = new Scene(layout, 360, 360);

        // Tao scene chứa game chính
        sceneGame = new Scene(root);

        // Them scene vao stage
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

        createMap();

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
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

        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity object;
                if (level1.map[i][j] == 1) {
                    object = new Wall(j, i, Sprite.wall.getFxImage());
                } else if (level1.map[i][j] == 2) {
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
