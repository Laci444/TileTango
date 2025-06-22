package com.unideb.inf.tiletango.frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class GUIFrontend extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.minHeightProperty().setValue(400);
        stage.minWidthProperty().setValue(600);

        GameConfiguration gameConfig = new GameConfiguration(6);
        GameSceneController gameSceneController = new GameSceneController(gameConfig);
        Scene gameScene = gameSceneController.getGameScene();

        stage.setScene(gameScene);
        stage.setTitle("TileTango");
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResource("/icons/TileTango logo dots cropped.PNG")).toExternalForm()));

        stage.show();

        gameSceneController.bindCircles();
    }
}
