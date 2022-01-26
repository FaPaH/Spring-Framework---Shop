package org.lab2.service;

public interface UserServiceUser {

    int getUserIdByLogin(String login);

    boolean isUserExist(String login);

    void createUser(String login, String password);
}
