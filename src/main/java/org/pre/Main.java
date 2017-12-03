package org.pre;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private AnnotationConfigApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/pre/view/main.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        Parent root = loader.load();
        primaryStage.setTitle("FX Predictor");
        primaryStage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
        primaryStage.setOnHidden(e -> Platform.exit());
    }

    @Override
    public void stop() {
        if (applicationContext != null) {
            applicationContext.close();
        }
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Main.class, Preloader.class, args);
    }
}
