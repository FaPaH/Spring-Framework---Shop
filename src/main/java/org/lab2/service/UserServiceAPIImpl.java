package org.lab2.service;

import org.apache.log4j.Logger;
import org.lab2.dao.daoImpl.DAOAPIConnectionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceAPIImpl implements UserServiceAPI{

    private static Logger logger = Logger.getLogger(UserServiceAPIImpl.class);

    private DAOAPIConnectionImpl daoapiConnection;

    @Autowired
    public void setDaoapiConnection(DAOAPIConnectionImpl daoapiConnection) {
        this.daoapiConnection = daoapiConnection;
    }

    @Override
    public String getMessage(String movieName){
        return daoapiConnection.getMessage(movieName);
    }
}
