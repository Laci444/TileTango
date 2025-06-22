package com.unideb.inf.tiletango.frontend;

import com.unideb.inf.tiletango.backend.Game;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameSceneController {

    final static double BASE_TILE_RADIUS = 100;

    GameConfiguration config;
    Scene gameScene;
    Pane gameBoard;
    Game game;

    List<TileView> tiles;
    ObservableList<Integer> tileOrder;
    double inititalGameBoardHeight;

    public GameSceneController(GameConfiguration config) throws IOException {
        this.config = config;
        loadScene();
        prepareScene();
    }

    public Scene getGameScene() {
        return gameScene;
    }

    private void loadScene() throws IOException {
        FXMLLoader gameSceneFxml = new FXMLLoader(getClass().getResource("/fxml/GameScene.fxml"));
        Parent root = gameSceneFxml.load();
        gameScene = new Scene(root);
        gameBoard = (Pane) gameScene.lookup("#game_board");
        this.game = new Game(config.numberOfTiles());
    }

    public void prepareScene() {
        var state = game.getState();

        tileOrder = FXCollections.observableArrayList(state);

        tiles = new ArrayList<>(state.size());

        // center tile creation
        TileView centerTile = new TileView(state.getFirst());
        centerTile.radiusProperty().bind(Bindings.createDoubleBinding(
                () -> 20 * Math.min(Math.min(gameBoard.getHeight(), gameBoard.getWidth()) / 300, 1.5), // TODO: magic number not really OK
                gameBoard.heightProperty(),
                gameBoard.widthProperty()
        ));
        gameBoard.getChildren().add(centerTile);
        tiles.add(centerTile);

        // circle tiles creation
        for (int i = 1; i < state.size(); i++) {
            TileView circleTile = new TileView(state.get(i));
            circleTile.radiusProperty().bind(Bindings.createDoubleBinding(
                    () -> 20 * Math.min(Math.min(gameBoard.getHeight(), gameBoard.getWidth()) / 300, 1.5), // TODO: magic number not really OK
                    gameBoard.heightProperty(),
                    gameBoard.widthProperty()
            ));
            gameBoard.getChildren().add(circleTile);
            tiles.add(circleTile);


            circleTile.setOnMouseClicked(_ -> {
                game.moveValue(circleTile.getNumber());
                buildOrder();
                positionCircleTiles(inititalGameBoardHeight); // TODO: this is so wrong
            });
        }
    }

    public void buildOrder() {
        tileOrder.setAll(game.getState());
    }

    public void bindCircles() {
        inititalGameBoardHeight = gameBoard.getHeight();

        /*
        centerTile.centerXProperty().bind(gameBoard.widthProperty().divide(2));
        centerTile.centerYProperty().bind(gameBoard.heightProperty().divide(2));
        */

        gameBoard.heightProperty().addListener((_, _, _) -> positionCircleTiles(inititalGameBoardHeight));
        gameBoard.widthProperty().addListener((_, _, _) -> positionCircleTiles(inititalGameBoardHeight));

        positionCircleTiles(inititalGameBoardHeight);
    }

    private void positionCircleTiles(double initialHeight) {
        double width = gameBoard.getWidth();
        double height = gameBoard.getHeight();
        double centerX = width / 2;
        double centerY = height / 2;

        double boundingBoxSizeGrow = Math.min(width, height) / initialHeight;
        boundingBoxSizeGrow = Math.min(boundingBoxSizeGrow, 1.5);

        double numberOfCirclesGrow = Math.max(tiles.size() / 12, 1);
        numberOfCirclesGrow = Math.min(numberOfCirclesGrow, 1.5);

        double radius = GameSceneController.BASE_TILE_RADIUS * boundingBoxSizeGrow * numberOfCirclesGrow;
        radius = Math.min(radius, Math.min(width * 2, height / 3));

        for (int i = 0; i < tileOrder.size(); i++) {
            int finalI = i;
            TileView tile = tiles.stream().filter(t -> tileOrder.get(finalI).equals(t.getNumber())).toList().getFirst(); // TODO: this has to be replaced

            if (i == 0) {
                tile.setCenterX(centerX);
                tile.setCenterY(centerY);
            } else {
                double angle = 2 * Math.PI * i / (tiles.size() - 1);
                double x = centerX + radius * Math.cos(angle);
                double y = centerY + radius * Math.sin(angle);
                tile.setCenterX(x);
                tile.setCenterY(y);
            }
        }
        System.out.println(game.isGameOver());
    }
}
