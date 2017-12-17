package org.pre;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main extends Application {
    private static AnnotationConfigApplicationContext applicationContext;

    public static AnnotationConfigApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void init() {
        try {
            applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        }catch (Exception e){

        };

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/pre/view/main.fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        try{
	        Parent root = loader.load();
	        primaryStage.setTitle("FX Predictor");
	        primaryStage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
	        primaryStage.setScene(new Scene(root, 1200, 700));
	        primaryStage.show();
	        primaryStage.setOnHidden(e -> Platform.exit());
        }catch(Exception e){
        	//TODO Hier Fehlermeldung Adden mit mehr Informationen zur Konfiguration. 
        	Text text = new Text("Es ist ein Problem mit MySQL aufgetreten blabla " + e.toString());
        	Pane root = new VBox(10,text);
        	Scene scene = new Scene(root);
        	primaryStage.setScene(scene);
        	primaryStage.show();
        }
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
