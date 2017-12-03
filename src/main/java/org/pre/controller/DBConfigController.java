package org.pre.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.pre.db.DBPreferences;

public class DBConfigController {
    @FXML private TextField dbURLTextField;
    @FXML private TextField dbUserTextField;
    @FXML private TextField dbPasswordTextField;
    @FXML private javafx.scene.control.Button saveDBBtn;
    @FXML private javafx.scene.control.Button cancelDBBtn;

    public void initialize() {
        DBPreferences dbPreferences = new DBPreferences();
        dbURLTextField.setText(dbPreferences.getdbURL());
        dbUserTextField.setText(dbPreferences.getdbUser());
        dbPasswordTextField.setText(dbPreferences.getdbPassword());
    }

    public void SaveDBData(ActionEvent actionEvent) {
        DBPreferences.setdbURL(dbURLTextField.getText());
        DBPreferences.setdbUser(dbUserTextField.getText());
        DBPreferences.setdbPassword(dbPasswordTextField.getText());
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
