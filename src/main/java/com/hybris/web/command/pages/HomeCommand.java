package com.hybris.web.command.pages;

import com.hybris.factory.ServiceFactory;
import com.hybris.web.command.MultipleMethodCommand;
import com.hybris.web.page.Page;
import com.hybris.service.Service;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.HOME_PAGE;
import static com.hybris.constant.PageConstants.HOME_REDIRECT;

public class HomeCommand extends MultipleMethodCommand {
    private final Service service;

    public HomeCommand() {
        this.service = ServiceFactory.getService();
    }

    @Override
    protected Page performGet(HttpServletRequest request) {
        request.setAttribute("productStatuses", service.productStatuses());
        request.setAttribute("products", service.getAllProducts());
        request.setAttribute("availableProducts", service.getAllAvailableProducts());
        request.setAttribute("topOrderedProducts", service.getTopOrderedProducts());
        request.setAttribute("orders", service.getAllOrders());
        return new Page(HOME_PAGE);
    }

    @Override
    protected Page performPost(HttpServletRequest request) {
        String action = request.getParameter("action");
        switch (action) {
            case "createProduct":
                service.createProduct(request.getParameter("name"), request.getParameter("price"), request.getParameter("statusType"));
                break;
            case "removeProduct":
                service.removeProduct(request.getParameter("productId"));
                break;
            case "removeAllProduct":
                service.removeAllProducts(request.getParameter("password"));
                break;
            case "createOrder":
                service.createOrder(request.getParameterValues("productIds"));
                break;
            case "searchOrder":
                request.getSession().setAttribute("orderById", service.getOrderById(request.getParameter("orderId")));
                request.getSession().setAttribute("totalPrice", service.getTotalPrice(request.getParameter("orderId")));
                request.getSession().setAttribute("productsByOrderId", service.getProductsById(request.getParameter("orderId")));
                break;
            case "updateOrder":
                service.updateOrder(request.getParameter("orderId"), request.getParameter("userId"), request.getParameter("status"), request.getParameter("created_at"));
                break;
        }
        return new Page(HOME_REDIRECT, true);
    }
}
