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
        int userId = -1;
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("select USER_ID from LAB2_USERS where USERNAME = ?");
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("USER_ID");
                System.out.println(userId);
                return userId;
            }
        } catch (SQLException e) {
            logger.error("SQLException in getUserIdByLogin() ", e);
        } finally {
            close();
        }
        return userId;
    }

    @Override
    public void createUser(String login, String password){
        try (Connection connection = daoConnection.getConnection()){
            statement = connection.prepareStatement("INSERT INTO LAB2_USERS(USER_ID, USERNAME, PASSWORD, ROLE_ID, ENABLE) " +
                    "VALUES (LAB2_USERS_SEQ.nextval, ?, ?, 2, 1)");
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            logger.error("SQLException in createUser() ", e);
        } finally {
            close();
        }
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
