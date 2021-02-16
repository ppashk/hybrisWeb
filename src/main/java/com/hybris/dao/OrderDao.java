package com.hybris.dao;

import com.hybris.entity.Order;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderDao extends AbstractDao<Order> {
    private static final Logger LOG = Logger.getLogger(OrderDao.class);

    private static final String USER_ID = "user_id";
    private static final String STATUS = "status";
    private static final String CREATED_AT = "created_at";

    private static final String SELECT_ALL = "SELECT * FROM orders";

    private static final String INSERT_INTO = "INSERT INTO orders ("
            + USER_ID + ", "
            + STATUS + ", "
            + CREATED_AT + ") VALUE (?, ?, ?)";

    private static final String UPDATE = "UPDATE orders SET "
            + USER_ID + "= ?, "
            + STATUS + "= ?, "
            + CREATED_AT + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM orders "
            + "WHERE " + ID + " = ?";

    private EntityMapper<Order> getMapper() {
        return resultSet -> new Order(resultSet.getInt(ID),
                resultSet.getInt(USER_ID),
                resultSet.getString(STATUS),
                resultSet.getString(CREATED_AT));
    }

    @Override
    public Order getById(int id) {
        return getElement("SELECT * FROM orders WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Order> getAll(){
        return getElements(SELECT_ALL,
                getMapper());
    }

    @Override
    public List<Order> getAll(boolean ordered) {
        return null;
    }

    @Override
    public List<Order> getAll(String orderId) {
        return null;
    }

    @Override
    public boolean create(Order entity) {
        LOG.debug("Create order: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setInt(1, entity.getUserId());
            ps.setString(2, entity.getStatus());
            ps.setString(3, entity.getCreatedAt());
        });
    }

    @Override
    public boolean update(Order entity) {
        LOG.debug("Update order: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setInt(1, entity.getUserId());
            ps.setString(2, entity.getStatus());
            ps.setString(3, entity.getCreatedAt());
            ps.setInt(4, entity.getId());
        });
    }

    @Override
    public boolean remove(Order entity){
        LOG.debug("Delete order: " + entity);
        return createUpdate(DELETE,
                ps -> ps.setInt(1, entity.getId()));
    }

    @Override
    public boolean query(String query) {
        LOG.debug("Executed query: " + query);
        return createUpdate(query);
    }
}
