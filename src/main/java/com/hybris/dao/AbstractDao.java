package com.hybris.dao;

import com.hybris.persistance.DataSourceConnectionPool;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements EntityDao<T> {
    private static final Logger LOG = Logger.getLogger(AbstractDao.class);

    protected static final String ID = "id";

    public T getElement(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper){
        T result = null;

        try (PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    result = mapper.map(resultSet);
                }
            }

            return result;
        } catch (SQLException e) {
            LOG.error("Exception while getting entity", e);
        }



        return result;
    }

    public List<T> getElements(String query, StatementMapper<T> statementMapper, EntityMapper<T> mapper){
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T entity = mapper.map(resultSet);

                    result.add(entity);
                }
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting entities", e);
        }

        return result;
    }

    public List<T> getElements(String query, EntityMapper<T> mapper){
        List<T> result = new ArrayList<>();

        try (PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);

                result.add(entity);
            }
        } catch (SQLException e) {
            LOG.error("Exception while getting entities", e);
        }

        return result;
    }
    
    public boolean createUpdate(String query, StatementMapper<T> statementMapper){
        try(PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query)) {
            statementMapper.map(preparedStatement);

            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            LOG.error("Could not create entity!!", e);
        }

        return false;
    }

    public boolean createUpdate(String query) {
        try(PreparedStatement preparedStatement = DataSourceConnectionPool.getPreparedStatement(query)) {
            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            LOG.error("Could not create entity!!", e);
        }

        return false;
    }
}

