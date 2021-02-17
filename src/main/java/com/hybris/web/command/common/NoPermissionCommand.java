package com.hybris.web.command.common;

import com.hybris.web.command.Command;
import com.hybris.web.page.Page;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.NO_PERMISSION_PAGE;

public class NoPermissionCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(NO_PERMISSION_PAGE);
    }
}
