package org.camunda.emulator.messageHandlers.clientFeedback;

import org.camunda.emulator.messageHandlers.base.BaseServiceTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class FeedbackAssignTeleconsultantServiceTaskHandler extends BaseServiceTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject feedback = currentVariables.opt("feedback") != null
            ? currentVariables.getJSONObject("feedback")
            :new JSONObject();

        Random r = new Random();
        int index = r.nextInt(1) + 1;
        feedback.put("teleconsultantUserId", String.format("teleConsultant%d", index));

        currentVariables.put("feedback", feedback);

        return currentVariables;
    }

}
