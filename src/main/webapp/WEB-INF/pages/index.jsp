<!doctype html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hybris</title>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="<c:url value="/static/css/reset.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/bootstrap.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/fonts.css"/>">
    <link rel="stylesheet" href="<c:url value="/static/css/style.css"/>">

</head>
<body>
    <div id="wrapper">
        <main>
            <h1>Product constructor</h1>
            <form method="post">
                <label>
                    <input type="text" name="name" placeholder="name" class="text-input" required>
                </label>
                <label>
                    <input type="number" name="price" placeholder="price" class="text-input" required>
                </label>
                <label>
                    <select name="statusType" required>
                        <option selected disabled>chose status</option>
                        <c:forEach items="${productStatuses}" var="productStatus">
                            <option value="${productStatus}">${productStatus}</option>
                        </c:forEach>
                    </select>
                </label>
                <button type="submit" name="action" value="createProduct" class="button">Create Product</button>
            </form>

            <hr>

            <h1>Product storage</h1>
            <c:choose>
                <c:when test="${not empty products}">
                    <c:forEach items="${products}" var="product">
                        <br>
                        <form method="post">
                            <div>
                                <p>Product id: ${product.id}</p>
                                <p>Product name: ${product.name}</p>
                                <p>Product price: ${product.price}</p>
                                <p>Product status: ${product.status}</p>
                                <p>Product creation time: ${product.createdAt}</p>
                            </div>
                            <label>
                                <input type="number" name="productId" value="${product.id}" hidden>
                            </label>
                            <button type="submit" name="action" value="removeProduct" class="button">Remove Product</button>
                        </form>
                    </c:forEach>
                    <br>
                    <form method="post">
                        <label>
                            <input type="number" name="password" placeholder="input password" class="text-input" required>
                        </label>
                        <button type="submit" name="action" value="removeAllProduct" class="button">Remove All Product</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p>Add new product above</p>
                </c:otherwise>
            </c:choose>

            <hr>

            <h1>Top ordered</h1>
            <c:choose>
                <c:when test="${not empty topOrderedProducts}">
                    <c:forEach items="${topOrderedProducts}" var="product">
                        <br>
                        <div>
                            <p>Product id: ${product.id}</p>
                            <p>Product name: ${product.name}</p>
                            <p>Product status: ${product.status}</p>
                            <p>Product creation time: ${product.createdAt}</p>
                            <p>Order quantities: ${product.createdAt}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>Products have never been ordered</p>
                </c:otherwise>
            </c:choose>


            <hr>

            <h1>Order constructor</h1>
            <c:choose>
                <c:when test="${not empty availableProducts}">
                    <form method="post">
                        <c:forEach items="${availableProducts}" var="product">
                            <input type="checkbox" name="productIds" value="${product.id}">${product.name}<br>
                        </c:forEach>
                        <button type="submit" name="action" value="createOrder" class="button">Create Order</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <p>Add products to storage</p>
                </c:otherwise>
            </c:choose>

            <c:if test="${not empty orders}">
                <hr>

                <h1>Order search</h1>
                <form method="post">
                    <label>
                        <input type="number" name="orderId" placeholder="order id" class="text-input" required>
                    </label>
                    <button type="submit" name="action" value="searchOrder" class="button">Search Order</button>
                </form>

                <c:if test="${not empty orderById}">
                    <div>
                        <p>Order id: ${orderById.id}</p>
                        <p>Creator id: ${orderById.userId}</p>
                        <p>Order status: ${orderById.status}</p>
                        <p>Order creation time: ${orderById.createdAt}</p>
                        <p>Total price: ${totalPrice}</p>
                        <c:forEach items="${productsByOrderId}" var="product">
                            <br>
                            <p>Product name: ${product.name}</p>
                            <p>Product price: ${product.price}</p>
                        </c:forEach>
                    </div>
                </c:if>

                <hr>

                <h1>Order editor</h1>
                <c:forEach items="${orders}" var="order">
                    <form method="post">
                        <div>
                            <p>Order id: ${order.id}</p>
                            <p>Creator id: ${order.userId}</p>
                            <p>Order status: ${order.status}</p>
                            <p>Order creation time: ${order.createdAt}</p>
                        </div>
                        <label>
                            <input type="number" name="orderId" value="${order.id}" hidden>
                        </label>
                        <label>
                            <input type="number" name="userId" placeholder="user id" class="text-input">
                        </label>
                        <label>
                            <input type="text" name="status" placeholder="name" class="text-input">
                        </label>
                        <label>
                            <input type="datetime-local" name="created_at" placeholder="created at" class="text-input">
                        </label>
                        <button type="submit" name="action" value="updateOrder" class="button">Update Order</button>
                    </form>
                </c:forEach>
            </c:if>
        </main>
    </div>
</body>
</html>