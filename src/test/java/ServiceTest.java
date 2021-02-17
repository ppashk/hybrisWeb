import com.hybris.dao.EntityDao;
import com.hybris.entity.Order;
import com.hybris.entity.Product;
import com.hybris.service.Service;
import com.hybris.web.filter.StaticResourceFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {
    private static final String ORDER_USER_ID = "1";
    private static final String ORDER_STATUS = "order status";
    private static final String ORDER_CREATED_AT = "order created at";
    private static final String INVALID_LOGIN = "invalid login";

    @InjectMocks
    private Service instance;
    @Mock
    private Order order;
    @Mock
    private EntityDao<Order> orderDao;
    @Mock
    private Product product;
    @Mock
    private EntityDao<Product> productDao;


    @Before
    public void setUp() {

    }
}