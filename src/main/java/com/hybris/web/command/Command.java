package com.hybris.web.command;

import com.hybris.web.page.Page;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    Page perform(HttpServletRequest request);
}
