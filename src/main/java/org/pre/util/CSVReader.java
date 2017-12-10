package org.pre.util;

import javafx.collections.FXCollections;
import org.pre.pojo.Data;
import org.springframework.expression.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.List;

public class CSVReader {
    public static List<Data> getDataListFromCsv(String path, Integer underlying_id) throws Exception {
        String FieldDelimiter = ";";
        List<Data> list = FXCollections.observableArrayList();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(path));
            br.readLine(); // this will read the first line
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(FieldDelimiter, -1);
                list.add(new Data(underlying_id, LocalDate.parse(fields[0], DateUtils.getCustomizedDateFormat()), Double.parseDouble(fields[1])));
            }

        } catch (FileNotFoundException | DateTimeParseException | NumberFormatException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
        // Sort old to new
        list.sort(Comparator.comparing(Data::getRateDate));


        return list;
    }
}
