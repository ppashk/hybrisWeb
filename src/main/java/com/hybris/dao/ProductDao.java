package com.hybris.dao;

import com.hybris.entity.Product;
import com.hybris.enums.ProductStatus;
import org.apache.log4j.Logger;

import java.util.List;

public class ProductDao extends AbstractDao<Product> {
    private static final Logger LOG = Logger.getLogger(ProductDao.class);

    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String STATUS = "status";
    private static final String CREATED_AT = "created_at";

    private static final String SELECT_ALL = "SELECT * FROM product";
    private static final String SELECT_ALL_ORDERED = "SELECT DISTINCT product.id, product.name, product.price, product.status, product.created_at  FROM product INNER JOIN order_product ON order_product.product_id = product.id GROUP BY order_product.product_id ORDER BY count(*) desc, order_product.id, order_product.product_id desc";
    private static final String SELECT_ALL_BY_ORDER_ID = "SELECT product.id, product.name, product.price, product.status, product.created_at FROM product INNER JOIN order_product ON order_product.product_id = product.id WHERE order_product.order_id = ?";

    private static final String INSERT_INTO = "INSERT INTO product ("
            + NAME + ", "
            + PRICE + ", "
            + STATUS + ", "
            + CREATED_AT + ") VALUE (?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE product SET "
            + NAME + "= ?, "
            + PRICE + "= ?, "
            + STATUS + "= ?, "
            + CREATED_AT + "= ? WHERE "
            + ID + " = ?";

    private static final String DELETE = "DELETE FROM product "
            + "WHERE " + ID + " = ?";

    private EntityMapper<Product> getMapper() {
        return resultSet -> new Product(resultSet.getInt(ID),
                resultSet.getString(NAME),
                resultSet.getInt(PRICE),
                ProductStatus.valueOf(resultSet.getString(STATUS)),
                resultSet.getTimestamp(CREATED_AT));
    }

    @Override
    public Product getById(int id) {
        return getElement("SELECT * FROM product WHERE id = ?",
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public List<Product> getAll(){
        return getElements(SELECT_ALL,
                getMapper());
    }

    @Override
    public List<Product> getAll(boolean ordered) {
        return getElements(SELECT_ALL_ORDERED,
                getMapper());
    }

    @Override
    public List<Product> getAll(String orderId) {
        int id = Integer.parseInt(orderId);
        return getElements(SELECT_ALL_BY_ORDER_ID,
                ps -> ps.setInt(1, id),
                getMapper());
    }

    @Override
    public boolean create(Product entity) {
        LOG.debug("Create product: + " + entity);
        return createUpdate(INSERT_INTO, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setString(3, entity.getStatus().toString());
            ps.setTimestamp(4, entity.getCreatedAt());
        });
    }

    @Override
    public boolean update(Product entity) {
        LOG.debug("Update product: " + entity);
        return createUpdate(UPDATE, ps -> {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPrice());
            ps.setString(3, entity.getStatus().toString());
            ps.setTimestamp(4, entity.getCreatedAt());
            ps.setInt(5, entity.getId());
        });
    }

    @Override
    public boolean remove(Product entity){
        LOG.debug("Delete product: " + entity);
        return createUpdate(DELETE, ps -> ps.setInt(1, entity.getId()));
    }

    @Override
    public boolean query(String query) {
        LOG.debug("Executed query: " + query);
        return createUpdate(query);
    }
}
