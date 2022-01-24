package org.lab2.dao.daoImpl;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.lab2.dao.DAOConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

@PropertySource("classpath:datasource-cfg.properties")
@Component("dao")
@Scope(value = "singleton")
public class DAOConnectionImpl implements DAOConnection {

    private static Logger logger = Logger.getLogger(DAOConnectionImpl.class);

    private int count;

    private ResultSet resultSet;
    private PreparedStatement statement;

    private Context context;
    private Connection connection;
    private DataSource dataSource;

    private static String databaseDriver;
    private static String contextData;
    private static String provideUrl;

    @Autowired
    public void setDatabaseDriver(@Value("${ds.database-driver}") String databaseDriver) {
        DAOConnectionImpl.databaseDriver = databaseDriver;
    }

    @Autowired
    public void setContextDataName(@Value("${ds.contextData}") String contextData) {
        DAOConnectionImpl.contextData = contextData;
    }

    @Autowired
    public void setProvideUrl(@Value("${ds.url}") String provideUrl) {
        DAOConnectionImpl.provideUrl = provideUrl;
    }

    @Override
    public DataSource getDataSource() {
        try {
            connect();
            return dataSource;
        } finally {
            try {
                connection.close();
                context.close();
            } catch (SQLException | NamingException e) {
                logger.error("getDataSource() disconnection error ", e);
            }
        }
    }

    @Override
    public Connection getConnection() {
        connect();
        return connection;
    }

    @Override
    public void connect() {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, databaseDriver);
            hashtable.put(Context.PROVIDER_URL, provideUrl);
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup(contextData);
            connection = dataSource.getConnection();
            logger.debug("connection is ok");
        } catch (SQLException | NamingException e) {
            logger.error("connect(): connection error ", e);
        }
    }

    @PostConstruct
    public void dataCreate() throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement("select count(1) as cnt from user_tables where table_name like 'LAB2%'");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }
//            if (count == 0) {
                File drop = ResourceUtils.getFile("classpath:dbDrop.sql");
                File create = ResourceUtils.getFile("classpath:dbScript.sql");
                File insert = ResourceUtils.getFile("classpath:dbInsert.sql");
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setStopOnError(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(drop)));
                scriptRunner.runScript(new BufferedReader(new FileReader(create)));
                scriptRunner.runScript(new BufferedReader(new FileReader(insert)));
//            }
        } catch (NullPointerException | IOException e) {
            logger.error("Error in create database ", e);
        } finally {
            try {
                connection.close();
                context.close();
            } catch (NamingException | SQLException e) {
                logger.error("Error in create database ", e);
            }
        }
    }
}
