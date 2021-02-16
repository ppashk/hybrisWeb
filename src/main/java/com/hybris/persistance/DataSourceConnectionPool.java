package com.hybris.persistance;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataSourceConnectionPool {
    private static Logger LOG = Logger.getLogger(DataSourceConnectionPool.class);
    private static DataSource dataSource;

    static {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("local");
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(bundle.getString("url"));
            ds.setUsername(bundle.getString("username"));
            ds.setPassword(bundle.getString("password"));
            ds.setMinIdle(Integer.parseInt(bundle.getString("min.idle")));
            ds.setMaxIdle(Integer.parseInt(bundle.getString("max.idle")));
            ds.setMaxOpenPreparedStatements(Integer.parseInt(bundle.getString("max.open.prepared.statements")));
            ds.setMaxActive(Integer.parseInt(bundle.getString("max.active")));
            dataSource = ds;
        } catch (Exception e) {
            LOG.error("Could not find DataSource JNDI", e);
        }
    }

    private DataSourceConnectionPool() {
    }

    public static Connection getConnection() {

        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            LOG.debug("Connection received " + connection);
        } catch (SQLException e) {
            LOG.error("Some problem was occurred while getting connection to BD", e);
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return getConnection().prepareStatement(query);
    }
}
