package com.dkay229.multisql.rest.server.model;

public class SqlStatement {
    String sqlText;

    public void setSqlText(String sqlText) {
        this.sqlText = sqlText;
    }

    public String getSqlText() {
        return sqlText;
    }

    public SqlStatement(String sqlText) {
        this.sqlText = sqlText;
    }
}
