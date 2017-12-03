package org.pre;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Preloader extends javafx.application.Preloader {
    private Stage preloaderStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.preloaderStage = primaryStage;
        VBox loading = new VBox();
        loading.getChildren().add(new ImageView(new Image("/org/pre/view/pics/logo_fxpredictor.png")));
        loading.getChildren().add(new ImageView(new Image("/org/pre/view/pics/loader.gif")));
        loading.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane(loading);
        Scene scene = new Scene(root);

        primaryStage.setTitle("FX Predictor");
        primaryStage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification stateChangeNotification) {
        if (stateChangeNotification.getType() == StateChangeNotification.Type.BEFORE_START) {
            preloaderStage.close();
        }
    }
}
