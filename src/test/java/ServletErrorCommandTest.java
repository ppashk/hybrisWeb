import com.hybris.web.command.common.ServletErrorCommand;
import com.hybris.web.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.SERVLET_ERROR;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ServletErrorCommandTest {

    @InjectMocks
    private ServletErrorCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnServletErrorPage() {
        Page result = instance.perform(request);

        assertThat(result.getUrl()).isEqualTo(SERVLET_ERROR);
        assertThat(result.isRedirect()).isFalse();
    }
}