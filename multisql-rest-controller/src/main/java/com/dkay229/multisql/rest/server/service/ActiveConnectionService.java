package com.dkay229.multisql.rest.server.service;

import com.dkay229.multisql.rest.server.entity.ClientConnection;

import java.util.HashMap;

public class ActiveConnectionService {
    private HashMap<String, ClientConnection> activeConnections;

    public ClientConnection addConnection (ClientConnection clientConnection) {
        activeConnections.putIfAbsent(clientConnection.getConnectionId(),clientConnection);
        return activeConnections.get(clientConnection.getConnectionId());
    }

    public ClientConnection getByConnectionId(String connectionId) {
        if (!activeConnections.containsKey(connectionId)) {
            throw new RuntimeException("not a valid connection");
        }
        return activeConnections.get(connectionId);
    }

}
