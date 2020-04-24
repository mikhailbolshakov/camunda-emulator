package org.camunda.emulator.messageHandlers.autoService;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.camunda.emulator.messageHandlers.base.BaseServiceTaskHandler;
import org.json.JSONArray;
import org.json.JSONObject;


public class RetrieveCarDetailsServiceTaskHandler extends BaseServiceTaskHandler {

    @Override
    protected JSONObject setVariables(JSONObject currentVariables) {

        JSONObject car = new JSONObject();
        car.put("mark", "KIA");
        car.put("model", "SOUL");
        car.put("number", "e720mo190");

        currentVariables.put("car", car);

        return currentVariables;
    }

}
