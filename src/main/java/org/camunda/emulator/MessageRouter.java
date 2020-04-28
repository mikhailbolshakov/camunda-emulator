package org.camunda.emulator;

import io.nats.client.MessageHandler;
import org.camunda.emulator.messageHandlers.autoService.*;
import org.camunda.emulator.messageHandlers.clientFeedback.FeedbackAssignTeleconsultantServiceTaskHandler;
import org.camunda.emulator.messageHandlers.clientFeedback.FeedbackTeleconsultantCallUserTaskHandler;
import org.camunda.emulator.messageHandlers.clientFeedback.FeedbackUserTaskHandler;
import org.camunda.emulator.messageHandlers.dumpHandler;

import java.util.HashMap;
import java.util.Map;

public class MessageRouter {

    public static Map<String, MessageHandler> getMapping() {

        Map<String, MessageHandler> mapping = new HashMap<String, MessageHandler>();

        mapping.put(dumpHandler.MESSAGE_SUBJECT, new dumpHandler());
        mapping.put("auto.mobile-service.client.car-details.s-task", new RetrieveCarDetailsServiceTaskHandler());
        mapping.put("auto.mobile-service.client.accident-details.u-task", new InputAccidentDetailsUserTaskHandler());
        mapping.put("auto.mobile-service.client.confirm-rq.u-task", new ConfirmRequestUserTaskHandler());
        mapping.put("auto.mobile-service.service-company.assign-operator.s-task", new ServiceCompanyOperatorAssignmentServiceTaskHandler());
        mapping.put("auto.mobile-service.service-company.mob-required.u-task", new CheckIfMobileServiceRequiredUserTaskHandler());
        mapping.put("auto.mobile-service.service-company.assign-mobile.s-task", new MobileServiceAssignmentServiceTaskHandler());
        mapping.put("auto.mobile-service.service-company.provide-online-service.u-task", new ProvideOnlineServiceUserTaskHandler());
        mapping.put("auto.mobile-service.service-company.provide-mob-service.u-task", new ProvideMobileServiceUserTaskHandler());

        //mapping.put("client-feedback.client.feedback.u-task", new FeedbackUserTaskHandler());
        mapping.put("client-feedback.teleconsultant.assign.s-task", new FeedbackAssignTeleconsultantServiceTaskHandler());
        mapping.put("client-feedback.teleconsultant.call-client.u-task", new FeedbackTeleconsultantCallUserTaskHandler());

        //mapping.put("test-task", new TestHandler());

        return mapping;
    }






}
