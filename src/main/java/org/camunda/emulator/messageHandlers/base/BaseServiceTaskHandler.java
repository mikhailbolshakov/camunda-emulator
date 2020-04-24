package org.camunda.emulator.messageHandlers.base;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public abstract class BaseServiceTaskHandler implements MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger("CamundaUserTaskDebugLogger");

    protected JSONObject setVariables(JSONObject currentVariables) {
        return currentVariables;
    }

    @Override
    public void onMessage(Message message) throws InterruptedException {

        String str = new String(message.getData(), StandardCharsets.UTF_8);

        logger.debug(String.format("%s.onMessage. Message: %s", this.getClass().getSimpleName(), str));

        JSONObject jobj = new JSONObject(str);

        JSONObject variables = jobj.getJSONObject("context").getJSONObject("variables");
        String taskExecutionId = jobj.getString("taskExecutionId");

        variables = setVariables(variables);

        JSONObject completeMsg = new JSONObject();
        completeMsg.put("taskExecutionId", taskExecutionId);
        completeMsg.put("variables", variables);

        Thread.sleep(2000);

        Nats.publish("service-task.completion", completeMsg.toString());

    }
}
