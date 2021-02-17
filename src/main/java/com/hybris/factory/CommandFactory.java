package com.hybris.factory;

import com.hybris.web.command.Command;
import com.hybris.web.command.pages.HomeCommand;
import com.hybris.web.command.common.NoPermissionCommand;
import com.hybris.web.command.common.NotFoundCommand;
import com.hybris.web.command.common.ServletErrorCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> getCommandMap = new HashMap<>();
    private static Map<String, Command> postCommandMap = new HashMap<>();
    private static Command defaultCommand = new NotFoundCommand();

    static {
        getCommandMap.put("/404", defaultCommand);
        getCommandMap.put("/403", new NoPermissionCommand());
        getCommandMap.put("/500", new ServletErrorCommand());
        getCommandMap.put("/", new HomeCommand());

        postCommandMap.put("/", new HomeCommand());
    }

    private CommandFactory() {
    }

    public static Command getCommand(String path, String type) {
        return "GET".equals(type)
                ? getCommand(path)
                : postCommand(path);
    }

    private static Command getCommand(String path) {
        return getCommandMap.getOrDefault(path, defaultCommand);
    }

    private static Command postCommand(String path) {
        return postCommandMap.getOrDefault(path, defaultCommand);
    }
}
