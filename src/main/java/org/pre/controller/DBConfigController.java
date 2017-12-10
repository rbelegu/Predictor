package org.pre.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.dialog.CommandLinksDialog;
import org.pre.dao.preferences.MySqlPreferences;

import java.util.Objects;

public class DBConfigController {
    @FXML private TextField dbURLTextField;
    @FXML private TextField dbUserTextField;
    @FXML private TextField dbPasswordTextField;
    @FXML private javafx.scene.control.Button saveDBBtn;
    @FXML private javafx.scene.control.Button cancelDBBtn;
    private MySqlPreferences mySqlPreferences;

    public void initialize() {
        mySqlPreferences = new MySqlPreferences();
        dbURLTextField.setText(mySqlPreferences.getdbURL());
        dbUserTextField.setText(mySqlPreferences.getdbUser());
        dbPasswordTextField.setText(mySqlPreferences.getdbPassword());
    }

    public void SaveDBData(ActionEvent actionEvent) {
        // Prüfen ob sicher Parameter geändert hatte
        if(!Objects.equals(mySqlPreferences.getdbURL(), dbURLTextField.getText()) || !Objects.equals(mySqlPreferences.getdbUser(), dbUserTextField.getText()) ||
                !Objects.equals(mySqlPreferences.getdbPassword(), dbPasswordTextField.getText())){
            MySqlPreferences.setdbURL(dbURLTextField.getText());
            MySqlPreferences.setdbUser(dbUserTextField.getText());
            MySqlPreferences.setdbPassword(dbPasswordTextField.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("Information");
            alert.setHeaderText("Changes will take effect after a restart");
            alert.showAndWait();
        }
        // get a handle to the stage
        Stage stage = (Stage) saveDBBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    public void CancelDBData(ActionEvent actionEvent) {
        // get a handle to the stage
        Stage stage = (Stage) cancelDBBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
