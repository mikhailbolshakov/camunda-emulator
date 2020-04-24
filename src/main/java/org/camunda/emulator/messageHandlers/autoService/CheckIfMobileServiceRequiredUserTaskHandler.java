package org.camunda.emulator.messageHandlers.autoService;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class CheckIfMobileServiceRequiredUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        currentVariables.put("mobileServiceRequired", (new Random()).nextBoolean());
        return currentVariables;
    }

}
