package org.pre.controller.util;


import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;



public class CellUtils {


//Generic für Datumsformatierung
    public static <ROW,T extends Temporal> Callback<TableColumn<ROW, T>, TableCell<ROW, T>> getDateCell(DateTimeFormatter format) {
        return column -> new TableCell<ROW, T> () {
            @Override
            protected void updateItem (T item, boolean empty) {
                super.updateItem (item, empty);
                if (item == null || empty) {
                    setText (null);
                }
                else {
                    setText (format.format (item));
                }
            }
        };
    }

}
