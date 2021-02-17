package com.hybris.service;

import com.hybris.dao.EntityDao;
import com.hybris.entity.Order;
import com.hybris.entity.Product;
import com.hybris.enums.DaoType;
import com.hybris.enums.ProductStatus;
import com.hybris.factory.DaoFactory;
import org.apache.log4j.Logger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.hybris.enums.ProductStatus.OUT_OF_STOCK;

public class Service {
    private static final Logger LOG = Logger.getLogger(Service.class);
    private EntityDao<Order> orderDao;
    private EntityDao<Product> productDao;

    public Service() {
        this.orderDao = DaoFactory.getEntityDao(DaoType.ORDER);
        this.productDao = DaoFactory.getEntityDao(DaoType.PRODUCT);
    }

    public ProductStatus[] productStatuses() {
        return ProductStatus.values();
    }

    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    public List<Product> getAllAvailableProducts() {
        List<Product> products = productDao.getAll();
        products.removeIf(product -> product.getStatus().equals(OUT_OF_STOCK));

        return products;
    }

    public List<Product> getTopOrderedProducts() {
        return productDao.getAll(true);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAll();
    }

    public Order getOrderById(String stringId) {
        Order order = null;
        try {
            int id = Integer.parseInt(stringId);
            order = orderDao.getById(id);
        } catch (Exception e) {
            LOG.error("Order id: " + stringId);
        }
        return order;
    }

    public Product getProductById(String stringId) {
        Product product = null;
        try {
            int id = Integer.parseInt(stringId);
            product = productDao.getById(id);
        } catch (Exception e) {
            LOG.error("Product id: " + stringId);
        }
        return product;
    }

    public List<Product> getProductsById(String stringId) {
        return productDao.getAll(stringId);
    }

    public int getTotalPrice(String stringId) {
        List<Product> products = productDao.getAll(stringId);
        int price = 0;
        for (Product product : products) {
            price += product.getPrice();
        }
        return price;
    }

    public boolean createProduct(String name, String stringPrice, String stringStatus) {
        Date date = new Date();
        try {
            return productDao.create(new Product(name, Integer.parseInt(stringPrice), ProductStatus.valueOf(stringStatus), new Timestamp(date.getTime())));
        } catch (Exception e) {
            LOG.error("Name: " + name + ", price: " + stringPrice + ", status: " + stringPrice + ", time: " + new Timestamp(date.getTime()));
        }
        return false;
    }

    public boolean createOrder(String[] productIds) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
            LocalDateTime now = LocalDateTime.now();
            List<Order> savedOrders = orderDao.getAll();
            int orderId = 0;
            for (Order savedOrder : savedOrders) {
                if (savedOrder.getId() > orderId)
                    orderId = savedOrder.getId();
            }
            orderId++;
            orderDao.create(new Order(orderId, (int) (Math.random() * 1000), "opened", dtf.format(now)));
            for (String productId : productIds) {
                if (orderId != 0)
                    addProductToOrder(orderId, productId);
            }
            return true;
        } catch (Exception e) {
            LOG.error("Cannot create order");
        }
        return false;
    }

    public boolean updateOrder(String stringId, String userId, String status, String createdAt) {
        try {
            LOG.info("Update: " + stringId + userId + status + createdAt);
            Order order = getOrderById(stringId);
            if (!userId.equals(""))
                order.setUserId(Integer.parseInt(userId));
            if (!status.equals(""))
                order.setStatus(status);
            if (!createdAt.equals(""))
                order.setCreatedAt(createdAt.replace("T", " "));
            return orderDao.update(order);
        } catch (Exception e) {
            LOG.error("Id: " + stringId + ", status: " + status + ", Date: " + createdAt);
        }
        return false;
    }

    public boolean removeProduct(String stringId) {
        try {
            Product product = getProductById(stringId);
            return productDao.remove(product);
        } catch (Exception e) {
            LOG.error("Id: " + stringId);
        }
        return false;
    }

    public boolean removeAllProducts(String password) {
        if (password.equals("1111")) {
            productDao.query("DELETE FROM order_product");
            return productDao.query("DELETE FROM product");
        } else return false;
    }

    private void addProductToOrder(int stringOrderId, String stringProductId) {
        try {
            orderDao.query("INSERT INTO order_product (order_id, product_id) VALUE (" + stringOrderId + ", " + stringProductId + ")");
        } catch (Exception e) {
            LOG.error("OrderId: " + stringOrderId + ", ProductId: " + stringProductId);
        }
    }
}
