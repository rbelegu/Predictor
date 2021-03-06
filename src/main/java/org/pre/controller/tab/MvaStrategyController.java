package org.pre.controller.tab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.pre.strategy_model.Strategies;
import org.pre.model.DataSetModel;
import org.pre.model.StrategyModel;
import org.pre.pojo.DataSet;
import org.pre.pojo.Strategy;
import org.pre.util.ProgressStatus;

import java.time.LocalDateTime;
import java.util.Objects;


public class MvaStrategyController {

    @FXML
    private TextField mvaMinField;

    @FXML
    private TextField mvaMaxField;

    @FXML
    private Button createMvaStrategiesBtn;


    private final DataSetModel dataSetModel;
    private final StrategyModel strategyModel;


    public MvaStrategyController(DataSetModel dataSetModel, StrategyModel strategyModel){
        this.dataSetModel = dataSetModel;
        this.strategyModel = strategyModel;
    }

    public void initialize() {
        // Button ist nur Aktive wenn die Felder auch ausgefüllt sind!
        createMvaStrategiesBtn.disableProperty().bind(
                mvaMinField.textProperty().isEmpty()
                        .or(mvaMaxField.textProperty().isEmpty()));
        // MVA Min Field
        mvaMinField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mvaMinField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // MVA Max Field
        mvaMaxField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                mvaMaxField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });


        // MVA Max Field
        mvaMaxField.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                // Your code
                if (!mvaMaxField.getText().isEmpty()){
                    if (Integer.parseInt(mvaMaxField.getText())<1) {
                      mvaMaxField.setText("");
                 }   else  if(!mvaMinField.getText().isEmpty()){
                            if(Integer.parseInt(mvaMaxField.getText())< Integer.parseInt(mvaMinField.getText())) {
                        mvaMinField.setText("");
                 }}
            }   }
        });

        // MVA Min Field
        mvaMinField.focusedProperty().addListener((ov, oldV, newV) -> {
            if (!newV) { // focus lost
                // Your code
                if (!mvaMinField.getText().isEmpty()){
                    if (Integer.parseInt(mvaMinField.getText())<1) {
                        mvaMinField.setText("");
                    }   else  if(!mvaMaxField.getText().isEmpty()){
                        if(Integer.parseInt(mvaMaxField.getText())< Integer.parseInt(mvaMinField.getText())) {
                            mvaMaxField.setText("");
                        }}
                }   }
        });


    }

    @FXML
    private void CreateMvaStrategies(ActionEvent event) {
        String userInfo = "";
        int userInfoCounter = 0;
        String newLine = System.getProperty("line.separator");
        for (DataSet item : dataSetModel.getDataSetList()){
            if(item.isChecked() && item.getStatus().equals(ProgressStatus.DONE.name())){
                System.out.println(item.getUnderlying());
                Strategy strategy = new Strategy();
                strategy.setUnderlying(item.getUnderlying());
                strategy.setDataSet_id(item.getId());
                strategy.setFromDate(item.getFromDate());
                strategy.setToDate(item.getToDate());
                strategy.setType(Strategies.MVA.toString());
                strategy.setParameter(mvaMinField.getText() + ";" + mvaMaxField.getText());
                strategy.setTimestamp(LocalDateTime.now());
                strategyModel.addStrategy(strategy);
            } else if(item.isChecked()){
                userInfo = userInfo + item.getUnderlying() + newLine;}
                userInfoCounter += 1;
        }
        if (userInfo != ""){
            Notifications.create().title("Status of the following DataSet(s) is not DONE").text(userInfo).hideAfter(Duration.minutes(1)).showInformation();
        }else if(userInfoCounter == 0){
            Notifications.create().title("No checked Data Set").text("You have to check a valid Data Set with Status DONE").hideAfter(Duration.minutes(1)).showInformation();}
    }


}
