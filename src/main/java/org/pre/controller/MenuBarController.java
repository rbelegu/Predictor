package org.pre.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class MenuBarController {
	
	public MenuBarController(){
		System.out.println("mbc");
	}
	
    @FXML
    private void handleClose(){
        Platform.exit();
    }

    @FXML
    private void handleDB() {
        try {
        	Parent root1;
        	System.out.println("start");
        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/pre/view/DBConfig.fxml"));
            root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("DB Configruation");
            stage.getIcons().add(new Image("/org/pre/view/pics/icon_fxpredictor.png"));
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }catch(Exception e) {
            e.printStackTrace();
		}
	}

    @FXML
    private void handleAbout() {
    	System.out.println("h1");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setGraphic(new ImageView(this.getClass().getResource("/org/pre/view/pics/icon_fxpredictor.png").toString()));
        alert.setHeaderText("FX Predictor V 1.0");
        alert.setContentText("by Nino Hafen, Rinon Belegu, Dimitrios Tsichlakis!" + "\n" + "\n" +
        "24h Support - Call +41 79 288 93 44");
        // Get the Stage.
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        // Add a custom icon.
        stage.getIcons().add(new Image(this.getClass().getResource("/org/pre/view/pics/icon_fxpredictor.png").toString()));
        alert.showAndWait();
    }


}
