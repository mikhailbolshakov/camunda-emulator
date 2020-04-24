package org.camunda.emulator.messageHandlers.autoService;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class FeedbackUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject feedBack = new JSONObject();
        feedBack.put("rate", (new Random()).nextInt(9) + 1);
        currentVariables.put("feedBack", feedBack);

        return currentVariables;
    }

}
