package org.pre.controller.util;


import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import java.time.DayOfWeek;
import java.time.LocalDate;
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



// Kreieren die Restriktion, so dass Daten in der zukunft selektiert werden können
    public static Callback<DatePicker, DateCell> getDatePickerRestriction() {
        Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
// Deaktiviert alle Daten welche in der Zukunft liegen
                        if (item.isAfter(LocalDate.now())) {
                            this.setDisable(true);
                        }

// Zeigt die Wocheende in Blauer Farbe
                        DayOfWeek day = DayOfWeek.from(item);
                        if (day == DayOfWeek.SATURDAY ||
                                day == DayOfWeek.SUNDAY) {
                            this.setTextFill(Color.BLUE);
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
}
