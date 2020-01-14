package com.iheartmedia.dlct.snspoc.controller;

import com.iheartmedia.dlct.snspoc.models.SimpleModel;
import com.iheartmedia.dlct.snspoc.service.SNSMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sns")
public class MessageController {

    @Autowired
    SNSMessageSender snsMessageSender;

    @PostMapping(value = "/send")
    public void sendMessageToSNS(@RequestBody SimpleModel message, @RequestParam("subject") String subject){
        snsMessageSender.sendMessage(message, subject);
    }

    @PostMapping(value = "/subscribe")
    public void emailSubScribe(@RequestParam("email") String email){
        snsMessageSender.emailSubscribe(email);
    }

}
