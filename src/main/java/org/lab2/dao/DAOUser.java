package org.lab2.dao;

public interface DAOUser {

    int getUserIdByLogin(String login);

    void createUser(String login, String password);
}
