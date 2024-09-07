package com.main.janBnb.util;
import com.twilio.type.PhoneNumber;
import com.main.janBnb.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String to, String body) {
        Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(twilioConfig.getPhoneNumber()),
                        body)
                .create();
    }
}