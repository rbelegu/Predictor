package org.pre.controller.tab;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import org.pre.model.DataSetModel;
import org.pre.pojo.DataSet;
import org.pre.util.ProgressStatus;


public class MvaStrategyController {

    @FXML
    private TextField mvaMinField;

    @FXML
    private TextField mvaMaxField;

    @FXML
    private Button createMvaStrategiesBtn;


    private final DataSetModel dataSetModel;


    public MvaStrategyController(DataSetModel dataSetModel){
        this.dataSetModel = dataSetModel;
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
        mvaMaxField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    mvaMaxField.setText(newValue.replaceAll("[^\\d]", ""));
                }
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
        for (DataSet item : dataSetModel.getDataSetList()){
            if(item.isChecked() && item.getStatus().equals(ProgressStatus.DONE.name())){
                System.out.println(item.getUnderlying());
            }
            //check the boolean value of each item to determine checkbox state
        }
    }


}
