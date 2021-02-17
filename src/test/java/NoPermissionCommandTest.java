import com.hybris.web.command.common.NoPermissionCommand;
import com.hybris.web.page.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.NO_PERMISSION_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NoPermissionCommandTest {

    @InjectMocks
    private NoPermissionCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnNoPermissionPage() {
        Page result = instance.perform(request);

        assertThat(result.getUrl()).isEqualTo(NO_PERMISSION_PAGE);
        assertThat(result.isRedirect()).isFalse();
    }
}