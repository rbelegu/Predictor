package org.pre.controller.tab;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.controlsfx.control.table.TableFilter;



public class ResultAnalyserController {


    @FXML
    private TableView resultTable;

    @FXML
    public void initialize() {
        TableFilter filter = new TableFilter(resultTable);

    }
}
