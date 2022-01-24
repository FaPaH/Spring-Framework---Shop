package com.test.dao;

import javax.sql.DataSource;
import java.sql.Connection;

public interface DAOConnection {

    DataSource getDataSource();

    Connection getConnection();

    void connect();
}
