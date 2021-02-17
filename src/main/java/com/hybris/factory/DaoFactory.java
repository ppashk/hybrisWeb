package com.hybris.factory;

import com.hybris.dao.EntityDao;
import com.hybris.dao.OrderDao;
import com.hybris.dao.ProductDao;
import com.hybris.enums.DaoType;

import java.util.HashMap;
import java.util.Map;

public class DaoFactory {
    private static Map<DaoType, EntityDao> daoMap = new HashMap<>();

    static {
        daoMap.put(DaoType.ORDER, new OrderDao());
        daoMap.put(DaoType.PRODUCT, new ProductDao());
    }

    private DaoFactory() {
    }

    public static EntityDao getEntityDao(DaoType daoType){
        EntityDao entityDao = daoMap.get(daoType);
        if(entityDao != null ){
            return entityDao;
        } throw new RuntimeException("Dao with current dao type do not exist: " + daoType.name());
    }
}
