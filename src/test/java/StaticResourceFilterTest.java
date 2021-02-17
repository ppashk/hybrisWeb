import com.hybris.web.filter.StaticResourceFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StaticResourceFilterTest {

    private static final String CONTEXT_PATH = "/context";
    private static final String APP_PATH = "/app/someCommand";
    private static final String STATIC_PATH = CONTEXT_PATH + "/static/file.exe";
    private static final String RESOURCE_PATH = CONTEXT_PATH + "/resources/somefile.ext";

    @InjectMocks
    private StaticResourceFilter instance;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() {
        when(request.getContextPath()).thenReturn(CONTEXT_PATH);
    }

    @Test
    public void shouldSkipWhenResource() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(RESOURCE_PATH);

        instance.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }

    @Test
    public void shouldSkipWhenStaticResource() throws IOException, ServletException {
        when(request.getRequestURI()).thenReturn(STATIC_PATH);

        instance.doFilter(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
    }
}