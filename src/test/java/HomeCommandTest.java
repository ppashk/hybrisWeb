import com.hybris.web.command.pages.HomeCommand;
import com.hybris.web.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.HOME_REDIRECT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeCommandTest {


    @InjectMocks
    private HomeCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnHomePage() {
        when(request.getParameter("action")).thenReturn("action");
        Page result = instance.perform(request);

        assertThat(result.getUrl()).isEqualTo(HOME_REDIRECT);
        assertThat(result.isRedirect()).isTrue();
    }
}