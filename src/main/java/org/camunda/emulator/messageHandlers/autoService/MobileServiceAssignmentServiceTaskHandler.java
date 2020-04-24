package org.camunda.emulator.messageHandlers.autoService;

import org.camunda.emulator.messageHandlers.base.BaseServiceTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class MobileServiceAssignmentServiceTaskHandler extends BaseServiceTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        Random r = new Random();
        int index = r.nextInt(2) + 1;
        currentVariables.put("mobileUserId", String.format("mobService%d", index));

        return currentVariables;
    }

}
