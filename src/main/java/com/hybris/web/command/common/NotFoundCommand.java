package com.hybris.web.command.common;

import com.hybris.web.command.Command;
import com.hybris.web.page.Page;

import javax.servlet.http.HttpServletRequest;

import static com.hybris.constant.PageConstants.NOT_FOUND_PAGE;

public class NotFoundCommand implements Command {
    @Override
    public Page perform(HttpServletRequest request) {
        return new Page(NOT_FOUND_PAGE);
    }
}
