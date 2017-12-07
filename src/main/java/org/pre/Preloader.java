package org.pre;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.stage.StageStyle;
import javafx.util.Duration;

import static com.sun.javafx.font.PrismFontFactory.isEmbedded;

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

        primaryStage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            if (isEmbedded && preloaderStage.isShowing()) {
                //fade out, hide stage at the end of animation
                FadeTransition ft = new FadeTransition(
                        Duration.millis(1000), preloaderStage.getScene().getRoot());
                ft.setFromValue(1.0);
                ft.setToValue(0.0);
                final Stage s = preloaderStage;
                EventHandler<ActionEvent> eh = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent t) {
                        s.hide();
                    }
                };
                ft.setOnFinished(eh);
                ft.play();
            } else {
                preloaderStage.hide();
            }
        }
    }
}
