package org.camunda.emulator.messageHandlers.autoService;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class ProvideOnlineServiceUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject onlineService = new JSONObject();
        onlineService.put("callTime", 20);
        onlineService.put("problemSolved", (new Random()).nextBoolean());

        currentVariables.put("onlineService", onlineService);

        return currentVariables;
    }

}
