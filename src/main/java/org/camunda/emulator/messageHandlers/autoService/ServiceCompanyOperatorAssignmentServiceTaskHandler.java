package org.camunda.emulator.messageHandlers.autoService;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.camunda.emulator.messageHandlers.base.BaseServiceTaskHandler;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class ServiceCompanyOperatorAssignmentServiceTaskHandler extends BaseServiceTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        Random r = new Random();
        int index = r.nextInt(3) + 1;
        currentVariables.put("operatorUserId", String.format("scOperator%d", index));

        return currentVariables;
    }

}
