package com.hybris.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface StatementMapper<T>{
    void map(PreparedStatement ps) throws SQLException;
}
