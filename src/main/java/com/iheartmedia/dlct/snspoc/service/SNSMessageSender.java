package com.iheartmedia.dlct.snspoc.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SNSMessageSender {

    private String topicName;

    private String topicArn;

    private AmazonSNS sns;

    private NotificationMessagingTemplate notificationMessagingTemplate;

    @Autowired
    public SNSMessageSender(@Value("${sns.topicName}") String topicName,
                            @Value("${sns.topicArn}") String topicArn,
                            @Value("${cloud.aws.credentials.accessKey}") String accessKey,
                            @Value("${cloud.aws.credentials.secretKey}") String secretKey){
        this.topicName = topicName;
        this.topicArn = topicArn;
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        sns = AmazonSNSClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_2).build();
        notificationMessagingTemplate = new NotificationMessagingTemplate(sns);
    }

    public void sendMessage(String message, String subject) {
        notificationMessagingTemplate.sendNotification(topicName, message, subject);
    }

    public void emailSubscribe(String email) {
        final SubscribeRequest subscribeRequest = new SubscribeRequest(topicArn, "email", email);
        sns.subscribe(subscribeRequest);
    }

}
