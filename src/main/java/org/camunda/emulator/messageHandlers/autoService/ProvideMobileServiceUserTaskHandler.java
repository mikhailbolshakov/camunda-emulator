package org.camunda.emulator.messageHandlers.autoService;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class ProvideMobileServiceUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {
        JSONObject mobileService = new JSONObject();
        mobileService.put("serviceTime", (new Random()).nextInt(100));
        currentVariables.put("mobileService", mobileService);
        return currentVariables;
    }

}
