package org.lab2.service;

import org.apache.log4j.Logger;
import org.lab2.dao.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceUserImpl implements UserServiceUser{

    private static Logger logger = Logger.getLogger(UserServiceUserImpl.class);

    private DAOUser daoUser;

    @Autowired
    public void setDaoUser(DAOUser daoUser) {
        this.daoUser = daoUser;
    }

    @Override
    public int getUserIdByLogin(String login) {
        logger.debug("called getUserIdByLogin() method. UserServiceImpl.class");
        return daoUser.getUserIdByLogin(login);
    }

    @Override
    public boolean isUserExist(String login){
        logger.debug("called isUserExist() method. UserServiceImpl.class");
        return daoUser.getUserIdByLogin(login) == -1;
    }

    @Override
    public void createUser(String login, String password){
        logger.debug("called createUser() method. UserServiceImpl.class");
        daoUser.createUser(login,password);
    }
}
