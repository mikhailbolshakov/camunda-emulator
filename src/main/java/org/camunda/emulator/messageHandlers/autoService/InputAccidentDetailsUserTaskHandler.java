package org.camunda.emulator.messageHandlers.autoService;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class InputAccidentDetailsUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject accident = new JSONObject();
        accident.put("location", "Moscow, st.Kutuzovskaya, 1");
        accident.put("reason", "Engine doesn't start");

        currentVariables.put("accident", accident);

        return currentVariables;
    }

}
