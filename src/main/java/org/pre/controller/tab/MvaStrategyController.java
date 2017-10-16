package org.pre.controller.tab;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.controlsfx.control.CheckListView;
import org.controlsfx.control.ListSelectionView;

import java.util.List;


public class MvaStrategyController {
    public ObservableList<String> strings;

    @FXML
    CheckListView<String> DataSetsComboBox;

    @FXML
    ListSelectionView<String> Test;

    @FXML
    private void CreateMvaStrategies(ActionEvent event)
    {
        System.out.println("lollolol");
        strings.remove("Item 19");
    }

    public MvaStrategyController(){
        strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 20; i++) {
            strings.add("EURCHF=X;16.05.2017;17.11.20" + i);
        }
    }
    public void initialize(){
        // create the data to show in the CheckComboBox
        strings.addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                while (c.next()) {
                    if (c.wasAdded()) {
                        List<? extends String> asl = c.getAddedSubList();
                        for (String s : asl) {
                            System.out.println("Neuer inhalt" + s);
                            Test.getSourceItems().add(s);
                            //permutate
                        }
                    }
                    if (c.wasRemoved()) {
                        List<? extends String> removed = c.getRemoved();
                        for (String s : removed) {
                            System.out.println("Neuer inhalt" + s);
                            Test.getSourceItems().remove(s);
                            Test.getTargetItems().remove(s);
                            //permutate
                        }
                    }
            }}
        });

        // Create the CheckComboBox with the data
        DataSetsComboBox.getItems().addAll(strings);
        // and listen to the relevant events (e.g. when the selected indices or
        // selected items change).
        DataSetsComboBox.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(DataSetsComboBox.getCheckModel().getCheckedItems());
            }
        });

        // Create the CheckComboBox with the data
        Test.getSourceItems().addAll(strings);
        // and listen to the relevant events (e.g. when the selected indices or
        // selected items change).
        Test.getTargetItems().addListener(new ListChangeListener<String>() {
            public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(Test.getTargetItems());
            }
        });
    }


}
