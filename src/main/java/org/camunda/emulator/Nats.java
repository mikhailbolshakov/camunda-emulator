package org.camunda.emulator;


import io.nats.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class Nats {

    private static final Logger logger = LoggerFactory.getLogger("CamundaUserTaskDebugLogger");

    public static Connection connect(boolean allowReconnect) throws Exception {
        Connection nc = io.nats.client.Nats.connect(createConnectionOptions(Options.DEFAULT_URL, allowReconnect));
        return nc;
    }

    private static Options createConnectionOptions(String server, boolean allowReconnect) throws Exception {
        Options.Builder builder = new Options.Builder().
                server(server).
                connectionTimeout(Duration.ofSeconds(5000)).
                pingInterval(Duration.ofSeconds(10)).
                reconnectWait(Duration.ofSeconds(1000)).
                errorListener(new ErrorListener() {
                    public void exceptionOccurred(Connection conn, Exception exp) {
                        logger.error("Exception " + exp.getMessage());
                    }

                    public void errorOccurred(Connection conn, String type) {
                        logger.error("Error " + type);
                    }

                    public void slowConsumerDetected(Connection conn, Consumer consumer) {
                        logger.error("Slow consumer");
                    }
                }).
                connectionListener(new ConnectionListener() {
                    public void connectionEvent(Connection conn, Events type) {
                        logger.info("Status change " + type);
                    }
                });

        if (!allowReconnect) {
            builder = builder.noReconnect();
        } else {
            builder = builder.maxReconnects(-1);
        }

        return builder.build();
    }

    public static void subscribe(Map<String, MessageHandler> handlers) throws Exception {

        Connection connection = connect(true);

        handlers.forEach((topic, handler) -> {

            logger.info(String.format("Handler '%s' is listening for '%s' subject...\n", handler.getClass().toString(), topic));

            //CountDownLatch latch = new CountDownLatch(1000);

            Dispatcher dispatcher = connection.createDispatcher((msg) -> {

                String str = new String(msg.getData(), StandardCharsets.UTF_8);

                logger.info(String.format("Message received: %s", str));

                handler.onMessage(msg);

                //latch.countDown();
            });

            dispatcher.subscribe(topic);

            //latch.await();

        });

    }

    public static void publish(String subject, String message) {

        try {

            logger.info(String.format("Sending message for %s subject....\n", subject));

            Connection conn = null;

            try {
                conn = connect(false);
                conn.publish(subject, message.getBytes(StandardCharsets.UTF_8));
                conn.flush(Duration.ZERO);
            } finally {
                if (conn != null)
                    conn.close();
            }

        } catch (Exception exp) {
            logger.error(exp.toString());
            exp.printStackTrace();
        }
    }

}
