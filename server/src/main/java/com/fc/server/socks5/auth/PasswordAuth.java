package com.fc.server.socks5.auth;

public interface PasswordAuth {

    public boolean auth(String user, String password);

}