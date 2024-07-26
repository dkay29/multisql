package com.dkay229.multisql.rest.server.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Table {
    private List<String> columnNames = new ArrayList<>();
    private List<List<String>> rows;

    public Table(String... columnNames) {
        this.columnNames = new ArrayList<>(Arrays.asList(columnNames));
    }
    public List<String> getColumnNames() {
        return columnNames;
    }
    public void addRow(String ...values) {
        rows.add(new ArrayList<>(Arrays.asList(values)));
    }
}
