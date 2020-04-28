package org.camunda.emulator.messageHandlers.clientFeedback;

import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.util.Random;

public class FeedbackTeleconsultantCallUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        Random r = new Random();

        JSONObject feedback = currentVariables.opt("feedback") != null
                ? currentVariables.getJSONObject("feedback")
                :new JSONObject();

        boolean obtained = r.nextBoolean();
        feedback.put("obtained", obtained);

        if(obtained) {
            feedback.put("rate", (new Random()).nextInt(9) + 1);
        }
        else {
            feedback.put("appointedNextCall", r.nextBoolean());

        }

        currentVariables.put("feedBack", feedback);

        return currentVariables;
    }

}
