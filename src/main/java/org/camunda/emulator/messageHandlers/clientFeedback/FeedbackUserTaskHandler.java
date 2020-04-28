package org.camunda.emulator.messageHandlers.clientFeedback;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class FeedbackUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) throws InterruptedException {

        Random r = new Random();

        // how much time a client needs to give a feedback
        Thread.sleep(r.nextInt(15) * 1000);

        JSONObject feedback = new JSONObject();
        feedback.put("rate", (new Random()).nextInt(9) + 1);
        currentVariables.put("feedback", feedback);

        return currentVariables;
    }

}
