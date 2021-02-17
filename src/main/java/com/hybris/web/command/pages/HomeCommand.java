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
                if (service.createProduct(request.getParameter("name"),
                        request.getParameter("price"),
                        request.getParameter("statusType")))
                    return new Page(HOME_REDIRECT, true);
                break;
            case "removeProduct":
                if (service.removeProduct(request.getParameter("productId")))
                    return new Page(HOME_REDIRECT, true);
                break;
            case "removeAllProducts":
                if (service.removeAllProducts(request.getParameter("password")))
                    return new Page(HOME_REDIRECT, true);
                break;
            case "createOrder":
                if (service.createOrder(request.getParameterValues("productIds")))
                    return new Page(HOME_REDIRECT, true);
                break;
            case "searchOrder":
                request.getSession().setAttribute("orderById", service.getOrderById(request.getParameter("orderId")));
                request.getSession().setAttribute("totalPrice", service.getTotalPrice(request.getParameter("orderId")));
                request.getSession().setAttribute("productsByOrderId", service.getProductsById(request.getParameter("orderId")));
                return new Page(HOME_REDIRECT, true);
            case "updateOrder":
                if (service.updateOrder(request.getParameter("orderId"),
                        request.getParameter("userId"),
                        request.getParameter("status"),
                        request.getParameter("created_at")))
                    return new Page(HOME_REDIRECT, true);
                break;
            default:
                return new Page(HOME_REDIRECT, true);
        }
        return new Page(HOME_REDIRECT, true);
    }
}
