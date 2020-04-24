package org.camunda.emulator.messageHandlers.autoService;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.camunda.emulator.messageHandlers.base.BaseUserTaskHandler;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class ConfirmRequestUserTaskHandler extends BaseUserTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject confirmation = new JSONObject();
        confirmation.put("confirmation-date", "2020-01-01");
        currentVariables.put("confirmation", confirmation);

        return currentVariables;
    }

}
