package org.lab2.dao.daoImpl;

import org.apache.log4j.Logger;
import org.lab2.dao.DAOConnection;
import org.lab2.dao.DAOUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("singleton")
public class DAOUserImpl implements DAOUser {

    private static Logger logger = Logger.getLogger(DAOUserImpl.class);

    private DAOConnection daoConnection;

    private ResultSet resultSet;
    private PreparedStatement statement;

    @Autowired
    public void setDaoConnection(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }

    @Override
    public int getUserIdByLogin(String login) {
        int userId = 0;
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("SELECT USER_ID FROM LAB2_USERS WHERE USERNAME = ?");
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            resultSet.next();
            userId = resultSet.getInt("USER_ID");
        } catch (SQLException e) {
            logger.error("SQLException in addDelivery() ", e);
        } finally {
            close();
        }
        return userId;
    }

    private void close() {
        try {
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            logger.error("error in close() ", e);
        }
    }
}
