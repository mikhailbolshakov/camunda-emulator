package org.camunda.emulator.messageHandlers;

import io.nats.client.Message;
import io.nats.client.MessageHandler;


public class dumpHandler implements MessageHandler {

    public static String MESSAGE_SUBJECT = "camunda.dump";

    @Override
    public void onMessage(Message message) throws InterruptedException {
    }

}
