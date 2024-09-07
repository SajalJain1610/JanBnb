package com.main.janBnb.util;

import com.main.janBnb.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppService {

    private final TwilioConfig twilioConfig;

    @Autowired
    public WhatsAppService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendWhatsappMessage(String to, String body) {
        Message.creator(
                        new PhoneNumber("whatsapp:" + to),
                        new PhoneNumber(twilioConfig.getWhatsappSandboxNumber()),
                        body)
                .create();
    }
}
