import com.hybris.web.command.common.NotFoundCommand;
import com.hybris.web.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.NOT_FOUND_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NotFoundCommandTest {

    @InjectMocks
    private NotFoundCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnNotFoundPage() {
        Page result = instance.perform(request);

        assertThat(result.getUrl()).isEqualTo(NOT_FOUND_PAGE);
        assertThat(result.isRedirect()).isFalse();
    }
}