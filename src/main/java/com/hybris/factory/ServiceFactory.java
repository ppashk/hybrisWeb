package com.hybris.factory;

import com.hybris.service.Service;

public class ServiceFactory {
    private static Service service = new Service();

    private ServiceFactory() {
    }

    public static Service getService() {
        return service;
    }
}
