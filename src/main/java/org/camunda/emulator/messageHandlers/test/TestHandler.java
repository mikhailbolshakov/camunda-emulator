package org.camunda.emulator.messageHandlers.test;

import io.nats.client.Message;
import io.nats.client.MessageHandler;
import org.camunda.emulator.Nats;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

public class TestHandler implements MessageHandler {

    @Override
    public void onMessage(Message message) throws InterruptedException {
        String str = new String(message.getData(), StandardCharsets.UTF_8);
        JSONObject jobj = new JSONObject(str);

        JSONObject variables = jobj.getJSONObject("context").getJSONObject("variables");
        String taskExecutionId = jobj.getString("taskExecutionId");

        variables.put("testVariable", "testValue");

        JSONObject completeMsg = new JSONObject();
        completeMsg.put("taskExecutionId", taskExecutionId);
        completeMsg.put("variables", variables);

        Nats.publish("service-task.completion", completeMsg.toString());

    }
}
