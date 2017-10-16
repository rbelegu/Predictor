package org.pre.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;


public class MenuBarController {



    @FXML
    private void handleDB(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/pre/view/DBConfig.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("DB Configruation");
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.show();
    }catch(Exception e) {
            e.printStackTrace();
        }    }


    @FXML
    private void handleAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("FX Predictor V 1.0");
        alert.setContentText("by Nino Hafen, Rinon, Dimitrios Tsichlakis!");
        alert.showAndWait();
    }


}
