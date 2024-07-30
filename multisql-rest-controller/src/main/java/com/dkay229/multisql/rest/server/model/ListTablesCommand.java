package com.dkay229.multisql.rest.server.model;

public class ListTablesCommand {
    private String glob;

    public ListTablesCommand(String glob) {
        this.glob = glob;
    }

    public String getGlob() {
        return glob;
    }

    public void setGlob(String glob) {
        this.glob = glob;
    }
}
