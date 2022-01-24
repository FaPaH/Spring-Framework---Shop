package com.test.dao.daoImpl;

import com.test.dao.DAOConnection;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
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
    private DataSource dataSource;
    private Context context;
    private Connection connection;

    private static String contextData;
    private static String jndi;
    private static String providerUrl;

    @Autowired
    public void setJndi(@Value("${lab2.contextFactory}") String jndi) {
        this.jndi = jndi;
    }

    @Autowired
    public void setProviderUrl(@Value("${lab2.providerUrl}") String providerUrl) {
        this.providerUrl = providerUrl;
    }

    @Autowired
    public void setContextData(@Value("${lab2.contextData}") String contextData) {
        this.contextData = contextData;
    }

    @Override
    public void connect() {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(Context.INITIAL_CONTEXT_FACTORY, jndi);
            hashtable.put(Context.PROVIDER_URL, providerUrl);
            context = new InitialContext(hashtable);
            dataSource = (DataSource) context.lookup(contextData);
            connection = dataSource.getConnection();
            logger.debug("connection is ok");
        } catch (SQLException | NamingException e) {
            logger.error("connect(): connection error ", e);
        }
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

    @PostConstruct
    public void dataCreate() throws SQLException {
        try {
            connect();
            statement = connection.prepareStatement("select count(1) as cnt from user_tables where table_name like '%LAB2%'");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("CNT");
            }
            if (count == 0) {
                File drop = ResourceUtils.getFile("classpath:drop.sql");
                File create = ResourceUtils.getFile("classpath:db.sql");
                File insert = ResourceUtils.getFile("classpath:insert.sql");
                ScriptRunner scriptRunner = new ScriptRunner(connection);
                scriptRunner.setStopOnError(false);
                scriptRunner.runScript(new BufferedReader(new FileReader(drop)));
                scriptRunner.runScript(new BufferedReader(new FileReader(create)));
                scriptRunner.runScript(new BufferedReader(new FileReader(insert)));
            }
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
