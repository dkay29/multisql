package com.dkay229.multisql.rest.server.entity;

import jakarta.persistence.Entity;
import java.security.SecureRandom;
import java.util.Base64;

@Entity
public class ClientConnection {
    MultiUser multiUser;
    String connectionId;
    String jwtToken;

    public ClientConnection(MultiUser multiUser,String jwtToken) {
        this.multiUser = multiUser;
        this.jwtToken = jwtToken;
        this.connectionId = generateBase64Key(32);
    }

    public static String generateBase64Key(int length) {
        byte[] key = new byte[length];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public MultiUser getUser() {
        return multiUser;
    }

    public String getConnectionId() {
        return connectionId;
    }
}
