import com.hybris.web.filter.EncodingFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "text/html; charset=UTF-8";
    private static final String REQUEST_ENCODING = "requestEncoding";
    private static final String ENCODING_FROM_CONFIG = "encoding";

    @InjectMocks
    private EncodingFilter instance;
    @Mock
    private FilterConfig filterConfig;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;

    @Test
    public void shouldPopulateEncodingFromConfig() throws ServletException {
        when(filterConfig.getInitParameter(REQUEST_ENCODING)).thenReturn(ENCODING_FROM_CONFIG);

        instance.init(filterConfig);

        assertThat(instance.getDefaultEncoding()).isEqualTo(ENCODING_FROM_CONFIG);
    }

    @Test
    public void shouldPopulateDefaultEncoding() throws ServletException {
        when(filterConfig.getInitParameter(REQUEST_ENCODING)).thenReturn(null);

        instance.init(filterConfig);

        assertThat(instance.getDefaultEncoding()).isEqualTo(ENCODING_UTF_8);
    }

    @Test
    public void shouldPopulateCharacterEncodingWhenNull() throws ServletException, IOException {
        instance.doFilter(request, response, filterChain);

        verify(request).setCharacterEncoding(null);
    }

    @Test
    public void shouldNotPopulateCharacterEncodingWhenNotNull() throws ServletException, IOException {
        when(filterConfig.getInitParameter(REQUEST_ENCODING)).thenReturn(ENCODING_FROM_CONFIG);
        instance.init(filterConfig);
        when(request.getCharacterEncoding()).thenReturn(ENCODING_FROM_CONFIG);

        instance.doFilter(request, response, filterChain);

        verify(request, never()).setCharacterEncoding(ENCODING_FROM_CONFIG);
    }

    @Test
    public void shouldPopulateResponseTypeEncoding() throws IOException, ServletException {
        when(filterConfig.getInitParameter(REQUEST_ENCODING)).thenReturn(ENCODING_FROM_CONFIG);
        instance.init(filterConfig);
        instance.doFilter(request, response, filterChain);

        verify(response).setContentType(DEFAULT_CONTENT_TYPE);
        verify(response).setCharacterEncoding(ENCODING_FROM_CONFIG);
    }
}