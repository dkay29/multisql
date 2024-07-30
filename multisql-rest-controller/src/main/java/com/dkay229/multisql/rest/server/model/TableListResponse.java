package com.dkay229.multisql.rest.server.model;

public class TableListResponse {
    private String[] tableNames;

    public String[] getTableNames() {
        return tableNames;
    }

    public TableListResponse(String[] tableNames) {
        this.tableNames = tableNames;
    }

    public void setTableNames(String[] tableNames) {
        this.tableNames = tableNames;
    }
}
