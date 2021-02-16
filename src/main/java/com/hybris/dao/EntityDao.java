package com.hybris.dao;

import java.util.List;

public interface EntityDao<T> {
    T getById(int id);

    List<T> getAll();
    List<T> getAll(boolean ordered);
    List<T> getAll(String orderId);


    boolean create(T entity);
    
    boolean update(T entity);
    
    boolean remove(T entity);

    boolean query(String query);
}
